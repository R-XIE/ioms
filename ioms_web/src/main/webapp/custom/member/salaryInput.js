var salaryLocal = '/member/salaryAction';
var chosenStaff;
var validateParam = {
	rules : {
		salaryStaff:{
			required : true,
			salaryStaffValidate:true
		},
		salaryAttendance:{
			required : true,
			number : true,
			min : 0,
			salary_attendance:true
		},
		salaryOvertime:{
			number : true,
			min : 0,
			salary_overtime:true
		},
		salaryFactAttendance : {
			required : true,
			number : true,
			min : 0
		},
		salaryBase : {
			required : true,
			number : true,
			min : 0
		},
		salaryPosition : {
			required : true,
			number : true,
			min : 0
		},
		salaryPerformance : {
			required : true,
			number : true,
			min : 0
		},
		salaryAdd : {
			number : true,
			min : 0
		},
		salarySupply : {
			number : true,
			min : 0
		},
		salaryAbsence : {
			number : true,
			min : 0
		},
		salaryPerformanceSub : {
			number : true,
			min : 0
		},
		salaryLate : {
			number : true,
			min : 0
		},
		salaryInsurance : {
			number : true,
			min : 0
		},
		salaryOther : {
			number : true,
			min : 0
		},
		salaryTax : {
			number : true,
			min : 0
		}
	},
	messages : {
		salaryStaff:{
			required : "姓名不能为空",
		},
		salaryAttendance : {
			required : "应出勤不能为空",
		},
		salaryFactAttendance : {
			required : "实出勤不能为空",
		},
		salaryBase : {
			required : "基本薪资不能为空",
		},
		salaryPosition : {
			required : "岗位薪资不能为空",
		},
		salaryPerformance : {
			required : "绩效薪资不能为空",
		}
	},
	onfocusout : function(element) {
		$(element).valid();
	}
};
var validate;
var editParam;
var addParam;
function initNotView() {
	$('#flag_fact').val($('#salaryFactAttendance').val());
	$.extend($.validator.messages, {
		number : "请输入合法的数字",
		min : $.validator.format("请输入一个最小为{0} 的值")
	});
	$.validator.addMethod("salaryStaffValidate", function(value, element) { 
		var param={
				staffId:$('#salaryStaff').val(),//用户
				salaryDateMonths:$("#salaryDateMonths").val(),//月份
				salaryId:$('#salaryId').val()//id
		};
		//在common的JS中写的
		return validateAjax(ctx+salaryLocal+"Validate.htm",param);
	}, "该用户在上月的工资条已经存在.");
	//为不同用户获取不同的上月薪资 并填补上去
	$('#salaryStaff').change(function(){
		var staffId=$('#salaryStaff').val();
		var months= $("#salaryDateMonths").val();
		$.ajax({
			type : "post",
			url : ctx + salaryLocal + "LastSalary.htm",
			data : {
				staffId : staffId,
				months:months
			},
			dataType:"json",
			success : function(data) {
				if(data!=null){
					$('#salaryBase').val(data.salaryBase);
					$('#salaryPosition').val(data.salaryPosition);
					$('#salaryPerformance').val(data.salaryPerformance);
					$('#salaryAdd').val(data.salaryAdd);
					$('#salarySupply').val(data.salarySupply);
				}
			}
		});
	});
	//应出勤验证
	$.validator.addMethod("salary_attendance", function(value, element) { 
		return validateSalaryAttFact();
	}, "应出勤或用户输入有误.");
	//加班验证
	$.validator.addMethod("salary_overtime", function(value, element) { 
		return validateSalaryAttFact();
	}, "应出勤或用户输入有误.");
	validate = $("#input_salary_form").validate(validateParam);
}
//计算实际出勤,验证基本不会触发,计算实际出勤=应出勤+加班-请假
function validateSalaryAttFact(){
	var staffId=$('#salaryStaff').val();//用户ID
	var attendance=$('#salaryAttendance').val();//应出勤
	var overtime=$('#salaryOvertime').val();//加班
	var months= $("#salaryDateMonths").val();//月份
	var flag=false;
	if(staffId!=null && attendance!=null){
		$.ajax({
			type : "post",
			url : ctx + salaryLocal + "SalaryFact.htm",
			data : {
				staffId : staffId,
				salary : attendance,
				overtime:overtime,
				months:months
			},
			success : function(data) {
				$('#salaryFactAttendance').attr('value',data);
			}
		});
		flag=true;
	}else{
		flag=false;
	}
	return flag;
}

function ck_salary() {
	blockPost(ctx + salaryLocal + 'ListAjax.htm');
}
function save(id) {
	var dialog = new DialogFunc("提示信息", '保存基本信息成功', "success").init('success',
			'hiddenDialog');
	if ($('#input_salary_form').valid()) {
		var json = form_serialize({
			salaryStaff : $('#salaryStaff').val()
		});
		if (id == null || id == '') {
			addParam = json;
			
			$.ajax({
				type : "post",
				url : ctx + salaryLocal + "Save.htm",
				data : addParam,
				success : function(data) {
					if (data == true) {
						dialog = new DialogFunc("提示信息", '新增薪资信息成功.', "success")
								.init('success', 'hiddenDialog');
						dialog.show();
						ck_salary();
					} else {
						dialog = new DialogFunc("错误", '新增异动信息失败', "alert")
								.init('alert', 'hiddenDialog');
						dialog.show();
					}
				},
				error : function(e) {
					dialog.hide();
					$('#hiddenDialog').html();
					$('div[class="modal-backdrop fade in"]').remove();
					dialog = new DialogFunc("错误", 'JS执行错误', "alert").init(
							'alert', 'hiddenDialog');
					dialog.show();
				}
			});
		} else {
			editParam = json;
			editParam['salaryId'] = id;
			$.ajax({
				type : "post",
				url : ctx + salaryLocal + "Save.htm",
				data : editParam,
				success : function(data) {
					if (data == true) {
						dialog = new DialogFunc("提示信息", '保存薪资信息成功', "success")
								.init('success', 'hiddenDialog');
						dialog.show();
						ck_salary();
					} else {
						dialog = new DialogFunc("错误", '保存薪资信息失败', "alert")
								.init('alert', 'hiddenDialog');
						dialog.show();
					}
				},
				error : function(e) {
					dialog.hide();
					$('#hiddenDialog').html();
					$('div[class="modal-backdrop fade in"]').remove();
					dialog = new DialogFunc("错误", 'JS执行错误', "alert").init(
							'alert', 'hiddenDialog');
					dialog.show();
				}
			});
		}
	}
}