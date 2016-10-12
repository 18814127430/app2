package service;

import java.util.List;

import utils.msg;
import bean.OInfo;
import bean.Order;
import dao.OInfoDAO;

public class OInfoService {
	private String Msg;// 保存业务逻辑错误信息字段
	private OInfoDAO oinfoDao;

	public OInfo Add(OInfo oinfo) {
		OInfo db_oinfo = oinfoDao.findById(oinfo.getOcId());
		if (db_oinfo != null) {
			this.Msg = msg.add_fail_name;
			System.out.println(this.Msg);
			return null;
		}
		OInfo result = oinfoDao.merge(oinfo);
		this.Msg = msg.add_success;
		return result;
	}

	public boolean Delete(OInfo oinfo) throws Exception {
		OInfo db_oinfo = oinfoDao.findById(oinfo.getOcId());
		if (db_oinfo == null) {
			this.Msg = msg.delete_fail_id;
			System.out.println(this.Msg);
			return false;
		}
		oinfoDao.delete(oinfo);
		return true;
	}

	public OInfo View(int oinfoID) throws Exception {
		OInfo db_oinfo = oinfoDao.findById(oinfoID);
		if (db_oinfo == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		return db_oinfo;
	}

	public int GetCountByOrderId(int orderid, String keyword) throws Exception {
		return oinfoDao.getCount(orderid, 0, 0, keyword);
	}

	public List FindByOrderId(int orderid) throws Exception {
		int length = oinfoDao.getCount(orderid, 0, 0, "");
		List list = oinfoDao.findAll(orderid, 0, 0, "", 0, length);
		return list;
	}

	public List FindByOrderId(int orderid, String keyword, int fromIndex, int length) throws Exception {
		List list = oinfoDao.findAll(orderid, 0, 0, keyword, fromIndex, length);
		return list;
	}

	public int GetCountByGoodsId(int goodsid, String keyword) throws Exception {
		return oinfoDao.getCount(0, goodsid, 0, keyword);
	}

	public List FindByGoodsId(int goodsid, String keyword, int fromIndex, int length) throws Exception {
		List list = oinfoDao.findAll(0, goodsid, 0, keyword, fromIndex, length);
		return list;
	}

	public int GetCountByGBatchId(int gbatchid, String keyword) throws Exception {
		return oinfoDao.getCount(0, 0, gbatchid, keyword);
	}

	public List FindByGBatchId(int gbatchid, String keyword, int fromIndex, int length) throws Exception {
		List list = oinfoDao.findAll(0, 0, gbatchid, keyword, fromIndex, length);
		return list;
	}

	public int GetCount(String keyword) throws Exception {
		return oinfoDao.getCount(0, 0, 0, keyword);
	}

	public List Find(String keyword, int fromIndex, int length) throws Exception {
		List list = oinfoDao.findAll(0, 0, 0, keyword, fromIndex, length);
		return list;
	}

	public OInfo Update(OInfo oinfo) throws Exception {
		OInfo db_oinfo = oinfoDao.findById(oinfo.getOcId());
		if (db_oinfo == null) {
			this.Msg = msg.update_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		OInfo result = oinfoDao.merge(oinfo);
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
