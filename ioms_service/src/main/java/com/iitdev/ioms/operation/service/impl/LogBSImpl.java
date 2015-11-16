package com.iitdev.ioms.operation.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iitdev.ioms.operation.data.bo.Log;
import com.iitdev.ioms.operation.data.vo.LogVO;
import com.iitdev.ioms.operation.service.LogBS;
import com.iitdev.orm.MysqlSqlBuilder;
import com.iitdev.orm.PublicBSImpl;
import com.iitdev.page.Result;

/**
 *更改维护记录 SERVICE接口 实现类
 *
 */
@Service("logBS")
public class LogBSImpl extends PublicBSImpl implements LogBS{
	public  final String SQL_QUERY_VO =  LogVO.QUERY_SQL;
	
	public LogVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" and obj.log_id  = ? ";
		return super.queryForBean(LogVO.class,sql ,new Object[]{id});
	}
	public List<LogVO> queryVOListAll(){
		return super.queryForBeanList(LogVO.class,SQL_QUERY_VO ,new Object[]{});
	}
	
	@SuppressWarnings("unchecked")
	public Result<LogVO> queryResultByPage(Integer currPage,
			Map<String, String> queryMap, Integer pageSize) {
		MysqlSqlBuilder builder = new MysqlSqlBuilder(LogVO.class, SQL_QUERY_VO, queryMap);
		builder.setListType(MysqlSqlBuilder.LIST_TYPE_PAGE);
		builder.setCurrPage(currPage);
		builder.setPageSize(pageSize);
		 builder.addCondition("logCause", "obj.log_cause",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addCondition("logContent", "obj.log_content",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addDateInCondition("logDateBegin","logDateEnd", "obj.log_date",MysqlSqlBuilder.ParamType.DATE);
		 builder.addCondition("logId", "obj.log_id",MysqlSqlBuilder.ParamType.LONG);
		 builder.addCondition("logLocation", "obj.log_location",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addCondition("logRemark", "obj.log_remark",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addCondition("logStaffName", "staff.staff_real_name",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addCondition("logState", "obj.log_state",MysqlSqlBuilder.ParamType.LONG);
		 builder.addCondition("logTitle", "obj.log_title",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		return (Result<LogVO>) super.querySqlForPage(builder);
	}
	
	/**有外键的字段必须填充,而且要一致***/
	public Log addLog(Log entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public Log modifyLog(Log entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	
	public boolean delLog(Log entity)throws Exception{
		//1表删除
		delete(entity);
		return true;
	}

}