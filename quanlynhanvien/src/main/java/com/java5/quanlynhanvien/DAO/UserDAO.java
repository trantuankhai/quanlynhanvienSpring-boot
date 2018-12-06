package com.java5.quanlynhanvien.DAO;

import com.java5.quanlynhanvien.model.User;

public interface UserDAO {
	public User getUser(String userName);

	public String checkLogin(String userName, String passWord);

}
