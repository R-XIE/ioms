package com.iitdev.ioms.cost.data.po;

import java.util.Map;

import com.iitdev.data.QueryFormBean;
import com.iitdev.utils.BeanUtils;


/**
 * Cost
 * 
 */
public class CostQueryForm extends QueryFormBean {
	private static final long serialVersionUID = 1L;
	// ///////////////////////////////SQL ///////////////////
	private Long costType;// costType
	private String costMoney;// costMoney
	private String costDesc;// costDesc
	private String costTimeBE;
	private String costCreateTimeBE;

	// ///////////////////////getter and setter //////////////////////////
	public Long getCostType() {
		return this.costType;
	}

	public void setCostType(Long value) {
		this.costType = value;
	}

	public String getCostMoney() {
		return this.costMoney;
	}

	public void setCostMoney(String value) {
		this.costMoney = value;
	}

	public String getCostDesc() {
		return this.costDesc;
	}

	public void setCostDesc(String value) {
		this.costDesc = value;
	}

	// ////////////////////////////////////////////////////
	public CostQueryForm() {
	}

	public Map<String, String> buildQueryMap() {
		return BeanUtils.getQueryMap(this);
	}

	public String getCostTimeBE() {
		return costTimeBE;
	}

	public void setCostTimeBE(String costTimeBE) {
		this.costTimeBE = costTimeBE;
	}

	public String getCostCreateTimeBE() {
		return costCreateTimeBE;
	}

	public void setCostCreateTimeBE(String costCreateTimeBE) {
		this.costCreateTimeBE = costCreateTimeBE;
	}

}