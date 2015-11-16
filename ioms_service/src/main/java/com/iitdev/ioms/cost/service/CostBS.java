package com.iitdev.ioms.cost.service;

//import com.iitdev.ioms.cost.data.bo.Cost;
import java.util.List;
import java.util.Map;

import com.iitdev.ioms.cost.data.bo.Cost;
import com.iitdev.ioms.cost.data.vo.CostVO;
import com.iitdev.orm.PublicBS;
import com.iitdev.page.Result;

/**
 *费用 SERVICE接口 
 */
public interface CostBS extends PublicBS {
	/**通用查询方法***/
	public CostVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public Cost addCost(Cost entity) throws Exception;//有外键的字段必须填充,而且要一致
	public Cost modifyCost(Cost entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delCost(Cost entity)throws Exception;
	public List<CostVO> queryVOListAll();
	public int queryCountAll();
	public int validateCountByType(Long typeId);
	
	/**
	 * 传统分页
	 * @param currPage 当前页数
	 * @param queryMap 参数map
	 * @return
	 */
	public Result<CostVO> queryResultByPage(int currPage,Map<String,String> queryMap,Integer pageSize);
}