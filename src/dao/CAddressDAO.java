package dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bean.CAddress;

/**
 * A data access object (DAO) providing persistence and search support for
 * CAddress entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see bean.CAddress
 * @author MyEclipse Persistence Tools
 */
public class CAddressDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CAddressDAO.class);
	// property constants
	public static final String ADDRESS_NAME = "addressName";
	public static final String ADDRESS_PHONE = "addressPhone";
	public static final String ADDRESS_PROVINCE = "addressProvince";
	public static final String ADDRESS_CITY = "addressCity";
	public static final String ADDRESS_STREET = "addressStreet";
	public static final String ADDRESS_DETIAL = "addressDetial";

	protected void initDao() {
		// do nothing
	}

	public int getCount(int customerid, String keyword) {
		System.out.println("customerid:" + customerid + "----keyword:" + keyword);
		String hql = "select count(*) from CAddress as model where 1=1 ";
		if (!keyword.equals("")) {
			hql = hql + " and (model.addressName like '%" + keyword + "%' or model.addressPhone like '%" + keyword
					+ "%' or model.addressProvince like '%" + keyword + "%' or model.addressCity like '%" + keyword
					+ "%' or model.addressStreet like '%" + keyword + "%' or model.addressDetial like '%" + keyword
					+ "%' or model.addressDate like '%" + keyword  + "%')";
		}
		if (customerid > 0) {
			hql = hql + " and model.customer.customerId = " + customerid;
		}

		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("intValue:" + count.intValue());
		return count.intValue();
	}

	public List findAll(final int customerid, String keyword, final int start, final int length) {
		System.out.println("customerid:" + customerid + "----keyword:" + keyword);
		String hql = "from CAddress as model where 1=1 ";
		if (!keyword.equals("")) {
			hql = hql + " and (model.addressName like '%" + keyword + "%' or model.addressPhone like '%" + keyword
					+ "%' or model.addressProvince like '%" + keyword + "%' or model.addressCity like '%" + keyword
					+ "%' or model.addressStreet like '%" + keyword + "%' or model.addressDetial like '%" + keyword
					+ "%' or model.addressDate like '%" + keyword  + "%')";
		}
		if (customerid > 0) {
			hql = hql + " and model.customer.customerId = " + customerid;
		}

		final String hql1 = hql;

		List listTable = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql1);
				System.out.println("-------->hql:" + hql1);
				query.setFirstResult(start);
				query.setMaxResults(length);
				List list = query.list();
				return list;
			}
		});
		return listTable;
	}

	public void save(CAddress transientInstance) {
		log.debug("saving CAddress instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CAddress persistentInstance) {
		log.debug("deleting CAddress instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CAddress findById(java.lang.Integer id) {
		log.debug("getting CAddress instance with id: " + id);
		try {
			CAddress instance = (CAddress) getHibernateTemplate().get("bean.CAddress", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CAddress instance) {
		log.debug("finding CAddress instance by example");
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
		log.debug("finding CAddress instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from CAddress as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAddressName(Object addressName) {
		return findByProperty(ADDRESS_NAME, addressName);
	}

	public List findByAddressPhone(Object addressPhone) {
		return findByProperty(ADDRESS_PHONE, addressPhone);
	}

	public List findByAddressProvince(Object addressProvince) {
		return findByProperty(ADDRESS_PROVINCE, addressProvince);
	}

	public List findByAddressCity(Object addressCity) {
		return findByProperty(ADDRESS_CITY, addressCity);
	}

	public List findByAddressStreet(Object addressStreet) {
		return findByProperty(ADDRESS_STREET, addressStreet);
	}

	public List findByAddressDetial(Object addressDetial) {
		return findByProperty(ADDRESS_DETIAL, addressDetial);
	}

	public List findAll() {
		log.debug("finding all CAddress instances");
		try {
			String queryString = "from CAddress";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CAddress merge(CAddress detachedInstance) {
		log.debug("merging CAddress instance");
		try {
			CAddress result = (CAddress) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CAddress instance) {
		log.debug("attaching dirty CAddress instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CAddress instance) {
		log.debug("attaching clean CAddress instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CAddressDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CAddressDAO) ctx.getBean("CAddressDAO");
	}
}