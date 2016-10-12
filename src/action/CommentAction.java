package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import service.CommentService;
import utils.msg;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Comment;

public class CommentAction extends ActionSupport {
	
	private CommentService commentService;
	private Comment comment;
	private String keyword;
	private int currentPage;
	private int customerid;
	private int goodsid;
	private int oinfoid;
	private int commentid;
	private int batchid;
	
	public String doDelete() throws Exception {
		Comment db_comment = commentService.View(commentid);
		System.out.println("daDelete要删除的信息:" + db_comment);
		if (commentService.Delete(db_comment)) {
			return (doFind());
		}
		else {
			ActionContext.getContext().put("Msg", commentService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doView() throws Exception {
		Comment db_comment = commentService.View(commentid);
		if (db_comment != null) {
			ActionContext.getContext().put("comment", db_comment);
			return "commentinfo_view";
		}
		else {
			ActionContext.getContext().put("Msg", commentService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doFind() throws Exception {
		
		if (customerid <= 0)
			customerid = 0;
		if (goodsid <= 0)
			goodsid = 0;
		if (oinfoid <= 0)
			oinfoid = 0;
		if (batchid <= 0)
			batchid = 0;
		if (currentPage <= 0)
			currentPage = 1;
		if (keyword == null || keyword.equals(""))
			keyword = "";
		
		System.out.println("rec+customerid:" + customerid);
		System.out.println("rec+goodsid:" + goodsid);
		System.out.println("rec+currentPage:" + currentPage);
		System.out.println("rec+keyword:" + keyword);
		
		int totalRecord = commentService.Count(customerid, goodsid, batchid, oinfoid, keyword);
		
		int totalPage = totalRecord / msg.RECORD_SIZE + 1;
		if ((totalRecord % msg.RECORD_SIZE == 0) && (totalRecord > msg.RECORD_SIZE)) {
			totalPage--;
		}
		currentPage = Math.min(currentPage, totalPage);
		
		int firstPage = 1;
		int lastPage = 1;
		if (totalPage < msg.PAGE_SIZE) {
			firstPage = 1;
			lastPage = totalPage;
		}
		else {
			firstPage = (currentPage / msg.PAGE_SIZE) * msg.PAGE_SIZE + 1;
			lastPage = firstPage + msg.PAGE_SIZE - 1;
			if (lastPage > totalPage) {
				lastPage = totalPage;
			}
		}
		
		int fromIndex = (currentPage - 1) * msg.RECORD_SIZE; // 选择从第几条开始
		
		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);
		
		List list = commentService.Find(customerid, oinfoid, goodsid, batchid, keyword, fromIndex, msg.RECORD_SIZE);
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", msg.PAGE_SIZE);
		ctx.put("keyword", keyword);
		
		if (customerid > 0) {
			ctx.put("customerid", customerid);
			return "commentlistbycustomer_view";
		}
		else if (goodsid > 0) {
			ctx.put("goodsid", goodsid);
			return "commentlistbygoods_view";
		}
		else {
			ActionContext.getContext().put("Msg", commentService.getMsg());
			return "systemerror_view";
		}
		
	}
	
	public CommentService getCommentService() {
		return commentService;
	}
	
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	public Comment getComment() {
		return comment;
	}
	
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getCustomerid() {
		return customerid;
	}
	
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	
	public int getGoodsid() {
		return goodsid;
	}
	
	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}
	
	public int getOinfoid() {
		return oinfoid;
	}
	
	public void setOinfoid(int oinfoid) {
		this.oinfoid = oinfoid;
	}
	
	public int getCommentid() {
		return commentid;
	}
	
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	
	public int getBatchid() {
		return batchid;
	}
	
	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}
}
