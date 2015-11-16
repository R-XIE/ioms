var costTypeLocal=ctx+'/cost/costTypeAction';
var param = {
	rules : {
		costTypeName : {
			required : true,
			minlength : 2
		}
	},
	messages : {
		costTypeName : {
			required : "消费类型名称不能为空",
			minlength : "消费类型名称最少2个字符"
		}
	},
	onfocusout: function(element) { 
	      $(element).valid(); 
	}
};
var validate;
var toggleInexButton;
var chosen;
$(function() {
	chosen=$(".chzn-select").chosen();
	toggleInexButton = $('#costTypeType_buttons').toggleButtons({
		label : {
			disabled : "收入",
			enabled : "支出"
		},
		style : {
			disabled : "info",
			enabled : "danger"
		}
	});
	validate=$("#input_cost_type_form").validate(param);
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
	var inex;
	if ($('#costTypeType').attr('checked') == 'checked') {
		inex = 0;
	} else {
		inex = 1;
	}
	var costTypeSuper=chosen.val();
	var costTypeName=$('#costTypeName').val();
	var costTypeDesc=$('#costTypeDesc').val();
	var url=costTypeLocal+'Save.htm';
	var dialog= new DialogFunc("提示信息", '修改信息成功', "success").init(
				'success', 'hiddenDialog');
	var addParam={costTypeName:costTypeName,costTypeDesc:costTypeDesc,costTypeInex:inex,costTypeSuper:costTypeSuper};
	var editParam={costTypeId:id,costTypeName:costTypeName,costTypeDesc:costTypeDesc,costTypeInex:inex,costTypeSuper:costTypeSuper};
	if($("#input_cost_type_form").valid()){
		if(id==null||id==''){
			$.ajax({  
                type:"post",  
                url:url,  
                data:addParam,  
                success:function(data){ 
                   if(data==true){
                	 dialog = new DialogFunc("提示信息", '新增费用类型成功', "success").init(
	   							'success', 'hiddenDialog');
                	 dialog.show();
                	 icon_remove();
                	 $('#index_refresh').trigger('click');
                   }else{
                	 dialog = new DialogFunc("错误", '新增费用类型失败', "alert").init(
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
			$.ajax({  
                type:"post",  
                url:url,  
                data:editParam,  
                success:function(data){ 
                   if(data==true){
                	 dialog = new DialogFunc("提示信息", '编辑费用类型成功', "success").init(
	   							'success', 'hiddenDialog');
                	 dialog.show();
                	 icon_remove();
                	 $('#index_refresh').trigger('click');
                   }else{
                	 dialog = new DialogFunc("错误", '编辑费用类型失败', "alert").init(
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