package service;

import java.util.List;

import utils.msg;
import bean.Help;
import dao.HelpDAO;

public class HelpService {
	private String Msg;// 保存业务逻辑错误信息字段
	private HelpDAO helpDao;

	public Help Add(Help help) {
		List list = helpDao.findByHelpTitle(help.getHelpTitle());
		if (list.size() > 0) {
			this.Msg = msg.help_helpexist;
			return null;
		}
		Help result = helpDao.merge(help);
		this.Msg = msg.help_success;
		return result;
	}

	public boolean Delete(Help help) throws Exception {
		Help db_help = helpDao.findById(help.getHelpId());
		if (db_help == null) {
			this.Msg = msg.help_helpnull;
			System.out.println(this.Msg);
			return false;
		}
		helpDao.delete(db_help);
		this.Msg = msg.help_success;
		return true;
	}

	public Help View(int helpID) throws Exception {
		Help db_help = helpDao.findById(helpID);
		if (db_help == null) {
			this.Msg = msg.help_helpnull;
			return null;
		}
		this.Msg = msg.help_success;
		return db_help;
	}

	public int Count_Keyword(String keyword) throws Exception {
		int count = helpDao.getCount(keyword);
		this.Msg = msg.help_success;
		return count;
	}

	public List Find_Keyword(String keyword, int start, int length) throws Exception {
		List list = helpDao.findAll(keyword, start, length);
		this.Msg = msg.help_success;
		return list;
	}

	public Help Update(Help help) throws Exception {
		Help db_help = helpDao.findById(help.getHelpId());
		if (db_help == null) {
			this.Msg = msg.help_helpnull;
			return null;
		}
		db_help = helpDao.merge(help);
		this.Msg = msg.help_success;
		return db_help;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public HelpDAO getHelpDao() {
		return helpDao;
	}

	public void setHelpDao(HelpDAO helpDao) {
		this.helpDao = helpDao;
	}

}
