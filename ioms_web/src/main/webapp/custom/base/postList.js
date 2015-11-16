var oTable;
	$(function() {
		//EditableTable.init();
		oTable=$('#editable-sample').dataTable(maxDataTable);
	});
	function doAction(fn,id,name,des){
		if(fn=='del'){
			var dialog = new DialogFunc("询问", '您是否删除这条记录', "confirm").init(
					'confirm', 'hiddenDialog');// loginout
			dialog.show();
			dialog.confirm(function() {
				 $.ajax({
					 type:"post",  
		                url:ctx+"/base/postActionDel.htm",  
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
		    	    			new DialogFunc("通知", '删除失败,该职位已经被使用!', "alert").init(
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
			var content="<form class=\"cmxform\"><div class=\"control-group\" align=\"center\">"+
            "<div class=\"controls\">"+
            "<input type=\"text\"  class=\"span5\" placeholder=\"请输入职位名称\" name=\"postName\" id=\"postName\"/>"+
            "</div></div><div class=\"control-group\"  align=\"center\" >"+
            "<div class=\"controls\"><input type=\"text\" placeholder=\"请输入职位描述\" class=\"span5\" name=\"postDesc\" id=\"postDesc\"/>"+
            "</div></div></form>";
			var dialog = new DialogFunc("新增职位",content, "dialog").init(
					'dialog', 'hiddenDialog');
			dialog.show();
			$('#callback').click(function(){
						if($('#postName').val()==''){
							dialog.hide();
		    	    		$('#hiddenDialog').html();
		    	    		$('div[class="modal-backdrop fade in"]').remove();
							new DialogFunc("信息","职位名称不能为空", "alert").init(
									'alert', 'hiddenDialog').show();
						}else{
							$.ajax({
								 type:"post",  
					                url:ctx+"/base/postActionSave.htm",  
					                data:{postName:$('#postName').val(),postDesc:$('#postDesc').val()},  
					                success:function(data){ 
					                	dialog.hide();
					    	    		$('#hiddenDialog').html();
					    	    		$('div[class="modal-backdrop fade in"]').remove();
					    	    		if(data==true){
					    	    			new DialogFunc("通知", '添加成功!', "alert").init(
					    							'alert', 'hiddenDialog').show();
					    	    			$('#index_refresh').trigger('click');
					    	                
					    	    		}else{
					    	    			new DialogFunc("通知", '添加失败!', "alert").init(
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
					}
			});
		}else if(fn=='edit'){
			var content="<form class=\"cmxform\"><div class=\"control-group\" align=\"center\">"+
            "<div class=\"controls\">"+
            "<input type=\"text\"  class=\"span5\" placeholder=\"请输入职位名称\" name=\"postName\" id=\"postName\" value=\""+name+"\"/>"+
            "</div></div><div class=\"control-group\"  align=\"center\" >"+
            "<div class=\"controls\"><input type=\"text\" placeholder=\"请输入职位描述\" value=\""+des+"\" class=\"span5\" name=\"postDesc\" id=\"postDesc\"/>"+
            "</div></div></form>";
			var dialog = new DialogFunc("编辑职位",content, "dialog").init(
					'dialog', 'hiddenDialog');
			dialog.show();
				$('#callback').click(function(){
					if($('#postName').val()==''){
						dialog.hide();
	    	    		$('#hiddenDialog').html();
	    	    		$('div[class="modal-backdrop fade in"]').remove();
						new DialogFunc("信息","职位名称不能为空", "alert").init(
								'alert', 'hiddenDialog').show();
					}else{
						$.ajax({
							 type:"post",  
				                url:ctx+"/base/postActionSave.htm",  
				                data:{postId:id,postName:$('#postName').val(),postDesc:$('#postDesc').val()},  
				                success:function(data){ 
				                	dialog.hide();
				    	    		$('#hiddenDialog').html();
				    	    		$('div[class="modal-backdrop fade in"]').remove();
				    	    		if(data==true){
				    	    			new DialogFunc("通知", '编辑成功!', "alert").init(
				    							'alert', 'hiddenDialog').show();
				    	    			$('#index_refresh').trigger('click');
				    	                
				    	    		}else{
				    	    			new DialogFunc("通知", '编辑失败!', "alert").init(
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
				}
			});
		}
	}
