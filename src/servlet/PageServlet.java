package servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import service.PageService;
import utils.msg;
import utils.test;
import androidbeans.a_Page;
import androidbeans.a_Goods_1;
import androidbeans.a_Page;
import bean.Page;
import bean.Customer;
import bean.Goods;
import bean.Page;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PageServlet extends ActionSupport {
	
	private PageService pageService;// 业务层对象
	private final int Carousel_figure = 4;
	
	public a_Page page2a(Page db_page) {
		a_Page a_page = new a_Page();
		
		a_page.setGoodsId(db_page.getGoods().getGoodsId());
		a_page.setPageHtml(db_page.getPageHtml());
		a_page.setPageId(db_page.getPageId());
		a_page.setPageImg(db_page.getPageImg());
		
		return a_page;
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
		
		List<?> list = pageService.Find_All();
		System.out.println("-------------->list.size:" + list.size());
		
		List list1 = new ArrayList();
		for (int i = 0; i < Math.min(Carousel_figure, list.size()); i++) {
			Page db_page = (Page) list.get(i);
			
			a_Page a_page = page2a(db_page);
			
			list1.add(a_page);
		}
		System.out.println("-------------->list1.size:" + list1.size());
		if (list1.size() <= 0) {
			message = msg.page_pagenull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
		
		message = msg.page_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		
		data = gson.toJson(list1);
		map.put("data", data);
		
		mapdata = gson.toJson(map);
		out.write(mapdata);
		out.flush();
		out.close();
		return;
	}
	
	public PageService getPageService() {
		return pageService;
	}
	
	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}
	
}
