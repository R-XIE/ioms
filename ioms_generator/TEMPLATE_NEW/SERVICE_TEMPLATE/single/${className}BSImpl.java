<#assign className = clazz.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameFull = "${basepackage}.${moduleName}.data.bo.${className}"> 
<#assign VONameFull = "${basepackage}.${moduleName}.data.vo.${className}VO"> 
package ${basepackage}.${moduleName}.service.impl;
import ${basepackage}.${moduleName}.data.bo.${className};
import ${basepackage}.${moduleName}.data.vo.${className}VO;

import com.iitdev.orm.MysqlSqlBuilder;
import com.iitdev.orm.PublicBSImpl;
import com.iitdev.page.Result;

import ${basepackage}.${moduleName}.service.${className}BS;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 *${entityCnName} SERVICE接口 实现类
 *
 */
@Service("${className}BS")
public class ${className}BSImpl extends PublicBSImpl implements ${className}BS{
	public  final String SQL_QUERY_VO =  ${className}VO.QUERY_SQL;
	
	public ${className}VO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" and obj.${clazz.pkProperty.columnName?lower_case}  = ? ";
		return super.queryForBean(${className}VO.class,sql ,new Object[]{id});
	}
	public List<${className}VO> queryVOListAll(){
		return super.queryForBeanList(${className}VO.class,SQL_QUERY_VO ,new Object[]{});
	}
	
	public int queryCountAll() {
		return super.queryForInt(${className}VO.QUERY_SQL_COUNT, new Object[]{});
	}
	
	public Result<${className}VO> queryResultByPage(Integer currPage,
			Map<String, String> queryMap, Integer pageSize) {
		MysqlSqlBuilder builder = new MysqlSqlBuilder(${className}VO.class, SQL_QUERY_VO, queryMap);
		builder.setListType(MysqlSqlBuilder.LIST_TYPE_PAGE);
		builder.setCurrPage(currPage);
		builder.setPageSize(pageSize);
		<#list clazz.properties as prop>
		 <#if (!prop.name?ends_with("remark")) && prop.name != "versionNo" && prop.name != "class">
		 <#if prop.asType == 'Date'>	
		 builder.addDateInCondition("${prop.name}Begin","${prop.name}End", "obj.${prop.columnName?lower_case}",MysqlSqlBuilder.ParamType.DATE);
		 <#elseif prop.name?ends_with("Id") || prop.name?ends_with("State")>
		 builder.addCondition("${prop.name}", "obj.${prop.columnName?lower_case}",MysqlSqlBuilder.ParamType.LONG);
		 <#else>
		 builder.addCondition("${prop.name}", "obj.${prop.columnName?lower_case}",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 </#if>
		 </#if>
		</#list>
		return (Result<${className}VO>) super.querySqlForPage(builder);
	}
	
	/**有外键的字段必须填充,而且要一致***/
	public ${className} add${className}(${className} entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public ${className} modify${className}(${className} entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	
	public boolean del${className}(${className} entity)throws Exception{
		//1表删除
		delete(entity);
		return true;
	}

}