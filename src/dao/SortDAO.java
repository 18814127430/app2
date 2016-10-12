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

import utils.HibernateSessionFactory;
import bean.Sort;

/**
 * A data access object (DAO) providing persistence and search support for Sort
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see bean.Sort
 * @author MyEclipse Persistence Tools
 */
public class SortDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(SortDAO.class);
	// property constants
	public static final String SORT_CLASS = "sortClass";
	public static final String SORT_NAME = "sortName";
	public static final String SORT_IMG_NAME = "sortImgName";
	public static final String SORT_IMG_PATH = "sortImgPath";
	public static final String SORT_IMG_EXTENSION = "sortImgExtension";

	protected void initDao() {
		// do nothing
	}

	public void save(Sort transientInstance) {
		log.debug("saving Sort instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Sort persistentInstance) {
		log.debug("deleting Sort instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Sort findById(java.lang.Integer id) {
		log.debug("getting Sort instance with id: " + id);
		try {
			Sort instance = (Sort) getHibernateTemplate().get("bean.Sort", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Sort instance) {
		log.debug("finding Sort instance by example");
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
		log.debug("finding Sort instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Sort as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySortClass(Object sortClass) {
		return findByProperty(SORT_CLASS, sortClass);
	}

	public List findBySortName(Object sortName) {
		return findByProperty(SORT_NAME, sortName);
	}

	public List findBySortImgName(Object sortImgName) {
		return findByProperty(SORT_IMG_NAME, sortImgName);
	}

	public List findBySortImgPath(Object sortImgPath) {
		return findByProperty(SORT_IMG_PATH, sortImgPath);
	}

	public List findBySortImgExtension(Object sortImgExtension) {
		return findByProperty(SORT_IMG_EXTENSION, sortImgExtension);
	}

	public List findAll() {
		log.debug("finding all Sort instances");
		try {
			String queryString = "from Sort";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public int getCount(int sortid, String keyword) {
		String hql = "select count(*) from Sort as model where 1=1 ";
		if (sortid > 0) {
			System.out.println("---->sortid:" + sortid);
			hql = hql + " and model.sort.sortId = " + sortid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.sortName like '%" + keyword + "%')";
		}

		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}

	public List findAll(int sortid) {
		System.out.println("findBySortID:" + sortid);
		String hql = "from Sort as model where model.sort.sortId=" + sortid + "and model.sortClass=2";
		return getHibernateTemplate().find(hql);
	}

	public List findAll(int sortid, String keyword, final int start, final int length) {
		String hql = "from Sort as model where 1=1 ";
		if (sortid > 0) {
			System.out.println("---->sortid:" + sortid);
			hql = hql + " and model.sort.sortId = " + sortid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.sortName like '%" + keyword + "%')";
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

	public Sort merge(Sort detachedInstance) {
		log.debug("merging Sort instance");
		try {
			Sort result = (Sort) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Sort instance) {
		log.debug("attaching dirty Sort instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Sort instance) {
		log.debug("attaching clean Sort instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SortDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SortDAO) ctx.getBean("SortDAO");
	}
}