var backupLocal = '/operation/backupAction';
var validate;
var editParam;
var addParam;
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
var chosenParam={no_results_text : '没有匹配结果'};
var validateParam = {
		rules : {
			backupDate:{
				required:true
			},
			backupServer:{
				required:true
			},
			backupTitle:{
				required:true
			}
		},
		messages : {
			backupDate:{
				required:"备份日期不能为空"
			},
			backupServer:{
				required:"备份服务器不能为空"
			},
			backupTitle:{
				required:"备份内容不能为空"
			}
		},
		onfocusout : function(element) {
			$(element).valid();
		}
};
var backupStateChosen;
function ck_backup() {
	blockPost(ctx + backupLocal + 'ListAjax.htm');
//	blockPost(ctx + backupLocal + 'List.htm');
}
function initNotView() {
	$('#backupDate').datetimepicker(dataParam);
	backupStateChosen=$('#backupState').chosen(chosenParam);
	validate = $("#input_backup_form").validate(validateParam);
}
function save(id) {
	var dialog = new DialogFunc("提示信息", '保存备份记录成功', "success").init('success',
			'hiddenDialog');
	if ($('#input_backup_form').valid()) {
			var json=form_serialize({backupState:backupStateChosen.val()});
			if (id == null || id == '') {
				addParam =json;
				$.ajax({
					type : "post",
					url : ctx + backupLocal + "Save.htm",
					data : addParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '新增备份记录成功.',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_backup();
						} else {
							dialog = new DialogFunc("错误", '新增备份记录失败', "alert")
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
				editParam['backupId']=id;
				$.ajax({
					type : "post",
					url : ctx + backupLocal + "Save.htm",
					data : editParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '保存备份记录成功',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_backup();
						} else {
							dialog = new DialogFunc("错误", '保存备份记录失败', "alert")
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