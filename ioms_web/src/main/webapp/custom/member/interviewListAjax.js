var interviewLocal = '/member/interviewAction';
var dataParam = {
		timePicker : true,
		timePickerIncrement : 1,
		format : 'YYYY-MM-DD HH:mm',
		timePicker12Hour:false
};
$(function() {
	$('#interviewDateBE').daterangepicker(dataParam);
	getBootstrapPaginator(interviewLocal);
});
function doAction(fn, id) {
	if (fn == 'del') {
		var dialog = new DialogFunc("询问", '您是否删除这条记录', "confirm").init(
				'confirm', 'hiddenDialog');// loginout
		dialog.show();
		dialog.confirm(function() {
			$.ajax({
				type : "post",
				url : ctx + interviewLocal + "Del.htm",
				data : {
					recordId : id
				},
				success : function(data) {
					dialog.hide();
					$('#hiddenDialog').html();
					$('div[class="modal-backdrop fade in"]').remove();
					if (data == true) {
						new DialogFunc("通知", '删除成功!', "alert").init('alert',
								'hiddenDialog').show();
						$('#index_refresh').trigger('click');

					} else {
						new DialogFunc("通知", '面试信息删除失败!', "alert").init(
								'alert', 'hiddenDialog').show();
					}
				},
				error : function(e) {
					dialog.hide();
					$('#hiddenDialog').html();
					$('div[class="modal-backdrop fade in"]').remove();
					dialog = new DialogFunc("错误", 'JS执行错误', "alert").init(
							'alert', 'hiddenDialogAlert');
					dialog.show();
				}
			});

		});
	} else if (fn == 'add') {
		blockPost(ctx + interviewLocal + 'Input.htm');
	} else if (fn == 'edit') {
		blockPost(ctx + interviewLocal + 'Input.htm', {
			recordId : id
		});
	} else if (fn == 'view') {
		blockPost(ctx + interviewLocal + 'View.htm', {
			recordId : id
		});
	} else if (fn == 'reset') {
		resetCondition();
	} else if (fn == 'search') {
		var param = $('#queryForm').serialize();
		blockPost(ctx + interviewLocal + 'ListAjax.htm', param);
	}
}
function ck_index() {
	blockPost(ctx + '/welcome/main.htm');
}