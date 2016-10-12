package service;

import java.util.List;

import utils.msg;
import bean.OOrder;
import dao.OOrderDAO;

public class OOrderService {
	private String Msg;// 保存业务逻辑错误信息字段
	private OOrderDAO oorderDao;
	
	public OOrder Add(OOrder order) {
		OOrder db_order = oorderDao.merge(order);
		if (db_order == null) {
			this.Msg = msg.order_ordernull;
			return null;
		}
		this.Msg = msg.order_success;
		return db_order;
	}
	
	public boolean Delete(OOrder order) throws Exception {
		OOrder db_order = oorderDao.findById(order.getOrderId());
		if (db_order == null) {
			this.Msg = msg.order_ordernull;
			System.out.println(this.Msg);
			return false;
		}
		oorderDao.delete(db_order);
		this.Msg = msg.order_success;
		return true;
	}
	
	public OOrder View_Id(int orderID) throws Exception {
		OOrder db_order = oorderDao.findById(orderID);
		if (db_order == null) {
			this.Msg = msg.order_ordernull;
			return null;
		}
		this.Msg = msg.order_success;
		return db_order;
	}
	
	public OOrder View_Serial(String serial) throws Exception {
		List list = oorderDao.findByOrderSerial(serial);
		if (list.size() == 0) {
			this.Msg = msg.order_ordernull;
			return null;
		}
		OOrder db_order = (OOrder) list.get(0);
		this.Msg = msg.order_success;
		return db_order;
	}
	
	/**
	 * @param customerid
	 * @param statusPay
	 * @param statusMethod
	 * @param statusOrder
	 * @param statusSend
	 * @param statusRefund
	 * @param statusComment
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public int Count(int customerid, int statusPay, int statusMethod, int statusOrder, int statusSend, int statusRefund,
			int statusComment, String keyword) throws Exception {
		int count = oorderDao.getCount(customerid, statusPay, statusMethod, statusOrder, statusSend, statusRefund,
				statusComment, keyword);
		this.Msg = msg.order_success;
		return count;
	}
	
	/**
	 * @param customerid
	 * @param statusPay
	 * @param statusMethod
	 * @param statusOrder
	 * @param statusSend
	 * @param statusRefund
	 * @param statusComment
	 * @param keyword
	 * @param start
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public List Find_Asc(int customerid, int statusPay, int statusMethod, int statusOrder, int statusSend,
			int statusRefund, int statusComment, String keyword, final int start, final int length) throws Exception {
		List list = oorderDao.findAll(customerid, statusPay, statusMethod, statusOrder, statusSend, statusRefund,
				statusComment, keyword, "asc", start, length);
		this.Msg = msg.order_success;
		return list;
	}
	
	/**
	 * @param customerid
	 * @param statusPay
	 * @param statusMethod
	 * @param statusOrder
	 * @param statusSend
	 * @param statusRefund
	 * @param statusComment
	 * @param keyword
	 * @param start
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public List Find_Desc(int customerid, int statusPay, int statusMethod, int statusOrder, int statusSend,
			int statusRefund, int statusComment, String keyword, final int start, final int length) throws Exception {
		List list = oorderDao.findAll(customerid, statusPay, statusMethod, statusOrder, statusSend, statusRefund,
				statusComment, keyword, "desc", start, length);
		this.Msg = msg.order_success;
		return list;
	}
	
	public OOrder Update(OOrder order) throws Exception {
		OOrder db_order = oorderDao.findById(order.getOrderId());
		if (db_order == null) {
			this.Msg = msg.order_ordernull;
			System.out.println(this.Msg);
			return null;
		}
		OOrder result = oorderDao.merge(order);
		this.Msg = msg.order_success;
		return result;
	}
	
	public String getMsg() {
		return Msg;
	}
	
	public void setMsg(String msg) {
		Msg = msg;
	}
	
	public OOrderDAO getOorderDao() {
		return oorderDao;
	}
	
	public void setOorderDao(OOrderDAO oorderDao) {
		this.oorderDao = oorderDao;
	}
	
}
