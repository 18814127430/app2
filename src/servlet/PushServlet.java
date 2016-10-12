package servlet;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import service.PushService;
import utils.msg;
import utils.test;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import androidbeans.a_Push;
import bean.Push;
import bean.Customer;
import bean.Push;

public class PushServlet extends ActionSupport {

	private PushService pushService;// 业务层对象

	public a_Push push2a(Push db_push) {
		a_Push a_push = new a_Push();

		a_push.setPushContext(db_push.getPushContext());
		a_push.setPushId(db_push.getPushId());
		a_push.setPushUrl(db_push.getPushUrl());

		return a_push;
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
		int pushid = Integer.parseInt(request.getParameter("id"));

		Push db_push = pushService.View(pushid);

		System.out.println("------->pushid:" + pushid);
		System.out.println("------->object:" + db_push);

		if (db_push == null) {
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
		} else {
			message = msg.push_success;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			data = gson.toJson(db_push);
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
		int totalRecord = pushService.Count_Keyword(keyword);
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

		List<?> list = pushService.Find_Keyword(keyword, fromIndex, msg.RECORD_SIZE);// 可优化
		if (list.size() == 0) {
			message = msg.push_pushnull;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			mapdata = gson.toJson(map);

			out.write(mapdata);
			out.flush();
			out.close();
			return;
		} else {
			message = msg.push_success;
			status = msg.status_1;
			map.put("status", status);
			map.put("message", message);
			data = gson.toJson(list);
			map.put("data", data);map.put("page", totalPage);
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

	public PushService getPushService() {
		return pushService;
	}

	public void setPushService(PushService pushService) {
		this.pushService = pushService;
	}

}
