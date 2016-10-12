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

import bean.OInfo;

/**
 * A data access object (DAO) providing persistence and search support for OInfo entities. Transaction control
 * of the save(), update() and delete() operations can directly support Spring container-managed transactions
 * or they can be augmented to handle user-managed Spring transactions. Each of these methods provides
 * additional information for how to configure it for the desired type of transaction control.
 * 
 * @see bean.OInfo
 * @author MyEclipse Persistence Tools
 */
public class OInfoDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(OInfoDAO.class);
	// property constants
	public static final String OINFO_SERIAL = "oinfoSerial";
	public static final String GOODS_NUM = "goodsNum";
	public static final String GOODS_PRICE = "goodsPrice";
	public static final String TOTAL_MONEY = "totalMoney";
	public static final String DELIVER_MONEY = "deliverMoney";
	public static final String DELIVER_FREE = "deliverFree";
	public static final String STATUS_SEND = "statusSend";
	public static final String STATUS_REFUND = "statusRefund";
	public static final String STATUS_COMMENT = "statusComment";
	
	protected void initDao() {
		// do nothing
	}
	

	/**
	 * @param orderid
	 * @param customerid
	 * @param goodsid
	 * @param gbatchid
	 * @param statusPay
	 * @param statusMethod
	 * @param statusOrder
	 * @param statusSend
	 * @param statusRefund
	 * @param statusComment
	 * @param keyword
	 * @return
	 */
	public int getCount(int orderid, int customerid, int goodsid, int gbatchid, int statusPay, int statusMethod,
			int statusOrder, int statusSend, int statusRefund, int statusComment, String keyword) {
		String hql = "select count(*) from OInfo as model where 1=1 ";
		if (orderid > 0) {
			System.out.println("---->orderid:" + orderid);
			hql = hql + " and model.OOrder.orderId = " + orderid;
		}
		if (customerid > 0) {
			System.out.println("---->customerid:" + customerid);
			hql = hql + " and model.OOrder.customer.customerId = " + customerid;
		}
		if (goodsid > 0) {
			System.out.println("---->goodsid:" + goodsid);
			hql = hql + " and model.GBatch.goods.goodsId = " + goodsid;
		}
		if (gbatchid > 0) {
			System.out.println("---->gbatchid:" + gbatchid);
			hql = hql + " and model.GBatch.batchId = " + gbatchid;
		}
		if (statusSend > 0) {
			System.out.println("---->statusSend:" + statusSend);
			hql = hql + " and model.OOrder.statusSend = " + statusSend;
		}
		if (statusRefund > 0) {
			System.out.println("---->statusRefund:" + statusRefund);
			hql = hql + " and model.OOrder.statusRefund = " + statusRefund;
		}
		if (statusOrder > 0) {
			System.out.println("---->statusOrder:" + statusOrder);
			hql = hql + " and model.OOrder.statusOrder = " + statusOrder;
		}
		if (statusPay > 0) {
			System.out.println("---->statusPay:" + statusPay);
			hql = hql + " and model.OOrder.statusPay = " + statusPay;
		}
		if (statusMethod > 0) {
			System.out.println("---->statusMethod:" + statusMethod);
			hql = hql + " and model.OOrder.statusMethod = " + statusMethod;
		}
		if (statusComment > 0) {
			System.out.println("---->statusComment:" + statusComment);
			hql = hql + " and model.statusComment = " + statusComment;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.oinfoID like '%" + keyword + "%' or model.GBatch.goods.goodsKeyWord like '%" + keyword
					+ "%' or model.OOrder.customer.customerPhone like'%" + keyword + "%' or model.oinfoSerial like '%"
					+ keyword + "%' or model.OOrder.orderSerial like '%" + keyword + "%' or model.OOrder.adrName like '%"
					+ keyword + "%' or model.OOrder.adrPhone like '%" + keyword + "%')";
		}
		
		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}
	
	
	/**
	 * @param orderid
	 * @param customerid
	 * @param goodsid
	 * @param gbatchid
	 * @param statusPay
	 * @param statusMethod
	 * @param statusOrder
	 * @param statusSend
	 * @param statusRefund
	 * @param statusComment
	 * @param keyword
	 * @param start
	 * @param length
	 * @return
	 */
	public List findAll(int orderid, int customerid, int goodsid, int gbatchid, int statusPay, int statusMethod,
			int statusOrder, int statusSend, int statusRefund, int statusComment, String keyword, final int start,
			final int length) {
		String hql = "from OInfo as model where 1=1 ";
		if (orderid > 0) {
			System.out.println("---->orderid:" + orderid);
			hql = hql + " and model.OOrder.orderId = " + orderid;
		}
		if (customerid > 0) {
			System.out.println("---->customerid:" + customerid);
			hql = hql + " and model.OOrder.customer.customerId = " + customerid;
		}
		if (goodsid > 0) {
			System.out.println("---->goodsid:" + goodsid);
			hql = hql + " and model.GBatch.goods.goodsId = " + goodsid;
		}
		if (gbatchid > 0) {
			System.out.println("---->gbatchid:" + gbatchid);
			hql = hql + " and model.GBatch.batchId = " + gbatchid;
		}
		if (statusSend > 0) {
			System.out.println("---->statusSend:" + statusSend);
			hql = hql + " and model.OOrder.statusSend = " + statusSend;
		}
		if (statusRefund > 0) {
			System.out.println("---->statusRefund:" + statusRefund);
			hql = hql + " and model.OOrder.statusRefund = " + statusRefund;
		}
		if (statusOrder > 0) {
			System.out.println("---->statusOrder:" + statusOrder);
			hql = hql + " and model.OOrder.statusOrder = " + statusOrder;
		}
		if (statusPay > 0) {
			System.out.println("---->statusPay:" + statusPay);
			hql = hql + " and model.OOrder.statusPay = " + statusPay;
		}
		if (statusMethod > 0) {
			System.out.println("---->statusMethod:" + statusMethod);
			hql = hql + " and model.OOrder.statusMethod = " + statusMethod;
		}
		if (statusComment > 0) {
			System.out.println("---->statusComment:" + statusComment);
			hql = hql + " and model.statusComment = " + statusComment;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.oinfoID like '%" + keyword + "%' or model.GBatch.goods.goodsKeyWord like '%" + keyword
					+ "%' or model.OOrder.customer.customerPhone like'%" + keyword + "%' or model.oinfoSerial like '%"
					+ keyword + "%' or model.OOrder.orderSerial like '%" + keyword + "%' or model.OOrder.adrName like '%"
					+ keyword + "%' or model.OOrder.adrPhone like '%" + keyword + "%')";
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
	
	public List findByOinfoSerial(Object oinfoSerial) {
		return findByProperty(OINFO_SERIAL, oinfoSerial);
	}
	
	public List findByGoodsNum(Object goodsNum) {
		return findByProperty(GOODS_NUM, goodsNum);
	}
	
	public List findByGoodsPrice(Object goodsPrice) {
		return findByProperty(GOODS_PRICE, goodsPrice);
	}
	
	public List findByTotalMoney(Object totalMoney) {
		return findByProperty(TOTAL_MONEY, totalMoney);
	}
	
	public List findByDeliverMoney(Object deliverMoney) {
		return findByProperty(DELIVER_MONEY, deliverMoney);
	}
	
	public List findByDeliverFree(Object deliverFree) {
		return findByProperty(DELIVER_FREE, deliverFree);
	}
	
	public List findByStatusSend(Object statusSend) {
		return findByProperty(STATUS_SEND, statusSend);
	}
	
	public List findByStatusRefund(Object statusRefund) {
		return findByProperty(STATUS_REFUND, statusRefund);
	}
	
	public List findByStatusComment(Object statusComment) {
		return findByProperty(STATUS_COMMENT, statusComment);
	}
	
	public List findAll() {
		log.debug("finding all OInfo instances");
		try {
			String queryString = "from OInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
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
