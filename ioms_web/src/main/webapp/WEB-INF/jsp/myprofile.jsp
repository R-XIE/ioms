<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="iitdev" uri="/iitdev-tag" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx}/css/profile.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
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
			<h3 class="page-title">我的主页</h3>
			<ul class="breadcrumb">
				<li><a href="${ctx}/welcome/index.htm">我的主页</a> <span class="divider">/</span></li>
				<li><a href="#">个人信息</a>
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
			<!-- BEGIN tabs-->
			<div class="widget blue">
				<div class="widget-title">
					<h4>
						<i class="icon-reorder"></i> 个人信息
					</h4>
					<span class="tools"> 
						<a href="javascript:;"	id="info_refresh" data-url="${ctx}/welcome/myprofile.htm" class="icon-refresh"></a>
						<a href="javascript:;"	class="icon-chevron-down"></a>
					</span>
				</div>
				<div class="widget-body">
					<div class="bs-docs-example">
						<ul class="nav nav-tabs" id="myTab">
							<li class="active"><a data-toggle="tab" href="#home">信息面板</a></li>
							<li><a data-toggle="tab" href="#profile">编辑资料</a></li>
							<li><a data-toggle="tab" href="#headImg">修改头像</a></li>
						</ul>
						<div class="tab-content" id="myTabContent">

							<div class="tab-pane fade in active profile-classic row-fluid "
								id="home">

								<div class="span2">
									<img class="infoIcon" src='${ctx}/<iitdev:loginInfo type="staffIcon"/>' alt="">
								</div>

								<ul class="unstyled span10">

									<li><span>用户名:</span> ${staff.staffLoginName}</li>

									<li><span>真实姓名:</span> ${staff.staffRealName}</li>

									<li><span>性  别:</span>${sexMap[staff.staffSex]}</li>

									<li><span>职位:</span> ${positionMap[staff.positionId]}</li>

									<li><span>岗位:</span> ${postMap[staff.postId]}</li>
									
									<li><span>身份证号:</span> ${staff.staffIdCard}</li>
									
									<li><span>联系方式:</span>${staff.staffPhone}</li>
									
									<li><span>紧急联系方式:</span>${staff.staffEmergency}</li>

									<li><span>Email:</span> <a href="#">${staff.staffEmail}</a></li>

									<li><span>用户地址:</span>${staff.staffAddress}</li>

									<li><span>学历:</span> ${degreeMap[staff.staffDegree]}</li>
									
									<li><span>毕业学校:</span> ${staff.staffGraduation}</li>
									
									<li><span>专业:</span>${staff.staffMajor}</li>
									
									<li><span>毕业日期:</span><fmt:formatDate value="${staff.staffMajorDate}" type="date" dateStyle="long"/></li>
									
									<li><span>入职日期:</span><fmt:formatDate value="${staff.staffEntryDate}" type="date" dateStyle="long"/></li>
									
									<li><span>签定合同日期:</span><fmt:formatDate value="${staff.staffContractDate}" type="date" dateStyle="long"/></li>
									
									<li><span>注册时间:</span><fmt:formatDate value="${staff.createTime}" type="both" dateStyle="long"/></li>
								</ul>
							</div>
							
							<div class="tab-pane fade widget-body" id="profile">
                            	<form class="form-horizontal cmxform" id="profileForm" action="#">
                                <div id="tabsleft" class="tabbable tabs-left">
                                <ul>
                                    <li><a href="#tabsleft-tab1" data-toggle="tab"><span class="strong">第一步</span> <span class="muted">个人设置</span></a></li>
                                    <!-- 
                                    <li><a href="#tabsleft-tab2" data-toggle="tab"><span class="strong">第二步</span> <span class="muted">头像预览</span></a></li> -->
                                    <li><a href="#tabsleft-tab2" data-toggle="tab"><span class="strong">第二步</span> <span class="muted">更多设置</span></a></li>
                                    <li><a href="#tabsleft-tab3" data-toggle="tab"><span class="strong">第四步</span> <span class="muted">确认信息</span></a></li>
                                </ul>
                                <div class="progress progress-info progress-striped">
                                    <div class="bar"></div>
                                </div>
                                <div class="tab-content">
                                    <div class="tab-pane" id="tabsleft-tab1">
                                        <h3>填写第一步</h3>
                                        <div class="control-group">
                                            <label  class="control-label">真实姓名</label>
		                                    <div class="controls">
		                                        <input class="span6 " id="username" name="username" type="text" disabled="disabled" value="${staff.staffRealName}"/>
		                                    </div>
                                        </div>
                                        <div class="control-group">
                                            <label for="password" class="control-label">新的密码</label>
		                                    <div class="controls">
		                                        <input class="span6 " id="password" name="password" type="password" />
		                                    </div>
                                        </div>
                                        <div class="control-group">
                                            <label for="confirm_password" class="control-label">重复新的密码</label>
		                                    <div class="controls">
		                                        <input class="span6 " id="confirm_password" name="confirm_password" type="password" />
		                                    </div>
                                        </div>
                                    </div>
                                    <!-- 
                                    <div class="tab-pane" id="tabsleft-tab2">
                                        <h3>填写第二步</h3>
                                        <div class="control-group">
			                                <label class="control-label">头像预览(未上传)</label>
			                                <div class="controls">
			                                    <div data-provides="fileupload" class="fileupload fileupload-new">
			                                        <div style="width: 200px; height: 150px;" class="fileupload-new thumbnail">
			                                        	<c:if test="${staff.staffIcon==null}">
			                                            	<img alt="用户的头像" src="${ctx}/icon/no+image.gif">
			                                            </c:if>
			                                            <c:if test="${staff.staffIcon!=null}">
			                                            	<img alt="用户的头像" src="${ctx}/icon/${staff.staffIcon}">
			                                            </c:if>
			                                        </div>
			                                        <div style="max-width: 200px; max-height: 150px; line-height: 20px;" class="fileupload-preview fileupload-exists thumbnail"></div>
			                                        <div>
			                                           <span class="btn btn-file"  ><span class="fileupload-new">选择图片</span>
			                                           <span class="fileupload-exists">修改</span>
			                                           <input type="file" class="default" accept="image/*" ></span>
			                                            <a data-dismiss="fileupload" class="btn fileupload-exists" href="#">删除</a>
			                                        </div>
			                                    </div>
			                                    <span class="label label-important">注意</span>
		                                         <span>
		                                         附加的图像缩略图支持最新的火狐，Chrome，Opera，Safari和IE 10
		                                         </span>
			                                </div>
			                            </div>
                                    </div> -->
                                    <div class="tab-pane" id="tabsleft-tab2">
                                        <h3>填写第三步</h3>

                                        <div class="control-group">
                                           <label class="control-label">联系方式</label>
                                            <div class="controls">
                                                <input type="text" class="span6" name="phone" id="phone" value="${staff.staffPhone}"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                           <label class="control-label">紧急联系方式</label>
                                            <div class="controls">
                                                <input type="text" class="span6" name="emergency" id="emergency" value="${staff.staffEmergency}"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                           <label class="control-label">Email</label>
                                            <div class="controls">
                                            	<div class="input-icon left">
	                                            	<i class="icon-envelope"></i>
	                                                <input type="text" class="span6" name="email" id="email" value="${staff.staffEmail}"/>
                                            	</div>
                                            </div>
                                        </div>
                                         <div class="control-group">
                                           <label class="control-label">用户地址</label>
                                            <div class="controls">
                                                <input type="text" class="span6" name="address" id="address" value="${staff.staffAddress}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="tabsleft-tab3">
                                        <h3>最后一步</h3>
                                        <div class="control-group">
                                            <label  class="control-label">真实姓名</label>
		                                    <div class="controls">
		                                        <span class="text" id="view_username">${staff.staffRealName}</span>
		                                    </div>
                                        </div>
                                        <div class="control-group">
                                            <label  class="control-label">联系方式</label>
		                                    <div class="controls">
		                                        <span class="text" id="view_phone">${staff.staffPhone}</span>
		                                    </div>
                                        </div>
                                        <div class="control-group">
                                            <label  class="control-label">紧急联系方式</label>
		                                    <div class="controls">
		                                        <span class="text" id="view_emergency">${staff.staffEmergency}</span>
		                                    </div>
                                        </div>
                                        <div class="control-group">
                                            <label  class="control-label">Email</label>
		                                    <div class="controls">
		                                        <span class="text" id="view_email">${staff.staffEmail}</span>
		                                    </div>
                                        </div>
                                        <div class="control-group">
                                            <label  class="control-label">用户地址</label>
		                                    <div class="controls">
		                                        <span class="text" id="view_address">${staff.staffAddress}</span>
		                                    </div>
                                        </div>
                                       
                                        <div class="control-group">
                                            <label class="control-label"></label>
                                            <div class="controls">
                                                <label class="checkbox">
                                                    <input type="checkbox" value=""  id="agree" name="agree"/>确认信息后提交
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <ul class="pager wizard">
                                        <li class="previous first"><a href="javascript:;">第一步</a></li>
                                        <li class="previous"><a href="javascript:;">上一步</a></li>
                                        <li class="next last"><a href="javascript:;">最后一步</a></li>
                                        <li class="next"><a href="javascript:;">下一步</a></li>
                                        <li class="next finish" style="display:none;"><a href="javascript:ck('${staff.staffId}','${ctx}');">完成</a></li>
                                    </ul>
                                </div>
                            </div>
                            </form>
                        	</div>
							
							<div class="tab-pane fade widget-body" id="headImg">
								<form action="#" class="form-horizontal cmxform" id="input_staff_img_form">
								<span class="btn btn-info fileinput-button">
				                    <i class="glyphicon glyphicon-plus icon-upload-alt"></i>
				                    <span>上传头像</span>
				                    <input  id="fileupload" type="file" data-form-data='{"recordId": "${staff.staffId}"}' name="fileupload">
				                </span>
				                </form>
				                <form action="#"  id="input_staff_img_process_form">
									<div class="span2">
										<img class="infoIcon"  width="300" height="225" src='${ctx}/<iitdev:loginInfo type="staffIcon"/>' />
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END tabs-->
		</div>
	</div>
	<!-- END EDITABLE TABLE widget-->
</div>
<script src="${ctx}/js/common-content-scripts.js"></script>
<script src="${ctx}/js/head.js"></script>
<script src="${ctx}/assets/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/additional-methods.min.js"></script>
<script src="${ctx}/js/form-wizard.js"></script>
<script src="${ctx}/assets/jquery-file-upload/jquery.fileupload.js"></script>
<script src="${ctx}/assets/jquery-file-upload/jquery.iframe-transport.js"></script>
<script src="${ctx}/assets/jquery-file-upload/jquery.ui.widget.js"></script>
<script src="${ctx}/assets/jquery-file-upload/jquery.xdr-transport.js"></script>
<script src="${ctx}/js/refresh.js"></script>
<script src="${ctx}/custom/myprofile.js"></script>
<script>
$(function () {
    $("input[type=radio], input[type=checkbox]").uniform();
});
</script>
  