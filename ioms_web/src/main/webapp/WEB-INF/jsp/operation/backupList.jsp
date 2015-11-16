<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="iitdev" uri="/iitdev-tag" %>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">备份记录管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#">备份记录管理</a> <span class="divider">/</span></li>
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
						<i class="icon-reorder"></i>备份记录列表
					</h4>
					<span class="tools"> <a href="javascript:;" id="index_refresh" data-url="${url}"
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
									<th>备份内容</th>
									<th>备份服务器</th>
									<th>备份日期</th>
									<th>备份状态</th>
									<th>编辑</th>
									<th>删除</th>		
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="vo" varStatus="status">
								<tr class="">
									<td>${status.count}</td>
									<td><a style="cursor: pointer;" onclick="javscript:doAction('view','${vo.backupId}')">${vo.backupTitle}</a></td>
									<td>${vo.backupServer}</td>
									<td><fmt:formatDate value="${vo.backupDate}"  pattern="yyyy-MM-dd"/></td>
									<td>${backupMap[vo.backupState]}</td>
									<td>
										<button class="btn btn-small btn-primary" onclick="javascript:doAction('edit','${vo.backupId}')" >
											<i class="icon-pencil icon-white"></i> 编辑
										</button>
									</td>
									<td>
										<button class="btn btn-small btn-danger" onclick="javascript:doAction('del','${vo.backupId}')" >
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
	src="${ctx}/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/data-tables/DT_bootstrap.js"></script>
<script src="${ctx}/js/common-content-scripts.js"></script>
<script src="${ctx}/js/head.js"></script>
<script type="text/javascript" src="${ctx}/assets/uniform/jquery.uniform.min.js"></script>
<script src="${ctx}/js/refresh.js"></script>
<script src="${ctx}/custom/operation/backupList.js"></script>