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

import bean.Comment;

/**
 * A data access object (DAO) providing persistence and search support for Comment entities. Transaction
 * control of the save(), update() and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type of transaction control.
 * 
 * @see bean.Comment
 * @author MyEclipse Persistence Tools
 */
public class CommentDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CommentDAO.class);
	// property constants
	public static final String COMMENT_STAR = "commentStar";
	public static final String COMMENT_COUNT = "commentCount";
	public static final String COMMENT_CONTENT = "commentContent";
	
	protected void initDao() {
		// do nothing
	}
	
	/**
	 * @param customerid
	 * @param goodsid
	 * @param batchid
	 * @param oinfoid
	 * @param keyword
	 * @return
	 */
	public int getCount(int customerid, int goodsid, int batchid, int oinfoid, String keyword) {
		String hql = "select count(*) from Comment as model where 1=1 ";
		if (customerid > 0) {
			System.out.println("---->customerid:" + customerid);
			hql = hql + " and model.OInfo.OOrder.customer.customerId = " + customerid;
		}
		if (goodsid > 0) {
			System.out.println("---->goodsid:" + goodsid);
			hql = hql + " and model.OInfo.GBatch.goods.goodsId = " + goodsid;
		}
		if (batchid > 0) {
			System.out.println("---->batchid:" + batchid);
			hql = hql + " and model.OInfo.GBatch.batchId = " + batchid;
		}
		if (oinfoid > 0) {
			System.out.println("---->orderid:" + oinfoid);
			hql = hql + " and model.OInfo.oinfoId = " + oinfoid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.commentStar like '%" + keyword + "%' or model.commentDate like'%" + keyword
					+ "%' or model.OInfo.GBatch.goods.goodsKeyWord like '%" + keyword
					+ "%' or model.OInfo.OOrder.customer.customerPhone like '%" + keyword + "%')";
		}
		
		final String hql1 = hql;
		System.out.println("----->hql:" + hql1);
		
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}
	
	/**
	 * @param customerid
	 * @param goodsid
	 * @param batchid
	 * @param oinfoid
	 * @param keyword
	 * @param start
	 * @param length
	 * @return
	 */
	public List findAll(int customerid, int goodsid, int batchid, int oinfoid, String keyword, final int start,
			final int length) {
		String hql = "from Comment as model where 1=1 ";
		if (customerid > 0) {
			System.out.println("---->customerid:" + customerid);
			hql = hql + " and model.OInfo.OOrder.customer.customerId = " + customerid;
		}
		if (goodsid > 0) {
			System.out.println("---->goodsid:" + goodsid);
			hql = hql + " and model.OInfo.GBatch.goods.goodsId = " + goodsid;
		}
		if (batchid > 0) {
			System.out.println("---->batchid:" + batchid);
			hql = hql + " and model.OInfo.GBatch.batchId = " + batchid;
		}
		if (oinfoid > 0) {
			System.out.println("---->orderid:" + oinfoid);
			hql = hql + " and model.OInfo.oinfoId = " + oinfoid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.commentStar like '%" + keyword + "%' or model.commentDate like'%" + keyword
					+ "%' or model.OInfo.GBatch.goods.goodsKeyWord like '%" + keyword
					+ "%' or model.OInfo.OOrder.customer.customerPhone like '%" + keyword + "%')";
		}
		
		final String hql1 = hql;
		System.out.println("----->hql:" + hql1);
		
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
	
	public void save(Comment transientInstance) {
		log.debug("saving Comment instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(Comment persistentInstance) {
		log.debug("deleting Comment instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public Comment findById(java.lang.Integer id) {
		log.debug("getting Comment instance with id: " + id);
		try {
			Comment instance = (Comment) getHibernateTemplate().get("bean.Comment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByExample(Comment instance) {
		log.debug("finding Comment instance by example");
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
		log.debug("finding Comment instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Comment as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByCommentStar(Object commentStar) {
		return findByProperty(COMMENT_STAR, commentStar);
	}
	
	public List findByCommentCount(Object commentCount) {
		return findByProperty(COMMENT_COUNT, commentCount);
	}
	
	public List findByCommentContent(Object commentContent) {
		return findByProperty(COMMENT_CONTENT, commentContent);
	}
	
	public List findAll() {
		log.debug("finding all Comment instances");
		try {
			String queryString = "from Comment";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Comment merge(Comment detachedInstance) {
		log.debug("merging Comment instance");
		try {
			Comment result = (Comment) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public void attachDirty(Comment instance) {
		log.debug("attaching dirty Comment instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void attachClean(Comment instance) {
		log.debug("attaching clean Comment instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public static CommentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CommentDAO) ctx.getBean("CommentDAO");
	}
}
