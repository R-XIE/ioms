package com.iitdev.ioms.member.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iitdev.ioms.member.data.bo.Interview;
import com.iitdev.ioms.member.data.vo.InterviewVO;
import com.iitdev.ioms.member.service.InterviewBS;
import com.iitdev.orm.MysqlSqlBuilder;
import com.iitdev.orm.PublicBSImpl;
import com.iitdev.page.Result;

/**
 *面试 SERVICE接口 实现类
 *
 */
@Service("interviewBS")
public class InterviewBSImpl extends PublicBSImpl implements InterviewBS{
	public  final String SQL_QUERY_VO =  InterviewVO.QUERY_SQL;
	
	public InterviewVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" where obj.interview_id  = ? ";
		return super.queryForBean(InterviewVO.class,sql ,new Object[]{id});
	}
	
	/**有外键的字段必须填充,而且要一致***/
	public Interview addInterview(Interview entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public Interview modifyInterview(Interview entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	public Boolean delInterview(Interview entity)throws Exception{
		//1表删除
		delete(entity);
		return true;
	}
	@Override
	public List<InterviewVO> queryVOAllList() {
		// TODO Auto-generated method stub
		return super.queryForBeanList(
				InterviewVO.class, InterviewVO.QUERY_SQL, new Object[] {});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result<InterviewVO> queryResultByPage(Integer currPage,
			Map<String, String> queryMap, Integer pageSize) {
		MysqlSqlBuilder builder = new MysqlSqlBuilder(InterviewVO.class, SQL_QUERY_VO, queryMap);
		builder.setListType(MysqlSqlBuilder.LIST_TYPE_PAGE);
		builder.setCurrPage(currPage);
		builder.setPageSize(pageSize);
		builder.addCondition("interviewName", "obj.interview_name", MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		builder.addCondition("interviewSex", "obj.interview_sex");
		builder.addCondition("interviewPosition", "obj.interview_position");
		builder.addCondition("interviewCurrent", "obj.interview_current");
		builder.addCondition("interviewResult", "obj.interview_result");
		builder.addCondition("interviewPhone", "obj.interview_phone", MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		builder.addCondition("interviewScore", "obj.interview_score", MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		builder.addDateInCondition("interviewDateBegin", "interviewDateEnd", "obj.interview_date", MysqlSqlBuilder.ParamType.DATE);
		return (Result<InterviewVO>) super.querySqlForPage(builder);
	}

}