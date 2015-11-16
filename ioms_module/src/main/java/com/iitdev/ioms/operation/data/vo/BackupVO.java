package com.iitdev.ioms.operation.data.vo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.iitdev.ioms.operation.data.bo.Backup;

/**
 * Backup
 *
 */
public class BackupVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/////////////////////////////////SQL ///////////////////
	public static final String QUERY_SQL = "select obj.* from o_backup obj" ;
	public static final String QUERY_SQL_COUNT = "select count(1) from o_backup obj" ;
	private Long backupId;//backupId
	private String backupServer;//备份服务器
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date backupDate;
	private String backupTitle;//备份内容
	private String backupContent;//备份详情
	private Long backupState;//备份状态
	
	
	/////////////////////////getter and setter //////////////////////////
	public Long getBackupId() {
		return this.backupId;
	}
	public void setBackupId(Long value) {
		this.backupId = value;
	}
	public String getBackupServer() {
		return this.backupServer;
	}
	public void setBackupServer(String value) {
		this.backupServer = value;
	}
	public Date getBackupDate() {
		return this.backupDate;
	}
	public void setBackupDate(Date value) {
		this.backupDate = value;
	}
	public String getBackupTitle() {
		return this.backupTitle;
	}
	public void setBackupTitle(String value) {
		this.backupTitle = value;
	}
	public String getBackupContent() {
		return this.backupContent;
	}
	public void setBackupContent(String value) {
		this.backupContent = value;
	}
	public Long getBackupState() {
		return this.backupState;
	}
	public void setBackupState(Long value) {
		this.backupState = value;
	}
	
	
	//////////////////////////////////////////////////////
	public BackupVO(){}

	public BackupVO(Backup backup){
		BeanUtils.copyProperties(backup,this);
	}
	public void copyValueTo(Backup bo){
		BeanUtils.copyProperties(this,bo);
	}
	public Backup cloneBO(){
		Backup bo = new Backup();
		this.copyValueTo(bo);
		return bo;
	}
	
	public static List<Backup> cloneBOList(List<BackupVO> vos){
		if(vos==null||vos.isEmpty())return new ArrayList<Backup>(0);
		List<Backup> result = new ArrayList<Backup>(vos.size());
		for(BackupVO vo: vos){
			Backup bo = vo.cloneBO();
			result.add(bo);
		}
		return result;
	}
}