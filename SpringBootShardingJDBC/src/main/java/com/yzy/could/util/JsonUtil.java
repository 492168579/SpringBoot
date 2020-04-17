package com.yzy.could.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

		private final static Logger log = LoggerFactory.getLogger(JsonUtil.class);

		/**
		 * Object对象转JSON字符串
		 * @param recordUnit
		 * @return
		 */
		public static String jsonObjectStr(Object recordTrans){
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = "";
			try {
				jsonStr = mapper.writeValueAsString(recordTrans);
			} catch (JsonProcessingException e) {
				log.error("", e);
			}
			return jsonStr;
		}
		
		/**
		 * 任意对象List，转换为json字符串的List
		 * @param list
		 * @return
		 */
		public static List<String> jsonStringList(List<?> list){
		List<String> jsonlist = new ArrayList<String>();
			for(Object o : list){
				String jsonstr = jsonObjectStr(o);
				jsonlist.add(jsonstr);
			}
			return jsonlist;
		}
}
