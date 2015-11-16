var oTable;
var bookLocal='/book/bookAction';
var bookRecordLocal='/book/bookRecordAction';
	$(function() {
		//EditableTable.init();
		oTable=$('#editable-sample').dataTable(maxDataTable);
	});
	function doAction(fn,id){
		if(fn=='del'){
			var dialog = new DialogFunc("询问", '您是否删除这条记录', "confirm").init(
					'confirm', 'hiddenDialog');// loginout
			dialog.show();
			dialog.confirm(function() {
				 $.ajax({
					 type:"post",  
		                url:ctx+bookLocal+"Del.htm",  
		                data:{recordId:id},  
		                success:function(data){ 
		                	dialog.hide();
		    	    		$('#hiddenDialog').html();
		    	    		$('div[class="modal-backdrop fade in"]').remove();
		    	    		if(data==true){
		    	    			new DialogFunc("通知", '删除成功!', "alert").init(
		    							'alert', 'hiddenDialog').show();
		    	    			$('#index_refresh').trigger('click');
		    	                
		    	    		}
		    	    		else{
		    	    			new DialogFunc("通知", '图书信息删除失败!', "alert").init(
		    							'alert', 'hiddenDialog').show();
		    	    		}
		                },  
		                error:function(e) {
		                	 dialog.hide();
		    	    		 $('#hiddenDialog').html();
		    	    		 $('div[class="modal-backdrop fade in"]').remove();
		                	dialog = new DialogFunc("错误", 'JS执行错误', "alert").init(
									'alert', 'hiddenDialogAlert');
							dialog.show();
		                }  
				 });
				
			});
		}else if(fn=='add'){
			blockPost(ctx+bookLocal+'Input.htm');
		}
		else if(fn=='edit'){
			blockPost(ctx+bookLocal+'Input.htm', {recordId:id});
		}else if(fn=='view'){
			blockPost(ctx+bookLocal+'View.htm', {recordId:id});
		}else if(fn=='bookRecord'){
			blockPost(ctx+bookRecordLocal+'BookRecordList.htm', {bookId:id});
		}else if(fn=='viewRecord'){
			blockPost(ctx+bookRecordLocal+'List.htm');
		}else if(fn=='borrow'){
			//book_borrow_input
			$.post(ctx+bookRecordLocal+'BorrowInput.htm', {recordId:id}, function(data) {
				$("#book_borrow_input").html(data);
			});
		}else if(fn=='returnA'){
			var dialog = new DialogFunc("询问", '您是否还书?', "confirm").init(
					'confirm', 'hiddenDialog');// loginout
			dialog.show();
			dialog.confirm(function() {
				 $.ajax({
					 type:"post",  
		                url:ctx+bookRecordLocal+"Return.htm",  
		                data:{recordId:id},  
		                success:function(data){ 
		                	dialog.hide();
		    	    		$('#hiddenDialog').html();
		    	    		$('div[class="modal-backdrop fade in"]').remove();
		    	    		if(data==true){
		    	    			new DialogFunc("通知", '还书成功!', "alert").init(
		    							'alert', 'hiddenDialog').show();
		    	    			$('#index_refresh').trigger('click');
		    	                
		    	    		}
		    	    		else{
		    	    			new DialogFunc("通知", '还书失败!', "alert").init(
		    							'alert', 'hiddenDialog').show();
		    	    		}
		                },  
		                error:function(e) {
		                	 dialog.hide();
		    	    		 $('#hiddenDialog').html();
		    	    		 $('div[class="modal-backdrop fade in"]').remove();
		                	dialog = new DialogFunc("错误", 'JS执行错误', "alert").init(
									'alert', 'hiddenDialogAlert');
							dialog.show();
		                }  
				 });
			});
	}}
	function ck_index(){
		blockPost(ctx+'/welcome/main.htm');
   }