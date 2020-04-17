package com.bjbt.monitoring.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.bjbt.monitoring.kafka.config.KafkaConfig;

/**
 * VersionResp01主题<br>
 * （版本升级返回）
 * 
 * @author zj
 *
 */
@Component
public class UpPhoneLocationConsumer {
	public static Logger logger = LoggerFactory.getLogger(UpPhoneLocationConsumer.class);

	@KafkaListener(topics = KafkaConfig.UPPHONELOCATION)
	public void listen(ConsumerRecord<String, String> record) throws Exception {
		System.out.println("=======================================" + record.topic());
		System.out.println("topic" + record.topic());
		System.out.println("topic" + record.topic());
		System.out.println("key:" + record.key());
		System.out.println("value:" + record.value());

	}

}
