<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="iitdev" uri="/iitdev-tag" %>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/simple-form.css" />
<link rel="stylesheet" type="text/css" media="all" href="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-bs2.css" />
<style>
	.row-fluid .input_span4 {
		margin-left: 0;
	}
</style>
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">面试信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#">面试信息管理</a> <span class="divider">/</span></li>
				<li class="active">列表信息</li>
				<li class="pull-right search-wrap">
					<%@include file="/common/search.jspf" %>
				</li>
			</ul>
			<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
	</div>
	<!-- END PAGE HEADER-->
	<!-- BEGIN EDITABLE TABLE widget-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN EXAMPLE TABLE widget-->

			<div class="widget purple">
				<div class="widget-title">
					<h4>
						<i class="icon-reorder"></i>面试信息列表
					</h4>
					<span class="tools"> <a href="javascript:;" id="index_refresh" data-url="${ctx}/member/interviewActionListAjax.htm"
						class="icon-refresh"></a> <a href="javascript:;"
						class="icon-chevron-down"></a> 
					</span>
				</div>
				<div class="widget-body">
					<div>
						<div class="row-fluid">
                            <div class=" clearfix btn_clear">
                                <div class=" btn-group">
                                    <button class="btn btn-info" onclick="javascript:doAction('search')">
                                    <i class="icon-search icon-white"></i> 搜索
                                    </button>
                                </div>
                                <div class=" btn-group">
                                    <button class="btn btn-danger" onclick="javascript:doAction('reset')">
                                    <i class="icon-refresh icon-white"></i> 清空
                                    </button>
                                </div>
                                <div class=" btn-group">
                                    <button class="btn btn-warning" onclick="javascript:doAction('add')">
                                    <i class="icon-plus icon-white"></i> 新增
                                    </button>
                                </div>
                            </div>
                        </div>
						<div class="row-fluid">
	                       <!--  <div class="span2">
	                            <select class="search_select">
	                                <option>100</option>
	                            </select><span class="search_line">行</span>
	                        </div> -->
	                        <form id="queryForm" method="post" action="${ctx}/member/interviewActionListAjax.htm">
	                            <div class="span4 input_span4" >
	                                <div class="input-prepend">
	                                    <span  class="btn">显示行数</span>
	                                    <!-- <input type="text" class="span2 search-query search_content"> -->
	                                    <select class="span2 search-query search_content" name="s">
	                                    	<c:forEach items="${pageMap}" var="type">
	                                    		<option value="${type.key}" 
	                                    		<c:if test="${type.key==query.s}">selected="selected"</c:if>
	                                    		>${type.value}</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>
	                            </div>
	                            <div class="span4 input_span4">
	                                <div class="input-prepend">
	                                    <span class="btn">应聘者姓名</span>
	                                    <input type="text" name="interviewName" value="${query.interviewName}"  class="span2 search-query search_content">
	                                </div>
	                            </div>
	                            
	                            <div class="span4 input_span4" >
	                                <div class="input-prepend">
	                                    <span  class="btn">性别</span>
	                                    <!-- <input type="text" class="span2 search-query search_content"> -->
	                                    <select class="span2 search-query search_content" name="interviewSex">
	                                    	<option value="">请选择</option>
	                                    	<c:forEach items="${sexMap}" var="type">
	                                    		<option value="${type.key}" 
	                                    		<c:if test="${type.key==query.interviewSex}">selected="selected"</c:if>
	                                    		>${type.value}</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>
	                            </div>
	                            
	                            <div class="span4 input_span4" >
	                                <div class="input-prepend">
	                                    <span  class="btn">面试岗位</span>
	                                    <!-- <input type="text" class="span2 search-query search_content"> -->
	                                    <select class="span2 search-query search_content" name="interviewPosition">
	                                    	<option value="">请选择</option>
	                                    	<c:forEach items="${positionMap}" var="type">
	                                    		<option value="${type.key}" 
	                                    		<c:if test="${type.key==query.interviewPosition}">selected="selected"</c:if>
	                                    		>${type.value}</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>
	                            </div>
	                            
	                            <div class="span4 input_span4" >
	                                <div class="input-prepend">
	                                    <span  class="btn">当前状况</span>
	                                    <!-- <input type="text" class="span2 search-query search_content"> -->
	                                    <select class="span2 search-query search_content" name="interviewCurrent">
	                                    	<option value="">请选择</option>
	                                    	<c:forEach items="${currentMap}" var="type">
	                                    		<option value="${type.key}" 
	                                    		<c:if test="${type.key==query.interviewCurrent}">selected="selected"</c:if>
	                                    		>${type.value}</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>
	                            </div>
	                            
	                            <div class="span4 input_span4">
	                                <div class="input-prepend">
	                                    <span class="btn">联系电话</span>
	                                    <input type="text" name="interviewPhone" value="${query.interviewPhone}"  class="span2 search-query search_content">
	                                </div>
	                            </div>
	                            
	                            <div class="span4 input_span4">
	                                <div class="input-prepend">
	                                    <span class="btn">面试评分</span>
	                                    <input type="text" name="interviewScore" value="${query.interviewScore}"  class="span2 search-query search_content">
	                                </div>
	                            </div>
	                            
	                            <div class="span4 input_span4" >
	                                <div class="input-prepend">
	                                    <span  class="btn">面试结果</span>
	                                    <!-- <input type="text" class="span2 search-query search_content"> -->
	                                    <select class="span2 search-query search_content" name="interviewResult">
	                                    	<option value="">请选择</option>
	                                    	<c:forEach items="${resultMap}" var="type">
	                                    		<option value="${type.key}" 
	                                    		<c:if test="${type.key==query.interviewResult}">selected="selected"</c:if>
	                                    		>${type.value}</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>
	                            </div>
	                            
	                            <div class="span4 input_span4">
	                                <div class="input-prepend control-group">
					                    <div class="controls">
					                     <div class="input-prepend input-group">
					                       <span class="btn">面试起始时间<!-- <i class="glyphicon icon-calendar fa fa-calendar"></i> --></span>
					                       <input type="text" style="width: 95%" readonly="readonly" name="interviewDateBE" id="interviewDateBE" class="form-control"   class="span6"
					                       	value="${query.interviewDateBE}"/>
					                     </div>
					                    </div>
	                                </div>
	                            </div>
	                        </form>
	                    </div>
						<table class="table table-striped table-hover table-bordered"
							id="editable-sample">
							<thead>
								<tr>
									<th>序号</th>
									<th>应聘者姓名</th>
									<th>性别</th>
									<th>面试岗位</th>
									<th>面试时间</th>
									<th>当前状况</th>
									<th>联系电话</th>
									<th>期望薪资</th>
									<th>面试评分</th>
									<th>面试结果</th>
									<th>编辑</th>
									<th>删除</th>		
								</tr>
							</thead>
							<tbody>
								<c:if test="${page.list==null ||  fn:length(page.list) == 0}">
									<tr>
										<td colspan="12">没有您要搜索的内容</td>
									</tr>
								</c:if>
								<c:forEach items="${page.list}" var="interview" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td><a style="cursor: pointer;" onclick="javscript:doAction('view','${interview.interviewId}')">${interview.interviewName}</a></td>
									<td>${sexMap[interview.interviewSex]}</td>
									<td>${positionMap[interview.interviewPosition]}</td>
									<td><fmt:formatDate value='${interview.interviewDate}' pattern='yyyy-MM-dd HH:mm'/></td>
									<td>${currentMap[interview.interviewCurrent]}</td>
									<td>${interview.interviewPhone}</td>
									<td>${interview.interviewSalary}</td>
									<td>${interview.interviewScore}</td>
									<td>${resultMap[interview.interviewResult]}</td>
									<td>
										<button class="btn btn-small btn-primary" onclick="javascript:doAction('edit','${interview.interviewId}')" >
											<i class="icon-pencil icon-white"></i> 编辑
										</button>
									</td>
									<td>
										<button class="btn btn-small btn-danger" onclick="javascript:doAction('del','${interview.interviewId}')">
											<i class="icon-remove icon-white"></i> 删除
										</button>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						<iitdev:page result="${page}"/>
					</div>
				</div>
			</div>
			<!-- END EXAMPLE TABLE widget-->
		</div>
	</div>
	<!-- END EDITABLE TABLE widget-->
</div>
<script src="${ctx}/js/common-content-scripts.js"></script>
<script src="${ctx}/js/head.js"></script>
<script type="text/javascript" src="${ctx}/assets/uniform/jquery.uniform.min.js"></script>
<script src="${ctx}/js/refresh.js"></script>
<script src="${ctx}/js/bootstrap-paginator.js"></script>
<script src="${ctx}/js/utils.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/moment-zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-zh-CN.js"></script>
<script src="${ctx}/custom/member/interviewListAjax.js"></script>