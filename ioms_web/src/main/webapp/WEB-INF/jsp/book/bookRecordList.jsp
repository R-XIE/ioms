<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="iitdev" uri="/iitdev-tag" %>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
<c:if test="${! empty type }">
<c:set var="info" value="${code}"></c:set>
</c:if>
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">${info}图书借还记录信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_book();">图书信息管理</a> <span class="divider">/</span></li>
				<li class="active">${info}图书借还记录信息管理</li>
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
						<i class="icon-reorder"></i>${info}图书借还记录信息列表
					</h4>
					<span class="tools"> <a href="javascript:;" id="index_refresh" 
						<c:if test="${empty type }">
						data-url="${ctx}/book/bookRecordActionList.htm"
						</c:if>
						<c:if test="${!empty type }">
						data-url="${ctx}/book/bookRecordActionBookRecordList.htm?bookId=${type}"
						</c:if>
						
						class="icon-refresh"></a> <a href="javascript:;"
						class="icon-chevron-down"></a> 
					</span>
				</div>
				<div class="widget-body">
					<div>
						<div class="clearfix">
							<c:if test="${bookType==0}">
							<div class="btn-group">
								<button class="btn btn-warning" onclick="javascript:doAction('add','${type}')">
									<i class="icon-plus icon-white"></i> 借书
								</button>
							</div>
							</c:if>
						</div>
						<div class="space15" ></div>
						<table class="table table-striped table-hover table-bordered"
							id="editable-sample">
							<thead>
								<tr>
									<th>序号</th>
									<th>图书编号</th>
									<th>图书名称</th>
									<th>借阅人</th>
									<th>借阅状态</th>
									<th>借阅时间</th>
									<th>归还时间</th>
									<th>操作</th>	
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="bookRecord" varStatus="status">
								<tr class="">
									<td>${status.count}</td>
									<td>${bookRecord.bookCode}</td>
									<td>${bookRecord.bookName}</td>
									<td>${bookRecord.staffRealName}</td>
									<td>${bookRecordMap[bookRecord.bookRecordState]}</td>
									<td><fmt:formatDate value='${bookRecord.borrowDate}' pattern='yyyy-MM-dd'/></td>
									<td><fmt:formatDate value='${bookRecord.returnDate}' pattern='yyyy-MM-dd'/></td>
									<td>
										<c:if test="${ bookRecord.bookRecordState==1}">
										<button class="btn btn-small btn-danger" onclick="javascript:doAction('del','${bookRecord.bookRecordId}')" >
											<i class="icon-remove icon-white"></i> 删除
										</button>
										</c:if>
										<c:if test="${ bookRecord.bookRecordState==0}">
										<button type="button" class="btn btn-small btn-warning" onclick="javascript:doAction('return','${bookRecord.bookRecordId}')" >
											<i class="icon-cloud-upload"></i> 还书
										</button>
										</c:if>
									</td>
									
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
<script src="${ctx}/custom/book/bookRecordList.js"></script>
