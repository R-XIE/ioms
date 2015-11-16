<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/uniform/css/uniform.default.css" />
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
				<li><a href="#">我的主页</a></li>
				<li class="pull-right search-wrap">
					<%@include	file="/common/search.jspf"%>
				</li>
			</ul>
			<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
	</div>
	<div class="row-fluid">
		<%@include file="/common/common_module.jspf"%>
	</div>
	<div class="row-fluid" id="main-pwd">
	</div> 
</div>
<!-- -->
<script type="text/javascript" src="${ctx}/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/assets/data-tables/DT_bootstrap.js"></script>
<script src="${ctx}/js/common-content-scripts.js"></script>
<script src="${ctx}/js/head.js"></script>
<script type="text/javascript" src="${ctx}/assets/uniform/jquery.uniform.min.js"></script>
<script src="${ctx}/js/refresh.js"></script>
<script type="text/javascript">
var Script = function() {
	blockPost('${ctx}/welcome/pwdList.htm','',"main-pwd");
}();
</script>