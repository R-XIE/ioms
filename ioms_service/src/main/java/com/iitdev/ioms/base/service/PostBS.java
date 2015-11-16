package com.iitdev.ioms.base.service;

//import com.iitdev.ioms.base.data.bo.Post;
import java.util.List;

import com.iitdev.ioms.base.common.CmdUtils;
import com.iitdev.ioms.base.data.bo.Post;
import com.iitdev.ioms.base.data.vo.PostVO;
import com.iitdev.orm.PublicBS;


/**
 *岗位 SERVICE接口 
 */
public interface PostBS extends PublicBS {
	/**通用查询方法***/
	public PostVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public Post addPost(Post entity) throws Exception;//有外键的字段必须填充,而且要一致
	public Post modifyPost(Post entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delPost(Post entity)throws Exception;
	
	public List<PostVO> queryVOAllList();
	/**
	 * @see CmdUtils#getPostByStaff(Long)
	 * @param staffId
	 * @return
	 */
	public PostVO queryPostByStaff(Long staffId);
}