package com.yzy.could.service;

import java.io.InputStream;

public interface FTPClientService {

	public void download(String remoteFileName, String localFileName, String remoteDir);

	public void uploadFile(InputStream inputStream, String originName, String remoteDir) throws Exception;
}
