<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/chosen-bootstrap/chosen/chosen.css" />
<link rel="stylesheet" type="text/css" media="all" href="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-bs2.css" />
<c:if test="${!empty type && type=='SV'  }">
	<c:set var="sv" value="个人"></c:set>
</c:if>
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">${sv}请假信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span
					class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_leave();">${sv}请假信息管理</a><span
					class="divider">/</span></li>
				<li class="active"><c:if test="${type=='ADD'}">请假信息新增</c:if> <c:if
						test="${type=='VIEW' || type=='SV'}">查看${sv}请假信息</c:if> <c:if
						test="${type=='EDIT'}">编辑请假信息信息</c:if></li>
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
			<div class="widget green">
				<div class="widget-title">
					<h4>
						<i class="icon-reorder"></i>
						<c:if test="${type=='ADD'}">请假信息新增</c:if>
						<c:if test="${type=='VIEW'|| type=='SV'}">查看${sv}请假信息</c:if>
						<c:if test="${type=='EDIT'}">编辑请假信息</c:if>
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down"></a>
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform"
						id="input_leave_form">
						<div class="control-group">
							<label class="control-label">请假人</label>
							<div class="controls">
								<c:if test="${type!='VIEW' && type!='SV'}">
									<select class="span6 chzn-select" name="staffId"
										id="staffId" tabindex="1">
										<c:forEach items="${staffMap}" var="map">
											<option value="${map.key}"
												<c:if test="${entity.staffId==map.key}"> 
								                selected="selected"
								                </c:if>>
												${map.value}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${type=='VIEW' || type=='SV'}">
									<input type="text" class="span6" name="staffId"
										id="staffId" disabled="disabled"
										value="${staffMap[entity.staffId]}" />
								</c:if>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">请假类别</label>
							<div class="controls">
								<c:if test="${type!='VIEW' && type!='SV'}">
									<select class="span6 chzn-select" name="leaveType"
										id="leaveType" tabindex="1">
										<c:forEach items="${leaveMap}" var="map">
											<option value="${map.key}"
												<c:if test="${entity.leaveType==map.key}"> 
								                selected="selected"
								                </c:if>>
												${map.value}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${type=='VIEW' || type=='SV'}">
									<input type="text" class="span6" name="leaveType"
										id="leaveType" disabled="disabled"
										value="${leaveMap[entity.leaveType]}" />
								</c:if>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">请假事由</label>
							<div class="controls">
								<input type="text" class="span6" name="leaveSubject"
									id="leaveSubject" placeholder="请输入请假事由"
									<c:if test="${type=='VIEW' || type=='SV'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.leaveSubject}" />
							</div>
						</div>
						<c:if test="${type!='VIEW' && type!='SV'}">
						<div class="control-group">
							<label class="control-label" for="leaveDate">请假起止时间</label>
			                    <div class="controls">
			                     <div class="input-prepend input-group">
			                       <span class="add-on input-group-addon"><i class="glyphicon icon-calendar fa fa-calendar"></i></span>
			                       <input type="text" style="width: 400px" readonly="readonly" name="leaveDate" id="leaveDate" class="form-control"   class="span6"
			                        <c:if test="${type=='EDIT'}">
			                        value='<fmt:formatDate value="${entity.leaveDateBegin}" pattern="yyyy-MM-dd HH:mm"/> - <fmt:formatDate value="${entity.leaveDateEnd}" pattern="yyyy-MM-dd HH:mm"/>'
									</c:if>
			                       />
			                     </div>
			                    </div>
						</div> 
						</c:if>
						<c:if test="${type=='VIEW' || type=='SV'}">
						<div class="control-group">
						<label class="control-label">请假起止时间</label>
							<div class="controls">
								<input type="text" class="span6"     disabled="disabled" 
									value="<fmt:formatDate value="${entity.leaveDateBegin}" pattern="yyyy-MM-dd HH:mm"/> - <fmt:formatDate value="${entity.leaveDateEnd}" pattern="yyyy-MM-dd HH:mm"/>" />
							</div>
						</div>
						</c:if>
						<div class="control-group">
							<label class="control-label">请假天数</label>
							<div class="controls">
								<input type="text" class="span2" name="leaveDaysDay"
									id="leaveDaysDay" placeholder="请输入请假天数"
									<c:if test="${type=='VIEW' || type=='SV'}">
                                  	 disabled="disabled" 
                                  	 value="${entity.leaveDaysDay}天" 
                                  	 </c:if>
                                  	 <c:if test="${type!='VIEW' && type!='SV'}">
									value="${entity.leaveDaysDay}" 
									</c:if>/>
									<input type="text" class="span2" name="leaveDaysHour"
									id="leaveDaysHour" placeholder="请输入请假小时数"
									<c:if test="${type=='VIEW' || type=='SV'}">
                                  	 disabled="disabled" 
                                  	value="<fmt:formatNumber value="${entity.leaveDaysHour}" pattern="0.0"/>小时"  
                                  	 </c:if>
                                  	  <c:if test="${type=='EDIT'}" >
									value="<fmt:formatNumber value="${entity.leaveDaysHour}" pattern="0.0"/>"  
									</c:if>/>
							</div>
						</div>
						<c:if test="${type=='VIEW' || type=='SV'}">
						<div class="control-group">
							<label class="control-label">填写日期</label>
							<div class="controls">
								<input type="text" class="span6" name="createDate"
										id="createDate" disabled="disabled"
										value="<fmt:formatDate value='${entity.createDate}' pattern='yyyy-MM-dd'/>" />
							</div>
						</div>
						</c:if>
						<c:if test="${type!='VIEW' && type!='SV'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success"
									onclick="javascript:save('${entity.leaveId}');">
									<i class="icon-save"></i> 保存
								</button>
								<button type="reset" class="btn">
									<i class="icon-remove"></i> 取消
								</button>
							</div>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="${ctx}/js/common-content-scripts.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/additional-methods.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/moment-zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-zh-CN.js"></script>
<c:if test="${!empty type && type!='SV'  }">
	<script src="${ctx}/custom/member/leaveInput.js"></script>
	<script>
		$(function() {
			if ('${type}' != 'VIEW') {
				initNotView();
			}
		});
	</script>
</c:if>
<c:if test="${!empty type && type=='SV'  }">
	<script src="${ctx}/custom/welcome/leaveInput.js"></script>
</c:if>