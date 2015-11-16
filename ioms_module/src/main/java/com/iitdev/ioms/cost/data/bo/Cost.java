package com.iitdev.ioms.cost.data.bo;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * Cost
 */
@Table(name = "c_cost")
public class Cost implements Serializable{
	private static final long serialVersionUID = 1L;
	public Cost(){}
	//属性
	private Long costId;//costId
	private String costDetail;//costName
	private Long costType;//costType
	private BigDecimal costMoney;//costMoney
	private String costDesc;//costDesc
	private Date costTime;//costTime
	private Date costCreateTime;//costCreateTime
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cost_id", unique = true, nullable = false,length = 19)
	public Long getCostId() {
		return this.costId;
	}
	
	public void setCostId(Long CostId) {
		this.costId = CostId;
	}
	
	@Column(name = "cost_detail",unique = false,nullable = true,length = 255)
	public String getCostDetail() {
		return costDetail;
	}

	public void setCostDetail(String costDetail) {
		this.costDetail = costDetail;
	}

	@Column(name = "cost_type",unique = false,nullable = false,length = 19)
	public Long getCostType() {
		return this.costType;
	}
	
	public void setCostType(Long value) {
		this.costType = value;
	}
	
	@Column(name = "cost_money",precision=20,scale = 2,unique = false,nullable = false)
	public BigDecimal getCostMoney() {
		return this.costMoney;
	}
	
	public void setCostMoney(BigDecimal value) {
		this.costMoney = value;
	}
	
	@Column(name = "cost_desc",unique = false,nullable = true,length = 255)
	public String getCostDesc() {
		return this.costDesc;
	}
	
	public void setCostDesc(String value) {
		this.costDesc = value;
	}
	
	@Column(name = "cost_time",unique = false,nullable = false,length = 0)
	public Date getCostTime() {
		return this.costTime;
	}
	
	public void setCostTime(Date value) {
		this.costTime = value;
	}
	
	@Column(name = "cost_create_time",unique = false,nullable = false,length = 0)
	public Date getCostCreateTime() {
		return this.costCreateTime;
	}
	
	public void setCostCreateTime(Date value) {
		this.costCreateTime = value;
	}
	
	
	
}
