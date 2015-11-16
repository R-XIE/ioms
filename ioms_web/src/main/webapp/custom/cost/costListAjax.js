var costLocal = '/cost/costAction';
var dataParam = {
		timePicker : true,
		timePickerIncrement : 1,
		format : 'YYYY-MM-DD HH:mm',
		timePicker12Hour:false
};
$(function(){
	$('#costTimeBE').daterangepicker(dataParam);
	$('#costCreateTimeBE').daterangepicker(dataParam);
	getBootstrapPaginator(costLocal);
});
function doAction(fn, id) {
	if (fn == 'del') {
		var dialog = new DialogFunc("询问", '您是否删除这条记录', "confirm").init(
				'confirm', 'hiddenDialog');// loginout
		dialog.show();
		dialog.confirm(function() {
			$.ajax({
				type : "post",
				url : ctx + costLocal + "Del.htm",
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
						new DialogFunc("通知", '费用信息删除失败!', "alert").init(
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
		blockPost(ctx + costLocal + 'Input.htm',{
			months:dateF
		});
	} else if (fn == 'edit') {
		blockPost(ctx + costLocal + 'Input.htm', {
			recordId : id
		});
	} else if (fn == 'view') {
		blockPost(ctx + costLocal + 'View.htm', {
			recordId : id
		});
	}else if(fn=='reset'){
		resetCondition();
	}else if(fn=='search'){
		var param=$('#queryForm').serialize();
		blockPost(ctx + costLocal + 'ListAjax.htm', 
			param
		);
	}
}