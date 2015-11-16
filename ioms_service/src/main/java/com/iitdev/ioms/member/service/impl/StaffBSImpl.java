package com.iitdev.ioms.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iitdev.orm.PublicBSImpl;
import com.iitdev.data.LoginInfo;
import com.iitdev.encrypt.MD5;
import com.iitdev.ioms.member.data.bo.Staff;
import com.iitdev.ioms.member.data.vo.StaffVO;
import com.iitdev.ioms.member.service.StaffBS;

/**
 *staff service implements
 *
 */
@Service("staffBS")
public  class StaffBSImpl extends PublicBSImpl implements StaffBS{
	
	public StaffVO queryVOById(Long id){
		String sql =StaffVO.QUERY_SQL_ALL+
			" where obj.staff_id  = ? ";
		return super.queryForBean(StaffVO.class,sql ,new Object[]{id});
	}
	
	@Override
	public LoginInfo validatePwd(String loginName, String pwd)
			{
		String sql =StaffVO.QUERY_SQL+" and obj.staff_login_name=? and obj.staff_pwd=?";
		LoginInfo loginInfo =null;
		String md5=MD5.getMd5Password(pwd);
		loginInfo=super.queryForBean(LoginInfo.class, sql, new Object[]{loginName,md5});
		if(loginInfo==null)return null;
		return loginInfo;
	}
	
	public Boolean validateLoginName(String loginName)
			{
		String sql ="select count(1) from m_staff obj where obj.staff_state!=-1"+
				" and obj.staff_login_name=?";
		int count=super.queryForInt(sql, new Object[]{loginName});
		if(count!=1){
			try {
				if(count>1)throw new Exception("返回结果不唯一");
			} catch (Exception e) {
				return false;
			}
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public Boolean validateInputLoginName(String loginName)
			{
		String sql ="select count(1) from m_staff obj where obj.staff_state!=-1"+
				" and obj.staff_login_name=?";
		int count=super.queryForInt(sql, new Object[]{loginName});
		if(count!=0){
			try {
				if(count>1)throw new Exception("返回结果不唯一");
			} catch (Exception e) {
				return false;
			}
			return false;
		}else{
			return true;
		}
	}
	
	public Staff addStaff(Staff entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public Staff modifyStaff(Staff entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	public Boolean delStaff(Staff entity)throws Exception{
		delete(entity);
		return true;
	}

	@Override
	public List<StaffVO> queryVOAllList(Boolean isLeave) {
		String whereSQL = null;
		if(isLeave){
			//离职的
			whereSQL=" where obj.staff_state=-1 ";
		}else if(isLeave==false){
			//在职
			whereSQL=" where obj.staff_state!=-1 ";
		}else if(isLeave==null){
			//全部
			whereSQL=" where 1=1 ";
		}
		return super.queryForBeanList(StaffVO.class, 
				StaffVO.QUERY_SQL_ALL+whereSQL, new Object[]{});
	}
	
	@Override
	public List<StaffVO> queryStaffByBranch(Long branchId){
		String whereSQL = " where obj.staff_state!=-1  and branch.branch_id=?";
		return super.queryForBeanList(StaffVO.class, 
				StaffVO.QUERY_SQL_ALL+whereSQL, new Object[]{branchId});
	}
	
	@Override
	public List<StaffVO> queryVOAllExistList() {
		return super.queryForBeanList(StaffVO.class, StaffVO.QUERY_SQL, new Object[]{});
	}

	@Override
	public Integer queryStaffIdDef() {
		// TODO Auto-generated method stub
		return super.queryForInt("select staff_id from m_staff limit 1", new Object[]{});
	}
}