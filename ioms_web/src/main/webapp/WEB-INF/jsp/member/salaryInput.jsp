<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/chosen-bootstrap/chosen/chosen.css" />
<c:if test="${!empty s}">
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
			<h3 class="page-title">${info}薪资信息管理</h3>
			<ul class="breadcrumb">
				<li><a href="#" onclick="javascript:ck_index();">主页</a> <span
					class="divider">/</span></li>
				<li><a href="#" onclick="javascript:ck_salary();">${info}薪资信息管理</a><span
					class="divider">/</span></li>
				<li class="active"><c:if test="${type=='ADD'}">薪资信息新增</c:if> <c:if
						test="${type=='VIEW'}">查看${info}薪资信息</c:if> <c:if
						test="${type=='EDIT'}">编辑${info}薪资信息</c:if></li>
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
						<c:if test="${type=='ADD'}">薪资信息新增</c:if>
						<c:if test="${type=='VIEW'}">查看${info}薪资信息</c:if>
						<c:if test="${type=='EDIT'}">编辑薪资信息</c:if>
					</h4>
					<span class="tools"> <a href="javascript:icon_down();"
						class="icon-chevron-down" id="input_icon_down"></a>
					</span>
				</div>
				<div class="widget-body">
					<form action="#" class="form-horizontal cmxform"
						id="input_salary_form">
						<input type="hidden" name="salaryId" id="salaryId" value="${entity.salaryId}"/>
						<div class="control-group">
							<label class="control-label">姓名</label>
							<div class="controls">
								<c:if test="${type!='VIEW'}">
									<select class="span5 chzn-select" name="salaryStaff" data-validate="salaryStaff"
										id="salaryStaff" tabindex="1">
										<c:forEach items="${staffMap}" var="map">
											<option value="${map.key}"
												<c:if test="${entity.salaryStaff==map.key}"> 
								                selected="selected"
								                </c:if>>
												${map.value}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${type=='VIEW' || type=='SV'}">
									<input type="text" class="span5" name="salaryStaff"
										id="salaryStaff" disabled="disabled"
										value="${entity.salaryStaffName}" />
								</c:if>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">月份</label>
							<div class="controls">
								<input type="text" class="span5" name="salaryDateMonths"
									id="salaryDateMonths"  disabled="disabled" 
								/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">应出勤</label>
							<div class="controls">
								<input type="text" class="span5" name="salaryAttendance"
									id="salaryAttendance" 
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									 <c:if test="${type=='ADD'}">
										value="${attendance}"
									 </c:if>
									 <c:if test="${type!='ADD'}">
										value="${entity.salaryAttendance}"
									 </c:if>
									 />
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">加班天数</label>
							<div class="controls">
								<input type="text" class="span5" name="salaryOvertime"
									id="salaryOvertime" 
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									 <c:if test="${type=='ADD'}">
										value="0.0"
									 </c:if>
									 <c:if test="${type!='ADD'}">
										value="${entity.salaryOvertime}"
									 </c:if>
									 />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">实出勤</label>
							<div class="controls">
								<input type="text" class="span5" name="salaryFactAttendance"
									id="salaryFactAttendance" placeholder="请输入实出勤"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									<c:if test="${type!='ADD'}">
										value="${entity.salaryFactAttendance}"
									</c:if>
									<c:if test="${type=='ADD'}">
										value="${factAttendance}"
									</c:if>
									 />
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">基本工资</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	<span class="add-on">￥</span>
								<input type="text" class="span12" name="salaryBase"
									id="salaryBase" placeholder="请输入基本工资"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  	 <c:if test="${type=='ADD'}">
                                  	 	value="${last.salaryBase}" 
                                  	 </c:if>
                                  	 <c:if test="${type!='ADD'}">
                                  	 	value="${entity.salaryBase}" 
                                  	 </c:if>
									/>
								</div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">岗位工资</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	<span class="add-on">￥</span>
								<input type="text" class="span12" name="salaryPosition"
									id="salaryPosition" placeholder="请输入岗位工资"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  	 <c:if test="${type=='ADD'}">
                                  	 	value="${last.salaryPosition}" 
                                  	 </c:if>
                                  	 <c:if test="${type!='ADD'}">
                                  	 	value="${entity.salaryPosition}"
                                  	 </c:if>
									 />
								</div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">绩效工资</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	<span class="add-on">￥</span>
								<input type="text" class="span12" name="salaryPerformance"
									id="salaryPerformance" placeholder="请输入绩效工资"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  	 <c:if test="${type=='ADD'}">
                                  	 	value="${last.salaryPerformance}" 
                                  	 </c:if>
                                  	 <c:if test="${type!='ADD'}">
                                  	 	value="${entity.salaryPerformance}" 
                                  	 </c:if>
									/>
									</div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">餐补</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	<span class="add-on">￥</span>
								<input type="text" class="span12" name="salaryAdd"
									id="salaryAdd" placeholder="请输入其他奖励"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  	  <c:if test="${type=='ADD'}">
                                  	 	value="${last.salaryAdd}" 
                                  	 </c:if>
                                  	 <c:if test="${type!='ADD'}">
                                  	 	value="${entity.salaryAdd}"
                                  	 </c:if>
									 />
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">补发工资</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	<span class="add-on">￥</span>
								<input type="text" class="span12" name="salarySupply"
									id="salarySupply" placeholder="请输入补发工资"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
                                  	 <c:if test="${type=='ADD'}">
                                  	 	value="${last.salarySupply}" 
                                  	 </c:if>
                                  	 <c:if test="${type!='ADD'}">
                                  	 	value="${entity.salarySupply}"
                                  	 </c:if>
									 />
								</div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">缺勤扣项工资</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	<span class="add-on">￥</span>
								<input type="text" class="span12" name="salaryAbsence"
									id="salaryAbsence" placeholder="请输入缺勤扣项工资"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.salaryAbsence}" />
								</div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">绩效扣项工资</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	<span class="add-on">￥</span>
								<input type="text" class="span12" name="salaryPerformanceSub"
									id="salaryPerformanceSub" placeholder="请输入绩效扣项工资"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.salaryPerformanceSub}" />
								</div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">迟到早退扣项工资</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	<span class="add-on">￥</span>
								<input type="text" class="span12" name="salaryLate"
									id="salaryLate" placeholder="请输入迟到早退扣项工资"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.salaryLate}" />
								</div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">保险扣项工资</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	<span class="add-on">￥</span>
								<input type="text" class="span12" name="salaryInsurance"
									id="salaryInsurance" placeholder="请输入保险扣项工资"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.salaryInsurance}" />
								</div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">其他扣项工资</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	<span class="add-on">￥</span>
								<input type="text" class="span12" name="salaryOther"
									id="salaryOther" placeholder="请输入其他扣项工资"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.salaryOther}" />
								</div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">个税扣项工资</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	<span class="add-on">￥</span>
								<input type="text" class="span12" name="salaryTax"
									id="salaryTax" placeholder="请输入个税扣项工资"
									<c:if test="${type=='VIEW'}">
                                  	 disabled="disabled" 
                                  	 </c:if>
									value="${entity.salaryTax}" />
								</div>
							</div>
						</div>
						<c:if test="${type=='VIEW'}">
						<div class="control-group">
							<label class="control-label">应发工资</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	<span class="add-on">￥</span>
								<input type="text" class="span12" name="salaryCount"
									id="salaryCount" disabled="disabled" 
									value="${entity.salaryCount}" />
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">实发工资</label>
							<div class="controls">
								<div class="input-prepend input-append">
                            	<span class="add-on">￥</span>
								<input type="text" class="span12" name="salaryCountFact"
									id="salaryCountFact" disabled="disabled" 
									value="${entity.salaryCountFact}" />
								</div>
							</div>
						</div>
						</c:if>
						
						
						<c:if test="${type!='VIEW'}">
							<div class="form-actions">
								<button type="button" class="btn btn-success"
									onclick="javascript:save('${entity.salaryId}');">
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
<script type="text/javascript" src="${ctx}/assets/chosen-bootstrap/chosen/chosen.jquery.js"></script>
<script type="text/javascript" src="${ctx}/assets/jquery.inputmask/jquery.inputmask.bundle.min.js"></script>
<!-- 
<script type="text/javascript"
	src="${ctx}/assets/bootstrap-datepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script> -->
<c:if test="${empty s}">
	<script src="${ctx}/custom/member/salaryInput.js"></script>
	<script>
		$(function() {
			if ('${type}' != 'VIEW') {
				initNotView();
			}
		});
	</script>
</c:if>
<c:if test="${!empty s}">
	<script src="${ctx}/custom/welcome/salaryInput.js"></script>
</c:if>
<script  type="text/javascript">
$(function() {
	//初始化时间数据
	if ('${type}' == 'ADD') {
		$('#salaryDateMonths').attr('value',dateF);
	}else{
		$('#salaryDateMonths').attr('value','${entity.salaryDateMonths}');
	}
});
</script>
<script src="${ctx}/js/form.serialize.js"></script>