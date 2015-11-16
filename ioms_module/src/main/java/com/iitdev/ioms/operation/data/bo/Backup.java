package com.iitdev.ioms.operation.data.bo;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Backup
 */
@Table(name = "o_backup")
public class Backup implements Serializable{
	private static final long serialVersionUID = 1L;
	public Backup(){}
	//属性
	private Long backupId;//backupId
	private String backupServer;//备份服务器
	private Date backupDate;//备份日期
	private String backupTitle;//备份内容
	private String backupContent;//备份详情
	private Long backupState;//备份状态
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "backup_id", unique = true, nullable = false,length = 19)
	public Long getBackupId() {
		return this.backupId;
	}
	
	public void setBackupId(Long BackupId) {
		this.backupId = BackupId;
	}
	
	@Column(name = "backup_server",unique = false,nullable = false,length = 255)
	public String getBackupServer() {
		return this.backupServer;
	}
	
	public void setBackupServer(String value) {
		this.backupServer = value;
	}
	
	@Column(name = "backup_date",unique = false,nullable = false,length = 0)
	public Date getBackupDate() {
		return this.backupDate;
	}
	
	public void setBackupDate(Date value) {
		this.backupDate = value;
	}
	
	@Column(name = "backup_title",unique = false,nullable = false,length = 255)
	public String getBackupTitle() {
		return this.backupTitle;
	}
	
	public void setBackupTitle(String value) {
		this.backupTitle = value;
	}
	
	@Column(name = "backup_content",unique = false,nullable = false,length = 2147483647)
	public String getBackupContent() {
		return this.backupContent;
	}
	
	public void setBackupContent(String value) {
		this.backupContent = value;
	}
	
	@Column(name = "backup_state",unique = false,nullable = false,length = 10)
	public Long getBackupState() {
		return this.backupState;
	}
	
	public void setBackupState(Long value) {
		this.backupState = value;
	}
	
	
	
}
