package action;

import java.util.ArrayList;
import java.util.List;

import service.GoodsService;
import service.PageService;
import utils.goodsTool;
import utils.msg;
import utils.pageTool;
import utils.test;
import bean.Goods;
import bean.Page;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PageAction extends ActionSupport {
	
	private PageService pageService;
	private GoodsService goodsService;
	private String img1;
	private String img2;
	private String img3;
	private String img4;
	private int goodsid1;
	private int goodsid2;
	private int goodsid3;
	private int goodsid4;
	private final int Carousel_figure = 4;
	
	public String beforedoAdd() throws Exception {
		List list = pageService.Find_All();
		test.a("list.size:" + list.size());
		if (list.size() == 4) {
			ActionContext.getContext().put("Msg", msg.page_pageexist);
			return "systemerror_view";
		}
		else {
			return "pageadd_view";
		}
	}
	
	public String doEdit1() throws Exception {
		List list = goodsService.Find_Rand(0, "", 0, 4);
		List list1 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Goods goods = (Goods) list.get(i);
			Page db_page = new Page(i + 1, goods, goods.getImg1(), goods.getGoodsHtmlUrl());
			test.a(db_page);
			db_page = pageService.Update(db_page);
			
			list1.add(db_page);
		}
		if (list1.size() == 4) {
			ActionContext.getContext().put("list", list1);
			return "pageinfo_view";
		}
		else {
			ActionContext.getContext().put("Msg", pageService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doEdit2() throws Exception {
		List list = pageService.Find_All();
		test.a("list.size:" + list.size());
		if (list.size() == 4) {
			ActionContext.getContext().put("list", list);
			return "pageedit2_view";
		}
		else {
			ActionContext.getContext().put("Msg", pageService.getMsg());
			return "systemerror_view";
		}
	}
	
	public String doUpdate() throws Exception {
		List list = new ArrayList();
		test.a("img:" + img1);
		test.a("img:" + img2);
		test.a("img:" + img3);
		test.a("img:" + img4);
		test.a("goodsid:" + goodsid1);
		test.a("goodsid:" + goodsid2);
		test.a("goodsid:" + goodsid3);
		test.a("goodsid:" + goodsid4);
		
		Goods goods1 = goodsService.View(goodsid1);
		if (goods1 == null || img1 == null || img1.equals("")) {
			ActionContext.getContext().put("Msg", pageService.getMsg());
			return "systemerror_view";
		}
		Page page1 = new Page(1, goods1, img1, goods1.getGoodsHtmlUrl());
		page1 = pageTool.SetPageImgSize(page1);
		
		Page db_page1 = pageService.Update(page1);
		test.a("doAdd后信息:" + db_page1);
		if (db_page1 == null) {
			pageTool.DeleteImg_New(page1);
			ActionContext.getContext().put("Msg", pageService.getMsg());
			return "systemerror_view";
		}
		pageTool.DeleteImg_Old(page1, db_page1);
		list.add(db_page1);
		
		Goods goods2 = goodsService.View(goodsid2);
		if (goods2 == null || img2 == null || img2.equals("")) {
			ActionContext.getContext().put("Msg", pageService.getMsg());
			return "systemerror_view";
		}
		Page page2 = new Page(2, goods2, img2, goods2.getGoodsHtmlUrl());
		page2 = pageTool.SetPageImgSize(page2);
		
		Page db_page2 = pageService.Update(page2);
		test.a("doAdd后信息:" + db_page2);
		if (db_page2 == null) {
			pageTool.DeleteImg_New(page2);
			ActionContext.getContext().put("Msg", pageService.getMsg());
			return "systemerror_view";
		}
		pageTool.DeleteImg_Old(page2, db_page2);
		list.add(db_page2);
		
		Goods goods3 = goodsService.View(goodsid3);
		if (goods3 == null || img3 == null || img3.equals("")) {
			ActionContext.getContext().put("Msg", pageService.getMsg());
			return "systemerror_view";
		}
		Page page3 = new Page(3, goods3, img3, goods3.getGoodsHtmlUrl());
		page3 = pageTool.SetPageImgSize(page3);
		
		Page db_page3 = pageService.Update(page3);
		test.a("doAdd后信息:" + db_page3);
		if (db_page3 == null) {
			pageTool.DeleteImg_New(page3);
			ActionContext.getContext().put("Msg", pageService.getMsg());
			return "systemerror_view";
		}
		pageTool.DeleteImg_Old(page3, db_page3);
		list.add(db_page3);
		
		Goods goods4 = goodsService.View(goodsid4);
		if (goods4 == null || img4 == null || img4.equals("")) {
			ActionContext.getContext().put("Msg", pageService.getMsg());
			return "systemerror_view";
		}
		Page page4 = new Page(4, goods4, img4, goods4.getGoodsHtmlUrl());
		page4 = pageTool.SetPageImgSize(page4);
		
		Page db_page4 = pageService.Update(page4);
		test.a("doAdd后信息:" + db_page4);
		if (db_page4 == null) {
			pageTool.DeleteImg_New(page4);
			ActionContext.getContext().put("Msg", pageService.getMsg());
			return "systemerror_view";
		}
		pageTool.DeleteImg_Old(page4, db_page4);
		list.add(db_page4);
		
		for (int i = 0; i < list.size(); i++)
			test.a("newimg:" + ((Page) list.get(i)).getPageImg());
		
		ActionContext.getContext().put("list", list);
		return "pageinfo_view";
	}
	
	public String doView() throws Exception {
		List list = pageService.Find_All();
		if (list.size() > 0 && list.size() == 4) {
			ActionContext.getContext().put("list", list);
			return "pageinfo_view";
		}
		else {
			ActionContext.getContext().put("Msg", pageService.getMsg());
			return "systemerror_view";
		}
	}
	
	public PageService getPageService() {
		return pageService;
	}
	
	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}
	
	public int getCarousel_figure() {
		return Carousel_figure;
	}
	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	public String getImg1() {
		return img1;
	}
	
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	
	public String getImg2() {
		return img2;
	}
	
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	
	public String getImg3() {
		return img3;
	}
	
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	
	public String getImg4() {
		return img4;
	}
	
	public void setImg4(String img4) {
		this.img4 = img4;
	}
	
	public int getGoodsid1() {
		return goodsid1;
	}
	
	public void setGoodsid1(int goodsid1) {
		this.goodsid1 = goodsid1;
	}
	
	public int getGoodsid2() {
		return goodsid2;
	}
	
	public void setGoodsid2(int goodsid2) {
		this.goodsid2 = goodsid2;
	}
	
	public int getGoodsid3() {
		return goodsid3;
	}
	
	public void setGoodsid3(int goodsid3) {
		this.goodsid3 = goodsid3;
	}
	
	public int getGoodsid4() {
		return goodsid4;
	}
	
	public void setGoodsid4(int goodsid4) {
		this.goodsid4 = goodsid4;
	}
	
}
