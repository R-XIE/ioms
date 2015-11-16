package com.iitdev.ioms.base.service.impl;
import java.util.List;
import java.util.Map;

import com.iitdev.ioms.base.data.bo.BillSerial;
import com.iitdev.ioms.base.data.bo.BillType;
import com.iitdev.ioms.base.data.vo.BillSerialVO;
import com.iitdev.ioms.base.data.vo.BillTypeVO;
import com.iitdev.ioms.base.service.BillTypeBS;
import com.iitdev.orm.MysqlSqlBuilder;
import com.iitdev.orm.PublicBSImpl;
import com.iitdev.page.Result;
import com.iitdev.utils.Util;

/**
 *单据类型 SERVICE接口 实现类
 *
 */
public class BillTypeBSImpl extends PublicBSImpl implements BillTypeBS{
	public  final String SQL_QUERY_VO =  BillTypeVO.QUERY_SQL;
	
	public BillTypeVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" and obj.bill_type_id  = ? ";
		return super.queryForBean(BillTypeVO.class,sql ,new Object[]{id});
	}
	
	
	@SuppressWarnings("unchecked")
	public Result<BillTypeVO> queryForPage(int currPage,Map<String,String> queryMap){
		MysqlSqlBuilder builder = new MysqlSqlBuilder(BillTypeVO.class, SQL_QUERY_VO, queryMap);
		builder.setListType(MysqlSqlBuilder.LIST_TYPE_PAGE);
		builder.setCurrPage(currPage);
		
		 builder.addCondition("billName", "obj.bill_name",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addCondition("billPrefix", "obj.bill_prefix",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addCondition("billTypeCode", "obj.bill_type_code",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addCondition("billTypeId", "obj.bill_type_id",MysqlSqlBuilder.ParamType.LONG);
		 builder.addCondition("serialFlag", "obj.serial_flag",  MysqlSqlBuilder.ParamType.LONG);
		 builder.addCondition("serialLen", "obj.serial_len", MysqlSqlBuilder.ParamType.LONG);
		 builder.addCondition("split", "obj.split",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addCondition("splitFlag", "obj.split_flag",  MysqlSqlBuilder.ParamType.LONG);
		 builder.addCondition("state", "obj.state",  MysqlSqlBuilder.ParamType.LONG);
		 builder.addCondition("timestampFlag", "obj.timestamp_flag",  MysqlSqlBuilder.ParamType.LONG);
		 builder.addCondition("timestampFormat", "obj.timestamp_format",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		return (Result<BillTypeVO>) super.querySqlForPage(builder);
	}
	
	/**有外键的字段必须填充,而且要一致***/
	public BillType addBillType(BillType entity) throws Exception{
		String countSQL = "select count(1) from b_bill_type where bill_type_code=?";
		int count = super.queryForInt(countSQL,
				new Object[] { entity.getBillTypeCode() });
		if (count != 0) {
			throw new Exception("该单据已经存在["
					+ entity.getBillTypeCode() + "]");
		}
		// 保存表
		super.saveObject(entity);
		return entity;
	}
	public BillType modifyBillType(BillType entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	/**删除的方法**/
	public void delBillTypeByIds(Long ...ids)throws Exception{
		for(Long id:ids){
			BillType entity = getById(id,BillType.class);
			if(entity!=null)delBillType(entity);
		}
	}
	public void delBillType(BillType entity)throws Exception{
		String icountSQL = "SELECT COUNT(1) AS icount FROM b_bill_serial WHERE bill_type=?";
		int icount = super.queryForInt(icountSQL,
				new Object[] { entity.getBillTypeId() });
		if (icount != 0) {
			throw new Exception("该表单据已经被使用,无法删除。");
		} else {
			entity.setState(-1l);
			modifyBillType(entity);
		}
	}
	
	public String generBillNo(String billTypeCode) throws Exception {
		// 1. 查找满足条件的单据类型
		// 2. 添加前缀
		StringBuffer result = new StringBuffer();
		String strSql =BillTypeVO.QUERY_SQL+" and obj.bill_type_code=?";
		
		List<BillTypeVO> listBillType = super.queryForBeanList(BillTypeVO.class, strSql, new String[]{billTypeCode});
		if (listBillType == null || listBillType.size() == 0)
			throw new Exception("没有维护该单据信息:" + billTypeCode);
		if (listBillType.size() > 1)
			throw new Exception("返回多个结果集:" + billTypeCode);
		BillTypeVO billType = listBillType.get(0);
		// 获取billType的前缀
		result.append(billType.getBillPrefix());
		// 判断是否有时间
		if (billType.getTimestampFlag().equals(new Long(0))) {
			String strDataFormat = billType.getTimestampFormat();
			String strData = Util.getSysDate(strDataFormat);
			if (billType.getSplitFlag().equals(new Long(0))) {
				result.append(billType.getSplit());
			}
			result.append(strData);
		}
		if (billType.getSerialFlag().equals(new Long(-1))) { // 无须序列号递增
			return result.toString();
		}
		String codeItem = result.toString();
		String serialNo = getBillSerial(billType.cloneBO(), codeItem);
		if (billType.getSplitFlag().equals(new Long(0))) {
			result.append(billType.getSplit());
		}
		result.append(serialNo);
		return result.toString();
	}
	
	private String getBillSerial(BillType billType, String codeItem)
			throws Exception {
		Long lSerial = 1l;
		Long lMaxSerial = Util.getMaxSerial(billType.getSerialLen().intValue());
		String strSql = "select * from b_bill_serial a where a.billType=?";
		BillSerialVO billSerialVO = super.queryForBean(BillSerialVO.class, strSql,new Object[] {
				billType.getBillTypeId()});
		boolean exists = billSerialVO != null;
		BillSerial billSerial;
		if (exists) {
			// 如果存在则更新
			//1判断是查询出的是否与传值过来的值一致？
			billSerial = billSerialVO.cloneBO();
			if(billSerialVO.getCodeItem().equals(codeItem)){
				//一致则更新
				lSerial = billSerial.getNextSerialNo();
				if (lSerial >= lMaxSerial) {
					throw new Exception("已经达到最大值");
				}
				billSerial.setMaxSerialNo(lMaxSerial);
				billSerial.setNextSerialNo(billSerial.getNextSerialNo() + 1);
				super.updateObject(billSerial);
			}else{
				//不一致则重新赋值
				billSerial.setBillType(billType.getBillTypeId());
				billSerial.setCodeItem(codeItem);
				billSerial.setMaxSerialNo(lMaxSerial);
				billSerial.setBeginSerialNo(1l);
				billSerial.setNextSerialNo(2l);
				super.updateObject(billSerial);
				lSerial = 1l;
			}
		} else {
			// 如果不存在则新增
			billSerial = new BillSerial();
			billSerial.setBillType(billType.getBillTypeId());
			billSerial.setCodeItem(codeItem);
			billSerial.setMaxSerialNo(lMaxSerial);
			billSerial.setBeginSerialNo(1l);
			billSerial.setNextSerialNo(2l);
			super.saveObject(billSerial);
			lSerial = 1l;
		}
		// 格式化
		String strFormat = "%0" + billType.getSerialLen() + "s";
		String strSerial = Util.format(lSerial, strFormat);
		return strSerial;
	}
}