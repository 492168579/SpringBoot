package com.yzy.could.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzy.could.VO.CaptchaVO;
import com.yzy.could.component.RedisUtils;
import com.yzy.could.util.Math;
import com.yzy.could.util.R;
import com.yzy.could.util.VerifyImageUtil;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {
	@Autowired
	private RedisUtils redisUtils;

	@GetMapping("/init")
	public R init(String uuid) {
		Map<String, byte[]> pictureMap;
		File templateFile; // 模板图片
		File targetFile; //
		try {
			Random random = new Random();
			int templateNo = random.nextInt(4) + 1;
			int targetNo = random.nextInt(20) + 1;
			InputStream stream = getClass().getClassLoader()
					.getResourceAsStream("static/templates/" + templateNo + ".png");
			templateFile = new File(templateNo + ".png");
			FileUtils.copyInputStreamToFile(stream, templateFile);
			stream = getClass().getClassLoader().getResourceAsStream("static/targets/" + targetNo + ".jpg");
			targetFile = new File(targetNo + ".jpg");
			FileUtils.copyInputStreamToFile(stream, targetFile);
			pictureMap = VerifyImageUtil.pictureTemplatesCut(templateFile, targetFile, "png", "jpg");
			byte[] oriCopyImages = pictureMap.get("oriCopyImage");
			byte[] newImages = pictureMap.get("newImage");
			CaptchaVO captchaVO = new CaptchaVO();
			captchaVO.setBigPicture(oriCopyImages);
			captchaVO.setSmallPicture(newImages);
			captchaVO.setPosition(String.valueOf(VerifyImageUtil.getX()));
			System.out.println(VerifyImageUtil.getyPercent());
			System.out.println(VerifyImageUtil.getY());
			System.out.println(VerifyImageUtil.getxPercent());
			System.out.println(VerifyImageUtil.getX());
			redisUtils.set(uuid, captchaVO, 60 * 5);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.ok();
	}

	@GetMapping("/captchaSmall.png")
	public void captcha(HttpServletResponse response, String uuid) throws Exception {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/png");
		CaptchaVO captchaVO = redisUtils.get(uuid, CaptchaVO.class);
		byte[] smallPicture = captchaVO.getSmallPicture();
		ServletOutputStream out = response.getOutputStream();
		out.write(smallPicture);
		out.flush();
		out.close();
	}

	@GetMapping("/captchaBig.png")
	public void captchaBig(HttpServletResponse response, String uuid) throws Exception {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/png");
		CaptchaVO captchaVO = redisUtils.get(uuid, CaptchaVO.class);
		byte[] bigPicture = captchaVO.getBigPicture();
		ServletOutputStream out = response.getOutputStream();
		out.write(bigPicture);
		out.flush();
		out.close();
	}
	
	@PostMapping("/check")
	public R check(@RequestBody Map<String, Object> params) throws Exception {
		String uuid = (String) params.get("uuid");
		Integer code = (Integer) params.get("code");
		CaptchaVO captchaVO = redisUtils.get(uuid, CaptchaVO.class);
		String position = captchaVO.getPosition();
		int max = Integer.parseInt(position) + 1;
		int min = Integer.parseInt(position) - 1;
		String interval = "[" + min + "," + max + "]";
		boolean inTheInterval = Math.isInTheInterval(code.toString(), interval);
		if (inTheInterval) {
			return R.ok();
		} else {
			return R.error();
		}
	}

}
