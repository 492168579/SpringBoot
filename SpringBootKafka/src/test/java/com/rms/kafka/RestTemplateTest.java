package com.rms.kafka;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.bjbt.monitoring.kafka.MonitoringKafkaApplication;
import com.bjbt.monitoring.kafka.util.FileUtil;
import com.bjbt.monitoring.kafka.util.ImageUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonitoringKafkaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateTest {
	public static Logger logger = LoggerFactory.getLogger(RestTemplateTest.class);
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * get请求
	 */
	//@Test
	public void getMethodTest() {
		String url = "http://localhost/powergrid/qrySmeter?imei={imei}";
		Map<String, Object> params = new HashMap<>();
		params.put("imei", "865439030219327");
		String result = restTemplate.getForObject(url, String.class, params);
		System.out.println(result);
	}
	/**
	 * post请求
	 * @throws Exception
	 */
//	@Test
	public void Test() throws Exception {
		String url = "http://localhost:8081/ammeter/kafka/snapResp01Consumer";
		Map<String, Object> params = new HashMap<>();
		byte[] imageByte = FileUtil.fileToByte("C:\\Users\\zj\\Desktop\\20180615144847.png");
		params.put("image", ImageUtil.byte2HexStr(imageByte));
		params.put("cellID", "小区ID");
		params.put("imei", "A0");
		params.put("lac", "865439030219327");
		params.put("msgType", "1");
		params.put("rect", "150.0-150.0-400.0-250.0");
		params.put("reqFlag", "1");
		params.put("serialNum", "20180615114200abcdefgh");
		params.put("signalLevel", "4");
		params.put("snapTime", "2018-06-15 08:23:21");
		params.put("temperature", "4324");
		params.put("voltage", "234");
		System.out.println(params);
		restTemplate.postForLocation(url, params);
	}
	@Test
	public void TestAlarmReq01Consumer() throws Exception {
		String url = "http://localhost:8081/ammeter/kafka/alarmReq01Consumer";
		Map<String, Object> params = new HashMap<>();
		params.put("serialNum", "20180615114200abcdefgh");
		params.put("imei", "A0");
		params.put("msgType", "2");
		params.put("reqFlag", "0");
		params.put("lac", "865439030219327");
		params.put("alarmType", "");
		params.put("cellID", "150");
		params.put("signalLevel", "1");
		params.put("voltage", "4");
		params.put("alartTime", "2018-06-15 08:23:21");
		params.put("temperature", "32.4");
		System.out.println(params);
		restTemplate.postForLocation(url, params);
	}
}
