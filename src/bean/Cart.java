package bean;

import java.sql.Timestamp;

/**
 * Cart entity. @author MyEclipse Persistence Tools
 */

public class Cart implements java.io.Serializable {

	// Fields

	private Integer cartId;
	private Goods goods;
	private Customer customer;
	private Integer goodsNum;
	private Timestamp cartDate;

	// Constructors

	/** default constructor */
	public Cart() {
	}

	/** full constructor */
	public Cart(Goods goods, Customer customer, Integer goodsNum, Timestamp cartDate) {
		this.goods = goods;
		this.customer = customer;
		this.goodsNum = goodsNum;
		this.cartDate = cartDate;
	}

	// Property accessors

	public Integer getCartId() {
		return this.cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getGoodsNum() {
		return this.goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Timestamp getCartDate() {
		return this.cartDate;
	}

	public void setCartDate(Timestamp cartDate) {
		this.cartDate = cartDate;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", goods=" + goods.getGoodsId() + ", customer=" + customer.getCustomerId() + ", goodsNum=" + goodsNum + ", cartDate=" + cartDate + "]";
	}

}