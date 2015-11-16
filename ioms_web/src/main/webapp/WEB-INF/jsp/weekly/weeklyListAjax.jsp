<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="iitdev" uri="/iitdev-tag"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/simple-form.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-bs2.css" />
<style>
.row-fluid .input_span4 {
	margin-left: 0;
}
</style>
<c:if test="${! empty s}">
	<c:set var="info" value="个人"></c:set>
</c:if>
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">${info}周报管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span
					class="divider">/</span></li>
				<li><a href="#">${info}周报管理</a> <span class="divider">/</span></li>
				<li class="active">列表信息</li>
				<li class="pull-right search-wrap"><%@include
						file="/common/search.jspf"%></li>
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
						<i class="icon-reorder"></i>${info}周报列表
					</h4>
					<span class="tools"> <a href="javascript:;"
						id="index_refresh" data-url="${url}" class="icon-refresh"></a> <a
						href="javascript:;" class="icon-chevron-down"></a>
					</span>
				</div>
				<div class="widget-body">
					<div>
						<div class="row-fluid">
							<div class=" clearfix btn_clear">
								<div class=" btn-group">
									<button class="btn btn-info"
										onclick="javascript:doAction('search')">
										<i class="icon-search icon-white"></i> 搜索
									</button>
								</div>
								<div class=" btn-group">
									<button class="btn btn-danger"
										onclick="javascript:doAction('reset')">
										<i class="icon-refresh icon-white"></i> 清空
									</button>
								</div>
								<div class=" btn-group">
									<button class="btn btn-warning"
										onclick="javascript:doAction('add')">
										<i class="icon-plus icon-white"></i> 新增
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<form id="queryForm" method="post">
							<div class="span4 input_span4">
								<div class="input-prepend">
									<span class="btn">显示行数</span>
									<!-- <input type="text" class="span2 search-query search_content"> -->
									<select class="span2 search-query search_content" name="s">
										<c:forEach items="${pageMap}" var="type">
											<option value="${type.key}"
												<c:if test="${type.key==query.s}">selected="selected"</c:if>>${type.value}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="span4 input_span4">
								<div class="input-prepend">
									<span class="btn">填报人</span>
									<input type="text" name="weeklyStaff"
										value="${query.weeklyStaff}"
										class="span2 search-query search_content">
								</div>
							</div>

							<div class="span4 input_span4">
								<div class="input-prepend">
									<span class="btn">周报日期起</span>
									<input type="text" name="weeklyBeginDate"
										value="${query.weeklyBeginDate}"
										class="span2 search-query search_content">
								</div>
							</div>

							<div class="span4 input_span4">
								<div class="input-prepend">
									<span class="btn">周报日期止</span>
									<input type="text" name="weeklyEndDate"
										value="${query.weeklyEndDate}"
										class="span2 search-query search_content">
								</div>
							</div>
							<div class="span6 input_span4">
								<div class="input-prepend control-group">
									<div class="controls">
										<div class="input-prepend input-group">
											<span class="btn">编写日期</span>
											<input type="text" style="width: 95%" readonly="readonly"
												name="weeklyCreateDateBE" id="weeklyCreateDateBE" class="form-control"
												class="span6" value="${query.weeklyCreateDateBE}" />
										</div>
									</div>
								</div>
							</div>
							
							<div class="span6 input_span4">
								<div class="input-prepend">
									<span class="btn">周报状态</span>
									<!-- <input type="text" class="span2 search-query search_content"> -->
									<select class="span2 search-query search_content"
										name="weeklyState">
										<option value="">请选择</option>
										<c:forEach items="${weeklyMap}" var="type">
											<option value="${type.key}"
												<c:if test="${!empty query.weeklyState && type.key==query.weeklyState}">selected="selected"</c:if>>${type.value}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</form>
					</div>
					<table class="table table-striped table-hover table-bordered"
						id="editable-sample">
						<thead>
							<tr>
								<th>序号</th>
								<th>填报人</th>
								<th>周报时间起</th>
								<th>周报时间止</th>
								<th>编写日期</th>
								<th>周报状态</th>
								<th>编辑</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${page.list==null ||  fn:length(page.list) == 0}">
								<tr>
									<td colspan="8">没有您要搜索的内容</td>
								</tr>
							</c:if>
							<c:forEach items="${page.list}" var="weekly" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td><a style="cursor: pointer;"
										onclick="javscript:doAction('view','${weekly.weeklyId}')">${weekly.weeklyStaffName}</a></td>
									<td><fmt:formatDate value='${weekly.weeklyBeginDate}'
											pattern='yyyy-MM-dd' /></td>
									<td><fmt:formatDate value='${weekly.weeklyEndDate}'
											pattern='yyyy-MM-dd' /></td>
									<td><fmt:formatDate value='${weekly.weeklyCreateDate}'
											pattern='yyyy-MM-dd' /></td>
									<td><c:if test="${ weekly.weeklyState==0 && !empty s }">
											${weeklyMap[weekly.weeklyState]}
											</c:if> <c:if test="${ weekly.weeklyState!=0 }">
											<a style="cursor: pointer;"
												onclick="javscript:doAction('state','${weekly.weeklyId}')">
												${weeklyMap[weekly.weeklyState]} </a>
										</c:if> <c:if test="${ weekly.weeklyState==0 && empty s }">
											<a style="cursor: pointer;"
												onclick="javscript:doAction('state_un','${weekly.weeklyId}')">
												${weeklyMap[weekly.weeklyState]} </a>
										</c:if></td>
									<td><c:if
											test="${(weekly.weeklyState!=0 && !empty s) || empty s }">
											<button class="btn btn-small btn-primary"
												onclick="javascript:doAction('edit','${weekly.weeklyId}')">
												<i class="icon-pencil icon-white"></i> 编辑
											</button>
										</c:if></td>
									<td><c:if
											test="${(weekly.weeklyState!=0 && !empty s) || empty s }">
											<button class="btn btn-small btn-danger"
												onclick="javascript:doAction('del','${weekly.weeklyId}')">
												<i class="icon-remove icon-white"></i> 删除
											</button>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<iitdev:page result="${page}" />
				</div>
			</div>
		</div>
		<!-- END EXAMPLE TABLE widget-->
	</div>
</div>
<!-- END EDITABLE TABLE widget-->
<script src="${ctx}/js/common-content-scripts.js"></script>
<script src="${ctx}/js/head.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/uniform/jquery.uniform.min.js"></script>
<script src="${ctx}/js/refresh.js"></script>
<%-- <script src="${ctx}/js/custombwi.js"></script> --%>
<script src="${ctx}/js/bootstrap-paginator.js"></script>
<script src="${ctx}/js/utils.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/moment-zh-CN.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-zh-CN.js"></script>
<c:if test="${empty s }">
	<script src="${ctx}/custom/weekly/weeklyListAjax.js"></script>
</c:if>
<c:if test="${!empty s }">
	<script src="${ctx}/custom/welcome/weeklyListAjax.js"></script>
</c:if>