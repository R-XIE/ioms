var oTable;
$(function() {
	// EditableTable.init();
	oTable = $('#editable-sample').dataTable(minDataTable);
	
});
function doAction(fn, id) {
	if (fn == 'del') {
		var dialog = new DialogFunc("询问", '您是否删除这条记录', "confirm").init(
				'confirm', 'hiddenDialog');// loginout
		dialog.show();
		dialog.confirm(function() {
			$.ajax({
				type : "post",
				url : biuPushPath + "delete.action",
				data : {
					id : id
				},
				dataType : "json",
				success : function(data) {
					dialog.hide();
					$('#hiddenDialog').html();
					$('div[class="modal-backdrop fade in"]').remove();
					if (data.result == true) {
						new DialogFunc("通知", '推送信息删除成功!', "alert").init(
								'alert', 'hiddenDialog').show();
						$('#index_refresh').trigger('click');

					} else {
						new DialogFunc("通知", '该推送信息删除失败!', "alert").init(
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
	} else {
		var title = $(".push-title-" + id).html();
		var content = $(".push-content-" + id).html();
		var beginTime = $(".push-beginTime-" + id).html();
		var endTime = $(".push-endTime-" + id).html();
		$.post(ctx + '/biu/pushActionInput.htm', {
			id : id,
			title : title,
			content : content,
			beginTime : beginTime,
			endTime : endTime
		}, function(data) {
			$("#push_input").html(data);
		});
	}
}
function ck_index() {
	blockPost(ctx + '/welcome/main.htm');
}