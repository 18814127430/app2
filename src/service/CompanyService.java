package service;

import java.util.List;

import utils.msg;
import bean.Company;
import dao.CompanyDAO;

public class CompanyService {
	private String Msg;// 保存业务逻辑错误信息字段
	private CompanyDAO companyDao;
	
	public Company Add(Company company) {
		List list = companyDao.findByCompanyName(company.getCompanyName());
		if (list.size() >= 1) {
			this.Msg = msg.company_companyexist;
			return null;
		}
		Company db_company = companyDao.merge(company);
		if (db_company == null) {
			this.Msg = msg.company_fail;
			return null;
		}
		this.Msg = msg.company_success;
		return db_company;
	}
	
	public boolean Delete(Company company) throws Exception {
		Company db_company = companyDao.findById(company.getCompanyId());
		if (db_company == null) {
			this.Msg = msg.company_companynull;
			return false;
		}
		companyDao.delete(db_company);
		this.Msg = msg.company_success;
		return true;
	}
	
	public Company View(int companyID) throws Exception {
		Company db_company = companyDao.findById(companyID);
		if (db_company == null) {
			this.Msg = msg.company_companynull;
			return null;
		}
		this.Msg = msg.company_success;
		return db_company;
	}
	
	public List Find_All(String keyword) throws Exception {
		List list = companyDao.findAll();
		this.Msg = msg.company_success;
		return list;
	}
	
	public int Count_Keyword(String keyword) {
		int count = companyDao.getCount(keyword);
		this.Msg = msg.company_success;
		return count;
	}
	
	public List Find_Keyword(String keyword, int fromindex, int length) throws Exception {
		List list = companyDao.findAll(keyword, fromindex, length);
		this.Msg = msg.company_success;
		return list;
	}
	
	public Company Update(Company company) throws Exception {
		Company db_company = companyDao.findById(company.getCompanyId());
		if (db_company == null) {
			this.Msg = msg.company_companynull;
			return null;
		}
		Company result = companyDao.merge(company);
		this.Msg = msg.company_success;
		return result;
	}
	
	public String getMsg() {
		return Msg;
	}
	
	public void setMsg(String msg) {
		Msg = msg;
	}
	
	public CompanyDAO getCompanyDao() {
		return companyDao;
	}
	
	public void setCompanyDao(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}
	
}
