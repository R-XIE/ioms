<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="iitdev" uri="/iitdev-tag" %>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/simple-form.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-bs2.css" />
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
			<h3 class="page-title">薪资管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#">薪资管理</a> <span class="divider">/</span></li>
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
						<i class="icon-reorder"></i>薪资列表
					</h4>
					<span class="tools"> <a href="javascript:;" id="index_refresh" data-url="${url}"
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
									<div class=" btn-group">
										<button class="btn btn-warning"
											onclick="javascript:doAction('add')">
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
	                                    <span class="btn">姓&nbsp;&nbsp;&nbsp;名</span>
	                                    <input type="text" name="salaryStaffName" value="${query.salaryStaffName}"  class="span2 search-query search_content">
	                                </div>
	                            </div>
	                            <div class="span4 input_span4">
	                                <div class="input-prepend">
	                                    <span class="btn">月&nbsp;&nbsp;&nbsp;份</span>
	                                    <input type="text" name="salaryDateMonths" value="${query.salaryDateMonths}"  class="span2 search-query search_content">
	                                </div>
	                            </div>
	                            <div class="span4 input_span4">
	                                <div class="input-prepend">
	                                    <span class="btn">应出勤</span>
	                                    <input type="text" name="salaryAttendance" value="${query.salaryAttendance}"  class="span2 search-query search_content">
	                                </div>
	                            </div>
	                            <div class="span4 input_span4">
	                                <div class="input-prepend">
	                                    <span class="btn">实出勤</span>
	                                    <input type="text" name="salaryFactAttendance" value="${query.salaryFactAttendance}"  class="span2 search-query search_content">
	                                </div>
	                            </div>
							</form>
						</div>
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
									<th>编辑</th>
									<th>删除</th>	
								</tr>
							</thead>
							<tbody>
								<c:if test="${page.list==null ||  fn:length(page.list) == 0}">
									<tr>
										<td colspan="9">没有您要搜索的内容</td>
									</tr>
								</c:if>
								<c:forEach items="${page.list}" var="salary" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td><a style="cursor: pointer;" onclick="javscript:doAction('view','${salary.salaryId}')">${salary.salaryStaffName}</a></td>
									<td>${salary.salaryDateMonths}</td>
									<td>${salary.salaryAttendance}</td>
									<td>${salary.salaryFactAttendance}</td>
									<td>${salary.salaryCount}</td>
									<td>${salary.salaryCountFact}</td>
									<td>
										<button class="btn btn-small btn-primary" onclick="javascript:doAction('edit','${salary.salaryId}')" >
											<i class="icon-pencil icon-white"></i> 编辑
										</button>
									</td>
									<td>
										<button class="btn btn-small btn-danger" onclick="javascript:doAction('del','${salary.salaryId}')">
											<i class="icon-remove icon-white"></i> 删除
										</button>
									</td>
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
<script type="text/javascript" src="${ctx}/assets/uniform/jquery.uniform.min.js"></script>
<script src="${ctx}/js/refresh.js"></script>
<script src="${ctx}/js/bootstrap-paginator.js"></script>
<script src="${ctx}/js/utils.js"></script>
<script src="${ctx}/custom/member/salaryListAjax.js"></script>