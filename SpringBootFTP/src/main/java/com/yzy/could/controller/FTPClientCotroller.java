package com.yzy.could.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yzy.could.controller.vo.FTPFile;
import com.yzy.could.service.FTPClientService;
import com.yzy.could.service.impl.FTPClientServiceImpl;
import com.yzy.could.util.R;

@RestController
@RequestMapping("/ftp")
public class FTPClientCotroller {

	private static Logger log = LoggerFactory.getLogger(FTPClientCotroller.class);

	@Autowired
	private FTPClientService ftpClientService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public R upload(FTPFile request) {
		try {
			ftpClientService.uploadFile(request.getFile().getInputStream(), request.getFile().getOriginalFilename(),
					request.getRemoteDir());
			return R.error("数据上传成功！");
		} catch (Exception e) {
			log.error("数据上传失败！", e);
			return R.error(e.getMessage());
		}

	}

	@RequestMapping(value = "/download", method = RequestMethod.POST)
	@ResponseBody
	public R download() {
		try {
			ftpClientService.download(null,null,null);
			return R.error("数据下载成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(e.getMessage());
		}

	}
}
