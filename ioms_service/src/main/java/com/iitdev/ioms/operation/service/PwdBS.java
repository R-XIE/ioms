package com.iitdev.ioms.operation.service;

//import com.iitdev.ioms.Pwd.data.bo.Pwd;
import java.util.List;

import com.iitdev.ioms.operation.data.bo.Pwd;
import com.iitdev.ioms.operation.data.vo.PwdVO;
import com.iitdev.orm.PublicBS;

/**
 *敏感信息 SERVICE接口 
 */
public interface PwdBS extends PublicBS {
	/**通用查询方法***/
	public PwdVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public Pwd addPwd(Pwd entity) throws Exception;//有外键的字段必须填充,而且要一致
	public Pwd modifyPwd(Pwd entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delPwd(Pwd entity)throws Exception;
	
	/**
	 * 获取所有的敏感信息
	 * @return
	 */
	public List<PwdVO> queryVOListAll();
	
	/**
	 * 通过用户获取所有的敏感信息,包括公开信息和机密信息
	 * @param staff
	 * @return
	 */
	public List<PwdVO> queryVOListByStaff(Long staff);
}

