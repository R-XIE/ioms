package com.iitdev.ioms.operation.data.po;
import java.util.Map;

import com.iitdev.data.QueryFormBean;
import com.iitdev.utils.BeanUtils;

/**
 * Backup
 *
 */
public class BackupQueryForm extends QueryFormBean {
	private static final long serialVersionUID = 1L;
	private String backupServer;//备份服务器
	private String backupDateBE;
	private String backupTitle;//备份内容
	private Long backupState;//备份状态
	
	
	/////////////////////////getter and setter //////////////////////////
	public String getBackupServer() {
		return this.backupServer;
	}
	public void setBackupServer(String value) {
		this.backupServer = value;
	}
	public String getBackupDateBE() {
		return this.backupDateBE;
	}
	public void setBackupDateBE(String value) {
		this.backupDateBE = value;
	}
	public String getBackupTitle() {
		return this.backupTitle;
	}
	public void setBackupTitle(String value) {
		this.backupTitle = value;
	}
	public Long getBackupState() {
		return this.backupState;
	}
	public void setBackupState(Long value) {
		this.backupState = value;
	}
	
	//////////////////////////////////////////////////////
	public BackupQueryForm(){}
	
	public Map<String, String> buildQueryMap() {
		return BeanUtils.getQueryMap(this);
	}
}