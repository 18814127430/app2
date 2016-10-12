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

import bean.Goods;

/**
 * A data access object (DAO) providing persistence and search support for Goods entities. Transaction control
 * of the save(), update() and delete() operations can directly support Spring container-managed transactions
 * or they can be augmented to handle user-managed Spring transactions. Each of these methods provides
 * additional information for how to configure it for the desired type of transaction control.
 * 
 * @see bean.Goods
 * @author MyEclipse Persistence Tools
 */
public class GoodsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(GoodsDAO.class);
	// property constants
	public static final String GOODS_NAME = "goodsName";
	public static final String GOODS_KEY_WORD = "goodsKeyWord";
	public static final String GOODS_TAGS = "goodsTags";
	public static final String GOODS_REMARK = "goodsRemark";
	public static final String GOODS_DESCRIPTION = "goodsDescription";
	public static final String GOODS_HTML_URL = "goodsHtmlUrl";
	public static final String NUM_TOTAL = "numTotal";
	public static final String NUM_STOCK = "numStock";
	public static final String MONEY_OLD = "moneyOld";
	public static final String MONEY_NEW = "moneyNew";
	public static final String MONEY_DELIVER = "moneyDeliver";
	public static final String DELIVER_FREE = "deliverFree";
	public static final String GOODS_UNITS = "goodsUnits";
	public static final String GOODS_SIZE1 = "goodsSize1";
	public static final String GOODS_SIZE2 = "goodsSize2";
	public static final String GOODS_WEIGHT = "goodsWeight";
	public static final String CHECK_DEPARTMENT = "checkDepartment";
	public static final String CHECK_DATE = "checkDate";
	public static final String CHECK_SERIAL = "checkSerial";
	public static final String CHECK_RESULT = "checkResult";
	public static final String IMG1 = "img1";
	public static final String IMG2 = "img2";
	public static final String IMG3 = "img3";
	public static final String IMG4 = "img4";
	public static final String IMG5 = "img5";
	public static final String IMG6 = "img6";
	public static final String IMG7 = "img7";
	public static final String IMG8 = "img8";
	public static final String IMG9 = "img9";
	public static final String IMG10 = "img10";
	public static final String IMG11 = "img11";
	public static final String IMG12 = "img12";
	public static final String IMG13 = "img13";
	public static final String IMG14 = "img14";
	public static final String IMG15 = "img15";
	public static final String IMG16 = "img16";
	public static final String COUNT_COLLECT = "countCollect";
	public static final String COUNT_CART = "countCart";
	public static final String COUNT_ORDER = "countOrder";
	public static final String COMMENT_STAR1 = "commentStar1";
	public static final String COMMENT_STAR2 = "commentStar2";
	public static final String COMMENT_STAR3 = "commentStar3";
	public static final String COMMENT_STAR4 = "commentStar4";
	public static final String COMMENT_STAR5 = "commentStar5";
	
	protected void initDao() {
		// do nothing
	}
	
	/**
	 * @param parentsortid
	 * @param sortid
	 * @param keyword
	 * @return
	 */
	public int getCount(int parentsortid, int sortid, String keyword) {
		String hql = "select count(*) from Goods as model where 1=1 ";
		if (parentsortid > 0) {
			System.out.println("---->parentsortid:" + parentsortid);
			hql = hql + " and model.sort.sort.sortId = " + parentsortid;
		}
		if (sortid > 0) {
			System.out.println("---->sortid:" + sortid);
			hql = hql + " and model.sort.sortId = " + sortid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.sort.sortName like '%" + keyword + "%' or model.goodsName like '%" + keyword
					+ "%' or model.goodsKeyWord like '%" + keyword + "%')";
		}
		
		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}
	
	/**
	 * @param parentsortid
	 * @param sortid
	 * @param keyword
	 * @param orderby
	 * @param type
	 * @param start
	 * @param length
	 * @return
	 */
	public List findAll(int parentsortid, int sortid, String keyword, String orderby, String type, final int start,
			final int length) {
		String hql = "from Goods as model where 1=1 ";
		if (parentsortid > 0) {
			System.out.println("---->parentsortid:" + parentsortid);
			hql = hql + " and model.sort.sort.sortId = " + parentsortid;
		}
		if (sortid > 0) {
			System.out.println("---->sortid:" + sortid);
			hql = hql + " and model.sort.sortId = " + sortid;
		}
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.sort.sortName like '%" + keyword + "%' or model.goodsName like '%" + keyword
					+ "%' or model.goodsKeyWord like '%" + keyword + "%')";
		}
		if (orderby.equals("price")) {
			System.out.println("---->orderby:" + orderby);
			hql = hql + " order by model.moneyNew ";
		}
		if (orderby.equals("time")) {
			System.out.println("---->orderby:" + orderby);
			hql = hql + " order by model.goodsAddDate ";
		}
		if (orderby.equals("sales")) {
			System.out.println("---->orderby:" + orderby);
			hql = hql + " order by model.countOrder ";
		}
		if (orderby.equals("cart")) {
			System.out.println("---->orderby:" + orderby);
			hql = hql + " order by model.countCart ";
		}
		if (orderby.equals("collect")) {
			System.out.println("---->orderby:" + orderby);
			hql = hql + " order by model.countCollect ";
		}
		if (orderby.equals("comment")) {
			System.out.println("---->orderby:" + orderby);
			hql = hql + " order by (model.commentStar5 + model.commentStar4) ";
		}
		if (orderby.equals("rand")) {
			System.out.println("---->orderby:" + orderby);
			hql = hql + " order by rand() ";
		}
		if (type.equals("desc")) {
			System.out.println("---->type:" + type);
			hql = hql + " desc ";
		}
		if (type.equals("asc")) {
			System.out.println("---->type:" + type);
			hql = hql + " asc ";
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
	
	public List findByGoodsTags(Object goodsTags) {
		return findByProperty(GOODS_TAGS, goodsTags);
	}
	
	public List findByGoodsRemark(Object goodsRemark) {
		return findByProperty(GOODS_REMARK, goodsRemark);
	}
	
	public List findByGoodsDescription(Object goodsDescription) {
		return findByProperty(GOODS_DESCRIPTION, goodsDescription);
	}
	
	public List findByGoodsHtmlUrl(Object goodsHtmlUrl) {
		return findByProperty(GOODS_HTML_URL, goodsHtmlUrl);
	}
	
	public List findByNumTotal(Object numTotal) {
		return findByProperty(NUM_TOTAL, numTotal);
	}
	
	public List findByNumStock(Object numStock) {
		return findByProperty(NUM_STOCK, numStock);
	}
	
	public List findByMoneyOld(Object moneyOld) {
		return findByProperty(MONEY_OLD, moneyOld);
	}
	
	public List findByMoneyNew(Object moneyNew) {
		return findByProperty(MONEY_NEW, moneyNew);
	}
	
	public List findByMoneyDeliver(Object moneyDeliver) {
		return findByProperty(MONEY_DELIVER, moneyDeliver);
	}
	
	public List findByDeliverFree(Object deliverFree) {
		return findByProperty(DELIVER_FREE, deliverFree);
	}
	
	public List findByGoodsUnits(Object goodsUnits) {
		return findByProperty(GOODS_UNITS, goodsUnits);
	}
	
	public List findByGoodsSize1(Object goodsSize1) {
		return findByProperty(GOODS_SIZE1, goodsSize1);
	}
	
	public List findByGoodsSize2(Object goodsSize2) {
		return findByProperty(GOODS_SIZE2, goodsSize2);
	}
	
	public List findByGoodsWeight(Object goodsWeight) {
		return findByProperty(GOODS_WEIGHT, goodsWeight);
	}
	
	public List findByCheckDepartment(Object checkDepartment) {
		return findByProperty(CHECK_DEPARTMENT, checkDepartment);
	}
	
	public List findByCheckDate(Object checkDate) {
		return findByProperty(CHECK_DATE, checkDate);
	}
	
	public List findByCheckSerial(Object checkSerial) {
		return findByProperty(CHECK_SERIAL, checkSerial);
	}
	
	public List findByCheckResult(Object checkResult) {
		return findByProperty(CHECK_RESULT, checkResult);
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
	
	public List findByImg10(Object img10) {
		return findByProperty(IMG10, img10);
	}
	
	public List findByImg11(Object img11) {
		return findByProperty(IMG11, img11);
	}
	
	public List findByImg12(Object img12) {
		return findByProperty(IMG12, img12);
	}
	
	public List findByImg13(Object img13) {
		return findByProperty(IMG13, img13);
	}
	
	public List findByImg14(Object img14) {
		return findByProperty(IMG14, img14);
	}
	
	public List findByImg15(Object img15) {
		return findByProperty(IMG15, img15);
	}
	
	public List findByImg16(Object img16) {
		return findByProperty(IMG16, img16);
	}
	
	public List findByCountCollect(Object countCollect) {
		return findByProperty(COUNT_COLLECT, countCollect);
	}
	
	public List findByCountCart(Object countCart) {
		return findByProperty(COUNT_CART, countCart);
	}
	
	public List findByCountOrder(Object countOrder) {
		return findByProperty(COUNT_ORDER, countOrder);
	}
	
	public List findByCommentStar1(Object commentStar1) {
		return findByProperty(COMMENT_STAR1, commentStar1);
	}
	
	public List findByCommentStar2(Object commentStar2) {
		return findByProperty(COMMENT_STAR2, commentStar2);
	}
	
	public List findByCommentStar3(Object commentStar3) {
		return findByProperty(COMMENT_STAR3, commentStar3);
	}
	
	public List findByCommentStar4(Object commentStar4) {
		return findByProperty(COMMENT_STAR4, commentStar4);
	}
	
	public List findByCommentStar5(Object commentStar5) {
		return findByProperty(COMMENT_STAR5, commentStar5);
	}
	
	public List findAll() {
		log.debug("finding all Goods instances");
		try {
			String queryString = "from Goods";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
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
