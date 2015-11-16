package com.iitdev.ioms.operation.data.vo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.iitdev.ioms.operation.data.bo.Log;

/**
 * Log
 *
 */
public class LogVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.*,staff.staff_real_name as log_staff_name "
								+ " from o_log obj INNER JOIN m_staff staff ON staff.staff_id=obj.log_staff" ;
	private Long logId;//logId
	private String logLocation;//产生修改位置
	private String logTitle;//记录修改内容
	private String logCause;//记录修改原因
	private String logContent;//记录修改方法
	private Long logStaff;//记录者
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date logDate;
	private Long logState;//该方法是否可行
	private String logRemark;//记录备注
	///
	private String logStaffName;//staffName
	
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
	public Long getLogStaff() {
		return this.logStaff;
	}
	public void setLogStaff(Long value) {
		this.logStaff = value;
	}
	public Date getLogDate() {
		return this.logDate;
	}
	public void setLogDate(Date value) {
		this.logDate = value;
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
	
	
	public String getLogStaffName() {
		return logStaffName;
	}
	public void setLogStaffName(String logStaffName) {
		this.logStaffName = logStaffName;
	}
	//////////////////////////////////////////////////////
	public LogVO(){}

	public LogVO(Log log){
		BeanUtils.copyProperties(log,this);
	}
	public void copyValueTo(Log bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Log cloneBO(){
		Log bo = new Log();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Log> cloneBOList(List<LogVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Log>(0);
		List<Log> result = new ArrayList<Log>(vos.size());
		for(LogVO vo: vos){
			Log bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}