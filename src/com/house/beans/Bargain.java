package com.house.beans;

public class Bargain {
	private String buyerId;
	private String houseId;
	private String account;
	private String price;
	private String time;
	private String timeok;
	private String status;

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount() {
		return account;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimeok() {
		return timeok;
	}

	public void setTimeok(String timeok) {
		this.timeok = timeok;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTimeOkYear() {
		int result;
		result = Integer.valueOf(timeok.substring(0, 4));
		System.out.println("截取的年时间=" + result);
		return result;
	}

	public int getTimeOkMonth() {
		int result;
		result = Integer.valueOf(timeok.substring(5, 7));
		System.out.println("截取的月时间=" + result);
		return result;
	}

}
