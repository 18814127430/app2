package service;

import java.util.List;

import utils.msg;
import bean.Cart;
import dao.CartDAO;

public class CartService {
	private String Msg;// 保存业务逻辑错误信息字段
	private CartDAO cartDao;

	public Cart Add(Cart cart) {
		Cart db_cart = cartDao.findById(cart.getCartId());
		if (db_cart != null) {
			this.Msg = msg.add_fail_name;
			System.out.println(this.Msg);
			return null;
		}
		Cart result = cartDao.merge(cart);
		this.Msg = msg.add_success;
		return result;
	}

	public boolean Delete(Cart cart) throws Exception {
		Cart db_cart = cartDao.findById(cart.getCartId());
		if (db_cart == null) {
			this.Msg = msg.delete_fail_id;
			System.out.println(this.Msg);
			return false;
		}
		cartDao.delete(cart);
		return true;
	}

	public Cart View(int cartID) throws Exception {
		Cart db_cart = cartDao.findById(cartID);
		if (db_cart == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		return db_cart;
	}

	public int GetCount(String keyword) throws Exception {
		return cartDao.getCount(0, 0, keyword);
	}

	public List Find(String keyword, int start, int length) throws Exception {
		List list = cartDao.findAll(0, 0, keyword, start, length);
		System.out.println("5859294:" + list.size());
		return list;
	}

	public int GetCountByCustomerId(int customerid, String keyword) throws Exception {
		return cartDao.getCount(customerid, 0, keyword);
	}

	public List FindByCustomerId(int customerid, String keyword, int fromindex, int length) throws Exception {
		List list = cartDao.findAll(customerid, 0, keyword, fromindex, length);
		return list;
	}

	public int GetCountByGoodsId(int goodsid, String keyword) throws Exception {
		return cartDao.getCount(0, goodsid, keyword);
	}

	public List FindByGoodsId(int goodsid, String keyword, int fromindex, int length) throws Exception {
		List list = cartDao.findAll(0, goodsid, keyword, fromindex, length);
		return list;
	}

	public Cart Update(Cart cart) throws Exception {
		Cart db_cart = cartDao.findById(cart.getCartId());
		if (db_cart == null) {
			this.Msg = msg.update_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		Cart result = cartDao.merge(cart);
		return result;
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
