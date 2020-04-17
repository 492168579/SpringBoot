package com.yzy.could.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yzy.could.entity.WxProduct;

public interface  WxProductService {
	int deleteByPrimaryKey(Long productId);

	int insert(WxProduct record);

	WxProduct selectByPrimaryKey(Long productId);

	List<WxProduct> selectAll();
	
	
    PageInfo<WxProduct> findByPage() ;
}
