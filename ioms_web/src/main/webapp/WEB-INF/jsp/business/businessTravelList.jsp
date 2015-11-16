<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="iitdev" uri="/iitdev-tag"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/uniform/css/uniform.default.css" />
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
			<h3 class="page-title">${info}出差信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span
					class="divider">/</span></li>
				<li><a href="#">${info}出差信息管理</a> <span class="divider">/</span></li>
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
						<i class="icon-reorder"></i>${info}出差信息列表
					</h4>
					<span class="tools"> <a href="javascript:;"
						id="index_refresh" data-url="${url}" class="icon-refresh"></a> <a
						href="javascript:;" class="icon-chevron-down"></a>
					</span>
				</div>
				<div class="widget-body">
					<div>
						<div class="clearfix">
							<c:if test="${ empty s }">
								<div class="btn-group pull-left">
									<button class="btn btn-warning"
										onclick="javascript:doAction('add')">
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
						<div class="space15"></div>
						<table class="table table-striped table-hover table-bordered"
							id="editable-sample">
							<thead>
								<tr>
									<th>序号</th>
									<th>出差人</th>
									<th>出差时间起</th>
									<th>出差时间止</th>
									<th>出差地址</th>
									<th>出差目的</th>
									<th>出差同行人</th>
									<th>交通工具</th>
									<th>出差总费用</th>
									<c:if test="${ empty s }">
										<th>编辑</th>
										<th>删除</th>
									</c:if>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="travel" varStatus="status">
									<tr class="">
										<input type="hidden" id="travelTicket-${travel.businessTravelId}" value="<fmt:formatNumber value='${travel.travelTicket}' type='currency' />"/>
										<input type="hidden" id="travelFare-${travel.businessTravelId}" value="<fmt:formatNumber value='${travel.travelFare}' type='currency' />"/>
										<input type="hidden" id="travelFares-${travel.businessTravelId}" value="<fmt:formatNumber value='${travel.travelFares}' type='currency' />"/>
										<input type="hidden" id="travelTransport-${travel.businessTravelId}" value="<fmt:formatNumber value='${travel.travelTransport}' type='currency' />"/>
										<input type="hidden" id="travelAllowance-${travel.businessTravelId}" value="<fmt:formatNumber value='${travel.travelAllowance}' type='currency' />"/>
										<input type="hidden" id="travelAccommodation-${travel.businessTravelId}" value="<fmt:formatNumber value='${travel.travelAccommodation}' type='currency' />"/>
										<input type="hidden" id="travelOther-${travel.businessTravelId}" value="<fmt:formatNumber value='${travel.travelOther}' type='currency' />"/>
										<td>${status.count}</td>
										<td><a style="cursor: pointer;"
											onclick="javscript:doAction('view','${travel.businessTravelId}')">${travel.travelStaffName}</a></td>
										<td><fmt:formatDate value="${travel.travelStartTime}"
												pattern="yyyy-MM-dd HH:mm" /></td>
										<td><fmt:formatDate value="${travel.travelEndTime}"
												pattern="yyyy-MM-dd HH:mm" /></td>
										<td>${travel.travelAddress}</td>
										<td>${travel.travelGoal}</td>
										<td>${travel.travelToGether}</td>
										<td>${travel.travelVehicle}</td>
										<td><a style="cursor: pointer;"	onclick="javscript:viewFares('${travel.businessTravelId}')">
										<fmt:formatNumber value="${travel.travelFaresCount}" type="currency" /></a></td>
										<c:if test="${ empty s }">
											<td>
												<button class="btn btn-small btn-primary"
													onclick="javascript:doAction('edit','${travel.businessTravelId}')">
													<i class="icon-pencil icon-white"></i> 编辑
												</button>
											</td>
											<td>
												<button class="btn btn-small btn-danger"
													onclick="javascript:doAction('del','${travel.businessTravelId}')">
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
<div id="fares-more-div" class="modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">出差费用明细</h3>
		
	</div>
	<div class="modal-body">
		<br> <a href="javascript:;" class="icon-btn span7"> <i class="icon-fighter-jet"><i></i></i>	<div>机票费</div> <span class="badge badge-success fares-d-1"></span></a>
		     <a href="javascript:;" class="icon-btn span4"> <i class="icon-signin"><i></i></i>      <div>火车费</div> <span class="badge badge-important fares-d-2"></span></a>
		     <a href="javascript:;" class="icon-btn span4"> <i class="icon-twitter"><i></i></i>     <div>车船费</div> <span class="badge badge-info fares-d-3"></span></a>
		     <a href="javascript:;" class="icon-btn span7"> <i class="icon-list"><i></i></i>        <div>市内交通</div> <span class="badge badge-important fares-d-4"></span></a>
		     <a href="javascript:;" class="icon-btn span7"> <i class="icon-paper-clip"><i></i></i>  <div>出差补助</div> <span class="badge badge-important fares-d-5"></span></a>
		     <a href="javascript:;" class="icon-btn span4"> <i class="icon-money"><i></i></i>       <div>住宿费</div> <span class="badge badge-success fares-d-6"></span></a> 
		     <a href="javascript:;" class="icon-btn span11"> <i class="icon-time"><i></i></i>		<div>其他补助</div> <span class="badge badge-info fares-d-7"></span></a> 
	</div>
	<div class="modal-footer">
		<button class="btn red" data-dismiss="modal" aria-hidden="true">关闭</button>
		<button data-dismiss="modal" class="btn btn-success">确定</button>
	</div>
</div>

<script type="text/javascript"
	src="${ctx}/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/data-tables/DT_bootstrap.js"></script>
<script src="${ctx}/js/common-content-scripts.js"></script>
<script src="${ctx}/js/head.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/uniform/jquery.uniform.min.js"></script>
<script src="${ctx}/js/refresh.js"></script>
<script src="${ctx}/js/custombwi.js"></script>
<script type="text/javascript">
	function viewFares(id){
		$('.fares-d-1').html($('#travelTicket-'+id).val());
		$('.fares-d-2').html($('#travelFare-'+id).val());
		$('.fares-d-3').html($('#travelFares-'+id).val());
		$('.fares-d-4').html($('#travelTransport-'+id).val());
		$('.fares-d-5').html($('#travelAllowance-'+id).val());
		$('.fares-d-6').html($('#travelAccommodation-'+id).val());
		$('.fares-d-7').html($('#travelOther-'+id).val());
		$('#fares-more-div').modal('show');
	}
</script>
<c:if test="${empty s }">
	<script src="${ctx}/custom/business/travelList.js"></script>
</c:if>
<c:if test="${!empty s }">
<script type="text/javascript">
	$(function() {
		//EditableTable.init();
		$('#editable-sample').dataTable(maxDataTable);
	});
	function doAction(fn,id){
		if(fn=='view'){
			blockPost(ctx+'/welcome/businessTravel'+'View.htm', {recordId:id});
		}
	}
	</script>
</c:if>