package com.yzy.could.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.BlockLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yzy.could.component.HdfsService;
import com.yzy.could.entity.User;
import com.yzy.springcloud.util.R;

@RestController
@RequestMapping("/hadoop/hdfs")
public class HdfsController {

	private static Logger LOGGER = LoggerFactory.getLogger(HdfsController.class);

	@Autowired
	private HdfsService hdfsService;

	/**
	 * 创建文件夹
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "mkdir", method = RequestMethod.POST)
	@ResponseBody
	public R mkdir(@RequestParam("path") String path) throws Exception {
		if (StringUtils.isEmpty(path)) {
			LOGGER.debug("请求参数为空");
			return R.error("请求参数为空");
		}
		// 创建空文件夹
		boolean isOk = hdfsService.mkdir(path);
		if (isOk) {
			LOGGER.debug("文件夹创建成功");
			return R.ok("文件夹创建成功");
		} else {
			LOGGER.debug("文件夹创建失败");
			return R.error("文件夹创建失败");
		}
	}

	/**
	 * 读取HDFS目录信息
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/readPathInfo")
	public R readPathInfo(@RequestParam("path") String path) throws Exception {
		List<Map<String, Object>> list = hdfsService.readPathInfo(path);
		return R.ok("读取HDFS目录信息成功").put("data", list);
	}

	/**
	 * 获取HDFS文件在集群中的位置
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getFileBlockLocations")
	public R getFileBlockLocations(@RequestParam("path") String path) throws Exception {
		BlockLocation[] blockLocations = hdfsService.getFileBlockLocations(path);
		return R.ok("获取HDFS文件在集群中的位置").put("data", blockLocations);
	}

	/**
	 * 创建文件
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/createFile")
	public R createFile(@RequestParam("path") String path, @RequestParam("file") MultipartFile file) throws Exception {
		if (StringUtils.isEmpty(path) || null == file.getBytes()) {
			return R.error("请求参数为空");

		}
		hdfsService.createFile(path, file);
		return R.ok("创建文件成功");
	}

	/**
	 * 读取HDFS文件内容
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/readFile")
	public R readFile(@RequestParam("path") String path) throws Exception {
		String targetPath = hdfsService.readFile(path);
		return R.ok("读取HDFS文件内容" + targetPath);
	}

	/**
	 * 读取HDFS文件转换成Byte类型
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/openFileToBytes")
	public R openFileToBytes(@RequestParam("path") String path) throws Exception {
		byte[] files = hdfsService.openFileToBytes(path);
		return R.ok("读取HDFS文件内容").put("data", files);
	}

	/**
	 * 读取HDFS文件装换成User对象
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/openFileToUser")
	public R openFileToUser(@RequestParam("path") String path) throws Exception {
		User user = hdfsService.openFileToObject(path, User.class);
		return R.ok("读取HDFS文件装换成User对象").put("user", user);
	}

	/**
	 * 读取文件列表
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/listFile")
	public R listFile(@RequestParam("path") String path) throws Exception {
		if (StringUtils.isEmpty(path)) {
			return R.error("请求参数为空");
		}
		List<Map<String, String>> returnList = hdfsService.listFile(path);
		return R.ok("读取文件列表成功").put("data", returnList);
	}

	/**
	 * 重命名文件
	 * 
	 * @param oldName
	 * @param newName
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/renameFile")
	public R renameFile(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName)
			throws Exception {
		if (StringUtils.isEmpty(oldName) || StringUtils.isEmpty(newName)) {
			return R.error("请求参数为空");
		}
		boolean isOk = hdfsService.renameFile(oldName, newName);
		if (isOk) {
			return R.ok("文件重命名成功");
		} else {
			return R.error("文件重命名失败");
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/deleteFile")
	public R deleteFile(@RequestParam("path") String path) throws Exception {
		boolean isOk = hdfsService.deleteFile(path);
		if (isOk) {
			return R.ok("delete file success");
		} else {
			return R.ok("delete file fail");
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param path
	 * @param uploadPath
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/uploadFile")
	public R uploadFile(@RequestParam("path") String path, @RequestParam("uploadPath") String uploadPath)
			throws Exception {
		hdfsService.uploadFile(path, uploadPath);
		return R.ok("upload file success");
	}

	/**
	 * 下载文件
	 * 
	 * @param path
	 * @param downloadPath
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/downloadFile")
	public R downloadFile(@RequestParam("path") String path, @RequestParam("downloadPath") String downloadPath)
			throws Exception {
		hdfsService.downloadFile(path, downloadPath);
		return R.ok("download file success");
	}

	/**
	 * HDFS文件复制
	 * 
	 * @param sourcePath
	 * @param targetPath
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/copyFile")
	public R copyFile(@RequestParam("sourcePath") String sourcePath, @RequestParam("targetPath") String targetPath)
			throws Exception {
		hdfsService.copyFile(sourcePath, targetPath);
		return R.ok("copy file success");
	}

	/**
	 * 查看文件是否已存在
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/existFile")
	public R existFile(@RequestParam("path") String path) throws Exception {
		boolean isExist = hdfsService.existFile(path);
		return R.ok("file isExist: " + isExist);
	}

}
