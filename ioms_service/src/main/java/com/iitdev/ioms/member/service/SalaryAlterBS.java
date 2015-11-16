package com.iitdev.ioms.member.service;

//import com.iitdev.ioms.member.data.bo.SalaryAlter;
import java.util.List;

import com.iitdev.ioms.member.data.bo.SalaryAlter;
import com.iitdev.ioms.member.data.vo.SalaryAlterVO;
import com.iitdev.orm.PublicBS;

/**
 *薪资调整 SERVICE接口 
 */
public interface SalaryAlterBS extends PublicBS {
	/**通用查询方法***/
	public SalaryAlterVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public SalaryAlter addSalaryAlter(SalaryAlter entity) throws Exception;//有外键的字段必须填充,而且要一致
	public SalaryAlter modifySalaryAlter(SalaryAlter entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public List<SalaryAlterVO> queryVOAllList();
	public Boolean delSalaryAlter(SalaryAlter entity)throws Exception;
	
	/**
	 * 获取登录用户所有的异动信息数量
	 * @see MainAction#main()
	 * @param staffId
	 * @return
	 */
	public Integer queryCountSalaryAlterByStaff(Long staffId);
	
	/**
	 * 获取登录用户所有异动信息列表
	 * @see ModuleAction#salaryAlterList()
	 * @param staffId
	 * @return
	 */
	public List<SalaryAlterVO> queryAlterByStaff(Long staffId);
	
	/**
	 * 获取登录用户的异动信息查看VO
	 * @see ModuleAction#salaryAlterView(Long)
	 * @param staffId
	 * @param alterId
	 * @return
	 */
	public SalaryAlterVO queryAlterByStaff(Long staffId,Long alterId);
	
	/**
	 * 验证异动信息是否存在,若存在返回false否则返回true
	 * @see SalaryAlterAction#validateSalary(Long, String)
	 * @param staffId
	 * @param alterDate
	 * @return
	 */
	public Boolean validateBeanExsit(Long staffId, String alterDate,Long alterId);
}

