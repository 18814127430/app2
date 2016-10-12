package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import service.OInfoService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.OInfo;
import bean.Order;

public class OInfoAction extends ActionSupport {

	private OInfoService oinfoService;// 业务层对象
	private OInfo oinfo;// 待操作的对象
	private String keyword;// 界面层需要查询的属性：关键字
	private String oinfoStartDate;
	private int firstPage;// 显示的第一页
	private int lastPage;// 显示的最后一页
	private int currentPage;// 显示的当前页
	private int totalPage;// 总页数
	private int totalRecord;// 总记录数
	private final int RECORD_SIZE = 10;// 每页记录数
	private final int PAGE_SIZE = 10;// 每组的页数
	private int goodsid;
	private String deletelist;

	public String doAdd() throws Exception {
		System.out.println("doAdd要添加的信息:" + oinfo);
		OInfo db_oinfo = oinfoService.Add(oinfo);
		System.out.println("doAdd添加后的信息:" + db_oinfo);
		this.oinfo = db_oinfo;
		if (db_oinfo != null) {
			ActionContext.getContext().put("oinfo", oinfo);
			return "oinfoinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", oinfoService.getMsg());
			return "systemerror_view";
		}
	}

	public String doDelete() throws Exception {
		OInfo db_oinfo = oinfoService.View(oinfo.getOcId());
		System.out.println("daDelete要删除的信息:" + db_oinfo);
		if (oinfoService.Delete(db_oinfo)) {
			if (goodsid > 0) {
				return (doFindByGoodsId());
			} else {
				return (doFind());
			}
		} else {
			ActionContext.getContext().put("errorMsg", oinfoService.getMsg());
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
			OInfo db_oinfo = oinfoService.View(num);
			oinfoService.Delete(db_oinfo);
		}
		if (goodsid > 0) {
			return (doFindByGoodsId());
		} else {
			return (doFind());
		}
	}

	public String doView() throws Exception {
		OInfo db_oinfo = oinfoService.View(oinfo.getOcId());
		this.oinfo = db_oinfo;
		if (db_oinfo != null) {
			ActionContext.getContext().put("oinfo", oinfo);
			return "oinfoinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", oinfoService.getMsg());
			return "systemerror_view";
		}
	}

	public String doFind() throws Exception {
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

		totalRecord = oinfoService.GetCount(keyword);
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
		List list = oinfoService.Find(keyword, fromIndex, toIndex - fromIndex);

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		ctx.put("keyword", keyword);
		return "oinfolist_view";
	}

	public String doFindByGoodsId() throws Exception {
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

		totalRecord = oinfoService.GetCountByGoodsId(goodsid, keyword);
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
		List list = oinfoService.FindByGoodsId(goodsid, keyword, fromIndex, toIndex - fromIndex);

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		ctx.put("keyword", keyword);
		ctx.put("goodsid", goodsid);
		return "oinfolistbygoods_view";
	}

	public String doEdit() throws Exception {
		System.out.println("doEdit要修改信息的ID：" + oinfo.getOcId());
		OInfo db_oinfo = oinfoService.View(oinfo.getOcId());
		this.oinfo = db_oinfo;
		System.out.println("doEdit要修改信息：" + oinfo);
		if (db_oinfo != null) {
			ActionContext.getContext().put("oinfo", oinfo);
			return "oinfoedit_view";
		} else {
			ActionContext.getContext().put("errorMsg", oinfoService.getMsg());
			return "systemerror_view";
		}
	}

	public String doUpdate() throws Exception {
		System.out.println("doUpdate要修改的信息:" + oinfo);
		OInfo db_oinfo = oinfoService.Update(oinfo);
		this.oinfo = db_oinfo;
		if (db_oinfo != null) {
			ActionContext.getContext().put("oinfo", oinfo);
			System.out.println("doUpdate修改后信息:" + oinfo);
			return "oinfoinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", oinfoService.getMsg());
			return "systemerror_view";
		}
	}

	public OInfoService getOInfoService() {
		return oinfoService;
	}

	public void setOInfoService(OInfoService oinfoService) {
		this.oinfoService = oinfoService;
	}

	public OInfo getOInfo() {
		return oinfo;
	}

	public void setOInfo(OInfo oinfo) {
		this.oinfo = oinfo;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOInfoStartDate() {
		return oinfoStartDate;
	}

	public void setOInfoStartDate(String oinfoStartDate) {
		this.oinfoStartDate = oinfoStartDate;
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

	public int getRECORD_SIZE() {
		return RECORD_SIZE;
	}

	public int getPAGE_SIZE() {
		return PAGE_SIZE;
	}

	public OInfoService getOinfoService() {
		return oinfoService;
	}

	public void setOinfoService(OInfoService oinfoService) {
		this.oinfoService = oinfoService;
	}

	public OInfo getOinfo() {
		return oinfo;
	}

	public void setOinfo(OInfo oinfo) {
		this.oinfo = oinfo;
	}

	public String getOinfoStartDate() {
		return oinfoStartDate;
	}

	public void setOinfoStartDate(String oinfoStartDate) {
		this.oinfoStartDate = oinfoStartDate;
	}

	public int getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public String getDeletelist() {
		return deletelist;
	}

	public void setDeletelist(String deletelist) {
		this.deletelist = deletelist;
	}
}
