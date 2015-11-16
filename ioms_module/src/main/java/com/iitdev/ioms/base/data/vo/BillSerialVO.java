package com.iitdev.ioms.base.data.vo;
import com.iitdev.ioms.base.data.bo.BillSerial;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * BillSerial
 *
 */
public class BillSerialVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from m_bill_serial obj" ;
	private Long billSerialId;//序列ID
	private Long billType;//序列类型（外键）
	private String codeItem;//序列前缀
	private Long beginSerialNo;//开始序列值
	private Long nextSerialNo;//下个序列值
	private Long maxSerialNo;//最大序列值
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getBillSerialId() {
		return this.billSerialId;
	}
	public void setBillSerialId(Long value) {
		this.billSerialId = value;
	}
	public Long getBillType() {
		return this.billType;
	}
	public void setBillType(Long value) {
		this.billType = value;
	}
	public String getCodeItem() {
		return this.codeItem;
	}
	public void setCodeItem(String value) {
		this.codeItem = value;
	}
	public Long getBeginSerialNo() {
		return this.beginSerialNo;
	}
	public void setBeginSerialNo(Long value) {
		this.beginSerialNo = value;
	}
	public Long getNextSerialNo() {
		return this.nextSerialNo;
	}
	public void setNextSerialNo(Long value) {
		this.nextSerialNo = value;
	}
	public Long getMaxSerialNo() {
		return this.maxSerialNo;
	}
	public void setMaxSerialNo(Long value) {
		this.maxSerialNo = value;
	}
	
	
	//////////////////////////////////////////////////////
	public BillSerialVO(){}

	public BillSerialVO(BillSerial billSerial){
		BeanUtils.copyProperties(billSerial,this);
	}
	public void copyValueTo(BillSerial bo){
		BeanUtils.copyProperties(this,bo);
	}
	public BillSerial cloneBO(){
		BillSerial bo = new BillSerial();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<BillSerial> cloneBOList(List<BillSerialVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<BillSerial>(0);
		List<BillSerial> result = new ArrayList<BillSerial>(vos.size());
		for(BillSerialVO vo: vos){
			BillSerial bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}