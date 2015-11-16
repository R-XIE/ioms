package com.iitdev.ioms.cost.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iitdev.ioms.cost.data.bo.Cost;
import com.iitdev.ioms.cost.data.vo.CostVO;
import com.iitdev.ioms.cost.service.CostBS;
import com.iitdev.orm.MysqlSqlBuilder;
import com.iitdev.orm.PublicBSImpl;
import com.iitdev.page.Result;

/**
 *费用 SERVICE接口 实现类
 *
 */
@Service("costBS")
public class CostBSImpl extends PublicBSImpl implements CostBS{
	public  final String SQL_QUERY_VO =  CostVO.QUERY_SQL;
	
	public CostVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" where obj.cost_id  = ? ";
		return super.queryForBean(CostVO.class,sql ,new Object[]{id});
	}
	public List<CostVO> queryVOListAll(){
		return super.queryForBeanList(CostVO.class,SQL_QUERY_VO ,new Object[]{});
	}
	public int queryCountAll() {
		return super.queryForInt(CostVO.QUERY_SQL_COUNT, new Object[]{});
	}
	/**有外键的字段必须填充,而且要一致***/
	public Cost addCost(Cost entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public Cost modifyCost(Cost entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	
	public boolean delCost(Cost entity)throws Exception{
		//1表删除
		delete(entity);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Result<CostVO> queryResultByPage(int currPage,Map<String,String> queryMap,Integer pageSize){
		MysqlSqlBuilder builder = new MysqlSqlBuilder(CostVO.class, SQL_QUERY_VO, queryMap);
		builder.setListType(MysqlSqlBuilder.LIST_TYPE_PAGE);
		builder.setCurrPage(currPage);
		builder.setPageSize(pageSize);
		builder.addCondition("costType", "obj.cost_type",  MysqlSqlBuilder.OperatorType.EQ);
		builder.addCondition("costMoney", "obj.cost_money",  MysqlSqlBuilder.OperatorType.EQ);
		builder.addCondition("costDesc", "obj.cost_desc",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		builder.addDateInCondition("costTimeBegin", "costTimeEnd","obj.cost_time",MysqlSqlBuilder.ParamType.DATE);
		builder.addDateInCondition("costCreateTimeBegin", "costCreateTimeEnd", "obj.cost_create_time",  MysqlSqlBuilder.ParamType.DATE);
		return (Result<CostVO>) super.querySqlForPage(builder);
	}
	@Override
	public int validateCountByType(Long typeId) {
		String sql ="select count(1) from c_cost where cost_type=?";
		return super.queryForInt(sql, new Object[]{typeId});
	}

}