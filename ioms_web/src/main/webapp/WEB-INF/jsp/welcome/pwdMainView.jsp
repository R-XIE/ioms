<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- BEGIN PAGE CONTENT-->
<div class="span6">
			<div class="widget red">
				<div class="widget-title">
					<h4>
						<i class="icon-key"></i>个人敏感信息管理
					</h4>
					<span class="tools"> 
						<a href="javascript:;" id="pwdRefresh"  class="icon-refresh"></a>
						<a href="javascript:;"	class="icon-chevron-down"></a>
					</span>
				</div>
				<div class="widget-body">
					<div class="row-fluid">
						<div class="span11">
							<h4>用户敏感信息和公开信息管理</h4>
							<div id="accordion" class="accordion in collapse"
								style="height: auto;">
								<c:forEach items="${list}" var="pwd" varStatus="status">
									<div class="accordion-group">
										<div class="accordion-heading">
											<a href="#collapse${status.count}" data-parent="#accordion"
												data-toggle="collapse" class="accordion-toggle">
												[${pwdMap[pwd.pwdLevel]}]${pwd.pwdName} </a>
										</div>
										<div 
											<c:if test="${status.count==1}">
											class="accordion-body collapse in"
											</c:if>
											<c:if test="${status.count!=1}">
											class="accordion-body collapse"
											</c:if>
											id="collapse${status.count}">
											<div class="accordion-inner">${pwd.pwdRemark}</div>
											<div style="text-align: right; padding-right: 30px;">${pwd.pwdLocation}</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END BLANK PAGE PORTLET-->
		</div>
<div class="span6"></div>
<!-- END PAGE CONTENT-->
<script src="${ctx}/assets/data-tables/DT_bootstrap.js"></script>
<script src="${ctx}/js/common-content-scripts.js"></script>
<script src="${ctx}/js/head.js"></script>
<script type="text/javascript" src="${ctx}/assets/uniform/jquery.uniform.min.js"></script>
<script type="text/javascript">
var Script = function() {
	$('#pwdRefresh').click(function(){
		blockPost('${ctx}/welcome/pwdList.htm','',"main-pwd");
	});
}();
</script>