<#assign className = clazz.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameFull = "${basepackage}.${moduleName}.data.bo.${className}"> 
<#assign VONameFull = "${basepackage}.${moduleName}.data.vo.${className}VO"> 
package ${basepackage}.${moduleName}.service;

//import ${basepackage}.${moduleName}.data.bo.${className};
import ${basepackage}.${moduleName}.data.bo.${className};
import ${basepackage}.${moduleName}.data.vo.${className}VO;

import com.iitdev.ioms.book.data.vo.BookVO;
import com.iitdev.ioms.member.data.vo.LeaveVO;
import com.iitdev.orm.PublicBS;
import com.iitdev.page.Result;

import java.util.List;
import java.util.Map;

/**
 *${entityCnName} SERVICE接口 
 */
public interface ${className}BS extends PublicBS {
	/**通用查询方法***/
	public ${className}VO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public ${className} add${className}(${className} entity) throws Exception;//有外键的字段必须填充,而且要一致
	public ${className} modify${className}(${className} entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean del${className}(${className} entity)throws Exception;
	public List<${className}VO> queryVOListAll();
	public int queryCountAll();
	/**
	 * 
	 */
	public Result<${className}VO> queryResultByPage(Integer currPage,Map<String, String> queryMap,Integer pageSize);
}

