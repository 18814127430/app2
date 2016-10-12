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
import bean.Customer;
import dao.CAddressDAO;

public class CAddressService {
	private String Msg;// 保存业务逻辑错误信息字段
	private CAddressDAO caddressDao;

	public CAddress Add(CAddress caddress) {
		CAddress db_address = caddressDao.merge(caddress);
		if (db_address == null) {
			this.Msg = msg.caddress_fail;
			return null;
		}
		this.Msg = msg.caddress_success;
		return db_address;
	}

	public boolean Delete(CAddress caddress) throws Exception {
		CAddress db_address = caddressDao.findById(caddress.getAddressId());
		if (db_address == null) {
			this.Msg = msg.caddress_addressnull;
			return false;
		}
		caddressDao.delete(db_address);
		this.Msg = msg.caddress_success;
		return true;
	}

	public CAddress View(int caddressid) throws Exception {
		CAddress db_address = caddressDao.findById(caddressid);
		if (db_address == null) {
			this.Msg = msg.caddress_fail;
			return null;
		}
		this.Msg = msg.caddress_success;
		return db_address;
	}

	public int Count_Keyword(String keyword) throws Exception {
		int count = caddressDao.getCount(0, keyword);
		this.Msg = msg.caddress_success;
		return count;
	}

	public List Find_Keyword(String keyword, int start, int length) throws Exception {
		List list = caddressDao.findAll(0, keyword, start, length);
		this.Msg = msg.caddress_success;
		return list;
	}

	public int Count_CustomerId(int customerid, String keyword) throws Exception {
		int count = caddressDao.getCount(customerid, keyword);
		this.Msg = msg.caddress_success;
		return count;
	}

	public List Find_CustomerId(int customerid, String keyword, int fromindex, int length) throws Exception {
		List list = caddressDao.findAll(customerid, keyword, fromindex, length);
		this.Msg = msg.caddress_success;
		return list;
	}

	public CAddress Update(CAddress caddress) throws Exception {
		CAddress db_address = caddressDao.findById(caddress.getAddressId());
		if (db_address == null) {
			this.Msg = msg.caddress_addressnull;
			return null;
		}

		CAddress result = caddressDao.merge(caddress);
		this.Msg = msg.caddress_success;
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
