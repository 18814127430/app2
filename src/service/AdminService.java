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
			this.Msg = msg.login_fail_account;
			return null;
		}
		Admin db_admin = (Admin) list.get(0);
		if (!db_admin.getAdminPassword().equals(admin.getAdminPassword())) {
			this.Msg = msg.login_fail_password;
			return null;
		}
		return db_admin;
	}

	public Admin Add(Admin admin) {
		List<?> list = adminDao.findByAdminAccount(admin.getAdminAccount());
		if (list.size() > 0) {
			this.Msg = msg.add_fail_account;
			return null;
		}

		Admin result = adminDao.merge(admin);
		this.Msg = msg.add_success;
		return result;
	}

	public boolean Delete(Admin admin) throws Exception {
		Admin db_admin = adminDao.findById(admin.getAdminId());
		if (db_admin == null) {
			this.Msg = msg.delete_fail_id;
			return false;
		}
		adminDao.delete(admin);
		this.Msg = msg.delete_success;
		return true;
	}

	public Admin View(int adminID) throws Exception {
		Admin db_admin = adminDao.findById(adminID);
		if (db_admin == null) {
			this.Msg = msg.find_fail_id;
			return null;
		}
		return db_admin;
	}

	public int GetCount(String keyword) throws Exception {
		return adminDao.getCount(keyword);
	}

	public List<Admin> Find(String keyword, int start, int length) throws Exception {
		List<Admin> list = adminDao.findAll(keyword, start, length);
		System.out.println("=======>list.size:" + list.size());
		return list;
	}

	public Admin Update(Admin admin) throws Exception {
		List<?> list = adminDao.findByAdminAccount(admin.getAdminAccount());
		if (list.size() == 0) {
			this.Msg = msg.update_fail_account;
			return null;
		}

		Admin result = adminDao.merge(admin);
		this.Msg = msg.update_success;
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
