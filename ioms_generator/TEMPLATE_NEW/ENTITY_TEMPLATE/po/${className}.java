<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${moduleName}.data.bo;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * ${table.tableAlias}
 */
@Table(name = "${table.sqlName}")
public class ${className} implements Serializable<#list table.columns as column><#if column.sqlName == "UPDATE_TIME">,RecordUserAndTimeAble</#if></#list>{
	private static final long serialVersionUID = 1L;
	<@generateConstructor/>
	//属性
	<@generateFields/>
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	<@generatePkProperties/>
	<@generateNotPkProperties/>
	
	
}
<#macro generateFields>
	<#list table.columns as column>
	<#if !column.sqlName?starts_with("DEFINE")>
	private ${column.javaType} ${column.columnNameLower};//${column.columnAlias}
	</#if>
	</#list>
</#macro>


<#macro generateConstructor>
	public ${className}(){}
</#macro>

<#macro generatePkProperties>
	<#list table.columns as column>
	<#if column.pk>	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "${column.sqlName}", unique = ${column.unique?string}, nullable = ${column.nullable?string},length = ${column.size})
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	public void set${column.columnName}(${column.javaType} ${column.columnName}) {
		this.${column.columnNameLower} = ${column.columnName};
	}
	
	</#if>
	</#list>
</#macro>



<#macro generateNotPkProperties>
	<#list table.columns as column>
	<#if !column.sqlName?starts_with("DEFINE")>
	<#if !column.pk>
	<#if column.sqlName == "VERSION_NO">
	@Version
	@Column(name = "${column.sqlName}")
	<#elseif column.javaType == "BigDecimal" >
	@Column(name = "${column.sqlName}",precision=${column.size},scale = ${column.decimalDigits},unique = ${column.unique?string},nullable = ${column.nullable?string})
	<#else>
	@Column(name = "${column.sqlName}",unique = ${column.unique?string},nullable = ${column.nullable?string},length = ${column.size})
	</#if>
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	</#if>
	</#if>
	</#list>
</#macro>