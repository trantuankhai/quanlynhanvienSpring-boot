package com.java5.quanlynhanvien.DAO;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.java5.quanlynhanvien.model.Records;
import com.java5.quanlynhanvien.model.Staffs;

@Repository
public class RecordsDaoImpl implements RecordsDAO {
	private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	private final Logger logger = LogManager.getLogger();
	private Transaction transaction = null;

// GET LIST RECORDS
	@Override
	@SuppressWarnings("unchecked")
	public List<Records> listRecords() {
		Session session = sessionFactory.openSession();
		List<Records> listRecords = null;
		try {
			transaction = session.beginTransaction();
			listRecords = session.createQuery("from Records").list();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.log(Level.toLevel("1"), e.toString());
			e.printStackTrace();
		} finally {
			session.close();

		}
		return listRecords;
	}

	/// ADD ONE RECORDS
	@Override
	public int addRecords(int manv, int type, String reason, String date) {
		Session session = sessionFactory.openSession();
		int id = -1;
		try {
			transaction = session.beginTransaction();
			Staffs staffs = session.get(Staffs.class, manv);
			if (staffs != null) {
				Records records = new Records(staffs, type, reason, date);
				id = (Integer) session.save(records);
				transaction.commit();
			} else {
				id = -1;
			}

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			logger.log(Level.toLevel("1"), e.toString());
			id = -1;
		} finally {
			session.close();
		}
		return id;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Records> recordsByIdStaffs(int id_staffs) {
		List<Records> records = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			Query<Records> query = session.createQuery("from Records where staffs.id_staffs = :idStaffs");
			query.setParameter("idStaffs", id_staffs);
			records = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();

			}
		} finally {
			session.close();
		}
		return records;
	}

}
