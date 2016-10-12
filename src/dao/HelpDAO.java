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

import bean.Help;

/**
 * A data access object (DAO) providing persistence and search support for Help
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see bean.Help
 * @author MyEclipse Persistence Tools
 */
public class HelpDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(HelpDAO.class);
	// property constants
	public static final String HELP_TITLE = "helpTitle";
	public static final String HELP_KEY_WORD = "helpKeyWord";
	public static final String HELP_DATE = "helpDate";
	public static final String HELP_CONTEXT = "helpContext";

	protected void initDao() {
		// do nothing
	}

	public void save(Help transientInstance) {
		log.debug("saving Help instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Help persistentInstance) {
		log.debug("deleting Help instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Help findById(java.lang.Integer id) {
		log.debug("getting Help instance with id: " + id);
		try {
			Help instance = (Help) getHibernateTemplate().get("bean.Help", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Help instance) {
		log.debug("finding Help instance by example");
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
		log.debug("finding Help instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Help as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByHelpTitle(Object helpTitle) {
		return findByProperty(HELP_TITLE, helpTitle);
	}

	public List findByHelpKeyWord(Object helpKeyWord) {
		return findByProperty(HELP_KEY_WORD, helpKeyWord);
	}

	public List findByHelpDate(Object helpDate) {
		return findByProperty(HELP_DATE, helpDate);
	}

	public List findByHelpContext(Object helpContext) {
		return findByProperty(HELP_CONTEXT, helpContext);
	}

	public int getCount(String keyword) {
		String hql = "select count(*) from Help as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.helpKeyWord like '%" + keyword + "%')";
		}

		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}

	public List findAll(String keyword, final int start, final int length) {
		String hql = "from Help as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.helpKeyWord like '%" + keyword + "%')";
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

	public Help merge(Help detachedInstance) {
		log.debug("merging Help instance");
		try {
			Help result = (Help) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Help instance) {
		log.debug("attaching dirty Help instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Help instance) {
		log.debug("attaching clean Help instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static HelpDAO getFromApplicationContext(ApplicationContext ctx) {
		return (HelpDAO) ctx.getBean("HelpDAO");
	}
}