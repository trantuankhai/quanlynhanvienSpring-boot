package com.java5.quanlynhanvien.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class User {
	@Id
	@Column(name = "USER_NAME")
	private String user_name;
	@Column(name = "PASS_WORD")
	private String pass_word;
	@Column(name = "FULL_NAME")
	private String full_name;
	@Column(name = "isAdmin")
	private String isadmin;

	public String getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPass_word() {
		return pass_word;
	}

	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public User(String user_name, String pass_word, String full_name, String isadmin) {
		super();
		this.user_name = user_name;
		this.pass_word = pass_word;
		this.full_name = full_name;
		this.isadmin = isadmin;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

}
