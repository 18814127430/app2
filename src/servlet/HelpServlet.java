package servlet;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import service.HelpService;
import utils.msg;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import bean.Help;

public class HelpServlet extends ActionSupport {

	private HelpService helpService;// 业务层对象
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
		int helpid = Integer.parseInt(request.getParameter("id"));

		Help object = helpService.View(helpid);

		System.out.println("------->helpid:" + helpid);
		System.out.println("------->object:" + object);

		if (object == null) {
			responsemsg = msg.fail;
			map.put("responsemsg", responsemsg);
			mapjson = gson.toJson(map);

			out.write(mapjson);
			out.flush();
			out.close();
			return;
		}

		json = gson.toJson(object);
		responsemsg = msg.success;
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
		int totalRecord = helpService.GetCount(keyword);
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

		List<?> list = helpService.Find(keyword, fromIndex, toIndex - fromIndex);// 可优化
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
		responsemsg = msg.success;
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

	public HelpService getHelpService() {
		return helpService;
	}

	public void setHelpService(HelpService helpService) {
		this.helpService = helpService;
	}

}
