package com.iitdev.ioms.cost.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.iitdev.ioms.cost.data.po.CostTypePO;
import com.iitdev.ioms.cost.data.vo.CostTypeVO;
import com.iitdev.ioms.cost.service.CostTypeBS;
import com.iitdev.web.SpringGetBean;


public class CmdUtils {
	/**
	 * 获取所有的费用类型列表
	 * @return
	 */
	public static Map<Long,String> getCostTypeMap(){
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		CostTypeBS costTypeBS = (CostTypeBS) SpringGetBean.getBean("costTypeBS");
		List<CostTypeVO> list =costTypeBS.queryVOListAll();
		for (CostTypeVO costTypeVO : list) {
			map.put(costTypeVO.getCostTypeId(),
					costTypeBS.queryTypeName(costTypeVO.getCostTypeSuper(),costTypeVO.getCostTypeId())
					+"["+ costTypeVO.getCostTypeName()+"]");
		}
		return map;
	}
	
	/**
	 * 获取所有的父级消费类型
	 * @return
	 */
	public static Map<Long,String> getCostTypeSuperMap(){
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		CostTypeBS costTypeBS = (CostTypeBS) SpringGetBean.getBean("costTypeBS");
		List<CostTypeVO> list =costTypeBS.queryVOListRoot();
		for (CostTypeVO costTypeVO : list) {
			map.put(costTypeVO.getCostTypeId(), costTypeVO.getCostTypeName());
		}
		return map;
	}
	
	/**
	 * 获取所有消费类型，用于cost模块的类型选取操作，呈现目录结构
	 * @return
	 */
	public static List<CostTypePO> getCostTypeList(){
		CostTypeBS costTypeBS = (CostTypeBS) SpringGetBean.getBean("costTypeBS");
		return costTypeBS.converPOList();
	}
}
