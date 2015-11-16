package com.iitdev.ioms.member.service;

//import com.iitdev.ioms.member.data.bo.Salary;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.iitdev.ioms.member.data.bo.Salary;
import com.iitdev.ioms.member.data.vo.SalaryVO;
import com.iitdev.orm.PublicBS;
import com.iitdev.page.Result;

/**
 *工资条 SERVICE接口 
 */
public interface SalaryBS extends PublicBS {
	/**通用查询方法***/
	public SalaryVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public Salary addSalary(Salary entity) throws Exception;//有外键的字段必须填充,而且要一致
	public Salary modifySalary(Salary entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delSalary(Salary entity)throws Exception;
	
	/**
	 * 获取所有的薪资条
	 * @see SalaryAction#list()
	 * @return
	 */
	public List<SalaryVO> queryVOListAll();
	
	/**
	 * 获取用户的上月工资
	 * @see MainAction#main()
	 * @param staffId
	 * @return
	 */
	public BigDecimal queryCountStaff(Long staffId);
	
	/**
	 * 获取用户自己所有的工资条
	 * @see ModuleAction#salaryList()
	 * @param staffId
	 * @return
	 */
	public List<SalaryVO> queryVOListStaff(Long staffId);
	
	/**
	 * 获取用户自己所有的工资条de查看VO
	 * @see ModuleAction#salaryView(Long)
	 * @param recordId
	 * @param staffId
	 * @return
	 */
	public SalaryVO queryBeanStaff(Long recordId, Long staffId);
	
	/**
	 * 根据用户和月份获取工资条VO
	 * @see SalaryAction#input(Long, String)
	 * @param staffId
	 * @param months
	 * @return
	 */
	public SalaryVO validateBeanStaff(Long staffId,String months,String currMonth);
	
	/**
	 * 验证当月的的工资条是否已经录入
	 * @see SalaryAction#validateSalary(Long, String, Long)
	 * @param staffId
	 * @param months
	 * @param salaryId
	 * @return
	 */
	public Boolean validateBeanExsit(Long staffId,String months,Long salaryId);
	
	/**
	 * 获取当月的应出勤
	 * @see SalaryAction#salaryFact(Long, BigDecimal, String)
	 * @param month
	 * @return
	 */
	public BigDecimal querySalaryAttendance(String month);
	public Result<SalaryVO> queryResultByPage(Integer p,
			Map<String, String> queryMap, Integer s);
	
	/**
	 * 工资条分页Json转换
	 * @param request
	 * @param requestdata
	 * @return
	 */
//	public JqueryDataJson<SalaryListSearchVO> queryJqueryJson(HttpServletRequest request,JqueryDataRequest requestdata);
}

