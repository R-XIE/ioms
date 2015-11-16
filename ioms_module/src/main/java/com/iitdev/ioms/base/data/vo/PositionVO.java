package com.iitdev.ioms.base.data.vo;
import com.iitdev.ioms.base.data.bo.Position;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


/**
 * Position
 *
 */
public class PositionVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from b_position obj" ;
	private Long positionId;//主键ID
	private Long branchId;//部门
	private String positionName;//岗位名称
	private String positionDesc;//岗位描述
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getPositionId() {
		return this.positionId;
	}
	public void setPositionId(Long value) {
		this.positionId = value;
	}
	public Long getBranchId() {
		return this.branchId;
	}
	
	public void setBranchId(Long BranchId) {
		this.branchId = BranchId;
	}
	public String getPositionName() {
		return this.positionName;
	}
	public void setPositionName(String value) {
		this.positionName = value;
	}
	public String getPositionDesc() {
		return this.positionDesc;
	}
	public void setPositionDesc(String value) {
		this.positionDesc = value;
	}
	
	
	//////////////////////////////////////////////////////
	public PositionVO(){}

	public PositionVO(Position position){
		BeanUtils.copyProperties(position,this);
	}
	public void copyValueTo(Position bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Position cloneBO(){
		Position bo = new Position();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Position> cloneBOList(List<PositionVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Position>(0);
		List<Position> result = new ArrayList<Position>(vos.size());
		for(PositionVO vo: vos){
			Position bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}