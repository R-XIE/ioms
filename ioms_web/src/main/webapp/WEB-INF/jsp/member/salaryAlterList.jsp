<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="iitdev" uri="/iitdev-tag" %>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
<c:if test="${ ! empty type}">
<c:set var="type" value="个人"></c:set>
<c:set var="wp" value="welcome"></c:set>
</c:if>
<c:if test="${empty type}">
<c:set var="wp" value="member"></c:set>
</c:if>
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">${type}薪资异动信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#">${type}薪资异动信息管理</a> <span class="divider">/</span></li>
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
						<i class="icon-reorder"></i>${type}薪资异动信息列表
					</h4>
					<span class="tools"> <a href="javascript:;" id="index_refresh" data-url="${ctx}/${wp }/salaryAlterActionList.htm"
						class="icon-refresh"></a> <a href="javascript:;"
						class="icon-chevron-down"></a> 
					</span>
				</div>
				<div class="widget-body">
					<div>
						<div class="clearfix">
							<c:if test="${empty type}">
							<div class="btn-group pull-left">
								<button class="btn btn-warning" onclick="javascript:doAction('add')">
									<i class="icon-plus icon-white"></i> 新增
								</button>
							</div>
							</c:if>
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
									<th>申请人</th>
									<th>申请人部门</th>
									<th>申请人职位</th>
									<th>申请人入职日期</th>
									<th>异动原因</th>
									<th>原薪资标准</th>
									<th>异动后薪资标准</th>
									<th>生效月份</th>
									<th>填写日期</th>
									<c:if test="${empty type}">
									<th>编辑</th>
									<th>删除</th>	
									</c:if>	
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="alter" varStatus="status">
								<tr class="">
									<td>${status.count}</td>
									<td><a style="cursor: pointer;" onclick="javscript:doAction('view','${alter.alterId}')">${staffMap[alter.staffId]}</a></td>
									<td>${alter.branchName}</td>
									<td>${alter.positionName}</td>
									<td><fmt:formatDate value="${alter.staffEntryDate}" pattern="yyyy-MM-dd"/></td>
									<td>${alter.alterCauseLable}</td>
									<td><fmt:formatNumber value="${alter.alterBefore}" type="currency" /></td>
									<td><fmt:formatNumber value="${alter.alterAfter}" type="currency" /></td>
									<td>${alter.alterDate}</td>
									<td><fmt:formatDate value="${alter.alterCreateDate}" pattern="yyyy-MM-dd"/></td>
									<c:if test="${empty type}">
									<td>
										<button class="btn btn-small btn-primary" onclick="javascript:doAction('edit','${alter.alterId}')" >
											<i class="icon-pencil icon-white"></i> 编辑
										</button>
									</td>
									<td>
										<button class="btn btn-small btn-danger" onclick="javascript:doAction('del','${alter.alterId}')" >
											<i class="icon-remove icon-white"></i> 删除
										</button>
									</td>
									</c:if>	
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
<c:if test="${empty type}">
<script src="${ctx}/custom/member/alterList.js"></script>
</c:if>
<c:if test="${!empty type}">
<script src="${ctx}/custom/welcome/alterList.js"></script>
</c:if>