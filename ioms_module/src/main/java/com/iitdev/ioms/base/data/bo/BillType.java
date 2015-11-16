package com.iitdev.ioms.base.data.bo;
import java.io.Serializable;
import javax.persistence.*;

/**
 * BillType
 */
@Table(name = "b_bill_type")
public class BillType implements Serializable{
	private static final long serialVersionUID = 1L;
	public BillType(){}
	//属性
	private Long billTypeId;//单据类型ID
	private String billTypeCode;//单据类型编码
	private String billPrefix;//前缀
	private String billName;//单据类型名字
	private Long state;//状态 0可用 -1 不可用
	private Long timestampFlag;//是否存在时间0有 -1 没有
	private String timestampFormat;//时间格式
	private Long serialFlag;//是否有序列递增
	private Long serialLen;//序列长度
	private Long splitFlag;//是否要符号分割
	private String split;//分隔符
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_type_id", unique = true, nullable = false,length = 10)
	public Long getBillTypeId() {
		return this.billTypeId;
	}
	
	public void setBillTypeId(Long BillTypeId) {
		this.billTypeId = BillTypeId;
	}
	
	@Column(name = "bill_type_code",unique = false,nullable = true,length = 255)
	public String getBillTypeCode() {
		return this.billTypeCode;
	}
	
	public void setBillTypeCode(String value) {
		this.billTypeCode = value;
	}
	
	@Column(name = "bill_prefix",unique = false,nullable = true,length = 255)
	public String getBillPrefix() {
		return this.billPrefix;
	}
	
	public void setBillPrefix(String value) {
		this.billPrefix = value;
	}
	
	@Column(name = "bill_name",unique = false,nullable = true,length = 255)
	public String getBillName() {
		return this.billName;
	}
	
	public void setBillName(String value) {
		this.billName = value;
	}
	
	@Column(name = "state",unique = false,nullable = true,length = 10)
	public Long getState() {
		return this.state;
	}
	
	public void setState(Long value) {
		this.state = value;
	}
	
	@Column(name = "timestampFlag",unique = false,nullable = true,length = 10)
	public Long getTimestampFlag() {
		return this.timestampFlag;
	}
	
	public void setTimestampFlag(Long value) {
		this.timestampFlag = value;
	}
	
	@Column(name = "timestampFormat",unique = false,nullable = true,length = 200)
	public String getTimestampFormat() {
		return this.timestampFormat;
	}
	
	public void setTimestampFormat(String value) {
		this.timestampFormat = value;
	}
	
	@Column(name = "serialFlag",unique = false,nullable = true,length = 10)
	public Long getSerialFlag() {
		return this.serialFlag;
	}
	
	public void setSerialFlag(Long value) {
		this.serialFlag = value;
	}
	
	@Column(name = "serialLen",unique = false,nullable = true,length = 10)
	public Long getSerialLen() {
		return this.serialLen;
	}
	
	public void setSerialLen(Long value) {
		this.serialLen = value;
	}
	
	@Column(name = "splitFlag",unique = false,nullable = true,length = 10)
	public Long getSplitFlag() {
		return this.splitFlag;
	}
	
	public void setSplitFlag(Long value) {
		this.splitFlag = value;
	}
	
	@Column(name = "split",unique = false,nullable = true,length = 10)
	public String getSplit() {
		return this.split;
	}
	
	public void setSplit(String value) {
		this.split = value;
	}
	
	
	
}
