package com.yzy.could.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private final static Logger log = LoggerFactory.getLogger(JsonUtil.class);
	private final static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Object对象转JSON字符串
	 * 
	 * @param recordUnit
	 * @return
	 */
	public static String jsonObjectStr(Object recordTrans) {

		String jsonStr = "";
		try {
			jsonStr = mapper.writeValueAsString(recordTrans);
		} catch (JsonProcessingException e) {
			log.error("", e);
		}
		return jsonStr;
	}

	public static <T> T json2Object(String json, Class<T> clas) {
		if (StringUtils.hasLength(json) == false)
			return null;

		T t = null;
		try {
			t = (T) mapper.readValue(json, clas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) t;
	}

}
