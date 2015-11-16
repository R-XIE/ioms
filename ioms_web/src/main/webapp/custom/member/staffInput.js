var staffLocal = '/member/staffAction';
var chosenPostId;
var chosenPositionId;
var chosenRoleId;
var chosenStaffDegree;
var chosenStaffState;
var toggleSexButton;
var dataParam = {
	language : 'zh-CN',
	format : 'yyyy-mm-dd',
	weekStart : 1,
	todayBtn : 1,
	autoclose : 1,
	todayHighlight : 1,
	startView : 2,
	minView : 2,
	forceParse : 0
};
var dateStaffMajorDate;
var dateStaffEntryDate;
var dateStaffContractDate;
var validateParam = {
	rules : {
		staffCode : {
			required : true,
		},
		staffLoginName : {
			required : true,
			loginNameValidate :true
		},
		staffRealName : {
			required : true,
		},
		staffIdCard : {
			required : true,
			isIdCard : true
		},
		staffPhone : {
			required : true,
			isPhone : true
		},
		staffEmergency : {
			required : true,
			minlength : 5
		},
		staffEmail : {
			required : true,
			email : true
		},
		staffAddress : {
			required : true,
			minlength : 5
		},
		staffGraduation : {
			required : true,
		},
		staffMajor : {
			required : true,
		},
		staffMajorDate : {
			required : true,
		},
		staffEntryDate : {
			required : true,
		},
		staffContractDate : {
			required : true,
		}
	},
	messages : {
		staffCode : {
			required : "编号不能为空"
		},
		staffLoginName : {
			required : "帐号不能为空",
		},
		staffRealName : {
			required : "姓名不能为空"
		},
		staffIdCard : {
			required : "身份证号不能为空",
			isIdCard : "请正确填写身份证号"
		},
		staffPhone : {
			required : "联系电话不能为空",
			isPhone : "请正确填写联系电话"
		},
		staffAddress : {
			required : "住址不能为空",
			minlength : "住址不得少于5个字符"
		},
		staffGraduation : {
			required : "毕业院校不能为空"
		},
		staffMajor : {
			required : "专业不能为空"
		},
		staffMajorDate : {
			required : "毕业日期不能为空"
		},
		staffEntryDate : {
			required : "入职日期不能为空"
		},
		staffContractDate : {
			required : "签订合同日期不能为空"
		},
		staffEmergency : {
			required : "紧急联系方式不能为空",
			minlength : "紧急联系方式不得少于5个字符"
		},
		staffEmail : {
			required : "邮箱地址不能为空",
			email : "请输入正确的邮箱地址"
		}
	},
	onfocusout : function(element) {
		$(element).valid();
	}
};
var validate;
var editParam;
var addParam;
$(function() {

});
function initNotView() {
	chosenPostId = $("#postId").chosen();
	chosenPositionId = $("#positionId").chosen();
	chosenRoleId = $("#roleId").chosen();
	chosenStaffDegree = $("#staffDegree").chosen();
	chosenStaffState = $("#staffState").chosen();
	// validate=$("#input_position_form").validate(param);
	toggleSexButton = $('#sex_buttons').toggleButtons({
		label : {
			enabled : "男",
			disabled : "女"
		},
		style : {
			enabled : "info",
			disabled : "danger"
		}
	});
	dateStaffMajorDate = $('#staffMajorDate').datetimepicker(dataParam);
	dateStaffEntryDate = $('#staffEntryDate').datetimepicker(dataParam);
	dateStaffContractDate = $('#staffContractDate').datetimepicker(dataParam);
	var regTel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
	var regMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	jQuery.validator.addMethod("isMobile", function(value, element) {
		var length = value.length;
		var mobile = regMobile;
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "请正确填写您的手机号码");

	jQuery.validator.addMethod("loginNameValidate", function(value, element) {
		var param={
				staffLoginName:$("#staffLoginName").val()	
		};
		return validateAjax(ctx+staffLocal+"Validate.htm",param);
	}, "该帐号已经被注册");

	// 电话号码验证
	jQuery.validator.addMethod("isTel", function(value, element) {
		var tel = regTel; // 电话号码格式010-12345678
		return this.optional(element) || (tel.test(value));
	}, "请正确填写您的电话号码");
	// 联系电话(手机/电话皆可)验证
	jQuery.validator.addMethod("isPhone", function(value, element) {
		var mobile = regMobile;
		var tel = regTel;
		return this.optional(element)
				|| (tel.test(value) || mobile.test(value));

	}, "请正确填写您的联系电话");
	jQuery.validator.addMethod("isIdCard", function(value, element) {
		return this.optional(element) || IdentityCodeValid(value);

	}, "请正确填写您的身份证号");
	validate = $("#input_staff_form").validate(validateParam);
}
function ck_index() {
	blockPost(ctx+'/welcome/main.htm');
}
function ck_staff(isLeave) {
	blockPost(ctx + staffLocal + 'List.htm?isLeave='+isLeave);
}
function save(id) {
	var sex;
	if ($('#staffSex').attr('checked') == 'checked') {
		sex = 0;
	} else {
		sex = -1;
	}
	var dialog = new DialogFunc("提示信息", '修改信息成功', "success").init('success',
			'hiddenDialog');
	if ($('#input_staff_form').valid()) {
		if (id == null || id == '') {
			addParam = {
				staffCode : $('#staffCode').val(),
				staffLoginName : $('#staffLoginName').val(),
				staffRealName : $('#staffRealName').val(),
				staffSex : sex,
				positionId : chosenPositionId.val(),
				postId : chosenPostId.val(),
				roleId : chosenRoleId.val(),
				staffIdCard : $('#staffIdCard').val(),
				staffPhone : $('#staffPhone').val(),
				staffAddress : $('#staffAddress').val(),
				staffDegree : chosenStaffDegree.val(),
				staffEmail : $('#staffEmail').val(),
				staffEmergency : $('#staffEmergency').val(),
				staffGraduation : $('#staffGraduation').val(),
				staffMajor : $('#staffMajor').val(),
				staffMajorDate : $('#staffMajorDate').val(),
				staffEntryDate : $('#staffEntryDate').val(),
				staffContractDate : $('#staffContractDate').val(),
				staffState : chosenStaffState.val(),
			};
			$.ajax({
				type : "post",
				url : ctx + staffLocal + "Save.htm",
				data : addParam,
				success : function(data) {
					if (data == true) {
						dialog = new DialogFunc("提示信息", '新增员工成功,请继续新增',
								"success").init('success', 'hiddenDialog');
						dialog.show();
						$.post(ctx + staffLocal + 'Input.htm', {}, function(
								data) {
							$("#main-content").html(data);
						});
					} else {
						dialog = new DialogFunc("错误", '新增用户失败', "alert").init(
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
				staffId : id,
				staffCode : $('#staffCode').val(),
				staffLoginName : $('#staffLoginName').val(),
				staffRealName : $('#staffRealName').val(),
				staffSex : sex,
				positionId : chosenPositionId.val(),
				postId : chosenPostId.val(),
				roleId : chosenRoleId.val(),
				staffIdCard : $('#staffIdCard').val(),
				staffPhone : $('#staffPhone').val(),
				staffAddress : $('#staffAddress').val(),
				staffDegree : chosenStaffDegree.val(),
				staffEmail : $('#staffEmail').val(),
				staffEmergency : $('#staffEmergency').val(),
				staffGraduation : $('#staffGraduation').val(),
				staffMajor : $('#staffMajor').val(),
				staffMajorDate : $('#staffMajorDate').val(),
				staffEntryDate : $('#staffEntryDate').val(),
				staffContractDate : $('#staffContractDate').val(),
				staffState : chosenStaffState.val(),
			};
			$.ajax({
				type : "post",
				url : ctx + staffLocal + "Save.htm",
				data : editParam,
				success : function(data) {
					if (data == true) {
						dialog = new DialogFunc("提示信息", '编辑员工成功', "success")
								.init('success', 'hiddenDialog');
						dialog.show();
						ck_staff(false);
					} else {
						dialog = new DialogFunc("错误", '编辑员工失败', "alert").init(
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