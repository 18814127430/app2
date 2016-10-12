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
		List<?> list = sortDao.findBySortName(sort.getSortName());
		if (list.size() > 0) {
			this.Msg = msg.add_fail_name;
			System.out.println(this.Msg);
			return null;
		}
		Sort result = sortDao.merge(sort);
		this.Msg = msg.add_success;
		return result;
	}

	public boolean Delete(Sort sort) throws Exception {
		Sort db_sort = sortDao.findById(sort.getSortId());
		if (db_sort == null) {
			this.Msg = msg.delete_fail_id;
			System.out.println(this.Msg);
			return false;
		}
		sortDao.delete(sort);
		return true;
	}

	public Sort FindById(int sortID) throws Exception {
		Sort db_sort = sortDao.findById(sortID);
		if (db_sort == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		return db_sort;
	}

	public int GetCount() throws Exception {
		return sortDao.getCount(0, "");
	}

	public List Find() throws Exception {
		List list = sortDao.findAll();
		return list;
	}

	public int GetCountBySortId(int sortid) throws Exception {
		return sortDao.getCount(sortid, "");
	}

	public List FindBySortId(int sortid) throws Exception {
		List list = sortDao.findAll(sortid);
		return list;
	}

	public List FindBySortId(int sortid, int start, int length) throws Exception {
		List list = sortDao.findAll(sortid, "", start, length);
		return list;
	}

	public List FindBySortClass(int sortclass) throws Exception {
		List list = sortDao.findBySortClass(sortclass);
		return list;
	}

	public Sort Update(Sort sort) throws Exception {

		Sort db_sort = sortDao.findById(sort.getSortId());
		if (db_sort == null) {
			this.Msg = msg.update_fail_account;
			return null;
		}

		sort.setSortImgExtension(test.GetFileExtension(sort.getSortImgPath()));
		sort.setSortImgName(test.GetFileName(sort.getSortImgPath()));

		Sort result = sortDao.merge(sort);
		this.Msg = msg.update_success;
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
