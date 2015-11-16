<#assign classNameLower = clazz.className?uncap_first>
<#macro jspEl value>${r"${"}${value}}</#macro> 
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="<@jspEl 'pageContext.request.contextPath'/>" />
<link rel="stylesheet" type="text/css" href="<@jspEl 'ctx'/>/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="<@jspEl 'ctx'/>/assets/chosen-bootstrap/chosen/chosen.css" />
<link rel="stylesheet" type="text/css" href="<@jspEl 'ctx'/>/assets/bootstrap-datepicker/css/bootstrap-datetimepicker.min.css" />
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">${entityCnName}管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_${clazzLowerName}();">${entityCnName}管理</a> <span class="divider">/</span></li>
				<li class="active"><c:if test="<@jspEl "type=='ADD'"/>">${entityCnName}添加</c:if>
						<c:if test="<@jspEl "type=='VIEW'"/>">查看${entityCnName}</c:if>
						<c:if test="<@jspEl "type=='EDIT'"/>">编辑${entityCnName}</c:if></li>
				<li class="pull-right search-wrap">
					<%@include file="/common/search.jspf" %>
				</li>
			</ul>
			<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
	</div>
	<!-- END PAGE HEADER-->
	<!-- BEGIN EDITABLE TABLE widget-->
	<div class="row-fluid">
		<div class="span12">
			<div class="widget green">
				<div class="widget-title">
					<h4>
						<i class="icon-reorder"></i>
						<c:if test="<@jspEl "type=='ADD'"/>">${entityCnName}添加</c:if>
						<c:if test="<@jspEl "type=='VIEW'"/>">查看${entityCnName}</c:if>
						<c:if test="<@jspEl "type=='EDIT'"/>">编辑${entityCnName}</c:if>
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down" ></a> 
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform" id="input_${clazz.underLineName}_form">
						<input id="id" type="hidden" value="<@jspEl "entity.${clazz.pkProperty.name}"/>"></input>
						<#list clazz.properties as prop> 
						<#if (prop.name !=clazz.pkProperty.name)&&(prop.name != "class")>
						<#if prop.asType== 'Date'>
						<div class="control-group">
							<label class="control-label">${prop.column.columnAlias}</label>
							<div class="controls">
								<c:if test="<@jspEl " type!='VIEW' "/>">
								<input id="${prop.name}" type="text"
									value="<fmt:formatDate value='<@jspEl "entity.${prop.name}"/>' pattern='yyyy-MM-dd HH:mm'/>"
									placeholder="请输入${prop.column.columnAlias}" size="16" class="span6 m-ctrl-medium"
									name="${prop.name}">
								</c:if>
								<c:if test="<@jspEl " type== 'VIEW'"/>">
								<input type="text" class="span6" name="${prop.name}"
									id="${prop.name}" disabled="disabled"
									value="<fmt:formatDate value='<@jspEl "entity.${prop.name}"/>' pattern='yyyy-MM-dd'/>" />
								</c:if>
							</div>
						</div>
						<#else> 
						<#if (prop.name?ends_with("State"))||(prop.name?ends_with("state"))|| (prop.name?ends_with("Id"))||(prop.name?ends_with("id")) || (prop.name?ends_with("Type"))||(prop.name?ends_with("type"))>
						<div class="control-group">
							<label class="control-label">${prop.column.columnAlias}</label>
							<div class="controls">
								<c:if test="<@jspEl " type!='VIEW' "/>">
								<select class="span6 chzn-select" name="${prop.name}"
									id="${prop.name}" tabindex="1">
									<c:forEach items='<@jspEl "${prop.name}Map"/>' var="map">
										<option value='<@jspEl "map.key"/>'
											<c:if test="<@jspEl "entity.${prop.name}==map.key"/>"> 
								                selected="selected"
								                </c:if>>
											<@jspEl "map.value"/></option>
									</c:forEach>
								</select>
								</c:if>
								<c:if test="<@jspEl " type== 'VIEW'"/>">
								<input type="text" class="span6" name="${prop.name}"
									id="${prop.name}" disabled="disabled"
									value='<@jspEl "entity.${prop.name}"/>' />
								</c:if>
							</div>
						</div>
						<#elseif (prop.name?ends_with("remark"))||(prop.name?ends_with("Remark"))||(prop.name?ends_with("content"))||(prop.name?ends_with("Content"))>
						<div class="control-group">
                             	<label class="control-label">${prop.column.columnAlias}</label>
                                <div class="controls">
                                   <textarea class="span6 ckeditor" name="${prop.name}" id="${prop.name}" rows="4" style="resize: none;"
                                   <c:if test="<@jspEl "type=='VIEW'"/>">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                   ><@jspEl 'entity.${prop.name}'/></textarea>
                                </div>
                        </div>
						<#else>
						<div class="control-group">
							<label class="control-label">${prop.column.columnAlias}</label>
							<div class="controls">
								<input type="text" class="span6" name="${prop.name}"
									id="${prop.name}" placeholder="请输入${prop.column.columnAlias}"
									<c:if test="<@jspEl "type=='VIEW'"/>"> 
									 disabled="disabled"
									 </c:if>
									value="<@jspEl 'entity.${prop.name}'/>" />
							</div>
						</div>
						</#if>
						</#if>
						</#if>
						</#list>
                        <c:if test="<@jspEl "type!='VIEW'"/>">
							<div class="form-actions">
								<button type="button" class="btn btn-success" onclick="javascript:save('<@jspEl "entity.${clazz.pkProperty.name}"/>');">
								<i class="icon-save"></i>
								保存信息</button>
								<button type="reset" class="btn" >
								<i class="icon-remove"></i>
								取消</button>
							</div>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="<@jspEl 'ctx'/>/js/common-content-scripts.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/js/additional-methods.min.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/assets/bootstrap-datepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/assets/bootstrap-datepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/custom/${moduleName}/${clazzLowerName}Input.js"></script>
<script>
	$(function(){
		if('<@jspEl "type"/>'!='VIEW'){
			initNotView();
		}
	});
</script>
<script src="<@jspEl 'ctx'/>/js/form.serialize.js"></script>