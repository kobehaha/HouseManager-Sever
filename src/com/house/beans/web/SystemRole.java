package com.house.beans.web;

public class SystemRole {

	private int id;
	private String account;
	private String password;

	public SystemRole() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswrod() {
		return password;
	}

	public void setPasswrod(String passwrod) {
		this.password = passwrod;
	}

	@Override
	public String toString() {
		return "System info id=" + id + "   account=" + account + "   password=" + password;

	}

}
