var leaveLocal = '/member/leaveAction';
var chosenStaff;
var chosenLeave;
var dataParam = {
		timePicker : true,
		timePickerIncrement : 1,
		format : 'YYYY-MM-DD HH:mm',
		timePicker12Hour:false
};
var leaveDate;
var validateParam = {
	rules : {
		leaveSubject : {
			required : true,
		},
		leaveDate : {
			required : true,
		},
		leaveDaysDay : {
			digits:true,
			min:1
		},
		leaveDaysHour : {
			min:0.5,
			max:7.5,
		    leaveDays:true
		}
	},
	messages : {
		leaveSubject : {
			required : "请假事由不能为空",
		},
		leaveDate : {
			required : "请假起止时间不能为空",
		},
		leaveDaysDay : {
			min:"请假天数最少为一天",
			digits:"请假天数必须为整数"
		},
		leaveDaysHour : {
			min:"请假小时数最少为半小时",
			max:"请假小时数最多为七个半小时,满8小时为一天"
		}
	},
	onfocusout : function(element) {
		$(element).valid();
	}
};
var validate;
var editParam;
var addParam;
var leaveDays=0;
function initNotView() {
	chosenStaff = $("#staffId").chosen();
	chosenLeave = $("#leaveType").chosen();
	leaveDate = $('#leaveDate').daterangepicker(dataParam);
	$.validator.addMethod("leaveDays", function(value, element) { 
		var daysDay=$('#leaveDaysDay').val();
		if(daysDay=='' && value=='')return false;
		else{
			var days,hours;
			if(daysDay=='')days=0;
			else days=parseInt(daysDay);
			if(value=='')hours=0;
			else hours=parseFloat(value);
			leaveDays=days+hours/8;
			return true;
		}
	}, "请假天数和请假小时数必填一项.");
	validate = $("#input_leave_form").validate(validateParam);
}
function ck_index() {
	blockPost(ctx+'/welcome/main.htm');
}
function ck_leave() {
	blockPost(ctx + leaveLocal + 'ListAjax.htm');
}
function save(id) {
	var dialog = new DialogFunc("提示信息", '保存基本信息成功', "success").init('success',
			'hiddenDialog');
	if ($('#input_leave_form').valid()) {
		var staffId=chosenStaff.val();
		var leaveType=chosenLeave.val();
		var leaveSubject=$('#leaveSubject').val();
		var leaveDate=$('#leaveDate').val();
		var leaveDateBegin=leaveDate.split(' - ')[0];
		var leaveDateEnd=leaveDate.split(' - ')[1];
		if (id == null || id == '') {
			addParam = {
				staffId : staffId,
				leaveType : leaveType,
				leaveSubject : leaveSubject,
				leaveDateBegin:leaveDateBegin,
				leaveDateEnd:leaveDateEnd,
				leaveDays : leaveDays,
			};
			$.ajax({
				type : "post",
				url : ctx + leaveLocal + "Save.htm",
				data : addParam,
				success : function(data) {
					if (data == true) {
						dialog = new DialogFunc("提示信息", '新增请假信息成功.',
								"success").init('success', 'hiddenDialog');
						dialog.show();
						ck_leave();
					} else {
						dialog = new DialogFunc("错误", '新增请假信息失败', "alert").init(
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
				leaveId : id,
				staffId : staffId,
				leaveType : leaveType,
				leaveSubject : leaveSubject,
				leaveDateBegin:leaveDateBegin,
				leaveDateEnd:leaveDateEnd,
				leaveDays : leaveDays,
			};
			$.ajax({
				type : "post",
				url : ctx + leaveLocal + "Save.htm",
				data : editParam,
				success : function(data) {
					if (data == true) {
						dialog = new DialogFunc("提示信息", '保存请假信息成功', "success")
								.init('success', 'hiddenDialog');
						dialog.show();
						ck_leave();
					} else {
						dialog = new DialogFunc("错误", '保存请假信息失败', "alert").init(
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