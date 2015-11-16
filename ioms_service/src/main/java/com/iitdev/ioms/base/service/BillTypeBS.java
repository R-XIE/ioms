package com.iitdev.ioms.base.service;

//import com.iitdev.ioms.base.data.bo.BillType;
import com.iitdev.ioms.base.data.bo.BillType;
import com.iitdev.ioms.base.data.vo.BillTypeVO;
import com.iitdev.orm.PublicBS;
import com.iitdev.page.Result;

import java.util.Map;

/**
 *单据类型 SERVICE接口 
 */
public interface BillTypeBS extends PublicBS {
	/**通用查询方法***/
	public BillTypeVO queryVOById(Long id);	
	public Result<BillTypeVO> queryForPage(int currPage,Map<String,String> queryMap);//分页方法
	/**通用持久化方法(添加和修改)***/
	public BillType addBillType(BillType entity) throws Exception;//有外键的字段必须填充,而且要一致
	public BillType modifyBillType(BillType entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public void delBillTypeByIds(Long ...ids)throws Exception;
	public void delBillType(BillType entity)throws Exception;
	
	public String generBillNo(String billTypeCode) throws Exception;//生成单据
}