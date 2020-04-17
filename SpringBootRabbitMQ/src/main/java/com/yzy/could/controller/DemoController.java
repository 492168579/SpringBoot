package com.yzy.could.controller;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yzy.could.vo.ResultVO;



@RestController
public class DemoController {
	@Autowired
	AmqpTemplate rabbitmqTemplate;


	@Resource(name = "testQueue") 
	private Queue queue;

	@PostMapping("api/send")
	public String send(@RequestBody ResultVO config) {
		// 发送消息
		rabbitmqTemplate.convertAndSend(queue.getName(), config);
		return "消息：" + config.toString() + ",已发送";
	}
	
}
