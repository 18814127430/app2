package service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import utils.msg;
import bean.Push;
import dao.PushDAO;

public class PushService {
	private String Msg;// 保存业务逻辑错误信息字段
	private PushDAO pushDao;

	public Push Add(Push push) {
		List list = pushDao.findByPushTitle(push.getPushTitle());
		if (list.size() > 0) {
			this.Msg = msg.push_pushexist;
			return null;
		}
		Push db_push = pushDao.merge(push);
		if (db_push == null) {
			this.Msg = msg.push_pushnull;
			return null;
		}
		this.Msg = msg.push_success;
		return db_push;
	}

	public boolean Delete(Push push) throws Exception {
		Push db_push = pushDao.findById(push.getPushId());
		if (db_push == null) {
			this.Msg = msg.push_pushnull;
			return false;
		}
		pushDao.delete(db_push);
		this.Msg = msg.push_success;
		return true;
	}

	public Push View(int pushID) throws Exception {
		Push db_push = pushDao.findById(pushID);
		if (db_push == null) {
			this.Msg = msg.push_pushnull;
			return null;
		}
		this.Msg = msg.push_success;
		return db_push;
	}

	public int Count_Keyword(String keyword) throws Exception {
		int count = pushDao.getCount(keyword);
		this.Msg = msg.push_success;
		return count;
	}

	public List Find_Keyword(String keyword, int start, int length) throws Exception {
		List list = pushDao.findAll(keyword, start, length);
		this.Msg = msg.push_success;
		return list;
	}

	public Push Update(Push push) throws Exception {
		Push db_push = pushDao.findById(push.getPushId());
		if (db_push == null) {
			this.Msg = msg.push_pushnull;
			return null;
		}
		db_push = pushDao.merge(push);
		this.Msg = msg.push_success;
		return db_push;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public PushDAO getPushDao() {
		return pushDao;
	}

	public void setPushDao(PushDAO pushDao) {
		this.pushDao = pushDao;
	}

}
