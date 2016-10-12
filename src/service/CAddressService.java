package service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import utils.msg;

import com.opensymphony.xwork2.ActionContext;

import bean.Admin;
import bean.CAddress;
import bean.CWords;
import bean.Customer;
import dao.CAddressDAO;

public class CAddressService {
	private String Msg;// 保存业务逻辑错误信息字段
	private CAddressDAO caddressDao;

	public CAddress Add(CAddress caddress) {

		CAddress result = caddressDao.merge(caddress);
		return result;
	}

	public boolean Delete(CAddress caddress) throws Exception {
		CAddress db_address = caddressDao.findById(caddress.getAddressId());
		if (db_address == null) {
			this.Msg = msg.delete_fail;
			return false;
		}
		caddressDao.delete(caddress);
		return true;
	}

	public CAddress View(int caddressid) throws Exception {
		CAddress db_address = caddressDao.findById(caddressid);
		if (db_address == null) {
			this.Msg = msg.find_fail;
			return null;
		}
		return db_address;
	}

	public int GetCount(String keyword) throws Exception {
		return caddressDao.getCount(0, keyword);
	}

	public List Find(String keyword, int start, int length) throws Exception {
		List list = caddressDao.findAll(0, keyword, start, length);
		System.out.println("5859294:" + list.size());
		return list;
	}

	public int GetCountByCustomerId(int customerid, String keyword) throws Exception {
		return caddressDao.getCount(customerid, keyword);
	}

	public List FindByCustomerId(int customerid, String keyword, int fromindex, int length) throws Exception {
		List list = caddressDao.findAll(customerid, keyword, fromindex, length);
		return list;
	}

	public CAddress Update(CAddress caddress) throws Exception {
		CAddress db_adress = caddressDao.findById(caddress.getAddressId());
		if (db_adress == null) {
			this.Msg = msg.update_fail_id;
			System.out.println(this.Msg);
			return null;
		}

		CAddress result = caddressDao.merge(caddress);
		this.Msg = msg.update_success;
		return result;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public CAddressDAO getCaddressDao() {
		return caddressDao;
	}

	public void setCaddressDao(CAddressDAO caddressDao) {
		this.caddressDao = caddressDao;
	}

}
