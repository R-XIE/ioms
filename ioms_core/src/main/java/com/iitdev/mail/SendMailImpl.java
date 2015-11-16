package com.iitdev.mail;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.iitdev.exception.IitdevRuntimeException;


public class SendMailImpl implements SendMail {
	protected JavaMailSender javaMailSender;
	protected MimeMessage mimeMessage;
	private Logger log = Logger.getLogger(SendMailImpl.class);

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public MimeMessage getMimeMessage() {
		return mimeMessage;
	}

	public void setMimeMessage(MimeMessage mimeMessage) {
		this.mimeMessage = mimeMessage;
	}

	@Override
	public boolean sendSingleMail(MailBean mailBean)
			throws IitdevRuntimeException {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		try {
			mailMessage.setTo(mailBean.getToEmail());
			mailMessage.setFrom(mailBean.getFromEmail());
			mailMessage.setSubject(mailBean.getEmailTitle());
			mailMessage.setText(mailBean.getEmailContent());
			javaMailSender.send(mailMessage);
		} catch (Exception e) {
			log.error("发送失败", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean sendHTMLMail(MailBean mailBean) throws IitdevRuntimeException {
		//MimeMessage mailMessage = javaMailSender.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		MimeMessageHelper messageHelper = null;

		try {
			messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			messageHelper.setTo(mailBean.getToEmail());
			messageHelper.setFrom(mailBean.getFromEmail());
			messageHelper.setSubject(mailBean.getEmailTitle());
			// 邮件内容，注意加参数true，表示启用html格式
			messageHelper.setText(mailBean.getEmailContent(), true);
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			log.error("发送失败", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean sendAttachedFileMail(MailBean mailBean)
			throws IitdevRuntimeException {
		//MimeMessage mailMessage = javaMailSender.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		MimeMessageHelper messageHelper = null;

		try {
			messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			messageHelper.setTo(mailBean.getToEmail());
			messageHelper.setFrom(mailBean.getFromEmail());
			messageHelper.setSubject(mailBean.getEmailTitle());
			// 邮件内容，注意加参数true，表示启用html格式
			messageHelper.setText(mailBean.getEmailContent(), true);
			// 遍历文件上传
			for (File file : mailBean.getSendFiles()) {
				FileSystemResource fsr = new FileSystemResource(file);
				messageHelper.addAttachment(file.getName(), fsr);
			}
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			log.error("发送失败", e);
			return false;
		}
		return true;
	}

}
