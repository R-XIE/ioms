package com.iitdev.ioms.base.service;

//import com.iitdev.ioms.base.data.bo.Project;
import com.iitdev.ioms.base.data.bo.Project;
import com.iitdev.ioms.base.data.vo.ProjectVO;
import com.iitdev.orm.PublicBS;
import com.iitdev.page.Result;

import java.util.Map;

/**
 *项目 SERVICE接口 
 */
public interface ProjectBS extends PublicBS {
	/**通用查询方法***/
	public ProjectVO queryVOById(Long id);	
	public Result<ProjectVO> queryForPage(int currPage,Map<String,String> queryMap);//分页方法
	/**通用持久化方法(添加和修改)***/
	public Project addProject(Project entity) throws Exception;//有外键的字段必须填充,而且要一致
	public Project modifyProject(Project entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public void delProjectByIds(Long ...ids)throws Exception;
	public void delProject(Project entity)throws Exception;
}

