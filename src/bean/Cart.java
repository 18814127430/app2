package bean;

/**
 * Cart entity. @author MyEclipse Persistence Tools
 */

public class Cart implements java.io.Serializable {

	// Fields

	private Integer cartId;
	private Goods goods;
	private Customer customer;
	private Integer goodsNum;
	private Double moneyDeliver;
	private Double moneyTotal;
	private String cartDate;

	// Constructors

	/** default constructor */
	public Cart() {
	}

	/** full constructor */
	public Cart(Goods goods, Customer customer, Integer goodsNum, Double moneyDeliver, Double moneyTotal, String cartDate) {
		this.goods = goods;
		this.customer = customer;
		this.goodsNum = goodsNum;
		this.moneyDeliver = moneyDeliver;
		this.moneyTotal = moneyTotal;
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

	public Double getMoneyDeliver() {
		return this.moneyDeliver;
	}

	public void setMoneyDeliver(Double moneyDeliver) {
		this.moneyDeliver = moneyDeliver;
	}

	public Double getMoneyTotal() {
		return this.moneyTotal;
	}

	public void setMoneyTotal(Double moneyTotal) {
		this.moneyTotal = moneyTotal;
	}

	public String getCartDate() {
		return this.cartDate;
	}

	public void setCartDate(String cartDate) {
		this.cartDate = cartDate;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", goods=" + goods + ", customer=" + customer + ", goodsNum=" + goodsNum + ", moneyDeliver=" + moneyDeliver
				+ ", moneyTotal=" + moneyTotal + ", cartDate=" + cartDate + "]";
	}

}