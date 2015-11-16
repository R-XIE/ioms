var alterLocal = '/member/salaryAlterAction';
var chosenStaff;
var chosenAlter;
var alterDate;
var validateParam = {
	rules : {
		alterBefore : {
			required : true,
			min:0
		},
		alterAfter : {
			required : true,
			afterValidate:true
		},
		alterDate : {
			required : true,
			alterDateValidate :true
		}
	},
	messages : {
		alterBefore : {
			required : "原薪资标准不能为空",
			min:"原薪资标准最少为0"
		},
		alterAfter : {
			required : "异动后薪资标准不能为空",
		},
		alterDate : {
			required : "生效月份不能为空"
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
	chosenStaff = $("#staffId").chosen({no_results_text:'没有匹配结果'});
	chosenAlter = $("#alterCause").chosen({no_results_text:'没有匹配结果'}).change(function(){
		if(chosenAlter.val()==null){//valid
			showError();
		}else{
			removeError();
		}
	});
	alterDate = $('#alterDate').inputmask("y.m",{"placeholder": "yyyy.mm"});
	$.validator.addMethod("afterValidate", function(value, element) { 
		var before= $('#alterBefore').val();
		return (before*1)<(value*1)? true:false;
	}, "异动后薪资标准必须大于原薪资标准");
	$.validator.addMethod("alterDateValidate", function(value, element) { 
        var param = {
        		staffId:$("#staffId").val(),
        		alterId:$("#alterId").val(),
        		alterDate:value
        };
		return validateAjax(ctx+alterLocal+"Validate.htm",param);
	}, "该用户的月份薪资异动信息已经存在");
	removeError();
	validate = $("#input_alter_form").validate(validateParam);
	$('#causeTypeValidate').blur(function(){
		if(chosenAlter.val()==null){//valid
			showError();
		}else{
			removeError();
		}
	});
	
}
function chose_mult_set_ini(values,type){
	var select='#alterCause';
    var arr = values.split(',');
    var length = arr.length;
    var value = '';
    for(var i=0;i<length;i++){
        value = arr[i];
        $(select+" [value='"+value+"']").attr('selected','selected');
    }
    if(type=='ADD') $(select+" [value='"+1+"']").attr('selected','selected');
    $(select).trigger("liszt:updated");
}
function showError(){
	$("#causeTypeError").addClass( "error")
	.html( "异动原因至少选择一项");
	$("#causeTypeError").removeAttr("style");//display: none;
}
function removeError(){
	$("#causeTypeError").removeClass( "error").html( "");
	$("#causeTypeError").css('display','none');
}
function ck_index() {
	blockPost(ctx+'/welcome/main.htm');
}
function ck_alter() {
	blockPost(ctx + alterLocal + 'List.htm');
}
function save(id) {
	var dialog = new DialogFunc("提示信息", '保存基本信息成功', "success").init('success',
			'hiddenDialog');
	var alter=$('#input_alter_form').valid();
	 console.log(alter);
	if ($('#input_alter_form').valid()) {
		if(chosenAlter.val()==null){//valid
			showError();
		}else{
			removeError();
			var staffId=chosenStaff.val();
			var alterCause=chosenAlter.val();
			var alterBefore=$('#alterBefore').val();
			var alterAfter=$('#alterAfter').val();
			var alterDate=$('#alterDate').val();
			if (id == null || id == '') {
				addParam = {
					staffId : staffId,
					alterCause : alterCause.join(','),
					alterBefore : alterBefore,
					alterAfter:alterAfter,
					alterDate:alterDate
				};
				$.ajax({
					type : "post",
					url : ctx + alterLocal + "Save.htm",
					data : addParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '新增薪资异动信息成功.',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_alter();
						} else {
							dialog = new DialogFunc("错误", '新增薪资异动信息失败', "alert").init(
									'alert', 'hiddenDialog');
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
				editParam = {
					alterId : id,
					staffId : staffId,
					alterCause : alterCause.join(','),
					alterBefore : alterBefore,
					alterAfter:alterAfter,
					alterDate:alterDate
				};
				$.ajax({
					type : "post",
					url : ctx + alterLocal + "Save.htm",
					data : editParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '保存薪资异动信息成功', "success")
									.init('success', 'hiddenDialog');
							dialog.show();
							ck_alter();
						} else {
							dialog = new DialogFunc("错误", '保存薪资异动信息失败', "alert").init(
									'alert', 'hiddenDialog');
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
}