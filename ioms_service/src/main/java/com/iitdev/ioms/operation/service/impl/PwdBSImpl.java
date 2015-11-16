package com.iitdev.ioms.operation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iitdev.ioms.operation.data.bo.Pwd;
import com.iitdev.ioms.operation.data.vo.PwdVO;
import com.iitdev.ioms.operation.service.PwdBS;
import com.iitdev.ioms.operation.service.PwdStaffBS;
import com.iitdev.orm.PublicBSImpl;
@Service("pwdBS")
public class PwdBSImpl extends PublicBSImpl implements PwdBS{
	public final String SQL_QUERY_VO = PwdVO.QUERY_SQL;
	@Autowired
	private PwdStaffBS pwdStaffBS;
	@Override
	public PwdVO queryVOById(Long id) {
		String sql = SQL_QUERY_VO + " where obj.pwd_id  = ? ";
		return super.queryForBean(PwdVO.class, sql, new Object[] { id });
	}

	@Override
	public Pwd addPwd(Pwd entity) throws Exception {
		super.saveObject(entity);
		return entity;
	}

	@Override
	public Pwd modifyPwd(Pwd entity) throws Exception {
		super.updateObject(entity);
		return entity;
	}

	@Override
	public boolean delPwd(Pwd entity) throws Exception {
		pwdStaffBS.delPwdStaffByPwd(entity.getPwdId());
		delete(entity);
		return true;
	}

	@Override
	public List<PwdVO> queryVOListAll() {
		return super.queryForBeanList(PwdVO.class, SQL_QUERY_VO,
				new Object[] {});
	}

	@Override
	public List<PwdVO> queryVOListByStaff(Long staff) {
		//获取所有的公开信息
		List<PwdVO> openVOList=super.queryForBeanList(PwdVO.class, SQL_QUERY_VO + " where obj.pwd_level=?" ,
				new Object[] {-1l});
		//获取所有的机密信息
		List<PwdVO> closeVOList=super.queryForBeanList(PwdVO.class, SQL_QUERY_VO + " INNER JOIN o_pwd_staff pwd_staff ON pwd_staff.pwd_id=obj.pwd_id"
				+ " where obj.pwd_level=? and pwd_staff.staff_id=? " ,
				new Object[] {1l,staff});
		List<PwdVO> list=openVOList;
		list.addAll(closeVOList);
		return list;
	}

}