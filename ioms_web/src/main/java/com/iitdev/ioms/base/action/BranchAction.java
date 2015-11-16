package com.iitdev.ioms.base.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.ioms.base.data.bo.Branch;
import com.iitdev.ioms.base.data.vo.BranchVO;
import com.iitdev.ioms.base.service.BranchBS;

/**
 * 
 * 部门 Action
 * 
 */
@Controller
@RequestMapping("/base")
public class BranchAction extends BaseAction<Branch> {
	@Autowired
	private BranchBS branchBS;
	
	public BranchAction() {
		super();
	}

	/************************************** 页面跳转 ******************************/

	/**
	 * 部门信息的列表
	 * @return
	 */
	@RequestMapping("branchActionList")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("base/branchList");
		List<BranchVO> branchList= branchBS.queryVOAllList();
		view.addObject("list", branchList);
		return view;

	}
	
	/**
	 * 删除部门信息
	 * @param recordId
	 * @throws Exception
	 */
	@RequestMapping("branchActionDel")
	public void del(Long recordId) throws Exception {
		BranchVO vo = branchBS.queryVOById(recordId);
		boolean res = branchBS.delBranch(vo.cloneBO());
		super.printJson(res + "");
	}
	
	/**
	 * 保存信息
	 * @param branch
	 * @throws Exception
	 */
	@RequestMapping("branchActionSave")
	public void save(Branch branch) throws Exception {
		Branch temp;
		if (branch.getBranchId() == null) {
			temp = branchBS.addBranch(branch);
		}else{
			temp =branchBS.modifyBranch(branch);
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}

}