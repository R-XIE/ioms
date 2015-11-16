<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" media="all" href="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-bs2.css" />
<div class="widget green">
	<div class="widget-title">
		<h4>
			<i class="icon-reorder"></i>
			<c:if test="${type=='ADD'}">增加推送信息</c:if>
			<c:if test="${type=='EDIT'}">编辑推送信息</c:if>
		</h4>
		<span class="tools"> <a href="javascript:icon_down();"
			class="icon-chevron-down" id="input_icon_down"></a> <a
			href="javascript:icon_remove();" id="input_icon_remove"
			class="icon-remove"></a>
		</span>
	</div>
	<div class="widget-body" id="input_widget">
		<form action="#" class="form-horizontal cmxform"
			id="input_push_form">
			<div class="control-group">
				<label class="control-label">推送标题</label>
				<div class="controls">
					<input type="text" class="span6" name="title" id="title"
						<c:if test="${type=='EDIT'}">
					value="${entity.title}" 
					</c:if>
						placeholder="请输入推送标题" />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">推送内容</label>
				<div class="controls">
					<input type="text" class="span6" name="content" id="content"
						<c:if test="${type=='EDIT'}">
					value="${entity.content}" 
					</c:if>
						placeholder="请输入推送内容" />
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="reservationtime">推送起止时间</label>
                    <div class="controls">
                     <div class="input-prepend input-group">
                       <span class="add-on input-group-addon"><i class="glyphicon icon-calendar fa fa-calendar"></i></span>
                       <input type="text" style="width: 400px" readonly="readonly" name="reservation" id="reservationtime" class="form-control"   class="span6"
                        <c:if test="${type=='EDIT'}">
                        value='<fmt:formatDate value="${entity.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/> - <fmt:formatDate value="${entity.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
						</c:if>
                       />
                       <label class="help-inline" >上午从0点到11:59,下午从12:00到23:59</label>
                     </div>
                    </div>
				
			</div> 

			<div class="form-actions">
				<button type="button" class="btn btn-success"
					onclick="javascript:save('${entity.id}');">保存</button>
				<button type="button" class="btn"
					onclick="javascript:icon_remove();">取消</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/moment-zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-zh-CN.js"></script>
<script src="${ctx}/custom/biu/pushInput.js"></script>