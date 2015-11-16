var weeklyLocal='/welcome/weekly';
var dataParam = {
		timePicker : false,
		timePickerIncrement : 1,
		format : 'YYYY-MM-DD',
		timePicker12Hour:false
};
$(function() {
	$('#weeklyCreateDateBE').daterangepicker(dataParam);
	getBootstrapPaginator(weeklyLocal);
});

function getState(weeklyId,state){
	var fn;
	if(state=='未提交'){
		fn='state'
		return "<a style=\"cursor: pointer;\" onclick=\"javscript:doAction('"+fn+"','"+weeklyId+"')\">"+state+"</a>";
	}else{
		return state;
	}	
}
function getMethod(weeklyId,state,type){
	if(type=='edit'){
		if(state.indexOf('未提交')>0){
			return "<button class='btn btn-small btn-primary' onclick='javascript:doAction(\"edit\",\""+weeklyId+"\")' ><i class=\"icon-pencil icon-white\"></i> 编辑	</button>";
		}else{
			return "";
		}
	}else{
		if(state.indexOf('未提交')>0){
			return "<button class='btn btn-small btn-danger' onclick='javascript:doAction(\"del\",\""+weeklyId+"\")' ><i class=\"icon-remove icon-white\"></i>删除</button>";
		}else{
			return "";
		}
	}
}
	function doAction(fn,id){
		if(fn=='del'){
			var dialog = new DialogFunc("询问", '您是否删除这条记录', "confirm").init(
					'confirm', 'hiddenDialog');// loginout
			dialog.show();
			dialog.confirm(function() {
				 $.ajax({
					 type:"post",  
		                url:ctx+weeklyLocal+"Del.htm",  
		                data:{recordId:id},  
		                success:function(data){ 
		                	dialog.hide();
		    	    		$('#hiddenDialog').html();
		    	    		$('div[class="modal-backdrop fade in"]').remove();
		    	    		if(data==true){
		    	    			new DialogFunc("通知", '个人周报删除成功!', "alert").init(
		    							'alert', 'hiddenDialog').show();
		    	    			$('#index_refresh').trigger('click');
		    	                
		    	    		}
		    	    		else{
		    	    			new DialogFunc("通知", '个人周报删除失败!', "alert").init(
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
		}else if(fn=='state'){
			var dialog = new DialogFunc("询问", '您是否提交该个人周报,提交后个人不可更改.', "confirm").init(
					'confirm', 'hiddenDialog');// loginout
			dialog.show();
			dialog.confirm(function() {
				 $.ajax({
					 type:"post",  
		                url:ctx+weeklyLocal+"State.htm",  
		                data:{recordId:id},  
		                success:function(data){ 
		                	dialog.hide();
		    	    		$('#hiddenDialog').html();
		    	    		$('div[class="modal-backdrop fade in"]').remove();
		    	    		if(data==true){
		    	    			new DialogFunc("通知", '周报提交成功!', "alert").init(
		    							'alert', 'hiddenDialog').show();
		    	    			$('#index_refresh').trigger('click');
		    	                
		    	    		}
		    	    		else{
		    	    			new DialogFunc("通知", '周报提交失败!', "alert").init(
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
			blockPost(ctx+weeklyLocal+'Input.htm');
		}
		else if(fn=='edit'){
			blockPost(ctx+weeklyLocal+'Input.htm', {recordId:id});
		}else if(fn=='view'){
			blockPost(ctx+weeklyLocal+'View.htm', {recordId:id});
		}else if(fn=='reset'){
			resetCondition();
		}else if(fn=='search'){
			var param=$('#queryForm').serialize();
			blockPost(ctx + weeklyLocal + 'ListAjax.htm', 
				param
			);
		}
	}
