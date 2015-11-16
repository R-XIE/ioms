package com.iitdev.ioms.cost.data.vo;
import com.iitdev.ioms.cost.data.bo.Cost;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

/**
 * Cost
 *
 */
public class CostVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from c_cost obj ";
	public static final String QUERY_SQL_COUNT = "select count(1) from c_cost obj" ;
	private Long costId;//costId
	private String costDetail;//costName
	private Long costType;//costType
	private BigDecimal costMoney;//costMoney
	private String costDesc;//costDesc
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date costTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date costCreateTime;
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getCostId() {
		return this.costId;
	}
	public void setCostId(Long value) {
		this.costId = value;
	}
	public Long getCostType() {
		return this.costType;
	}
	public void setCostType(Long value) {
		this.costType = value;
	}
	public BigDecimal getCostMoney() {
		return this.costMoney;
	}
	public void setCostMoney(BigDecimal value) {
		this.costMoney = value;
	}
	public String getCostDesc() {
		return this.costDesc;
	}
	public void setCostDesc(String value) {
		this.costDesc = value;
	}
	public Date getCostTime() {
		return this.costTime;
	}
	public void setCostTime(Date value) {
		this.costTime = value;
	}
	public Date getCostCreateTime() {
		return this.costCreateTime;
	}
	public void setCostCreateTime(Date value) {
		this.costCreateTime = value;
	}
	
	
	//////////////////////////////////////////////////////
	public CostVO(){}

	public CostVO(Cost cost){
		BeanUtils.copyProperties(cost,this);
	}
	public void copyValueTo(Cost bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Cost cloneBO(){
		Cost bo = new Cost();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Cost> cloneBOList(List<CostVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Cost>(0);
		List<Cost> result = new ArrayList<Cost>(vos.size());
		for(CostVO vo: vos){
			Cost bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
	public String getCostDetail() {
		return costDetail;
	}
	public void setCostDetail(String costDetail) {
		this.costDetail = costDetail;
	}
}