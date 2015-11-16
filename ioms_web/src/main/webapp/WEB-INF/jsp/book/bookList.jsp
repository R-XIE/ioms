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
			<h3 class="page-title">图书信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#">图书信息管理</a> <span class="divider">/</span></li>
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
					<span class="tools"> <a href="javascript:;" id="index_refresh" data-url="${ctx}/book/bookActionList.htm"
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
							<!--  -->
							<div class="btn-group pull-right">
								<button class="btn btn-info" onclick="javascript:doAction('viewRecord')">
									<i class="icon-search icon-white"></i>查看图书所有借还记录
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
									<th>图书售价</th>
									<th>图书状态</th>
									<th>图书借还</th>
									<th>编辑</th>
									<th>删除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="book" varStatus="status">
								<tr class="">
									<td>${status.count}</td>
									<td>
										<a onclick="javscript:doAction('view','${book.bookId}')" href="#">
											${book.bookCode}
										</a>
										
									</td>
									<td>${book.bookTypeLable}</td>
									<td>${book.bookName}</td>
									<td><fmt:formatNumber value="${book.bookPrice}" type="currency"/> </td>
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
									<td>
										<c:if test="${book.bookState==0}">
										<button type="button" class="btn btn-small btn-success" onclick="javascript:doAction('borrow','${book.bookId}')" >
											<i class="icon-cloud-download"></i> 借书
										</button>
										</c:if>
										<c:if test="${book.bookState==-1}">
										<button type="button" class="btn btn-small btn-danger" onclick="javascript:doAction('returnA','${book.bookId}')" >
											<i class="icon-cloud-upload" ></i> 还书
										</button>
										</c:if>
										<button type="button" class="btn btn-small btn-info"  onclick="javascript:doAction('bookRecord','${book.bookId}')" >
											<i class="icon-search"></i> 查看借还记录
										</button>
									</td>
									<td>
										<button class="btn btn-small btn-primary" onclick="javascript:doAction('edit','${book.bookId}')" >
											<i class="icon-pencil icon-white"></i> 编辑
										</button>
									</td>
									
									<td>
										<c:if test="${book.bookState!=-1}">
											<button class="btn btn-small btn-danger" onclick="javascript:doAction('del','${book.bookId}')" >
												<i class="icon-remove icon-white"></i>删除
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
<script src="${ctx}/custom/book/bookList.js"></script>
