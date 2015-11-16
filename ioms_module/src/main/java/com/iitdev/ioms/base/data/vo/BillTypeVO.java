package com.iitdev.ioms.base.data.vo;
import com.iitdev.ioms.base.data.bo.BillType;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * BillType
 *
 */
public class BillTypeVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from b_bill_type obj where obj.state=0" ;
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
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getBillTypeId() {
		return this.billTypeId;
	}
	public void setBillTypeId(Long value) {
		this.billTypeId = value;
	}
	public String getBillTypeCode() {
		return this.billTypeCode;
	}
	public void setBillTypeCode(String value) {
		this.billTypeCode = value;
	}
	public String getBillPrefix() {
		return this.billPrefix;
	}
	public void setBillPrefix(String value) {
		this.billPrefix = value;
	}
	public String getBillName() {
		return this.billName;
	}
	public void setBillName(String value) {
		this.billName = value;
	}
	public Long getState() {
		return this.state;
	}
	public void setState(Long value) {
		this.state = value;
	}
	public Long getTimestampFlag() {
		return this.timestampFlag;
	}
	public void setTimestampFlag(Long value) {
		this.timestampFlag = value;
	}
	public String getTimestampFormat() {
		return this.timestampFormat;
	}
	public void setTimestampFormat(String value) {
		this.timestampFormat = value;
	}
	public Long getSerialFlag() {
		return this.serialFlag;
	}
	public void setSerialFlag(Long value) {
		this.serialFlag = value;
	}
	public Long getSerialLen() {
		return this.serialLen;
	}
	public void setSerialLen(Long value) {
		this.serialLen = value;
	}
	public Long getSplitFlag() {
		return this.splitFlag;
	}
	public void setSplitFlag(Long value) {
		this.splitFlag = value;
	}
	public String getSplit() {
		return this.split;
	}
	public void setSplit(String value) {
		this.split = value;
	}
	
	
	//////////////////////////////////////////////////////
	public BillTypeVO(){}

	public BillTypeVO(BillType billType){
		BeanUtils.copyProperties(billType,this);
	}
	public void copyValueTo(BillType bo){
		BeanUtils.copyProperties(this,bo);
	}
	public BillType cloneBO(){
		BillType bo = new BillType();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<BillType> cloneBOList(List<BillTypeVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<BillType>(0);
		List<BillType> result = new ArrayList<BillType>(vos.size());
		for(BillTypeVO vo: vos){
			BillType bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}