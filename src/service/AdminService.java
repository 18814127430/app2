package service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import utils.msg;
import utils.test;
import bean.Admin;
import dao.AdminDAO;

public class AdminService {
	private String Msg;// 保存业务逻辑错误信息字段
	private AdminDAO adminDao;

	public Admin Login(Admin admin) {
		List<?> list = adminDao.findByAdminAccount(admin.getAdminAccount());
		if (null == list || list.size() == 0) {
			this.Msg = msg.admin_accountnull;
			return null;
		}
		Admin db_admin = (Admin) list.get(0);
		if (!db_admin.getAdminPassword().equals(admin.getAdminPassword())) {
			this.Msg = msg.admin_passwordwrong;
			return null;
		}
		this.Msg = msg.admin_success;
		return db_admin;
	}

	public Admin Add(Admin admin) {
		List list = adminDao.findByAdminAccount(admin.getAdminAccount());
		if (list.size() > 0) {
			this.Msg = msg.admin_accountexist;
			return null;
		}
		Admin result = adminDao.merge(admin);
		this.Msg = msg.admin_success;
		return result;
	}

	public boolean Delete(Admin admin) throws Exception {
		Admin db_admin = adminDao.findById(admin.getAdminId());
		if (db_admin == null) {
			this.Msg = msg.admin_idnull;
			return false;
		}
		adminDao.delete(db_admin);
		this.Msg = msg.admin_success;
		return true;
	}

	public Admin View(int adminID) throws Exception {
		Admin db_admin = adminDao.findById(adminID);
		if (db_admin == null) {
			this.Msg = msg.admin_idnull;
			return null;
		}
		this.Msg = msg.admin_success;
		return db_admin;
	}

	public int Count_Keyword(String keyword) throws Exception {
		int count = adminDao.getCount(keyword);
		this.Msg = msg.admin_success;
		return count;
	}

	public List Find_Keyword(String keyword, int start, int length) throws Exception {
		List list = adminDao.findAll(keyword, start, length);
		this.Msg = msg.admin_success;
		return list;
	}

	public Admin Update(Admin admin) throws Exception {
		List list = adminDao.findByAdminAccount(admin.getAdminAccount());
		if (!(list.size() == 1)) {
			this.Msg = msg.admin_accountexist;
			return null;
		}

		Admin result = adminDao.merge(admin);
		this.Msg = msg.admin_success;
		return result;
	}

	public String getMsg() {
		return Msg;
	}

	public AdminDAO getAdminDao() {
		return adminDao;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public void setAdminDao(AdminDAO adminDao) {
		this.adminDao = adminDao;
	}

}
