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
			<h3 class="page-title">费用信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_cost();">费用信息管理</a> <span class="divider">/</span></li>
				<li class="active"><c:if test="${type=='ADD'}">费用信息新增</c:if>
						<c:if test="${type=='VIEW'}">查看费用信息</c:if>
						<c:if test="${type=='EDIT'}">编辑费用信息</c:if></li>
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
						<c:if test="${type=='ADD'}">费用信息新增</c:if>
						<c:if test="${type=='VIEW'}">查看费用信息</c:if>
						<c:if test="${type=='EDIT'}">编辑费用信息</c:if>
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down" ></a> 
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform" id="input_cost_form">
						<input id="id" value="${entity.costId}"  type="hidden"/>
						<%-- <div class="control-group">
							<label class="control-label">费用名称</label>
							<div class="controls">
								<input type="text" class="span6" name="costName" id="costName"   placeholder="请输入费用名称"
								 <c:if test="${type=='VIEW'}"> 
								 disabled="disabled"
								 </c:if>
								 value="${entity.costName}"/> 
							</div>
						</div> --%>
                         <div class="control-group">
							<label class="control-label">费用类别及名称</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                            	  	<select class="span6 chzn-select" name="costType" id="costType"
											 tabindex="1">
								            <c:forEach items="${costTypeList}" var="list">  
								                <optgroup label="${list.lable}">
								                	<c:forEach items="${list.costTypeList}" var="type"> 
								                		<option value="${type.costTypeId}" 
								                		<c:if test="${entity.costType==type.costTypeId}"> 
										                selected="selected"
										                </c:if>
										                >  
										                ${type.costTypeName}</option>
								                	</c:forEach>
								                </optgroup>
								            </c:forEach>  
									</select>
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="costType" id="costType" disabled="disabled" value="${costTypeMap[entity.costType]}"/> 
                                  </c:if>
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">产生费用金额</label>
                            <div class="controls">
                            	  <input type="text" class="span6" name="costMoney" id="costMoney" placeholder="请输入产生费用金额"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  value="${entity.costMoney}"/> 
                            </div>
                         </div>
                         
                         <div class="control-group">
							<label class="control-label">费用用处描述</label>
                            <div class="controls">
                            	  <input type="text" class="span6" name="costDesc" id="costDesc" placeholder="请输入费用用处描述"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  value="${entity.costDesc}"/> 
                            </div>
                         </div>
                         
                         <div class="control-group">
							<label class="control-label">产生费用时间</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                            	  	 <input id="costTime"  type="text" value="<fmt:formatDate value='${entity.costTime}' pattern='yyyy-MM-dd HH:mm'/>" 
                            	  	 placeholder="请输入产生费用时间" size="16" class="span6 m-ctrl-medium" name="costTime" >
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="costTime" id="costTime" disabled="disabled" 
                                  	 value="<fmt:formatDate value='${entity.costTime}' pattern='yyyy-MM-dd HH:mm'/>"/> 
                                  </c:if>
                            </div>
                         </div>
                        
                         <c:if test="${type=='VIEW'}">
                         <div class="control-group">
							<label class="control-label">记录时间</label>
                            <div class="controls">
                            	  <input type="text" class="span6"disabled="disabled" 
                                 value="<fmt:formatDate value='${entity.costCreateTime}' pattern='yyyy-MM-dd HH:mm'/>"/> 
                            </div>
                         </div>
                         </c:if>
						 <c:if test="${type=='ADD'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success" onclick="javascript:save('${entity.costId}');">
								<i class="icon-save"></i>
								保存</button>
								<button type="reset" class="btn" >
								<i class="icon-remove"></i>
								取消</button>
							</div>
						 </c:if>
						 <c:if test="${type=='EDIT'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success" onclick="javascript:save('${entity.costId}');">
								<i class="icon-save"></i>
								保存基本信息</button>
								<button type="button" class="btn btn-info" onclick="javascript:upload('${entity.costId}');">
								<i class="icon-upload-alt"></i>
								上传明细信息</button>
								<button type="reset" class="btn" >
								<i class="icon-remove"></i>
								取消</button>
							</div>
						</c:if>
						<c:if test="${!empty entity.costDetail && type=='VIEW'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success" onclick="javascript:download('${entity.costDetail}');">下载明细</button>
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
<script src="${ctx}/custom/cost/costInput.js"></script>
<script>
	$(function(){
		if('${type}'!='VIEW'){
			initNotView();
		}
	});
</script>
<script src="${ctx}/js/form.serialize.js"></script>