package com.iitdev.ioms.base.service;

//import com.iitdev.ioms.base.data.bo.Branch;
import java.util.List;

import com.iitdev.ioms.base.common.CmdUtils;
import com.iitdev.ioms.base.data.bo.Branch;
import com.iitdev.ioms.base.data.vo.BranchVO;
import com.iitdev.orm.PublicBS;


/**
 *部门 SERVICE接口 
 */
public interface BranchBS extends PublicBS {
	/**通用查询方法***/
	public BranchVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public Branch addBranch(Branch entity) throws Exception;//有外键的字段必须填充,而且要一致
	public Branch modifyBranch(Branch entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delBranch(Branch entity)throws Exception;
	
	public List<BranchVO> queryVOAllList();
	/**
	 * @see CmdUtils#getBranchByStaff()
	 * @param staffId 用户ID
	 * @return vo
	 */
	public BranchVO queryBranchByStaff(Long staffId);
}

