package com.house.beans;

public class SellerAccount {

	private String sellerPassword;
	private String sellerAccount;
	private int manageraccount;

	public SellerAccount() {

	}

	public SellerAccount(int sellerID, String password, String account, int manageraccount) {
		this.sellerPassword = password;
		this.sellerAccount = account;
		this.manageraccount = manageraccount;
	}

	public String getAccount() {
		return sellerAccount;
	}

	public void setAccount(String account) {
		this.sellerAccount = account;
	}

	public String getPassword() {
		return sellerPassword;
	}

	public void setPassword(String password) {
		this.sellerPassword = password;
	}

	public int getManagerAccount() {
		return manageraccount;
	}

	public void setManagerAccount(int managerID) {
		this.manageraccount = managerID;
	}

}
