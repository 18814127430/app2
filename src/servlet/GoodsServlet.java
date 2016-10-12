package servlet;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import service.CompanyService;
import service.GoodsService;
import service.SortService;
import utils.msg;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Admin;
import bean.CAddress;
import bean.Customer;
import bean.Goods;
import bean.Sort;

public class GoodsServlet extends ActionSupport {

	private GoodsService goodsService;// 业务层对象
	private CompanyService companyService;// 业务层对象
	private SortService sortService;// 业务层对象
	private String keyword;// 界面层需要查询的属性：关键字
	private int sortid;// 界面层需要查询的属性：关键字

	private final int RECORD_SIZE = 10;// 每页记录数
	private final int PAGE_SIZE = 10;// 每组的页数

	private Gson gson = new Gson();
	private Map<String, String> map = new HashMap<String, String>();
	private String json = "";
	private String responsemsg = "";
	private String mapjson = "";

	public void doView() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		int goodsid = Integer.parseInt(request.getParameter("id"));

		Goods db_goods = goodsService.View(goodsid);
		if (db_goods == null) {
			responsemsg = msg.fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		json = gson.toJson(db_goods);
		responsemsg = msg.fail;
		map.put("json", json);
		map.put("responsemsg", responsemsg);
		mapjson = gson.toJson(map);

		out.write(mapjson);
		out.flush();
		out.close();
		return;
	}

	public void doFind() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		int currentPage = Integer.parseInt(request.getParameter("page"));
		String keyword = request.getParameter("kw");

		if (keyword == null || keyword == "")
			keyword = "";

		int firstPage = 1;
		int lastPage = 1;
		int totalRecord = goodsService.GetCount(keyword);
		int totalPage = totalRecord / this.RECORD_SIZE + 1;
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

		List<?> list = goodsService.Find(keyword, fromIndex, toIndex - fromIndex);// 可优化
		if (list.size() == 0) {
			responsemsg = msg.fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		json = gson.toJson(list);
		responsemsg = msg.fail;
		map.put("json", json);
		map.put("responsemsg", responsemsg);
		map.put("totalPage", Integer.toString(totalPage));
		map.put("totalRecord", Integer.toString(totalRecord));
		map.put("currentPage", Integer.toString(currentPage));
		map.put("fromIndex", Integer.toString(fromIndex));
		map.put("toIndex", Integer.toString(toIndex));
		map.put("firstPage", Integer.toString(firstPage));
		map.put("lastPage", Integer.toString(lastPage));
		mapjson = gson.toJson(map);

		out.write(mapjson);
		out.flush();
		out.close();
		return;
	}

	public void doFindBySortId() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		int currentPage = Integer.parseInt(request.getParameter("page"));
		String keyword = request.getParameter("kw");

		if (keyword == null || keyword == "")
			keyword = "";

		int firstPage = 1;
		int lastPage = 1;
		int totalRecord = goodsService.GetCountBySortId(sortid, keyword);
		int totalPage = totalRecord / this.RECORD_SIZE + 1;
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
		List list = goodsService.FindBySortId(sortid, fromIndex, toIndex - fromIndex);
		if (list.size() == 0) {
			responsemsg = msg.fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		json = gson.toJson(list);
		responsemsg = msg.fail;
		map.put("json", json);
		map.put("responsemsg", responsemsg);
		map.put("totalPage", Integer.toString(totalPage));
		map.put("totalRecord", Integer.toString(totalRecord));
		map.put("currentPage", Integer.toString(currentPage));
		map.put("fromIndex", Integer.toString(fromIndex));
		map.put("toIndex", Integer.toString(toIndex));
		map.put("firstPage", Integer.toString(firstPage));
		map.put("lastPage", Integer.toString(lastPage));
		mapjson = gson.toJson(map);

		out.write(mapjson);
		out.flush();
		out.close();
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
