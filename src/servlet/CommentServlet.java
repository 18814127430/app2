package servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import service.CommentService;
import service.CustomerService;
import service.OInfoService;
import utils.msg;
import utils.test;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import androidbeans.a_Comment;
import bean.Comment;
import bean.Customer;
import bean.OInfo;

public class CommentServlet extends ActionSupport {
	
	private CommentService commentService;
	private CustomerService customerService;
	private OInfoService oinfoService;
	
	public a_Comment comment2a(Comment db_comment) {
		a_Comment a_comment = new a_Comment();
		
		a_comment.setCustomerPhone(db_comment.getOInfo().getOOrder().getCustomer().getCustomerPhone());
		
		a_comment.setCommentContent(db_comment.getCommentContent());
		a_comment.setCommentId(db_comment.getCommentId());
		a_comment.setCommentStar(db_comment.getCommentStar());
		a_comment.setCommentCount(db_comment.getCommentCount());
		a_comment.setCustomerId(db_comment.getOInfo().getOOrder().getCustomer().getCustomerId());
		a_comment.setOrderDate(db_comment.getOInfo().getOOrder().getOrderDate());
		a_comment.setOrderId(db_comment.getOInfo().getOinfoId());
		a_comment.setOrderPrice(db_comment.getOInfo().getOOrder().getMoneyTotal());
		a_comment.setOrderSerial(db_comment.getOInfo().getOOrder().getPaySerial());
		
		return a_comment;
	}
	
	public Comment a2comment(a_Comment a_comment, OInfo db_oinfo) {
		
		Comment db_comment = new Comment();
		
		db_comment.setCommentId(a_comment.getCommentId());
		db_comment.setCommentContent(a_comment.getCommentContent());
		db_comment.setCommentDate(test.String2Timestamp(test.GetCurrentTime()));
		db_comment.setCommentCount(a_comment.getCommentCount());
		db_comment.setCommentStar(a_comment.getCommentStar());
		db_comment.setOInfo(db_oinfo);
		
		return db_comment;
		
	}
	
	public void doAdd() throws Exception {
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		String status = "";
		String message = "";
		String data = "";
		int page = 0;
		String mapdata = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String s1 = request.getParameter("jn");
		String s2 = request.getParameter("tk");
		
		String commentdata;
		if (s1 == null || s1.equals("")) {
			commentdata = "";
		}
		else {
			commentdata = s1;
		}
		
		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		}
		else {
			token = s2;
		}
		
		System.out.println("string1:" + s1 + "------------commentdata:" + commentdata);
		System.out.println("string2:" + s2 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.comment_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		a_Comment a_comment = gson.fromJson(commentdata, a_Comment.class);
		if (a_comment == null) {
			message = msg.comment_jsonnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (a_comment.getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.comment_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		OInfo db_oinfo = oinfoService.View_Id(a_comment.getOrderId());
		if (db_oinfo == null) {
			message = msg.oinfo_oinfonull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Comment comment = a2comment(a_comment, db_oinfo);
		
		System.out.println("------->token:" + token);
		System.out.println("------->commentdata:" + commentdata);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + a_comment);
		
		Comment db_comment = commentService.Find_CustomerId_OInfoId(db_customer.getCustomerId(), a_comment.getOrderId());
		if (db_comment == null) {
			comment.setCommentCount(1);
			comment.setCommentDate(test.String2Timestamp(test.GetCurrentTime()));
			comment = commentService.Add(comment);
		}
		else {
			comment.setCommentId(db_comment.getCommentId());
			comment.setCommentDate(test.String2Timestamp(test.GetCurrentTime()));
			comment.setCommentCount(db_comment.getCommentCount() + 1);
			comment = commentService.Update(comment);
		}
		System.out.println("------->comment:" + comment);
		
		if (comment == null) {
			message = msg.comment_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
		}
		
		a_comment = comment2a(comment);
		data = gson.toJson(a_comment);
		
		message = msg.comment_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		map.put("data", data);
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	public void doUpdate() throws Exception {
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		String status = "";
		String message = "";
		String data = "";
		int page = 0;
		String mapdata = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String s1 = request.getParameter("jn");
		String s2 = request.getParameter("tk");
		
		String commentdata;
		if (s1 == null || s1.equals("")) {
			commentdata = "";
		}
		else {
			commentdata = s1;
		}
		
		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		}
		else {
			token = s2;
		}
		
		System.out.println("string1:" + s1 + "------------commentdata:" + commentdata);
		System.out.println("string2:" + s2 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.comment_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		a_Comment a_comment = gson.fromJson(commentdata, a_Comment.class);
		System.out.println("------->token:" + token);
		System.out.println("------->commentdata:" + commentdata);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + a_comment);
		if (a_comment == null) {
			message = msg.comment_jsonnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (a_comment.getCustomerId().intValue() != db_customer.getCustomerId().intValue()) {
			message = msg.comment_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		OInfo db_oinfo = oinfoService.View_Id(a_comment.getOrderId());
		if (db_oinfo == null) {
			message = msg.oinfo_oinfonull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Comment comment = commentService.View(a_comment.getCommentId());
		Comment db_comment = a2comment(a_comment, db_oinfo);
		
		db_comment.setCommentCount(comment.getCommentCount() + 1);
		db_comment.setCommentDate(test.String2Timestamp(test.GetCurrentTime()));
		
		db_comment = commentService.Update(db_comment);
		System.out.println("------->db_comment:" + db_comment);
		
		if (db_comment == null) {
			message = msg.comment_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
		}
		
		a_comment = comment2a(db_comment);
		data = gson.toJson(a_comment);
		
		message = msg.comment_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		map.put("data", data);
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	public void doDelete() throws Exception {
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		String status = "";
		String message = "";
		String data = "";
		int page = 0;
		String mapdata = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String s1 = request.getParameter("id");
		String s2 = request.getParameter("tk");
		
		int commentid;
		if (s1 == null || s1.equals("") || !test.isInteger(s1)) {
			message = msg.cart_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		else {
			commentid = Integer.parseInt(s1);
		}
		
		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		}
		else {
			token = s2;
		}
		
		System.out.println("string1:" + s1 + "------------commentid:" + commentid);
		System.out.println("string2:" + s2 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.comment_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Comment db_comment = commentService.View(commentid);
		System.out.println("------->token:" + token);
		System.out.println("------->commentid:" + commentid);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + db_comment);
		
		if (db_comment == null) {
			message = msg.comment_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (db_comment.getOInfo().getOOrder().getCustomer().getCustomerId().intValue() != db_customer.getCustomerId()
				.intValue()) {
			message = msg.comment_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (!commentService.Delete(db_comment)) {
			message = msg.comment_fail;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.comment_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	public void doView() throws Exception {
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		String status = "";
		String message = "";
		String data = "";
		int page = 0;
		String mapdata = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String s1 = request.getParameter("id");
		String s2 = request.getParameter("tk");
		
		int commentid;
		if (s1 == null || s1.equals("") || !test.isInteger(s1)) {
			message = msg.cart_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		else {
			commentid = Integer.parseInt(s1);
		}
		
		String token;
		if (s2 == null || s2.equals("")) {
			token = "";
		}
		else {
			token = s2;
		}
		
		System.out.println("string1:" + s1 + "------------commentid:" + commentid);
		System.out.println("string2:" + s2 + "------------token:" + token);
		
		Customer db_customer = customerService.FindByToken(token);
		if (db_customer == null) {
			message = msg.comment_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Comment db_comment = commentService.View(commentid);
		System.out.println("------->token:" + token);
		System.out.println("------->commentid:" + commentid);
		System.out.println("------->db_customer:" + db_customer);
		System.out.println("------->object:" + db_comment);
		if (db_comment == null) {
			message = msg.comment_commentnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		if (db_comment.getOInfo().getOOrder().getCustomer().getCustomerId().intValue() != db_customer.getCustomerId()
				.intValue()) {
			message = msg.comment_customerwrong;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.comment_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		
		a_Comment a_comment = comment2a(db_comment);
		
		data = gson.toJson(a_comment);
		map.put("data", data);
		mapdata = gson.toJson(map);
		
		test.a(data);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	public void doFind() throws Exception {
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		String status = "";
		String message = "";
		String data = "";
		int page = 0;
		String mapdata = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		
		String s1 = request.getParameter("tk");
		String s2 = request.getParameter("page");
		String s3 = request.getParameter("kw");
		String s4 = request.getParameter("id");
		
		String token;
		if (s1 == null || s1.equals("")) {
			token = "";
		}
		else {
			token = s1;
		}
		
		int currentPage;
		if (s2 == null || s2.equals("") || !test.isInteger(s2)) {
			currentPage = 1;
		}
		else {
			currentPage = Integer.parseInt(s2);
		}
		
		String keyword;
		if (s3 == null || s3.equals("")) {
			keyword = "";
		}
		else {
			keyword = s3;
		}
		
		int goodsid;
		if (s4 == null || s4.equals("") || !test.isInteger(s4)) {
			goodsid = 0;
		}
		else {
			goodsid = Integer.parseInt(s4);
		}
		
		Customer db_customer = customerService.FindByToken(token);
		
		int totalRecord = 0;
		//查看商品的所有评论
		if (goodsid > 0) {
			totalRecord = commentService.Count_GoodsId(goodsid, keyword);
		}
		//查看自己的所有评论
		else {
			if (db_customer == null) {
				message = msg.comment_customerwrong;
				status = msg.status_1;
				map.put("status", status);
				map.put("message", message);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			totalRecord = commentService.Count_CustomerId(db_customer.getCustomerId(), keyword);
		}
		
		int totalPage = totalRecord / (3 * msg.RECORD_SIZE) + 1;
		if ((totalRecord % (3 * msg.RECORD_SIZE) == 0) && (totalRecord > (3 * msg.RECORD_SIZE))) {
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
		
		int fromIndex = (currentPage - 1) * (3 * msg.RECORD_SIZE); // 选择从第几条开始
		
		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);
		
		List list = new ArrayList();
		if (goodsid > 0) {
			list = commentService.Find_GoodsId(goodsid, keyword, fromIndex, (3 * msg.RECORD_SIZE));
		}
		else {
			list = commentService.Find_CustomerId(db_customer.getCustomerId(), keyword, fromIndex, (3 * msg.RECORD_SIZE));
		}
		
		List list1 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Comment db_comment = (Comment) list.get(i);
			
			a_Comment a_comment = comment2a(db_comment);
			
			list1.add(a_comment);
		}
		
		if (list.size() == 0) {
			message = msg.comment_commentnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.comment_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		data = gson.toJson(list1);
		map.put("data", data);
		map.put("page", totalPage);
		mapdata = gson.toJson(map);
		
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	public CommentService getCommentService() {
		return commentService;
	}
	
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	public CustomerService getCustomerService() {
		return customerService;
	}
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public OInfoService getOinfoService() {
		return oinfoService;
	}
	
	public void setOinfoService(OInfoService oinfoService) {
		this.oinfoService = oinfoService;
	}
}
