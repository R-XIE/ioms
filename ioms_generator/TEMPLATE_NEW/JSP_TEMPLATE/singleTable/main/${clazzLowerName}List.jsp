<#assign classNameLower = clazz.className?uncap_first>
<#macro jspEl value>${r"${"}${value}}</#macro> 
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="<@jspEl 'pageContext.request.contextPath'/>" />
<%@ taglib prefix="iitdev" uri="/iitdev-tag" %>
<link rel="stylesheet" type="text/css" href="<@jspEl 'ctx'/>/assets/uniform/css/uniform.default.css" />
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
				<li><a href="#">${entityCnName}管理</a> <span class="divider">/</span></li>
				<li class="active">列表信息</li>
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
			<!-- BEGIN EXAMPLE TABLE widget-->

			<div class="widget purple">
				<div class="widget-title">
					<h4>
						<i class="icon-reorder"></i>${entityCnName}列表
					</h4>
					<span class="tools"> <a href="javascript:;" id="index_refresh" data-url="<@jspEl 'url'/>"
						class="icon-refresh"></a> <a href="javascript:;"
						class="icon-chevron-down"></a> 
					</span>
				</div>
				<div class="widget-body">
					<div>
						<div class="clearfix">
							<div class="btn-group pull-left">
								<button class="btn btn-warning" onclick="javascript:doAction('add')">
									<i class="icon-plus icon-white"></i> 新增
								</button>
							</div>
							<!-- 
							<div class="btn-group pull-right">
								<button class="btn dropdown-toggle" data-toggle="dropdown">
									工具<i class="icon-angle-down"></i>
								</button>
								<ul class="dropdown-menu pull-right">
									<li><a href="#">打印</a></li>
									<li><a href="#">保存</a></li>
									<li><a href="#">导出</a></li>
								</ul>
							</div> -->
						</div>
						<div class="space15" ></div>
						<table class="table table-striped table-hover table-bordered"
							id="editable-sample">
							<thead>
								<tr>
									<th>序号</th>
									<#list clazz.properties as prop>
									<#if (prop.name != clazz.pkProperty.name)&&(prop.name != "class")&&(!prop.name?ends_with("remark"))&&(!prop.name?ends_with("Remark"))&&(!prop.name?ends_with("content"))&&(!prop.name?ends_with("Content"))>
									<th>${prop.column.columnAlias}</th>
									</#if>
									</#list>
									<th>编辑</th>
									<th>删除</th>		
								</tr>
							</thead>
							<tbody>
								<c:forEach items="<@jspEl 'list'/>" var="vo" varStatus="status">
								<tr class="">
									<td><@jspEl 'status.count'/></td>
									<#list clazz.properties as prop>
									<#if (prop.name != clazz.pkProperty.name)&&(prop.name != "class")&&(!prop.name?ends_with("remark"))&&(!prop.name?ends_with("Remark"))&&(!prop.name?ends_with("content"))&&(!prop.name?ends_with("Content"))>
									<#if prop.asType == 'Date'>	
									<td><fmt:formatDate value="<@jspEl 'vo.${prop.name}'/>"  pattern="yyyy-MM-dd HH:mm"/></td>
									<#elseif prop.asType =='Number'>
									<td><@jspEl 'vo.${prop.name}'/></td>
									<#else>
									<#if prop.name == "${classNameLower}Name" || prop.name == "${classNameLower}Title">
									<td><a style="cursor: pointer;" onclick="javscript:doAction('view','<@jspEl 'vo.${clazz.pkProperty.name}'/>')"><@jspEl 'vo.${prop.name}'/></a></td>
									<#else>
									<td><@jspEl 'vo.${prop.name}'/></td>
									</#if>
									</#if>
									</#if>
									</#list>
									<td>
										<button class="btn btn-small btn-primary" onclick="javascript:doAction('edit','<@jspEl 'vo.${clazz.pkProperty.name}'/>')" >
											<i class="icon-pencil icon-white"></i> 编辑
										</button>
									</td>
									<td>
										<button class="btn btn-small btn-danger" onclick="javascript:doAction('del','<@jspEl 'vo.${clazz.pkProperty.name}'/>')" >
											<i class="icon-remove icon-white"></i> 删除
										</button>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- END EXAMPLE TABLE widget-->
		</div>
	</div>
	<!-- END EDITABLE TABLE widget-->
</div>
<script type="text/javascript"
	src="<@jspEl 'ctx'/>/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="<@jspEl 'ctx'/>/assets/data-tables/DT_bootstrap.js"></script>
<script src="<@jspEl 'ctx'/>/js/common-content-scripts.js"></script>
<script src="<@jspEl 'ctx'/>/js/head.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/assets/uniform/jquery.uniform.min.js"></script>
<script src="<@jspEl 'ctx'/>/js/refresh.js"></script>
<script src="<@jspEl 'ctx'/>/custom/${moduleName}/${clazzLowerName}List.js"></script>