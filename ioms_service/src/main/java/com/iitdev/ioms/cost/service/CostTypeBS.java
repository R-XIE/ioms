package com.iitdev.ioms.cost.service;

//import com.iitdev.ioms.cost.data.bo.CostType;
import java.util.List;

import com.iitdev.ioms.cost.common.CmdUtils;
import com.iitdev.ioms.cost.data.bo.CostType;
import com.iitdev.ioms.cost.data.po.CostTypePO;
import com.iitdev.ioms.cost.data.vo.CostTypeVO;
import com.iitdev.orm.PublicBS;


/**
 *费用类别 SERVICE接口 
 */
public interface CostTypeBS extends PublicBS {
	/**通用查询方法***/
	public CostTypeVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public CostType addCostType(CostType entity) throws Exception;//有外键的字段必须填充,而且要一致
	public CostType modifyCostType(CostType entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delCostType(CostType entity)throws Exception;
	public List<CostTypeVO> queryVOListAll();
	/**
	 * 获取所有根节点的类型用于父级节点的选择
	 * @see CmdUtils#getCostTypeSuperMap()
	 * @return
	 */
	public List<CostTypeVO> queryVOListRoot();
	/**
	 * 用于cost模块的下拉显示
	 * @return
	 */
	public List<CostTypePO> converPOList();
	
	/**
	 * 页面显示map获取父级名称
	 * @param id
	 * @return
	 */
	public String queryTypeName(Long id,Long defId);
}