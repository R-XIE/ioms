package com.iitdev.ioms.business.service;

//import com.iitdev.ioms.business.data.bo.BusinessTravel;
import java.util.List;

import com.iitdev.ioms.business.data.bo.BusinessTravel;
import com.iitdev.ioms.business.data.vo.BusinessTravelVO;
import com.iitdev.orm.PublicBS;

/**
 *出差 SERVICE接口 
 */
public interface BusinessTravelBS extends PublicBS {
	/**通用查询方法***/
	public BusinessTravelVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public BusinessTravel addBusinessTravel(BusinessTravel entity) throws Exception;//有外键的字段必须填充,而且要一致
	public BusinessTravel modifyBusinessTravel(BusinessTravel entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delBusinessTravel(BusinessTravel entity)throws Exception;
	
	/**
	 * 所有的出差记录
	 * @see BusinessTravelAction#list()
	 * @return
	 */
	public List<BusinessTravelVO> queryVOListAll();
	
	/**
	 * 获取所有用户自己的出差记录
	 * @see ModuleAction#businessTravelList()
	 * @param staffId
	 * @return
	 */
	public List<BusinessTravelVO> queryVOListStaffAll(Long staffId);
	
	//public int queryCountAll();
	
	/**
	 * 获取用户自己的所有的出差记录数量
	 * @see MainAction#main()
	 * @param staffId
	 * @return
	 */
	public int queryCountAllByStaff(Long staffId);
	
	/**
	 * 查看用户自己的出差记录的查看页面
	 * @see ModuleAction#businessTravelView(Long)
	 * @param staffId 用户ID
	 * @param id 记录ID
	 * @return
	 */
	public BusinessTravelVO queryVOByStaff(Long staffId,Long id);
}

