package com.java5.quanlynhanvien.Services;

import javax.servlet.http.HttpServletRequest;

import com.java5.quanlynhanvien.model.User;

public interface UserServices {
	public User getUser(String userName);

	public String checkLogin(String userName, String passWord);
	public User whoami(HttpServletRequest req);
}
