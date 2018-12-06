package com.java5.quanlynhanvien.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java5.quanlynhanvien.Services.UserServicesImpl;
import com.java5.quanlynhanvien.model.User;

@CrossOrigin(origins ="http://localhost:8010")
@RestController
public class UserController {
	@Autowired
	private UserServicesImpl userServices;

	@RequestMapping(value = "User", method = RequestMethod.GET)
	@PreAuthorize("hasRole('admin')")
	public User getUser(@RequestParam("userName") String userName) {
		return userServices.getUser(userName);
	}

	@RequestMapping(value = "User/checkLogin", method = RequestMethod.GET)
	public String checkLogin(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
		return userServices.checkLogin(userName, passWord);
	}

}
