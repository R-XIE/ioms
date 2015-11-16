<#macro jspEl value>${r"${"}${value}}</#macro>
<#assign properties=clazz.properties>
var ${clazzLowerName}Local = '/${moduleName}/${clazzLowerName}Action';
var validate;
var editParam;
var addParam;
var dataParam = {
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
	};
var chosenParam={no_results_text : '没有匹配结果'};
var validateParam = {
		rules : {
			<#list properties as prop>
			<#if (prop.name != clazz.pkProperty.name)&&(prop.name != "class")>
			<#if (!prop.name?ends_with("remark"))&&(!prop.name?ends_with("Remark"))&&(!prop.name?ends_with("content"))&&(!prop.name?ends_with("Content"))>
			<#if (!prop.name?ends_with("state"))&&(!prop.name?ends_with("State"))>
			${prop.name}:{
				required:true
			},
			</#if>
			</#if>
			</#if>
			</#list>
		},
		messages : {
			<#list properties as prop>
			<#if (prop.name != clazz.pkProperty.name)&&(prop.name != "class")>
			<#if (!prop.name?ends_with("remark"))&&(!prop.name?ends_with("Remark"))&&(!prop.name?ends_with("content"))&&(!prop.name?ends_with("Content"))>
			<#if (!prop.name?ends_with("state"))&&(!prop.name?ends_with("State"))>
			${prop.name}:{
				required:"${prop.column.columnAlias}不能为空"
			},
			</#if>
			</#if>
			</#if>
			</#list>
		},
		onfocusout : function(element) {
			$(element).valid();
		}
};
function ck_${clazzLowerName}() {
	blockPost(ctx + ${clazzLowerName}Local + 'ListAjax.htm');
//	blockPost(ctx + ${clazzLowerName}Local + 'List.htm');
}
<#list properties as prop>
<#if (prop.name !=clazz.pkProperty.name)&&(prop.name != "class")>
<#if (prop.name?ends_with("State"))||(prop.name?ends_with("state"))|| (prop.name?ends_with("Id"))||(prop.name?ends_with("id")) || (prop.name?ends_with("Type"))||(prop.name?ends_with("type"))>
var ${prop.name}Chosen;
</#if>
</#if>
</#list>
function initNotView() {
	<#list properties as prop>
	<#if (prop.name !=clazz.pkProperty.name)&&(prop.name != "class")>
	<#if prop.asType== 'Date'>
	var ${prop.name}=$('#${prop.name}').datetimepicker(dataParam);
	<#else>
	<#if (prop.name?ends_with("State"))||(prop.name?ends_with("state"))|| (prop.name?ends_with("Id"))||(prop.name?ends_with("id")) || (prop.name?ends_with("Type"))||(prop.name?ends_with("type"))>
	${prop.name}Chosen=$('#${prop.name}').chosen(chosenParam);
	</#if>
	</#if>
	</#if>
	</#list>
	validate = $("#input_${clazz.underLineName}_form").validate(validateParam);
}
function save(id) {
	var dialog = new DialogFunc("提示信息", '保存${entityCnName}成功', "success").init('success',
			'hiddenDialog');
	if ($('#input_${clazz.underLineName}_form').valid()) {
			var json=form_serialize({
				<#list properties as prop>
				<#if (prop.name !=clazz.pkProperty.name)&&(prop.name != "class")>
				<#if (prop.name?ends_with("State"))||(prop.name?ends_with("state"))|| (prop.name?ends_with("Id"))||(prop.name?ends_with("id")) || (prop.name?ends_with("Type"))||(prop.name?ends_with("type"))>
				${prop.name}:${prop.name}Chosen.val(),
				</#if>
				</#if>
				</#list>
			});
			if (id == null || id == '') {
				addParam =json;
				$.ajax({
					type : "post",
					url : ctx + ${clazzLowerName}Local + "Save.htm",
					data : addParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '新增${entityCnName}成功.',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_${clazzLowerName}();
						} else {
							dialog = new DialogFunc("错误", '新增${entityCnName}失败', "alert")
									.init('alert', 'hiddenDialog');
							dialog.show();
						}
					},
					error : function(e) {
						dialog.hide();
						$('#hiddenDialog').html();
						$('div[class="modal-backdrop fade in"]').remove();
						dialog = new DialogFunc("错误", 'JS执行错误', "alert").init(
								'alert', 'hiddenDialog');
						dialog.show();
					}
				});
			} else {
				editParam = json;
				editParam['${clazz.pkProperty.name}']=id;
				$.ajax({
					type : "post",
					url : ctx + ${clazzLowerName}Local + "Save.htm",
					data : editParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '保存${entityCnName}成功',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_${clazzLowerName}();
						} else {
							dialog = new DialogFunc("错误", '保存${entityCnName}失败', "alert")
									.init('alert', 'hiddenDialog');
							dialog.show();
						}
					},
					error : function(e) {
						dialog.hide();
						$('#hiddenDialog').html();
						$('div[class="modal-backdrop fade in"]').remove();
						dialog = new DialogFunc("错误", 'JS执行错误', "alert").init(
								'alert', 'hiddenDialog');
						dialog.show();
					}
				});
			}
		}
	}