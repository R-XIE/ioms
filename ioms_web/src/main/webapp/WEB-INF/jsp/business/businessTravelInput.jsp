<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/chosen-bootstrap/chosen/chosen.css" />
<link rel="stylesheet" type="text/css" media="all" href="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-bs2.css" />
<c:if test="${!empty s  }">
	<c:set var="info" value="个人"></c:set>
</c:if>
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN THEME CUSTOMIZER-->
			<%@include file="/common/theme.jspf"%>
			<!-- END THEME CUSTOMIZER-->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">${info}出差信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_travel();">${info}出差信息管理</a> <span class="divider">/</span></li>
				<li class="active"><c:if test="${type=='ADD'}">${info}出差信息注册</c:if>
						<c:if test="${type=='VIEW'}">查看${info}出差信息</c:if>
						<c:if test="${type=='EDIT'}">编辑${info}出差信息</c:if></li>
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
			<div class="widget green">
				<div class="widget-title">
					<h4>
						<i class="icon-reorder"></i>
						<c:if test="${type=='ADD'}">${info}面试信息注册</c:if>
						<c:if test="${type=='VIEW'}">查看${info}出差信息</c:if>
						<c:if test="${type=='EDIT'}">编辑${info}出差信息</c:if>
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down" ></a> 
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform" id="input_travel_form">
						<div class="control-group">
							<label class="control-label">出差人员</label>
							<div class="controls">
								<c:if test="${type!='VIEW'}">
									<select class="span6 chzn-select" name="travelStaff"
										id="travelStaff" tabindex="1" >
										<c:forEach items="${staffMap}" var="map">
											<option value="${map.key}"
												<c:if test="${entity.travelStaff==map.key}"> 
								                selected="selected"
								                </c:if>>
												${map.value}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${type=='VIEW'}">
									<input type="text" class="span6" name="travelStaff"
										id="travelStaff" disabled="disabled"
										value="${staffMap[entity.travelStaff]}" />
								</c:if>
							</div>
						</div>
						<c:if test="${type!='VIEW'}">
						<div class="control-group">
							<label class="control-label">出差起止时间</label>
			                    <div class="controls">
			                     <div class="input-prepend input-group">
			                       <span class="add-on input-group-addon"><i class="glyphicon icon-calendar fa fa-calendar"></i></span>
			                       <input type="text" style="width: 400px" readonly="readonly" name="travelTime" id="travelTime" class="form-control"   class="span6 datestartend"
			                        <c:if test="${type=='EDIT'}">
			                        value='<fmt:formatDate value="${entity.travelStartTime}" pattern="yyyy-MM-dd HH:mm"/> - <fmt:formatDate value="${entity.travelEndTime}" pattern="yyyy-MM-dd HH:mm"/>'
									</c:if>
			                       />
			                     </div>
			                    </div>
						</div> 
						</c:if>
						<c:if test="${type=='VIEW'}">
						<div class="control-group">
						<label class="control-label">出差起止时间</label>
							<div class="controls">
								<input type="text" class="span6"     disabled="disabled" 
									value="<fmt:formatDate value="${entity.travelStartTime}" pattern="yyyy-MM-dd HH:mm"/> - <fmt:formatDate value="${entity.travelEndTime}" pattern="yyyy-MM-dd HH:mm"/>" />
							</div>
						</div>
						</c:if>
						
                         
                         <div class="control-group">
							<label class="control-label">出差地址</label>
                            <div class="controls">
                            	  <input type="text" class="span6" name="travelAddress" id="travelAddress" placeholder="请输入出差地址"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  value="${entity.travelAddress}"/> 
                            </div>
                         </div>
                        
                         <div class="control-group">
							<label class="control-label">出差目的</label>
                            <div class="controls">
                            	 <input type="text" class="span6" name="travelGoal" id="travelGoal"  placeholder="请输入出差目的"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  value="${entity.travelGoal}"/> 
                            </div>
                         </div>
                          <div class="control-group">
							<label class="control-label">同行人</label>
                            <div class="controls">
                            	 <input type="text" class="span6" name="travelToGether" id="travelToGether"  placeholder="请输入同行人(可空)"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  value="${entity.travelToGether}"/> 
                            </div>
                         </div>
                          <div class="control-group">
							<label class="control-label">交通工具</label>
                            <div class="controls">
                            	 <input type="text" class="span6" name="travelVehicle" id="travelVehicle"  placeholder="请输入交通工具"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  value="${entity.travelVehicle}"/> 
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">发票数目</label>
                            <div class="controls">
                            	 <input type="text" class="span6" name="travelInvoiceNum" id="travelInvoiceNum"  placeholder="请输入发票数目(可空)"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  value="${entity.travelInvoiceNum}"/> 
                            </div>
                         </div>
                          <div class="control-group">
							<label class="control-label">机票费</label>
                            <div class="controls">
                            	 <div class="input-prepend input-append">
                            	 <span class="add-on">￥</span>
                            	 <input type="text" class="span12" name="travelTicket" id="travelTicket"  placeholder="请输入机票费(可空)"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  	 value="${entity.travelTicket}"
                                  /> 
                                  </div>
                            </div>
                         </div>
                          <div class="control-group">
							<label class="control-label">火车费</label>
                            <div class="controls">
                            	 <div class="input-prepend input-append">
                            	 <span class="add-on">￥</span>
                            	 <input type="text" class="span12" name="travelFare" id="travelFare"  placeholder="请输入火车费(可空)"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  	 value="${entity.travelFare}"
                                  /> 
                                  </div>
                            </div>
                         </div>
                          <div class="control-group">
							<label class="control-label">车船费</label>
                            <div class="controls">
                            	 <div class="input-prepend input-append">
                            	 <span class="add-on">￥</span>
                            	 <input type="text" class="span12" name="travelFares" id="travelFares"  placeholder="请输入车船费(可空)"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>                                 	
                                  	 value="${entity.travelFares}"                                  	 
                                  /> 
                                  </div>
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">市内交通</label>
                            <div class="controls">
                            	  <div class="input-prepend input-append">
                            	 <span class="add-on">￥</span>
                            	 <input type="text" class="span12" name="travelTransport" id="travelTransport"  placeholder="请输入市内交通(可空)"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  	 value="${entity.travelTransport}"
                                  /> 
                                  </div>
                            </div>
                         </div>
                         <div class="control-group">
							<label class="control-label">出差补助</label>
                            <div class="controls">
                            	  <div class="input-prepend input-append">
                            	 <span class="add-on">￥</span>
                            	 <input type="text" class="span12" name="travelAllowance" id="travelAllowance"  placeholder="请输入出差补助(可空)"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  	 value="${entity.travelAllowance}"
                                  /> 
                                  </div>
                            </div>
                         </div>
                          <div class="control-group">
							<label class="control-label">住宿费</label>
                            <div class="controls">
                            	  <div class="input-prepend input-append">
                            	 <span class="add-on">￥</span>
                            	 <input type="text" class="span12" name="travelAccommodation" id="travelAccommodation"  placeholder="请输入住宿费(可空)"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  	 value="${entity.travelAccommodation}"
                                  /> 
                                  </div>
                            </div>
                         </div>
                          <div class="control-group">
							<label class="control-label">其他费用</label>
                            <div class="controls">
                            	  <div class="input-prepend input-append">
                            	 <span class="add-on">￥</span>
                            	 <input type="text" class="span12" name="travelOther" id="travelOther"  placeholder="请输入其他费用(可空)"
                                  	 <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  	 value="${entity.travelOther}"
                                  /> 
                                  </div>
                            </div>
                         </div>
                         <c:if test="${type=='VIEW'}">
                         <div class="control-group">
							<label class="control-label">出差总费用</label>
                            <div class="controls">
                            	   <div class="input-prepend input-append">
                            	 <span class="add-on">￥</span>
                            	 <input type="text" class="span12"    disabled="disabled" 
                                  value="${entity.travelFaresCount}"/> 
                                  </div>
                            </div>
                         </div>
                         </c:if>
                         <div class="control-group">
                             	<label class="control-label">出差行程</label>
                                <div class="controls">
                                   <textarea class="span6 ckeditor" name="travelTrip" id="travelTrip" rows="4" style="resize: none;"
                                   <c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                   >${entity.travelTrip}</textarea>
                                </div>
                                </div>
                         
                         
                        <c:if test="${type!='VIEW'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success" onclick="javascript:save('${entity.businessTravelId}');">
								<i class="icon-save"></i>
								保存</button>
								<button type="reset" class="btn" >
								<i class="icon-remove"></i>
								取消</button>
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
<script type="text/javascript" src="${ctx}/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/moment-zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-daterangepicker-1.3.13/daterangepicker-zh-CN.js"></script>
<c:if test="${empty s}">
<script src="${ctx}/custom/business/travelInput.js"></script>
<script>
	$(function(){
		if('${type}'!='VIEW'){
			initNotView();
		}
	});
</script>
</c:if>
<c:if test="${!empty s}">
<script>
function ck_travel(){
	blockPost(ctx+'/welcome/businessTravelList.htm');
}
</script>
</c:if>
<script src="${ctx}/js/form.serialize.js"></script>