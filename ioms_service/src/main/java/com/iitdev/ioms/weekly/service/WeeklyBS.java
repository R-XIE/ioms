package com.iitdev.ioms.weekly.service;

//import com.iitdev.ioms.weekly.data.bo.Weekly;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.iitdev.ioms.weekly.common.Globals.Mweekly;
import com.iitdev.ioms.weekly.data.bo.Weekly;
import com.iitdev.ioms.weekly.data.vo.WeeklyVO;
import com.iitdev.orm.PublicBS;
import com.iitdev.page.Result;

/**
 *个人周报 SERVICE接口 
 */
public interface WeeklyBS extends PublicBS {
	/**通用查询方法***/
	public WeeklyVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public Weekly addWeekly(Weekly entity,Mweekly state) throws Exception;//有外键的字段必须填充,而且要一致
	public Weekly modifyWeekly(Weekly entity,Long staffId) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delWeekly(Weekly entity)throws Exception;
	
	/**
	 * 获取所有的周报
	 * @see WeeklyAction#list()
	 * @return
	 */
	public List<WeeklyVO> queryVOListAll();
	
	/**
	 * 获取所有登录用户自己的周报数量
	 * @see MainAction#main()
	 * @param staffId 登录用户的ID
	 * @return
	 */
	public int queryCountAll(Long staffId);
	
	/**
	 * 获取所有的登录用户自己的周报列表
	 * @see ModuleAction#weeklyList()
	 * @param staffId
	 * @return
	 */
	public List<WeeklyVO> queryVOListStaff(Long staffId);
	
	/**
	 * 获取登录用户的周报信息查看方法 
	 * @see ModuleAction#weeklyView(Long)
	 * @param recordId
	 * @param staffId
	 * @return
	 */
	public WeeklyVO queryBeanStaff(Long recordId,Long staffId);
	
	/**
	 * 验证本周周报是否已经编写
	 * @see WeeklyAction#validate(Long, Date, Date)
	 * @param staffId 用户ID
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	@Deprecated
	public Boolean validateWeeklyStaffMonth(Long staffId,Date beginDate,Date endDate);
	public Result<WeeklyVO> queryResultByPage(Integer p,Map<String, String> queryMap, Integer s);
}

