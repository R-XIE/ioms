<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/jquery-file-upload/css/jquery.fileupload.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/jquery-file-upload/css/jquery.fileupload-ui.css" />
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
				<li><a href="#" onclick="javascript:ck_cost_edit('${entity.costId}');">编辑费用信息</a> <span class="divider">/</span></li>
				<li class="active">费用明细上传</li>
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
						费用明细上传
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down" ></a> 
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform" id="input_cost_update_form">
                         <input id="id" type="hidden" value="${entity.costId }"></input>
					<input id="detail" type="hidden" value="${entity.costDetail }"></input>
                         <div class="control-group">
							<label class="control-label">费用类别及名称</label>
                            <div class="controls">
                                <input type="text" class="span6" name="costType" id="costType" disabled="disabled" value="${costTypeMap[entity.costType]}"/> 
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">产生费用金额</label>
                            <div class="controls">
                            	  <input type="text" class="span6" name="costMoney" id="costMoney" placeholder="请输入产生费用金额"
                                  	 disabled="disabled" 
                                  value="${entity.costMoney}"/> 
                            </div>
                         </div>
                         
                         <div class="control-group">
							<label class="control-label">费用用处描述</label>
                            <div class="controls">
                            	  <input type="text" class="span6" name="costDesc" id="costDesc" placeholder="请输入费用用处描述"
                                  	 disabled="disabled" 
                                  value="${entity.costDesc}"/> 
                            </div>
                         </div>
                         
                         <div class="control-group">
							<label class="control-label">产生费用时间</label>
                            <div class="controls">
                                  	 <input type="text" class="span6" name="costTime" id="costTime" disabled="disabled" 
                                  	 value="<fmt:formatDate value='${entity.costTime}' pattern='yyyy-MM-dd HH:mm'/>"/> 
                            </div>
                         </div>
                        
                         <div class="control-group">
							<label class="control-label">记录时间</label>
                            <div class="controls">
                            	  <input type="text" class="span6"disabled="disabled" 
                                 value="<fmt:formatDate value='${entity.costCreateTime}' pattern='yyyy-MM-dd HH:mm'/>"/> 
                            </div>
                         </div>
                         <div class="form-actions">
						 		<span class="btn btn-info fileinput-button">
				                    <i class="glyphicon glyphicon-plus icon-upload-alt"></i>
				                    <span>上传明细信息</span>
				                    <input  id="fileupload" type="file" data-form-data='{"recordId": "${entity.costId}"}' name="fileupload">
				                </span>
								<button type="button" class="btn" id="delBtn" onclick="del('${entity.costId}','${entity.costDetail }')">
									<i class="icon-remove"></i>
									删除明细信息</button>
								<button type="button" class="btn" id="downloadBtn" onclick="download('${entity.costDetail }')">
									<i class="icon-download-alt"></i>
									下载明细信息</button>
						 </div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/js/common-content-scripts.js"></script>
<script src="${ctx}/assets/jquery-file-upload/jquery.fileupload.js"></script>
<script src="${ctx}/assets/jquery-file-upload/jquery.iframe-transport.js"></script>
<script src="${ctx}/assets/jquery-file-upload/jquery.ui.widget.js"></script>
<script src="${ctx}/assets/jquery-file-upload/jquery.xdr-transport.js"></script>
<script src="${ctx}/custom/cost/costUploadView.js"></script>