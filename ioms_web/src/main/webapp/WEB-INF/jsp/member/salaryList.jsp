<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="iitdev" uri="/iitdev-tag" %>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
<c:set var="info" value="个人"></c:set>
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">${info}薪资管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#">${info}薪资管理</a> <span class="divider">/</span></li>
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
						<i class="icon-reorder"></i>${info}薪资列表
					</h4>
					<span class="tools"> <a href="javascript:;" id="index_refresh" data-url="${url}"
						class="icon-refresh"></a> <a href="javascript:;"
						class="icon-chevron-down"></a> 
					</span>
				</div>
				<div class="widget-body">
					<div>
						<table class="table table-striped table-hover table-bordered"
							id="editable-sample">
							<thead>
								<tr>
									<th>序号</th>
									<th>姓名</th>
									<th>月份</th>
									<th>应出勤</th>
									<th>实出勤</th>
									<th>应发工资</th>
									<th>实发工资</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="salary" varStatus="status">
								<tr class="">
									<td>${status.count}</td>
									<td><a style="cursor: pointer;" onclick="javscript:doAction('view','${salary.salaryId}')">${salary.salaryStaffName}</a></td>
									<td>${salary.salaryDateMonths}</td>
									<td>${salary.salaryAttendance}</td>
									<td>${salary.salaryFactAttendance}</td>
									<td><fmt:formatNumber value="${salary.salaryCount}" type="currency" /></td>
									<td><fmt:formatNumber value="${salary.salaryCountFact}" type="currency" /></td>
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
<script src="${ctx}/custom/welcome/salaryList.js"></script>