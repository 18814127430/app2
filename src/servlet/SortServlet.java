package servlet;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import service.GoodsService;
import service.SortService;
import utils.msg;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Goods;
import bean.Sort;

public class SortServlet extends ActionSupport {

	private SortService sortService;// 业务层对象
	private GoodsService goodsService;// 业务层对象
	private Sort sort;// 待操作的对象
	private int parentid;
	private final int RECORD_SIZE = 10;// 每页记录数
	private final int PAGE_SIZE = 10;// 每组的页数

	private Gson gson = new Gson();
	private Map<String, String> map = new HashMap<String, String>();
	private String json = "";
	private String responsemsg = "";
	private String mapjson = "";

	public void doFind() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");

		List<?> list = sortService.Find();
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Sort sort = (Sort) list.get(i);
			if (sort.getSortClass() == 1) {
				list1.add(sort);
			}
			if (sort.getSortClass() == 2) {
				list2.add(sort);
			}
		}
		if (list1.size() == 0 || list2.size() == 0) {
			responsemsg = msg.fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		System.out.println(list1.size());
		System.out.println(list2.size());
		
		String json1 = gson.toJson(list1);
		String json2 = gson.toJson(list2);
		
		System.out.println(json1);
		System.out.println(json2);
		
		
		responsemsg = msg.success;
		map.put("json1", json1);
		map.put("json2", json2);
		map.put("responsemsg", responsemsg);
		mapjson = gson.toJson(map);

		out.write(mapjson);
		out.flush();
		out.close();
		return;
	}

	public SortService getSortService() {
		return sortService;
	}

	public void setSortService(SortService sortService) {
		this.sortService = sortService;
	}

	public GoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
}
