package com.iitdev.ioms.base.service.impl;
import com.iitdev.ioms.base.data.bo.Project;
import com.iitdev.ioms.base.data.vo.ProjectVO;
import com.iitdev.ioms.base.service.ProjectBS;
import com.iitdev.orm.MysqlSqlBuilder;
import com.iitdev.orm.PublicBSImpl;
import com.iitdev.page.Result;

import java.util.Map;

/**
 *项目 SERVICE接口 实现类
 *
 */
public class ProjectBSImpl extends PublicBSImpl implements ProjectBS{
	public  final String SQL_QUERY_VO =  ProjectVO.QUERY_SQL;
	
	public ProjectVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" and obj.project_id  = ? ";
		return super.queryForBean(ProjectVO.class,sql ,new Object[]{id});
	}
	
	
	@SuppressWarnings("unchecked")
	public Result<ProjectVO> queryForPage(int currPage,Map<String,String> queryMap){
		MysqlSqlBuilder builder = new MysqlSqlBuilder(ProjectVO.class, SQL_QUERY_VO, queryMap);
		builder.setListType(MysqlSqlBuilder.LIST_TYPE_PAGE);
		builder.setCurrPage(currPage);
		
		 builder.addCondition("projectCode", "obj.project_code",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addCondition("projectDescription", "obj.project_description",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addCondition("projectId", "obj.project_id",MysqlSqlBuilder.ParamType.LONG);
		 builder.addCondition("projectName", "obj.project_name",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addCondition("staffId", "obj.staff_id",MysqlSqlBuilder.ParamType.LONG);
		return (Result<ProjectVO>) super.querySqlForPage(builder);
	}
	
	/**有外键的字段必须填充,而且要一致***/
	public Project addProject(Project entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public Project modifyProject(Project entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	/**删除的方法**/
	public void delProjectByIds(Long ...ids)throws Exception{
		for(Long id:ids){
			Project entity = getById(id,Project.class);
			if(entity!=null)delProject(entity);
		}
	}
	public void delProject(Project entity)throws Exception{
		//1表删除
		delete(entity);
	}

}