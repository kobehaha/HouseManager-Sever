package com.house.beans;

public class Buyer {

	

	private int buyerID;
	private String name;
	private String sex;
	private int age;
	private String houseId;
	private String phone;
	private String weiChat;
	private String createTime;
	private String url;
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getWeiChat() {
		return weiChat;
	}

	public void setWeiChat(String weiChat) {
		this.weiChat = weiChat;
	}

	public int getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(int buyerID) {
		this.buyerID = buyerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Buyer [buyerID=" + buyerID + ", name=" + name + ", sex=" + sex + ", age=" + age + ", phone=" + phone
				+ ", weiChat=" + weiChat + "  ,url= " + url + "]";
	}

}
