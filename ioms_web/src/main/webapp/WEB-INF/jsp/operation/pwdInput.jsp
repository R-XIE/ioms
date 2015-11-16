<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/chosen-bootstrap/chosen/chosen.css" />
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">敏感信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span
					class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_pwd();">敏感信息管理</a><span
					class="divider">/</span></li>
				<li class="active"><c:if test="${type=='ADD'}">敏感信息新增</c:if> <c:if
						test="${type=='VIEW'}">查看敏感信息</c:if> <c:if
						test="${type=='EDIT'}">编辑敏感信息</c:if></li>
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
						<c:if test="${type=='ADD'}">敏感信息新增</c:if>
						<c:if test="${type=='VIEW'}">查看敏感信息</c:if>
						<c:if test="${type=='EDIT'}">编辑敏感信息</c:if>
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down"></a>
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform"
						id="input_pwd_form">
						<div class="control-group">
							<label class="control-label">敏感标题</label>
							<div class="controls">
								<input type="text" class="span6" name="pwdName"
									id="pwdName" placeholder="请输入敏感标题"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.pwdName}" />
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">敏感位置</label>
							<div class="controls">
								<input type="text" class="span6" name="pwdLocation"
									id="pwdLocation" placeholder="请输入敏感位置"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.pwdLocation}" />
							</div>
						</div>
						
						<div class="control-group">
                             	<label class="control-label">敏感内容</label>
                                <div class="controls">
                                   <textarea class="span6 ckeditor" name="pwdRemark" id="pwdRemark" rows="4" style="resize: none;"
                                   <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                   >${entity.pwdRemark}</textarea>
                                </div>
                        </div>
                        
                        <div class="control-group">
							<label class="control-label">敏感信息级别</label>
							<div class="controls">
								<c:if test="${type!='VIEW'}">
									<select class="span6 chzn-select" name="pwdLevel"
										id="pwdLevel" tabindex="1" >
										<c:forEach items="${pwdLeaveMap}" var="map">
											<option value="${map.key}"
												<c:if test="${entity.pwdLevel==map.key}"> 
								                selected="selected"
								                </c:if>>
												${map.value}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${type=='VIEW'}">
									<input type="text" class="span6" name="pwdLevel"
										id="pwdLevel" disabled="disabled"
										value="${pwdLeaveMap[entity.pwdLevel]}" />
								</c:if>
							</div>
						</div>
                        
						<c:if test="${type!='VIEW'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success"
									onclick="javascript:save('${entity.pwdId}');">
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
<!-- 
<script type="text/javascript"
	src="${ctx}/assets/bootstrap-datepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script> -->
<script src="${ctx}/custom/operation/pwdInput.js"></script>
<script>
	$(function(){
		if('${type}'!='VIEW'){
			initNotView();
		}
	});
</script>
<script src="${ctx}/js/form.serialize.js"></script>