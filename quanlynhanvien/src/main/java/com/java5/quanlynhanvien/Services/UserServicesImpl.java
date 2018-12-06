package com.java5.quanlynhanvien.Services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java5.quanlynhanvien.DAO.UserDaoImpl;
import com.java5.quanlynhanvien.Security.JwtTokenProvider;
import com.java5.quanlynhanvien.model.User;

@Service
public class UserServicesImpl implements UserServices {
	@Autowired
	private UserDaoImpl userDao;
	@Autowired
	private JwtTokenProvider jwt;

	@Override
	public User getUser(String userName) {
		return userDao.getUser(userName);
	}

	@Override
	public String checkLogin(String userName, String passWord) {
		return userDao.checkLogin(userName, passWord);
	}

	@Override
	public User whoami(HttpServletRequest req) {
		return userDao.getUser(jwt.getUsername(jwt.resolveToken(req)));
	}

}
