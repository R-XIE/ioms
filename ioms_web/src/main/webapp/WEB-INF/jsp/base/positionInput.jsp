<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="widget green">
	<div class="widget-title">
		<h4>
			<i class="icon-reorder"></i>
			<c:if test="${type=='ADD'}">增加岗位</c:if>
			<c:if test="${type=='EDIT'}">编辑岗位</c:if>
		</h4>
		<span class="tools"> <a href="javascript:icon_down();"
			class="icon-chevron-down" id="input_icon_down" ></a> <a href="javascript:icon_remove();" id="input_icon_remove"
			class="icon-remove"></a>
		</span>
	</div>
	<div class="widget-body" id="input_widget">
		<form action="#" class="form-horizontal cmxform" id="input_position_form">
			<div class="control-group">
				<label class="control-label">岗位名称</label>
				<div class="controls">
					<input type="text" class="span6" name="positionName" id="positionName" value="${entity.positionName}" placeholder="请输入岗位名称"/> 
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">所属部门</label>
				<div class="controls">
					<select class="span6 chzn-select" name="branchId" id="branchId"
						data-placeholder="选择所属部门" tabindex="1">
						<c:if test="${!empty branchMap}">  
				            <c:forEach items="${branchMap}" var="map">  
				                <option value="${map.key}" 
				                <c:if test="${entity.branchId==map.key}"> 
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
				<label class="control-label">岗位描述</label>
				<div class="controls">
					<input type="text" class="span6 " name="positionDesc" id="positionDesc" value="${entity.positionDesc}" placeholder="请输入岗位描述"  /> 
				</div>
			</div>
			
			<div class="form-actions">
				<button type="button" class="btn btn-success" onclick="javascript:save('${entity.positionId}');">保存</button>
				<button type="button" class="btn" onclick="javascript:icon_remove();">取消</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script src="${ctx}/custom/base/positionInput.js"></script>