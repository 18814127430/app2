package bean;

/**
 * Page entity. @author MyEclipse Persistence Tools
 */

public class Page implements java.io.Serializable {

	// Fields

	private Integer pageId;
	private Goods goods;
	private String pageImg;
	private String pageHtml;

	// Constructors

	/** default constructor */
	public Page() {
	}

	/** full constructor */
	public Page(Integer pageId, Goods goods, String pageImg, String pageHtml) {
		this.pageId = pageId;
		this.goods = goods;
		this.pageImg = pageImg;
		this.pageHtml = pageHtml;
	}

	// Property accessors

	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getPageImg() {
		return this.pageImg;
	}

	public void setPageImg(String pageImg) {
		this.pageImg = pageImg;
	}

	public String getPageHtml() {
		return this.pageHtml;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

	@Override
	public String toString() {
		return "Page [pageId=" + pageId + ", goods=" + goods.getGoodsId() + ", pageImg=" + pageImg + ", pageHtml=" + pageHtml + "]";
	}

}