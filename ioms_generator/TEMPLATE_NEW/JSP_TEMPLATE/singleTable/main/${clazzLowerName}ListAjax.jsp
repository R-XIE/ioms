<#assign classNameLower = clazz.className?uncap_first>
<#assign properties=clazz.properties>
<#macro jspEl value>${r"${"}${value}}</#macro> 
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="<@jspEl 'pageContext.request.contextPath'/>" />
<%@ taglib prefix="iitdev" uri="/iitdev-tag" %>
<link rel="stylesheet" type="text/css" href="<@jspEl 'ctx'/>/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="<@jspEl 'ctx'/>/css/simple-form.css" />
<link rel="stylesheet" type="text/css" media="all" href="<@jspEl 'ctx'/>/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-bs2.css" />
<style>
	.row-fluid .input_span4 {
		margin-left: 0;
	}
</style>
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
						<div class="row-fluid">
                            <div class=" clearfix btn_clear">
                                <div class=" btn-group">
                                    <button class="btn btn-info" onclick="javascript:doAction('search')">
                                    <i class="icon-search icon-white"></i> 搜索
                                    </button>
                                </div>
                                <div class=" btn-group">
                                    <button class="btn btn-danger" onclick="javascript:doAction('reset')">
                                    <i class="icon-refresh icon-white"></i> 清空
                                    </button>
                                </div>
                                <div class=" btn-group">
                                    <button class="btn btn-warning" onclick="javascript:doAction('add')">
                                    <i class="icon-plus icon-white"></i> 新增
                                    </button>
                                </div>
                            </div>
                        </div>
						<div class="row-fluid">
	                       <!--  <div class="span2">
	                            <select class="search_select">
	                                <option>100</option>
	                            </select><span class="search_line">行</span>
	                        </div> -->
	                        <form id="queryForm" method="post" action="<@jspEl 'ctx'/>/${moduleName}/${clazzLowerName}ActionListAjax.htm">
	                            <div class="span4 input_span4" >
	                                <div class="input-prepend">
	                                    <span  class="btn">显示行数</span>
	                                    <!-- <input type="text" class="span2 search-query search_content"> -->
	                                    <select class="span2 search-query search_content" name="s">
	                                    	<c:forEach items="<@jspEl 'pageMap'/>" var="type">
	                                    		<option value="<@jspEl 'type.key'/>" 
	                                    		<c:if test="<@jspEl 'type.key==query.s'/>">selected="selected"</c:if>
	                                    		><@jspEl 'type.value'/></option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>
	                            </div>
	                            <#list properties as prop>
									<#if (prop.name != clazz.pkProperty.name)&&(prop.name != "class")&&(!prop.name?ends_with("remark"))&&(!prop.name?ends_with("Remark"))&&(!prop.name?ends_with("content"))&&(!prop.name?ends_with("Content"))>
									<#if (!prop.name?ends_with("remark"))&&(!prop.name?ends_with("Remark"))&&(!prop.name?ends_with("content"))&&(!prop.name?ends_with("Content"))>
									<#if prop.asType == 'Date'>	
									<div class="span4 input_span4">
	                                <div class="input-prepend control-group">
					                    <div class="controls">
					                     <div class="input-prepend input-group">
					                       <span class="btn add-on input-group-addon">${prop.column.columnAlias}起始<!-- <i class="glyphicon icon-calendar fa fa-calendar"></i> --></span>
					                       <input type="text" style="width: 95%" readonly="readonly" name="${prop.name}BE" id="${prop.name}BE" class="form-control"   class="span6"
					                       	value="<@jspEl 'query.${prop.name}BE'/>"/>
					                     </div>
					                    </div>
	                                </div>
	                            	</div>
									<#else>
									<#if (prop.name?ends_with("State"))||(prop.name?ends_with("state"))|| (prop.name?ends_with("Id"))||(prop.name?ends_with("id")) || (prop.name?ends_with("Type"))||(prop.name?ends_with("type"))>
									<div class="span4 input_span4" >
	                                <div class="input-prepend">
	                                    <span  class="btn">${prop.column.columnAlias}</span>
	                                    <!-- <input type="text" class="span2 search-query search_content"> -->
	                                    <select class="span2 search-query search_content" name="${prop.name}">
	                                    	<option value="">请选择</option>
	                                    	<c:forEach items="<@jspEl '${prop.name}Map'/>" var="type">
	                                    		<option value="<@jspEl 'type.key'/>" 
	                                    		<c:if test="<@jspEl "query.${prop.name}==type.key"/>">selected="selected"</c:if>
	                                    		><@jspEl 'type.value'/></option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>
	                            	</div>
									<#else>
									<div class="span4 input_span4">
		                                <div class="input-prepend">
		                                    <span class="btn">${prop.column.columnAlias}</span>
		                                    <input type="text" name="${prop.name}" value="<@jspEl 'query.${prop.name}'/>"  class="span2 search-query search_content">
		                                </div>
	                            	</div>
									</#if>
									</#if>
									</#if>
									</#if>
								</#list>
	                        </form>
	                    </div>
						<table class="table table-striped table-hover table-bordered"
							id="editable-sample">
							<thead>
								<tr>
									<th>序号</th>
									<#list properties as prop>
									<#if (prop.name != clazz.pkProperty.name)&&(prop.name != "class")&&(!prop.name?ends_with("remark"))&&(!prop.name?ends_with("Remark"))&&(!prop.name?ends_with("content"))&&(!prop.name?ends_with("Content"))>
									<th>${prop.column.columnAlias}</th>
									</#if>
									</#list>
									<th>编辑</th>
									<th>删除</th>		
								</tr>
							</thead>
							<tbody>
								<c:if test="<@jspEl 'page.list==null ||  fn:length(page.list) == 0'/>">
									<tr>
										<td colspan="12">没有您要搜索的内容</td>
									</tr>
								</c:if>
								
								<c:forEach items="<@jspEl 'page.list'/>" var="vo" varStatus="status">
								<tr class="">
									<td><@jspEl 'status.count'/></td>
									<#list properties as prop>
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
						<iitdev:page result="<@jspEl 'page'/>"/>
					</div>
				</div>
			</div>
			<!-- END EXAMPLE TABLE widget-->
		</div>
	</div>
	<!-- END EDITABLE TABLE widget-->
</div>
<script type="text/javascript" src="<@jspEl 'ctx'/>/js/common-content-scripts.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/js/head.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/assets/uniform/jquery.uniform.min.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/js/refresh.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/js/bootstrap-paginator.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/js/utils.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/assets/bootstrap-daterangepicker-1.3.13/moment-zh-CN.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-zh-CN.js"></script>
<script type="text/javascript" src="<@jspEl 'ctx'/>/custom/${moduleName}/${clazzLowerName}ListAjax.js"></script>