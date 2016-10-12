package service;

import java.util.List;

import utils.msg;
import bean.Company;
import dao.CompanyDAO;

public class CompanyService {
	private String Msg;// 保存业务逻辑错误信息字段
	private CompanyDAO companyDao;

	public Company Add(Company company) {
		Company db_company = companyDao.findById(company.getCompanyId());
		if (db_company != null) {
			this.Msg = msg.add_fail_name;
			System.out.println(this.Msg);
			return null;
		}
		Company result = companyDao.merge(company);
		this.Msg = msg.add_success;
		return result;
	}

	public boolean Delete(Company company) throws Exception {
		Company db_company = companyDao.findById(company.getCompanyId());
		if (db_company == null) {
			this.Msg = msg.delete_fail_id;
			System.out.println(this.Msg);
			return false;
		}
		companyDao.delete(company);
		return true;
	}

	public Company View(int companyID) throws Exception {
		Company db_company = companyDao.findById(companyID);
		if (db_company == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		return db_company;
	}

	public List Find(String keyword) throws Exception {
		List list = companyDao.findAll();
		return list;
	}

	public Company Update(Company company) throws Exception {
		Company db_company = companyDao.findById(company.getCompanyId());
		if (db_company == null) {
			this.Msg = msg.update_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		Company result = companyDao.merge(company);
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
