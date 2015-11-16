package com.iitdev.ioms.cost.data.vo;
import com.iitdev.ioms.cost.data.bo.CostType;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * CostType
 *
 */
public class CostTypeVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from c_cost_type obj" ;
	public static final String QUERY_SQL_COUNT = "select count(1) from c_cost_type obj" ;
	private Long costTypeId;//费用类别
	private String costTypeName;//名称
	private Long costTypeSuper;
	private Long costTypeInex;//收入还是支出？
	private String costTypeDesc;//costTypeDesc
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getCostTypeId() {
		return this.costTypeId;
	}
	public void setCostTypeId(Long value) {
		this.costTypeId = value;
	}
	public String getCostTypeName() {
		return this.costTypeName;
	}
	public void setCostTypeName(String value) {
		this.costTypeName = value;
	}
	public Long getCostTypeSuper() {
		return costTypeSuper;
	}
	public void setCostTypeSuper(Long costTypeSuper) {
		this.costTypeSuper = costTypeSuper;
	}
	public Long getCostTypeInex() {
		return this.costTypeInex;
	}
	public void setCostTypeInex(Long value) {
		this.costTypeInex = value;
	}
	public String getCostTypeDesc() {
		return this.costTypeDesc;
	}
	public void setCostTypeDesc(String value) {
		this.costTypeDesc = value;
	}
	
	
	//////////////////////////////////////////////////////
	public CostTypeVO(){}

	public CostTypeVO(CostType costType){
		BeanUtils.copyProperties(costType,this);
	}
	public void copyValueTo(CostType bo){
		BeanUtils.copyProperties(this,bo);
	}
	public CostType cloneBO(){
		CostType bo = new CostType();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<CostType> cloneBOList(List<CostTypeVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<CostType>(0);
		List<CostType> result = new ArrayList<CostType>(vos.size());
		for(CostTypeVO vo: vos){
			CostType bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}