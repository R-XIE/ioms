package com.iitdev.ioms.base.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.iitdev.ioms.base.data.vo.BranchVO;
import com.iitdev.ioms.base.data.vo.PositionVO;
import com.iitdev.ioms.base.data.vo.PostVO;
import com.iitdev.ioms.base.service.BillTypeBS;
import com.iitdev.ioms.base.service.BranchBS;
import com.iitdev.ioms.base.service.PositionBS;
import com.iitdev.ioms.base.service.PostBS;
import com.iitdev.web.SpringGetBean;

public class CmdUtils {

	public static BillTypeBS getBillTypeBS() {
		BillTypeBS billTypeBS = (BillTypeBS) SpringGetBean.getBean("billTypeBS");
		return billTypeBS;
	}
	
	/**
	 * 通过用户获取该用户所属的部门组织
	 * @param staffId
	 * @return
	 */
	public static BranchVO getBranchByStaff(Long staffId){
		BranchBS branchBS = (BranchBS) SpringGetBean.getBean("branchBS");
		return branchBS.queryBranchByStaff(staffId);
	}
	
	
	/**
	 * 通过用户获取该用户的职位信息
	 * @param staffId
	 * @return
	 */
	public static PositionVO getPositionByStaff(Long staffId){
		PositionBS positionBS= (PositionBS) SpringGetBean.getBean("positionBS");
		return positionBS.queryPositionByStaff(staffId);
	}
	
	/**
	 * 通过用户获取该用户的岗位信息
	 * @param staffId
	 * @return
	 */
	public static PostVO getPostByStaff(Long staffId){
		PostBS postBS= (PostBS) SpringGetBean.getBean("postBS");
		return postBS.queryPostByStaff(staffId);
	}
	
	
	/**
	 * 获取所有的部门信息
	 * @return
	 */
	public static Map<Long,String> getBranchMap(){
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		BranchBS branchBS = (BranchBS) SpringGetBean.getBean("branchBS");
		List<BranchVO> brachList= branchBS.queryVOAllList();
		for (BranchVO branchVO : brachList) {
			map.put(branchVO.getBranchId(), branchVO.getBranchName());
		}
		return map;
	}
	
	/**
	 * 获取所有的职位信息
	 * @return
	 */
	public static Map<Long,String> getPositionMap(){
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		PositionBS positionBS = (PositionBS) SpringGetBean.getBean("positionBS");
		List<PositionVO> positionList= positionBS.queryVOAllList();
		for (PositionVO positionVO : positionList) {
			map.put(positionVO.getPositionId(), positionVO.getPositionName());
		}
		return map;
	}
	
	/**
	 * 获取所有的岗位信息
	 * @return
	 */
	public static Map<Long,String> getPostMap(){
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		PostBS postBS = (PostBS) SpringGetBean.getBean("postBS");
		List<PostVO> postList= postBS.queryVOAllList();
		for (PostVO postVO : postList) {
			map.put(postVO.getPostId(), postVO.getPostName());
		}
		return map;
	}
}
