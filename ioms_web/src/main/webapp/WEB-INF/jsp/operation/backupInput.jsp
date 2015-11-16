<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/chosen-bootstrap/chosen/chosen.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/bootstrap-datepicker/css/bootstrap-datetimepicker.min.css" />
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">备份记录管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_backup();">备份记录管理</a> <span class="divider">/</span></li>
				<li class="active"><c:if test="${type=='ADD'}">备份记录添加</c:if>
						<c:if test="${type=='VIEW'}">查看备份记录</c:if>
						<c:if test="${type=='EDIT'}">编辑备份记录</c:if></li>
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
						<c:if test="${type=='ADD'}">备份记录添加</c:if>
						<c:if test="${type=='VIEW'}">查看备份记录</c:if>
						<c:if test="${type=='EDIT'}">编辑备份记录</c:if>
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down" ></a> 
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform" id="input_backup_form">
						<input id="id" type="hidden" value="${entity.backupId}"></input>
						
						<div class="control-group">
							<label class="control-label">备份服务器</label>
							<div class="controls">
								<input type="text" class="span6" name="backupServer"
									id="backupServer" placeholder="请输入备份服务器"
									<c:if test="${type=='VIEW'}"> 
									 disabled="disabled"
									 </c:if>
									value="${entity.backupServer}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">备份内容</label>
							<div class="controls">
								<input type="text" class="span6" name="backupTitle"
									id="backupTitle" placeholder="请输入备份内容"
									<c:if test="${type=='VIEW'}"> 
									 disabled="disabled"
									 </c:if>
									value="${entity.backupTitle}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">备份日期</label>
							<div class="controls">
								<c:if test="${ type!='VIEW' }">
								<input id="backupDate" type="text"
									value="<fmt:formatDate value='${entity.backupDate}' pattern='yyyy-MM-dd'/>"
									placeholder="请输入备份日期" size="16" class="span6 m-ctrl-medium"
									name="backupDate">
								</c:if>
								<c:if test="${ type== 'VIEW'}">
								<input type="text" class="span6" name="backupDate"
									id="backupDate" disabled="disabled"
									value="<fmt:formatDate value='${entity.backupDate}' pattern='yyyy-MM-dd'/>" />
								</c:if>
							</div>
						</div>
						<div class="control-group">
                             	<label class="control-label">备份详情</label>
                                <div class="controls">
                                   <textarea class="span6 ckeditor" name="backupContent" id="backupContent" rows="4" style="resize: none;"
                                   <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                   >${entity.backupContent}</textarea>
                                </div>
                        </div>
   						
  						<div class="control-group">
							<label class="control-label">备份状态</label>
							<div class="controls">
								<c:if test="${type!='VIEW'}">
								<select class="span6 chzn-select" name="backupState"
									id="backupState" tabindex="1">
									<c:forEach items='${backupMap}' var="map">
										<option value='${map.key}'
											<c:if test="${entity.backupState==map.key}"> 
								                selected="selected"
								                </c:if>>
											${map.value}</option>
									</c:forEach>
								</select>
								</c:if>
								<c:if test="${type=='VIEW'}">
								<input type="text" class="span6" name="backupState"
									id="backupState" disabled="disabled"
									value='${backupMap[entity.backupState]}' />
								</c:if>
							</div>
						</div>
                         <c:if test="${type!='VIEW'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success" onclick="javascript:save('${entity.backupId}');">
								<i class="icon-save"></i>
								保存信息</button>
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
<script type="text/javascript" src="${ctx}/assets/bootstrap-datepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-datepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/custom/operation/backupInput.js"></script>
<script>
	$(function(){
		if('${type}'!='VIEW'){
			initNotView();
		}
	});
</script>
<script src="${ctx}/js/form.serialize.js"></script>