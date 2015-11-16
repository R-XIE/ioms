/**
 * 使用对话框需要引用这两个文件,
 * <link href="${ctx}/assets/fancybox/source/jquery.fancybox.css"
	rel="stylesheet" />
	<script src="${ctx}/js/gritter.js" type="text/javascript"></script>
 * 
 */
/**
 * type：类型[dialog,alert,success,confirm] title：对话框的标题 content:对话框的内容 id：需要加载到的ID
 * herf：点击触发的href去掉#即可 callback：触发监听器 目前只支持dialog和confirm
 */
$.dialogFun = function(type, title, content, id, herf, callback) {
	var divId = $('#' + id);
	var divHead = "<div id=\"" + herf
			+ "\" class=\"modal hide fade\" tabindex=\"-1\""
			+ " role=\"dialog\" aria-labelledby=\"myModalLabel1\" "
			+ "aria-hidden=\"true\">	<div class=\"modal-header\">"
			+ "<button type=\"button\" class=\"close\" data-dismiss=\"modal\""
			+ " aria-hidden=\"true\">×</button><h3 id=\"myModalLabel1\">"
			+ title + "</h3></div><div class=\"modal-body\"><p>" + content
			+ "</p></div><div class=\"modal-footer\">";
	var divFoot = "</div></div>";
	var divContent = "";
	switch (type) {
	case ('dialog'): {
		divContent = "<button class=\"btn\" data-dismiss=\"modal\" "
				+ "aria-hidden=\"true\">关闭</button>	<button "
				+ "class=\"btn btn-primary\" id=\"callback\">保存</button>";
		break;
	}
	case ('alert'): {
		divContent = "<button data-dismiss=\"modal\" class=\"btn btn-primary\">"
				+ "确定</button>";
		break;
	}
	case ('confirm'): {
		divContent = "<button class=\"btn\" data-dismiss=\"modal\" "
				+ "aria-hidden=\"true\" id=\"callback\">是</button><button "
				+ "data-dismiss=\"modal\" class=\"btn btn-primary\">否</button>";
		break;
	}
	case ('success'): {
		divContent = "<button class=\"btn\" data-dismiss=\"modal\" "
				+ "aria-hidden=\"true\">关闭</button><button "
				+ "data-dismiss=\"modal\" class=\"btn btn-success\">"
				+ "确定</button>";
		break;
	}
	}

	divId.html(divHead + divContent + divFoot);
	$('#callback').click(function() {
		callback();
		$('#' + herf).modal('hide');
	});
	return $('#' + herf);
};
function DialogFunc(title, content,herf,height) {
	this.title = title;
	this.content = content;
	this.herf=herf;
	this.divHead = "<div id=\"" + this.herf
			+ "\" class=\"modal  hide fade\" tabindex=\"-1\""
			+ " role=\"dialog\" aria-labelledby=\"myModalLabel1\" "
			+ "aria-hidden=\"true\">	<div class=\"modal-header\">"
			+ "<button type=\"button\" class=\"close\" data-dismiss=\"modal\""
			+ " aria-hidden=\"true\">×</button><h3 id=\"myModalLabel1\">"
			+ this.title + "</h3></div><div class=\"modal-body \" style=\"height: "+height+"\"><p>" + this.content
			+ "</p></div><div class=\"modal-footer\" id=\"foot-button\">";
	this.divFoot = "</div></div>";
	this.init=function(type,divId){
		this.type=type;
		switch (type) {
			case ('dialog'): {
				divContent = "<button class=\"btn\" data-dismiss=\"modal\" "
						+ "aria-hidden=\"true\">关闭</button>	<button "
						+ "class=\"btn btn-primary\" id=\"callback\">保存</button>";
				break;
			}
			case ('alert'): {
				divContent = "<button data-dismiss=\"modal\" class=\"btn btn-primary\">"
						+ "确定</button>";
				break;
			}
			case ('confirm'): {
				divContent = "<button class=\"btn\" data-dismiss=\"modal\" "
						+ "aria-hidden=\"true\" id=\"callbackYes\">是</button><button "
						+ "data-dismiss=\"modal\" id=\"callbackNo\" class=\"btn btn-primary\">否</button>";
				break;
			}
			case ('success'): {
				divContent = "<button class=\"btn\" data-dismiss=\"modal\" "
						+ "aria-hidden=\"true\">关闭</button><button "
						+ "data-dismiss=\"modal\" class=\"btn btn-success\">"
						+ "确定</button>";
				break;
			}
		}
		$('#'+divId).html(this.divHead+divContent+this.divFoot);
		return this;
	};
	this.confirm=function(callbackYes,callbackNo){
		var flag=false;
		$("#foot-button *").click(function(e){
			if(e.target == $("#callbackNo")[0]){
				callbackNo();
			}else if(e.target == $("#callbackYes")[0]){
				callbackYes();
			}
			//this.hide();
		});
		return flag;
	};
	this.show=function(){
		this.dialog().modal('show');
	};
	this.hide=function(){
		this.dialog().modal('hide');
	};
	this.dialog=function(){
		return $('#' + this.herf);
	};
}
