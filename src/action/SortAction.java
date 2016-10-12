package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import service.GoodsService;
import service.SortService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Goods;
import bean.Sort;
import utils.test;

public class SortAction extends ActionSupport {

	private SortService sortService;// 业务层对象
	private GoodsService goodsService;// 业务层对象
	private Sort sort;// 待操作的对象
	private String keyword = "";// 界面层需要查询的属性：关键字
	private String sortStartDate;
	private int parentid;
	private File imgFile;
	private int firstPage;// 显示的第一页
	private int lastPage;// 显示的最后一页
	private int currentPage;// 显示的当前页
	private int totalPage;// 总页数
	private int totalRecord;// 总记录数
	private final int RECORD_SIZE = 10;// 每页记录数
	private final int PAGE_SIZE = 10;// 每组的页数

	public String beforedoAdd() throws Exception {
		List<?> list = sortService.FindBySortClass(1);
		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		return "sortadd_view";
	}

	public String doAdd() throws Exception {
		System.out.println("doAdd添加后的信息:" + parentid);
		Sort parent_sort = sortService.FindById(parentid);
		sort.setSortClass(2);
		sort.setSort(parent_sort);
		sort.setSortImgName(test.GetFileName(sort.getSortImgPath()));
		sort.setSortImgExtension(test.GetFileExtension(sort.getSortImgPath()));

		System.out.println("doAdd要添加的信息:" + sort);

		Sort db_sort = sortService.Add(sort);
		System.out.println("doAdd添加后的信息:" + db_sort);
		this.sort = db_sort;
		if (db_sort != null) {
			return (doFind());
		} else {
			ActionContext.getContext().put("errorMsg", sortService.getMsg());
			return "systemerror_view";
		}
	}

	public String doDelete() throws Exception {
		Sort db_sort = sortService.FindById(sort.getSortId());
		System.out.println("daDelete要删除的信息:" + db_sort);
		if (sortService.Delete(db_sort)) {
			return (doFind());
		} else {
			ActionContext.getContext().put("errorMsg", sortService.getMsg());
			return "systemerror_view";
		}
	}

	public String doView() throws Exception {
		Sort db_sort = sortService.FindById(sort.getSortId());
		this.sort = db_sort;
		if (db_sort != null) {
			ActionContext.getContext().put("sort", sort);
			return "sortinfo_view";
		} else {
			ActionContext.getContext().put("errorMsg", sortService.getMsg());
			return "systemerror_view";
		}
	}

	public String doFind() throws Exception {

		System.out.println(keyword);
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

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list1", list1);
		ctx.put("list2", list2);
		return "sortlist_view";
	}

	public String doEdit() throws Exception {
		System.out.println("doEdit要修改信息的ID：" + sort.getSortId());
		Sort db_sort = sortService.FindById(sort.getSortId());
		this.sort = db_sort;
		if (db_sort != null) {
			System.out.println("doE3333333333：" + db_sort.getSortClass());
			int parentsortclass = db_sort.getSortClass() - 1;
			if (parentsortclass == 0) {
				parentsortclass = 1;
			}
			List<?> list1 = sortService.FindBySortClass(parentsortclass);
			ActionContext ctx = ActionContext.getContext();
			ctx.put("list1", list1);
			ctx.put("sort", sort);
			return "sortedit_view";
		} else {
			ActionContext.getContext().put("errorMsg", sortService.getMsg());
			return "systemerror_view";
		}
	}

	public String doUpdate() throws Exception {
		System.out.println("doUpdate要修改的信息:" + parentid);
		Sort db_sort_parent = sortService.FindById(parentid);
		sort.setSort(db_sort_parent);
		System.out.println(db_sort_parent);
		Sort db_sort = sortService.Update(sort);
		this.sort = db_sort;
		if (db_sort != null) {
			ActionContext.getContext().put("sort", sort);
			System.out.println("doUpdate修改后信息:" + sort);
			return (doFind());
		} else {
			ActionContext.getContext().put("errorMsg", sortService.getMsg());
			return "systemerror_view";
		}
	}

	public SortService getSortService() {
		return sortService;
	}

	public void setSortService(SortService sortService) {
		this.sortService = sortService;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSortStartDate() {
		return sortStartDate;
	}

	public void setSortStartDate(String sortStartDate) {
		this.sortStartDate = sortStartDate;
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

	public GoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}
}
