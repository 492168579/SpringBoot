package com.yzy.could.listener;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.yzy.could.config.RabbitConfig;
import com.yzy.could.vo.ResultVO;

@Component
@RabbitListener(queues = RabbitConfig.TEST_QUEUE)
public class Consumer {
	/**
	 * @RabbitHandler 指定消息的处理方法
	 * @param message
	 */
	@RabbitHandler
	public void process(@Payload ResultVO resultVO) {
		System.out.println(Thread.currentThread().getName() + " 接收到来自test-Queue队列的消息：" + resultVO.toString());
	}

	// @RabbitHandler
	// public void onOrderMessage(@Payload SysConfigEntity order, @Headers
	// Map<String, Object> headers, Channel channel)
	// throws Exception {
	// // 消费者操作
	// try {
	// System.out.println("------收到消息，开始消费------");
	// System.out.println("订单ID：" + order.getId());
	//
	// Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
	// // 现在是手动确认消息 ACK
	// channel.basicAck(deliveryTag, false);
	// } finally {
	// channel.close();
	// }
	// }
}
