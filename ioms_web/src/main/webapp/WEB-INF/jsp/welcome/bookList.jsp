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
			<h3 class="page-title">图书信息列表</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#">图书信息列表</a> <span class="divider">/</span></li>
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
						<i class="icon-reorder"></i>图书信息列表
					</h4>
					<span class="tools"> <a href="javascript:;" id="index_refresh" data-url="${ctx}/welcome/bookList.htm"
						class="icon-refresh"></a> <a href="javascript:;"
						class="icon-chevron-down"></a> 
					</span>
				</div>
				<div class="widget-body">
					<div>
						<div class="clearfix">
								<div class="btn-group pull-left">
									<button class="btn btn-info"
										onclick="javascript:download()">
										<i class="icon-download-alt"></i> 下载图书馆管理制度
									</button>
								</div>
						</div>
						<div class="space15" ></div>
						<table class="table table-striped table-hover table-bordered"
							id="editable-sample">
							<thead>
								<tr>
									<th>序号</th>
									<th>图书编号</th>
									<th>图书类型</th>
									<th>图书名称</th>
									<th>图书状态</th>
									<th>借阅人</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="book" varStatus="status">
								<tr class="">
									<td>${status.count}</td>
									<td>${book.bookCode}</td>
									<td>${book.bookTypeLable}</td>
									<td>${book.bookName}</td>
									<td>
										<c:if test="${book.bookState==0}">
											<span class="label label-success label-mini">${bookMap[book.bookState]}</span>
										</c:if>
										<c:if test="${book.bookState==-1}">
											<span class="label label-important label-mini">${bookMap[book.bookState]}</span>
										</c:if>
										<c:if test="${book.bookState==-2}">
											<span class="label label-danger label-mini">${bookMap[book.bookState]}</span>
										</c:if>
									</td>
									<td>${book.borrowStaffName}</td>
									
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
<script type="text/javascript">
var oTable;
$(function() {
	//EditableTable.init();
	oTable=$('#editable-sample').dataTable(maxDataTable);
});
function ck_index(){
	blockPost(ctx+'/welcome/main.htm');
}
function download(){
	window.location = ctx +"welcome/bookListDownload.htm";
}
</script>