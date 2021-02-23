package com.yzy.springcloud.entity;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class WxProduct {

	private Long productId;
	private Date creteTime;
	private String imgBigUrl;
	private String imgUrl;
	private BigDecimal price;
	private String productCode;
	private String productName;
	private Date updateTime;
	private String validStatus;

}