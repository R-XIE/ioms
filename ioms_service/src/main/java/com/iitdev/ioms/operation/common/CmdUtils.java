package com.iitdev.ioms.operation.common;

import com.iitdev.ioms.operation.data.vo.PwdStaffVO;
import com.iitdev.ioms.operation.service.PwdStaffBS;
import com.iitdev.web.SpringGetBean;



public class CmdUtils {

	/**
	 * 是否已经设置信息
	 * @return
	 */
	public static Boolean isAuth(Long pwdId,Long staffId){
		PwdStaffBS pwdStaffBS= (PwdStaffBS) SpringGetBean.getBean("pwdStaffBS");
		PwdStaffVO vo = pwdStaffBS.queryPwdStaffByPwdStaff(pwdId, staffId);
		if(vo!=null)
			return true;
		else 
			return false;
	}
	
}