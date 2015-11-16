package com.iitdev.ioms.base.data.bo;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Post 职位
 */
@Table(name = "b_post")
public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	public Post(){}
	//属性
	private Long postId;//主键ID
	private String postName;//职位名称
	private String postDesc;//职位描述
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id", unique = true, nullable = false,length = 10)
	public Long getPostId() {
		return this.postId;
	}
	
	public void setPostId(Long PostId) {
		this.postId = PostId;
	}
	
	@Column(name = "post_name",unique = false,nullable = false,length = 50)
	public String getPostName() {
		return this.postName;
	}
	
	public void setPostName(String value) {
		this.postName = value;
	}
	
	@Column(name = "post_desc",unique = false,nullable = false,length = 50)
	public String getPostDesc() {
		return this.postDesc;
	}
	
	public void setPostDesc(String value) {
		this.postDesc = value;
	}
	
	
	
}
