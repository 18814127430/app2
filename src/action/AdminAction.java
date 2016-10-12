package action;

import java.util.List;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.struts2.ServletActionContext;
import service.AdminService;
import utils.msg;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Admin;
import utils.test;

public class AdminAction extends ActionSupport {
	private final Log logger = LogFactory.getLog(getClass());
	
	private AdminService adminService;
	private Admin admin;
	private String keyword;
	private String name;
	private String password;
	private int currentPage;
	private String deletelist;
	
	public String doLogin() {
		return "login_view";
	}
	
	public String checkLogin() throws Exception {
		System.out.println("name:" + name);
		System.out.println("password:" + password);
		
		Admin db_admin = new Admin();
		db_admin.setAdminAccount(name);
		db_admin.setAdminPassword(password);
		
		if (name == null || name.equals("") || password == null || password.equals("")) {
			
			return "login_view";
		}
		
		db_admin = adminService.Login(db_admin);
		if (db_admin == null) {
			ActionContext.getContext().put("Msg", adminService.getMsg());
			return "systemerror_view";
		}
		
		if (db_admin.getAdminDates().equals("") || db_admin.getAdminDates() == null) {
			db_admin.setAdminDates(test.GetCurrentTime());
		}
		else {
			db_admin.setAdminDates(test.GetCurrentTime() + "," + db_admin.getAdminDates());
		}
		db_admin = adminService.Update(db_admin);
		
		msg.root_path = ServletActionContext.getServletContext().getRealPath("/");
		
		ActionContext.getContext().getSession().put("user", db_admin);
		return "main_view";
	}
	
	public String beforedoAdd() throws Exception {
		
		Admin user = (Admin) ActionContext.getContext().getSession().get("user");
		System.out.println("user_class:" + user.getAdminClass());
		if (!user.getAdminClass().equalsIgnoreCase("super")) {
			ActionContext.getContext().put("Msg", msg.admin_authority);
			return "systemerror_view";
		}
		
		return "adminadd_view";
	}
	
	public String doAdd() throws Exception {
		
		Admin user = (Admin) ActionContext.getContext().getSession().get("user");
		System.out.println("user_class:" + user.getAdminClass());
		if (!user.getAdminClass().equalsIgnoreCase("super")) {
			ActionContext.getContext().put("Msg", msg.admin_authority);
			return "systemerror_view";
		}
		
		admin.setAdminDates(test.GetCurrentTime());
		if (admin.getAdminImg().equals("") || admin.getAdminImg() == null) {
			admin.setAdminImg(msg.defaultImg_admin);
		}
		
		Admin db_admin = adminService.Add(admin);
		
		System.out.println("doAdd要添加的信息:" + admin);
		System.out.println("doAdd添加后的信息:" + db_admin);
		
		if (db_admin == null) {
			ActionContext.getContext().put("Msg", adminService.getMsg());
			return "systemerror_view";
		}
		
		this.admin = db_admin;
		ActionContext.getContext().put("admin", admin);
		return "admininfo_view";
	}
	
	public String doDelete() throws Exception {
		
		Admin user = (Admin) ActionContext.getContext().getSession().get("user");
		System.out.println("user_class:" + user.getAdminClass());
		if (!user.getAdminClass().equalsIgnoreCase("super")) {
			ActionContext.getContext().put("Msg", msg.admin_authority);
			return "systemerror_view";
		}
		
		Admin db_admin = adminService.View(admin.getAdminId());
		if (!adminService.Delete(db_admin)) {
			ActionContext.getContext().put("Msg", adminService.getMsg());
			return "systemerror_view";
			
		}
		return (doFind());
	}
	
	public String doDeleteAll() throws Exception {
		
		Admin user = (Admin) ActionContext.getContext().getSession().get("user");
		System.out.println("user_class:" + user.getAdminClass());
		if (!user.getAdminClass().equalsIgnoreCase("super")) {
			ActionContext.getContext().put("Msg", msg.admin_authority);
			return "systemerror_view";
		}
		
		String[] id = deletelist.split(",");
		
		for (int i = 0; i < id.length; i++) {
			int num = Integer.parseInt(id[i]);
			Admin db_admin = adminService.View(num);
			adminService.Delete(db_admin);
		}
		
		return (doFind());
	}
	
	public String doView() throws Exception {
		Admin db_admin = adminService.View(admin.getAdminId());
		if (db_admin == null) {
			ActionContext.getContext().put("Msg", adminService.getMsg());
			return "systemerror_view";
		}
		
		db_admin.setAdminPassword("");
		this.admin = db_admin;
		ActionContext.getContext().put("admin", admin);
		return "admininfo_view";
	}
	
	public String doFind() throws Exception {
		
		if (currentPage <= 0)
			currentPage = 1;
		if (keyword == null)
			keyword = "";
		
		System.out.println("--------currentPage:" + currentPage);
		System.out.println("--------keyword:" + keyword);
		
		int totalRecord = adminService.Count_Keyword(keyword);
		int totalPage = totalRecord / msg.RECORD_SIZE + 1;
		if ((totalRecord % msg.RECORD_SIZE == 0) && (totalRecord > msg.RECORD_SIZE)) {
			totalPage--;
		}
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		
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
		
		int fromIndex = (currentPage - 1) * msg.RECORD_SIZE; // 选择从第几条开始
		
		System.out.println("当前页码：totalPage" + totalPage);
		System.out.println("当前页码：totalRecord" + totalRecord);
		System.out.println("当前页码：currentPage" + currentPage);
		System.out.println("当前页码：fromIndex" + fromIndex);
		System.out.println("当前页码：firstPage" + firstPage);
		System.out.println("当前页码：lastPage" + lastPage);
		
		List<?> list = adminService.Find_Keyword(keyword, fromIndex, msg.RECORD_SIZE);
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", msg.PAGE_SIZE);
		ctx.put("keyword", keyword);
		
		return "adminlist_view";
	}
	
	public String doEdit() throws Exception {
		
		Admin user = (Admin) ActionContext.getContext().getSession().get("user");
		System.out.println("user_class:" + user.getAdminClass());
		System.out.println("user:" + user);
		System.out.println("admin:" + admin);
		
		if (user.getAdminId().intValue() != admin.getAdminId().intValue()) {
			if (user.getAdminClass().equalsIgnoreCase("general")) {
				ActionContext.getContext().put("Msg", msg.admin_authority);
				return "systemerror_view";
			}
		}
		
		Admin db_admin = adminService.View(admin.getAdminId());
		System.out.println("doEdit要修改信息：" + db_admin);
		
		if (db_admin == null) {
			ActionContext.getContext().put("Msg", adminService.getMsg());
			return "systemerror_view";
		}
		
		this.admin = db_admin;
		ActionContext.getContext().put("admin", admin);
		return "adminedit_view";
	}
	
	public String doUpdate() throws Exception {
		
		Admin user = (Admin) ActionContext.getContext().getSession().get("user");
		System.out.println("user_class:" + user.getAdminClass());
		System.out.println("user:" + user);
		System.out.println("admin:" + admin);
		
		if (user.getAdminId().intValue() != admin.getAdminId().intValue()) {
			if (user.getAdminClass().equalsIgnoreCase("general")) {
				ActionContext.getContext().put("Msg", msg.admin_authority);
				return "systemerror_view";
			}
		}
		
		System.out.println("doUpdate要修改的信息:" + admin);
		
		Admin db_admin = adminService.Update(admin);
		System.out.println("doUpdate修改后信息:" + db_admin);
		
		if (db_admin == null) {
			ActionContext.getContext().put("Msg", adminService.getMsg());
			return "systemerror_view";
		}
		
		this.admin = db_admin;
		ActionContext.getContext().put("admin", admin);
		return "admininfo_view";
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
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public String getDeletelist() {
		return deletelist;
	}
	
	public void setDeletelist(String deletelist) {
		this.deletelist = deletelist;
	}
	
}
