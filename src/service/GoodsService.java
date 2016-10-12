package service;

import java.util.List;

import utils.msg;
import bean.Goods;
import dao.GoodsDAO;

public class GoodsService {
	private String Msg;// 保存业务逻辑错误信息字段
	private GoodsDAO goodsDao;
	
	public Goods Add(Goods goods) {
		List list = goodsDao.findByGoodsName(goods.getGoodsName());
		if (list.size() > 0) {
			this.Msg = msg.goods_goodsexist;
			return null;
		}
		Goods db_goods = goodsDao.merge(goods);
		this.Msg = msg.goods_success;
		return db_goods;
	}
	
	public boolean Delete(Goods goods) throws Exception {
		Goods db_goods = goodsDao.findById(goods.getGoodsId());
		if (db_goods == null) {
			this.Msg = msg.goods_goodsnull;
			return false;
		}
		goodsDao.delete(db_goods);
		this.Msg = msg.goods_success;
		return true;
	}
	
	public Goods View(int goodsID) throws Exception {
		Goods db_goods = goodsDao.findById(goodsID);
		if (db_goods == null) {
			this.Msg = msg.goods_goodsnull;
			return null;
		}
		this.Msg = msg.goods_success;
		return db_goods;
	}
	
	public int Count_Sort_Keyword(int parentsort, int sortid, String keyword) throws Exception {
		int count = goodsDao.getCount(parentsort, sortid, keyword);
		this.Msg = msg.goods_success;
		return count;
	}
	
	public List Find_Sort_Parent(int parentsortid, String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(parentsortid, 0, keyword, "", "", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public List Find_Sort_Parent_Rand(int parentsortid, String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(parentsortid, 0, keyword, "rand", "", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public List Find_Sort_Desc(int sortid, String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(0, sortid, keyword, "time", "desc", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public List Find_Time_Desc(int sortid, String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(0, sortid, keyword, "time", "desc", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public List Find_Time_Asc(int sortid, String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(0, sortid, keyword, "time", "asc", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public List Find_Time(int sortid, String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(0, sortid, keyword, "time", "", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public List Find_Price_Desc(int sortid, String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(0, sortid, keyword, "price", "desc", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public List Find_Price_Asc(int sortid, String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(0, sortid, keyword, "price", "asc", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public List Find_Price(int sortid, String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(0, sortid, keyword, "price", "", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public List Find_Sales_Desc(int sortid, String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(0, sortid, keyword, "sales", "desc", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public List Find_Sales_Asc(int sortid, String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(0, sortid, keyword, "sales", "asc", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public List Find_Sales(int sortid, String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(0, sortid, keyword, "sales", "", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public List Find_Rand(int sortid, String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(0, sortid, keyword, "rand", "", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public List Find_Desc(String keyword, int start, int length) throws Exception {
		List list = goodsDao.findAll(0, 0, keyword, "time", "desc", start, length);
		System.out.println("list.size:" + list.size());
		this.Msg = msg.goods_success;
		return list;
	}
	
	public Goods Update(Goods goods) throws Exception {
		Goods db_goods = goodsDao.findById(goods.getGoodsId());
		if (db_goods == null) {
			this.Msg = msg.goods_goodsnull;
			return null;
		}
		Goods result = goodsDao.merge(goods);
		this.Msg = msg.goods_success;
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
