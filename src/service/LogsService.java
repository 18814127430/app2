package service;

import java.util.List;

import utils.msg;
import bean.Logs;
import dao.LogsDAO;

public class LogsService {
	private String Msg;// 保存业务逻辑错误信息字段
	private LogsDAO logsDao;

	public Logs Add(Logs logs) {
		Logs db_logs = logsDao.findById(logs.getLogsId());
		if (db_logs != null) {
			this.Msg = msg.add_fail_name;
			System.out.println(this.Msg);
			return null;
		}
		Logs result = logsDao.merge(logs);
		this.Msg = msg.add_success;
		return result;
	}

	public boolean Delete(Logs logs) throws Exception {
		Logs db_logs = logsDao.findById(logs.getLogsId());
		if (db_logs == null) {
			this.Msg = msg.delete_fail_id;
			System.out.println(this.Msg);
			return false;
		}
		logsDao.delete(logs);
		return true;
	}

	public Logs View(int logsID) throws Exception {
		Logs db_logs = logsDao.findById(logsID);
		if (db_logs == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		return db_logs;
	}

	public int GetCount(String keyword) throws Exception {
		return logsDao.getCount(keyword);
	}

	public List<?> Find(String keyword, int start, int length) throws Exception {
		List<?> list = logsDao.findAll(keyword, start, length);
		System.out.println("5859294:" + list.size());
		return list;
	}

	public Logs Update(Logs logs) throws Exception {
		Logs db_logs = logsDao.findById(logs.getLogsId());
		if (db_logs == null) {
			this.Msg = msg.update_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		Logs result = logsDao.merge(logs);
		return result;
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
