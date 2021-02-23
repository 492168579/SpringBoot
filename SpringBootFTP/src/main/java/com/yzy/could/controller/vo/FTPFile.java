package com.yzy.could.controller.vo;

import org.springframework.web.multipart.MultipartFile;

public class FTPFile {

	private MultipartFile file;

	private String remoteDir;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getRemoteDir() {
		return remoteDir;
	}

	public void setRemoteDir(String remoteDir) {
		this.remoteDir = remoteDir;
	}
	
	
}
