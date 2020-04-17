package com.yzy.could.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzy.could.service.EmailService;

@RestController
public class EmailController {
	@Autowired
	private EmailService emailService;
	
	
	@GetMapping("/email")
	public int findById() {
		emailService.sendSimpleMail("492168579@qq.com", "你好", "你好");
		return 1 ;
	}
	

}
