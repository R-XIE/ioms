package com.iitdev.ioms.business.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iitdev.ioms.business.data.bo.BusinessTravel;
import com.iitdev.ioms.business.data.vo.BusinessTravelVO;
import com.iitdev.ioms.business.service.BusinessTravelBS;
import com.iitdev.orm.PublicBSImpl;

/**
 *出差 SERVICE接口 实现类
 *
 */
@Service("businessTravelBS")
public class BusinessTravelBSImpl extends PublicBSImpl implements BusinessTravelBS{
	public  final String SQL_QUERY_VO =  BusinessTravelVO.QUERY_SQL;
	
	public BusinessTravelVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" and obj.business_travel_id  = ? ";
		return super.queryForBean(BusinessTravelVO.class,sql ,new Object[]{id});
	}
	public List<BusinessTravelVO> queryVOListAll(){
		return super.queryForBeanList(BusinessTravelVO.class,SQL_QUERY_VO ,new Object[]{});
	}
	public List<BusinessTravelVO> queryVOListStaffAll(Long staffId){
		return super.queryForBeanList(BusinessTravelVO.class,SQL_QUERY_VO+" where travel_staff=?", new Object[]{staffId});
	}
	@Override
	public BusinessTravelVO queryVOByStaff(Long staffId,Long id) {
		// TODO Auto-generated method stub
		String sql = SQL_QUERY_VO+
				" and obj.business_travel_id  = ? and obj.travel_staff=? ";
		return super.queryForBean(BusinessTravelVO.class,sql ,new Object[]{id,staffId});
	}
//	public int queryCountAll() {
//		return super.queryForInt(BusinessTravelVO.QUERY_SQL_COUNT, new Object[]{});
//	}
	public int queryCountAllByStaff(Long staffId){
		return super.queryForInt(BusinessTravelVO.QUERY_SQL_COUNT+" where travel_staff=?", new Object[]{staffId});
	}
	/**有外键的字段必须填充,而且要一致***/
	public BusinessTravel addBusinessTravel(BusinessTravel entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public BusinessTravel modifyBusinessTravel(BusinessTravel entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	
	public boolean delBusinessTravel(BusinessTravel entity)throws Exception{
		//1表删除
		delete(entity);
		return true;
	}

}