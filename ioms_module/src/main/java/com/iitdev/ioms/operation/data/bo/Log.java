package com.iitdev.ioms.operation.data.bo;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Log
 */
@Table(name = "o_log")
public class Log implements Serializable{
	private static final long serialVersionUID = 1L;
	public Log(){}
	//属性
	private Long logId;//logId
	private String logLocation;//产生修改位置
	private String logTitle;//记录修改内容
	private String logCause;//记录修改原因
	private String logContent;//记录修改方法
	private Long logStaff;//记录者
	private Date logDate;//记录日期
	private Long logState;//该方法是否可行
	private String logRemark;//记录备注
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id", unique = true, nullable = false,length = 19)
	public Long getLogId() {
		return this.logId;
	}
	
	public void setLogId(Long LogId) {
		this.logId = LogId;
	}
	
	@Column(name = "log_location",unique = false,nullable = false,length = 255)
	public String getLogLocation() {
		return this.logLocation;
	}
	
	public void setLogLocation(String value) {
		this.logLocation = value;
	}
	
	@Column(name = "log_title",unique = false,nullable = false,length = 255)
	public String getLogTitle() {
		return this.logTitle;
	}
	
	public void setLogTitle(String value) {
		this.logTitle = value;
	}
	
	@Column(name = "log_cause",unique = false,nullable = false,length = 255)
	public String getLogCause() {
		return this.logCause;
	}
	
	public void setLogCause(String value) {
		this.logCause = value;
	}
	
	@Column(name = "log_content",unique = false,nullable = false,length = 2147483647)
	public String getLogContent() {
		return this.logContent;
	}
	
	public void setLogContent(String value) {
		this.logContent = value;
	}
	
	@Column(name = "log_staff",unique = false,nullable = false,length = 10)
	public Long getLogStaff() {
		return this.logStaff;
	}
	
	public void setLogStaff(Long value) {
		this.logStaff = value;
	}
	
	@Column(name = "log_date",unique = false,nullable = false,length = 0)
	public Date getLogDate() {
		return this.logDate;
	}
	
	public void setLogDate(Date value) {
		this.logDate = value;
	}
	
	@Column(name = "log_state",unique = false,nullable = false,length = 10)
	public Long getLogState() {
		return this.logState;
	}
	
	public void setLogState(Long value) {
		this.logState = value;
	}
	
	@Column(name = "log_remark",unique = false,nullable = true,length = 500)
	public String getLogRemark() {
		return this.logRemark;
	}
	
	public void setLogRemark(String value) {
		this.logRemark = value;
	}
	
	
	
}
