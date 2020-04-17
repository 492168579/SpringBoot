package com.bjbt.monitoring.mqtt.util;

import java.io.IOException;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json 工具类
 * 
 * @author yezhaoyi
 *
 */
public class JsonUtils {
	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * 对象转json
	 * 
	 * @param object
	 * @return
	 */
	public static String object2Json(Object object) {
		if (null != object) {

			try {
				return mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * json 转对象
	 * 
	 * @param strJson
	 * @param objectClass
	 * @return
	 */
	public static <T> T json2Ojbect(String strJson, Class<T> objectClass) {
		if (!StringUtils.isEmpty(strJson)) {
			try {
				return  mapper.readValue(strJson, objectClass);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return null;
	}
}
