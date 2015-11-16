<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/chosen-bootstrap/chosen/chosen.css" />
<link rel="stylesheet"
	href="${ctx}/assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />
<div class="widget green">
	<div class="widget-title">
		<h4>
			<i class="icon-reorder"></i>
			<c:if test="${type=='ADD'}">增加费用类型</c:if>
			<c:if test="${type=='EDIT'}">编辑费用类型</c:if>
		</h4>
		<span class="tools"> <a href="javascript:icon_down();"
			class="icon-chevron-down" id="input_icon_down"></a> <a
			href="javascript:icon_remove();" id="input_icon_remove"
			class="icon-remove"></a>
		</span>
	</div>
	<div class="widget-body" id="input_widget">
		<form action="#" class="form-horizontal cmxform"
			id="input_cost_type_form">
			<div class="control-group">
				<label class="control-label">费用类型名称</label>
				<div class="controls">
					<input type="text" class="span6" name="costTypeName"
						id="costTypeName" value="${entity.costTypeName}"
						placeholder="请输入消费类型名称" />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">支出收入类别</label>
				<div class="controls">
					<div id="costTypeType_buttons">
						<input name="costTypeType" id="costTypeType" type="checkbox"
							<c:if test="${entity.costTypeInex!=1}">
                                       	checked="checked"
                                </c:if>>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">上级费用类型</label>
				<div class="controls">
						<select class="span6 chzn-select" name="costTypeSuper" id="costTypeSuper" data-placeholder="选择上级费用类别"
							tabindex="1">
							<option value=""></option>
							<c:forEach items="${costTypeSuperRootMap}" var="map">
								<option value="${map.key}"
									<c:if test="${entity.costTypeSuper==map.key}"> 
								                selected="selected"
								                </c:if>>
									${map.value}</option>
							</c:forEach>
						</select>
				</div>
			</div>


			<div class="control-group">
				<label class="control-label">消费类型描述</label>
				<div class="controls">
					<input type="text" class="span6 " name="costTypeDesc"
						id="costTypeDesc" value="${entity.costTypeDesc}"
						placeholder="请输入消费类型描述" />
				</div>
			</div>

			<div class="form-actions">
				<button type="button" class="btn btn-success"
					onclick="javascript:save('${entity.costTypeId}');">保存</button>
				<button type="button" class="btn"
					onclick="javascript:icon_remove();">取消</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/bootstrap-toggle-buttons/static/js/jquery.toggle.buttons.js"></script>
<script src="${ctx}/custom/cost/costTypeInput.js"></script>