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
import bean.OInfo;

/**
 * A data access object (DAO) providing persistence and search support for OInfo
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see bean.OInfo
 * @author MyEclipse Persistence Tools
 */
public class OInfoDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(OInfoDAO.class);
	// property constants
	public static final String OC_NUM = "ocNum";
	public static final String OC_MONEY_DELIVER = "ocMoneyDeliver";
	public static final String OC_MONEY_TOTAL = "ocMoneyTotal";
	public static final String OC_DATE = "ocDate";

	protected void initDao() {
		// do nothing
	}

	public void save(OInfo transientInstance) {
		log.debug("saving OInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OInfo persistentInstance) {
		log.debug("deleting OInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OInfo findById(java.lang.Integer id) {
		log.debug("getting OInfo instance with id: " + id);
		try {
			OInfo instance = (OInfo) getHibernateTemplate().get("bean.OInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OInfo instance) {
		log.debug("finding OInfo instance by example");
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
		log.debug("finding OInfo instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from OInfo as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOcNum(Object ocNum) {
		return findByProperty(OC_NUM, ocNum);
	}

	public List findByOcMoneyDeliver(Object ocMoneyDeliver) {
		return findByProperty(OC_MONEY_DELIVER, ocMoneyDeliver);
	}

	public List findByOcMoneyTotal(Object ocMoneyTotal) {
		return findByProperty(OC_MONEY_TOTAL, ocMoneyTotal);
	}

	public List findByOcDate(Object ocDate) {
		return findByProperty(OC_DATE, ocDate);
	}

	public int getCount(int orderid, int goodsid, int gbatchid, String keyword) {
		String hql = "select count(*) from OInfo as model where 1=1 ";
		if (orderid > 0) {
			System.out.println("---->orderid:" + orderid);
			hql = hql + " and model.order.orderId = " + orderid;
		}
		if (goodsid > 0) {
			System.out.println("---->goodsid:" + goodsid);
			hql = hql + " and model.goods.goodsId = " + goodsid;
		}
		if (gbatchid > 0) {
			System.out.println("---->gbatchid:" + gbatchid);
			hql = hql + " and model.GBatch.batchId = " + gbatchid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.ocDate like '%" + keyword + "%' or model.goods.goodsKeyWord like '%" + keyword
					+ "%' or model.order.customer.customerPhone like'%" + keyword + "%' or model.order.orderSerial like'%" + keyword
					+ "%' or model.order.orderName like '%" + keyword + "%' or model.order.orderPhone like '%" + keyword + "%')";
		}

		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}

	public List findAll(int orderid, int goodsid, int gbatchid, String keyword, final int start, final int length) {
		String hql = "from OInfo as model where 1=1 ";
		if (orderid > 0) {
			System.out.println("---->orderid:" + orderid);
			hql = hql + " and model.order.orderId = " + orderid;
		}
		if (goodsid > 0) {
			System.out.println("---->goodsid:" + goodsid);
			hql = hql + " and model.goods.goodsId = " + goodsid;
		}
		if (gbatchid > 0) {
			System.out.println("---->gbatchid:" + gbatchid);
			hql = hql + " and model.GBatch.batchId = " + gbatchid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.ocDate like '%" + keyword + "%' or model.goods.goodsKeyWord like '%" + keyword
					+ "%' or model.order.customer.customerPhone like'%" + keyword + "%' or model.order.orderSerial like'%" + keyword
					+ "%' or model.order.orderName like '%" + keyword + "%' or model.order.orderPhone like '%" + keyword + "%')";
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

	public OInfo merge(OInfo detachedInstance) {
		log.debug("merging OInfo instance");
		try {
			OInfo result = (OInfo) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OInfo instance) {
		log.debug("attaching dirty OInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OInfo instance) {
		log.debug("attaching clean OInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OInfoDAO) ctx.getBean("OInfoDAO");
	}
}