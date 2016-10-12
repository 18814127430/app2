package bean;

/**
 * Collect entity. @author MyEclipse Persistence Tools
 */

public class Collect implements java.io.Serializable {

	// Fields

	private Integer collectId;
	private Goods goods;
	private Customer customer;
	private String collectDate;

	// Constructors

	/** default constructor */
	public Collect() {
	}

	/** full constructor */
	public Collect(Goods goods, Customer customer, String collectDate) {
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

	public String getCollectDate() {
		return this.collectDate;
	}

	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}

}