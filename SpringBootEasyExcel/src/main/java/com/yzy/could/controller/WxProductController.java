package com.yzy.could.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yzy.could.entity.WxProduct;
import com.yzy.could.service.WxProductService;

@RestController
@RequestMapping("/wxProduct")
public class WxProductController {
	@Autowired
	private WxProductService wxProductService;
	
	
	@GetMapping("/findByPage")
	public PageInfo<WxProduct> findByPage() {
		return wxProductService.findByPage();
	}


}
