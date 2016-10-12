package dao;

import java.sql.SQLException;
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

import bean.CWords;

/**
 * A data access object (DAO) providing persistence and search support for
 * CWords entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see bean.CWords
 * @author MyEclipse Persistence Tools
 */
public class CWordsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CWordsDAO.class);
	// property constants
	public static final String WORDS_CONTENT = "wordsContent";
	public static final String WORDS_DATE = "wordsDate";
	public static final String WORDS_STATUS = "wordsStatus";

	protected void initDao() {
		// do nothing
	}

	public void save(CWords transientInstance) {
		log.debug("saving CWords instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CWords persistentInstance) {
		log.debug("deleting CWords instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CWords findById(java.lang.Integer id) {
		log.debug("getting CWords instance with id: " + id);
		try {
			CWords instance = (CWords) getHibernateTemplate().get("bean.CWords", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CWords instance) {
		log.debug("finding CWords instance by example");
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
		log.debug("finding CWords instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from CWords as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByWordsContent(Object wordsContent) {
		return findByProperty(WORDS_CONTENT, wordsContent);
	}

	public List findByWordsDate(Object wordsDate) {
		return findByProperty(WORDS_DATE, wordsDate);
	}

	public List findByWordsStatus(Object wordsStatus) {
		return findByProperty(WORDS_STATUS, wordsStatus);
	}

	public int getCount(int customerid, int adminid, String keyword) {
		String hql = "select count(*) from CWords as model where 1=1 ";
		if (customerid > 0) {
			System.out.println("---->customerid:" + customerid);
			hql = hql + " and model.customer.customerId = " + customerid;
		}
		if (adminid > 0) {
			System.out.println("---->adminid:" + adminid);
			hql = hql + " and model.admin.adminId = " + adminid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.wordsContent like '%" + keyword + "%' or model.wordsDate like '%" + keyword
					+ "%' or model.admin.adminAccount like '%" + keyword + "%' or model.admin.adminName like '%" + keyword
					+ "%' or model.customer.customerPhone like '%" + keyword + "%')";
		}

		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}

	public List findAll(int customerid, int adminid, String keyword, final int start, final int length) {
		String hql = "from CWords as model where 1=1 ";
		if (customerid > 0) {
			System.out.println("---->customerid:" + customerid);
			hql = hql + " and model.customer.customerId = " + customerid;
		}
		if (adminid > 0) {
			System.out.println("---->adminid:" + adminid);
			hql = hql + " and model.admin.adminId = " + adminid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.wordsContent like '%" + keyword + "%' or model.wordsDate like '%" + keyword
					+ "%' or model.admin.adminAccount like '%" + keyword + "%' or model.admin.adminName like '%" + keyword
					+ "%' or model.customer.customerPhone like '%" + keyword + "%')";
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

	public CWords merge(CWords detachedInstance) {
		log.debug("merging CWords instance");
		try {
			CWords result = (CWords) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CWords instance) {
		log.debug("attaching dirty CWords instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CWords instance) {
		log.debug("attaching clean CWords instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CWordsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CWordsDAO) ctx.getBean("CWordsDAO");
	}
}