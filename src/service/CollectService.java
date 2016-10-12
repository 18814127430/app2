package service;

import java.util.List;

import utils.msg;
import bean.Collect;
import dao.CollectDAO;

public class CollectService {
	private String Msg;// 保存业务逻辑错误信息字段
	private CollectDAO collectDao;

	public Collect Add(Collect collect) {
		Collect db_collect = collectDao.findById(collect.getCollectId());
		if (db_collect != null) {
			this.Msg = msg.add_fail_name;
			System.out.println(this.Msg);
			return null;
		}
		Collect result = collectDao.merge(collect);
		this.Msg = msg.add_success;
		return result;
	}

	public boolean Delete(Collect collect) throws Exception {
		Collect db_collect = collectDao.findById(collect.getCollectId());
		if (db_collect == null) {
			this.Msg = msg.delete_fail_id;
			System.out.println(this.Msg);
			return false;
		}
		collectDao.delete(collect);
		return true;
	}

	public Collect View(int collectID) throws Exception {
		Collect db_collect = collectDao.findById(collectID);
		if (db_collect == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		return db_collect;
	}

	public int GetCount(String keyword) throws Exception {
		return collectDao.getCount(0, 0, keyword);
	}

	public List Find(String keyword, int start, int length) throws Exception {
		List list = collectDao.findAll(0, 0, keyword, start, length);
		System.out.println("5859294:" + list.size());
		return list;
	}

	public int GetCountByCustomerId(int customerid, String keyword) throws Exception {
		return collectDao.getCount(customerid, 0, keyword);
	}

	public List FindByCustomerId(int customerid, String keyword, int fromindex, int length) throws Exception {
		List list = collectDao.findAll(customerid, 0, keyword, fromindex, length);
		return list;
	}

	public int GetCountByGoodsId(int goodsid, String keyword) throws Exception {
		return collectDao.getCount(0, goodsid, keyword);
	}

	public List FindByGoodsId(int goodsid, String keyword, int fromindex, int length) throws Exception {
		List list = collectDao.findAll(0, goodsid, keyword, fromindex, length);
		return list;
	}

	public Collect Update(Collect collect) throws Exception {
		Collect db_collect = collectDao.findById(collect.getCollectId());
		if (db_collect == null) {
			this.Msg = msg.update_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		Collect result = collectDao.merge(collect);
		return result;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public CollectDAO getCollectDao() {
		return collectDao;
	}

	public void setCollectDao(CollectDAO collectDao) {
		this.collectDao = collectDao;
	}

}
