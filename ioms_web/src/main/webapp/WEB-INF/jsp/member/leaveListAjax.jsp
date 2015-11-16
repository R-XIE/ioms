<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			<h3 class="page-title">${type}请假信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span
					class="divider">/</span></li>
				<li><a href="#">${type}请假信息管理</a> <span class="divider">/</span></li>
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
						<i class="icon-reorder"></i>${type}请假信息列表
					</h4>
					<span class="tools"> <a href="javascript:;"
						id="index_refresh" data-url="${ctx}/${wp}/leaveActionListAjax.htm"
						class="icon-refresh"></a> <a href="javascript:;"
						class="icon-chevron-down"></a>
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
								<c:if test="${empty type}">
									<div class=" btn-group">
										<button class="btn btn-warning"
											onclick="javascript:doAction('add')">
											<i class="icon-plus icon-white"></i> 新增
										</button>
									</div>
								</c:if>
							</div>
						</div>
						<div class="row-fluid">
							<!--  <div class="span2">
	                            <select class="search_select">
	                                <option>100</option>
	                            </select><span class="search_line">行</span>
	                        </div> -->
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
								<c:if test="${empty type}">
								<div class="span4 input_span4">
									<div class="input-prepend">
										<span class="btn">请假人</span>
										<input type="text" name="staffName" value="${query.staffName}"
											class="span2 search-query search_content">
									</div>
								</div>
								
								<div class="span4 input_span4">
									<div class="input-prepend">
										<span class="btn">请假人部门</span>
										<!-- <input type="text" class="span2 search-query search_content"> -->
										<select class="span2 search-query search_content"
											name="branchId">
											<option value="">请选择</option>
											<c:forEach items="${branchMap}" var="type">
												<option value="${type.key}"
													<c:if test="${type.key==query.branchId}">selected="selected"</c:if>>${type.value}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="span4 input_span4">
									<div class="input-prepend">
										<span class="btn">请假人岗位</span>
										<!-- <input type="text" class="span2 search-query search_content"> -->
										<select class="span2 search-query search_content"
											name="positionId">
											<option value="">请选择</option>
											<c:forEach items="${positionMap}" var="type">
												<option value="${type.key}"
													<c:if test="${type.key==query.positionId}">selected="selected"</c:if>>${type.value}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								</c:if>
								<div class="span4 input_span4">
									<div class="input-prepend">
										<span class="btn">请假类别</span>
										<!-- <input type="text" class="span2 search-query search_content"> -->
										<select class="span2 search-query search_content"
											name="leaveType">
											<option value="">请选择</option>
											<c:forEach items="${leaveMap}" var="type">
												<option value="${type.key}"
													<c:if test="${type.key==query.leaveType}">selected="selected"</c:if>>${type.value}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="span4 input_span4">
									<div class="input-prepend">
										<span class="btn">请假时间</span>
										<input type="text" name="leaveDate" value="${query.leaveDate}"
											class="span2 search-query search_content">
									</div>
								</div>
								<div class="span4 input_span4">
									<div class="input-prepend">
										<span class="btn">请假天数</span>
										<input type="text" name="leaveDays" value="${query.leaveDays}"
											class="span2 search-query search_content">
									</div>
								</div>
								<div class="span6 input_span4">
									<div class="input-prepend control-group">
										<div class="controls">
											<div class="input-prepend input-group">
												<span class="btn">录入起始日期<!-- <i class="glyphicon icon-calendar fa fa-calendar"></i> --></span>
												<input type="text" style="width: 95%" readonly="readonly"
													name="createDateBE" id="createDateBE" class="form-control"
													class="span6" value="${query.createDateBE}" />
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
						<table class="table table-striped table-hover table-bordered"
							id="editable-sample">
							<thead>
								<tr>
									<th>序号</th>
									<th>请假人</th>
									<th>请假人部门</th>
									<th>请假人岗位</th>
									<th>请假类别</th>
									<th>请假时间开始</th>
									<th>请假时间结束</th>
									<th>请假总天数</th>
									<th>填写时间</th>
									<c:if test="${empty type}">
										<th>编辑</th>
										<th>删除</th>
									</c:if>
								</tr>
							</thead>
							<tbody>
								<c:if test="${page.list==null ||  fn:length(page.list) == 0}">
									<tr>
										<c:if test="${empty type}">
										<td colspan="11">没有您要搜索的内容</td>
										</c:if>
										<c:if test="${!empty type}">
										<td colspan="9">没有您要搜索的内容</td>
										</c:if>
									</tr>
								</c:if>
								<c:forEach items="${page.list}" var="leave" varStatus="status">
									<tr>
										<td>${status.count}</td>
										<td><a style="cursor: pointer;"
											onclick="javscript:doAction('view','${leave.leaveId}')">${staffMap[leave.staffId]}</a></td>
										<td>${leave.branchName}</td>
										<td>${leave.positionName}</td>
										<td>${leaveMap[leave.leaveType]}</td>
										<td><fmt:formatDate value='${leave.leaveDateBegin}'
												pattern='yyyy-MM-dd HH:mm' /></td>
										<td><fmt:formatDate value='${leave.leaveDateEnd}'
												pattern='yyyy-MM-dd HH:mm' /></td>
										<td>${leave.leaveDays}</td>
										<td><fmt:formatDate value='${leave.createDate}'
												pattern='yyyy-MM-dd' /></td>
										<c:if test="${empty type}">
											<td>
												<button class="btn btn-small btn-primary"
													onclick="javascript:doAction('edit','${leave.leaveId}')">
													<i class="icon-pencil icon-white"></i> 编辑
												</button>
											</td>
											<td>
												<button class="btn btn-small btn-danger"
													onclick="javascript:doAction('del','${leave.leaveId}')">
													<i class="icon-remove icon-white"></i> 删除
												</button>
											</td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<iitdev:page result="${page}"/>
					</div>
				</div>
			</div>
			<!-- END EXAMPLE TABLE widget-->
		</div>
	</div>
	<!-- END EDITABLE TABLE widget-->
</div>
<script src="${ctx}/js/common-content-scripts.js"></script>
<script src="${ctx}/js/head.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/uniform/jquery.uniform.min.js"></script>
<script src="${ctx}/js/bootstrap-paginator.js"></script>
<script src="${ctx}/js/utils.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/moment-zh-CN.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-zh-CN.js"></script>
<script src="${ctx}/js/refresh.js"></script>
<c:if test="${empty type}">
	<script>
		var leaveLocal = '/member/leaveAction';
	</script>
</c:if>
<c:if test="${!empty type}">
	<script>
		var leaveLocal = '/welcome/leaveAction';
	</script>
</c:if>
<script src="${ctx}/custom/member/leaveListAjax.js"></script>
