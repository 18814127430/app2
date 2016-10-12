package service;

import java.util.List;

import utils.msg;
import bean.GBatch;
import dao.GBatchDAO;

public class GBatchService {
	private String Msg;// 保存业务逻辑错误信息字段
	private GBatchDAO gbatchDao;

	public GBatch Add(GBatch gbatch) {
		GBatch result = gbatchDao.merge(gbatch);
		this.Msg = msg.add_success;
		return result;
	}

	public boolean Delete(GBatch gbatch) throws Exception {
		GBatch db_gbatch = gbatchDao.findById(gbatch.getBatchId());
		if (db_gbatch == null) {
			this.Msg = msg.delete_fail_id;
			System.out.println(this.Msg);
			return false;
		}
		gbatchDao.delete(gbatch);
		return true;
	}

	public GBatch View(int gbatchID) throws Exception {
		GBatch db_gbatch = gbatchDao.findById(gbatchID);
		if (db_gbatch == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		return db_gbatch;
	}

	public int GetCount(String keyword) throws Exception {
		return gbatchDao.getCount(0, 0, 0, keyword);
	}

	public List Find(String keyword, int start, int length) throws Exception {
		List list = gbatchDao.findAll(0, 0, 0, keyword, start, length);
		System.out.println("5859294:" + list.size());
		return list;
	}

	public int GetCountByGoodsId(int goodsid, String keyword) throws Exception {
		return gbatchDao.getCount(0, 0, goodsid, keyword);
	}

	public List FindByGoodsId(int goodsid, String keyword, int start, int length) throws Exception {
		List list = gbatchDao.findAll(0, 0, goodsid, keyword, start, length);
		return list;
	}

	public int GetCountByProducerId(int producerid, String keyword) throws Exception {
		return gbatchDao.getCount(producerid, 0, 0, keyword);
	}

	public List FindByProducerId(int producerid, String keyword, int start, int length) throws Exception {

		List list = gbatchDao.findAll(producerid, 0, 0, keyword, start, length);
		return list;
	}

	public int GetCountBySellerId(int sellerid, String keyword) throws Exception {
		return gbatchDao.getCount(0, sellerid, 0, keyword);
	}

	public List FindBySellerId(int sellerid, String keyword, int start, int length) throws Exception {

		List list = gbatchDao.findAll(0, sellerid, 0, keyword, start, length);
		return list;
	}

	public GBatch Update(GBatch gbatch) throws Exception {
		GBatch db_gbatch = gbatchDao.findById(gbatch.getBatchId());
		if (db_gbatch == null) {
			this.Msg = msg.update_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		GBatch result = gbatchDao.merge(gbatch);
		return result;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public GBatchDAO getGBatchDao() {
		return gbatchDao;
	}

	public void setGBatchDao(GBatchDAO gbatchDao) {
		this.gbatchDao = gbatchDao;
	}

	public GBatchDAO getGbatchDao() {
		return gbatchDao;
	}

	public void setGbatchDao(GBatchDAO gbatchDao) {
		this.gbatchDao = gbatchDao;
	}

}
