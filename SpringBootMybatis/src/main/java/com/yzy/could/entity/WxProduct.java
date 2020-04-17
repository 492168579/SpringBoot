package com.yzy.could.entity;

import java.math.BigDecimal;
import java.util.Date;

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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Date getCreteTime() {
		return creteTime;
	}

	public void setCreteTime(Date creteTime) {
		this.creteTime = creteTime;
	}

	public String getImgBigUrl() {
		return imgBigUrl;
	}

	public void setImgBigUrl(String imgBigUrl) {
		this.imgBigUrl = imgBigUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
}