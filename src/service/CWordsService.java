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

import bean.CWords;
import dao.CWordsDAO;

public class CWordsService {
	private String Msg;// 保存业务逻辑错误信息字段
	private CWordsDAO cwordsDao;

	public CWords Add(CWords cwords) {
		List<?> list = cwordsDao.findByExample(cwords);
		if (list.size() > 0) {
			this.Msg = msg.add_fail;
			return null;
		}
		CWords result = cwordsDao.merge(cwords);
		return result;
	}

	public Boolean Delete(int cwordsid) {
		CWords cwords = cwordsDao.findById(cwordsid);
		if (cwords == null) {
			this.Msg = msg.add_fail;
			return false;
		}
		cwordsDao.delete(cwords);
		return true;
	}

	public CWords View(int cwordsID) throws Exception {
		CWords db_address = cwordsDao.findById(cwordsID);
		if (db_address == null) {
			this.Msg = msg.find_fail;
			return null;
		}
		return db_address;
	}

	public int GetCount(String keyword) throws Exception {
		return cwordsDao.getCount(0, 0, keyword);
	}

	public List Find(String keyword, int start, int length) throws Exception {
		List list = cwordsDao.findAll(0, 0, keyword, start, length);
		System.out.println("5859294:" + list.size());
		return list;
	}

	public int GetCountByCustomerId(int customerid, String keyword) throws Exception {
		return cwordsDao.getCount(customerid, 0, keyword);
	}

	public List FindByCustomerId(int customerid, String keyword, int fromindex, int length) throws Exception {
		List list = cwordsDao.findAll(customerid, 0, keyword, fromindex, length);
		return list;
	}

	public int GetCountByAdminId(int adminid, String keyword) throws Exception {
		return cwordsDao.getCount(0, adminid, keyword);
	}

	public List FindByAdminId(int adminid, String keyword, int fromindex, int length) throws Exception {
		List list = cwordsDao.findAll(0, adminid, keyword, fromindex, length);
		return list;
	}

	public CWords Update(CWords cwords) throws Exception {
		CWords db_adress = cwordsDao.findById(cwords.getWordsId());
		if (db_adress == null) {
			this.Msg = msg.update_fail_id;
			System.out.println(this.Msg);
			return null;
		}

		CWords result = cwordsDao.merge(cwords);
		this.Msg = msg.update_success;
		return result;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public CWordsDAO getCwordsDao() {
		return cwordsDao;
	}

	public void setCwordsDao(CWordsDAO cwordsDao) {
		this.cwordsDao = cwordsDao;
	}

}
