package com.iitdev.ioms.base.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.ioms.base.data.bo.Post;
import com.iitdev.ioms.base.data.vo.PostVO;
import com.iitdev.ioms.base.service.PostBS;

/**
 * 
 * 职位 Action
 * 
 */
@Controller
@RequestMapping("/base")
public class PostAction extends BaseAction<Post> {
	@Autowired
	private PostBS postBS;
	
	public PostAction() {
		super();
	}

	/************************************** 页面跳转 ******************************/

	@RequestMapping("postActionList")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("base/postList");
		List<PostVO> postList= postBS.queryVOAllList();
		view.addObject("list", postList);
		return view;

	}
	@RequestMapping("postActionDel")
	public void del(Long recordId) throws Exception {
		PostVO vo = postBS.queryVOById(recordId);
		boolean res = postBS.delPost(vo.cloneBO());
		super.printJson(res + "");
	}
	
	@RequestMapping("postActionSave")
	public void save(Post post) throws Exception {
		Post temp;
		if (post.getPostId() == null) {
			temp = postBS.addPost(post);
		}else{
			temp =postBS.modifyPost(post);
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}

}