package com.iitdev.ioms.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iitdev.ioms.base.data.bo.Post;
import com.iitdev.ioms.base.data.vo.PostVO;
import com.iitdev.ioms.base.service.PostBS;
import com.iitdev.ioms.member.data.vo.StaffVO;
import com.iitdev.ioms.member.service.StaffBS;
import com.iitdev.orm.PublicBSImpl;

/**
 *岗位 SERVICE接口 实现类
 *
 */
@Service("postBS")
public class PostBSImpl extends PublicBSImpl implements PostBS{
	public  final String SQL_QUERY_VO =  PostVO.QUERY_SQL;
	@Resource
	private StaffBS staffBS;
	public PostVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" where obj.post_id  = ? ";
		return super.queryForBean(PostVO.class,sql ,new Object[]{id});
	}
	public PostVO queryPostByStaff(Long staffId){
		StaffVO staff=staffBS.queryVOById(staffId);
		return queryVOById(staff.getPostId());
	}
	
	/**有外键的字段必须填充,而且要一致***/
	public Post addPost(Post entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public Post modifyPost(Post entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	public boolean delPost(Post entity)throws Exception{
		String icountStaffSQL="select count(1) from m_staff staff "
				+ "WHERE staff.post_id=?";
		int icount= super.queryForInt(icountStaffSQL, new Object[]{entity.getPostId()});
		if(icount!=0){
			return false;
		}else{
			delete(entity);
			return true;
		}
	}
	@Override
	public List<PostVO> queryVOAllList() {
		// TODO Auto-generated method stub
		return super.queryForBeanList(PostVO.class, PostVO.QUERY_SQL, new Object[]{});
	}

}