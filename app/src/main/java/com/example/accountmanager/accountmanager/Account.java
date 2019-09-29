package com.example.accountmanager.accountmanager;

public class Account {

	private int id;
	private String name;
	private String account;
	private String password;

	public Account() {
		
	}

	public Account(String name, String account, String password) {
		this.name = name;
		this.account = account;
		this.password = password;
	}
	
	public Account(int id, String name, String account, String password) {
		this.id = id;
		this.name = name;
		this.account = account;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
