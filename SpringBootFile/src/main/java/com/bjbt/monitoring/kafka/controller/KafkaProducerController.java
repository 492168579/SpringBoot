package com.bjbt.monitoring.kafka.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bjbt.monitoring.kafka.config.KafkaConfig;
import com.bjbt.monitoring.kafka.util.JsonUtils;
import com.bjbt.monitoring.kafka.util.R;

@RestController
@RequestMapping("/kafkaProducer")
public class KafkaProducerController {
	private static Logger logger = LoggerFactory.getLogger(KafkaProducerController.class);
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * 手机app位置上报接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upPhoneLocation", method = RequestMethod.POST)
	public R upPhoneLocation(@RequestBody Map<String, Object> request) {
		try {
			kafkaTemplate.send(KafkaConfig.UPPHONELOCATION, JsonUtils.object2Json(request));
			return R.ok("数据上传成功");
		} catch (Exception e) {
			logger.error("数据上传失败！", e);
			return R.error(e.getMessage());
		}

	}

}
