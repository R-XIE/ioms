package com.iitdev.ioms.base.data.vo;
import com.iitdev.ioms.base.data.bo.Post;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * Post
 *
 */
public class PostVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from b_post obj" ;
	private Long postId;//主键ID
	private String postName;//职位名称
	private String postDesc;//职位描述
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getPostId() {
		return this.postId;
	}
	public void setPostId(Long value) {
		this.postId = value;
	}
	public String getPostName() {
		return this.postName;
	}
	public void setPostName(String value) {
		this.postName = value;
	}
	public String getPostDesc() {
		return this.postDesc;
	}
	public void setPostDesc(String value) {
		this.postDesc = value;
	}
	
	
	//////////////////////////////////////////////////////
	public PostVO(){}

	public PostVO(Post post){
		BeanUtils.copyProperties(post,this);
	}
	public void copyValueTo(Post bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Post cloneBO(){
		Post bo = new Post();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Post> cloneBOList(List<PostVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Post>(0);
		List<Post> result = new ArrayList<Post>(vos.size());
		for(PostVO vo: vos){
			Post bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}