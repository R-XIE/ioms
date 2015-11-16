package com.iitdev.ioms.member.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.iitdev.ioms.member.data.vo.StaffVO;
import com.iitdev.ioms.member.service.StaffBS;
import com.iitdev.web.SpringGetBean;


public class CmdUtils {

	/**
	 * 获取所有的用户信息
	 * @return
	 */
	public static Map<Long,String> getStaffMap(){
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		StaffBS staffBS = (StaffBS) SpringGetBean.getBean("staffBS");
		List<StaffVO> staffList= staffBS.queryVOAllExistList();
		for (StaffVO staffVO : staffList) {
			map.put(staffVO.getStaffId(), staffVO.getStaffRealName());
		}
		return map;
	}
	
	
	/**
	 * 根据部门获取用户
	 * @param branchId 部门ID
	 * @return
	 */
	public static List<StaffVO> getStaffByBranch(Long branchId){
		StaffBS staffBS = (StaffBS) SpringGetBean.getBean("staffBS");
		return staffBS.queryStaffByBranch(branchId);
	}
	
	/**
	 * 获取所有的用户ID,作用是为表单填写默认用户
	 * @return
	 */
	public static Integer getStaffIdDefalt(){
		StaffBS staffBS = (StaffBS) SpringGetBean.getBean("staffBS");
		return staffBS.queryStaffIdDef();
	}
	
	//获取上个月
	/**
	 * 获取上个月
	 * @param months
	 * @return
	 */
	public static String getLastMonth(String months){
		String[] monthArr= months.split("\\.");
		String year=monthArr[0];
		String month=monthArr[1];
		if(month.equals("01")){
			year=new String((Integer.valueOf(year).intValue()-1)+"");
			month="12";
		}else{
			int monthInt=Integer.valueOf(month)-1;
			if(monthInt<10){
				month="0"+monthInt;
			}else{
				month=monthInt+"";
			}
		}
		return year+"."+month;
	}
}
