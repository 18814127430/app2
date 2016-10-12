package androidbeans;

import bean.Goods;

/**
 * Page entity. @author MyEclipse Persistence Tools
 */

public class a_Page implements java.io.Serializable {
	
	// Fields
	
	private Integer pageId;
	private Integer goodsId;
	private String pageImg;
	private String pageHtml;
	
	public a_Page(Integer pageId, Integer goodsId, String pageImg, String pageHtml) {
		super();
		this.pageId = pageId;
		this.goodsId = goodsId;
		this.pageImg = pageImg;
		this.pageHtml = pageHtml;
	}
	
	public a_Page() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getPageId() {
		return pageId;
	}
	
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	
	public Integer getGoodsId() {
		return goodsId;
	}
	
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	public String getPageImg() {
		return pageImg;
	}
	
	public void setPageImg(String pageImg) {
		this.pageImg = pageImg;
	}
	
	public String getPageHtml() {
		return pageHtml;
	}
	
	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}
	
	@Override
	public String toString() {
		return "a_Page [pageId=" + pageId + ", goodsId=" + goodsId + ", pageImg=" + pageImg + ", pageHtml=" + pageHtml
				+ "]";
	}
	
}
