package action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import service.CompanyService;
import service.GoodsService;
import service.SortService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Admin;
import bean.CAddress;
import bean.Goods;
import bean.Sort;

public class GoodsAction extends ActionSupport {

	private GoodsService goodsService;// 业务层对象
	private CompanyService companyService;// 业务层对象
	private SortService sortService;// 业务层对象
	private Goods goods;// 待操作的对象
	private String keyword;// 界面层需要查询的属性：关键字
	private int sortid;// 界面层需要查询的属性：关键字
	private int producerid;// 界面层需要查询的属性：关键字
	private int sellerid;// 界面层需要查询的属性：关键字
	private String goodsStartDate;
	private int firstPage;// 显示的第一页
	private int lastPage;// 显示的最后一页
	private int currentPage;// 显示的当前页
	private int totalPage;// 总页数
	private int totalRecord;// 总记录数
	private final int RECORD_SIZE = 10;// 每页记录数
	private final int PAGE_SIZE = 10;// 每组的页数
	private String deletelist;

	public String beforedoAdd() throws Exception {

		List<?> list = sortService.Find();
		List parentlist = new ArrayList();
		List sortlist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Sort sort = (Sort) list.get(i);
			if (sort.getSortClass() == 1) {
				parentlist.add(sort);
			}
		}
		for (int i = 0; i < parentlist.size(); i++) {
			Sort sort = (Sort) parentlist.get(i);
			List childlist = sortService.FindBySortId(sort.getSortId());
			Set<Sort> set = new HashSet<Sort>(childlist);
			sort.setSorts(set);
			sortlist.add(sort);
		}

		for (int i = 0; i < sortlist.size(); i++) {
			Sort sort = (Sort) sortlist.get(i);
			System.out.println("father:" + sort);

			Set sortSet = sort.getSorts();
			Iterator iterator1 = sortSet.iterator();
			int j = 0;
			while (iterator1.hasNext()) {
				Sort sort1 = (Sort) iterator1.next();
				j++;
				System.out.println("children: " + sort1);
			}
		}

		List companylist = companyService.Find(keyword);

		// Sort[][] array = new Sort[sortlist.size()][];
		// for (int i = 0; i < array.length; i++) {
		// Sort sort = (Sort) sortlist.get(i);
		// System.out.println("00parent" + i + ":" + sort.getSortName());
		// Set sortSet = sort.getSorts();
		// Iterator iterator1 = sortSet.iterator();
		// int j = 0;
		// while (iterator1.hasNext()) {
		// Sort childrensort = (Sort) iterator1.next();
		// System.out.println("010children" + j + ":" +
		// childrensort.getSortName());
		// array[i][j] = childrensort;
		// System.out.println("00children" + j + ":" +
		// childrensort.getSortName());
		// j++;
		// }
		// }
		//
		// for (int i = 0; i < array.length; i++) {
		// Sort sort = (Sort) sortlist.get(i);
		// System.out.println("11parent" + i + ":" + sort.getSortName());
		// for (int j = 0; j < array[i].length; j++) {
		// System.out.println("11children" + j + ":" +
		// array[i][j].getSortName());
		// }
		// }

		ActionContext ctx = ActionContext.getContext();
		ctx.put("sortlist", sortlist);
		ctx.put("companylist", companylist);
		return "goodsadd_view";
	}

	public String doAdd() throws Exception {
		System.out.println("doAdd要添加的信息:" + goods);
		System.out.println("doAdd要添加的sortid信息:" + sortid);
		System.out.println("doAdd要添加的producerid信息:" + producerid);
		System.out.println("doAdd要添加的sellerid信息:" + sellerid);
		Sort sort = sortService.FindById(sortid);
		goods.setSort(sort);
		Goods db_goods = goodsService.Add(goods);
		System.out.println("doAdd添加后的信息:" + db_goods);
		this.goods = db_goods;
		if (db_goods != null) {
			ActionContext.getContext().put("goods", goods);
			return (doFind());
			// return "goodsinfo_view";
		} else {
			ActionContext.getContext().put("Msg", goodsService.getMsg());
			return "systemerror_view";
		}
	}

	public String doDelete() throws Exception {
		Goods db_goods = goodsService.View(goods.getGoodsId());
		System.out.println("daDelete要删除的信息:" + db_goods);
		if (goodsService.Delete(db_goods)) {
			return (doFind());
		} else {
			ActionContext.getContext().put("Msg", goodsService.getMsg());
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
			Goods db_goods = goodsService.View(num);
			goodsService.Delete(db_goods);
		}

		return (doFind());
	}

	public String doView() throws Exception {
		System.out.println("121212" + goods.getGoodsId());
		Goods db_goods = goodsService.View(goods.getGoodsId());
		System.out.println("121212" + db_goods.getSort());
		Sort db_sort = sortService.FindById(db_goods.getSort().getSortId());
		System.out.println("121212" + db_sort.getSort());
		this.goods = db_goods;
		if (db_goods != null) {
			ActionContext ctx = ActionContext.getContext();
			ctx.put("goods", goods);
			ctx.put("sort", db_sort);
			return "goodsinfo_view";
		} else {
			ActionContext.getContext().put("Msg", goodsService.getMsg());
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
		totalRecord = goodsService.GetCount(keyword);
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

		List<?> list = goodsService.Find(keyword, fromIndex, toIndex - fromIndex);// 可优化

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		ctx.put("keyword", keyword);
		return "goodslist_view";
	}

	public String doFindBySortId() throws Exception {

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

		totalRecord = goodsService.GetCountBySortId(sortid, keyword);
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
		List list = goodsService.FindBySortId(sortid, fromIndex, toIndex - fromIndex);

		ActionContext ctx = ActionContext.getContext();
		ctx.put("list", list);
		ctx.put("totalRecord", totalRecord);
		ctx.put("totalPage", totalPage);
		ctx.put("firstPage", firstPage);
		ctx.put("currentPage", currentPage);
		ctx.put("lastPage", lastPage);
		ctx.put("PAGE_SIZE", PAGE_SIZE);
		ctx.put("keyword", keyword);
		return "goodslist_view";
	}

	public String doEdit() throws Exception {
		List<?> list = sortService.Find();
		List parentlist = new ArrayList();
		List sortlist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Sort sort = (Sort) list.get(i);
			if (sort.getSortClass() == 1) {
				parentlist.add(sort);
			}
		}
		for (int i = 0; i < parentlist.size(); i++) {
			Sort sort = (Sort) parentlist.get(i);
			List childlist = sortService.FindBySortId(sort.getSortId());
			Set<Sort> set = new HashSet<Sort>(childlist);
			sort.setSorts(set);
			sortlist.add(sort);
		}

		List companylist = companyService.Find(keyword);

		System.out.println("doEdit要修改信息的ID：" + goods.getGoodsId());
		Goods db_goods = goodsService.View(goods.getGoodsId());
		this.goods = db_goods;
		System.out.println("doEdit要修改信息：" + goods);

		System.out.println("5656" + goods.getSort());
		Sort db_sort = sortService.FindById(db_goods.getSort().getSortId());
		System.out.println("565" + db_sort.getSort());

		if (db_goods != null) {
			ActionContext ctx = ActionContext.getContext();
			ctx.put("sortlist", sortlist);
			ctx.put("companylist", companylist);
			ctx.put("goods", goods);
			ctx.put("sort", db_sort);

			return "goodsedit_view";
		} else {
			ActionContext.getContext().put("Msg", goodsService.getMsg());
			return "systemerror_view";
		}
	}

	public String doUpdate() throws Exception {
		System.out.println("doUpdate要修改的信息:" + goods);
		System.out.println("doUpdate要修改的信息:" + sortid);

		Sort sort = sortService.FindById(sortid);
		goods.setSort(sort);

		Goods db_goods = goodsService.Update(goods);

		System.out.println("121212" + db_goods.getSort());
		Sort db_sort = sortService.FindById(db_goods.getSort().getSortId());
		System.out.println("121212" + db_sort.getSort());

		this.goods = db_goods;
		if (db_goods != null) {

			ActionContext ctx = ActionContext.getContext();
			ctx.put("goods", goods);
			ctx.put("sort", db_sort);
			System.out.println("doUpdate修改后信息:" + goods);
			return "goodsinfo_view";
		} else {
			ActionContext.getContext().put("Msg", goodsService.getMsg());
			return "systemerror_view";
		}
	}

	public GoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getGoodsStartDate() {
		return goodsStartDate;
	}

	public void setGoodsStartDate(String goodsStartDate) {
		this.goodsStartDate = goodsStartDate;
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

	public int getSortid() {
		return sortid;
	}

	public void setSortid(int sortid) {
		this.sortid = sortid;
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

	public int getProducerid() {
		return producerid;
	}

	public void setProducerid(int producerid) {
		this.producerid = producerid;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

	public String getDeletelist() {
		return deletelist;
	}

	public void setDeletelist(String deletelist) {
		this.deletelist = deletelist;
	}

}
