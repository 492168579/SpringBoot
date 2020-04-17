package com.yzy.could.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	public static final String TEST_QUEUE = "demo-Queue";

	/**
	 * 定义一个名为：testQueue 的队列
	 * 
	 * @return
	 */
	@Bean(name = "testQueue")
	public Queue queue() {
		return new Queue(RabbitConfig.TEST_QUEUE);
	}
}
