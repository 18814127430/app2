package service;

import java.util.List;

import utils.msg;
import bean.Comment;
import dao.CommentDAO;

public class CommentService {
	private String Msg;// 保存业务逻辑错误信息字段
	private CommentDAO commentDao;

	public Comment Add(Comment comment) {
		Comment db_comment = commentDao.findById(comment.getCommentId());
		if (db_comment != null) {
			this.Msg = msg.add_fail_name;
			System.out.println(this.Msg);
			return null;
		}
		Comment result = commentDao.merge(comment);
		this.Msg = msg.add_success;
		return result;
	}

	public boolean Delete(Comment comment) throws Exception {
		Comment db_comment = commentDao.findById(comment.getCommentId());
		if (db_comment == null) {
			this.Msg = msg.delete_fail_id;
			System.out.println(this.Msg);
			return false;
		}
		commentDao.delete(comment);
		return true;
	}

	public Comment View(int commentid) throws Exception {
		Comment db_comment = commentDao.findById(commentid);
		if (db_comment == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		return db_comment;
	}

	public int GetCount(String keyword) throws Exception {
		return commentDao.getCount(0, 0, 0, 0, keyword);
	}

	public List Find(String keyword, int start, int length) throws Exception {
		List list = commentDao.findAll(0, 0, 0, 0, keyword, start, length);
		System.out.println("----------->:" + list.size());
		return list;
	}

	public int GetCountByOInfoId(int oifoid) throws Exception {
		System.out.println("++++++++++++++++++++++++++++++++++++");
		return commentDao.getCount(0, 0, oifoid, 0, "");
	}

	public List FindByOInfoId(int oifoid, int length) throws Exception {
		List list = commentDao.findAll(0, 0, oifoid, 0, "", 0, length);
		return list;
	}

	public int GetCountByCustomerId(int customerid, String keyword) throws Exception {
		System.out.println("++++++++++++++++++++++++++++++++++++");
		return commentDao.getCount(customerid, 0, 0, 0, keyword);
	}

	public List FindByCustomerId(int customerid, String keyword, int fromindex, int length) throws Exception {
		List list = commentDao.findAll(customerid, 0, 0, 0, keyword, fromindex, length);
		return list;
	}

	public int GetCountByGoodsId(int goodsid, String keyword) throws Exception {
		System.out.println("++++++++++++++++++++++++++++++++++++");
		return commentDao.getCount(0, goodsid, 0, 0, keyword);
	}

	public List FindByGoodsId(int goodsid, String keyword, int fromindex, int length) throws Exception {
		List list = commentDao.findAll(0, goodsid, 0, 0, keyword, fromindex, length);
		return list;
	}

	public int GetCountByOrderId(int orderid, String keyword) throws Exception {
		System.out.println("++++++++++++++++++++++++++++++++++++");
		return commentDao.getCount(0, 0, 0, orderid, keyword);
	}

	public List FindByOrderId(int orderid, String keyword, int fromindex, int length) throws Exception {
		List list = commentDao.findAll(0, 0, 0, orderid, keyword, fromindex, length);
		return list;
	}

	public Comment Update(Comment comment) throws Exception {
		Comment db_comment = commentDao.findById(comment.getCommentId());
		if (db_comment == null) {
			this.Msg = msg.update_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		Comment result = commentDao.merge(comment);
		return result;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public CommentDAO getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}

}
