package com.bjbt.monitoring.mqtt.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bjbt.monitoring.mqtt.component.MqttGateway;
import com.bjbt.monitoring.mqtt.util.JsonUtils;
import com.bjbt.monitoring.mqtt.util.R;

@RestController
@RequestMapping("/mqtt")
public class MqttController {
	private static Logger logger = LoggerFactory.getLogger(MqttController.class);
	@Autowired
	private MqttGateway mqttGateway;

	/**
	 * 手机app位置上报接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upPhoneLocation", method = RequestMethod.POST)
	public R upPhoneLocation(@RequestBody Map<String, Object> request) {
		try {
			mqttGateway.sendToMqtt(JsonUtils.object2Json(request));
			mqttGateway.sendToMqtt(JsonUtils.object2Json(request), "yzytest1");
			return R.ok("数据上传成功");
		} catch (Exception e) {
			logger.error("数据上传失败！", e);
			return R.error(e.getMessage());
		}

	}

}
