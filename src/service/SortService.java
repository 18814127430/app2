package service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import utils.msg;
import utils.test;
import bean.Sort;
import bean.Sort;
import dao.SortDAO;

public class SortService {
	private String Msg;// 保存业务逻辑错误信息字段
	private SortDAO sortDao;
	
	public Sort Add(Sort sort) {
		List list = sortDao.findBySortName(sort.getSortName());
		if (list.size() > 0) {
			this.Msg = msg.sort_sortexist;
			return null;
		}
		Sort db_sort = sortDao.merge(sort);
		if (db_sort == null) {
			this.Msg = msg.sort_sortnull;
			return null;
		}
		this.Msg = msg.sort_success;
		return db_sort;
	}
	
	public boolean Delete(Sort sort) throws Exception {
		Sort db_sort = sortDao.findById(sort.getSortId());
		if (db_sort == null) {
			this.Msg = msg.sort_sortnull;
			System.out.println(this.Msg);
			return false;
		}
		sortDao.delete(db_sort);
		this.Msg = msg.sort_success;
		return true;
	}
	
	public Sort View(int sortID) throws Exception {
		Sort db_sort = sortDao.findById(sortID);
		if (db_sort == null) {
			this.Msg = msg.sort_sortnull;
			return null;
		}
		this.Msg = msg.sort_success;
		return db_sort;
	}
	
	public int Count_All() throws Exception {
		int count = sortDao.getCount(0, "");
		this.Msg = msg.sort_success;
		return count;
	}
	
	public List Find_All() throws Exception {
		List list = sortDao.findAll();
		this.Msg = msg.sort_success;
		return list;
	}
	
	public int Count_SortId(int sortid) throws Exception {
		int count = sortDao.getCount(sortid, "");
		this.Msg = msg.sort_success;
		return count;
	}
	
	public List Find_SortId(int sortid) throws Exception {
		List list = sortDao.findAll(sortid);
		this.Msg = msg.sort_success;
		return list;
	}
	
	public List Find_SortId(int sortid, int start, int length) throws Exception {
		List list = sortDao.findAll(sortid, "", start, length);
		this.Msg = msg.sort_success;
		return list;
	}
	
	public List Find_SortClass(int sortclass) throws Exception {
		List list = sortDao.findBySortClass(sortclass);
		this.Msg = msg.sort_success;
		return list;
	}
	
	public Sort Update(Sort sort) throws Exception {
		Sort db_sort = sortDao.findById(sort.getSortId());
		if (db_sort == null) {
			this.Msg = msg.sort_sortnull;
			return null;
		}
		List list = sortDao.findBySortName(sort.getSortName());
		for (int i = 0; i < list.size(); i++) {
			Sort sort1 = (Sort) list.get(i);
			if (sort1.getSortId().intValue() != sort.getSortId().intValue()) {
				test.a(sort1);
				test.a(sort);
				this.Msg = msg.sort_sortexist;
				return null;
			}
		}
		Sort result = sortDao.merge(sort);
		this.Msg = msg.sort_success;
		return result;
		
	}
	
	public String getMsg() {
		return Msg;
	}
	
	public void setMsg(String msg) {
		Msg = msg;
	}
	
	public SortDAO getSortDao() {
		return sortDao;
	}
	
	public void setSortDao(SortDAO sortDao) {
		this.sortDao = sortDao;
	}
	
}
