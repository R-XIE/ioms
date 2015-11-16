package com.iitdev.ioms.member.service.impl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iitdev.ioms.member.data.bo.Leave;
import com.iitdev.ioms.member.data.po.LeavePO;
import com.iitdev.ioms.member.data.vo.LeaveVO;
import com.iitdev.ioms.member.service.LeaveBS;
import com.iitdev.orm.MysqlSqlBuilder;
import com.iitdev.orm.PublicBSImpl;
import com.iitdev.page.Result;


/**
 *请假 SERVICE接口 实现类
 *
 */
@Service("leaveBS")
public class LeaveBSImpl extends PublicBSImpl implements LeaveBS{
	public  final String SQL_QUERY_VO =  LeaveVO.QUERY_SQL;
	
	public LeaveVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" where obj.leave_id  = ? ";
		return super.queryForBean(LeaveVO.class,sql ,new Object[]{id});
	}
	
	public Integer queryCountLeaveByStaff(Long staffId){
		String sql = "select count(1) from m_leave obj where obj.staff_id=?";
		return super.queryForInt(sql, new Object[]{staffId});
	}
	
	/**有外键的字段必须填充,而且要一致***/
	public Leave addLeave(Leave entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public Leave modifyLeave(Leave entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	public Boolean delLeave(Leave entity)throws Exception{
		//1表删除
		delete(entity);
		return true;
	}

	@Override
	public List<LeaveVO> queryLeaveByStaff(Long staffId) {
		// TODO Auto-generated method stub
		return super.queryForBeanList(LeaveVO.class, LeaveVO.QUERY_SQL+" where obj.staff_id=? ", new Object[]{staffId});
	}
	
	public LeaveVO queryLeaveByStaff(Long staffId,Long leaveId){
		return super.queryForBean(LeaveVO.class, LeaveVO.QUERY_SQL+" where obj.staff_id=? and obj.leave_id  = ?", new Object[]{staffId,leaveId});
	}
	
	@Override
	public BigDecimal validateListByStaffMonth(Long staff,String months) {
		String sql=LeavePO.QUERY_SQL+" where obj.staff_id=? and leave_date_months=?";
		BigDecimal monthsCount=new BigDecimal(0);
		List<LeavePO> list= super.queryForBeanList(LeavePO.class, sql, new Object[]{staff,months});
		for (LeavePO leavePO : list) {
			monthsCount=monthsCount.add(leavePO.getLeaveDays());
		}
		return monthsCount;
	}

	@Override
	public List<LeaveVO> queryVOAllList() {
		// TODO Auto-generated method stub
		return super.queryForBeanList(LeaveVO.class, LeaveVO.QUERY_SQL, new Object[]{});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result<LeaveVO> queryResultByPage(Integer p,
			Map<String, String> queryMap, Integer s) {
		MysqlSqlBuilder builder = new MysqlSqlBuilder(LeaveVO.class, SQL_QUERY_VO, queryMap);
		builder.setListType(MysqlSqlBuilder.LIST_TYPE_PAGE);
		builder.setCurrPage(p);
		builder.setPageSize(s);
		builder.addCondition("staffId", "obj.staff_id");
		builder.addCondition("staffName", "staff.staff_real_name", MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		builder.addCondition("branchId", "branch.branch_id");
		builder.addCondition("positionId", "staff.position_id");
		builder.addCondition("leaveType", "obj.leave_type");
		builder.addCondition("leaveDate", "obj.leave_date_begin", MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		builder.addCondition("leaveDays", "obj.leave_days", MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		builder.addDateInCondition("createDateBegin", "createDateEnd", "obj.create_date", MysqlSqlBuilder.ParamType.DATE);
		return (Result<LeaveVO>) super.querySqlForPage(builder);
	}

}