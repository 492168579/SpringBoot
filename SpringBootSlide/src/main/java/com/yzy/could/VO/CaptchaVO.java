package com.yzy.could.VO;

public class CaptchaVO {
	private byte[] bigPicture;
	private byte[] smallPicture;
	private String position;

	public byte[] getBigPicture() {
		return bigPicture;
	}

	public void setBigPicture(byte[] bigPicture) {
		this.bigPicture = bigPicture;
	}

	public byte[] getSmallPicture() {
		return smallPicture;
	}

	public void setSmallPicture(byte[] smallPicture) {
		this.smallPicture = smallPicture;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
