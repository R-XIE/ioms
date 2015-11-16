<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">敏感信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#">敏感信息管理</a> <span class="divider">/</span></li>
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
						<i class="icon-reorder"></i>敏感信息列表
					</h4>
					<span class="tools"> <a href="javascript:;" id="index_refresh" data-url="${url}"
						class="icon-refresh"></a> <a href="javascript:;"
						class="icon-chevron-down"></a> 
					</span>
				</div>
				<div class="widget-body">
					<div>
						<div class="clearfix">
							<div class="btn-group">
								<button class="btn btn-warning" onclick="javascript:doAction('add')">
									<i class="icon-plus icon-white"></i> 新增
								</button>
							</div>
						</div>
						<div class="space15" ></div>
						<table class="table table-striped table-hover table-bordered"
							id="editable-sample">
							<thead>
								<tr>
									<th>序号</th>
									<th>敏感信息标题</th>
									<th>敏感信息级别</th>
									<th>敏感信息位置</th>
									<th>敏感信息内容</th>
									<th>设置可看列表</th>
									<th>编辑</th>
									<th>删除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="pwd" varStatus="status">
								<tr class="">
									<td>${status.count}</td>
									<td>
										<a onclick="javscript:doAction('view','${pwd.pwdId}')" href="#">
											${pwd.pwdName}
										</a>
										
									</td>
									<td>${pwdLeaveMap[pwd.pwdLevel]}</td>
									<td>${pwd.pwdLocation}</td>
									<td>${pwd.pwdRemark}</td>
									<td>
										<c:if test="${pwd.pwdLevel==1}">
											<button class="btn btn-small btn-success" onclick="javascript:doAction('auth','${pwd.pwdId}')">
												<i class="icon-ok icon-white"></i> 设置
											</button>
										</c:if>
									</td>
									<td>
										<button class="btn btn-small btn-primary" onclick="javascript:doAction('edit','${pwd.pwdId}')" >
											<i class="icon-pencil icon-white"></i> 编辑
										</button>
									</td>
									
									<td>
										<button class="btn btn-small btn-danger" onclick="javascript:doAction('del','${pwd.pwdId}')" >
											<i class="icon-remove icon-white"></i>删除
										</button>
									</td>
									
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div id="book_borrow_input">
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
<script src="${ctx}/custom/operation/pwdList.js"></script>
