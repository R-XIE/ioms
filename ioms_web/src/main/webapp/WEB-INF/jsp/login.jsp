<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="zh" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<title>IOMS</title>
<link rel="icon" href="${ctx}/img/icon/favicon/favicon.png" type="image/x-icon" /> 
<link rel="shortcut icon" href="${ctx}/img/icon/favicon/favicon.png" type="image/x-icon"/>
<link href="${ctx}/assets/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="${ctx}/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<link href="${ctx}/css/style.css" rel="stylesheet" />
<link href="${ctx}/css/style-default.css" rel="stylesheet" />
<link href="${ctx}/css/style-responsive.css" rel="stylesheet" />
<script src="${ctx}/js/jquery-1.8.3.min.js"></script>
<!--[if IE]>
    <script src="${ctx}/js/html5.js"></script>
	<![endif]-->
<!-- Load javascripts at bottom, this will reduce page load time -->
<script src="${ctx}/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="${ctx}/assets/jquery-ui/jquery-ui-1.10.1.custom.min.js"
	type="text/javascript"></script>
<script src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<!--script for this page only-->
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<!-- ie8 fixes -->
<!--[if lt IE 9]>
	<script src="${ctx}/js/excanvas.js"></script>
	<script src="${ctx}/js/respond.js"></script>
	<![endif]-->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="lock">
	<div class="lock-header">
		<!-- BEGIN LOGO -->
		<a class="center" id="logo"> <img class="center"
			alt="logo" src="img/logo/logo2.png">
		</a>
		<!-- END LOGO -->
	</div>
	<div class="login-wrap">
		<div class="metro single-size red">
			<div class="locked">
				<i class="icon-lock"></i> <span>Login</span>
			</div>
		</div>
			<div class="metro double-size green">
				<div class="input-append lock-input">
					<input type="text" class="" name="name" id="uname_input" 
					placeholder="请输入正确的用户名">
				</div>
			</div>

			<div class="metro double-size yellow">

				<div class="input-append lock-input">
					<input type="password" class="" name="pwd" id="pwd_input"
						placeholder="请输入正确的密码">
				</div>
			</div>
			<div class="metro single-size terques login">
				<div id="btn_submit"  class="btn login-btn">
					<span id="sub_login">登录</span> <i class=" icon-long-arrow-right"></i>
				</div>

			</div>
		<div class="metro double-size navy-blue ">
			<a href="http://www.iitdev.com/" target="_blank" class="social-link"> <i
				class="icon-facebook-sign"></i> <span>爱迪尔</span>
			</a>
		</div>
		<div class="metro single-size deep-red">
			<a href="http://www.hometeda.com/" target="_blank" class="social-link"> <i
				class="icon-google-plus-sign"></i> <span>泰达家园</span>
			</a>
		</div>
		<div class="metro double-size blue">
			<a href="http://bbs.hometeda.com/" target="_blank"  class="social-link"> <i
				class="icon-twitter-sign"></i> <span>泰达家园论坛</span>
			</a>
		</div>
		<div class="metro single-size purple">
			<a href="http://biuapp.net/"  target="_blank" class="social-link"> <i 
			class="icon-skype"></i>	<span>BIU版官方网站</span>
			</a>
		</div>
	</div>
	<div id="hiddenDialog"></div>
</body>
<!-- END BODY -->
<script>
var ctx='/';
</script>
<script src="${ctx}/custom/login.js"></script>
</html>