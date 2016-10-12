package servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import service.CompanyService;
import service.GoodsService;
import service.SortService;
import utils.msg;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import androidbeans.a_Goods_1;
import androidbeans.a_Goods_2;
import androidbeans.a_Goods_3;
import bean.Goods;
import utils.test;
import task.Task_SetHomePage;

public class GoodsServlet extends ActionSupport {
	
	private final Log logger = LogFactory.getLog(getClass());
	private GoodsService goodsService;
	private CompanyService companyService;
	private SortService sortService;
	
	public a_Goods_3 goods2a3(Goods db_goods) {
		
		a_Goods_3 a_goods_3 = new a_Goods_3();
		a_goods_3.setGoodsAddDate("");
		a_goods_3.setGoodsCheckDate(db_goods.getCheckDate());
		a_goods_3.setGoodsCheckDepartment(db_goods.getCheckDepartment());
		a_goods_3.setGoodsCheckResult(db_goods.getCheckResult());
		a_goods_3.setGoodsCheckSerial(db_goods.getCheckSerial());
		a_goods_3.setGoodsHtmlUrl(db_goods.getGoodsHtmlUrl());
		a_goods_3.setGoodsId(db_goods.getGoodsId());
		a_goods_3.setGoodsMoneyDeliver(db_goods.getMoneyDeliver());
		a_goods_3.setGoodsMoneyRetail(db_goods.getMoneyNew());
		a_goods_3.setGoodsName(db_goods.getGoodsName());
		a_goods_3.setGoodsNumStock(db_goods.getNumStock());
		a_goods_3.setGoodsRemark(db_goods.getGoodsTags());
		a_goods_3.setGoodsSize1(db_goods.getGoodsSize1());
		a_goods_3.setGoodsSize2(db_goods.getGoodsSize2());
		a_goods_3.setGoodsUnits(db_goods.getGoodsUnits());
		a_goods_3.setGoodsWeight(db_goods.getGoodsWeight());
		
		return a_goods_3;
	}
	
	public a_Goods_2 goods2a2(Goods db_goods) {
		
		a_Goods_2 a_goods_2 = new a_Goods_2();
		a_goods_2.setGoodPrice(db_goods.getMoneyNew());
		a_goods_2.setGoodsHtmlUrl(db_goods.getGoodsHtmlUrl());
		a_goods_2.setGoodsId(db_goods.getGoodsId());
		a_goods_2.setGoodsName(db_goods.getGoodsName());
		a_goods_2.setGoodsRemark(db_goods.getGoodsDescription());
		a_goods_2.setGoodsSize1(db_goods.getGoodsSize1());
		a_goods_2.setImg1(db_goods.getImg6());//详情页：pic_gooddetail 750*750
		
		return a_goods_2;
	}
	
	public a_Goods_1 goods2a1(Goods db_goods) {
		
		a_Goods_1 a_goods_1 = new a_Goods_1();
		
		a_goods_1.setGoodPrice(db_goods.getMoneyNew());
		a_goods_1.setGoodsId(db_goods.getGoodsId());
		a_goods_1.setGoodsName(db_goods.getGoodsName());
		
		a_goods_1.setImg1(db_goods.getImg7());//搜索二级页面：pic_sec_good 390*390
		
		a_goods_1.setGoodWord(db_goods.getGoodsTags());
		a_goods_1.setGoodTag(db_goods.getGoodsTags());
		
		return a_goods_1;
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
		request.setCharacterEncoding("utf-8");
		String s1 = request.getParameter("id");
		String s2 = request.getParameter("typ");
		
		if (s1 == null || s1.equals("") || !test.isInteger(s1) || s2 == null || s2.equals("") || !test.isInteger(s2)) {
			message = msg.goods_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		int goodsid = Integer.parseInt(s1);
		int type = Integer.parseInt(s2);
		
		if (type < 1 || type > 3) {
			message = msg.goods_illegalinput;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		Goods db_goods = goodsService.View(goodsid);
		
		if (db_goods == null) {
			message = msg.goods_goodsnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.goods_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		
		if (type == 1) {
			a_Goods_1 a_goods_1 = goods2a1(db_goods);
			data = gson.toJson(a_goods_1);
		}
		else if (type == 2) {//（2为详情页,1和3不用显示图片）
			a_Goods_2 a_goods_2 = goods2a2(db_goods);
			data = gson.toJson(a_goods_2);
		}
		else {
			a_Goods_3 a_goods_3 = goods2a3(db_goods);
			data = gson.toJson(a_goods_3);
		}
		
		map.put("data", data);
		mapdata = gson.toJson(map);
		
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
		request.setCharacterEncoding("utf-8");
		String s1 = request.getParameter("kw");
		String s2 = request.getParameter("page");
		String s3 = request.getParameter("oby");
		String s4 = request.getParameter("typ");
		String s5 = request.getParameter("id");
		
		String keyword;
		if (s1 == null || s1.equals("")) {
			keyword = "";
		}
		else {
			keyword = s1;
		}
		
		int currentPage;
		if (s2 == null || s2.equals("") || !test.isInteger(s2)) {
			currentPage = 1;
		}
		else {
			currentPage = Integer.parseInt(s2);
		}
		
		int orderby;
		if (s3 == null || s3.equals("") || !test.isInteger(s3)) {
			orderby = 0;
		}
		else {
			orderby = Integer.parseInt(s3);
			if (orderby < 0 || orderby > 4)
				orderby = 0;
		}
		
		int type;
		if (s4 == null || s4.equals("") || !test.isInteger(s4)) {
			type = 0;
		}
		else {
			type = Integer.parseInt(s4);
			if (type < 0 || type > 2)
				type = 0;
		}
		
		int sortid;
		if (s5 == null || s5.equals("") || !test.isInteger(s5)) {
			sortid = 0;
		}
		else {
			sortid = Integer.parseInt(s5);
			if (sortid < 10 || sortid > 26)
				sortid = 0;
		}
		
		System.out.println("string1:" + s1 + "------------keyword:" + keyword);
		System.out.println("string2:" + s2 + "------------currentPage:" + currentPage);
		System.out.println("string3:" + s3 + "------------orderby:" + orderby);
		System.out.println("string4:" + s4 + "------------type:" + type);
		System.out.println("string5:" + s5 + "------------sortid:" + sortid);
		
		int firstPage = 1;
		int lastPage = 1;
		int totalRecord = goodsService.Count_Sort_Keyword(0, sortid, keyword);
		int totalPage = totalRecord / msg.RECORD_SIZE + 1;
		if ((totalRecord % msg.RECORD_SIZE == 0) && (totalRecord > msg.RECORD_SIZE)) {
			totalPage--;
		}
		if (currentPage > totalPage || currentPage <= 0) {
			currentPage = 1;
		}
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
		int toIndex = Math.min(fromIndex + msg.RECORD_SIZE, totalRecord);// 选择从第几条结束
		
		System.out.println("当前页码：totalPage:" + totalPage);
		System.out.println("当前页码：totalRecord:" + totalRecord);
		System.out.println("当前页码：currentPage:" + currentPage);
		System.out.println("当前页码：fromIndex:" + fromIndex);
		System.out.println("当前页码：toIndex:" + toIndex);
		System.out.println("当前页码：firstPage:" + firstPage);
		System.out.println("当前页码：lastPage:" + lastPage);
		
		List list = new ArrayList();
		List list1 = new ArrayList();
		
		if (orderby == 1 && type == 1)
			list = goodsService.Find_Price_Desc(sortid, keyword, fromIndex, msg.RECORD_SIZE);
		else if (orderby == 1 && type == 2)
			list = goodsService.Find_Price_Asc(sortid, keyword, fromIndex, msg.RECORD_SIZE);
		else if (orderby == 1)
			list = goodsService.Find_Price(sortid, keyword, fromIndex, msg.RECORD_SIZE);
		else if (orderby == 2 && type == 1)
			list = goodsService.Find_Sales_Desc(sortid, keyword, fromIndex, msg.RECORD_SIZE);
		else if (orderby == 2 && type == 2)
			list = goodsService.Find_Sales_Asc(sortid, keyword, fromIndex, msg.RECORD_SIZE);
		else if (orderby == 2)
			list = goodsService.Find_Sales(sortid, keyword, fromIndex, msg.RECORD_SIZE);
		else if (orderby == 3 && type == 1)
			list = goodsService.Find_Time_Desc(sortid, keyword, fromIndex, msg.RECORD_SIZE);
		else if (orderby == 3 && type == 2)
			list = goodsService.Find_Time_Asc(sortid, keyword, fromIndex, msg.RECORD_SIZE);
		else if (orderby == 3)
			list = goodsService.Find_Time(sortid, keyword, fromIndex, msg.RECORD_SIZE);
		else if (orderby == 4)
			list = goodsService.Find_Rand(sortid, keyword, 0, msg.RECORD_SIZE);
		else {
			list = goodsService.Find_Sort_Desc(sortid, keyword, fromIndex, msg.RECORD_SIZE);
		}
		
		if (list.size() == 0) {
			message = msg.goods_goodsnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		for (int i = 0; i < list.size(); i++) {
			Goods goods = (Goods) list.get(i);
			
			a_Goods_1 a_goods_1 = goods2a1(goods);
			
			list1.add(a_goods_1);
		}
		
		message = msg.goods_success;
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
		
		logger.info("获取商品数据成功！");
		
		return;
	}
	
	public void doHomePage() throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		
		test.a(Task_SetHomePage.map_data);
		out.write(Task_SetHomePage.map_data);
		out.flush();
		out.close();
		
		logger.info("获取首页数据成功！");
		
		return;
	}
	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	public CompanyService getCompanyService() {
		return companyService;
	}
	
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	public SortService getSortService() {
		return sortService;
	}
	
	public void setSortService(SortService sortService) {
		this.sortService = sortService;
	}
	
}
