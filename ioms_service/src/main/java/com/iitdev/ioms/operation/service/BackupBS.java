package com.iitdev.ioms.operation.service;

//import com.iitdev.ioms.operation.data.bo.Backup;
import java.util.List;
import java.util.Map;

import com.iitdev.ioms.operation.data.bo.Backup;
import com.iitdev.ioms.operation.data.vo.BackupVO;
import com.iitdev.orm.PublicBS;
import com.iitdev.page.Result;

/**
 *备份记录 SERVICE接口 
 */
public interface BackupBS extends PublicBS {
	/**通用查询方法***/
	public BackupVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public Backup addBackup(Backup entity) throws Exception;//有外键的字段必须填充,而且要一致
	public Backup modifyBackup(Backup entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delBackup(Backup entity)throws Exception;
	public List<BackupVO> queryVOListAll();
	public int queryCountAll();
	/**
	 * 
	 */
	public Result<BackupVO> queryResultByPage(Integer currPage,Map<String, String> queryMap,Integer pageSize);
}

