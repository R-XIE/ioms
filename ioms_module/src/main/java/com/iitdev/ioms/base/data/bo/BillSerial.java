package com.iitdev.ioms.base.data.bo;
import java.io.Serializable;
import javax.persistence.*;

/**
 * BillSerial
 */
@Table(name = "b_bill_serial")
public class BillSerial implements Serializable{
	private static final long serialVersionUID = 1L;
	public BillSerial(){}
	//属性
	private Long billSerialId;//序列ID
	private Long billType;//序列类型（外键）
	private String codeItem;//序列前缀
	private Long beginSerialNo;//开始序列值
	private Long nextSerialNo;//下个序列值
	private Long maxSerialNo;//最大序列值
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_serial_id", unique = true, nullable = false,length = 10)
	public Long getBillSerialId() {
		return this.billSerialId;
	}
	
	public void setBillSerialId(Long BillSerialId) {
		this.billSerialId = BillSerialId;
	}
	
	@Column(name = "bill_type",unique = false,nullable = true,length = 10)
	public Long getBillType() {
		return this.billType;
	}
	
	public void setBillType(Long value) {
		this.billType = value;
	}
	
	@Column(name = "codeItem",unique = false,nullable = true,length = 100)
	public String getCodeItem() {
		return this.codeItem;
	}
	
	public void setCodeItem(String value) {
		this.codeItem = value;
	}
	
	@Column(name = "beginSerialNo",unique = false,nullable = true,length = 10)
	public Long getBeginSerialNo() {
		return this.beginSerialNo;
	}
	
	public void setBeginSerialNo(Long value) {
		this.beginSerialNo = value;
	}
	
	@Column(name = "nextSerialNo",unique = false,nullable = true,length = 10)
	public Long getNextSerialNo() {
		return this.nextSerialNo;
	}
	
	public void setNextSerialNo(Long value) {
		this.nextSerialNo = value;
	}
	
	@Column(name = "maxSerialNo",unique = false,nullable = true,length = 10)
	public Long getMaxSerialNo() {
		return this.maxSerialNo;
	}
	
	public void setMaxSerialNo(Long value) {
		this.maxSerialNo = value;
	}
	
	
	
}
