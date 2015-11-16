var logLocation = '/operation/logAction';
var validateParam = {
	rules : {
		logCause:{
			required:true
		},
		logTitle:{
			required:true
		},
		logContent:{
			required:true
		},
		logLocation:{
			required:true
		}
	},
	messages : {
		logCause:{
			required:"维修原因不能为空"
		},
		logTitle:{
			required:"维修内容不能为空"
		},
		logContent:{
			required:"维修方法不能为空"
		},
		logLocation:{
			required:"服务器地址不能为空"
		}
	},
	onfocusout : function(element) {
		$(element).valid();
	}
};
var validate;
var editParam;
var addParam;
var chosenState;
function initNotView() {
	chosenState= $("#logState").chosen({
		no_results_text : '没有匹配结果'
	});
	validate = $("#input_log_form").validate(validateParam);
}
function ck_log() {
	blockPost(ctx + logLocation + 'ListAjax.htm');
}
function save(id) {
	var dialog = new DialogFunc("提示信息", '保存更改维护记录成功', "success").init('success',
			'hiddenDialog');
	if ($('#input_log_form').valid()) {
			var json=form_serialize({logState:chosenState.val()});
			if (id == null || id == '') {
				addParam =json;
				$.ajax({
					type : "post",
					url : ctx + logLocation + "Save.htm",
					data : addParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '新增更改维护记录成功.',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_log();
						} else {
							dialog = new DialogFunc("错误", '新增更改维护记录失败', "alert")
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
				editParam['logId']=id;
				$.ajax({
					type : "post",
					url : ctx + logLocation + "Save.htm",
					data : editParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '保存更改维护记录成功',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_log();
						} else {
							dialog = new DialogFunc("错误", '保存更改维护记录失败', "alert")
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