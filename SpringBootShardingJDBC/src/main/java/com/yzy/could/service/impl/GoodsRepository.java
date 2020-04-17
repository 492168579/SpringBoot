package com.yzy.could.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yzy.could.entity.Goods;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {

	List<Goods> findAllByGoodsIdBetween(Long goodsId1, Long goodsId2);

	List<Goods> findAllByGoodsIdIn(List<Long> goodsIds);

}
