<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" href="${ctx}/assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />  
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
			<h3 class="page-title">面试信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_interview();">面试信息管理</a> <span class="divider">/</span></li>
				<li class="active"><c:if test="${type=='ADD'}">面试信息注册</c:if>
						<c:if test="${type=='VIEW'}">查看面试信息</c:if>
						<c:if test="${type=='EDIT'}">编辑面试信息</c:if></li>
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
						<c:if test="${type=='ADD'}">面试信息注册</c:if>
						<c:if test="${type=='VIEW'}">查看面试信息</c:if>
						<c:if test="${type=='EDIT'}">编辑面试信息</c:if>
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down" ></a> 
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform" id="input_interview_form">
						<input id="id" type="hidden" value="${entity.interviewId }"></input>
						<div class="control-group">
							<label class="control-label">应聘人姓名</label>
							<div class="controls">
								<input type="text" class="span6" name="interviewName" id="interviewName"   placeholder="请输入应聘人姓名"
								 <c:if test="${type=='VIEW'}"> 
								 disabled="disabled"
								 </c:if>
								 value="${entity.interviewName}"/> 
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">面试官姓名(可多个)</label>
							<div class="controls">
								<input type="text" class="span6" name="interviewToName" id="interviewToName"  placeholder="请输入面试官姓名"
								<c:if test="${type=='VIEW'}"> 
								disabled="disabled"
								</c:if>
								value="${entity.interviewToName}"/> 
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">性别</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                                  <div id="sex_buttons">
                                       <input name="interviewSex" id="interviewSex" type="checkbox" 
                                       <c:if test="${entity.interviewSex!=-1}">
                                       	checked="checked"
                                       </c:if>
                                       >
                                  </div>
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="interviewSex" id="interviewSex"  disabled="disabled" value="${sexMap[entity.interviewSex]}"/> 
                                  </c:if>
                            </div>
                         </div>
                         
                         <div class="control-group">
							<label class="control-label">面试岗位</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                            	  	<select class="span6 chzn-select" name="interviewPosition" id="interviewPosition"
											 tabindex="1">
								            <c:forEach items="${positionMap}" var="map">  
								                <option value="${map.key}" 
								                <c:if test="${entity.interviewPosition==map.key}"> 
								                selected="selected"
								                </c:if>
								                >  
								                ${map.value}</option>  
								            </c:forEach>  
									</select>
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="interviewPosition" id="interviewPosition" disabled="disabled" value="${positionMap[entity.interviewPosition]}"/> 
                                  </c:if>
                            </div>
                         </div>
                         <!-- 面试时间 -->
                         <div class="control-group">
							<label class="control-label">面试时间</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                            	  	 <input id="interviewDate"  type="text" value="<fmt:formatDate value='${entity.interviewDate}' pattern='yyyy-MM-dd HH:mm'/>" 
                            	  	 placeholder="请输入面试时间" size="16" class="span6 m-ctrl-medium" name="interviewDate" >
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="interviewDate" id="interviewDate" disabled="disabled" 
                                  	 value="<fmt:formatDate value='${entity.interviewDate}' pattern='yyyy-MM-dd HH:mm'/>"/> 
                                  </c:if>
                            </div>
                         </div>
                         
                         <div class="control-group">
							<label class="control-label">联系电话</label>
                            <div class="controls">
                            	  <input type="text" class="span6" name="interviewPhone" id="interviewPhone" placeholder="请输入联系电话"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  value="${entity.interviewPhone}"/> 
                            </div>
                         </div>
                        
                          <div class="control-group">
                              <label class="control-label">当前状况</label>
                                <div class="controls">
                                     <c:if test="${type!='VIEW'}">
                            	  	<select class="span6 chzn-select" name="interviewCurrent" id="interviewCurrent"
											 tabindex="1">
								            <c:forEach items="${currentMap}" var="map">  
								                <option value="${map.key}" 
								                <c:if test="${entity.interviewCurrent==map.key}"> 
								                selected="selected"
								                </c:if>
								                >  
								                ${map.value}</option>  
								            </c:forEach>  
									</select>
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="interviewCurrent" id="interviewCurrent" disabled="disabled" value="${currentMap[entity.interviewCurrent]}"/> 
                                  </c:if>
                                  
                                </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">现在(之前)工作单位</label>
                            <div class="controls">
                            	 <input type="text" class="span6" name="interviewCurrentWork" id="interviewCurrentWork"  placeholder="请输入现在(之前)工作单位"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  value="${entity.interviewCurrentWork}"/> 
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">跳槽原因</label>
                            <div class="controls">
                            	  <input type="text" class="span6" name="interviewQuit" id="interviewQuit"  placeholder="请输入跳槽原因"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  value="${entity.interviewQuit}"/> 
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">期望薪水</label>
                            <div class="controls">
                            	 <div class="input-prepend input-append">
                            	 <span class="add-on">￥</span>
                                 <input type="text" class="span12" name="interviewSalary" id="interviewSalary"  placeholder="请输入期望薪资，例如3000.00"
                                  	<c:if test="${type=='VIEW'}">
                                  	disabled="disabled" 
                                  	 </c:if>
                                 value="${entity.interviewSalary}"/> 
                                 </div>
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">评分</label>
                            <div class="controls">
                            	  <input type="text" class="span6" name="interviewScore" id="interviewScore"  placeholder="请输入最后评分，例如7.50"
                                  	<c:if test="${type=='VIEW'}">
                                  	disabled="disabled" 
                                  	 </c:if>
                                 value="${entity.interviewScore}"/> 
                            </div>
                         </div>
                         
                         <div class="control-group">
                              <label class="control-label">面试结果</label>
                                <div class="controls">
                                     <c:if test="${type!='VIEW'}">
                            	  	<select class="span6 chzn-select" name="interviewResult" id="interviewResult" 
											 tabindex="1">
								            <c:forEach items="${resultMap}" var="map">  
								                <option value="${map.key}" 
								                <c:if test="${entity.interviewResult==map.key}"> 
								                selected="selected"
								                </c:if>
								                >  
								                ${map.value}</option>  
								            </c:forEach>  
									</select>
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="interviewResult" id="interviewResult" disabled="disabled" value="${resultMap[entity.interviewResult]}"/> 
                                  </c:if>
                                  
                                </div>
                         </div>
                         <c:if test="${type=='VIEW'}">
                         <div class="control-group">
							<label class="control-label">记录日期</label>
                            <div class="controls">
                            	  <input type="text" class="span6"disabled="disabled" 
                                 value="<fmt:formatDate value='${entity.createTime}' pattern='yyyy-MM-dd HH:mm'/>"/> 
                            </div>
                         </div>
                         </c:if>
                        <c:if test="${type=='EDIT'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success" onclick="javascript:save('${entity.interviewId}');">
								<i class="icon-save"></i>
								保存基本信息</button>
								<button type="button" class="btn btn-info" onclick="javascript:upload('${entity.interviewId}');">
								<i class="icon-upload-alt"></i>
								保存简历信息</button>
								<button type="reset" class="btn" >
								<i class="icon-remove"></i>
								取消</button>
							</div>
						</c:if>
						<c:if test="${type=='ADD'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success" onclick="javascript:save('${entity.interviewId}');">
								<i class="icon-save"></i>
								保存基本信息</button>
								<button type="reset" class="btn" >
								<i class="icon-remove"></i>
								取消</button>
							</div>
						</c:if>
						<c:if test="${!empty entity.interviewCv && type=='VIEW'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success" onclick="javascript:download('${entity.interviewCv}');">下载简历</button>
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
<script type="text/javascript" src="${ctx}/assets/bootstrap-toggle-buttons/static/js/jquery.toggle.buttons.js"></script>
<script type="text/javascript" src="${ctx}/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-datepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-datepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/custom/member/interviewInput.js"></script>
<script>
	$(function(){
		if('${type}'!='VIEW'){
			initNotView();
		}
	});
</script>