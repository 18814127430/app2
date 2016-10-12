package service;

import java.util.List;

import utils.msg;
import bean.Cart;
import bean.Comment;
import dao.CommentDAO;

public class CommentService {
	private String Msg;// 保存业务逻辑错误信息字段
	private CommentDAO commentDao;
	
	public Comment Add(Comment comment) {
		Comment db_comment = commentDao.merge(comment);
		if (db_comment == null) {
			this.Msg = msg.comment_fail;
			return null;
		}
		this.Msg = msg.comment_success;
		return db_comment;
	}
	
	public boolean Delete(Comment comment) throws Exception {
		Comment db_comment = commentDao.findById(comment.getCommentId());
		if (db_comment == null) {
			this.Msg = msg.comment_commentnull;
			return false;
		}
		commentDao.delete(db_comment);
		this.Msg = msg.comment_success;
		return true;
	}
	
	public Comment View(int commentid) throws Exception {
		Comment db_comment = commentDao.findById(commentid);
		if (db_comment == null) {
			this.Msg = msg.comment_commentnull;
			return null;
		}
		this.Msg = msg.comment_success;
		return db_comment;
	}
	
	/**
	 * @param customerid
	 * @param oinfoid
	 * @param goodsid
	 * @param batchid
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public int Count(int customerid, int oinfoid, int goodsid, int batchid, String keyword) throws Exception {
		int count = commentDao.getCount(customerid, goodsid, batchid, oinfoid, keyword);
		this.Msg = msg.comment_success;
		return count;
	}
	
	/**
	 * @param customerid
	 * @param oinfoid
	 * @param goodsid
	 * @param batchid
	 * @param keyword
	 * @param start
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public List Find(int customerid, int oinfoid, int goodsid, int batchid, String keyword, int start, int length)
			throws Exception {
		List list = commentDao.findAll(customerid, goodsid, batchid, oinfoid, keyword, start, length);
		if (list.size() == 0) {
			this.Msg = msg.comment_commentnull;
			return null;
		}
		this.Msg = msg.comment_success;
		return list;
	}
	
	public Comment Find_OInfoId(int oifoid) throws Exception {
		List list = commentDao.findAll(0, 0, 0, oifoid, "", 0, msg.RECORD_SIZE);
		if (list.size() == 0) {
			this.Msg = msg.comment_commentnull;
			return null;
		}
		Comment db_comment = (Comment) list.get(0);
		this.Msg = msg.comment_success;
		return db_comment;
	}
	
	/**
	 * @param customerid
	 * @param oinfoid
	 * @return
	 * @throws Exception
	 */
	public Comment Find_CustomerId_OInfoId(int customerid, int oinfoid) throws Exception {
		List list = commentDao.findAll(customerid, 0, 0, oinfoid, "", 0, msg.RECORD_SIZE);
		if (list.size() == 0) {
			this.Msg = msg.comment_commentnull;
			return null;
		}
		System.out.println("list.size1:" + list.size());
		while (list.size() > 1) {
			System.out.println("----Find_CustomerId_OInfoId-----list.size1:" + list.size());
			commentDao.delete((Comment) list.get(1));
			list = commentDao.findAll(customerid, 0, 0, oinfoid, "", 0, msg.RECORD_SIZE);
		}
		Comment db_comment = (Comment) list.get(0);
		this.Msg = msg.comment_success;
		return db_comment;
	}
	
	public int Count_Keyword(String keyword) throws Exception {
		int count = commentDao.getCount(0, 0, 0, 0, keyword);
		this.Msg = msg.comment_success;
		return count;
	}
	
	public List Find_Keyword(String keyword, int start, int length) throws Exception {
		List list = commentDao.findAll(0, 0, 0, 0, keyword, start, length);
		this.Msg = msg.comment_success;
		return list;
	}
	
	public int Count_CustomerId(int customerid, String keyword) throws Exception {
		int count = commentDao.getCount(customerid, 0, 0, 0, keyword);
		this.Msg = msg.comment_success;
		return count;
	}
	
	public List Find_CustomerId(int customerid, String keyword, int fromindex, int length) throws Exception {
		List list = commentDao.findAll(customerid, 0, 0, 0, keyword, fromindex, length);
		this.Msg = msg.comment_success;
		return list;
	}
	
	public int Count_GoodsId(int goodsid, String keyword) throws Exception {
		int count = commentDao.getCount(0, goodsid, 0, 0, keyword);
		this.Msg = msg.comment_success;
		return count;
	}
	
	public List Find_GoodsId(int goodsid, String keyword, int fromindex, int length) throws Exception {
		List list = commentDao.findAll(0, goodsid, 0, 0, keyword, fromindex, length);
		this.Msg = msg.comment_success;
		return list;
	}
	
	public Comment Update(Comment comment) throws Exception {
		Comment db_comment = commentDao.findById(comment.getCommentId());
		if (db_comment == null) {
			this.Msg = msg.comment_commentnull;
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
