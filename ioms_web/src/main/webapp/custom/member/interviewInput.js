var interviewLocal = '/member/interviewAction';
var chosenPosition;
var chosenCurrent;
var chosenResult;
var toggleSexButton;
var dataParam = {
	language : 'zh-CN',
	format : 'yyyy-mm-dd hh:ii',
	pickDate: true,
    pickTime: true,
    showMeridian: true,
    hourStep: 1,
    minuteStep: 5,
	todayBtn : 1,
	autoclose : 1,
	inputMask: true
};
var interviewDate;
var validateParam = {
	rules : {
		interviewName : {
			required : true,
		},
		interviewToName : {
			required : true,
		},
		interviewDate : {
			required : true
		},
		interviewPhone : {
			required : true,
			isPhone : true
		},
		interviewCurrentWork : {
			required : true,
		},
		interviewQuit : {
			required : true,
		},
		interviewSalary : {
			required : true,
			min:0,
			max:100000
		},
		interviewScore : {
			required : true,
			min:0,
			max:10
		}
	},
	messages : {
		interviewName : {
			required : "应聘人姓名不能为空",
		},
		interviewToName : {
			required :  "面试官姓名不能为空",
		},
		interviewDate : {
			required :  "面试时间不能为空",
		},
		interviewPhone : {
			required : "应聘人联系方式不能为空",
			isPhone : "应聘人联系方式格式不正确,应为电话号或者手机号",
		},
		interviewCurrentWork : {
			required : "应聘人之前工作单位不能为空"
		},
		interviewQuit : {
			required :"应聘人跳槽原因不能为空",
		},
		interviewSalary : {
			required : "应聘人期望薪资不能为空",
			min:"应聘人期望薪资最少为￥0",
			max:"应聘人期望薪资最多为￥100000"
		},
		interviewScore : {
			required : "应聘人最后评分不能为空",
			min:"应聘人最后评分最少为0",
			max:"应聘人最后评分最多为10"
		}
	},
	onfocusout : function(element) {
		$(element).valid();
	}
};
var validate;
var editParam;
var addParam;
function download(cv){
	var out=$('#id').val()+$('#interviewName').val()+"的简历"+
	$('#interviewDate').val().split(' ')[0].replace('-','').replace('-','');
	out=encodeURI(out);
	window.location = ctx + interviewLocal + "Download.htm?fileName="+cv+"&out="+out;
}
function initNotView() {
	chosenPosition = $("#interviewPosition").chosen();
	chosenCurrent = $("#interviewCurrent").chosen();
	chosenResult = $("#interviewResult").chosen();
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
	interviewDate = $('#interviewDate').datetimepicker(dataParam);
	var regTel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
	var regMobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
	//var regScore=
	jQuery.validator.addMethod("isMobile", function(value, element) {
		var length = value.length;
		var mobile = regMobile;
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "请正确填写您的手机号码");

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
	validate = $("#input_interview_form").validate(validateParam);
}
function ck_index() {
	blockPost(ctx+'/welcome/main.htm');
}
function ck_interview(id) {
	blockPost(ctx + interviewLocal + 'ListAjax.htm');
}
function upload(id){
	blockPost(ctx+interviewLocal+'UploadView.htm',{recordId:id});
}
function save(id) {
	var sex;
	if ($('#interviewSex').attr('checked') == 'checked') {
		sex = 0;
	} else {
		sex = -1;
	}
	var dialog = new DialogFunc("提示信息", '保存基本信息成功', "success").init('success',
			'hiddenDialog');
	if ($('#input_interview_form').valid()) {
		if (id == null || id == '') {
			addParam = {
				interviewName : $('#interviewName').val(),
				interviewToName : $('#interviewToName').val(),
				interviewSex : sex,
				interviewPosition : chosenPosition.val(),
				interviewDate : $('#interviewDate').val(),
				interviewPhone : $('#interviewPhone').val(),
				interviewCurrent : chosenCurrent.val(),
				interviewCurrentWork : $('#interviewCurrentWork').val(),
				interviewQuit : $('#interviewQuit').val(),
				interviewSalary : $('#interviewSalary').val(),
				interviewScore : $('#interviewScore').val(),
				interviewResult : chosenResult.val()
			};
			$.ajax({
				type : "post",
				url : ctx + interviewLocal + "Save.htm",
				data : addParam,
				success : function(data) {
					if (data == true) {
						dialog = new DialogFunc("提示信息", '新增面试信息成功.',
								"success").init('success', 'hiddenDialog');
						dialog.show();
						ck_interview();
					} else {
						dialog = new DialogFunc("错误", '新增面试信息失败', "alert").init(
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
				interviewId : id,
				interviewName : $('#interviewName').val(),
				interviewToName : $('#interviewToName').val(),
				interviewSex : sex,
				interviewPosition : chosenPosition.val(),
				interviewDate : $('#interviewDate').val(),
				interviewPhone : $('#interviewPhone').val(),
				interviewCurrent : chosenCurrent.val(),
				interviewCurrentWork : $('#interviewCurrentWork').val(),
				interviewQuit : $('#interviewQuit').val(),
				interviewSalary : $('#interviewSalary').val(),
				interviewScore : $('#interviewScore').val(),
				interviewResult : chosenResult.val()
			};
			$.ajax({
				type : "post",
				url : ctx + interviewLocal + "Save.htm",
				data : editParam,
				success : function(data) {
					if (data == true) {
						dialog = new DialogFunc("提示信息", '保存基本信息成功', "success")
								.init('success', 'hiddenDialog');
						dialog.show();
						ck_interview();
					} else {
						dialog = new DialogFunc("错误", '保存基本信息失败', "alert").init(
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