/**
 * jquery 扩展
 */
$.fn.selectReadOnly=function(){
	$(this).hide();
	var selId= $(this).attr("id");var selHtml=$(this).find('option:selected').html();
	if($('#_hiddenSelText_'+selId)[0]){}else{
		$(this).before('<input type="text" readOnly="" class="readOnlyStyle" id="_hiddenSelText_'+selId+'" value="'+selHtml+'" />' );
	}
};
$.fn.selectUnReadOnly=function(){
	var selId= $(this).attr("id");
	$(this).show();
	$('#_hiddenSelText_'+selId).remove();
};
/**
 * 设置页面INPUT 不可编辑
 * @return
 */
function setPageUnEdit(){
	$(":text,:password,:submit,:image,:reset,:button,:file").each(function(i){
		$(this).attr("readonly",true);
		$(this).addClass("readOnlyStyle");
	});
	$("textarea").each(function(i){
		$(this).attr("readonly",true);
		$(this).addClass("readOnlyAreaStyle");
	});
	//日期控件
	$("input.Wdate").each(function(i){
		var selId= $(this).attr("id");
		if($('#_hiddenDateText_'+selId)[0]){}else{
			$(this).before('<input type="text" readonly="readonly" class="readOnlyStyle" id="_hiddenDateText_'+selId+'" value="'+$(this).val()+'" />');
		}
		$(this).hide();
	});
	// 超链接
	$("form a").each(function(){
		$(this).hide();
	});
	//TODO 需要更好的方式解决
	//由于设备审批页面某些提交按钮在form下所以特例
	$("form li a").each(function(){
		$(this).show();
	});
	
	//
	
	/*$("input + a").each(function(){
		$(this).hide();
	});*/
//	$("a + a ").each(function(){
//		$(this).hide();
//	});
	
	$("select").each(function(){
		$(this).selectReadOnly();
	});
}
/**
 * 设置
 * @param editorVar
 */
function setKindEditorUnEdit(editorVar){
	editorVar.readonly();
}
function setEditableById(id,editFlag){
	var element = null;
	if(id != null && id != undefined && id != ''){
		element = $("#"+id);
	}
	if(element==null||element==undefined
			||element[0]==null||element[0]===undefined){return;}
	
	var tagName=element[0]['tagName'];
		//element[0].attr("tagName"); 
	if("SELECT" == tagName){//下拉框
		(true===editFlag)?element.selectUnReadOnly():element.selectReadOnly();
	
	}else if("A" == tagName || "BUTTON" == tagName){//按钮
		(true===editFlag)?element.show():element.hide();
		
	}else if(element.hasClass("Wdate")){
		if(editFlag){
			element.show();
			var selId= element.attr("id");
			if($('#_hiddenDateText_'+selId)[0]){
				$('#_hiddenDateText_'+selId).remove();
			}
		}else{
			element.hide();
			var selId= element.attr("id");
			if($('#_hiddenDateText_'+selId)[0]){}else{
				element.before( '<input type="text" id="_hiddenDateText_'+selId+'"  readonly="readonly" class="readOnlyStyle" value="' +element.val()+ '" />' );
			}
		}
	}else{
			if(editFlag){
				element.removeAttr("readonly");
				element.css({background : "#ffffff"});
			}else{
				element.attr("readonly",true);
				element.css({background : "#f0f0f0"});	
			}
	}
}

function setPageElementsUnEdit(idStr,editAble){
	if(idStr){
		var ids = idStr.split(",");
		for (key in ids){
			setEditableById(ids[key],editAble);
		}	
	}
}
//清空form的内容
function resetForm(formId){
	resetCondition();
}

function resetCondition(type){
	$(':input','#queryForm').not(':button, :submit, :reset')
	.val('')
	.removeAttr('checked').removeAttr('selected');
//	if($('#currPage')[0]){
//		$('#currPage').val(1);
//	}
	for(var i = 0; i < arguments.length; i++) {
        $('#'+arguments[i]).val('');
    }
}

function alertDlg(msg){//弹出消息提示框
	alert(msg+'');
}
function confirmDlg(msg){//弹出对话框
	return confirm(msg);
}