package androidbeans;

import java.util.HashSet;
import java.util.Set;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class a_Goods_2 implements java.io.Serializable {

	private Integer goodsId;
	private String goodsName;
	private Double goodPrice;
	private String img1;
	private String img2;
	private String img3;
	private String img4;
	private String goodsSize1;
	private String goodsRemark;//描述
	private String goodsHtmlUrl;

	public a_Goods_2(Integer goodsId, String goodsName, Double goodPrice, String img1, String img2, String img3, String img4, String goodsSize1,
			String goodsRemark, String goodsHtmlUrl) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodPrice = goodPrice;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.goodsSize1 = goodsSize1;
		this.goodsRemark = goodsRemark;
		this.goodsHtmlUrl = goodsHtmlUrl;
	}

	public a_Goods_2() {
		// TODO Auto-generated constructor stub
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(Double goodPrice) {
		this.goodPrice = goodPrice;
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

	public String getGoodsSize1() {
		return goodsSize1;
	}

	public void setGoodsSize1(String goodsSize1) {
		this.goodsSize1 = goodsSize1;
	}

	public String getGoodsRemark() {
		return goodsRemark;
	}

	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}

	public String getGoodsHtmlUrl() {
		return goodsHtmlUrl;
	}

	public void setGoodsHtmlUrl(String goodsHtmlUrl) {
		this.goodsHtmlUrl = goodsHtmlUrl;
	}

	@Override
	public String toString() {
		return "a_Goods_2 [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodPrice=" + goodPrice + ", img1=" + img1 + ", img2=" + img2
				+ ", img3=" + img3 + ", img4=" + img4 + ", goodsSize1=" + goodsSize1 + ", goodsRemark=" + goodsRemark + ", goodsHtmlUrl="
				+ goodsHtmlUrl + "]";
	}

}