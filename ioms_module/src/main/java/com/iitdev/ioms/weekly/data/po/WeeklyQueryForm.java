package com.iitdev.ioms.weekly.data.po;
import java.util.Map;

import com.iitdev.data.QueryFormBean;
import com.iitdev.utils.BeanUtils;

/**
 *
 */
public class WeeklyQueryForm extends QueryFormBean{
	private static final long serialVersionUID = 1L;
	private String weeklyStaff;//
	private String weeklyBeginDate;
	private String weeklyEndDate;
	private String weeklyCreateDateBE;
	private String weeklyState;//weeklyState
	
	public Map<String,String> buildQueryMap(){
		return BeanUtils.getQueryMap(this);
	}
	
	public String getWeeklyStaff() {
		return weeklyStaff;
	}
	public void setWeeklyStaff(String weeklyStaff) {
		this.weeklyStaff = weeklyStaff;
	}
	public String getWeeklyBeginDate() {
		return weeklyBeginDate;
	}
	public void setWeeklyBeginDate(String weeklyBeginDate) {
		this.weeklyBeginDate = weeklyBeginDate;
	}
	public String getWeeklyEndDate() {
		return weeklyEndDate;
	}
	public void setWeeklyEndDate(String weeklyEndDate) {
		this.weeklyEndDate = weeklyEndDate;
	}
	public String getWeeklyCreateDateBE() {
		return weeklyCreateDateBE;
	}
	public void setWeeklyCreateDateBE(String weeklyCreateDateBE) {
		this.weeklyCreateDateBE = weeklyCreateDateBE;
	}
	public String getWeeklyState() {
		return weeklyState;
	}
	public void setWeeklyState(String weeklyState) {
		this.weeklyState = weeklyState;
	}
	
	
}