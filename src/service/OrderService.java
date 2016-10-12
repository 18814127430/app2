package service;

import java.util.List;

import utils.msg;
import bean.Order;
import dao.OrderDAO;

public class OrderService {
	private String Msg;// 保存业务逻辑错误信息字段
	private OrderDAO orderDao;

	public Order Add(Order order) {
		Order db_order = orderDao.findById(order.getOrderId());
		if (db_order != null) {
			this.Msg = msg.add_fail_name;
			System.out.println(this.Msg);
			return null;
		}
		Order result = orderDao.merge(order);
		this.Msg = msg.add_success;
		return result;
	}

	public boolean Delete(Order order) throws Exception {
		Order db_order = orderDao.findById(order.getOrderId());
		if (db_order == null) {
			this.Msg = msg.delete_fail_id;
			System.out.println(this.Msg);
			return false;
		}
		orderDao.delete(order);
		return true;
	}

	public Order View(int orderID) throws Exception {
		Order db_order = orderDao.findById(orderID);
		if (db_order == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		return db_order;
	}

	public int GetCountByCustomerId(int customerid, String keyword) throws Exception {
		return orderDao.getCount(customerid, 0, "", keyword);
	}

	public List FindByCustomerId(int customerid, String keyword, int fromindex, int length) throws Exception {
		List list = orderDao.findAll(customerid, 0, "", keyword, fromindex, length);
		return list;
	}

	public int GetCountByOrderStatus(String orderstatus, String keyword) throws Exception {
		return orderDao.getCount(0, 0, orderstatus, keyword);
	}

	public List FindByOrderStatus(String orderstatus, String keyword, int start, int length) throws Exception {
		List list = orderDao.findAll(0, 0, orderstatus, keyword, start, length);
		return list;
	}

	public int GetCountByGoodsId(int goodsid, String keyword) throws Exception {
		return orderDao.getCount(0, goodsid, "", keyword);
	}

	public List FindByGoodsId(int goodsid, String keyword, int start, int length) throws Exception {
		List list = orderDao.findAll(0, goodsid, "", keyword, start, length);
		return list;
	}

	public int GetCount(String keyword) throws Exception {
		return orderDao.getCount(0, 0, "", keyword);
	}

	public List<?> Find(String keyword, int start, int length) throws Exception {
		List<?> list = orderDao.findAll(0, 0, "", keyword, start, length);
		System.out.println("5859695959595294:" + list.size());
		return list;
	}

	public Order Update(Order order) throws Exception {
		Order db_order = orderDao.findById(order.getOrderId());
		if (db_order == null) {
			this.Msg = msg.update_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		Order result = orderDao.merge(order);
		return result;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

}
