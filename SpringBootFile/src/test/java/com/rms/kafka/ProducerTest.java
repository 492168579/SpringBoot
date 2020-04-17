package com.rms.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.bjbt.monitoring.kafka.MonitoringKafkaApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonitoringKafkaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProducerTest {
	public static Logger logger = LoggerFactory.getLogger(ProducerTest.class);
	public static final String SMETER_RSP_TOPIC = "kafka.topic.smeter-rsp";// kafka.topic.smeter-rsp主题
	private static final String SNAP_REPORT_TOPIC = "kafka.topic.snap-report";// kafka.topic.snap-report主题
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void produceSmeterRespMsg() {
		String msg = "{\"msgType\":5,\"imei\":\"865439030009215\",\"reqFlag\":0,\"result\":0,\"opts\":{\"status\":10}}";
		kafkaTemplate.send(SMETER_RSP_TOPIC, msg);
	}

	@Test
	public void ammeterCommand() {
		String msg = "{\r\n" + "	\"commandType\":1,\r\n" + "	\"batch\":1,\r\n" + "	\"localNetId\":35101,\r\n"
				+ "	\"areaId\":35103,\r\n" + "	\"countyId\":35105,\r\n" + "	\"executeTime\":\"2018-07-05\",\r\n"
				+ "	\"imei\":\"20180705\",\r\n" + "	\"commandContent\":\"2.0\"\r\n" + "}";
		kafkaTemplate.send("AmmeterCommand", msg);
	}

	@Test
	public void testSnapReport() {
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//			JSONObject parseObject = parseObject(jsonString);
//			HttpEntity<String> entity = new HttpEntity<String>(parseObject.toString(), headers);
//			String url = "http://localhost:8084/ammeter/kafka/snapResp01Consumer";
//			restTemplate.postForLocation(url, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
