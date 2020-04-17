package com.yzy.could.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzy.could.dao.WxProductMapper;
import com.yzy.could.entity.WxProduct;
import com.yzy.could.service.WxProductService;
@Service
public class WxProductServiceImpl implements WxProductService{
	
	@Autowired
	private WxProductMapper wxProductMapper ;
	@Override
	public int deleteByPrimaryKey(Long productId) {
		return wxProductMapper.deleteByPrimaryKey(productId);
	}

	@Override
	public int insert(WxProduct record) {
		return wxProductMapper.insert(record);
	}

	@Override
	public WxProduct selectByPrimaryKey(Long productId) {
		return wxProductMapper.selectByPrimaryKey(productId);
	}

	@Override
	public List<WxProduct> selectAll() {
		return wxProductMapper.selectAll();
	}

	@Override
	public PageInfo<WxProduct> findByPage() {
		PageHelper.startPage(1,10);
		List<WxProduct> products = wxProductMapper.selectAll();
	    PageInfo<WxProduct> appsPageInfo = new PageInfo<WxProduct>(products);
		return appsPageInfo;
	}

}
