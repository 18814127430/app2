package service;

import java.util.List;

import utils.msg;
import bean.Logs;
import dao.LogsDAO;

public class LogsService {
	private String Msg;// 保存业务逻辑错误信息字段
	private LogsDAO logsDao;

	public Logs Add(Logs logs) {
		Logs db_logs = logsDao.merge(logs);
		if (db_logs == null) {
			this.Msg = msg.logs_logsnull;
			return null;
		}
		this.Msg = msg.logs_success;
		return db_logs;
	}

	public boolean Delete(Logs logs) throws Exception {
		Logs db_logs = logsDao.findById(logs.getLogsId());
		if (db_logs == null) {
			this.Msg = msg.logs_logsnull;
			return false;
		}
		logsDao.delete(logs);
		this.Msg = msg.logs_success;
		return true;
	}

	public Logs View(int logsID) throws Exception {
		Logs db_logs = logsDao.findById(logsID);
		if (db_logs == null) {
			this.Msg = msg.logs_logsnull;
			return null;
		}
		this.Msg = msg.logs_success;
		return db_logs;
	}

	public int Count_Keyword(String keyword) throws Exception {
		int count = logsDao.getCount(keyword);
		this.Msg = msg.logs_success;
		return count;
	}

	public List Find_Keyword(String keyword, int start, int length) throws Exception {
		List list = logsDao.findAll(keyword, start, length);
		this.Msg = msg.logs_success;
		return list;
	}

	public Logs Update(Logs logs) throws Exception {
		Logs db_logs = logsDao.findById(logs.getLogsId());
		if (db_logs == null) {
			this.Msg = msg.logs_logsnull;
			return null;
		}
		db_logs = logsDao.merge(logs);
		this.Msg = msg.logs_success;
		return db_logs;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public LogsDAO getLogsDao() {
		return logsDao;
	}

	public void setLogsDao(LogsDAO logsDao) {
		this.logsDao = logsDao;
	}

}
