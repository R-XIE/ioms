<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="zh" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="zh"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>404</title>
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
   <link href="${ctx}/assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
   <link href="${ctx}/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
   <link href="${ctx}/css/style.css" rel="stylesheet" />
   <link href="${ctx}/css/style-responsive.css" rel="stylesheet" />
   <link href="${ctx}/css/style-default.css" rel="stylesheet" id="style_color" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="error-404">
    <div class="error-wrap error-wrap-404">
        <div class="metro big terques">
           <span> OOPS! </span>
        </div>
        <div class="metro green">
            <span> 4 </span>
        </div>
        <div class="metro yellow">
            <span> 0 </span>
        </div>
        <div class="metro purple">
            <span> 4 </span>
        </div>
        <div class="metro double red">
            <span class="page-txt"> Page Not Found </span>
        </div>
        <div class="metro gray">
            <a href="${ctx}/index.jsp" class="home"><i class="icon-home"></i> </a>
        </div>

    </div>
</body>
<!-- END BODY -->
</html>