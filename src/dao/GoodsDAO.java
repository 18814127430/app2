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

import bean.Goods;

/**
 * A data access object (DAO) providing persistence and search support for Goods
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see bean.Goods
 * @author MyEclipse Persistence Tools
 */
public class GoodsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(GoodsDAO.class);
	// property constants
	public static final String GOODS_NAME = "goodsName";
	public static final String GOODS_KEY_WORD = "goodsKeyWord";
	public static final String GOODS_DESCRIPTION = "goodsDescription";
	public static final String GOODS_WEIGHT = "goodsWeight";
	public static final String GOODS_REMARK = "goodsRemark";
	public static final String GOODS_NUM_TOTAL = "goodsNumTotal";
	public static final String GOODS_NUM_STOCK = "goodsNumStock";
	public static final String GOODS_MONEY_ENTRY = "goodsMoneyEntry";
	public static final String GOODS_MONEY_RETAIL = "goodsMoneyRetail";
	public static final String GOODS_MONEY_DELIVER = "goodsMoneyDeliver";
	public static final String GOODS_SIZE1 = "goodsSize1";
	public static final String GOODS_SIZE2 = "goodsSize2";
	public static final String GOODS_UNITS = "goodsUnits";
	public static final String GOODS_CHECK_DATE = "goodsCheckDate";
	public static final String GOODS_CHECK_DEPARTMENT = "goodsCheckDepartment";
	public static final String GOODS_CHECK_SERIAL = "goodsCheckSerial";
	public static final String GOODS_CHECK_RESULT = "goodsCheckResult";
	public static final String IMG1 = "img1";
	public static final String IMG2 = "img2";
	public static final String IMG3 = "img3";
	public static final String IMG4 = "img4";
	public static final String IMG5 = "img5";
	public static final String IMG6 = "img6";
	public static final String IMG7 = "img7";
	public static final String IMG8 = "img8";
	public static final String IMG9 = "img9";

	protected void initDao() {
		// do nothing
	}

	public void save(Goods transientInstance) {
		log.debug("saving Goods instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Goods persistentInstance) {
		log.debug("deleting Goods instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Goods findById(java.lang.Integer id) {
		log.debug("getting Goods instance with id: " + id);
		try {
			Goods instance = (Goods) getHibernateTemplate().get("bean.Goods", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Goods instance) {
		log.debug("finding Goods instance by example");
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
		log.debug("finding Goods instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Goods as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGoodsName(Object goodsName) {
		return findByProperty(GOODS_NAME, goodsName);
	}

	public List findByGoodsKeyWord(Object goodsKeyWord) {
		return findByProperty(GOODS_KEY_WORD, goodsKeyWord);
	}

	public List findByGoodsDescription(Object goodsDescription) {
		return findByProperty(GOODS_DESCRIPTION, goodsDescription);
	}

	public List findByGoodsWeight(Object goodsWeight) {
		return findByProperty(GOODS_WEIGHT, goodsWeight);
	}

	public List findByGoodsRemark(Object goodsRemark) {
		return findByProperty(GOODS_REMARK, goodsRemark);
	}

	public List findByGoodsNumTotal(Object goodsNumTotal) {
		return findByProperty(GOODS_NUM_TOTAL, goodsNumTotal);
	}

	public List findByGoodsNumStock(Object goodsNumStock) {
		return findByProperty(GOODS_NUM_STOCK, goodsNumStock);
	}

	public List findByGoodsMoneyEntry(Object goodsMoneyEntry) {
		return findByProperty(GOODS_MONEY_ENTRY, goodsMoneyEntry);
	}

	public List findByGoodsMoneyRetail(Object goodsMoneyRetail) {
		return findByProperty(GOODS_MONEY_RETAIL, goodsMoneyRetail);
	}

	public List findByGoodsMoneyDeliver(Object goodsMoneyDeliver) {
		return findByProperty(GOODS_MONEY_DELIVER, goodsMoneyDeliver);
	}

	public List findByGoodsSize1(Object goodsSize1) {
		return findByProperty(GOODS_SIZE1, goodsSize1);
	}

	public List findByGoodsSize2(Object goodsSize2) {
		return findByProperty(GOODS_SIZE2, goodsSize2);
	}

	public List findByGoodsUnits(Object goodsUnits) {
		return findByProperty(GOODS_UNITS, goodsUnits);
	}

	public List findByGoodsCheckDate(Object goodsCheckDate) {
		return findByProperty(GOODS_CHECK_DATE, goodsCheckDate);
	}

	public List findByGoodsCheckDepartment(Object goodsCheckDepartment) {
		return findByProperty(GOODS_CHECK_DEPARTMENT, goodsCheckDepartment);
	}

	public List findByGoodsCheckSerial(Object goodsCheckSerial) {
		return findByProperty(GOODS_CHECK_SERIAL, goodsCheckSerial);
	}

	public List findByGoodsCheckResult(Object goodsCheckResult) {
		return findByProperty(GOODS_CHECK_RESULT, goodsCheckResult);
	}

	public List findByImg1(Object img1) {
		return findByProperty(IMG1, img1);
	}

	public List findByImg2(Object img2) {
		return findByProperty(IMG2, img2);
	}

	public List findByImg3(Object img3) {
		return findByProperty(IMG3, img3);
	}

	public List findByImg4(Object img4) {
		return findByProperty(IMG4, img4);
	}

	public List findByImg5(Object img5) {
		return findByProperty(IMG5, img5);
	}

	public List findByImg6(Object img6) {
		return findByProperty(IMG6, img6);
	}

	public List findByImg7(Object img7) {
		return findByProperty(IMG7, img7);
	}

	public List findByImg8(Object img8) {
		return findByProperty(IMG8, img8);
	}

	public List findByImg9(Object img9) {
		return findByProperty(IMG9, img9);
	}

	public int getCount(int sortid, String keyword) {
		String hql = "select count(*) from Goods as model where 1=1 ";
		if (sortid > 0) {
			System.out.println("---->sortid:" + sortid);
			hql = hql + " and model.sort.sortId = " + sortid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.sort.sortName like '%" + keyword + "%' or model.goodsKeyWord like '%" + keyword + "%')";
		}

		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}

	public List findAll(int sortid, String keyword, final int start, final int length) {
		String hql = "from Goods as model where 1=1 ";
		if (sortid > 0) {
			System.out.println("---->sortid:" + sortid);
			hql = hql + " and model.sort.sortId = " + sortid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.sort.sortName like '%" + keyword + "%' or model.goodsKeyWord like '%" + keyword + "%')";
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

	public Goods merge(Goods detachedInstance) {
		log.debug("merging Goods instance");
		try {
			Goods result = (Goods) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Goods instance) {
		log.debug("attaching dirty Goods instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Goods instance) {
		log.debug("attaching clean Goods instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static GoodsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (GoodsDAO) ctx.getBean("GoodsDAO");
	}
}