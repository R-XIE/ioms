package com.iitdev.ioms.operation.service;

//import com.iitdev.ioms.operation.data.bo.Log;
import java.util.List;
import java.util.Map;

import com.iitdev.ioms.operation.data.bo.Log;
import com.iitdev.ioms.operation.data.vo.LogVO;
import com.iitdev.orm.PublicBS;
import com.iitdev.page.Result;

/**
 *更改维护记录 SERVICE接口 
 */
public interface LogBS extends PublicBS {
	/**通用查询方法***/
	public LogVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public Log addLog(Log entity) throws Exception;//有外键的字段必须填充,而且要一致
	public Log modifyLog(Log entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delLog(Log entity)throws Exception;
	public List<LogVO> queryVOListAll();
	/**
	 * 
	 */
	public Result<LogVO> queryResultByPage(Integer currPage,Map<String, String> queryMap,Integer pageSize);
}

