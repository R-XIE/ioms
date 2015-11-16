var pwdLocal = '/operation/pwdAction';
var validateParam = {
	rules : {
		pwdName : {
			required : true,
		},
		pwdLocation : {
			required : true,
		},
		pwdRemark : {
			required : true,
		}
	},
	messages : {
		pwdName : {
			required : "敏感信息标题不能为空",
		},
		pwdLocation : {
			required : "敏感信息位置不能为空",
		},
		pwdRemark : {
			required : "敏感信息内容不能为空",
		}
	},
	onfocusout : function(element) {
		$(element).valid();
	}
};
var chosenLevel;
function initNotView() {
	chosenLevel= $("#pwdLevel").chosen({
		no_results_text : '没有匹配结果'
	});
	validate = $("#input_pwd_form").validate(validateParam);
	
}
var validate;
var editParam;
var addParam;
function ck_index() {
	blockPost(ctx + '/welcome/main.htm');
}
function ck_pwd() {
	blockPost(ctx + pwdLocal + 'List.htm');
}
function save(id) {
	var dialog = new DialogFunc("提示信息", '保存敏感信息成功', "success").init('success',
			'hiddenDialog');
	if ($('#input_pwd_form').valid()) {
			if (id == null || id == '') {
				var addParam=form_serialize({pwdLevel:chosenLevel.val()});
				$.ajax({
					type : "post",
					url : ctx + pwdLocal + "Save.htm",
					data : addParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '新增敏感信息成功.',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_pwd();
						} else {
							dialog = new DialogFunc("错误", '新增敏感信息失败', "alert")
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
				editParam =form_serialize({pwdLevel:chosenLevel.val()});
				editParam['pwdId']=id;
				$.ajax({
					type : "post",
					url : ctx + pwdLocal + "Save.htm",
					data : editParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '保存敏感信息成功',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_pwd();
						} else {
							dialog = new DialogFunc("错误", '保存敏感信息失败', "alert")
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