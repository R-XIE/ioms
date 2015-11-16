var weekly = '/welcome/weekly';
var validateParam = {
	rules : {
		weeklyLastSummary:{
			required:true
		},
		weeklyThisSummary:{
			required:true
		},
		weeklyNextSummary:{
			required:true
		}
	},
	messages : {
		weeklyLastSummary : {
			required : "上周工作总结不能为空",
		},
		weeklyThisSummary : {
			required : "本周工作内容不能为空",
		},
		weeklyNextSummary : {
			required : "下周工作计划不能为空"
		}
	},
	onfocusout : function(element) {
		$(element).valid();
	}
};
var validate;
var editParam;
var addParam;
var chosenStaff;
function initNotView() {
	chosenStaff = $("#weeklyStaff").chosen({
		no_results_text : '没有匹配结果'
	});
	validate = $("#input_weekly_form").validate(validateParam);
}
function ck_weekly() {
	blockPost(ctx + weekly + 'ListAjax.htm');
}
function save(id) {
	var dialog = new DialogFunc("提示信息", '保存基本信息成功', "success").init('success',
			'hiddenDialog');
	if ($('#input_weekly_form').valid()) {
			var weeklyBeginDate =$('#weeklyDate').val().split(' - ')[0];
			var weeklyEndDate =$('#weeklyDate').val().split(' - ')[1];
			var json=form_serialize({weeklyStaff:chosenStaff.val()});
			json['weeklyBeginDate']=weeklyBeginDate;
			json['weeklyEndDate']=weeklyEndDate;
			if (id == null || id == '') {
				addParam =json;
				$.ajax({
					type : "post",
					url : ctx + weekly + "Save.htm",
					data : addParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '新增个人周报成功.',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_weekly();
						} else {
							dialog = new DialogFunc("错误", '新增个人周报失败,一人一周只能填写一次', "alert")
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
				editParam['weeklyId']=id;
				$.ajax({
					type : "post",
					url : ctx + weekly + "Save.htm",
					data : editParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '保存个人周报成功',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_weekly();
						} else {
							dialog = new DialogFunc("错误", '保存个人周报失败,一人一周只能填写一次', "alert")
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