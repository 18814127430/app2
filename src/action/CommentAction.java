package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import service.CommentService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Comment;

public class CommentAction extends ActionSupport {

	private CommentService commentService;// 业务层对象
	private Comment comment;// 待操作的对象
	private String keyword;// 界面层需要查询的属性：关键字
	private String commentStartDate;
	private int firstPage;// 显示的第一页
	private int lastPage;// 显示的最后一页
	private int currentPage;// 显示的当前页
	private int totalPage;// 总页数
	private int totalRecord;// 总记录数
	private final int RECORD_SIZE = 10;// 每页记录数
	private final int PAGE_SIZE = 10;// 每组的页数
	private int customerid;
	private int goodsid;
	private int oinfoid;

	public String doAdd() throws Exception {
		System.out.println("doAdd要添加的信息:" + comment);
		Comment db_comment = commentService.Add(comment);
		System.out.println("doAdd添加后的信息:" + db_comment);
		this.comment = db_comment;
		if (db_comment != null) {
			ActionContext.getContext().put("comment", comment);
			return "commentinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", commentService.getMsg());
			return "systemerror_view";
		}
	}

	public String doDelete() throws Exception {
		Comment db_comment = commentService.View(comment.getCommentId());
		System.out.println("daDelete要删除的信息:" + db_comment);
		if (commentService.Delete(db_comment)) {
			return (doFind());
		} else {
			ActionContext.getContext().put("errorMsg", commentService.getMsg());
			return "systemerror_view";
		}
	}

	public String doView() throws Exception {
		Comment db_comment = commentService.View(comment.getCommentId());
		this.comment = db_comment;
		if (db_comment != null) {
			ActionContext.getContext().put("comment", comment);
			return "commentinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", commentService.getMsg());
			return "systemerror_view";
		}
	}

	public Comment doFindByOInfoId() throws Exception {

		List<?> list = commentService.FindByOInfoId(oinfoid, 1);
		Comment db_comment = (Comment) list.get(0);
		return db_comment;
	}

	public String doFindByCustomerId() throws Exception {
		if (totalRecord == 0)
			totalRecord = 1;
		if (totalPage == 0)
			totalPage = 1;
		if (firstPage == 0)
			firstPage = 1;
		if (currentPage == 0)
			currentPage = 1;
		if (lastPage == 0)
			lastPage = 1;
		if (keyword == null)
			keyword = "";

		System.out.println("5959000000000000004646:" + customerid);
		totalRecord = commentService.GetCountByCustomerId(customerid, keyword);
		System.out.println("59594646" + totalRecord);

		totalPage = totalRecord / this.RECORD_SIZE + 1;
		if ((totalRecord % this.RECORD_SIZE == 0) && (totalRecord > this.RECORD_SIZE)) {
			totalPage--;
		}
		if (totalPage < PAGE_SIZE) {
			firstPage = 1;
			lastPage = totalPage;
		} else {
			firstPage = (currentPage / PAGE_SIZE) * PAGE_SIZE + 1;
			lastPage = firstPage + PAGE_SIZE - 1;
			if (lastPage > totalPage) {
				lastPage = totalPage;
			}
		}
		if (currentPage > totalPage) {
			System.out.println("currentPage>totalPage");
		} else {
			System.out.println("当前页码：" + currentPage + "页码列表：");
			for (int i = firstPage; i <= lastPage; i++) {
				System.out.print(i);
			}
		}
		int fromIndex = (currentPage - 1) * this.RECORD_SIZE; // 选择从第几条开始
		int toIndex = Math.min(fromIndex + this.RECORD_SIZE, totalRecord);// 调用Math.min函数取目的数

		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：toIndex" + toIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);

		List<?> list = commentService.FindByCustomerId(customerid, keyword, fromIndex, toIndex - fromIndex);// 可优化
		System.out.println("5494949494949:" + list.size());

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		return "commentlistbycustomer_view";

	}

	public String doFindByGoodsId() throws Exception {
		if (totalRecord == 0)
			totalRecord = 1;
		if (totalPage == 0)
			totalPage = 1;
		if (firstPage == 0)
			firstPage = 1;
		if (currentPage == 0)
			currentPage = 1;
		if (lastPage == 0)
			lastPage = 1;
		if (keyword == null)
			keyword = "";

		totalRecord = commentService.GetCountByGoodsId(goodsid, keyword);
		System.out.println("59594646" + totalRecord);

		totalPage = totalRecord / this.RECORD_SIZE + 1;
		if ((totalRecord % this.RECORD_SIZE == 0) && (totalRecord > this.RECORD_SIZE)) {
			totalPage--;
		}
		if (totalPage < PAGE_SIZE) {
			firstPage = 1;
			lastPage = totalPage;
		} else {
			firstPage = (currentPage / PAGE_SIZE) * PAGE_SIZE + 1;
			lastPage = firstPage + PAGE_SIZE - 1;
			if (lastPage > totalPage) {
				lastPage = totalPage;
			}
		}
		if (currentPage > totalPage) {
			System.out.println("currentPage>totalPage");
		} else {
			System.out.println("当前页码：" + currentPage + "页码列表：");
			for (int i = firstPage; i <= lastPage; i++) {
				System.out.print(i);
			}
		}
		int fromIndex = (currentPage - 1) * this.RECORD_SIZE; // 选择从第几条开始
		int toIndex = Math.min(fromIndex + this.RECORD_SIZE, totalRecord);// 调用Math.min函数取目的数

		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：toIndex" + toIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);

		List<?> list = commentService.FindByGoodsId(goodsid, keyword, fromIndex, toIndex - fromIndex);// 可优化
		System.out.println("5494949494949:" + list.size());

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		return "commentlistbycustomer_view";

	}

	public String doFind() throws Exception {
		if (totalRecord == 0)
			totalRecord = 1;
		if (totalPage == 0)
			totalPage = 1;
		if (firstPage == 0)
			firstPage = 1;
		if (currentPage == 0)
			currentPage = 1;
		if (lastPage == 0)
			lastPage = 1;
		if (keyword == null)
			keyword = "";

		System.out.println(keyword);

		totalRecord = commentService.GetCount(keyword);
		totalPage = totalRecord / this.RECORD_SIZE + 1;
		if ((totalRecord % this.RECORD_SIZE == 0) && (totalRecord > this.RECORD_SIZE)) {
			totalPage--;
		}
		if (totalPage < PAGE_SIZE) {
			firstPage = 1;
			lastPage = totalPage;
		} else {
			firstPage = (currentPage / PAGE_SIZE) * PAGE_SIZE + 1;
			lastPage = firstPage + PAGE_SIZE - 1;
			if (lastPage > totalPage) {
				lastPage = totalPage;
			}
		}
		if (currentPage > totalPage) {
			System.out.println("currentPage>totalPage");
		} else {
			System.out.println("当前页码：" + currentPage + "页码列表：");
			for (int i = firstPage; i <= lastPage; i++) {
				System.out.print(i);
			}
		}
		int fromIndex = (currentPage - 1) * this.RECORD_SIZE; // 选择从第几条开始
		int toIndex = Math.min(fromIndex + this.RECORD_SIZE, totalRecord);// 调用Math.min函数取目的数

		List list = commentService.Find(keyword, fromIndex, toIndex - fromIndex);

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		ctx.put("keyword", keyword);
		return "commentlist_view";
	}

	public String doEdit() throws Exception {
		System.out.println("doEdit要修改信息的ID：" + comment.getCommentId());
		Comment db_comment = commentService.View(comment.getCommentId());
		this.comment = db_comment;
		System.out.println("doEdit要修改信息：" + comment);
		if (db_comment != null) {
			ActionContext.getContext().put("comment", comment);
			return "commentedit_view";
		} else {
			ActionContext.getContext().put("errorMsg", commentService.getMsg());
			return "systemerror_view";
		}
	}

	public String doUpdate() throws Exception {
		System.out.println("doUpdate要修改的信息:" + comment);
		Comment db_comment = commentService.Update(comment);
		this.comment = db_comment;
		if (db_comment != null) {
			ActionContext.getContext().put("comment", comment);
			System.out.println("doUpdate修改后信息:" + comment);
			return "commentinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", commentService.getMsg());
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

	public String getCommentStartDate() {
		return commentStartDate;
	}

	public void setCommentStartDate(String commentStartDate) {
		this.commentStartDate = commentStartDate;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getRECORD_SIZE() {
		return RECORD_SIZE;
	}

	public int getPAGE_SIZE() {
		return PAGE_SIZE;
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
}
