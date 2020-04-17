package com.yzy.could.controller.vo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.yzy.could.entity.WiselyMessage;
import com.yzy.could.entity.WiselyResponse;
import com.yzy.could.service.impl.WebSocketService;
import com.yzy.could.util.Constant;

@Controller
public class WsController {
	@Resource
	WebSocketService webSocketService;

	@MessageMapping(Constant.FORETOSERVERPATH) // @MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
	@SendTo(Constant.PRODUCERPATH) // 如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
	public WiselyResponse say(WiselyMessage message) throws Exception {
		List<String> users = new ArrayList<String>();
		users.add("d892bf12bf7d11e793b69c5c8e6f60fb");// 此处写死只是为了方便测试,此值需要对应页面中订阅个人消息的userId
		webSocketService.send2Users(users, new WiselyResponse("admin hello"));

		return new WiselyResponse("Welcome, " + message.getName() + "!");
	}
}
