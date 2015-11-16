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
			<h3 class="page-title">员工管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_staff('${pageStatus}');">
				<c:if test="${!pageStatus}">在职</c:if><c:if test="${pageStatus}">离职</c:if>员工管理
				</a> <span class="divider">/</span></li>
				<li class="active"><c:if test="${type=='ADD'}">员工注册</c:if>
						<c:if test="${type=='VIEW'}">查看员工信息</c:if>
						<c:if test="${type=='EDIT'}">编辑员工信息</c:if></li>
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
						<c:if test="${type=='ADD'}">员工注册</c:if>
						<c:if test="${type=='VIEW'}">查看员工信息</c:if>
						<c:if test="${type=='EDIT'}">编辑员工信息</c:if>
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down" ></a> 
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform" id="input_staff_form">
						<div class="control-group">
							<label class="control-label">员工编号</label>
							<div class="controls">
								<input type="text" class="span6" name="staffCode" id="staffCode"  placeholder="请输入员工编号,员工编号与打卡机绑定,一旦设置无法更改!"
								 <c:if test="${type!='ADD'}"> 
								 disabled="disabled"
								 </c:if>
								 value="${entity.staffCode}"/> 
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">员工帐号</label>
							<div class="controls">
								<input type="text" class="span6" name="staffLoginName" id="staffLoginName" placeholder="请输入员工登录帐号,员工登录帐号,一旦设置无法更改!"
								<c:if test="${type!='ADD'}"> 
								disabled="disabled"
								</c:if>
								value="${entity.staffLoginName}"/> 
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">员工姓名</label>
							<div class="controls">
								<input type="text" class="span6" name="staffRealName" id="staffRealName" placeholder="请输入员工姓名"
								 <c:if test="${type=='VIEW'}">
								 disabled="disabled" 
								 </c:if>
								 value="${entity.staffRealName}"/> 
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">性别</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                                  <div id="sex_buttons">
                                       <input name="staffSex" id="staffSex" type="checkbox" 
                                       <c:if test="${entity.staffSex!=-1}">
                                       	checked="checked"
                                       </c:if>
                                       >
                                  </div>
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="staffSex" id="staffSex"  disabled="disabled" value="${sexMap[entity.staffSex]}"/> 
                                  </c:if>
                            </div>
                         </div>
                         
                         <div class="control-group">
							<label class="control-label">岗位</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                            	  	<select class="span6 chzn-select" name="positionId" id="positionId"
											 tabindex="1">
								            <c:forEach items="${positionMap}" var="map">  
								                <option value="${map.key}" 
								                <c:if test="${entity.positionId==map.key}"> 
								                selected="selected"
								                </c:if>
								                >  
								                ${map.value}</option>  
								            </c:forEach>  
									</select>
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="positionId" id="positionId" disabled="disabled" value="${positionMap[entity.positionId]}"/> 
                                  </c:if>
                            </div>
                         </div>
                         
                         <div class="control-group">
							<label class="control-label">职位</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                            	  	<select class="span6 chzn-select" name="postId" id="postId"
											 tabindex="2">
								            <c:forEach items="${postMap}" var="map">  
								                <option value="${map.key}" 
								                <c:if test="${entity.postId==map.key}"> 
								                selected="selected"
								                </c:if>
								                >  
								                ${map.value}</option>  
								            </c:forEach>  
									</select>
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="postId" id="postId" disabled="disabled" value="${postMap[entity.postId]}"/> 
                                  </c:if>
                            </div>
                         </div>
                         
                         <div class="control-group">
							<label class="control-label">角色</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                            	  	<select class="span6 chzn-select" name="roleId" id="roleId"
											 tabindex="3">
								            <c:forEach items="${roleMap}" var="map">  
								                <option value="${map.key}" 
								                <c:if test="${entity.roleId==map.key}"> 
								                selected="selected"
								                </c:if>
								                >  
								                ${map.value}</option>  
								            </c:forEach>  
									</select>
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="roleId" id="roleId" disabled="disabled" value="${roleMap[entity.roleId]}"/> 
                                  </c:if>
                            </div>
                         </div> 
						<!-- 
						<div class="control-group">
							<label class="control-label">所属部门</label>
							<div class="controls">
								<select class="span6 chzn-select" name="branchId" id="branchId"
									data-placeholder="选择所属部门" tabindex="1">
									<c:if test="${!empty branchMap}">  
							            <c:forEach items="${branchMap}" var="map">  
							                <option value="${map.key}" 
							                <c:if test="${entity.branchId==map.key}"> 
							                selected="selected"
							                </c:if>
							                >  
							                ${map.value}</option>  
							            </c:forEach>  
							        </c:if>  
								</select>
							</div>
						</div>
						 -->
						<div class="control-group">
							<label class="control-label">员工身份证</label>
                            <div class="controls">
                                 <input type="text" class="span6" name="staffIdCard" id="staffIdCard"  placeholder="请输入员工真实身份证号"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  value="${entity.staffIdCard}"/> 
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">员工联系电话</label>
                            <div class="controls">
                            	  <input type="text" class="span6" name="staffPhone" id="staffPhone"  placeholder="请输入员工的电话或者手机"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  value="${entity.staffPhone}"/> 
                            </div>
                         </div>
                         
                        
                         <div class="control-group">
                              <label class="control-label">Email</label>
                                  <div class="controls">
                                      <div class="input-icon left">
	                                       <i class="icon-envelope"></i>
	                                       <input type="text" class="span6" name="staffEmail" id="staffEmail" placeholder="请输入员工Email"
	                                       <c:if test="${type=='VIEW'}">
		                                  	 disabled="disabled" 
		                                  	</c:if>
	                                       value="${entity.staffEmail}"/>
                                       </div>
                                   </div>
                         </div> 
                          <div class="control-group">
                              <label class="control-label">紧急联系方式</label>
                                <div class="controls">
                                    <input type="text" class="span6" name="staffEmergency" id="staffEmergency"  placeholder="请输入员工紧急联系方式"
                                    <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                    value="${entity.staffEmergency}"/>
                                </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">员工家庭住址</label>
                            <div class="controls">
                            	 <input type="text" class="span6" name="staffAddress" id="staffAddress" placeholder="请输入员工家庭住址"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  value="${entity.staffAddress}"/> 
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">员工学历</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                            	  	<!--  -->
                            	  	<select class="span6 chzn-select" name="staffDegree" id="staffDegree"
											 tabindex="5">
								            <c:forEach items="${degreeMap}" var="map">  
								                <option value="${map.key}" 
								                <c:if test="${entity.staffDegree==map.key}"> 
								                selected="selected"
								                </c:if>
								                >  
								                ${map.value}</option>  
								            </c:forEach>  
									</select>
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="staffDegree" id="staffDegree" disabled="disabled" value="${degreeMap[entity.staffDegree]}"/> 
                                  </c:if>
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">员工毕业院校</label>
                            <div class="controls">
                                 <input type="text" class="span6" name="staffGraduation" id="staffGraduation"  placeholder="请输入员工毕业院校"
                                  	<c:if test="${type=='VIEW'}">
                                  	disabled="disabled" 
                                  	 </c:if>
                                 value="${entity.staffGraduation}"/> 
                                 
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">员工专业</label>
                            <div class="controls">
                            	  <input type="text" class="span6" name="staffMajor" id="staffMajor"  placeholder="请输入员工所学专业"
                                  	<c:if test="${type=='VIEW'}">
                                  	disabled="disabled" 
                                  	 </c:if>
                                 value="${entity.staffMajor}"/> 
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">毕业日期</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                            	  	 <input id="staffMajorDate"  type="text" value="<fmt:formatDate value='${entity.staffMajorDate}' pattern='yyyy-MM-dd'/>" 
                            	  	 size="16" class="span6 m-ctrl-medium" name="staffMajorDate" placeholder="请输入员工毕业日期" >
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="staffMajorDate" id="staffMajorDate" disabled="disabled" 
                                  	 value="<fmt:formatDate value='${entity.staffMajorDate}' pattern='yyyy-MM-dd'/>"/> 
                                  </c:if>
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">入职日期</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                            	  	<input id="staffEntryDate" type="text" value="<fmt:formatDate value='${entity.staffEntryDate}' pattern='yyyy-MM-dd'/>" 
                            	  	 size="16" class="span6 m-ctrl-medium" name="staffEntryDate" placeholder="请输入员工入职日期">
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="staffEntryDate" id="staffEntryDate" disabled="disabled" 
                                  	 value="<fmt:formatDate value='${entity.staffEntryDate}' pattern='yyyy-MM-dd'/>"/> 
                                  </c:if>
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">签订合同日期</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                            	  	<input id="staffContractDate" type="text" value="<fmt:formatDate value='${entity.staffContractDate}' pattern='yyyy-MM-dd'/>" 
                            	  	 size="16" class="span6 m-ctrl-medium" name="staffContractDate" placeholder="请输入员工签订合同日期">
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="staffContractDate" id="staffContractDate" disabled="disabled" 
                                  	 value="<fmt:formatDate value='${entity.staffContractDate}' pattern='yyyy-MM-dd'/>"/> 
                                  </c:if>
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">入职状态</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW'}">
                            	  	 <select class="span6 chzn-select" name="staffState" id="staffState"
											 tabindex="5">
								            <c:forEach items="${staffMap}" var="map">  
								                <option value="${map.key}" 
								                <c:if test="${entity.staffState==map.key}"> 
								                selected="selected"
								                </c:if>
								                >  
								                ${map.value}</option>  
								            </c:forEach>  
									</select>
                                  </c:if>
                                  <c:if test="${type=='VIEW'}">
                                  	 <input type="text" class="span6" name="staffState" id="staffState" disabled="disabled" value="${staffMap[entity.staffState]}"/> 
                                  </c:if>
                            </div>
                         </div>
                         <c:if test="${pageStatus}">
                         <div class="control-group">
							<label class="control-label">离职时间</label>
                            <div class="controls">
                                 <input type="text" class="span6" name="leaveDate" id="leaveDate"
                                  	disabled="disabled" 
                                 value="${entity.leaveDate}"/> 
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">离职原因</label>
                            <div class="controls">
                                 <input type="text" class="span6" name="leaveRemark" id="leaveRemark"
                                  	disabled="disabled" 
                                 value="${entity.leaveRemark}"/> 
                            </div>
                         </div>
                         </c:if>
                        <c:if test="${type!='VIEW'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success" onclick="javascript:save('${entity.staffId}');">保存</button>
								<button type="reset" class="btn" >取消</button>
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
<script src="${ctx}/js/idcard-validate.js"></script>
<script src="${ctx}/custom/member/staffInput.js"></script>
<script>
	$(function(){
		if('${type}'!='VIEW'){
			initNotView();
		}
	});
</script>