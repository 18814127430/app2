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
import bean.Order;

/**
 * A data access object (DAO) providing persistence and search support for Order
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see bean.Order
 * @author MyEclipse Persistence Tools
 */
public class OrderDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(OrderDAO.class);
	// property constants
	public static final String ORDER_SERIAL = "orderSerial";
	public static final String ORDER_MONEY_DELIVER = "orderMoneyDeliver";
	public static final String ORDER_MONEY_TOTAL = "orderMoneyTotal";
	public static final String ORDER_REMARK = "orderRemark";
	public static final String ORDER_STATUS = "orderStatus";
	public static final String ORDER_DATE = "orderDate";
	public static final String ORDER_NAME = "orderName";
	public static final String ORDER_PHONE = "orderPhone";
	public static final String ORDER_PROVINCE = "orderProvince";
	public static final String ORDER_CITY = "orderCity";
	public static final String ORDER_STREET = "orderStreet";
	public static final String ORDER_DETIAL = "orderDetial";

	protected void initDao() {
		// do nothing
	}

	public void save(Order transientInstance) {
		log.debug("saving Order instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Order persistentInstance) {
		log.debug("deleting Order instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Order findById(java.lang.Integer id) {
		log.debug("getting Order instance with id: " + id);
		try {
			Order instance = (Order) getHibernateTemplate().get("bean.Order", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Order instance) {
		log.debug("finding Order instance by example");
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
		log.debug("finding Order instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Order as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOrderSerial(Object orderSerial) {
		return findByProperty(ORDER_SERIAL, orderSerial);
	}

	public List findByOrderMoneyDeliver(Object orderMoneyDeliver) {
		return findByProperty(ORDER_MONEY_DELIVER, orderMoneyDeliver);
	}

	public List findByOrderMoneyTotal(Object orderMoneyTotal) {
		return findByProperty(ORDER_MONEY_TOTAL, orderMoneyTotal);
	}

	public List findByOrderRemark(Object orderRemark) {
		return findByProperty(ORDER_REMARK, orderRemark);
	}

	public List findByOrderStatus(Object orderStatus) {
		return findByProperty(ORDER_STATUS, orderStatus);
	}

	public List findByOrderDate(Object orderDate) {
		return findByProperty(ORDER_DATE, orderDate);
	}

	public List findByOrderName(Object orderName) {
		return findByProperty(ORDER_NAME, orderName);
	}

	public List findByOrderPhone(Object orderPhone) {
		return findByProperty(ORDER_PHONE, orderPhone);
	}

	public List findByOrderProvince(Object orderProvince) {
		return findByProperty(ORDER_PROVINCE, orderProvince);
	}

	public List findByOrderCity(Object orderCity) {
		return findByProperty(ORDER_CITY, orderCity);
	}

	public List findByOrderStreet(Object orderStreet) {
		return findByProperty(ORDER_STREET, orderStreet);
	}

	public List findByOrderDetial(Object orderDetial) {
		return findByProperty(ORDER_DETIAL, orderDetial);
	}

	public List findByCustomerId(int id) {
		try {
			System.out.println("findByCustomerID:" + id);

			String hql = "from Order as model where model.customer.customerId=" + id;
			return getHibernateTemplate().find(hql);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public int getCount(int customerid, final int goodsid, String status, String keyword) {
		String hql = "select count(*) from Order as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.orderStatus like '%" + keyword + "%' or model.orderDate like '%" + keyword + "%' or model.orderName like '%"
					+ keyword + "%' or model.orderPhone like '%" + keyword + "%' or model.orderProvince like '%" + keyword
					+ "%' or model.orderCity like '%" + keyword + "%' or model.orderStreet like '%" + keyword
					+ "%' or model.customer.customerPhone like '%" + keyword + "%')";
		}
		if (customerid > 0) {
			System.out.println("---->customerid:" + customerid);
			hql = hql + " and model.customer.customerId = " + customerid;
		}
		if (goodsid > 0) {
			System.out.println("---->goodsid:" + goodsid);
			hql = hql + " and model.goods.goodsId = " + goodsid;
		}
		if (!status.equals("")) {
			System.out.println("---->status:" + status);
			hql = hql + " and model.orderStatus = '" + status + "'";
		}
		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("intValue:" + count.intValue());
		return count.intValue();
	}

	public List findAll(final int customerid, final int goodsid, String status, String keyword, final int start, final int length) {
		String hql = "from Order as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.orderStatus like '%" + keyword + "%' or model.orderDate like '%" + keyword + "%' or model.orderName like '%"
					+ keyword + "%' or model.orderPhone like '%" + keyword + "%' or model.orderProvince like '%" + keyword
					+ "%' or model.orderCity like '%" + keyword + "%' or model.orderStreet like '%" + keyword
					+ "%' or model.customer.customerPhone like '%" + keyword + "%')";
		}
		if (customerid > 0) {
			System.out.println("---->customerid:" + customerid);
			hql = hql + " and model.customer.customerId = " + customerid;
		}
		if (goodsid > 0) {
			System.out.println("---->goodsid:" + goodsid);
			hql = hql + " and model.goods.goodsId = " + goodsid;
		}
		if (!status.equals("")) {
			System.out.println("---->status:" + status);
			hql = hql + " and model.orderStatus = '" + status + "'";
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

	public Order merge(Order detachedInstance) {
		log.debug("merging Order instance");
		try {
			Order result = (Order) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Order instance) {
		log.debug("attaching dirty Order instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Order instance) {
		log.debug("attaching clean Order instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OrderDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OrderDAO) ctx.getBean("OrderDAO");
	}
}