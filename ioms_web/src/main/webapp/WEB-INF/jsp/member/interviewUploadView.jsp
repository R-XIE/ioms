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
			<h3 class="page-title">面试信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_interview();">面试信息管理</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_interview_edit('${entity.interviewId}');">编辑面试信息</a> <span class="divider">/</span></li>
				<li class="active">面试简历上传</li>
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
						面试简历上传
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down" ></a> 
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform" id="input_interview_form">
					<input id="id" type="hidden" value="${entity.interviewId }"></input>
					<input id="cv" type="hidden" value="${entity.interviewCv }"></input>
						<div class="control-group">
							<label class="control-label">应聘人姓名</label>
							<div class="controls">
								<input type="text" class="span6" name="interviewName" id="interviewName"   placeholder="请输入应聘人姓名"
								 disabled="disabled" value="${entity.interviewName}"/> 
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">面试官姓名(可多个)</label>
							<div class="controls">
								<input type="text" class="span6" name="interviewToName" id="interviewToName"  placeholder="请输入面试官姓名"
								disabled="disabled"	value="${entity.interviewToName}"/> 
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">性别</label>
                            <div class="controls">
                                  	 <input type="text" class="span6" name="interviewSex" id="interviewSex"  disabled="disabled" value="${sexMap[entity.interviewSex]}"/> 
                            </div>
                         </div>
                         
                         <div class="control-group">
							<label class="control-label">面试岗位</label>
                            <div class="controls">
                                <input type="text" class="span6" name="interviewPosition" id="interviewPosition" disabled="disabled" value="${positionMap[entity.interviewPosition]}"/> 
                            </div>
                         </div>
                         <!-- 面试时间 -->
                         <div class="control-group">
							<label class="control-label">面试时间</label>
                            <div class="controls">
                                  	 <input type="text" class="span6" name="interviewDate" id="interviewDate" disabled="disabled" 
                                  	 value="<fmt:formatDate value='${entity.interviewDate}' pattern='yyyy-MM-dd HH:mm'/>"/> 
                            </div>
                         </div>
                         
                         <div class="control-group">
							<label class="control-label">联系电话</label>
                            <div class="controls">
                            	  <input type="text" class="span6" name="interviewPhone" id="interviewPhone" placeholder="请输入联系电话"
                                  	 disabled="disabled" 
                                  value="${entity.interviewPhone}"/> 
                            </div>
                         </div>
                        
                         <div class="control-group">
							<label class="control-label">期望薪水</label>
                            <div class="controls">
                                 <input type="text" class="span6" name="interviewSalary" id="interviewSalary"  placeholder="请输入期望薪资，例如3000.00"
                                  	disabled="disabled" 
                                 value="${entity.interviewSalary}"/> 
                                 
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">评分</label>
                            <div class="controls">
                            	  <input type="text" class="span6" name="interviewScore" id="interviewScore"  placeholder="请输入最后评分，例如7.50"
                                  	disabled="disabled" 
                                 value="${entity.interviewScore}"/> 
                            </div>
                         </div>
                         
                         <div class="control-group">
                              <label class="control-label">面试结果</label>
                                <div class="controls">
                                  	 <input type="text" class="span6" name="interviewResult" id="interviewResult" disabled="disabled" value="${resultMap[entity.interviewResult]}"/> 
                                </div>
                         </div>
						 <div class="form-actions">
						 		<span class="btn btn-info fileinput-button">
				                    <i class="glyphicon glyphicon-plus icon-upload-alt"></i>
				                    <span>上传简历信息</span>
				                    <input  id="fileupload" type="file" data-form-data='{"recordId": "${entity.interviewId}"}' name="fileupload">
				                </span>
								<button type="button" class="btn" id="delBtn" onclick="del('${entity.interviewId}','${entity.interviewCv }')">
									<i class="icon-remove"></i>
									删除简历信息</button>
								<button type="button" class="btn" id="downloadBtn" onclick="download('${entity.interviewCv }')">
									<i class="icon-download-alt"></i>
									下载简历信息</button>
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
<script src="${ctx}/custom/member/interviewUploadView.js"></script>
