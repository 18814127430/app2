package service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import utils.msg;
import bean.Page;
import dao.PageDAO;

public class PageService {
	private String Msg;// 保存业务逻辑错误信息字段
	private PageDAO pageDao;

	public Page Update(Page page) {
		Page db_page = pageDao.merge(page);
		if (db_page == null) {
			this.Msg = msg.page_pagenull;
			return null;
		}
		this.Msg = msg.page_success;
		return db_page;
	}

	public List Find_All() throws Exception {
		List list = pageDao.findAll();
		System.out.println("list.size1:" + list.size());
		while (list.size() > 4) {
			System.out.println("list.size1:" + list.size());
			pageDao.delete((Page) list.get(4));
			list = pageDao.findAll();
		}
		if (list.size() <= 0) {
			this.Msg = msg.page_pagenull;
			return null;
		}

		this.Msg = msg.page_success;
		return list;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public PageDAO getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDAO pageDao) {
		this.pageDao = pageDao;
	}

}
