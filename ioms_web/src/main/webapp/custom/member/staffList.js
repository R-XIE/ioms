var oTable;
var staffLocal='/member/staffAction';
	$(function() {
		//EditableTable.init();
		oTable=$('#editable-sample').dataTable(maxDataTable);
		$('#sample_2_column_toggler input[type="checkbox"]').change(function(){
            /* Get the DataTables object again - this is not a recreation, just a get of the object */
            var iCol = parseInt($(this).attr("data-column"));
            var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
            oTable.fnSetColumnVis(iCol, (bVis ? false : true));
        });
	});
	function doAction(fn,id,isLeave){
		if(fn=='del'){
			var dialog = new DialogFunc("询问", '您是否删除这条记录,请谨慎操作!', "confirm").init(
					'confirm', 'hiddenDialog');// loginout
			dialog.show();
			dialog.confirm(function() {
				 $.ajax({
					 type:"post",  
		                url:ctx+staffLocal+"Del.htm",  
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
		    	    			new DialogFunc("通知", '删除失败!', "alert").init(
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
			blockPost(ctx+staffLocal+'Input.htm');
		}
		else if(fn=='edit'){
			blockPost(ctx+staffLocal+'Input.htm', {recordId:id,isLeave:isLeave});
		}
		else if(fn=='view'){
			blockPost(ctx+staffLocal+'View.htm', {recordId:id,isLeave:isLeave});
		}else if(fn=='leave'){
			blockPost(ctx+staffLocal+'List.htm?isLeave=true');
		}else if(fn=='change'){
			//变为离职
			var leaveInfo;
			if(isLeave=='true')
				leaveInfo="是否修改该用户的离职信息?";
			else 
				leaveInfo="该用户是否离职,请谨慎操作!一旦离职,无法撤销!";
			var dialog = new DialogFunc("询问", leaveInfo, "confirm").init(
					'confirm', 'hiddenDialog');// loginout
			dialog.show();
			dialog.confirm(function() {
				dialog.hide();
	    		$('#hiddenDialog').html();
	    		$('div[class="modal-backdrop fade in"]').remove();
	    		var lable;
    			if(isLeave=='true')
    				lable="编辑";
    			else
    				lable="添加";
    			var date=$('#hid_leave_date_'+id).val();
    			var remark=$('#hid_leave_remark_'+id).val();
				var content="<form class=\"cmxform\"><div class=\"control-group\" align=\"center\">"+
	            "<div class=\"controls\">"+
	            "<input type=\"text\"  class=\"span5\" placeholder=\"请输入离职时间\" value=\""+date+"\"" +
	            "name=\"leaveDate\" id=\"leaveDate\"/>"+
	            "</div></div><div class=\"control-group\"  align=\"center\" >"+
	            "<div class=\"controls\">" +
	            "<input type=\"text\" placeholder=\"离职原因\" class=\"span5\"  value=\""+remark+"\"" +
	            "name=\"leaveRemark\" id=\"leaveRemark\"/>"+
	            "</div></div></form>";
				dialog = new DialogFunc(lable+"用户离职",content, "dialog").init(
						'dialog', 'hiddenDialog');
				dialog.show();
				$('#callback').click(function(){
					if($('#leaveDate').val()==''){
						dialog.hide();
	    	    		$('#hiddenDialog').html();
	    	    		$('div[class="modal-backdrop fade in"]').remove();
						new DialogFunc("警告","离职时间不能为空", "alert").init(
								'alert', 'hiddenDialog').show();
					}else{
						var postJSON={id:id,leaveDate:$('#leaveDate').val(),
								leaveRemark:$('#leaveRemark').val(),isLeave:isLeave};
						$.ajax({
							 type:"post",  
				                url:ctx+staffLocal+"Leave.htm",  
				                data:postJSON,  
				                success:function(data){ 
				                	dialog.hide();
				    	    		$('#hiddenDialog').html();
				    	    		$('div[class="modal-backdrop fade in"]').remove();
				    	    		if(data==true){
				    	    			new DialogFunc("通知", lable+'成功!', "alert").init(
				    							'alert', 'hiddenDialog').show();
				    	    			$('#index_refresh').trigger('click');
				    	                
				    	    		}else{
				    	    			new DialogFunc("通知", lable+'失败!', "alert").init(
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
			});
		}
	}
	function ck_index(){
		blockPost(ctx+'/welcome/main.htm');
   }
	function home_index(status){
		if(status)
			blockPost(ctx+staffLocal+'List.htm');
	}