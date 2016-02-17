<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jspf" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="zh" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="zh"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>${title}</title>
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
    <%@include file="/common/path.jspf" %>
   <meta content="" name="author" />
   <%@include file="/common/csslibs.jspf" %>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top loading-page">
   <!-- BEGIN HEADER -->
   <%@include file="/common/head.jspf" %>
   <input type="hidden" id="hid_flag" value="1"/>
   <!-- END HEADER -->
   <!-- BEGIN CONTAINER -->
   <div id="container" class="row-fluid">
      <!-- BEGIN SIDEBAR -->
      <div class="sidebar-scroll">
          <div id="sidebar" class="nav-collapse collapse">
              <!-- BEGIN RESPONSIVE QUICK SEARCH FORM 待完成-->
              <div class="navbar-inverse">
                  <form class="navbar-search visible-phone">
                      <input type="text" class="search-query" placeholder="请筛选资源" />
                  </form>
              </div>
              <!-- END RESPONSIVE QUICK SEARCH FORM -->
              <!-- BEGIN SIDEBAR MENU -->
              <%@include file="/common/menu.jspf" %>
              <!-- END SIDEBAR MENU -->
          </div>
      </div>
      <!-- END SIDEBAR -->
      <!-- BEGIN PAGE -->  
      <div id="main-content">
   	  </div>
   </div>
   <!-- END CONTAINER -->
 	
   <!-- BEGIN FOOTER -->
   <div id="footer">
   
       2013&copy;cuisongliu个人开发
   </div>
   <!-- END FOOTER -->
   <div id="hiddenDialog"></div>
   <!-- BEGIN JAVASCRIPTS -->
   <!-- Load javascripts at bottom, this will reduce page load time -->
   
   
	<%@include file="/common/jslibs.jspf" %>
	<script type="text/javascript" src="${ctx}/assets/gritter/js/jquery.gritter.js"></script>
   <!-- END JAVASCRIPTS -->
   <script src="${ctx}/custom/index.js"></script>
   <script src="${ctx}/js/common-scripts.js"></script>
</body>
<!-- END BODY -->
</html>
