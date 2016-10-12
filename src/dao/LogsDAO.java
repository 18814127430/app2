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

import bean.Logs;

/**
 * A data access object (DAO) providing persistence and search support for Logs
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see bean.Logs
 * @author MyEclipse Persistence Tools
 */
public class LogsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(LogsDAO.class);
	// property constants
	public static final String LOGS_DATE = "logsDate";
	public static final String LOGS_CONTENT = "logsContent";

	protected void initDao() {
		// do nothing
	}

	public void save(Logs transientInstance) {
		log.debug("saving Logs instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Logs persistentInstance) {
		log.debug("deleting Logs instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Logs findById(java.lang.Integer id) {
		log.debug("getting Logs instance with id: " + id);
		try {
			Logs instance = (Logs) getHibernateTemplate().get("bean.Logs", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Logs instance) {
		log.debug("finding Logs instance by example");
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
		log.debug("finding Logs instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Logs as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLogsDate(Object logsDate) {
		return findByProperty(LOGS_DATE, logsDate);
	}

	public List findByLogsContent(Object logsContent) {
		return findByProperty(LOGS_CONTENT, logsContent);
	}

	public int getCount(String keyword) {
		String hql = "select count(*) from Logs as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.logsDate like '%" + keyword + "%' or model.logsContent like '%" + keyword + "%')";
		}

		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}

	public List findAll(String keyword, final int start, final int length) {
		String hql = "from Logs as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.logsDate like '%" + keyword + "%' or model.logsContent like '%" + keyword + "%')";
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

	public Logs merge(Logs detachedInstance) {
		log.debug("merging Logs instance");
		try {
			Logs result = (Logs) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Logs instance) {
		log.debug("attaching dirty Logs instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Logs instance) {
		log.debug("attaching clean Logs instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LogsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LogsDAO) ctx.getBean("LogsDAO");
	}
}