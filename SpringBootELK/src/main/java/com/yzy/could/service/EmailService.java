package com.yzy.could.service;


public interface EmailService {
	public void sendSimpleMail(String to, String subject, String content);

	public void sendHtmlMail(String to, String subject, String content);

	public void sendAttachmentsMail(String to, String subject, String content, String filePath);

	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

}
