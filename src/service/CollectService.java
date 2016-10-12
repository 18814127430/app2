package service;

import java.util.List;

import utils.msg;
import bean.Cart;
import bean.Collect;
import dao.CollectDAO;

public class CollectService {
	private String Msg;// 保存业务逻辑错误信息字段
	private CollectDAO collectDao;

	public Collect Add(Collect collect) throws Exception {
		Collect db_collect = Find_CustomerId_GoodsId(collect.getCustomer().getCustomerId(), collect.getGoods().getGoodsId());
		System.out.println("------->db_3collect:" + db_collect);
		if (db_collect == null) {
			this.Msg = msg.collect_collectnull;
			System.out.println("----db_collect == null-----" + collect);
			db_collect = collectDao.merge(collect);
		}
		this.Msg = msg.collect_success;
		return db_collect;
	}

	public boolean Delete(Collect collect) throws Exception {
		if (collect.getCollectId() == null) {
			Collect db_collect = Find_CustomerId_GoodsId(collect.getCustomer().getCustomerId(), collect.getGoods().getGoodsId());
			if (db_collect != null) {
				System.out.println("----db_collect != null-----" + collect);
				collectDao.delete(db_collect);
			}
			this.Msg = msg.collect_success;
			return true;
		} else {
			Collect db_collect = collectDao.findById(collect.getCollectId());
			if (db_collect == null) {
				this.Msg = msg.collect_collectnull;
				return false;
			}
			collectDao.delete(db_collect);
			this.Msg = msg.collect_success;
			return true;
		}
	}

	public Collect View(int collectID) throws Exception {
		Collect db_collect = collectDao.findById(collectID);
		if (db_collect == null) {
			this.Msg = msg.collect_collectnull;
			return null;
		}
		this.Msg = msg.collect_success;
		return db_collect;
	}

	public Collect Find_CustomerId_GoodsId(int customerid, int goodsid) throws Exception {
		List list = collectDao.findAll(customerid, goodsid, "", 0, msg.RECORD_SIZE);
		if (list.size() == 0) {
			this.Msg = msg.collect_collectnull;
			return null;
		}
		System.out.println("list.size1:" + list.size());
		while (list.size() > 1) {
			System.out.println("----Find_CustomerId_GoodsId-----list.size1:" + list.size());
			collectDao.delete((Collect) list.get(1));
			list = collectDao.findAll(customerid, goodsid, "", 0, msg.RECORD_SIZE);
		}
		Collect db_collect = (Collect) list.get(0);
		this.Msg = msg.collect_success;
		return db_collect;
	}

	public int Count_Keyword(String keyword) throws Exception {
		int count = collectDao.getCount(0, 0, keyword);
		this.Msg = msg.collect_success;
		return count;
	}

	public List Find_Keyword(String keyword, int start, int length) throws Exception {
		List list = collectDao.findAll(0, 0, keyword, start, length);
		this.Msg = msg.collect_success;
		return list;
	}

	public int Count_CustomerId(int customerid, String keyword) throws Exception {
		int count = collectDao.getCount(customerid, 0, keyword);
		this.Msg = msg.collect_success;
		return count;
	}

	public List Find_CustomerId(int customerid, String keyword, int fromindex, int length) throws Exception {
		List list = collectDao.findAll(customerid, 0, keyword, fromindex, length);
		this.Msg = msg.collect_success;
		return list;
	}

	public int Count_GoodsId(int goodsid, String keyword) throws Exception {
		int count = collectDao.getCount(0, goodsid, keyword);
		this.Msg = msg.collect_success;
		return count;
	}

	public List Find_GoodsId(int goodsid, String keyword, int fromindex, int length) throws Exception {
		List list = collectDao.findAll(0, goodsid, keyword, fromindex, length);
		this.Msg = msg.collect_success;
		return list;
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
