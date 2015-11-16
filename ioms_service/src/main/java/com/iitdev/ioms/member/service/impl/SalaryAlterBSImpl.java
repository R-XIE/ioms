package com.iitdev.ioms.member.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iitdev.ioms.member.data.bo.SalaryAlter;
import com.iitdev.ioms.member.data.vo.SalaryAlterVO;
import com.iitdev.ioms.member.service.SalaryAlterBS;
import com.iitdev.orm.PublicBSImpl;

/**
 *薪资调整 SERVICE接口 实现类
 *
 */
@Service("salaryAlterBS")
public class SalaryAlterBSImpl extends PublicBSImpl implements SalaryAlterBS{
	public  final String SQL_QUERY_VO =  SalaryAlterVO.QUERY_SQL;
	
	public SalaryAlterVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" where obj.alter_id  = ? ";
		return super.queryForBean(SalaryAlterVO.class,sql ,new Object[]{id});
	}
	@Override
	public Boolean validateBeanExsit(Long staffId, String alterDate,Long alterId) {
		String sql =" select count(1) from m_salary_alter obj where obj.staff_id=? and obj.alter_date=? ";
		int ioun ;
		if(alterId!=null){
			sql+=" and obj.alter_id!=?";
			ioun = super.queryForInt(sql, new Object[]{staffId,alterDate,alterId});
		}else{
			ioun = super.queryForInt(sql, new Object[]{staffId,alterDate});
		}
		if(ioun!=0)
			return false;
		else
			return true;
	}
	
	@Override
	public Integer queryCountSalaryAlterByStaff(Long staffId)  {
		String countSQL="select count(1) from m_salary_alter where staff_id=?";
		return super.queryForInt(countSQL, new Object[]{staffId});
	}

	public List<SalaryAlterVO> queryAlterByStaff(Long staffId){
		return super.queryForBeanList(SalaryAlterVO.class, SalaryAlterVO.QUERY_SQL+" where obj.staff_id=? ", new Object[]{staffId});
	}
	public SalaryAlterVO queryAlterByStaff(Long staffId,Long alterId){
		return super.queryForBean(SalaryAlterVO.class, SalaryAlterVO.QUERY_SQL+" where obj.staff_id=? and obj.alter_id  = ?", new Object[]{staffId,alterId});
	}
	
	/**有外键的字段必须填充,而且要一致***/
	public SalaryAlter addSalaryAlter(SalaryAlter entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public SalaryAlter modifySalaryAlter(SalaryAlter entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	public Boolean delSalaryAlter(SalaryAlter entity)throws Exception{
		//1表删除
		delete(entity);
		return true;
	}
	@Override
	public List<SalaryAlterVO> queryVOAllList() {
		// TODO Auto-generated method stub
		return super.queryForBeanList(SalaryAlterVO.class, SalaryAlterVO.QUERY_SQL, new Object[]{});
	}
}