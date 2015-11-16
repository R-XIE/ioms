package com.iitdev.ioms.operation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iitdev.ioms.operation.data.bo.PwdStaff;
import com.iitdev.ioms.operation.data.vo.PwdStaffVO;
import com.iitdev.ioms.operation.service.PwdStaffBS;
import com.iitdev.orm.PublicBSImpl;

/**
 * 敏感信息与用户中间表 SERVICE接口 实现类
 * 
 */
@Service("pwdStaffBS")
public class PwdStaffBSImpl extends PublicBSImpl implements PwdStaffBS {
	public final String SQL_QUERY_VO = PwdStaffVO.QUERY_SQL;

	public PwdStaffVO queryVOById(Long id) {
		String sql = SQL_QUERY_VO + " where obj.pwd_staff_id  = ? ";
		return super.queryForBean(PwdStaffVO.class, sql, new Object[] { id });
	}

	public List<PwdStaffVO> queryVOListAll() {
		return super.queryForBeanList(PwdStaffVO.class, SQL_QUERY_VO,
				new Object[] {});
	}

	/** 有外键的字段必须填充,而且要一致 ***/
	public PwdStaff addPwdStaff(PwdStaff entity) throws Exception {
		// 1主表验证和此表的外键字段一致
		// 添加code编码
		// 保存表
		super.saveObject(entity);
		return entity;
	}

	public PwdStaff modifyPwdStaff(PwdStaff entity) throws Exception {
		// 1主表验证和此表的外键字段一致

		// 修改表
		super.updateObject(entity);
		return entity;
	}

	public boolean delPwdStaff(PwdStaff entity) throws Exception {
		// 1表删除
		delete(entity);
		return true;
	}

	@Override
	public boolean delPwdStaffByPwd(Long pwdId) {
		String sql = "delete from o_pwd_staff where  pwd_id=" + pwdId;
		super.executeSql(sql);
		return true;
	}

	@Override
	public boolean delPwdStaffByPwdStaff(Long pwdId, Long staff) {
		String sql = "delete from o_pwd_staff where  pwd_id=" + pwdId
				+ " and staff_id=" + staff;
		super.executeSql(sql);
		return true;
	}

	@Override
	public PwdStaffVO queryPwdStaffByPwdStaff(Long pwdId, Long staff) {
		String sql = SQL_QUERY_VO + " where obj.pwd_id  = ?  and obj.staff_id =? ";
		return super.queryForBean(PwdStaffVO.class, sql, new Object[] { pwdId,staff });
	}
}