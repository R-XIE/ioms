<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="iitdev" uri="/iitdev-tag" %>
<jsp:useBean id="now" class="java.util.Date" /> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/chosen-bootstrap/chosen/chosen.css" />
<link rel="stylesheet" type="text/css" media="all" href="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-bs2.css" />
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">运维更改维护记录</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_log();">运维更改维护记录</a> <span class="divider">/</span></li>
				<li class="active"><c:if test="${type=='ADD'}">更改维护记录添加</c:if>
						<c:if test="${type=='VIEW'}">查看更改维护记录</c:if>
						<c:if test="${type=='EDIT'}">编辑更改维护记录</c:if></li>
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
						<c:if test="${type=='ADD'}">更改维护记录添加</c:if>
						<c:if test="${type=='VIEW'}">查看更改维护记录</c:if>
						<c:if test="${type=='EDIT'}">编辑更改维护记录</c:if>
						(*注意，请在本地写好复制,以防网站过期.)	
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down" ></a> 
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform" id="input_log_form">
						
						<div class="control-group">
							<label class="control-label">记录人</label>
							<div class="controls">
									<input type="text" class="span3" name="logStaffName"
										id="logStaffName" disabled="disabled"
										<c:if test="${type!='ADD'}">
											value="${entity.logStaffName}" 
										</c:if>
										<c:if test="${type=='ADD'}">
											value='<iitdev:loginInfo type="staffRealName"/>'
										</c:if>
										/>
									<input type="hidden" name="logStaff"	id="logStaff"
										<c:if test="${type!='ADD'}">
											value="${entity.logStaff}" 
										</c:if>
										<c:if test="${type=='ADD'}">
											value='<iitdev:loginInfo type="staffId"/>'
										</c:if>
									/>
							</div>
						</div>
						
						<div class="control-group">
						<label class="control-label">记录日期</label>
							<div class="controls">
								<div class="input-prepend input-group">
			                    <span class="add-on input-group-addon"><i class="glyphicon icon-calendar fa fa-calendar"></i></span>
								<input type="text" style="width: 400px" class="span6"  disabled="disabled" id="logDate"  name="logDate"
									<c:if test="${type!='ADD'}">
									value="<fmt:formatDate value='${entity.logDate}' pattern='yyyy-MM-dd' />"
									</c:if>
									<c:if test="${type=='ADD'}">
									value="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd' />"
									</c:if>
								/>
							</div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">服务器地址</label>
							<div class="controls">
								<input type="text" class="span6" name="logLocation"
									id="logLocation" placeholder="请输入服务器地址"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.logLocation}" />
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">维修原因</label>
							<div class="controls">
								<input type="text" class="span6" name="logCause"
									id="logCause" placeholder="请输入维修原因"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.logCause}" />
							</div>
						</div>
						
						<div class="control-group">
                             	<label class="control-label">维修内容</label>
                                <div class="controls">
                                   <textarea class="span6 ckeditor" name="logTitle" id="logTitle" rows="4" style="resize: none;"
                                   <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                   >${entity.logTitle}</textarea>
                                </div>
                        </div>
                      
                        <div class="control-group">
                             	<label class="control-label">维修方法</label>
                                <div class="controls">
                                   <textarea class="span6 ckeditor" name="logContent" id="logContent" rows="4" style="resize: none;"
                                   <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                   >${entity.logContent}</textarea>
                                </div>
                        </div>
                        
                        
                        <div class="control-group">
                             	<label class="control-label">备注说明</label>
                                <div class="controls">
                                   <textarea class="span6 ckeditor" name="logRemark" id="logRemark" rows="4" style="resize: none;"
                                   <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                   >${entity.logRemark}</textarea>
                                </div>
                        </div>
                        
                        <div class="control-group">
							<label class="control-label">维修情况</label>
							<div class="controls">
								<c:if test="${type!='VIEW'}">
									<select class="span6 chzn-select" name="logState"
										id="logState" tabindex="1" >
										<c:forEach items="${logMap}" var="map">
											<option value="${map.key}"
												<c:if test="${entity.logState==map.key}"> 
								                selected="selected"
								                </c:if>>
												${map.value}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${type=='VIEW'}">
									<input type="text" class="span6" name="logState"
										id="logState" disabled="disabled"
										value="${logMap[entity.logState]}" />
								</c:if>
							</div>
						</div>
						
                        <c:if test="${type!='VIEW'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success" onclick="javascript:save('${entity.logId}');">
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
<script src="${ctx}/js/common-content-scripts.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/additional-methods.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/moment-zh-CN.js"></script>
<script src="${ctx}/custom/operation/logInput.js"></script>
<script>
	$(function(){
		if('${type}'!='VIEW'){
			initNotView();
		}
	});
</script>
<script src="${ctx}/js/form.serialize.js"></script>