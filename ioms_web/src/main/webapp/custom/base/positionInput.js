var param = {
	rules : {
		positionName : {
			required : true,
			minlength : 2
		}
	},
	messages : {
		positionName : {
			required : "岗位名称不能为空",
			minlength : "岗位名称最少2个字符"
		}
	},
	onfocusout: function(element) { 
	      $(element).valid(); 
	}
};
var validate;
var chosen;
$(function() {
	chosen=$(".chzn-select").chosen();
	validate=$("#input_position_form").validate(param);
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
function save(id){
	var positionName=$('#positionName').val();
	var positionDesc=$('#positionDesc').val();
	var branchId=chosen.val();
	var param;
	var url=ctx+'/base/positionActionSave.htm';
	var dialog= new DialogFunc("提示信息", '修改信息成功', "success").init(
				'success', 'hiddenDialog');
	if($('#positionName').valid()){
		if(id==null||id==''){
			param={positionName:positionName,branchId:branchId,positionDesc:positionDesc};
			$.ajax({  
                type:"post",  
                url:url,  
                data:param,  
                success:function(data){ 
                   if(data==true){
                	 dialog = new DialogFunc("提示信息", '新增岗位成功', "success").init(
	   							'success', 'hiddenDialog');
                	 dialog.show();
                	 icon_remove();
                	 $('#index_refresh').trigger('click');
                   }else{
                	 dialog = new DialogFunc("错误", '新增岗位失败', "alert").init(
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
			
		}else{
			param={positionId:id,positionName:positionName,branchId:branchId,positionDesc:positionDesc};
			$.ajax({  
                type:"post",  
                url:url,  
                data:param,  
                success:function(data){ 
                   if(data==true){
                	 dialog = new DialogFunc("提示信息", '编辑岗位成功', "success").init(
	   							'success', 'hiddenDialog');
                	 dialog.show();
                	 icon_remove();
                	 $('#index_refresh').trigger('click');
                   }else{
                	 dialog = new DialogFunc("错误", '编辑岗位失败', "alert").init(
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
	}
}