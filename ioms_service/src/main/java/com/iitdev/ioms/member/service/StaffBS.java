package com.iitdev.ioms.member.service;

//import com.iitdev.ioms.member.data.bo.Staff;

import java.util.List;

import com.iitdev.data.LoginInfo;
import com.iitdev.ioms.member.data.bo.Staff;
import com.iitdev.ioms.member.data.vo.StaffVO;
import com.iitdev.orm.PublicBS;

/**
 * Staff service interface
 */
public interface StaffBS extends PublicBS {
	/**common query method***/
	public StaffVO queryVOById(Long id);	
	/**common add\modify method***/
	public Staff addStaff(Staff entity) throws Exception;
	public Staff modifyStaff(Staff entity) throws Exception;
	/**delMethod**/
	public Boolean delStaff(Staff entity)throws Exception;
	/**loginMethodValidate**/
	/**
	 * @param isLeave [true离职,false在职,null全部]
	 * @return
	 */
	public List<StaffVO> queryVOAllList(Boolean isLeave);
	/**所有有效的用户**/
	public List<StaffVO> queryVOAllExistList();
	/**
	 * 验证用户名和密码是否匹配 返回info
	 * @see IndexAction#validateLogin(String, String)
	 * @param loginName
	 * @param pwd
	 * @return
	 */
	public LoginInfo validatePwd(String loginName,String pwd);
	
	/**
	 * 验证登录名是否重复
	 * @see 
	 * @param loginName
	 * @return
	 */
	@Deprecated
	public Boolean validateLoginName(String loginName);
	
	/**
	 * 验证用户名是否重复
	 * @see StaffAction#validate(String)
	 * @param loginName
	 * @return
	 */
	public Boolean validateInputLoginName(String loginName);
	
	public Integer queryStaffIdDef();
	
	/**
	 * 根据部门获取用户信息
	 * @param branchId
	 * @return
	 */
	public List<StaffVO> queryStaffByBranch(Long branchId); 
}

