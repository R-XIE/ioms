<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/chosen-bootstrap/chosen/chosen.css" />
<div class="widget green">
	<div class="widget-title">
		<h4>
			<i class="icon-reorder"></i>
			借书
		</h4>
		<span class="tools"> <a href="javascript:icon_down();"
			class="icon-chevron-down" id="input_icon_down" ></a> <a href="javascript:icon_remove();" id="input_icon_remove"
			class="icon-remove"></a>
		</span>
	</div>
	<div class="widget-body" id="input_widget">
		<form action="#" class="form-horizontal cmxform" id="input_position_form">
			<div class="control-group">
				<label class="control-label">借阅书籍</label>
				<div class="controls">
					<select class="span6 chzn-select" name="bookId" id="bookId"
						data-placeholder="选择借阅书籍" tabindex="1">
						<c:if test="${!empty bookMap}">  
				            <c:forEach items="${bookMap}" var="map">  
				                <option value="${map.key}" 
				                <c:if test="${entity.bookId==map.key}"> 
				                selected="selected"
				                </c:if>
				                >  
				                ${map.value}</option>  
				            </c:forEach>  
				        </c:if>  
					</select>
				</div>
			</div>
			
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
				                </c:if>
				                >  
				                ${map.value}</option>  
				            </c:forEach>  
				        </c:if>  
					</select>
				</div>
			</div>
			
			<div class="form-actions">
				<button type="button" class="btn btn-success" onclick="javascript:save();">保存</button>
				<button type="button" class="btn" onclick="javascript:icon_remove();">取消</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script src="${ctx}/custom/book/bookRecordInput.js"></script>