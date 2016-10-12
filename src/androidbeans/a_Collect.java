package androidbeans;

import java.sql.Timestamp;

/**
 * Collect entity. @author MyEclipse Persistence Tools
 */

public class a_Collect implements java.io.Serializable {

	private Integer collectId;
	private Integer customerId;
	private Integer goodsId;
	private String goodImg;
	private String goodName;
	private String goodWord;
	private Double goodPrice;

	public a_Collect(Integer collectId, Integer customerId, Integer goodsId, String goodImg, String goodName, String goodWord, Double goodPrice) {
		super();
		this.collectId = collectId;
		this.customerId = customerId;
		this.goodsId = goodsId;
		this.goodImg = goodImg;
		this.goodName = goodName;
		this.goodWord = goodWord;
		this.goodPrice = goodPrice;
	}

	public a_Collect() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCollectId() {
		return collectId;
	}

	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodImg() {
		return goodImg;
	}

	public void setGoodImg(String goodImg) {
		this.goodImg = goodImg;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getGoodWord() {
		return goodWord;
	}

	public void setGoodWord(String goodWord) {
		this.goodWord = goodWord;
	}

	public Double getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(Double goodPrice) {
		this.goodPrice = goodPrice;
	}

	@Override
	public String toString() {
		return "a_Collect [collectId=" + collectId + ", customerId=" + customerId + ", goodsId=" + goodsId + ", goodImg=" + goodImg + ", goodName="
				+ goodName + ", goodWord=" + goodWord + ", goodPrice=" + goodPrice + "]";
	}

}
