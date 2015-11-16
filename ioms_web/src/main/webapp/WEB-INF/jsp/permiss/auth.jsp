<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib prefix="utils" uri="/utils" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
			<h3 class="page-title">${role.roleName}角色授权</h3>
			<input type="hidden" id="hid_roleId" value="${role.roleId}"/>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_role();">角色管理</a> <span class="divider">/</span></li>
				<li class="active">角色授权</li>
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
        	<c:forEach items="${module}" var="module" varStatus="status"> 
        	<c:set var="resources" value="${utils:getResourcesByModule(module.resourcesId)}"></c:set>
        	<c:if test="${fn:length(resources)!=0}">
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
                        <h4><i class="icon-edit"></i>${module.resourcesName}</h4>
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
                            	<c:forEach items="${resources}" var="res">                             
                                <div class="controls">
                                    <label class="checkbox">
                                        <!----><input type="checkbox" name="resId" value="${res.resourcesId}" 
                                          
                                        <c:if test="${utils:getResourceRoleAuth(res.resourcesId,role.roleId)==true}">
                                        checked="true"
                                        </c:if>
                                        />
                                        
                                        <span id="span_${res.resourcesId}">${res.resourcesName}</span>
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
	src="${ctx}/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/data-tables/DT_bootstrap.js"></script>
<script src="${ctx}/js/common-content-scripts.js"></script>
<script src="${ctx}/js/head.js"></script>
<script type="text/javascript" src="${ctx}/assets/uniform/jquery.uniform.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/gritter/js/jquery.gritter.js"></script>
<script src="${ctx}/js/draggable-portlet.js"></script>
<script src="${ctx}/custom/permiss/auth.js"></script>  