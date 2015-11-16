<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/chosen-bootstrap/chosen/chosen.css" />
<div class="widget green">
	<div class="widget-title">
		<h4>
			<i class="icon-reorder"></i> 借书
		</h4>
		<span class="tools"> <a href="javascript:icon_down();"
			class="icon-chevron-down" id="input_icon_down"></a> <a
			href="javascript:icon_remove();" id="input_icon_remove"
			class="icon-remove"></a>
		</span>
	</div>
	<div class="widget-body" id="input_widget">
		<form action="#" class="form-horizontal cmxform"
			id="input_position_form">
			<input type="hidden" id="bookId" value="${bookId}" />

			<div class="control-group">
				<label class="control-label">借阅人</label>
				<div class="controls">
					<select class="span6 chzn-select" name="staffId" id="staffId"
						data-placeholder="选择借阅人" tabindex="1">
						<c:if test="${!empty staffMap}">
							<c:forEach items="${staffMap}" var="map">
								<option value="${map.key}"
									<c:if test="${entity.staffId==map.key}"> 
				                selected="selected"
				                </c:if>>
									${map.value}</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
			</div>

			<div class="form-actions">
				<button type="button" class="btn btn-success"
					onclick="javascript:save();">保存</button>
				<button type="button" class="btn"
					onclick="javascript:icon_remove();">取消</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript"
	src="${ctx}/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript">
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
	var chosenStaff;
	$(function() {
		chosenStaff=$("#staffId").chosen();
	});
	function save(){
		var bookId=$('#bookId').val();
		var staffId=chosenStaff.val();
		var dialog= new DialogFunc("提示信息", '修改信息成功', "success").init(
		'success', 'hiddenDialog');
		var param={bookId:bookId,staffId:staffId};
		$.ajax({  
	      type:"post",  
	      url:ctx+bookRecordLocal+"Save.htm",  
	      data:param,  
	      success:function(data){ 
	         if(data==true){
	      	 dialog = new DialogFunc("提示信息", '借书成功', "success").init(
								'success', 'hiddenDialog');
	      	 dialog.show();
	      	 icon_remove();
	      	 $('#index_refresh').trigger('click');
	         }else{
	      	 dialog = new DialogFunc("错误", '借书失败', "alert").init(
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
</script>