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
		                url:ctx+"/permiss/roleActionDel.htm",  
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
		    	    			new DialogFunc("通知", '删除失败,该角色已经被使用!', "alert").init(
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
			var content="<form class=\"cmxform\" id=\"roleActionForm\"><div class=\"control-group\" align=\"center\">"+
            "<div class=\"controls\">"+
            "<input type=\"text\"  class=\"span5\" placeholder=\"请输入角色名称\" name=\"roleName\" id=\"roleName\"/>"+
            "</div></div><div class=\"control-group\"  align=\"center\" >"+
            "<div class=\"controls\"><input type=\"text\" placeholder=\"请输入角色描述\" class=\"span5\" name=\"roleDescription\" id=\"roleDescription\"/>"+
            "</div></div></form>";
			var dialog = new DialogFunc("新增角色",content, "dialog").init(
					'dialog', 'hiddenDialog');
			dialog.show();
			$('#callback').click(function(){
						if($('#roleName').val()==''){
							dialog.hide();
		    	    		$('#hiddenDialog').html();
		    	    		$('div[class="modal-backdrop fade in"]').remove();
							new DialogFunc("信息","角色名称不能为空", "alert").init(
									'alert', 'hiddenDialog').show();
						}else{
							$.ajax({
								 type:"post",  
					                url:ctx+"/permiss/roleActionSave.htm",  
					                data:{roleName:$('#roleName').val(),roleDescription:$('#roleDescription').val()},  
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
			var content="<form class=\"cmxform\" id=\"roleActionForm\"><div class=\"control-group\" align=\"center\">"+
            "<div class=\"controls\">"+
            "<input type=\"text\"  class=\"span5\" placeholder=\"请输入角色名称\" name=\"roleName\" id=\"roleName\" value=\""+name+"\"/>"+
            "</div></div><div class=\"control-group\"  align=\"center\" >"+
            "<div class=\"controls\"><input type=\"text\" placeholder=\"请输入角色描述\" value=\""+des+"\" class=\"span5\" name=\"roleDescription\" id=\"roleDescription\"/>"+
            "</div></div></form>";
			var dialog = new DialogFunc("编辑角色",content, "dialog").init(
					'dialog', 'hiddenDialog');
			dialog.show();
				$('#callback').click(function(){
					if($('#roleName').val()==''){
						dialog.hide();
	    	    		$('#hiddenDialog').html();
	    	    		$('div[class="modal-backdrop fade in"]').remove();
						new DialogFunc("信息","角色名称不能为空", "alert").init(
								'alert', 'hiddenDialog').show();
					}else{
						$.ajax({
							 type:"post",  
				                url:ctx+"/permiss/roleActionSave.htm",  
				                data:{roleId:id,roleName:$('#roleName').val(),roleDescription:$('#roleDescription').val()},  
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
		}else if(fn=='auth'){
			var url = ctx+'/permiss/roleActionAuth.htm';
			var param={recordId:id};
			blockPost(url,param);
		}
	}
	function ck_index(){
		blockPost(ctx+'/welcome/main.htm');
   }