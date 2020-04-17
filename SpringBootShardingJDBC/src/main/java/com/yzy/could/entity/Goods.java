package com.yzy.could.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "goods")
@Data
public class Goods {
	@Id
	private Long goodsId;

	private String goodsName;

	private Long goodsType;
}
