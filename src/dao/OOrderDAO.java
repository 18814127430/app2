package dao;

import java.sql.SQLException;
import java.sql.Timestamp;
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

import bean.OOrder;

/**
 * A data access object (DAO) providing persistence and search support for OOrder entities. Transaction
 * control of the save(), update() and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type of transaction control.
 * 
 * @see bean.OOrder
 * @author MyEclipse Persistence Tools
 */
public class OOrderDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(OOrderDAO.class);
	// property constants
	public static final String ORDER_SUBJECT = "orderSubject";
	public static final String ORDER_BODY = "orderBody";
	public static final String ORDER_SERIAL = "orderSerial";
	public static final String ORDER_REMARK = "orderRemark";
	public static final String MONEY_TOTAL = "moneyTotal";
	public static final String MONEY_DELIVER = "moneyDeliver";
	public static final String PAY_SERIAL = "paySerial";
	public static final String PAY_METHOD = "payMethod";
	public static final String ADR_NAME = "adrName";
	public static final String ADR_PHONE = "adrPhone";
	public static final String ADR_PROVINCE = "adrProvince";
	public static final String ADR_CITY = "adrCity";
	public static final String ADR_STREET = "adrStreet";
	public static final String ADR_DETIAL = "adrDetial";
	public static final String STATUS_PAY = "statusPay";
	public static final String STATUS_METHOD = "statusMethod";
	public static final String STATUS_ORDER = "statusOrder";
	public static final String STATUS_SEND = "statusSend";
	public static final String STATUS_REFUND = "statusRefund";
	public static final String NUM_INFO = "numInfo";
	public static final String NUM_COMMENT = "numComment";
	public static final String DELIVER_TIME = "deliverTime";
	public static final String BILL_MEMO = "billMemo";
	
	protected void initDao() {
		// do nothing
	}
	
	/**
	 * @param customerid
	 * @param statusPay
	 * @param statusMethod
	 * @param statusOrder
	 * @param statusSend
	 * @param statusRefund
	 * @param statusComment
	 * @param keyword
	 * @return
	 */
	public int getCount(int customerid, int statusPay, int statusMethod, int statusOrder, int statusSend, int statusRefund,
			int statusComment, String keyword) {
		String hql = "select count(*) from OOrder as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.orderSerial like '%" + keyword + "%' or model.orderBody like '%" + keyword
					+ "%' or model.orderDate like '%" + keyword + "%' or model.adrName like '%" + keyword
					+ "%' or model.adrPhone like '%" + keyword + "%' or model.adrProvince like '%" + keyword
					+ "%' or model.adrCity like '%" + keyword + "%' or model.adrStreet like '%" + keyword
					+ "%' or model.customer.customerPhone like '%" + keyword + "%')";
		}
		if (customerid > 0) {
			System.out.println("---->customerid:" + customerid);
			hql = hql + " and model.customer.customerId = " + customerid;
		}
		if (statusPay > 0) {
			System.out.println("---->statusPay:" + statusPay);
			hql = hql + " and model.statusPay = " + statusPay;
		}
		if (statusMethod > 0) {
			System.out.println("---->statusMethod:" + statusMethod);
			hql = hql + " and model.statusMethod = " + statusMethod;
		}
		if (statusOrder == 4) {//未关闭
			System.out.println("---->statusOrder:" + statusOrder);
			hql = hql + " and ( model.statusOrder = 2 or model.statusOrder = 3 )";
		}
		else if (statusOrder > 0) {
			System.out.println("---->statusOrder:" + statusOrder);
			hql = hql + " and model.statusOrder = " + statusOrder;
		}
		if (statusSend == 5) {//未收货
			System.out.println("---->statusSend:" + statusSend);
			hql = hql + " and ( model.statusSend = 2 or model.statusSend = 3 )";
		}
		else if (statusSend > 0) {
			System.out.println("---->statusSend:" + statusSend);
			hql = hql + " and model.statusSend = " + statusSend;
		}
		if (statusRefund > 0) {
			System.out.println("---->statusRefund:" + statusRefund);
			hql = hql + " and model.statusRefund = " + statusRefund;
		}
		if (statusComment == 1) {
			System.out.println("---->statusComment:" + statusComment);
			hql = hql + " and model.numComment < model.numInfo ";
		}
		if (statusComment == 2) {
			System.out.println("---->statusComment:" + statusComment);
			hql = hql + " and model.numComment = model.numInfo ";
		}
		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("intValue:" + count.intValue());
		return count.intValue();
	}
	
	/**
	 * @param customerid
	 * @param statusPay
	 * @param statusMethod
	 * @param statusOrder
	 * @param statusSend
	 * @param statusRefund
	 * @param statusComment
	 * @param keyword
	 * @param type
	 * @param start
	 * @param length
	 * @return
	 */
	public List findAll(int customerid, int statusPay, int statusMethod, int statusOrder, int statusSend, int statusRefund,
			int statusComment, String keyword, String type, final int start, final int length) {
		String hql = "from OOrder as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.orderSerial like '%" + keyword + "%' or model.orderBody like '%" + keyword
					+ "%' or model.orderDate like '%" + keyword + "%' or model.adrName like '%" + keyword
					+ "%' or model.adrPhone like '%" + keyword + "%' or model.adrProvince like '%" + keyword
					+ "%' or model.adrCity like '%" + keyword + "%' or model.adrStreet like '%" + keyword
					+ "%' or model.customer.customerPhone like '%" + keyword + "%')";
		}
		if (customerid > 0) {
			System.out.println("---->customerid:" + customerid);
			hql = hql + " and model.customer.customerId = " + customerid;
		}
		if (statusPay > 0) {
			System.out.println("---->statusPay:" + statusPay);
			hql = hql + " and model.statusPay = " + statusPay;
		}
		if (statusMethod > 0) {
			System.out.println("---->statusMethod:" + statusMethod);
			hql = hql + " and model.statusMethod = " + statusMethod;
		}
		if (statusOrder == 4) {//未关闭
			System.out.println("---->statusOrder:" + statusOrder);
			hql = hql + " and ( model.statusOrder = 2 or model.statusOrder = 3 )";
		}
		else if (statusOrder > 0) {
			System.out.println("---->statusOrder:" + statusOrder);
			hql = hql + " and model.statusOrder = " + statusOrder;
		}
		if (statusSend == 5) {//未收货
			System.out.println("---->statusSend:" + statusSend);
			hql = hql + " and ( model.statusSend = 2 or model.statusSend = 3 )";
		}
		else if (statusSend > 0) {
			System.out.println("---->statusSend:" + statusSend);
			hql = hql + " and model.statusSend = " + statusSend;
		}
		if (statusRefund > 0) {
			System.out.println("---->statusRefund:" + statusRefund);
			hql = hql + " and model.statusRefund = " + statusRefund;
		}
		if (statusComment == 1) {
			System.out.println("---->statusComment:" + statusComment);
			hql = hql + " and model.numComment < model.numInfo ";
		}
		if (statusComment == 2) {
			System.out.println("---->statusComment:" + statusComment);
			hql = hql + " and model.numComment = model.numInfo ";
		}
		if (type.equals("desc")) {
			System.out.println("---->type:" + type);
			hql = hql + " order by model.orderDate desc ";
		}
		if (type.equals("asc")) {
			System.out.println("---->type:" + type);
			hql = hql + " order by model.orderDate asc ";
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
	
	public void save(OOrder transientInstance) {
		log.debug("saving OOrder instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(OOrder persistentInstance) {
		log.debug("deleting OOrder instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public OOrder findById(java.lang.Integer id) {
		log.debug("getting OOrder instance with id: " + id);
		try {
			OOrder instance = (OOrder) getHibernateTemplate().get("bean.OOrder", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByExample(OOrder instance) {
		log.debug("finding OOrder instance by example");
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
		log.debug("finding OOrder instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from OOrder as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByOrderSubject(Object orderSubject) {
		return findByProperty(ORDER_SUBJECT, orderSubject);
	}
	
	public List findByOrderBody(Object orderBody) {
		return findByProperty(ORDER_BODY, orderBody);
	}
	
	public List findByOrderSerial(Object orderSerial) {
		return findByProperty(ORDER_SERIAL, orderSerial);
	}
	
	public List findByOrderRemark(Object orderRemark) {
		return findByProperty(ORDER_REMARK, orderRemark);
	}
	
	public List findByMoneyTotal(Object moneyTotal) {
		return findByProperty(MONEY_TOTAL, moneyTotal);
	}
	
	public List findByMoneyDeliver(Object moneyDeliver) {
		return findByProperty(MONEY_DELIVER, moneyDeliver);
	}
	
	public List findByPaySerial(Object paySerial) {
		return findByProperty(PAY_SERIAL, paySerial);
	}
	
	public List findByPayMethod(Object payMethod) {
		return findByProperty(PAY_METHOD, payMethod);
	}
	
	public List findByAdrName(Object adrName) {
		return findByProperty(ADR_NAME, adrName);
	}
	
	public List findByAdrPhone(Object adrPhone) {
		return findByProperty(ADR_PHONE, adrPhone);
	}
	
	public List findByAdrProvince(Object adrProvince) {
		return findByProperty(ADR_PROVINCE, adrProvince);
	}
	
	public List findByAdrCity(Object adrCity) {
		return findByProperty(ADR_CITY, adrCity);
	}
	
	public List findByAdrStreet(Object adrStreet) {
		return findByProperty(ADR_STREET, adrStreet);
	}
	
	public List findByAdrDetial(Object adrDetial) {
		return findByProperty(ADR_DETIAL, adrDetial);
	}
	
	public List findByStatusPay(Object statusPay) {
		return findByProperty(STATUS_PAY, statusPay);
	}
	
	public List findByStatusMethod(Object statusMethod) {
		return findByProperty(STATUS_METHOD, statusMethod);
	}
	
	public List findByStatusOrder(Object statusOrder) {
		return findByProperty(STATUS_ORDER, statusOrder);
	}
	
	public List findByStatusSend(Object statusSend) {
		return findByProperty(STATUS_SEND, statusSend);
	}
	
	public List findByStatusRefund(Object statusRefund) {
		return findByProperty(STATUS_REFUND, statusRefund);
	}
	
	public List findByNumInfo(Object numInfo) {
		return findByProperty(NUM_INFO, numInfo);
	}
	
	public List findByNumComment(Object numComment) {
		return findByProperty(NUM_COMMENT, numComment);
	}
	
	public List findByDeliverTime(Object deliverTime) {
		return findByProperty(DELIVER_TIME, deliverTime);
	}
	
	public List findByBillMemo(Object billMemo) {
		return findByProperty(BILL_MEMO, billMemo);
	}
	
	public List findAll() {
		log.debug("finding all OOrder instances");
		try {
			String queryString = "from OOrder";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public OOrder merge(OOrder detachedInstance) {
		log.debug("merging OOrder instance");
		try {
			OOrder result = (OOrder) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public void attachDirty(OOrder instance) {
		log.debug("attaching dirty OOrder instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void attachClean(OOrder instance) {
		log.debug("attaching clean OOrder instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public static OOrderDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OOrderDAO) ctx.getBean("OOrderDAO");
	}
}
