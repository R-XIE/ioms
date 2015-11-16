package com.iitdev.ioms.cost.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iitdev.ioms.cost.data.bo.CostType;
import com.iitdev.ioms.cost.data.po.CostTypePO;
import com.iitdev.ioms.cost.data.vo.CostTypeVO;
import com.iitdev.ioms.cost.service.CostBS;
import com.iitdev.ioms.cost.service.CostTypeBS;
import com.iitdev.orm.PublicBSImpl;

/**
 * 费用类别 SERVICE接口 实现类
 *
 */
@Service("costTypeBS")
public class CostTypeBSImpl extends PublicBSImpl implements CostTypeBS {
	public final String SQL_QUERY_VO = CostTypeVO.QUERY_SQL;
	@Autowired
	private CostBS costBS;

	public CostTypeVO queryVOById(Long id) {
		String sql = SQL_QUERY_VO + " where obj.cost_type_id  = ? ";
		return super.queryForBean(CostTypeVO.class, sql, new Object[] { id });
	}

	@Override
	public String queryTypeName(Long id, Long defId) {
		String sql = SQL_QUERY_VO + " where obj.cost_type_id  = ? ";
		CostTypeVO vo = super.queryForBean(CostTypeVO.class, sql,
				new Object[] { id });
		if (vo == null)
			vo = super.queryForBean(CostTypeVO.class, sql,
					new Object[] { defId });
		return vo.getCostTypeName();
	}

	public List<CostTypeVO> queryVOListAll() {
		return super.queryForBeanList(CostTypeVO.class, SQL_QUERY_VO,
				new Object[] {});
	}

	@Override
	public List<CostTypeVO> queryVOListRoot() {
		return super.queryForBeanList(CostTypeVO.class, SQL_QUERY_VO
				+ " where obj.cost_type_super is null ", new Object[] {});
	}

	/** 有外键的字段必须填充,而且要一致 ***/
	public CostType addCostType(CostType entity) throws Exception {
		// 1主表验证和此表的外键字段一致
		// 添加code编码
		// 保存表
		super.saveObject(entity);
		return entity;
	}

	public CostType modifyCostType(CostType entity) throws Exception {
		// 1主表验证和此表的外键字段一致

		// 修改表
		super.updateObject(entity);
		return entity;
	}

	public boolean delCostType(CostType entity) throws Exception {
		// 1表删除
		if (costBS.validateCountByType(entity.getCostTypeId()) == 0) {
			delete(entity);
			return true;
		} else {
			return false;
		}
	}

	private int validateChild(Long rootId) {
		String sql = "select count(1) from c_cost_type where cost_type_super=?";
		return super.queryForInt(sql, new Object[] { rootId });
	}

	private List<CostTypeVO> queryChild(Long rootId) {
		String sql = "select * from c_cost_type where cost_type_super=?";
		return super.queryForBeanList(CostTypeVO.class, sql,
				new Object[] { rootId });
	}

	@Override
	public List<CostTypePO> converPOList() {
		List<CostTypePO> res = new ArrayList<CostTypePO>();
		List<CostTypeVO> root = this.queryVOListRoot();
		for (CostTypeVO costTypeVO : root) {
			CostTypePO po = new CostTypePO();
			List<CostTypeVO> chird = new ArrayList<CostTypeVO>();
			po.setLable(costTypeVO.getCostTypeName());
			int icount = validateChild(costTypeVO.getCostTypeId());
			if (icount == 0) {
				chird.add(costTypeVO);
			} else {
				chird = queryChild(costTypeVO.getCostTypeId());
			}
			po.setCostTypeList(chird);
			res.add(po);
		}
		return res;
	}

}