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

import bean.Company;

/**
 * A data access object (DAO) providing persistence and search support for
 * Company entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see bean.Company
 * @author MyEclipse Persistence Tools
 */
public class CompanyDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CompanyDAO.class);
	// property constants
	public static final String COMPANY_NAME = "companyName";
	public static final String COMPANY_TYPE = "companyType";
	public static final String COMPANY_PHONE = "companyPhone";
	public static final String COMPANY_POST_CODE = "companyPostCode";
	public static final String COMPANY_FAX = "companyFax";
	public static final String COMPANY_URL = "companyUrl";
	public static final String COMPANY_PROVINCE = "companyProvince";
	public static final String COMPANY_CITY = "companyCity";
	public static final String COMPANY_STRESS = "companyStress";
	public static final String COMPANY_DETIAL = "companyDetial";
	public static final String COMPANY_INTRODUCTION = "companyIntroduction";
	public static final String COMPANY_STATUS = "companyStatus";

	protected void initDao() {
		// do nothing
	}

	public int getCount(String keyword) {
		String hql = "select count(*) from Company as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.companyName like '%" + keyword + "%' or model.companyPhone like '%" + keyword + "%')";
		}

		System.out.println(hql);
		Integer count = (Integer) getHibernateTemplate().find(hql).listIterator().next();
		System.out.println("----->intValue:" + count.intValue());
		return count.intValue();
	}

	public List findAll(String keyword, final int start, final int length) {
		String hql = "from Company as model where 1=1 ";
		if (!keyword.equals("")) {
			System.out.println("---->keyword:" + keyword);
			hql = hql + " and (model.companyName like '%" + keyword + "%' or model.companyPhone like '%" + keyword + "%')";
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

	public void save(Company transientInstance) {
		log.debug("saving Company instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Company persistentInstance) {
		log.debug("deleting Company instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Company findById(java.lang.Integer id) {
		log.debug("getting Company instance with id: " + id);
		try {
			Company instance = (Company) getHibernateTemplate().get("bean.Company", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Company instance) {
		log.debug("finding Company instance by example");
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
		log.debug("finding Company instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Company as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCompanyName(Object companyName) {
		return findByProperty(COMPANY_NAME, companyName);
	}

	public List findByCompanyType(Object companyType) {
		return findByProperty(COMPANY_TYPE, companyType);
	}

	public List findByCompanyPhone(Object companyPhone) {
		return findByProperty(COMPANY_PHONE, companyPhone);
	}

	public List findByCompanyPostCode(Object companyPostCode) {
		return findByProperty(COMPANY_POST_CODE, companyPostCode);
	}

	public List findByCompanyFax(Object companyFax) {
		return findByProperty(COMPANY_FAX, companyFax);
	}

	public List findByCompanyUrl(Object companyUrl) {
		return findByProperty(COMPANY_URL, companyUrl);
	}

	public List findByCompanyProvince(Object companyProvince) {
		return findByProperty(COMPANY_PROVINCE, companyProvince);
	}

	public List findByCompanyCity(Object companyCity) {
		return findByProperty(COMPANY_CITY, companyCity);
	}

	public List findByCompanyStress(Object companyStress) {
		return findByProperty(COMPANY_STRESS, companyStress);
	}

	public List findByCompanyDetial(Object companyDetial) {
		return findByProperty(COMPANY_DETIAL, companyDetial);
	}

	public List findByCompanyIntroduction(Object companyIntroduction) {
		return findByProperty(COMPANY_INTRODUCTION, companyIntroduction);
	}

	public List findByCompanyStatus(Object companyStatus) {
		return findByProperty(COMPANY_STATUS, companyStatus);
	}

	public List findAll() {
		log.debug("finding all Company instances");
		try {
			String queryString = "from Company";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Company merge(Company detachedInstance) {
		log.debug("merging Company instance");
		try {
			Company result = (Company) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Company instance) {
		log.debug("attaching dirty Company instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Company instance) {
		log.debug("attaching clean Company instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CompanyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CompanyDAO) ctx.getBean("CompanyDAO");
	}
}