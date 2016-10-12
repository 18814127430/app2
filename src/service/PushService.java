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
		List<?> list = pushDao.findByPushTitle(push.getPushTitle());
		if (list.size() > 0) {
			this.Msg = msg.add_fail_name;
			System.out.println(this.Msg);
			return null;
		}
		Push result = pushDao.merge(push);
		return result;
	}

	public boolean Delete(Push push) throws Exception {
		Push db_push = pushDao.findById(push.getPushId());
		if (db_push == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return false;
		}
		pushDao.delete(push);
		return true;
	}

	public Push View(int pushID) throws Exception {
		Push db_push = pushDao.findById(pushID);
		if (db_push == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		return db_push;
	}

	public int GetCount(String keyword) throws Exception {
		return pushDao.getCount(keyword);
	}

	public List<?> Find(String keyword, int start, int length) throws Exception {
		List<?> list = pushDao.findAll(keyword, start, length);
		System.out.println("5859695959595294:" + list.size());
		return list;
	}

	public Push Update(Push push) throws Exception {
		Push db_push = pushDao.findById(push.getPushId());
		if (db_push == null) {
			this.Msg = msg.update_fail_id;
			System.out.println(this.Msg);
			return null;
		}

		Push result = pushDao.merge(push);
		this.Msg = msg.update_success;
		return result;
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
