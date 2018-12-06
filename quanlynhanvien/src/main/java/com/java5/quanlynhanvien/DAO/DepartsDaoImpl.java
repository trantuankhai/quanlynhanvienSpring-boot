package com.java5.quanlynhanvien.DAO;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.java5.quanlynhanvien.model.Departs;
import com.java5.quanlynhanvien.model.DepartsExtend;

@Repository("DepartsDaoImpl")
public class DepartsDaoImpl implements DeapartsDAO {
	private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	private final Logger logger = LogManager.getLogger(DepartsDaoImpl.class);

	// GET DEPARTS
	@Override
	@SuppressWarnings("unchecked")
	public List<Departs> listDeparts() {
		Session session = sessionFactory.openSession();
		List<Departs> listDeparts = null;
		try {
			session.getTransaction().begin();
			Query<Departs> query = session.createQuery("from Departs");
			listDeparts = query.list();
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
		} finally {
			session.close();
		}
		return listDeparts;
	}

	// ADD DEPARTS
	@Override
	public int addDeparts(Departs departs) {
		Session session = sessionFactory.openSession();
		int id = -1;
		try {
			session.getTransaction().begin();
			id = (Integer) session.save(departs);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();

			}
			e.printStackTrace();
			logger.error(e);
			id = -1;
		} finally {
			session.close();
		}
		return id;

	}

	// EDIT DEPARTS
	@Override
	public String editDeparts(int id, String name) {
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			Departs departs = session.get(Departs.class, id);
			departs.setName_departs(name);
			session.update(departs);
			session.getTransaction().commit();
			return "Success";

		} catch (HibernateException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
			return "Fail";
		} finally {
			session.close();
		}
	}

	// DELETE DEPARTS
	@Override
	public String deleteDeparts(int id) {
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			Departs departs = session.get(Departs.class, id);
			session.delete(departs);
			session.getTransaction().commit();
			return "Success";
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();

			}
			logger.error(e);
			return "Fail";
		} finally {
			session.close();
		}

	}

	// GET COUNT PAGE
	@SuppressWarnings("unchecked")
	@Override
	public long sumPage(long showInPage) {
		long page = 0;
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			Query<Long> query = session.createQuery("select count(*) from Departs");
			long sumPage = (long) query.uniqueResult();
			if (sumPage % showInPage == 0) {
				page = sumPage / showInPage;
			} else {
				page = sumPage / showInPage + 1;
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

	// SHOW BETTEWN FROM X TO Y
	@SuppressWarnings("unchecked")
	@Override
	public List<Departs> showPagination(int min, int max) {
		Session session = sessionFactory.openSession();
		List<Departs> list = null;
		try {
			session.getTransaction().begin();
			Query<Departs> query = session.createQuery("from Departs ");
			query.setFirstResult(min);
			query.setMaxResults(max);
			list = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long countStaffsInDeparts(int id) {
		long sl = 0;
		StringBuilder subQuery = new StringBuilder();
		subQuery.append("select");
		subQuery.append("	count(*)");
		subQuery.append("	from Staffs");
		subQuery.append("	where departs.id_departs=:iddeparts ");
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			Query<Long> query = session.createQuery(subQuery.toString());
			query.setParameter("iddeparts", id);
			sl = (long) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		return sl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartsExtend> thanhTichPhongBan(int min, int max) {
		StringBuilder subQuery = new StringBuilder();
		subQuery.append("SELECT");
		subQuery.append(" 	d.id_departs, d.name_departs ,sum(r.records_Type) as tong");
		subQuery.append("	FROM");
		subQuery.append("	Departs d");
		subQuery.append("	LEFT JOIN");
		subQuery.append("	Staffs s");
		subQuery.append("	ON");
		subQuery.append("	s.departs.id_departs = d.id_departs");
		subQuery.append("	left join Records r");
		subQuery.append("	on s.id_staffs = r.staffs.id_staffs");
		subQuery.append("	group by");
		subQuery.append("	d.id_departs, d.name_departs");
		subQuery.append("	order by tong desc ");
		Session session = sessionFactory.openSession();
		List<DepartsExtend> listDeparts = null;
		try {
			session.getTransaction().begin();
			Query<DepartsExtend> query = session.createQuery(subQuery.toString());
			query.setFirstResult(min);
			query.setMaxResults(max);
			listDeparts = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error(e);
		} finally {
			session.close();
		}
		return listDeparts;
	}
}
