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
		                url:ctx+"/base/branchActionDel.htm",  
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
		    	    			new DialogFunc("通知", '删除失败,该部门已经被使用!', "alert").init(
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
            "<input type=\"text\"  class=\"span5\" placeholder=\"请输入部门名称\" name=\"branchName\" id=\"branchName\"/>"+
            "</div></div><div class=\"control-group\"  align=\"center\" >"+
            "<div class=\"controls\"><input type=\"text\" placeholder=\"请输入部门描述\" class=\"span5\" name=\"branchDesc\" id=\"branchDesc\"/>"+
            "</div></div></form>";
			var dialog = new DialogFunc("新增部门",content, "dialog").init(
					'dialog', 'hiddenDialog');
			dialog.show();
			$('#callback').click(function(){
						if($('#branchName').val()==''){
							dialog.hide();
		    	    		$('#hiddenDialog').html();
		    	    		$('div[class="modal-backdrop fade in"]').remove();
							new DialogFunc("信息","部门名称不能为空", "alert").init(
									'alert', 'hiddenDialog').show();
						}else{
							$.ajax({
								 type:"post",  
					                url:ctx+"/base/branchActionSave.htm",  
					                data:{branchName:$('#branchName').val(),branchDesc:$('#branchDesc').val()},  
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
            "<input type=\"text\"  class=\"span5\" placeholder=\"请输入部门名称\" name=\"branchName\" id=\"branchName\" value=\""+name+"\"/>"+
            "</div></div><div class=\"control-group\"  align=\"center\" >"+
            "<div class=\"controls\"><input type=\"text\" placeholder=\"请输入部门描述\" value=\""+des+"\" class=\"span5\" name=\"branchDesc\" id=\"branchDesc\"/>"+
            "</div></div></form>";
			var dialog = new DialogFunc("编辑部门",content, "dialog").init(
					'dialog', 'hiddenDialog');
			dialog.show();
				$('#callback').click(function(){
					if($('#branchName').val()==''){
						dialog.hide();
	    	    		$('#hiddenDialog').html();
	    	    		$('div[class="modal-backdrop fade in"]').remove();
						new DialogFunc("信息","部门名称不能为空", "alert").init(
								'alert', 'hiddenDialog').show();
					}else{
						$.ajax({
							 type:"post",  
				                url:ctx+"/base/branchActionSave.htm",  
				                data:{branchId:id,branchName:$('#branchName').val(),branchDesc:$('#branchDesc').val()},  
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
