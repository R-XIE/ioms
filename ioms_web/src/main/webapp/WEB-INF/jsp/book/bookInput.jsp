<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/chosen-bootstrap/chosen/chosen.css" />
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">图书信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span
					class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_book();">图书信息管理</a><span
					class="divider">/</span></li>
				<li class="active"><c:if test="${type=='ADD'}">图书信息新增</c:if> <c:if
						test="${type=='VIEW' || type=='SV'}">查看图书信息</c:if> <c:if
						test="${type=='EDIT'}">编辑图书信息</c:if></li>
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
						<c:if test="${type=='ADD'}">图书信息新增</c:if>
						<c:if test="${type=='VIEW'}">查看图书信息</c:if>
						<c:if test="${type=='EDIT'}">编辑图书信息</c:if>
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down"></a>
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform"
						id="input_book_form">
						<div class="control-group">
							<label class="control-label">图书编号</label>
							<div class="controls">
								<input type="text" class="span6" name="bookCode"
									id="bookCode" placeholder="请输入图书编号"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.bookCode}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">图书类型</label>
							<div class="controls">
								<c:if test="${type!='VIEW'}">
									<select class="span6 chzn-select" name="bookType"
										data-placeholder="请选择图书类型" data-validate="bookType"
										id="bookType" multiple tabindex="4">
										<c:forEach items="${bookTypeMap}" var="map">
											<option value="${map.key}">${map.value}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${type=='VIEW'}">
									<input type="text" class="span6" name="bookType"
										id="bookType" disabled="disabled"
										value="${entity.bookTypeLable}" />
								</c:if>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">图书名称</label>
							<div class="controls">
								<input type="text" class="span6" name="bookName"
									id="bookName" placeholder="请输入图书名称"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.bookName}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">图书价格</label>
							<div class="controls">
								<input type="text" class="span6" name="bookPrice"
									id="bookPrice" placeholder="请输入图书价格"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.bookPrice}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">图书入库日期</label>
							<div class="controls">
								<c:if test="${type!='VIEW'}">
									<input id="bookInputDate" class="span6" type="text"
										value="<fmt:formatDate value='${entity.bookInputDate}' pattern='yyyy-MM-dd'/>"
										placeholder="请输入图书入库日期" size="16" class="span6" name="bookInputDate">
								</c:if>
								<c:if test="${type=='VIEW'}">
									<input type="text" class="span6" name="bookInputDate"
										id="bookInputDate" disabled="disabled"
										value="<fmt:formatDate value='${entity.bookInputDate}' pattern='yyyy-MM-dd'/>" />
								</c:if>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">图书描述</label>
							<div class="controls">
								<input type="text" class="span6" name="bookDesc"
									id="bookDesc" placeholder="请输入图书描述"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.bookDesc}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">图书状态</label>
							<div class="controls">
								<c:if test="${type!='VIEW' && entity.bookState!=-1}">
									<select class="span6 chzn-select" name="bookState"
										id="bookState" tabindex="1">
										<c:forEach items="${bookUpdateMap}" var="map">
											<option value="${map.key}"
												<c:if test="${entity.bookState==map.key}"> 
								                selected="selected"
								                </c:if>>
												${map.value}</option>
										</c:forEach>
									</select>
								</c:if>								
								<c:if test="${type=='VIEW' || entity.bookState==-1}">
									<input type="text" class="span6" name="bookStateLable"
										id="bookStateLable" disabled="disabled"
										value="${bookMap[entity.bookState]}" />
									<input type="hidden" name="bookState"
										id="bookState" value="${entity.bookState}" />
								</c:if>
							</div>
						</div>
						<c:if test="${type!='VIEW'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success"
									onclick="javascript:save('${entity.bookId}');">
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
<script type="text/javascript"
	src="${ctx}/assets/chosen-bootstrap/chosen/chosen.jquery.js"></script>
	<script type="text/javascript" src="${ctx}/assets/jquery.inputmask/jquery.inputmask.bundle.min.js"></script>
<!-- 
<script type="text/javascript"
	src="${ctx}/assets/bootstrap-datepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script> -->
<script src="${ctx}/custom/book/bookInput.js"></script>
<script>
	$(function() {
		if ('${type}' != 'VIEW') {
			initNotView('${entity.bookState}');
			chose_mult_set_ini('${entity.bookType}', '${type}');
		}
	});
</script>
