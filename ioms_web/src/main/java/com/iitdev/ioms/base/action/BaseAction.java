package com.iitdev.ioms.base.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.iitdev.ioms.book.service.BookBS;
import com.iitdev.ioms.book.service.BookRecordBS;
import com.iitdev.ioms.business.service.BusinessTravelBS;
import com.iitdev.ioms.member.service.LeaveBS;
import com.iitdev.ioms.member.service.SalaryAlterBS;
import com.iitdev.ioms.member.service.SalaryBS;
import com.iitdev.ioms.member.service.StaffBS;
import com.iitdev.ioms.operation.service.PwdBS;
import com.iitdev.ioms.permiss.service.PermissionsBS;
import com.iitdev.ioms.weekly.service.WeeklyBS;


public class BaseAction<T> extends com.iitdev.action.BaseAction<T> {
	@Autowired
	protected StaffBS staffBS;
	@Autowired
	protected LeaveBS leaveBS;
	@Autowired
	protected SalaryAlterBS salaryAlterBS;
	@Autowired
	protected BookRecordBS bookRecordBS;
	@Autowired
	protected BookBS bookBS;
	@Autowired
	protected BusinessTravelBS businessTravelBS;
	@Autowired
	protected WeeklyBS weeklyBS;
	@Autowired
	protected PermissionsBS permissionsBS;
	@Autowired
	protected SalaryBS salaryBS;
	@Autowired
	protected PwdBS pwdBS;
}