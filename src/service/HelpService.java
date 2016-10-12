package service;

import java.util.List;

import utils.msg;
import bean.Help;
import dao.HelpDAO;

public class HelpService {
	private String Msg;// 保存业务逻辑错误信息字段
	private HelpDAO helpDao;

	public Help Add(Help help) {
		List<?> list = helpDao.findByHelpTitle(help.getHelpTitle());
		if (list.size() > 0) {
			this.Msg = msg.add_fail_name;
			System.out.println(this.Msg);
			return null;
		}
		Help result = helpDao.merge(help);
		this.Msg = " 操作成功 ";
		return result;
	}

	public boolean Delete(Help help) throws Exception {
		Help db_help = helpDao.findById(help.getHelpId());
		if (db_help == null) {
			this.Msg = msg.delete_fail_id;
			System.out.println(this.Msg);
			return false;
		}
		helpDao.delete(help);
		return true;
	}

	public Help View(int helpID) throws Exception {
		Help db_help = helpDao.findById(helpID);
		if (db_help == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		return db_help;
	}

	public int GetCount(String keyword) throws Exception {
		return helpDao.getCount(keyword);
	}

	public List Find(String keyword, int start, int length) throws Exception {
		List list = helpDao.findAll(keyword, start, length);
		System.out.println("5859695959595294:" + list.size());
		return list;
	}

	public Help Update(Help help) throws Exception {
		Help db_help = helpDao.findById(help.getHelpId());
		if (db_help == null) {
			this.Msg = msg.update_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		Help result = helpDao.merge(help);
		return result;
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
