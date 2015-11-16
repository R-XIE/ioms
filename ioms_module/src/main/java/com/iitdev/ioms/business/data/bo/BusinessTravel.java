package com.iitdev.ioms.business.data.bo;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * BusinessTravel
 */
@Table(name = "bu_business_travel")
public class BusinessTravel implements Serializable{
	private static final long serialVersionUID = 1L;
	public BusinessTravel(){}
	//属性
	private Long businessTravelId;//businessTravelId
	private Long travelStaff;//请假人
	private Date travelStartTime;//精确到小时 开始时间
	private Date travelEndTime;//结束时间
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
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "business_travel_id", unique = true, nullable = false,length = 19)
	public Long getBusinessTravelId() {
		return this.businessTravelId;
	}
	
	public void setBusinessTravelId(Long BusinessTravelId) {
		this.businessTravelId = BusinessTravelId;
	}
	
	@Column(name = "travel_staff",unique = false,nullable = false,length = 10)
	public Long getTravelStaff() {
		return this.travelStaff;
	}
	
	public void setTravelStaff(Long value) {
		this.travelStaff = value;
	}
	
	@Column(name = "travel_start_time",unique = false,nullable = false,length = 0)
	public Date getTravelStartTime() {
		return this.travelStartTime;
	}
	
	public void setTravelStartTime(Date value) {
		this.travelStartTime = value;
	}
	
	@Column(name = "travel_end_time",unique = false,nullable = false,length = 0)
	public Date getTravelEndTime() {
		return this.travelEndTime;
	}
	
	public void setTravelEndTime(Date value) {
		this.travelEndTime = value;
	}
	
	@Column(name = "travel_address",unique = false,nullable = false,length = 255)
	public String getTravelAddress() {
		return this.travelAddress;
	}
	
	public void setTravelAddress(String value) {
		this.travelAddress = value;
	}
	
	@Column(name = "travel_goal",unique = false,nullable = true,length = 255)
	public String getTravelGoal() {
		return this.travelGoal;
	}
	
	public void setTravelGoal(String value) {
		this.travelGoal = value;
	}
	
	@Column(name = "travel_to_gether",unique = false,nullable = true,length = 255)
	public String getTravelToGether() {
		return this.travelToGether;
	}
	
	public void setTravelToGether(String value) {
		this.travelToGether = value;
	}
	
	@Column(name = "travel_vehicle",unique = false,nullable = true,length = 255)
	public String getTravelVehicle() {
		return this.travelVehicle;
	}
	
	public void setTravelVehicle(String value) {
		this.travelVehicle = value;
	}
	
	@Column(name = "travel_invoice_num",unique = false,nullable = true,length = 10)
	public Long getTravelInvoiceNum() {
		return this.travelInvoiceNum;
	}
	
	public void setTravelInvoiceNum(Long value) {
		this.travelInvoiceNum = value;
	}
	
	@Column(name = "travel_ticket",precision=7,scale = 2,unique = false,nullable = true)
	public BigDecimal getTravelTicket() {
		return this.travelTicket;
	}
	
	public void setTravelTicket(BigDecimal value) {
		this.travelTicket = value;
	}
	
	@Column(name = "travel_fare",precision=7,scale = 2,unique = false,nullable = true)
	public BigDecimal getTravelFare() {
		return this.travelFare;
	}
	
	public void setTravelFare(BigDecimal value) {
		this.travelFare = value;
	}
	
	@Column(name = "travel_fares",precision=7,scale = 2,unique = false,nullable = true)
	public BigDecimal getTravelFares() {
		return this.travelFares;
	}
	
	public void setTravelFares(BigDecimal value) {
		this.travelFares = value;
	}
	
	@Column(name = "travel_transport",precision=7,scale = 2,unique = false,nullable = true)
	public BigDecimal getTravelTransport() {
		return this.travelTransport;
	}
	
	public void setTravelTransport(BigDecimal value) {
		this.travelTransport = value;
	}
	
	@Column(name = "travel_allowance",precision=7,scale = 2,unique = false,nullable = true)
	public BigDecimal getTravelAllowance() {
		return this.travelAllowance;
	}
	
	public void setTravelAllowance(BigDecimal value) {
		this.travelAllowance = value;
	}
	
	@Column(name = "travel_accommodation",precision=7,scale = 2,unique = false,nullable = true)
	public BigDecimal getTravelAccommodation() {
		return this.travelAccommodation;
	}
	
	public void setTravelAccommodation(BigDecimal value) {
		this.travelAccommodation = value;
	}
	
	@Column(name = "travel_other",precision=7,scale = 2,unique = false,nullable = true)
	public BigDecimal getTravelOther() {
		return this.travelOther;
	}
	
	public void setTravelOther(BigDecimal value) {
		this.travelOther = value;
	}
	
	@Column(name = "travel_trip",unique = false,nullable = true,length = 255)
	public String getTravelTrip() {
		return this.travelTrip;
	}
	
	public void setTravelTrip(String value) {
		this.travelTrip = value;
	}
	
	
	
}
