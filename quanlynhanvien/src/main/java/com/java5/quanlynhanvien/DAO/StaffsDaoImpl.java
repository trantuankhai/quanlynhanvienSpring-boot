package com.java5.quanlynhanvien.DAO;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.java5.quanlynhanvien.model.Staffs;
import com.java5.quanlynhanvien.model.StaffsExtend;

@Repository
public class StaffsDaoImpl implements StaffDAO {
	private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	private final Logger logger = LogManager.getLogger(StaffsDaoImpl.class);
	private Transaction transaction = null;

	// GET ALL STAFFS
	@SuppressWarnings("unchecked")
	@Override
	public List<Staffs> listStaffs() {
		Session session = sessionFactory.openSession();
		List<Staffs> staffs = null;
		try {
			transaction = session.beginTransaction();
			staffs = session.createQuery("from Staffs").list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();

			}
			logger.error(e);
		} finally {
			session.close();
		}
		return staffs;
	}

	// ADD STAFFS
	@Override
	public int addStaffs(Staffs staffs) {
		int kq = -1;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			kq = (java.lang.Integer) session.save(staffs);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();

			}
			logger.error(e);
			kq=-1;

		} finally {
			session.close();
		}
		return kq;
	}

// get departs  by id
	public Staffs getStaffsById(int id) {
		Session session = sessionFactory.openSession();
		Staffs staffs = null;
		try {
			transaction = session.beginTransaction();
			staffs = session.get(Staffs.class, id);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return staffs;
	}

	// EDIT STAFFS
	@Override
	public String editStaffs(int id, Staffs staffs) {
		Staffs staffs2 = getStaffsById(id);
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			if (staffs2 != null) {
				staffs.setId_staffs(staffs2.getId_staffs());
				session.update(staffs);
				transaction.commit();
				return "Success";
			} else {
				return "Nhân Viên Không Tồn Tại";
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();

			}
			logger.error(e);
			return "Fail";

		} finally {
			session.close();
		}
	}

	// DELETE STAFFS
	@Override
	public String deleteStaffs(int id) {
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			Staffs staffs2 = session.get(Staffs.class, id);
			session.delete(staffs2);
			transaction.commit();
			return "Success";
		} catch (HibernateException e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();

			}
			logger.error(e);
			return "Fail";

		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Staffs> layKhoangNhanVien(int max) {
		Session session = sessionFactory.openSession();
		List<Staffs> staffs = null;
		try {
			transaction = session.beginTransaction();
			staffs = session.createQuery("from Staffs").setFirstResult(0).setMaxResults(max).list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();

			}
			logger.error(e);
		} finally {
			session.close();
		}
		return staffs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long sumPage(long showOnePage) {
		long page = 0;
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			Query<Long> query = session.createQuery("select count(*) from Staffs");
			long sumPage = (long) query.uniqueResult();
			if (sumPage % showOnePage == 0) {
				page = sumPage / showOnePage;
			} else {
				page = sumPage / showOnePage + 1;
			}
			session.getTransaction().commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Staffs> chuyenHuongTrang(int min, int max) {
		Session session = sessionFactory.openSession();
		List<Staffs> staffs = null;
		try {
			transaction = session.beginTransaction();
			staffs = session.createQuery("from Staffs").setFirstResult(min).setMaxResults(max).list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();

			}
			logger.error(e);
		} finally {
			session.close();
		}
		return staffs;
	}

	@Override
	public List<Staffs> top10Staffs() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StaffsExtend> thongKeThanhTichNhanVien(int first, int End) {
		Session session = sessionFactory.openSession();
		List<StaffsExtend> list = null;
		StringBuilder subQuery = new StringBuilder();
		subQuery.append("SELECT");
		subQuery.append("	s.id_staffs,");
		subQuery.append("	d.name_departs,");
		subQuery.append("	s.name,");
		subQuery.append("	s.gender,");
		subQuery.append("	s.birthday,");
		subQuery.append("	s.photo,");
		subQuery.append("	s.email,");
		subQuery.append("	s.rank_staff,");
		subQuery.append("	s.phone,");
		subQuery.append("	s.salary,");
		subQuery.append("	s.note,");
		subQuery.append("	sum(r.records_Type) as tong");
		subQuery.append("	from");
		subQuery.append("	Staffs s");
		subQuery.append("	left join");
		subQuery.append("	Departs d");
		subQuery.append("	on");
		subQuery.append("	s.departs.id_departs");
		subQuery.append("	= d.id_departs");
		subQuery.append("	left join");
		subQuery.append("	Records r");
		subQuery.append("	on");
		subQuery.append("	s.id_staffs");
		subQuery.append("	= r.staffs.id_staffs");
		subQuery.append("	group by");
		subQuery.append(
				"	s.id_staffs,d.name_departs,s.name,s.gender,s.birthday,s.photo,s.email,s.rank_staff,s.phone,s.salary,s.note");
		subQuery.append("	ORDER BY");
		subQuery.append("	tong desc");
		System.out.println(subQuery.toString());
		try {
			transaction = session.beginTransaction();
			list = session.createQuery(subQuery.toString()).setFirstResult(first).setMaxResults(End).list();
			transaction.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return list;
	}
}
