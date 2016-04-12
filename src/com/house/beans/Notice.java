package com.house.beans;

import java.util.Date;

public class Notice {

	private int id;
	private String content;
	private Date date;
	private int account;

	public int getAccount(){
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
