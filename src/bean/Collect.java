package bean;

import java.sql.Timestamp;

/**
 * Collect entity. @author MyEclipse Persistence Tools
 */

public class Collect implements java.io.Serializable {

	// Fields

	private Integer collectId;
	private Goods goods;
	private Customer customer;
	private Timestamp collectDate;

	// Constructors

	/** default constructor */
	public Collect() {
	}

	/** full constructor */
	public Collect(Goods goods, Customer customer, Timestamp collectDate) {
		this.goods = goods;
		this.customer = customer;
		this.collectDate = collectDate;
	}

	// Property accessors

	public Integer getCollectId() {
		return this.collectId;
	}

	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
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

	public Timestamp getCollectDate() {
		return this.collectDate;
	}

	public void setCollectDate(Timestamp collectDate) {
		this.collectDate = collectDate;
	}

	@Override
	public String toString() {
		return "Collect [collectId=" + collectId + ", collectDate=" + collectDate + "]";
	}

}