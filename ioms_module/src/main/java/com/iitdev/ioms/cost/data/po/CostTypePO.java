package com.iitdev.ioms.cost.data.po;

import java.io.Serializable;
import java.util.List;

import com.iitdev.ioms.cost.data.vo.CostTypeVO;

/**
 * 用于下拉的辅助类
 * @author jerry
 *
 */
public class CostTypePO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String lable;//父级菜单
	private List<CostTypeVO> costTypeList;
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public List<CostTypeVO> getCostTypeList() {
		return costTypeList;
	}
	public void setCostTypeList(List<CostTypeVO> costTypeList) {
		this.costTypeList = costTypeList;
	}
}