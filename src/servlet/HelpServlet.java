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
import utils.test;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import androidbeans.a_Help;
import bean.Help;
import bean.Customer;
import bean.Help;

public class HelpServlet extends ActionSupport {

	private HelpService helpService;// 业务层对象

	public a_Help help2a(Help db_help) {
		a_Help a_help = new a_Help();

		a_help.setHelpContext(db_help.getHelpContext());
		a_help.setHelpId(db_help.getHelpId());

		return a_help;
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
		int helpid = Integer.parseInt(request.getParameter("id"));

		Help db_help = helpService.View(helpid);

		System.out.println("------->helpid:" + helpid);
		System.out.println("------->object:" + db_help);

		if (db_help == null) {
			message = msg.help_helpnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
		} else {
			message = msg.help_success;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			data = gson.toJson(db_help);
			map.put("data", data);
		}

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
		request.setCharacterEncoding("UTF-8");
		int currentPage = Integer.parseInt(request.getParameter("page"));
		String keyword = new String(request.getParameter("kw").getBytes("iso-8859-1"), "utf-8");

		if (keyword == null || keyword == "")
			keyword = "";

		int firstPage = 1;
		int lastPage = 1;
		int totalRecord = helpService.Count_Keyword(keyword);
		int totalPage = totalRecord / msg.RECORD_SIZE + 1;
		if ((totalRecord % msg.RECORD_SIZE == 0) && (totalRecord > msg.RECORD_SIZE)) {
			totalPage--;
		}
		if (currentPage > totalPage) {
			currentPage = 1;
		}
		if (totalPage < msg.PAGE_SIZE) {
			firstPage = 1;
			lastPage = totalPage;
		} else {
			firstPage = (currentPage / msg.PAGE_SIZE) * msg.PAGE_SIZE + 1;
			lastPage = firstPage + msg.PAGE_SIZE - 1;
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
		int fromIndex = (currentPage - 1) * msg.RECORD_SIZE; // 选择从第几条开始
		int toIndex = Math.min(fromIndex + msg.RECORD_SIZE, totalRecord);// 调用Math.min函数取目的数

		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：toIndex" + toIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);

		List<?> list = helpService.Find_Keyword(keyword, fromIndex, msg.RECORD_SIZE);// 可优化
		if (list.size() == 0) {
			message = msg.help_helpnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		} else {
			message = msg.help_success;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			data = gson.toJson(list);
			map.put("data", data);
			map.put("page", totalPage);
		}

		// map.put("totalPage", Integer.toString(totalPage));
		// map.put("totalRecord", Integer.toString(totalRecord));
		// map.put("currentPage", Integer.toString(currentPage));
		// map.put("fromIndex", Integer.toString(fromIndex));
		// map.put("toIndex", Integer.toString(toIndex));
		// map.put("firstPage", Integer.toString(firstPage));
		// map.put("lastPage", Integer.toString(lastPage));

		mapdata = gson.toJson(map);

		out.write(mapdata);
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
