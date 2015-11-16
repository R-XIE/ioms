package com.iitdev.ioms.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iitdev.ioms.base.data.bo.Branch;
import com.iitdev.ioms.base.data.vo.BranchVO;
import com.iitdev.ioms.base.data.vo.PositionVO;
import com.iitdev.ioms.base.service.BranchBS;
import com.iitdev.ioms.base.service.PositionBS;
import com.iitdev.ioms.member.data.vo.StaffVO;
import com.iitdev.ioms.member.service.StaffBS;
import com.iitdev.orm.PublicBSImpl;

/**
 *部门 SERVICE接口 实现类
 *
 */
@Service("branchBS")
public class BranchBSImpl extends PublicBSImpl implements BranchBS{
	public  final String SQL_QUERY_VO =  BranchVO.QUERY_SQL;
	@Autowired
	private StaffBS staffBS;
	@Autowired
	private PositionBS positionBS;
	public BranchVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" where obj.branch_id  = ? ";
		return super.queryForBean(BranchVO.class,sql ,new Object[]{id});
	}
	
	@Override
	public BranchVO queryBranchByStaff(Long staffId) {
		StaffVO vo = staffBS.queryVOById(staffId);
		PositionVO position=positionBS.queryVOById(vo.getPositionId());
		return queryVOById(position.getBranchId());
	}
	
	
	/**有外键的字段必须填充,而且要一致***/
	public Branch addBranch(Branch entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public Branch modifyBranch(Branch entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	public boolean delBranch(Branch entity)throws Exception{
		//1表删除
		String icountStaffSQL="SELECT count(1) from b_position position  "
				+ "INNER JOIN b_branch branch on branch.branch_id=position.branch_id "
				+ "WHERE branch.branch_id=?";
		int icount= super.queryForInt(icountStaffSQL, new Object[]{entity.getBranchId()});
		if(icount!=0){
			return false;
		}else{
			delete(entity);
			return true;
		}
	}

	@Override
	public List<BranchVO> queryVOAllList() {
		// TODO Auto-generated method stub
		return super.queryForBeanList(BranchVO.class, BranchVO.QUERY_SQL, new Object[]{});
	}
}