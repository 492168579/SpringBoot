package com.yzy.could.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Base64;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzy.could.SpringBootFTPApplication;
import com.yzy.could.config.FTPConfig;
import com.yzy.could.service.FTPClientService;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.ftp.Ftp;

@Service("ftpClienService'")
public class FTPClientServiceImpl implements FTPClientService {
	final Base64.Decoder decoder = Base64.getDecoder();
	final Base64.Encoder encoder = Base64.getEncoder();

	private static Logger log = LoggerFactory.getLogger(FTPClientServiceImpl.class);

	@Autowired 
	FTPConfig ftpConfig ;


	@Override
	public void download(String remoteFileName, String localFileName, String remoteDir) {
		Ftp ftp = new Ftp(ftpConfig.getHost(),ftpConfig.getPort(),ftpConfig.getUsername(),ftpConfig.getPassword());
		ftp.download("/home/ftpuser01/logs/aa", "一体机.postman_collection.json", FileUtil.file("D:/一体机.postman_collection.json"));
	}

	@Override
	public void uploadFile(InputStream inputStream, String originName, String remoteDir) throws Exception {
	    InetAddress ip = InetAddress.getLocalHost();
	    log.info("ip:{}",ip.getHostAddress());
		Ftp ftp = new Ftp(ftpConfig.getHost(),ftpConfig.getPort(),ftpConfig.getUsername(),ftpConfig.getPassword());
		ftp.upload(remoteDir, originName,inputStream);
		ftp.close();
	}

}
