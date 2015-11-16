package com.iitdev.ioms.member.service;

//import com.iitdev.ioms.member.data.bo.Interview;
import java.util.List;
import java.util.Map;

import com.iitdev.ioms.member.data.bo.Interview;
import com.iitdev.ioms.member.data.vo.InterviewVO;
import com.iitdev.orm.PublicBS;
import com.iitdev.page.Result;


/**
 *面试 SERVICE接口 
 */
public interface InterviewBS extends PublicBS {
	/**通用查询方法***/
	public InterviewVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public Interview addInterview(Interview entity) throws Exception;//有外键的字段必须填充,而且要一致
	public Interview modifyInterview(Interview entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public Boolean delInterview(Interview entity)throws Exception;
	
	public List<InterviewVO> queryVOAllList();
	/**
	 * @param currPage 当前页
	 * @param queryMap 查询条件
	 * @param pageSize 每页数量
	 * @return
	 */
	public Result<InterviewVO> queryResultByPage(Integer currPage,	Map<String, String> queryMap, Integer pageSize);
}

