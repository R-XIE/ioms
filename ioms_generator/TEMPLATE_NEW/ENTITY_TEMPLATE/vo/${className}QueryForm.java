<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${moduleName}.data.po;
import ${basepackage}.${moduleName}.data.bo.${className};

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.io.Serializable;

import com.iitdev.data.QueryFormBean;
import com.iitdev.utils.BeanUtils;

/**
 * ${table.tableAlias}
 *
 */
public class ${className}QueryForm extends QueryFormBean {
	private static final long serialVersionUID = 1L;
	<#list table.columns as column>
	<#if !column.sqlName?starts_with("DEFINE")>
	<#if column.sqlName == "STATE">
	private ${column.javaType} ${column.columnNameLower} = "0";
	<#elseif column.sqlName?ends_with("date")>
	private String ${column.columnNameLower}BE;
	<#elseif column.sqlName?ends_with("time")>
	private String ${column.columnNameLower}BE;
	<#else>
	private ${column.javaType} ${column.columnNameLower};//${column.columnAlias}
	</#if>
	</#if>
	</#list>
	
	
	/////////////////////////getter and setter //////////////////////////
	<#list table.columns as column>
	<#if !column.sqlName?starts_with("DEFINE") &&  !column.sqlName?ends_with("time") && !column.sqlName?ends_with("date")>
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	<#elseif column.sqlName?ends_with("time") || column.sqlName?ends_with("date")>
	public String get${column.columnName}BE() {
		return this.${column.columnNameLower}BE;
	}
	public void set${column.columnName}BE(String value) {
		this.${column.columnNameLower}BE = value;
	}
	</#if>
	</#list>
	
	
	//////////////////////////////////////////////////////
	public ${className}QueryForm(){}
	
	public Map<String, String> buildQueryMap() {
		return BeanUtils.getQueryMap(this);
	}
}