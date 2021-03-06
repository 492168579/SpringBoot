package com.yzy.could.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzy.could.entity.Goods;
import com.yzy.could.service.impl.GoodsRepository;

@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private GoodsRepository goodsRepository;

	@GetMapping("/save")
	public String save() {
		for (int i = 1; i <= 40; i++) {
			Goods goods = new Goods();
			goods.setGoodsId((long) i);
			goods.setGoodsName("shangpin" + i);
			goods.setGoodsType((long) (i + 1));
			goodsRepository.save(goods);
		}
		return "success";
	}

	@GetMapping("/select")
	public String select() {
		return goodsRepository.findAll().toString();
	}

	@GetMapping("/delete")
	public void delete() {
		goodsRepository.deleteAll();
	}

	@GetMapping("/query1")
	public Object query1() {
		return goodsRepository.findAllByGoodsIdBetween(10L, 30L);
	}

	@GetMapping("/query2")
	public Object query2() {
		List<Long> goodsIds = new ArrayList<Long>();
		goodsIds.add(10L);
		goodsIds.add(15L);
		goodsIds.add(20L);
		goodsIds.add(25L);
		return goodsRepository.findAllByGoodsIdIn(goodsIds);
	}

}
