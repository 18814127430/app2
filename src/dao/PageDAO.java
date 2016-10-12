package dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bean.Page;

/**
 * A data access object (DAO) providing persistence and search support for Page
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see bean.Page
 * @author MyEclipse Persistence Tools
 */
public class PageDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(PageDAO.class);
	// property constants
	public static final String PAGE_IMG = "pageImg";
	public static final String PAGE_HTML = "pageHtml";

	protected void initDao() {
		// do nothing
	}

	public void save(Page transientInstance) {
		log.debug("saving Page instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Page persistentInstance) {
		log.debug("deleting Page instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Page findById(java.lang.Integer id) {
		log.debug("getting Page instance with id: " + id);
		try {
			Page instance = (Page) getHibernateTemplate().get("bean.Page", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Page instance) {
		log.debug("finding Page instance by example");
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
		log.debug("finding Page instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Page as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPageImg(Object pageImg) {
		return findByProperty(PAGE_IMG, pageImg);
	}

	public List findByPageHtml(Object pageHtml) {
		return findByProperty(PAGE_HTML, pageHtml);
	}

	public List findAll() {
		log.debug("finding all Page instances");
		try {
			String queryString = "from Page";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Page merge(Page detachedInstance) {
		log.debug("merging Page instance");
		try {
			Page result = (Page) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Page instance) {
		log.debug("attaching dirty Page instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Page instance) {
		log.debug("attaching clean Page instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PageDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PageDAO) ctx.getBean("PageDAO");
	}
}