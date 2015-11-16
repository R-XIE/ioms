<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- BEGIN PAGE CONTENT-->
<div class="span11">
			<div class="widget orange">
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
					<div>
						<div class="space15" ></div>
						<table class="table table-striped table-hover table-bordered"
							id="editable-sample">
							<thead>
								<tr>
									<th>序号</th>
									<th>敏感信息标题</th>
									<th>敏感信息级别</th>
									<th>敏感信息位置</th>
									<th>敏感信息内容</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="pwd" varStatus="status">
								<tr class="">
									<td>${status.count}</td>
									<td>${pwd.pwdName}</td>
									<td>${pwdMap[pwd.pwdLevel]}</td>
									<td>${pwd.pwdLocation}</td>
									<td>${pwd.pwdRemark}</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- END BLANK PAGE PORTLET-->
		</div>
<!-- END PAGE CONTENT-->
<script src="${ctx}/assets/data-tables/DT_bootstrap.js"></script>
<script src="${ctx}/js/common-content-scripts.js"></script>
<script src="${ctx}/js/head.js"></script>
<script type="text/javascript" src="${ctx}/assets/uniform/jquery.uniform.min.js"></script>
<script type="text/javascript">
var Script = function() {
	$('#editable-sample').dataTable(maxDataTable);
	$('#pwdRefresh').click(function(){
		blockPost('${ctx}/welcome/pwdList.htm','',"main-pwd");
	});
}();
</script>