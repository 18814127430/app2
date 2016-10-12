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

import bean.Push;

/**
 * A data access object (DAO) providing persistence and search support for Push
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see bean.Push
 * @author MyEclipse Persistence Tools
 */
public class PushDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(PushDAO.class);
	// property constants
	public static final String PUSH_EDITOR = "pushEditor";
	public static final String PUSH_STATUS = "pushStatus";
	public static final String PUSH_TITLE = "pushTitle";
	public static final String PUSH_KEY_WORD = "pushKeyWord";
	public static final String PUSH_URL = "pushUrl";
	public static final String PUSH_CONTEXT = "pushContext";

	protected void initDao() {
		// do nothing
	}

	public int getCount(String keyword) {
		String hql = "select count(*) from Push as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.pushKeyWord like '%" + keyword + "%' or model.pushDate like '%" + keyword + "%')";
		}

		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}

	public List findAll(String keyword, final int start, final int length) {
		String hql = "from Push as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.pushKeyWord like '%" + keyword + "%' or model.pushDate like '%" + keyword + "%')";
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

	public void save(Push transientInstance) {
		log.debug("saving Push instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Push persistentInstance) {
		log.debug("deleting Push instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Push findById(java.lang.Integer id) {
		log.debug("getting Push instance with id: " + id);
		try {
			Push instance = (Push) getHibernateTemplate().get("bean.Push", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Push instance) {
		log.debug("finding Push instance by example");
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
		log.debug("finding Push instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Push as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPushEditor(Object pushEditor) {
		return findByProperty(PUSH_EDITOR, pushEditor);
	}

	public List findByPushStatus(Object pushStatus) {
		return findByProperty(PUSH_STATUS, pushStatus);
	}

	public List findByPushTitle(Object pushTitle) {
		return findByProperty(PUSH_TITLE, pushTitle);
	}

	public List findByPushKeyWord(Object pushKeyWord) {
		return findByProperty(PUSH_KEY_WORD, pushKeyWord);
	}

	public List findByPushUrl(Object pushUrl) {
		return findByProperty(PUSH_URL, pushUrl);
	}

	public List findByPushContext(Object pushContext) {
		return findByProperty(PUSH_CONTEXT, pushContext);
	}

	public List findAll() {
		log.debug("finding all Push instances");
		try {
			String queryString = "from Push";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Push merge(Push detachedInstance) {
		log.debug("merging Push instance");
		try {
			Push result = (Push) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Push instance) {
		log.debug("attaching dirty Push instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Push instance) {
		log.debug("attaching clean Push instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PushDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PushDAO) ctx.getBean("PushDAO");
	}
}