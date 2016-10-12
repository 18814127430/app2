package androidbeans;

import java.util.HashSet;
import java.util.Set;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class a_Goods_1 implements java.io.Serializable {

	private Integer goodsId;
	private String goodsName;
	private Double goodPrice;
	private String goodWord;
	private String goodTag;
	private String img1;

	public a_Goods_1(Integer goodsId, String goodsName, String goodWord, Double goodPrice, String img1, String goodTag) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodWord = goodWord;
		this.goodPrice = goodPrice;
		this.img1 = img1;
		this.goodTag = goodTag;
	}

	public a_Goods_1() {
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

	public String getGoodWord() {
		return goodWord;
	}

	public void setGoodWord(String goodWord) {
		this.goodWord = goodWord;
	}

	public String getGoodTag() {
		return goodTag;
	}

	public void setGoodTag(String goodTag) {
		this.goodTag = goodTag;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	@Override
	public String toString() {
		return "a_Goods_1 [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodPrice=" + goodPrice + ", goodWord=" + goodWord + ", goodTag="
				+ goodTag + ", img1=" + img1 + "]";
	}

}