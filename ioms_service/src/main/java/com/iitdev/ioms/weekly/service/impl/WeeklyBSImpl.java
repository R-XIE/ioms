package com.iitdev.ioms.weekly.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iitdev.ioms.weekly.common.Globals;
import com.iitdev.ioms.weekly.common.Globals.Mweekly;
import com.iitdev.ioms.weekly.data.bo.Weekly;
import com.iitdev.ioms.weekly.data.vo.WeeklyVO;
import com.iitdev.ioms.weekly.service.WeeklyBS;
import com.iitdev.orm.MysqlSqlBuilder;
import com.iitdev.orm.PublicBSImpl;
import com.iitdev.page.Result;

/**
 * 个人周报 SERVICE接口 实现类
 * 
 */
@Service("weeklyBS")
public class WeeklyBSImpl extends PublicBSImpl implements WeeklyBS {
	public final String SQL_QUERY_VO = WeeklyVO.QUERY_SQL;
	
	public WeeklyVO queryVOById(Long id) {
		String sql = SQL_QUERY_VO + " and obj.weekly_id  = ? ";
		return super.queryForBean(WeeklyVO.class, sql, new Object[] { id });
	}

	public List<WeeklyVO> queryVOListAll() {
		return super.queryForBeanList(WeeklyVO.class, SQL_QUERY_VO,
				new Object[] {});
	}

	public int queryCountAll(Long staffId) {
		return super.queryForInt(WeeklyVO.QUERY_SQL_COUNT
				+ " where obj.weekly_staff=? ", new Object[] { staffId });
	}

	@Override
	public List<WeeklyVO> queryVOListStaff(Long staffId) {
		return super.queryForBeanList(WeeklyVO.class, SQL_QUERY_VO
				+ " where obj.weekly_staff=? ", new Object[] { staffId });
	}

	@Override
	public WeeklyVO queryBeanStaff(Long recordId, Long staffId) {
		return super.queryForBean(WeeklyVO.class, SQL_QUERY_VO
				+ " where obj.weekly_staff=? and obj.weekly_id=? ",
				new Object[] { staffId, recordId });
	}


	/** 有外键的字段必须填充,而且要一致 ***/
	public Weekly addWeekly(Weekly entity, Mweekly state) throws Exception {
		// 1主表验证和此表的外键字段一致
		String icountSQL = "select count(1) from w_weekly obj where weekly_staff=? and weekly_begin_date=?";
		int icount = super.queryForInt(
				icountSQL,
				new Object[] { entity.getWeeklyStaff(),
						entity.getWeeklyBeginDate() });
		if (icount != 0)
			return null;
		else {
			// 添加code编码
			// 保存表
			entity.setWeeklyCreateDate(new Date());
			if (state != null)
				switch (state) {
				case SUBMITED: {
					entity.setWeeklyState(Globals.L_WEEKLY_STATE_SUBMIT);
					break;
				}
				case UNSUBMITED: {
					entity.setWeeklyState(Globals.L_WEEKLY_STATE_UNSUBMIT);
					break;
				}
				default:
					break;
				}
			super.saveObject(entity);
			return entity;
		}
	}

	public Weekly modifyWeekly(Weekly entity,Long staffId) throws Exception {
		// 1主表验证和此表的外键字段一致
		String icountSQL = "select count(1) from w_weekly obj where weekly_staff=? and weekly_begin_date=? and weekly_staff!=?";
		int icount = super.queryForInt(
				icountSQL,
				new Object[] { entity.getWeeklyStaff(),
						entity.getWeeklyBeginDate() ,staffId});
		if (icount != 0)
			return null;
		// 修改表
		else {
			super.updateObject(entity);
			return entity;
		}
	}

	public boolean delWeekly(Weekly entity) throws Exception {
		// 1表删除
		delete(entity);
		return true;
	}

	@Override
	public Boolean validateWeeklyStaffMonth(Long staffId, Date beginDate,
			Date endDate) {
		String sql="SELECT count(1) from w_weekly where weekly_staff=? and weekly_begin_date=? and weekly_end_date=?";
		int icount=super.queryForInt(sql, new Object[]{staffId,beginDate,endDate});
		if(icount==0)
			return true;
		else
			return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result<WeeklyVO> queryResultByPage(Integer p,
			Map<String, String> queryMap, Integer s) {
		MysqlSqlBuilder builder = new MysqlSqlBuilder(WeeklyVO.class, SQL_QUERY_VO, queryMap);
		builder.setListType(MysqlSqlBuilder.LIST_TYPE_PAGE);
		builder.setCurrPage(p);
		builder.setPageSize(s);
		builder.addCondition("staffId", "obj.weekly_staff");
		builder.addCondition("weeklyStaff", "staff.staff_real_name", MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		builder.addCondition("weeklyBeginDate", "obj.weekly_begin_date", MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		builder.addCondition("weeklyEndDate", "obj.weekly_end_date", MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		builder.addCondition("weeklyState", "obj.weekly_state");
		builder.addDateInCondition("weeklyCreateDateBegin", "weeklyCreateDateEnd", "obj.weekly_create_date", MysqlSqlBuilder.ParamType.DATE);
		return (Result<WeeklyVO>) super.querySqlForPage(builder);
	}

}