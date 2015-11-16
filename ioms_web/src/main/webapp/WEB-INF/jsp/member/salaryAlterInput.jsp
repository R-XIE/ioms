<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/chosen-bootstrap/chosen/chosen.css" />
<c:if test="${!empty type && type=='SV'  }">
	<c:set var="sv" value="个人"></c:set>
</c:if>
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">${sv}薪资异动信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span
					class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_alter();">${sv}薪资异动信息管理</a><span
					class="divider">/</span></li>
				<li class="active"><c:if test="${type=='ADD'}">薪资异动信息新增</c:if> <c:if
						test="${type=='VIEW' || type=='SV'}">查看${sv}薪资异动信息</c:if> <c:if
						test="${type=='EDIT'}">编辑薪资异动信息</c:if></li>
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
						<c:if test="${type=='ADD'}">薪资异动信息新增</c:if>
						<c:if test="${type=='VIEW'|| type=='SV'}">查看${sv}薪资异动信息</c:if>
						<c:if test="${type=='EDIT'}">编辑薪资异动信息</c:if>
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down"></a>
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform"
						id="input_alter_form">
						<input id="alterId" type="hidden" value="${entity.alterId }"/>
						<div class="control-group">
							<label class="control-label">申请人</label>
							<div class="controls">
								<c:if test="${type!='VIEW' && type!='SV'}">
									<select class="span6 chzn-select" name="staffId"
										id="staffId" tabindex="1">
										<c:forEach items="${staffMap}" var="map">
											<option value="${map.key}"
												<c:if test="${entity.staffId==map.key}"> 
								                selected="selected"
								                </c:if>>
												${map.value}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${type=='VIEW' || type=='SV'}">
									<input type="text" class="span6" name="staffId"
										id="staffId" disabled="disabled"
										value="${staffMap[entity.staffId]}" />
								</c:if>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">异动原因</label>
							<div class="controls">
								<c:if test="${type!='VIEW' && type!='SV'}">
									<select class="span6 chzn-select" name="alterCause" data-placeholder="请选择异动原因" data-validate="causeType"
										id="alterCause"  multiple tabindex="4">
										<c:forEach items="${alterMap}" var="map">
												<option value="${map.key}">
												${map.value}</option>
										</c:forEach>
									</select>
									
								</c:if>
								<c:if test="${type=='VIEW' || type=='SV'}">
									<input type="text" class="span6" name="alterCause"
										id="alterCause" disabled="disabled"
										value="${entity.alterCauseLable}" />
								</c:if>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">原薪资标准</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	 <span class="add-on">￥</span>
                            	 <input type="text" class="span12" name="alterBefore" id="alterBefore"  placeholder="请输入原薪资标准"
                                  	<c:if test="${type=='VIEW' || type=='SV'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  	 value="${entity.alterBefore}"
                                  /> 
                                  </div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">异动后薪资标准</label>
							<div class="controls">
								 <div class="input-prepend input-append">
                            	 <span class="add-on">￥</span>
                            	 <input type="text" class="span12" name="alterAfter" id="alterAfter"  placeholder="请输入异动后薪资标准"
                                  	<c:if test="${type=='VIEW' || type=='SV'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  	 value="${entity.alterAfter}"
                                  /> 
                                  </div>
										
							</div>
						</div>
						 <div class="control-group">
							<label class="control-label">生效月份</label>
                            <div class="controls">
                            	  <c:if test="${type!='VIEW' &&  type!='SV'}">
                            	  	 <input id="alterDate" class="span6" type="text" value="${entity.alterDate}" 
                            	  	 placeholder="请输入生效月份" size="16" class="span6" name="alterDate" >
                                  </c:if>
                                  <c:if test="${type=='VIEW' || type=='SV'}">
                                  	 <input type="text" class="span6" name="alterDate" id="alterDate" disabled="disabled" 
                                  	 value="${entity.alterDate}"/> 
                                  </c:if>
                            </div>
                         </div>
						<c:if test="${type=='VIEW' || type=='SV'}">
						<div class="control-group">
							<label class="control-label">填写日期</label>
							<div class="controls">
								<input type="text" class="span6"  disabled="disabled"
								value="<fmt:formatDate value='${entity.alterCreateDate}' pattern='yyyy-MM-dd'/>" />
							</div>
						</div>
						</c:if>
						<c:if test="${type!='VIEW' && type!='SV'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success"
									onclick="javascript:save('${entity.alterId}');">
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
<script type="text/javascript" src="${ctx}/assets/chosen-bootstrap/chosen/chosen.jquery.js"></script>
<script type="text/javascript" src="${ctx}/assets/jquery.inputmask/jquery.inputmask.bundle.min.js"></script>
<!-- 
<script type="text/javascript"
	src="${ctx}/assets/bootstrap-datepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script> -->
<c:if test="${!empty type && type!='SV'  }">
	<script src="${ctx}/custom/member/alterInput.js"></script>
	<script>
		$(function() {
			if ('${type}' != 'VIEW') {
				initNotView();
				chose_mult_set_ini('${entity.alterCause}','${type}');
			}
		});
	</script>
</c:if>
<c:if test="${!empty type && type=='SV'  }">
	<script src="${ctx}/custom/welcome/alterInput.js"></script>
</c:if>