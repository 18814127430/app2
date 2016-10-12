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

import bean.GBatch;

/**
 * A data access object (DAO) providing persistence and search support for
 * GBatch entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see bean.GBatch
 * @author MyEclipse Persistence Tools
 */
public class GBatchDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(GBatchDAO.class);
	// property constants
	public static final String NUM_TOTAL = "numTotal";
	public static final String NUM_STOCK = "numStock";
	public static final String PRODUCER_SEND_CONTENT = "producerSendContent";
	public static final String SELLER_RECEIVE_CONTENT = "sellerReceiveContent";

	protected void initDao() {
		// do nothing
	}

	public int getCount(int producerid, int sellerid, int goodsid, String keyword) {
		String hql = "select count(*) from GBatch as model where 1=1 ";
		if (producerid > 0) {
			System.out.println("---->producerid:" + producerid);
			hql = hql + " and model.companyByProducerId.companyId = " + producerid;
		}
		if (sellerid > 0) {
			System.out.println("---->sellerid:" + sellerid);
			hql = hql + " and model.companyBySellerId.companyId = " + sellerid;
		}
		if (goodsid > 0) {
			System.out.println("---->goodsid:" + goodsid);
			hql = hql + " and model.goods.goodsId = " + goodsid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.dateKeep1 like '%" + keyword + "%' or model.dateKeep2 like '%" + keyword
					+ "%' or model.goods.goodsKeyWord like '%" + keyword + "%' or model.companyBySellerId.companyName like '%" + keyword
					+ "%' or model.companyByProducerId.companyName like '%" + keyword + "%')";
		}

		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}

	public List findAll(int producerid, int sellerid, int goodsid, String keyword, final int start, final int length) {
		String hql = "from GBatch as model where 1=1 ";
		if (producerid > 0) {
			System.out.println("---->producerid:" + producerid);
			hql = hql + " and model.companyByProducerId.companyId = " + producerid;
		}
		if (sellerid > 0) {
			System.out.println("---->sellerid:" + sellerid);
			hql = hql + " and model.companyBySellerId.companyId = " + sellerid;
		}
		if (goodsid > 0) {
			System.out.println("---->goodsid:" + goodsid);
			hql = hql + " and model.goods.goodsId = " + goodsid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.dateKeep1 like '%" + keyword + "%' or model.dateKeep2 like '%" + keyword
					+ "%' or model.goods.goodsKeyWord like '%" + keyword + "%' or model.companyBySellerId.companyName like '%" + keyword
					+ "%' or model.companyByProducerId.companyName like '%" + keyword + "%')";
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

	public GBatch findByQuatifyDateKeep(final int num, int goodsid) {
		String hql = "from GBatch as model where 1=1 ";
		hql = hql + "and model.numStock > 0 ";
		hql = hql + "and UNIX_TIMESTAMP() <= UNIX_TIMESTAMP(model.dateKeep2) ";
		hql = hql + " and model.goods.goodsId = " + goodsid;
		final String hql1 = hql;

		List listTable = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql1);
				System.out.println("------>hql:" + hql1);
				query.setFirstResult(0);
				query.setMaxResults(num);
				List list = query.list();
				return list;
			}
		});

		System.out.println(listTable.size());
		if (listTable.size() <= 0) {
			return null;
		}

		int number = 0;
		GBatch gbatch = new GBatch();
		for (int i = 0; i < listTable.size(); i++) {
			GBatch g = (GBatch) listTable.get(i);
			number = g.getNumStock() + number;

			if (number < num) {
				g.setNumStock(0);
				merge(g);
			} else {
				g.setNumStock(number);
				gbatch = merge(g);
				break;
			}
		}

		return gbatch;
	}

	public void save(GBatch transientInstance) {
		log.debug("saving GBatch instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GBatch persistentInstance) {
		log.debug("deleting GBatch instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GBatch findById(java.lang.Integer id) {
		log.debug("getting GBatch instance with id: " + id);
		try {
			GBatch instance = (GBatch) getHibernateTemplate().get("bean.GBatch", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(GBatch instance) {
		log.debug("finding GBatch instance by example");
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
		log.debug("finding GBatch instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from GBatch as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNumTotal(Object numTotal) {
		return findByProperty(NUM_TOTAL, numTotal);
	}

	public List findByNumStock(Object numStock) {
		return findByProperty(NUM_STOCK, numStock);
	}

	public List findByProducerSendContent(Object producerSendContent) {
		return findByProperty(PRODUCER_SEND_CONTENT, producerSendContent);
	}

	public List findBySellerReceiveContent(Object sellerReceiveContent) {
		return findByProperty(SELLER_RECEIVE_CONTENT, sellerReceiveContent);
	}

	public List findAll() {
		log.debug("finding all GBatch instances");
		try {
			String queryString = "from GBatch";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GBatch merge(GBatch detachedInstance) {
		log.debug("merging GBatch instance");
		try {
			GBatch result = (GBatch) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GBatch instance) {
		log.debug("attaching dirty GBatch instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GBatch instance) {
		log.debug("attaching clean GBatch instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static GBatchDAO getFromApplicationContext(ApplicationContext ctx) {
		return (GBatchDAO) ctx.getBean("GBatchDAO");
	}
}