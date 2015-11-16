<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="utils" uri="/utils" %>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/gritter/css/jquery.gritter.css" />
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">${pwdVO.pwdName}设置</h3>
			<input type="hidden" id="hid_pwdId" value="${pwdVO.pwdId}"/>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_pwd();">敏感信息管理</a> <span class="divider">/</span></li>
				<li class="active">敏感信息设置</li>
				<li class="pull-right search-wrap">
					<%@include file="/common/search.jspf" %>
				</li>
			</ul>
			<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
	</div>
	<!-- END PAGE HEADER-->
	<!-- BEGIN EDITABLE TABLE widget-->
		
		
		
		<div class="container-fluid">           
        	<c:forEach items="${branchList}" var="branch" varStatus="status"> 
        	<c:set var="staff" value="${utils:getStaffByBranch(branch.branchId)}"></c:set>
        	<c:if test="${fn:length(staff)!=0}">
        	<c:if test="${status.count%3==1 }">
        		<div class="row-fluid">   
        	</c:if>   	
            <div class="span4">           
            	<c:if test="${status.count%6==1 }">
                	<div class="widget red">
                </c:if>
                <c:if test="${status.count%6==2 }">
                	<div class="widget yellow">
                </c:if>
                <c:if test="${status.count%6==3 }">
                	<div class="widget purple">
                </c:if>
                <c:if test="${status.count%6==4 }">
                	<div class="widget blue">
                </c:if>
                <c:if test="${status.count%6==5 }">
                	<div class="widget orange">
                </c:if>
                <c:if test="${status.count%6==0 }">
            		<div class="widget green">
            	</c:if>
                    <div class="widget-title " style="cursor: pointer;">
                        <h4><i class="icon-edit"></i>${branch.branchName}</h4>
                         <span class="tools">
                            <a class="icon-chevron-down" href="javascript:;"></a>
                         </span>
                    </div>
                    <div class="widget-body">
                        <div>
                           <div class="widget-add">                        	
                        <!-- BEGIN FORM-->
                        <form action="#" class="form-horizontal">                            
                            <div class="control-group">
                            	<c:forEach items="${staff}" var="staff">                             
                                <div class="controls">
                                    <label class="checkbox">
                                        <input type="checkbox" name="staffId" value="${staff.staffId}" 
                                        <c:if test="${utils:getPwdStaffAuth(pwdVO.pwdId,staff.staffId)==true}">
                                        checked="true"
                                        </c:if>
                                        
                                        /><span id="span_${staff.staffId}">${staff.staffRealName}</span>
                                    </label>
                                </div>
                                </c:forEach>   
                                
                            </div>                                
                        </form>
                        <!-- END FORM-->
                        </div>
                        </div>
                    </div>
                </div>          
                        		
            </div> 
            <c:if test="${status.count%3==0 ||status.last}">
            </div>
            </c:if> 
            </c:if>         
            </c:forEach>
        </div>       

	<!-- END EDITABLE TABLE widget-->
</div>
<script type="text/javascript"
	src="${ctx}/assets/data-tables/DT_bootstrap.js"></script>
<script src="${ctx}/js/common-content-scripts.js"></script>
<script src="${ctx}/js/head.js"></script>
<script type="text/javascript" src="${ctx}/assets/uniform/jquery.uniform.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/gritter/js/jquery.gritter.js"></script>
<script src="${ctx}/js/draggable-portlet.js"></script>
<script src="${ctx}/custom/operation/pwdAuth.js"></script>  