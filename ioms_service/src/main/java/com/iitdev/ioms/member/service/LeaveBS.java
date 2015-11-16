package com.iitdev.ioms.member.service;

//import com.iitdev.ioms.member.data.bo.Leave;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.iitdev.ioms.member.data.bo.Leave;
import com.iitdev.ioms.member.data.vo.LeaveVO;
import com.iitdev.orm.PublicBS;
import com.iitdev.page.Result;

/**
 * 请假 SERVICE接口
 */
public interface LeaveBS extends PublicBS {
	/** 通用查询方法 ***/
	public LeaveVO queryVOById(Long id);

	/** 通用持久化方法(添加和修改) ***/
	public Leave addLeave(Leave entity) throws Exception;// 有外键的字段必须填充,而且要一致

	public Leave modifyLeave(Leave entity) throws Exception;// 有外键的字段必须填充,而且要一致

	/** 删除的方法 **/
	public Boolean delLeave(Leave entity) throws Exception;

	public List<LeaveVO> queryVOAllList();

	/**
	 * 获取用户自己的请假数目
	 * 
	 * @see MainAction#main()
	 * @param staffId
	 * @return
	 */
	public Integer queryCountLeaveByStaff(Long staffId);

	/**
	 * 获取用户自己所有的请假记录
	 * 
	 * @see ModuleAction#leaveList()
	 * @param staffId
	 * @return
	 */
	public List<LeaveVO> queryLeaveByStaff(Long staffId);

	/**
	 * 用户自己的请假记录的查看
	 * 
	 * @see ModuleAction#leaveView(Long)
	 * @param staffId
	 * @param leaveId
	 * @return
	 */
	public LeaveVO queryLeaveByStaff(Long staffId, Long leaveId);

	/**
	 * 通过用户和月份获取当月用户请假总天数
	 * 
	 * @see SalaryAction#input(Long, String)
	 * @see SalaryAction#salaryFact(Long, BigDecimal, BigDecimal, String)
	 * @param staff
	 * @param months
	 * @return
	 */
	public BigDecimal validateListByStaffMonth(Long staff, String months);

	public Result<LeaveVO> queryResultByPage(Integer p,
			Map<String, String> queryMap, Integer s);
}