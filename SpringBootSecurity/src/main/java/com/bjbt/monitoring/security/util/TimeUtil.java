package com.bjbt.monitoring.security.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	/**
	 * 获取【yyyy-MM-dd HH:mm:ss】格式的当前时间（如 2018-06-22 10:14:21）
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
}
