package com.yzy.could.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yzy.could.entity.User;

@RestController
public class EasyExcleController {

	/**
	 * 文件上传
	 * <p>
	 * 1. 创建excel对应的实体对象 参照{@link UploadData}
	 * <p>
	 * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
	 * <p>
	 * 3. 直接读即可
	 */
	@PostMapping("upload")
	@ResponseBody
	public String upload(MultipartFile file) throws Exception {
		return "success";
	}
}
