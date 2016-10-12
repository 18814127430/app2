package service;

import java.util.List;

import utils.msg;
import bean.CAddress;
import bean.Cart;
import bean.Collect;
import bean.Page;
import dao.CartDAO;

public class CartService {
	private String Msg;// 保存业务逻辑错误信息字段
	private CartDAO cartDao;

	public Cart Add(Cart cart) throws Exception {

		Cart db_cart = Find_CustomerId_GoodsId(cart.getCustomer().getCustomerId(), cart.getGoods().getGoodsId());
		if (db_cart == null) {
			this.Msg = msg.cart_cartnull;
			System.out.println("----db_cart == null-----" + cart);
			db_cart = cartDao.merge(cart);
		} else {
			System.out.println("----db_cart != null-----" + cart);
			db_cart.setGoodsNum(cart.getGoodsNum());
			db_cart = cartDao.merge(db_cart);
		}
		this.Msg = msg.cart_success;
		return db_cart;
	}

	public Cart AddMore(Cart cart) throws Exception {

		Cart db_cart = Find_CustomerId_GoodsId(cart.getCustomer().getCustomerId(), cart.getGoods().getGoodsId());
		if (db_cart == null) {
			this.Msg = msg.cart_cartnull;
			System.out.println("----db_cart == null-----" + cart);
			db_cart = cartDao.merge(cart);
		} else {
			System.out.println("----db_cart != null-----" + cart);
			db_cart.setGoodsNum(db_cart.getGoodsNum() + cart.getGoodsNum());
			db_cart = cartDao.merge(db_cart);
		}
		this.Msg = msg.cart_success;
		return db_cart;
	}

	public boolean Delete(Cart cart) throws Exception {
		if (cart.getCartId() == null) {
			Cart db_cart = Find_CustomerId_GoodsId(cart.getCustomer().getCustomerId(), cart.getGoods().getGoodsId());
			if (db_cart != null) {
				System.out.println("----db_cart != null-----" + cart);
				cartDao.delete(db_cart);
			}
			this.Msg = msg.cart_success;
			return true;
		} else {
			Cart db_cart = cartDao.findById(cart.getCartId());
			if (db_cart == null) {
				this.Msg = msg.cart_cartnull;
				return false;
			}
			cartDao.delete(db_cart);
			this.Msg = msg.cart_success;
			return true;
		}
	}

	public Cart View(int cartID) throws Exception {
		Cart db_cart = cartDao.findById(cartID);
		if (db_cart == null) {
			this.Msg = msg.cart_cartnull;
			return null;
		}
		this.Msg = msg.cart_success;
		return db_cart;
	}

	/**
	 * @param customerid
	 * @param goodsid
	 * @return
	 * @throws Exception
	 */
	public Cart Find_CustomerId_GoodsId(int customerid, int goodsid) throws Exception {
		List list = cartDao.findAll(customerid, goodsid, "", 0, msg.RECORD_SIZE);
		if (list.size() == 0) {
			this.Msg = msg.cart_cartnull;
			return null;
		}
		System.out.println("list.size1:" + list.size());
		while (list.size() > 1) {
			System.out.println("----Find_CustomerId_GoodsId-----list.size1:" + list.size());
			cartDao.delete((Cart) list.get(1));
			list = cartDao.findAll(customerid, goodsid, "", 0, msg.RECORD_SIZE);
		}
		Cart db_cart = (Cart) list.get(0);
		this.Msg = msg.cart_success;
		return db_cart;
	}

	public int Count_Keyword(String keyword) throws Exception {
		int count = cartDao.getCount(0, 0, keyword);
		this.Msg = msg.cart_success;
		return count;
	}

	public List Find_Keyword(String keyword, int start, int length) throws Exception {
		List list = cartDao.findAll(0, 0, keyword, start, length);
		this.Msg = msg.cart_success;
		return list;
	}

	public int Count_CustomerId(int customerid, String keyword) throws Exception {
		int count = cartDao.getCount(customerid, 0, keyword);
		this.Msg = msg.cart_success;
		return count;
	}

	public List Find_CustomerId(int customerid, String keyword, int fromindex, int length) throws Exception {
		List list = cartDao.findAll(customerid, 0, keyword, fromindex, length);
		this.Msg = msg.cart_success;
		return list;
	}

	public int Count_GoodsId(int goodsid, String keyword) throws Exception {
		int count = cartDao.getCount(0, goodsid, keyword);
		this.Msg = msg.cart_success;
		return count;
	}

	public List Find_GoodsId(int goodsid, String keyword, int fromindex, int length) throws Exception {
		List list = cartDao.findAll(0, goodsid, keyword, fromindex, length);
		this.Msg = msg.cart_success;
		return list;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public CartDAO getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDAO cartDao) {
		this.cartDao = cartDao;
	}

}
