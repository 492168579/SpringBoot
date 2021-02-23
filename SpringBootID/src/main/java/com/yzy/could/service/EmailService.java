package com.yzy.could.service;

import java.util.List;

public interface EmailService {
	/**
	 * 添加附件
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 */
	public void sendAttachmentsMail(String to, String subject, String content, List<String> filePaths);

}
