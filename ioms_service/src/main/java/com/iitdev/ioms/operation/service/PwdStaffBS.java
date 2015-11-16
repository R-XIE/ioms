package com.iitdev.ioms.operation.service;

//import com.iitdev.ioms.operation.data.bo.PwdStaff;
import java.util.List;

import com.iitdev.ioms.operation.data.bo.PwdStaff;
import com.iitdev.ioms.operation.data.vo.PwdStaffVO;
import com.iitdev.orm.PublicBS;

/**
 *敏感信息与用户中间表 SERVICE接口 
 */
public interface PwdStaffBS extends PublicBS {
	/**通用查询方法***/
	public PwdStaffVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public PwdStaff addPwdStaff(PwdStaff entity) throws Exception;//有外键的字段必须填充,而且要一致
	public PwdStaff modifyPwdStaff(PwdStaff entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delPwdStaff(PwdStaff entity)throws Exception;
	public List<PwdStaffVO> queryVOListAll();
	
	/**
	 * 根据敏感信息删除所有的中间表信息
	 * @param pwdId
	 * @return
	 */
	public boolean delPwdStaffByPwd(Long pwdId);
	
	/**
	 * 根据用户和敏感信息删除中间表数据
	 * @param pwdId
	 * @param staff
	 * @return
	 */
	public boolean delPwdStaffByPwdStaff(Long pwdId,Long staff);
	
	/**
	 * 根据用户和敏感信息查询中间表数据
	 * @param pwdId
	 * @param staff
	 * @return
	 */
	public PwdStaffVO queryPwdStaffByPwdStaff(Long pwdId,Long staff);
}