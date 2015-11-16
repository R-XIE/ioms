<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="iitdev" uri="/iitdev-tag" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/chosen-bootstrap/chosen/chosen.css" />
<link rel="stylesheet" type="text/css" media="all" href="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-bs2.css" />
<c:if test="${!empty s  }">
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
			<h3 class="page-title">${info}周报管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_weekly();">${info}周报管理</a> <span class="divider">/</span></li>
				<li class="active"><c:if test="${type=='ADD'}">${info}周报添加</c:if>
						<c:if test="${type=='VIEW'}">查看${info}周报</c:if>
						<c:if test="${type=='EDIT'}">编辑${info}周报</c:if></li>
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
			<div class="widget green">
				<div class="widget-title">
					<h4>
						<i class="icon-reorder"></i>
						<c:if test="${type=='ADD'}">${info}面试信息添加</c:if>
						<c:if test="${type=='VIEW'}">查看${info}周报</c:if>
						<c:if test="${type=='EDIT'}">编辑${info}周报</c:if>
						(*注意，请在本地写好复制,以防网站过期.)	
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down" ></a> 
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform" id="input_weekly_form">
						<input type="hidden" name="weeklyCreateDate" id="weeklyCreateDate" value="<fmt:formatDate value="${entity.weeklyCreateDate}"
												pattern="yyyy-MM-dd" />"/>
						<c:if test="${empty s}">
						<div class="control-group">
							<label class="control-label">编报人</label>
							<div class="controls">
								<%-- <c:if test="${type!='VIEW'}">
									<select class="span6 chzn-select" name="weeklyStaff"
										id="weeklyStaff" tabindex="1" >
										<c:forEach items="${staffMap}" var="map">
											<option value="${map.key}"
												<c:if test="${entity.weeklyStaff==map.key}"> 
								                selected="selected"
								                </c:if>>
												${map.value}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${type=='VIEW'}"> --%>
									<input type="text" class="span3" name="weeklyStaffName"
										id="weeklyStaffName" disabled="disabled"
										value="<iitdev:loginInfo type="staffRealName"/>" />
									<input type="hidden" name="weeklyStaff"	id="weeklyStaff"
										value="<iitdev:loginInfo type="staffId"/>" />
								<%-- </c:if> --%>
							</div>
						</div>
						</c:if>
						<div class="control-group">
						<label class="control-label">周报起始日期</label>
							<div class="controls">
								<div class="input-prepend input-group">
			                    <span class="add-on input-group-addon"><i class="glyphicon icon-calendar fa fa-calendar"></i></span>
								<input type="text" style="width: 400px" class="span6"  disabled="disabled" id="weeklyDate"  name="weeklyDate"/>
							</div>
							</div>
						</div>
						
                      
                        <div class="control-group">
                             	<label class="control-label">上周工作总结</label>
                                <div class="controls">
                                   <textarea class="span6 ckeditor" name="weeklyLastSummary" id="weeklyLastSummary" rows="4" style="resize: none;"
                                   <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                   >${entity.weeklyLastSummary}</textarea>
                                </div>
                        </div>
                        <div class="control-group">
                             	<label class="control-label">本周工作内容</label>
                                <div class="controls">
                                   <textarea class="span6 ckeditor" name="weeklyThisSummary" id="weeklyThisSummary" rows="4" style="resize: none;"
                                   <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                   >${entity.weeklyThisSummary}</textarea>
                                </div>
                        </div>
                        <div class="control-group">
                             	<label class="control-label">下周工作计划</label>
                                <div class="controls">
                                   <textarea class="span6 ckeditor" name="weeklyNextSummary" id="weeklyNextSummary" rows="4" style="resize: none;"
                                   <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                   >${entity.weeklyNextSummary}</textarea>
                                </div>
                        </div>
                        <div class="control-group">
                             	<label class="control-label">工作遇到问题</label>
                                <div class="controls">
                                   <textarea class="span6 ckeditor" name="weeklyProblem" id="weeklyProblem" rows="4" style="resize: none;"
                                   <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                   >${entity.weeklyProblem}</textarea>
                                </div>
                        </div>
                        <div class="control-group">
                             	<label class="control-label">工作注意事项</label>
                                <div class="controls">
                                   <textarea class="span6 ckeditor" name="workNotes" id="workNotes" rows="4" style="resize: none;"
                                   <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                   >${entity.workNotes}</textarea>
                                </div>
                        </div>
                        <c:if test="${empty s}">
                        <div class="control-group">
							<label class="control-label">个人周报状态(已提交个人不可更改)</label>
							<div class="controls">
								<c:if test="${type!='VIEW'}">
									<select class="span6 chzn-select" name="weeklyState"
										id="weeklyState" tabindex="1" >
										<c:forEach items="${weeklyMap}" var="map">
											<option value="${map.key}"
												<c:if test="${entity.weeklyState==map.key}"> 
								                selected="selected"
								                </c:if>>
												${map.value}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${type=='VIEW'}">
									<input type="text" class="span6" name="weeklyState"
										id="weeklyState" disabled="disabled"
										value="${weeklyMap[entity.weeklyState]}" />
								</c:if>
							</div>
						</div>
                        </c:if>
                        <c:if test="${type!='VIEW'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success" onclick="javascript:save('${entity.weeklyId}');">
								<i class="icon-save"></i>
								保存</button>
								<button type="reset" class="btn" >
								<i class="icon-remove"></i>
								取消</button>
							</div>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<input id="hid_weeklyDate" type="hidden" value="<fmt:formatDate value='${entity.weeklyBeginDate}' pattern='yyyy-MM-dd' /> - <fmt:formatDate value='${entity.weeklyEndDate}' pattern='yyyy-MM-dd' />"/>
<script src="${ctx}/js/common-content-scripts.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/additional-methods.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/moment-zh-CN.js"></script>
<script>
	$(function(){
		if('${type}'=='ADD')
			$('#weeklyDate').attr('value',moment().startOf('isoWeek').format('YYYY-MM-DD') + ' - ' +moment().endOf('isoWeek').format('YYYY-MM-DD'));
		else
			$('#weeklyDate').attr('value',$('#hid_weeklyDate').val());
	});
</script>
<c:if test="${empty s}">
<script src="${ctx}/custom/weekly/weeklyInput.js"></script>
</c:if>
<c:if test="${!empty s}">
<script src="${ctx}/custom/welcome/weeklyInput.js"></script>
</c:if>
<script>
	$(function(){
		if('${type}'!='VIEW'){
			initNotView();
		}
	});
</script>
<script src="${ctx}/js/form.serialize.js"></script>