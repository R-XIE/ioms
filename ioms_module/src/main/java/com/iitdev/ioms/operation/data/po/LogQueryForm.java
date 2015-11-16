package com.iitdev.ioms.operation.data.po;
import java.util.Map;

import com.iitdev.data.QueryFormBean;
import com.iitdev.utils.BeanUtils;

/**
 * Log
 *
 */
public class LogQueryForm extends QueryFormBean {
	private static final long serialVersionUID = 1L;
	private Long logId;//logId
	private String logLocation;//产生修改位置
	private String logTitle;//记录修改内容
	private String logCause;//记录修改原因
	private String logContent;//记录修改方法
	private String logStaffName;//记录者
	private String logDateBE;
	private Long logState;//该方法是否可行
	private String logRemark;//记录备注
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getLogId() {
		return this.logId;
	}
	public void setLogId(Long value) {
		this.logId = value;
	}
	public String getLogLocation() {
		return this.logLocation;
	}
	public void setLogLocation(String value) {
		this.logLocation = value;
	}
	public String getLogTitle() {
		return this.logTitle;
	}
	public void setLogTitle(String value) {
		this.logTitle = value;
	}
	public String getLogCause() {
		return this.logCause;
	}
	public void setLogCause(String value) {
		this.logCause = value;
	}
	public String getLogContent() {
		return this.logContent;
	}
	public void setLogContent(String value) {
		this.logContent = value;
	}
	public String getLogStaffName() {
		return this.logStaffName;
	}
	public void setLogStaffName(String value) {
		this.logStaffName = value;
	}
	public String getLogDateBE() {
		return this.logDateBE;
	}
	public void setLogDateBE(String value) {
		this.logDateBE = value;
	}
	public Long getLogState() {
		return this.logState;
	}
	public void setLogState(Long value) {
		this.logState = value;
	}
	public String getLogRemark() {
		return this.logRemark;
	}
	public void setLogRemark(String value) {
		this.logRemark = value;
	}
	
	
	//////////////////////////////////////////////////////
	public LogQueryForm(){}
	
	public Map<String, String> buildQueryMap() {
		return BeanUtils.getQueryMap(this);
	}
}