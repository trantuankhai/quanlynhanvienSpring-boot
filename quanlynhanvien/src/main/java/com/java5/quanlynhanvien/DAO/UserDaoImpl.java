package com.java5.quanlynhanvien.DAO;

import org.apache.logging.log4j.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java5.quanlynhanvien.Security.JwtTokenProvider;
import com.java5.quanlynhanvien.model.User;

@Repository
public class UserDaoImpl implements UserDAO {
	private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	private final Logger logger = LogManager.getLogger();
	private Transaction transaction = null;
	@Autowired
	JwtTokenProvider token;

	@Override
	public User getUser(String userName) {
		User user = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			user = (User) session.get(User.class, userName);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			logger.log(Level.toLevel("1"), e.toString());

		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public String checkLogin(String userName, String passWord) {
		String result = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			User user = session.get(User.class, userName.trim());
			if (user != null) {
				if (user.getPass_word().equals(passWord.trim())) {
					result = token.createToken(user.getUser_name());
				} else {
					result = "error password";
				}

			} else {
				result = "error username";
			}
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
}
