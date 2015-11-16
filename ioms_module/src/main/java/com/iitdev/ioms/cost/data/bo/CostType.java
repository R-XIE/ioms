package com.iitdev.ioms.cost.data.bo;
import java.io.Serializable;
import javax.persistence.*;

/**
 * CostType
 */
@Table(name = "c_cost_type")
public class CostType implements Serializable{
	private static final long serialVersionUID = 1L;
	public CostType(){}
	//属性
	private Long costTypeId;//费用类别
	private String costTypeName;//名称
	private Long costTypeSuper;//costTypeSuper
	private Long costTypeInex;//收入还是支出？1收入0支出
	private String costTypeDesc;//costTypeDesc
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cost_type_id", unique = true, nullable = false,length = 10)
	public Long getCostTypeId() {
		return this.costTypeId;
	}
	
	public void setCostTypeId(Long CostTypeId) {
		this.costTypeId = CostTypeId;
	}
	
	@Column(name = "cost_type_name",unique = false,nullable = false,length = 255)
	public String getCostTypeName() {
		return this.costTypeName;
	}
	
	public void setCostTypeName(String value) {
		this.costTypeName = value;
	}
	
	@Column(name = "cost_type_super",unique = false,nullable = true,length = 19)
	public Long getCostTypeSuper() {
		return this.costTypeSuper;
	}
	
	public void setCostTypeSuper(Long value) {
		this.costTypeSuper = value;
	}
	
	@Column(name = "cost_type_inex",unique = false,nullable = false,length = 5)
	public Long getCostTypeInex() {
		return this.costTypeInex;
	}
	
	public void setCostTypeInex(Long value) {
		this.costTypeInex = value;
	}
	
	@Column(name = "cost_type_desc",unique = false,nullable = true,length = 255)
	public String getCostTypeDesc() {
		return this.costTypeDesc;
	}
	
	public void setCostTypeDesc(String value) {
		this.costTypeDesc = value;
	}
	
	
}
