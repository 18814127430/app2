package service;

import java.util.List;

import utils.msg;
import bean.OInfo;
import bean.OOrder;
import dao.OInfoDAO;

public class OInfoService {
	private String Msg;// 保存业务逻辑错误信息字段
	private OInfoDAO oinfoDao;
	
	public OInfo Add(OInfo oinfo) {
		OInfo db_oinfo = oinfoDao.merge(oinfo);
		if (db_oinfo == null) {
			this.Msg = msg.oinfo_oinfonull;
			return null;
		}
		this.Msg = msg.oinfo_success;
		return db_oinfo;
	}
	
	public boolean Delete(OInfo oinfo) throws Exception {
		OInfo db_oinfo = oinfoDao.findById(oinfo.getOinfoId());
		if (db_oinfo == null) {
			this.Msg = msg.oinfo_oinfonull;
			return false;
		}
		oinfoDao.delete(db_oinfo);
		this.Msg = msg.oinfo_success;
		return true;
	}
	
	public OInfo View_Id(int oinfoID) throws Exception {
		OInfo db_oinfo = oinfoDao.findById(oinfoID);
		if (db_oinfo == null) {
			this.Msg = msg.oinfo_oinfonull;
			return null;
		}
		this.Msg = msg.oinfo_success;
		return db_oinfo;
	}
	
	public OInfo View_Serial(String serial) throws Exception {
		List list = oinfoDao.findByOinfoSerial(serial);
		if (list.size() == 0) {
			this.Msg = msg.oinfo_oinfonull;
			return null;
		}
		OInfo db_oinfo = (OInfo) list.get(0);
		this.Msg = msg.oinfo_success;
		return db_oinfo;
	}
	
	/**
	 * @param orderid
	 * @param customerid
	 * @param goodsid
	 * @param gbatchid
	 * @param statusSend
	 * @param statusRefund
	 * @param statusOrder
	 * @param statusPay
	 * @param statusMethod
	 * @param statusComment
	 * @param keyword
	 * @return
	 */
	public int Count(int orderid, int customerid, int goodsid, int gbatchid, int statusSend, int statusRefund,
			int statusOrder, int statusPay, int statusMethod, int statusComment, String keyword) {
		int count = oinfoDao.getCount(orderid, customerid, goodsid, gbatchid, statusPay, statusMethod, statusOrder,
				statusSend, statusRefund, statusComment, keyword);
		this.Msg = msg.oinfo_success;
		return count;
	}
	
	/**
	 * @param orderid
	 * @param customerid
	 * @param goodsid
	 * @param gbatchid
	 * @param statusSend
	 * @param statusRefund
	 * @param statusOrder
	 * @param statusPay
	 * @param statusMethod
	 * @param statusComment
	 * @param keyword
	 * @param start
	 * @param length
	 * @return
	 */
	public List Find(int orderid, int customerid, int goodsid, int gbatchid, int statusSend, int statusRefund,
			int statusOrder, int statusPay, int statusMethod, int statusComment, String keyword, int start, int length) {
		List list = oinfoDao.findAll(orderid, customerid, goodsid, gbatchid, statusPay, statusMethod, statusOrder,
				statusSend, statusRefund, statusComment, keyword, start, length);
		this.Msg = msg.oinfo_success;
		return list;
	}
	
	public OInfo Update(OInfo oinfo) throws Exception {
		OInfo db_oinfo = oinfoDao.findById(oinfo.getOinfoId());
		if (db_oinfo == null) {
			this.Msg = msg.oinfo_oinfonull;
			return null;
		}
		OInfo result = oinfoDao.merge(oinfo);
		this.Msg = msg.oinfo_success;
		return result;
	}
	
	public String getMsg() {
		return Msg;
	}
	
	public void setMsg(String msg) {
		Msg = msg;
	}
	
	public OInfoDAO getOInfoDao() {
		return oinfoDao;
	}
	
	public void setOInfoDao(OInfoDAO oinfoDao) {
		this.oinfoDao = oinfoDao;
	}
	
	public OInfoDAO getOinfoDao() {
		return oinfoDao;
	}
	
	public void setOinfoDao(OInfoDAO oinfoDao) {
		this.oinfoDao = oinfoDao;
	}
	
}
