package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import service.AdminService;
import service.CAddressService;
import service.CWordsService;
import service.CustomerService;
import service.HelpService;
import utils.MD5Util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.AdminDAO;
import dao.OrderDAO;
import bean.Admin;
import bean.CAddress;
import bean.CWords;
import bean.Customer;
import bean.Order;

public class AdminAction extends ActionSupport {

	private static final Log log = LogFactory.getLog(AdminAction.class);
	private AdminService adminService;// 业务层对象
	private Admin admin;// 待操作的对象
	private String keyword;// 界面层需要查询的属性：关键字
	private File imgFile;
	private String imgFileName;
	private String name;
	private String password;
	private int firstPage;// 显示的第一页
	private int lastPage;// 显示的最后一页
	private int currentPage;// 显示的当前页
	private int totalPage;// 总页数
	private int totalRecord;// 总记录数
	private final int RECORD_SIZE = 10;// 每页记录数
	private final int PAGE_SIZE = 10;// 每组的页数
	private String deletelist;

	public String doLogin() {
		return "login_view";
	}

	public String checkLogin() throws Exception {
		System.out.println(name);
		System.out.println(password);

		Admin temp_admin = new Admin();
		temp_admin.setAdminAccount(name);
		temp_admin.setAdminPassword(password);

		Admin db_admin = adminService.Login(temp_admin);
		this.admin = db_admin;
		if (db_admin == null) {
			ActionContext.getContext().put("Msg", adminService.getMsg());
			return "systemerror_view";
		} else {
			ActionContext.getContext().getSession().put("user", admin);
			log.info(db_admin.toString());
			return "main_view";
		}
	}

	public String doAdd() throws Exception {
		System.out.println("doAdd要添加的图片Name信息:" + admin.getAdminImg());
		Admin db_admin = adminService.Add(admin);
		System.out.println("doAdd要添加的信息:" + admin);
		System.out.println("doAdd要添加的图片File信息:" + imgFile);
		System.out.println("doAdd要添加的图片Name信息:" + admin.getAdminImg());
		System.out.println("doAdd添加后的信息:" + db_admin);
		this.admin = db_admin;
		if (db_admin != null) {
			ActionContext.getContext().put("admin", admin);
			return "admininfo_view";
		} else {
			ActionContext.getContext().put("Msg", adminService.getMsg());
			return "systemerror_view";
		}
	}

	public String doDelete() throws Exception {
		Admin db_admin = adminService.View(admin.getAdminId());
		if (adminService.Delete(db_admin)) {
			return (doFind());
		} else {
			ActionContext.getContext().put("Msg", adminService.getMsg());
			return "systemerror_view";
		}
	}

	public String doDeleteAll() throws Exception {

		String[] id = deletelist.split(",");// 用逗号切割
		System.out.println("用逗号切割");
		System.out.println(deletelist);
		System.out.println(currentPage);

		for (int i = 0; i < id.length; i++) {
			int num = Integer.parseInt(id[i]);
			Admin db_admin = adminService.View(num);
			adminService.Delete(db_admin);
		}

		return (doFind());
	}

	public String doView() throws Exception {
		Admin db_admin = adminService.View(admin.getAdminId());
		this.admin = db_admin;
		if (db_admin != null) {
			ActionContext.getContext().put("admin", admin);
			return "admininfo_view";
		} else {
			ActionContext.getContext().put("Msg", adminService.getMsg());
			return "systemerror_view";
		}
	}

	public String doFind() throws Exception {
		System.out.println(keyword);

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

		totalRecord = adminService.GetCount(keyword);
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

		List<?> list = adminService.Find(keyword, fromIndex, toIndex - fromIndex);// 可优化

		// 从总数列表中截取子列表,返回从fromIndex到toindex-1 的 子列表
		// List list1 = list.subList(fromIndex, toIndex);

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		ctx.put("keyword", keyword);

		return "adminlist_view";
	}

	public String doEdit() throws Exception {
		Admin db_admin = adminService.View(admin.getAdminId());
		System.out.println("doEdit要修改信息：" + db_admin);
		this.admin = db_admin;
		if (db_admin != null) {
			ActionContext.getContext().put("admin", admin);
			return "adminedit_view";
		} else {
			ActionContext.getContext().put("Msg", adminService.getMsg());
			return "systemerror_view";
		}
	}

	public String doUpdate() throws Exception {
		System.out.println("doUpdate要修改的信息:" + admin);
		System.out.println("doUpdate要修改的图片File信息:" + imgFile);
		System.out.println("doUpdate要修改的图片Name信息:" + imgFileName);
		Admin db_admin = adminService.Update(admin);
		this.admin = db_admin;
		if (db_admin != null) {
			ActionContext.getContext().put("admin", admin);
			System.out.println("doUpdate修改后信息:" + db_admin);
			return "admininfo_view";
		} else {
			ActionContext.getContext().put("Msg", adminService.getMsg());
			return "systemerror_view";
		}
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public static Log getLog() {
		return log;
	}

	public int getRECORD_SIZE() {
		return RECORD_SIZE;
	}

	public int getPAGE_SIZE() {
		return PAGE_SIZE;
	}

	public String getDeletelist() {
		return deletelist;
	}

	public void setDeletelist(String deletelist) {
		this.deletelist = deletelist;
	}

}
