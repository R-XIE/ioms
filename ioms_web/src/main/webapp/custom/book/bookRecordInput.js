var chosenBook;
var chosenStaff;
var bookRecordLocal='/book/bookRecordAction';
$(function() {
	chosenBook=$("#bookId").chosen();
	chosenStaff=$("#staffId").chosen();
});
function icon_down() {
	var el = $('#input_widget');
	if ($('#input_icon_down').hasClass("icon-chevron-down")) {
		$('#input_icon_down').removeClass("icon-chevron-down").addClass(
				"icon-chevron-up");
		el.slideUp(200);
	} else {
		$('#input_icon_down').removeClass("icon-chevron-up").addClass(
				"icon-chevron-down");
		el.slideDown(200);
	}
}
function icon_remove() {
	$('#input_icon_remove').parents(".widget").remove();
}
function save(){
	var bookId=chosenBook.val();
	var staffId=chosenStaff.val();
	var dialog= new DialogFunc("提示信息", '修改信息成功', "success").init(
	'success', 'hiddenDialog');
	var param={bookId:bookId,staffId:staffId};
	$.ajax({  
      type:"post",  
      url:ctx+bookRecordLocal+"Save.htm",  
      data:param,  
      success:function(data){ 
         if(data==true){
      	 dialog = new DialogFunc("提示信息", '借书成功', "success").init(
							'success', 'hiddenDialog');
      	 dialog.show();
      	 icon_remove();
      	 $('#index_refresh').trigger('click');
         }else{
      	 dialog = new DialogFunc("错误", '借书失败', "alert").init(
						'alert', 'hiddenDialog');
				 dialog.show();
         }
      },  
      error:function(e) {
      	 dialog.hide();
  		 $('#hiddenDialog').html();
  		 $('div[class="modal-backdrop fade in"]').remove();
      	dialog = new DialogFunc("错误", 'JS执行错误', "alert").init(
					'alert', 'hiddenDialog');
			dialog.show();
      }  
  });
}