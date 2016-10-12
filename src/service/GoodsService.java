package service;

import java.util.List;

import utils.msg;
import bean.Goods;
import dao.GoodsDAO;

public class GoodsService {
	private String Msg;// 保存业务逻辑错误信息字段
	private GoodsDAO goodsDao;

	public Goods Add(Goods goods) {
		List<?> list = goodsDao.findByGoodsName(goods.getGoodsName());
		if (list.size() > 0) {
			this.Msg = msg.add_fail_name;
			System.out.println(this.Msg);
			return null;
		}
		Goods result = goodsDao.merge(goods);
		this.Msg = msg.add_success;
		return result;
	}

	public boolean Delete(Goods goods) throws Exception {
		Goods db_goods = goodsDao.findById(goods.getGoodsId());
		if (db_goods == null) {
			this.Msg = msg.delete_fail_id;
			System.out.println(this.Msg);
			return false;
		}
		goodsDao.delete(goods);
		return true;
	}

	public Goods View(int goodsID) throws Exception {
		Goods db_goods = goodsDao.findById(goodsID);
		if (db_goods == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		return db_goods;
	}

	public int GetCount(String keyword) throws Exception {
		return goodsDao.getCount(0, keyword);
	}

	public List Find(String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(0, keyword, start, length);
		System.out.println("5859294:" + list.size());
		return list;
	}

	public int GetCountBySortId(int sortid, String keyword) throws Exception {
		return goodsDao.getCount(sortid, keyword);
	}

	public List FindBySortId(int sortid, int start, int length) throws Exception {

		List list = goodsDao.findAll(sortid, "", start, length);
		System.out.println("5859294:" + list.size());
		return list;

	}

	public Goods Update(Goods goods) throws Exception {
		Goods db_goods = goodsDao.findById(goods.getGoodsId());
		if (db_goods == null) {
			this.Msg = msg.update_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		Goods result = goodsDao.merge(goods);
		return result;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public GoodsDAO getGoodsDao() {
		return goodsDao;
	}

	public void setGoodsDao(GoodsDAO goodsDao) {
		this.goodsDao = goodsDao;
	}

}
