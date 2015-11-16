package com.iitdev.mail;

import java.io.File;

import com.iitdev.globals.PropertiesLoader;


/**
 * 本类是主要进行发送E-mail信息。
 * @author Jerry
 * 如果是HTML :<html><head></head><body><h1>hello!!spring html Mail</h1><a href=\"http://www.sina.com\">超级链接</a></body></html>
 * 请加上http:// 否则URL会失效
 */
public class MailBean {
	private String toEmail;//e-mail目的地
	private String fromEmail;//e-mail来源地
	private String emailTitle;//邮件标题
	private String emailContent;//邮件正文
	private File[] sendFiles;//添加附件
	private PropertiesLoader propertiesLoder = new PropertiesLoader("ttcity.properties");
	public MailBean(String toEmail, String emailTitle,
			String emailContent) {
		super();
		this.toEmail = toEmail;
		this.fromEmail = propertiesLoder.getProperty("mail.username", "cuisongliu@163.com");
		this.emailTitle = emailTitle;
		this.emailContent = emailContent;
	}
	public MailBean(String toEmail, String emailTitle,
			String emailContent, File[] sendFiles) {
		super();
		this.toEmail = toEmail;
		this.fromEmail = propertiesLoder.getProperty("mail.username", "cuisongliu@163.com");
		this.emailTitle = emailTitle;
		this.emailContent = emailContent;
		this.sendFiles = sendFiles;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	@SuppressWarnings("unused")
	private void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getEmailTitle() {
		return emailTitle;
	}
	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	public File[] getSendFiles() {
		return sendFiles;
	}
	public void setSendFiles(File[] sendFiles) {
		this.sendFiles = sendFiles;
	}
	
}
