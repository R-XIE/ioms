package com.iitdev.ioms.base.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iitdev.ioms.base.data.bo.Position;
import com.iitdev.ioms.base.data.vo.PositionVO;
import com.iitdev.ioms.base.service.PositionBS;
import com.iitdev.ioms.member.data.vo.StaffVO;
import com.iitdev.ioms.member.service.StaffBS;
import com.iitdev.orm.PublicBSImpl;


/**
 *职位 SERVICE接口 实现类
 *
 */
@Service("positionBS")
public class PositionBSImpl extends PublicBSImpl implements PositionBS{
	public  final String SQL_QUERY_VO =  PositionVO.QUERY_SQL;
	@Autowired
	private StaffBS staffBS;
	public PositionVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" where obj.position_id  = ? ";
		return super.queryForBean(PositionVO.class,sql ,new Object[]{id});
	}
	public PositionVO queryPositionByStaff(Long staffId){
		StaffVO staff=staffBS.queryVOById(staffId);
		return queryVOById(staff.getPositionId());
	}
	
	/**有外键的字段必须填充,而且要一致***/
	public Position addPosition(Position entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public Position modifyPosition(Position entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	public boolean delPosition(Position entity)throws Exception{
		String icountStaffSQL="select count(1) from m_staff staff where staff.position_id=?";
		int icount= super.queryForInt(icountStaffSQL, new Object[]{entity.getPositionId()});
		if(icount!=0){
			return false;
		}else{
			delete(entity);
			return true;
		}
	}
	@Override
	public List<PositionVO> queryVOAllList() {
		// TODO Auto-generated method stub
		return super.queryForBeanList(PositionVO.class, PositionVO.QUERY_SQL, new Object[]{});
	}
}