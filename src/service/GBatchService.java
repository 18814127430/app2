package service;

import java.util.List;

import utils.msg;
import bean.GBatch;
import dao.GBatchDAO;

public class GBatchService {
	private String Msg;// 保存业务逻辑错误信息字段
	private GBatchDAO gbatchDao;

	public GBatch Add(GBatch gbatch) {
		GBatch db_gabtch = gbatchDao.merge(gbatch);
		if (db_gabtch == null) {
			this.Msg = msg.gbatch_fail;
			return null;
		}
		this.Msg = msg.gbatch_success;
		return db_gabtch;
	}

	public boolean Delete(GBatch gbatch) throws Exception {
		GBatch db_gbatch = gbatchDao.findById(gbatch.getBatchId());
		if (db_gbatch == null) {
			this.Msg = msg.gbatch_gbatchnull;
			return false;
		}
		gbatchDao.delete(db_gbatch);
		this.Msg = msg.gbatch_success;
		return true;
	}

	public GBatch View(int gbatchID) throws Exception {
		GBatch db_gbatch = gbatchDao.findById(gbatchID);
		if (db_gbatch == null) {
			this.Msg = msg.gbatch_gbatchnull;
			return null;
		}
		this.Msg = msg.gbatch_success;
		return db_gbatch;
	}

	public int Count_Keyword(String keyword) throws Exception {
		int count = gbatchDao.getCount(0, 0, 0, keyword);
		this.Msg = msg.gbatch_success;
		return count;
	}

	public List Find_Keyword(String keyword, int start, int length) throws Exception {
		List list = gbatchDao.findAll(0, 0, 0, keyword, start, length);
		this.Msg = msg.gbatch_success;
		return list;
	}

	public int Count_GoodsId(int goodsid, String keyword) throws Exception {
		int count = gbatchDao.getCount(0, 0, goodsid, keyword);
		this.Msg = msg.gbatch_success;
		return count;
	}

	public List Find_GoodsId(int goodsid, String keyword, int start, int length) throws Exception {
		List list = gbatchDao.findAll(0, 0, goodsid, keyword, start, length);
		this.Msg = msg.gbatch_success;
		return list;
	}

	public int Count_ProducerId(int producerid, String keyword) throws Exception {
		int count = gbatchDao.getCount(producerid, 0, 0, keyword);
		this.Msg = msg.gbatch_success;
		return count;
	}

	public List Find_ProducerId(int producerid, String keyword, int start, int length) throws Exception {

		List list = gbatchDao.findAll(producerid, 0, 0, keyword, start, length);
		this.Msg = msg.gbatch_success;
		return list;
	}

	public int Count_SellerId(int sellerid, String keyword) throws Exception {
		int count = gbatchDao.getCount(0, sellerid, 0, keyword);
		this.Msg = msg.gbatch_success;
		return count;
	}

	public List Find_SellerId(int sellerid, String keyword, int start, int length) throws Exception {

		List list = gbatchDao.findAll(0, sellerid, 0, keyword, start, length);
		this.Msg = msg.gbatch_success;
		return list;
	}

	/**
	 * @param num
	 * @param goodsid
	 * @return
	 * @throws Exception
	 */
	public GBatch findByQuatifyDateKeep(int num, int goodsid) throws Exception {
		GBatch db_gbatch = gbatchDao.findByQuatifyDateKeep(num, goodsid);
		if (db_gbatch == null) {
			this.Msg = msg.gbatch_gbatchnull;
			return null;
		}
		this.Msg = msg.gbatch_success;
		return db_gbatch;
	}

	public GBatch Update(GBatch gbatch) throws Exception {
		GBatch db_gbatch = gbatchDao.findById(gbatch.getBatchId());
		if (db_gbatch == null) {
			this.Msg = msg.gbatch_gbatchnull;
			return null;
		}
		db_gbatch = gbatchDao.merge(gbatch);
		this.Msg = msg.gbatch_success;
		return db_gbatch;
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
