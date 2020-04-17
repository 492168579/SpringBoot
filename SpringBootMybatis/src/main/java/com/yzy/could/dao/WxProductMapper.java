package com.yzy.could.dao;

import com.yzy.could.entity.WxProduct;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface WxProductMapper {

	int deleteByPrimaryKey(Long productId);

	int insert(WxProduct record);

	WxProduct selectByPrimaryKey(Long productId);

	List<WxProduct> selectAll();

	int updateByPrimaryKey(WxProduct record);
}