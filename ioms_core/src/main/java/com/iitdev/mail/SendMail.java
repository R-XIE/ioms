package com.iitdev.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;

import com.iitdev.exception.IitdevRuntimeException;


public interface SendMail {
	public JavaMailSender getJavaMailSender();
	public MimeMessage getMimeMessage();
	/**
	 * 发送普通的email
	 * @param mailBean Email的实体类
	 * @return  true/false 发送成功/失败
	 * @throws BaseRuntimeException
	 */
	public boolean sendSingleMail(MailBean mailBean)throws IitdevRuntimeException;
	/**
	 * 发送HTML格式的email
	 * @param mailBean Email的实体类
	 * @return true/false 发送成功/失败
	 * @throws BaseRuntimeException
	 */
	public boolean sendHTMLMail(MailBean mailBean)throws IitdevRuntimeException;	
	/**
	 * 发送带附件的email
	 * @param mailBean Email的实体类
	 * @return true/false 发送成功/失败
	 * @throws BaseRuntimeException
	 */
	public boolean sendAttachedFileMail (MailBean mailBean)throws IitdevRuntimeException;

}
