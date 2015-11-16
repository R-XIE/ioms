<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${moduleName}.data.vo;
import ${basepackage}.${moduleName}.data.bo.${className};
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

/**
 * ${table.tableAlias}
 *
 */
public class ${className}VO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from ${table.sqlName} obj" ;
	public static final String QUERY_SQL_COUNT = "select count(1) from ${table.sqlName} obj" ;
	<#list table.columns as column>
	<#if !column.sqlName?starts_with("DEFINE")>
	<#if column.sqlName == "STATE">
	private ${column.javaType} ${column.columnNameLower} = "0";
	<#elseif column.sqlName?ends_with("date")>
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private ${column.javaType} ${column.columnNameLower};
	<#elseif column.sqlName?ends_with("time")>
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private ${column.javaType} ${column.columnNameLower};
	<#else>
	private ${column.javaType} ${column.columnNameLower};//${column.columnAlias}
	</#if>
	</#if>
	</#list>
	
	
	/////////////////////////getter and setter //////////////////////////
	<#list table.columns as column>
	<#if !column.sqlName?starts_with("DEFINE")>
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	</#if>
	</#list>
	
	
	//////////////////////////////////////////////////////
	public ${className}VO(){}

	public ${className}VO(${className} ${classNameLower}){
		BeanUtils.copyProperties(${classNameLower},this);
	}
	public void copyValueTo(${className} bo){
		BeanUtils.copyProperties(this,bo);
	}
	public ${className} cloneBO(){
		${className} bo = new ${className}();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<${className}> cloneBOList(List<${className}VO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<${className}>(0);
		List<${className}> result = new ArrayList<${className}>(vos.size());
		for(${className}VO vo: vos){
			${className} bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}