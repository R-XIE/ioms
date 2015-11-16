package com.iitdev.ioms.operation.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iitdev.ioms.operation.data.bo.Backup;
import com.iitdev.ioms.operation.data.vo.BackupVO;
import com.iitdev.ioms.operation.service.BackupBS;
import com.iitdev.orm.MysqlSqlBuilder;
import com.iitdev.orm.PublicBSImpl;
import com.iitdev.page.Result;

/**
 *备份记录 SERVICE接口 实现类
 *
 */
@Service("backupBS")
public class BackupBSImpl extends PublicBSImpl implements BackupBS{
	public  final String SQL_QUERY_VO =  BackupVO.QUERY_SQL;
	
	public BackupVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" where obj.backup_id  = ? ";
		return super.queryForBean(BackupVO.class,sql ,new Object[]{id});
	}
	public List<BackupVO> queryVOListAll(){
		return super.queryForBeanList(BackupVO.class,SQL_QUERY_VO ,new Object[]{});
	}
	
	public int queryCountAll() {
		return super.queryForInt(BackupVO.QUERY_SQL_COUNT, new Object[]{});
	}
	
	@SuppressWarnings("unchecked")
	public Result<BackupVO> queryResultByPage(Integer currPage,
			Map<String, String> queryMap, Integer pageSize) {
		MysqlSqlBuilder builder = new MysqlSqlBuilder(BackupVO.class, SQL_QUERY_VO, queryMap);
		builder.setListType(MysqlSqlBuilder.LIST_TYPE_PAGE);
		builder.setCurrPage(currPage);
		builder.setPageSize(pageSize);
		 builder.addCondition("backupContent", "obj.backup_content",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addDateInCondition("backupDateBegin","backupDateEnd", "obj.backup_date",MysqlSqlBuilder.ParamType.DATE);
		 builder.addCondition("backupId", "obj.backup_id",MysqlSqlBuilder.ParamType.LONG);
		 builder.addCondition("backupServer", "obj.backup_server",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		 builder.addCondition("backupState", "obj.backup_state",MysqlSqlBuilder.ParamType.LONG);
		 builder.addCondition("backupTitle", "obj.backup_title",  MysqlSqlBuilder.OperatorType.LIKE_ANYWHERE);
		return (Result<BackupVO>) super.querySqlForPage(builder);
	}
	
	/**有外键的字段必须填充,而且要一致***/
	public Backup addBackup(Backup entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public Backup modifyBackup(Backup entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	
	public boolean delBackup(Backup entity)throws Exception{
		//1表删除
		delete(entity);
		return true;
	}

}