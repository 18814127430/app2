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
import utils.goodsTool;
import utils.sortTool;
import utils.test;

public class SortAction extends ActionSupport {
	
	private SortService sortService;// 业务层对象
	private GoodsService goodsService;// 业务层对象
	private Sort sort;// 待操作的对象
	private String keyword = "";// 界面层需要查询的属性：关键字
	private int parentid;
	private int currentPage;// 显示的当前页
	
	public String beforedoAdd() throws Exception {
		List<?> list = sortService.Find_SortClass(1);
		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		return "sortadd_view";
	}
	
	public String doAdd() throws Exception {
		sort = sortTool.SetSortImgSize(sort);
		
		Sort parent_sort = sortService.View(parentid);
		sort.setSortClass(2);
		sort.setSort(parent_sort);
		sort.setSortImgExtension(test.GetFileExtension(new File(sort.getSortImgPath())));
		
		System.out.println("doAdd要添加的信息:" + sort);
		Sort db_sort = sortService.Add(sort);
		System.out.println("doAdd添加后的信息:" + db_sort);
		
		if (db_sort != null) {
			return (doFind());
		}
		else {
			sortTool.DeleteImg_New(sort);
			ActionContext.getContext().put("Msg", sortService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doDelete() throws Exception {
		Sort db_sort = sortService.View(sort.getSortId());
		System.out.println("daDelete要删除的信息:" + db_sort);
		if (sortService.Delete(db_sort)) {
			return (doFind());
		}
		else {
			ActionContext.getContext().put("Msg", sortService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doView() throws Exception {
		Sort db_sort = sortService.View(sort.getSortId());
		this.sort = db_sort;
		if (db_sort != null) {
			ActionContext.getContext().put("sort", sort);
			return "sortinfo_view";
		}
		else {
			ActionContext.getContext().put("Msg", sortService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doFind() throws Exception {
		
		List<?> list = sortService.Find_All();
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
		Sort db_sort = sortService.View(sort.getSortId());
		this.sort = db_sort;
		if (db_sort != null) {
			int parentsortclass = db_sort.getSortClass() - 1;
			if (parentsortclass == 0) {
				parentsortclass = 1;
			}
			List list1 = sortService.Find_SortClass(parentsortclass);
			ActionContext ctx = ActionContext.getContext();
			ctx.put("list1", list1);
			ctx.put("sort", sort);
			return "sortedit_view";
		}
		else {
			ActionContext.getContext().put("Msg", sortService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doUpdate() throws Exception {
		Sort old_sort = sortService.View(sort.getSortId());
		
		sort = sortTool.SetSortImgSize(sort);
		
		Sort db_sort_parent = sortService.View(parentid);
		sort.setSort(db_sort_parent);
		
		System.out.println("doUpdate要修改的信息:" + sort);
		Sort db_sort = sortService.Update(sort);
		System.out.println("doUpdate要修改的信息:" + db_sort);
		
		if (db_sort != null) {
			sortTool.Delete_Img_Old(old_sort, db_sort);
			ActionContext.getContext().put("sort", db_sort);
			return (doFind());
		}
		else {
			sortTool.DeleteImg_New(sort);
			ActionContext.getContext().put("Msg", sortService.getMsg());
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
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
	
}
