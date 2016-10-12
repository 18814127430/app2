package androidbeans;

import java.sql.Timestamp;

/**
 * Cart entity. @author MyEclipse Persistence Tools
 */

public class a_Cart implements java.io.Serializable {

	private Integer customerId;
	private Integer cartId;
	private Integer goodsId;
	private String goodName;
	private String goodsImg;
	private String goodsRemark;
	private Integer goodsNum;
	private Double goodPrice;
	private Double moneyDeliver;

	public a_Cart(Integer customerId, Integer cartId, Integer goodsId, String goodName, String goodsImg, String goodsRemark, Integer goodsNum,
			Double goodPrice, Double moneyDeliver) {
		super();
		this.customerId = customerId;
		this.cartId = cartId;
		this.goodsId = goodsId;
		this.goodName = goodName;
		this.goodsImg = goodsImg;
		this.goodsRemark = goodsRemark;
		this.goodsNum = goodsNum;
		this.goodPrice = goodPrice;
		this.moneyDeliver = moneyDeliver;
	}

	public a_Cart() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public String getGoodsRemark() {
		return goodsRemark;
	}

	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Double getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(Double goodPrice) {
		this.goodPrice = goodPrice;
	}

	public Double getMoneyDeliver() {
		return moneyDeliver;
	}

	public void setMoneyDeliver(Double moneyDeliver) {
		this.moneyDeliver = moneyDeliver;
	}

	@Override
	public String toString() {
		return "a_Cart [customerId=" + customerId + ", cartId=" + cartId + ", goodsId=" + goodsId + ", goodName=" + goodName + ", goodsImg="
				+ goodsImg + ", goodsRemark=" + goodsRemark + ", goodsNum=" + goodsNum + ", goodPrice=" + goodPrice + ", moneyDeliver="
				+ moneyDeliver + "]";
	}

}
