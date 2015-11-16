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
			<h3 class="page-title">员工信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:home_index('${pageStatus}');">在职员工信息管理</a> <span class="divider">/</span></li>
				<c:if test="${pageStatus}">
				<li class="active">离职员工信息管理</li>
				</c:if>
				<c:if test="${!pageStatus}">
				<li class="active">信息列表</li>
				</c:if>
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
						<i class="icon-reorder"></i>
						<c:if test="${!pageStatus}">在职</c:if><c:if test="${pageStatus}">离职</c:if>
						员工列表
					</h4>
					<span class="tools"> <a href="javascript:;" id="index_refresh" data-url="${url}"
						class="icon-refresh"></a> <a href="javascript:;"
						class="icon-chevron-down"></a> 
					</span>
				</div>
				<div class="widget-body">
					<div>
						<div class="clearfix">
							<c:if test="${!pageStatus}">
							<div class="btn-group pull-left">
								<button class="btn btn-warning" onclick="javascript:doAction('add')">
									<i class="icon-plus icon-white"></i> 新增
								</button>
							</div>
							
							<div class="btn-group pull-right">
								<button class="btn btn-info" onclick="javascript:doAction('leave')">
									<i class="icon-search icon-white" ></i> 查看所有离职人员
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
									<c:if test="${!pageStatus}">
									<th>员工编号</th>
									<th>员工名称</th>
									<th>员工姓名</th>
									</c:if>
									<c:if test="${pageStatus}">
									<th>员工姓名</th>
									</c:if>
									<th>员工部门</th>
									<th>员工职位</th>
									<th>员工岗位</th>
									<th>员工学历</th>
									<th>所学专业</th>
									<th>毕业日期</th>
									<th>入职日期</th>
									<th>签订合同日期</th>
									<th>入职状态</th>
									<c:if test="${pageStatus}">
									<th>离职时间</th>
									</c:if>
									<c:if test="${!pageStatus}">
									<th>编辑</th>
									</c:if>
									<th>删除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="staff" varStatus="status">
								<tr class="">
									<td>${status.count}</td>
									<c:if test="${!pageStatus}">
									<td><a style="cursor: pointer;" onclick="javscript:doAction('view','${staff.staffId}')">${staff.staffCode}</a></td>
									<td>${staff.staffLoginName}</td>
									<td>${staff.staffRealName}</td>
									</c:if>
									<c:if test="${pageStatus}">
									<td><a style="cursor: pointer;" onclick="javscript:doAction('view','${staff.staffId}','${pageStatus}')">${staff.staffRealName}</a></td>
									</c:if>
									<td>${staff.branchName}</td>
									<td>${postMap[staff.postId]}</td>
									<td>${positionMap[staff.positionId]}</td>
									<td>${degreeMap[staff.staffDegree]}</td>
									<td>${staff.staffMajor}</td>
									<td><fmt:formatDate value="${staff.staffMajorDate}" pattern="yyyy-MM-dd"/></td>
									<td><fmt:formatDate value="${staff.staffEntryDate}" pattern="yyyy-MM-dd"/></td>
									<td><fmt:formatDate value="${staff.staffContractDate}" pattern="yyyy-MM-dd"/></td>
									<td>
										<a style="cursor: pointer;" onclick="javscript:doAction('change','${staff.staffId}','${pageStatus}')">
										${staffMap[staff.staffState]}
										</a>
										<input type="hidden" id="hid_leave_date_${staff.staffId}" value="${staff.leaveDate}"/>
										<input type="hidden" id="hid_leave_remark_${staff.staffId}" value="${staff.leaveRemark}"/>
									</td>
									<c:if test="${pageStatus}">
									<td>${staff.leaveDate}</td>
									</c:if>
									<c:if test="${!pageStatus}">
									<td>
										<button class="btn btn-small btn-primary" onclick="javascript:doAction('edit','${staff.staffId}','${pageStatus}')" >
											<i class="icon-pencil icon-white"></i> 编辑
										</button>
									</td>
									</c:if>
									<td>
										<c:if test="${loginInfoId!=staff.staffId}">
										<button class="btn btn-small btn-danger" onclick="javascript:doAction('del','${staff.staffId}')" >
											<i class="icon-remove icon-white"></i> 删除
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
<script src="${ctx}/custom/member/staffList.js"></script>