package dao;

import java.sql.SQLException;
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

import bean.Customer;

/**
 * A data access object (DAO) providing persistence and search support for
 * Customer entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see bean.Customer
 * @author MyEclipse Persistence Tools
 */
public class CustomerDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CustomerDAO.class);
	// property constants
	public static final String CUSTOMER_PHONE = "customerPhone";
	public static final String CUSTOMER_PASSWORD = "customerPassword";
	public static final String CUSTOMER_MAIL = "customerMail";
	public static final String CUSTOMER_IMG = "customerImg";
	public static final String CUSTOMER_CODE = "customerCode";
	public static final String CUSTOMER_TOKEN = "customerToken";
	public static final String CUSTOMER_ARRAY = "customerArray";

	protected void initDao() {
		// do nothing
	}

	public void save(Customer transientInstance) {
		log.debug("saving Customer instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Customer persistentInstance) {
		log.debug("deleting Customer instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Customer findById(java.lang.Integer id) {
		log.debug("getting Customer instance with id: " + id);
		try {
			Customer instance = (Customer) getHibernateTemplate().get("bean.Customer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Customer instance) {
		log.debug("finding Customer instance by example");
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
		log.debug("finding Customer instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Customer as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCustomerPhone(Object customerPhone) {
		return findByProperty(CUSTOMER_PHONE, customerPhone);
	}

	public List findByCustomerPassword(Object customerPassword) {
		return findByProperty(CUSTOMER_PASSWORD, customerPassword);
	}

	public List findByCustomerMail(Object customerMail) {
		return findByProperty(CUSTOMER_MAIL, customerMail);
	}

	public List findByCustomerImg(Object customerImg) {
		return findByProperty(CUSTOMER_IMG, customerImg);
	}

	public List findByCustomerCode(Object customerCode) {
		return findByProperty(CUSTOMER_CODE, customerCode);
	}

	public List findByCustomerToken(Object customerToken) {
		return findByProperty(CUSTOMER_TOKEN, customerToken);
	}

	public List findByCustomerArray(Object customerArray) {
		return findByProperty(CUSTOMER_ARRAY, customerArray);
	}

	public int getCount(String keyword) {
		String hql = "select count(*) from Customer as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.customerPhone like '%" + keyword + "%' or model.customerMail like '%" + keyword + "%')";
		}

		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}

	public List findAll(String keyword, final int start, final int length) {
		String hql = "from Customer as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.customerPhone like '%" + keyword + "%' or model.customerMail like '%" + keyword + "%')";
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

	public Customer merge(Customer detachedInstance) {
		log.debug("merging Customer instance");
		try {
			Customer result = (Customer) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Customer instance) {
		log.debug("attaching dirty Customer instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Customer instance) {
		log.debug("attaching clean Customer instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CustomerDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CustomerDAO) ctx.getBean("CustomerDAO");
	}
}