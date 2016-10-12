package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import utils.HibernateSessionFactory;
import bean.Admin;

/**
 * A data access object (DAO) providing persistence and search support for Admin
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see bean.Admin
 * @author MyEclipse Persistence Tools
 */
public class AdminDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AdminDAO.class);
	// property constants
	public static final String ADMIN_ACCOUNT = "adminAccount";
	public static final String ADMIN_PASSWORD = "adminPassword";
	public static final String ADMIN_NAME = "adminName";
	public static final String ADMIN_PHONE = "adminPhone";
	public static final String ADMIN_MAIL = "adminMail";
	public static final String ADMIN_REGION = "adminRegion";
	public static final String ADMIN_IMG = "adminImg";
	public static final String ADMIN_CLASS = "adminClass";
	public static final String ADMIN_START_DATE = "adminStartDate";

	protected void initDao() {
		// do nothing
	}

	public void save(Admin transientInstance) {
		log.debug("saving Admin instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Admin persistentInstance) {
		log.debug("deleting Admin instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Admin findById(java.lang.Integer id) {
		log.debug("getting Admin instance with id: " + id);
		try {
			Admin instance = (Admin) getHibernateTemplate().get("bean.Admin", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Admin instance) {
		log.debug("finding Admin instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Admin instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Admin as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAdminAccount(Object adminAccount) {
		return findByProperty(ADMIN_ACCOUNT, adminAccount);
	}

	public List findByAdminPassword(Object adminPassword) {
		return findByProperty(ADMIN_PASSWORD, adminPassword);
	}

	public List findByAdminName(Object adminName) {
		return findByProperty(ADMIN_NAME, adminName);
	}

	public List findByAdminPhone(Object adminPhone) {
		return findByProperty(ADMIN_PHONE, adminPhone);
	}

	public List findByAdminMail(Object adminMail) {
		return findByProperty(ADMIN_MAIL, adminMail);
	}

	public List findByAdminRegion(Object adminRegion) {
		return findByProperty(ADMIN_REGION, adminRegion);
	}

	public List findByAdminImg(Object adminImg) {
		return findByProperty(ADMIN_IMG, adminImg);
	}

	public List findByAdminClass(Object adminClass) {
		return findByProperty(ADMIN_CLASS, adminClass);
	}

	public List findByAdminStartDate(Object adminStartDate) {
		return findByProperty(ADMIN_START_DATE, adminStartDate);
	}

	public int getCount(String keyword) {
		String hql = "select count(*) from Admin as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.adminAccount like '%" + keyword + "%' or model.adminName like '%" + keyword + "%' or model.adminPhone like'%"
					+ keyword + "%' or model.adminMail like'%" + keyword + "%' or model.adminRegion like '%" + keyword
					+ "%' or model.adminClass like '%" + keyword + "%' or model.adminStartDate like '%" + keyword + "%')";
		}

		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}

	public List findAll(String keyword, final int start, final int length) {
		String hql = "from Admin as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.adminAccount like '%" + keyword + "%' or model.adminName like '%" + keyword + "%' or model.adminPhone like'%"
					+ keyword + "%' or model.adminMail like'%" + keyword + "%' or model.adminRegion like '%" + keyword
					+ "%' or model.adminClass like '%" + keyword + "%' or model.adminStartDate like '%" + keyword + "%')";
		}

		final String hql1 = hql;

		List listTable = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql1);
				System.out.println("------>hql:" + hql1);
				query.setFirstResult(start);
				query.setMaxResults(length);
				List list = query.list();
				return list;
			}
		});
		return listTable;
	}

	public Admin merge(Admin detachedInstance) {
		log.debug("merging Admin instance");
		try {
			Admin result = (Admin) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Admin instance) {
		log.debug("attaching dirty Admin instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Admin instance) {
		log.debug("attaching clean Admin instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdminDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AdminDAO) ctx.getBean("AdminDAO");
	}
}