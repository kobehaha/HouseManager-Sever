package com.house.beans;

public class House {

	private String houseID;
	private String name;
	private String location;
	private double size;
	private int years;
	private double price;
	private String brief;
	private String picture;
	private int status;
	private int managerAccount;

	// public int getAccount() {
	// return account;
	// }
	//
	// public void setAccount(int account) {
	// this.account = account;
	// }

	public String getHouseID() {
		return houseID;
	}

	public int getManagerAccount() {
		return managerAccount;
	}

	public void setManagerAccount(int managerAccount) {
		this.managerAccount = managerAccount;
	}

	public void setHouseID(String houseID) {
		this.houseID = houseID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "House [houseID=" + houseID + ", name=" + name + ", location=" + location + ", size=" + size + ", years="
				+ years + ", price=" + price + ", brief=" + brief + ", picture=" + picture + ", status=" + status + "]";
	}

}
