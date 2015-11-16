package com.iitdev.ioms.business.data.vo;
import com.iitdev.ioms.business.data.bo.BusinessTravel;
import com.iitdev.utils.Util;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

/**
 * BusinessTravel
 *
 */
public class BusinessTravelVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.*,staff.staff_real_name as travel_staff_name from bu_business_travel obj "
			+ " INNER JOIN m_staff staff on staff_id=obj.travel_staff " ;
	public static final String QUERY_SQL_COUNT = "select count(1) from bu_business_travel obj" ;
	private Long businessTravelId;//businessTravelId
	private Long travelStaff;//请假人
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date travelStartTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date travelEndTime;
	private String travelAddress;//地址
	private String travelGoal;//目的
	private String travelToGether;//同行人
	private String travelVehicle;//交通工具
	private Long travelInvoiceNum;//travelInvoiceNum
	private BigDecimal travelTicket;//机票费
	private BigDecimal travelFare;//火车费
	private BigDecimal travelFares;//车船费
	private BigDecimal travelTransport;//市内交通
	private BigDecimal travelAllowance;//出差补助
	private BigDecimal travelAccommodation;//住宿费
	private BigDecimal travelOther;//travelOther
	private String travelTrip;//行程
	//
	//
	private String travelStaffName;
	private BigDecimal travelFaresCount;//
	/////////////////////////getter and setter //////////////////////////
	public Long getBusinessTravelId() {
		return this.businessTravelId;
	}
	public void setBusinessTravelId(Long value) {
		this.businessTravelId = value;
	}
	public Long getTravelStaff() {
		return this.travelStaff;
	}
	public void setTravelStaff(Long value) {
		this.travelStaff = value;
	}
	public Date getTravelStartTime() {
		return this.travelStartTime;
	}
	public void setTravelStartTime(Date value) {
		this.travelStartTime = value;
	}
	public Date getTravelEndTime() {
		return this.travelEndTime;
	}
	public void setTravelEndTime(Date value) {
		this.travelEndTime = value;
	}
	public String getTravelAddress() {
		return this.travelAddress;
	}
	public void setTravelAddress(String value) {
		this.travelAddress = value;
	}
	public String getTravelGoal() {
		return this.travelGoal;
	}
	public void setTravelGoal(String value) {
		this.travelGoal = value;
	}
	public String getTravelToGether() {
		if(Util.isNull(this.travelToGether)){
			return "无";
		}else
			return this.travelToGether;
	}
	public void setTravelToGether(String value) {
		this.travelToGether = value;
	}
	public String getTravelVehicle() {
		return this.travelVehicle;
	}
	public void setTravelVehicle(String value) {
		this.travelVehicle = value;
	}
	public Long getTravelInvoiceNum() {
		if(this.travelInvoiceNum==null)
			this.travelInvoiceNum=new Long(0);
		return this.travelInvoiceNum;
	}
	public void setTravelInvoiceNum(Long value) {
		this.travelInvoiceNum = value;
	}
	public BigDecimal getTravelTicket() {
		if(this.travelTicket==null)
			this.travelTicket=new BigDecimal(0);
		return this.travelTicket;
	}
	public void setTravelTicket(BigDecimal value) {
		this.travelTicket = value;
	}
	public BigDecimal getTravelFare() {
		if(this.travelFare==null)
			this.travelFare=new BigDecimal(0);
		return this.travelFare;
	}
	public void setTravelFare(BigDecimal value) {
		this.travelFare = value;
	}
	public BigDecimal getTravelFares() {
		if(this.travelFares==null)
			this.travelFares=new BigDecimal(0);
		return this.travelFares;
	}
	public void setTravelFares(BigDecimal value) {
		this.travelFares = value;
	}
	public BigDecimal getTravelTransport() {
		if(this.travelTransport==null)
			this.travelTransport=new BigDecimal(0);
		return this.travelTransport;
	}
	public void setTravelTransport(BigDecimal value) {
		this.travelTransport = value;
	}
	public BigDecimal getTravelAllowance() {
		if(this.travelAllowance==null)
			this.travelAllowance=new BigDecimal(0);
		return this.travelAllowance;
	}
	public void setTravelAllowance(BigDecimal value) {
		this.travelAllowance = value;
	}
	public BigDecimal getTravelAccommodation() {
		if(this.travelAccommodation==null)
			this.travelAccommodation=new BigDecimal(0);
		return this.travelAccommodation;
	}
	public void setTravelAccommodation(BigDecimal value) {
		this.travelAccommodation = value;
	}
	public BigDecimal getTravelOther() {
		if(this.travelOther==null)
			this.travelOther=new BigDecimal(0);
		return this.travelOther;
	}
	public void setTravelOther(BigDecimal value) {
		this.travelOther = value;
	}
	public String getTravelTrip() {
		return this.travelTrip;
	}
	public void setTravelTrip(String value) {
		this.travelTrip = value;
	}
	
	
	public String getTravelStaffName() {
		return travelStaffName;
	}
	public void setTravelStaffName(String travelStaffName) {
		this.travelStaffName = travelStaffName;
	}
	//////////////////////////////////////////////////////
	public BusinessTravelVO(){}

	public BusinessTravelVO(BusinessTravel businessTravel){
		BeanUtils.copyProperties(businessTravel,this);
	}
	public void copyValueTo(BusinessTravel bo){
		BeanUtils.copyProperties(this,bo);
	}
	public BusinessTravel cloneBO(){
		BusinessTravel bo = new BusinessTravel();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<BusinessTravel> cloneBOList(List<BusinessTravelVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<BusinessTravel>(0);
		List<BusinessTravel> result = new ArrayList<BusinessTravel>(vos.size());
		for(BusinessTravelVO vo: vos){
			BusinessTravel bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
	public BigDecimal getTravelFaresCount() {
		this.travelFaresCount=travelTicket.add(getTravelFare()).add(getTravelFares())
				.add(getTravelTransport()).add(getTravelAllowance()).add(getTravelAccommodation());
		return travelFaresCount;
	}
	public void setTravelFaresCount(BigDecimal travelFaresCount) {
		this.travelFaresCount = travelFaresCount;
	}
}