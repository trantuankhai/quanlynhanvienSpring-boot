package com.java5.quanlynhanvien.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java5.quanlynhanvien.DAO.UserDaoImpl;


@Service
public class MyUserDetails implements UserDetailsService {

	@Autowired
	private UserDaoImpl userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.java5.quanlynhanvien.model.User account = userDao.getUser(username);
		if (account == null)
			return null;
		return User.withUsername(username).password(account.getPass_word()).roles(account.getIsadmin().toString()).accountExpired(false).accountLocked(false)
				.credentialsExpired(false).disabled(false).build();
	}

}
