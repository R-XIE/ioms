package com.iitdev.ioms.member.service.impl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iitdev.ioms.member.data.bo.Salary;
import com.iitdev.ioms.member.data.vo.SalaryVO;
import com.iitdev.ioms.member.service.SalaryAlterBS;
import com.iitdev.ioms.member.service.SalaryBS;
import com.iitdev.orm.MysqlSqlBuilder;
import com.iitdev.orm.PublicBSImpl;
import com.iitdev.page.Result;

/**
 *工资条 SERVICE接口 实现类
 *
 */
@Service("salaryBS")
public class SalaryBSImpl extends PublicBSImpl implements SalaryBS{
	public  final String SQL_QUERY_VO =  SalaryVO.QUERY_SQL;
	@Autowired
	private SalaryAlterBS salaryAlterBS;

	public SalaryVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" and obj.salary_id  = ? ";
		return super.queryForBean(SalaryVO.class,sql ,new Object[]{id});
	}
	public List<SalaryVO> queryVOListAll(){
		return super.queryForBeanList(SalaryVO.class,SQL_QUERY_VO ,new Object[]{});
	}
	public List<SalaryVO> queryVOListStaff(Long staffId){
		String sql = SQL_QUERY_VO+
				" where obj.salary_staff  = ? ";
		return super.queryForBeanList(SalaryVO.class,sql ,new Object[]{staffId});
	}
	@Override
	public SalaryVO queryBeanStaff(Long recordId, Long staffId) {
		String sql = SQL_QUERY_VO+
				" where obj.salary_staff  = ? and obj.salary_id  = ? ";
		return super.queryForBean(SalaryVO.class, sql, new Object[]{staffId,recordId});
	}
	@Override
	public SalaryVO validateBeanStaff(Long staffId, String months,String currMonth) {
		String sql = SQL_QUERY_VO+
				" and obj.salary_staff  = ?  and obj.salary_date_months=?";
		SalaryVO vo =new SalaryVO();
		if(salaryAlterBS.validateBeanExsit(staffId, currMonth, null)){
			vo=super.queryForBean(SalaryVO.class, sql, new Object[]{staffId,months});
		}
		return vo;
	}
	public BigDecimal queryCountStaff(Long staffId) {
		//select * from m_salary salary ORDER BY salary.salary_date_months desc
		SalaryVO vo= super.queryForBean(SalaryVO.class, SalaryVO.QUERY_SQL_COUNT, new Object[]{staffId});
		if(vo!=null)
			return vo.getSalaryCountFact();
		else 
			return new BigDecimal(0);
		
	}
	public Boolean validateBeanExsit(Long staffId,String months,Long salaryId){
		String sql="select count(1) from m_salary salary where salary.salary_staff=? and salary.salary_date_months=?";
		if(salaryId==null){
			//add
			int icount = super.queryForInt(sql, new Object[]{staffId,months});
			if(icount==0){
				return true;
			}else{
				return false;
			}
		}else{
			//edit
			Long salaryStaffId=queryVOById(salaryId).getSalaryStaff();
			sql="select count(1) from m_salary salary where salary.salary_staff=? and salary.salary_date_months=? and salary.salary_staff!=?";
			int icount = super.queryForInt(sql, new Object[]{staffId,months,salaryStaffId});
			if(icount==0){
				return true;
			}else{
				return false;
			}
		}
	}
	@Override
	public BigDecimal querySalaryAttendance(String month) {
		String sql ="SELECT salary_attendance from m_salary where salary_date_months=?";
		List<BigDecimal> attendances=super.queryForList(sql, new Object[]{month}, BigDecimal.class);
		if(attendances.size()!=0){
			return attendances.get(0);
		}else{
			return new BigDecimal(21);
		}
		
	}
	/**有外键的字段必须填充,而且要一致***/
	public Salary addSalary(Salary entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public Salary modifySalary(Salary entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	
	public boolean delSalary(Salary entity)throws Exception{
		//1表删除
		delete(entity);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result<SalaryVO> queryResultByPage(Integer p,
			Map<String, String> queryMap, Integer s) {
		MysqlSqlBuilder builder = new MysqlSqlBuilder(SalaryVO.class, SQL_QUERY_VO, queryMap);
		builder.setListType(MysqlSqlBuilder.LIST_TYPE_PAGE);
		builder.setCurrPage(p);
		builder.setPageSize(s);
		builder.addCondition("salaryStaffName", "staff.staff_real_name", MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		builder.addCondition("salaryDateMonths", "obj.salary_date_months", MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		builder.addCondition("salaryAttendance", "obj.salary_attendance", MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		builder.addCondition("salaryFactAttendance", "obj.salary_fact_attendance", MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		return (Result<SalaryVO>) super.querySqlForPage(builder);
	}
	
}