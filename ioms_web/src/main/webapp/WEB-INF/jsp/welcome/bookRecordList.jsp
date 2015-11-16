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
			<h3 class="page-title">个人图书借还记录信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li class="active">个人图书借还记录信息管理</li>
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
						<i class="icon-reorder"></i>个人图书借还记录信息列表
					</h4>
					<span class="tools"> <a href="javascript:;" id="index_refresh" 
						data-url="${ctx}/welcome/bookRecordList.htm"
						class="icon-refresh"></a> <a href="javascript:;"
						class="icon-chevron-down"></a> 
					</span>
				</div>
				<div class="widget-body">
					<div>
						<div class="space15" ></div>
						<table class="table table-striped table-hover table-bordered"
							id="editable-sample">
							<thead>
								<tr>
									<th>序号</th>
									<th>图书编号</th>
									<th>图书名称</th>
									<th>图书类型</th>
									<th>借阅状态</th>
									<th>借阅时间</th>
									<th>归还时间</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="bookRecord" varStatus="status">
								<tr class="">
									<td>${status.count}</td>
									<td>${bookRecord.bookCode}</td>
									<td>${bookRecord.bookName}</td>
									<td>${bookRecord.bookTypeLable}</td>
									<td>${bookRecordMap[bookRecord.bookRecordState]}</td>
									<td><fmt:formatDate value='${bookRecord.borrowDate}' pattern='yyyy-MM-dd'/></td>
									<td><fmt:formatDate value='${bookRecord.returnDate}' pattern='yyyy-MM-dd'/></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div id="book_record_input">
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
</script>