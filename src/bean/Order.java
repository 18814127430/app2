package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private Customer customer;
	private String orderSerial;
	private Double orderMoneyDeliver;
	private Double orderMoneyTotal;
	private String orderRemark;
	private String orderStatus;
	private String orderDate;
	private String orderName;
	private String orderPhone;
	private String orderProvince;
	private String orderCity;
	private String orderStreet;
	private String orderDetial;
	private Set OInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Order() {
	}

	/** minimal constructor */
	public Order(Customer customer, String orderSerial, Double orderMoneyDeliver, Double orderMoneyTotal, String orderRemark, String orderStatus,
			String orderDate, String orderName, String orderPhone, String orderProvince, String orderCity, String orderStreet, String orderDetial) {
		this.customer = customer;
		this.orderSerial = orderSerial;
		this.orderMoneyDeliver = orderMoneyDeliver;
		this.orderMoneyTotal = orderMoneyTotal;
		this.orderRemark = orderRemark;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.orderName = orderName;
		this.orderPhone = orderPhone;
		this.orderProvince = orderProvince;
		this.orderCity = orderCity;
		this.orderStreet = orderStreet;
		this.orderDetial = orderDetial;
	}

	/** full constructor */
	public Order(Customer customer, String orderSerial, Double orderMoneyDeliver, Double orderMoneyTotal, String orderRemark, String orderStatus,
			String orderDate, String orderName, String orderPhone, String orderProvince, String orderCity, String orderStreet, String orderDetial,
			Set OInfos) {
		this.customer = customer;
		this.orderSerial = orderSerial;
		this.orderMoneyDeliver = orderMoneyDeliver;
		this.orderMoneyTotal = orderMoneyTotal;
		this.orderRemark = orderRemark;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.orderName = orderName;
		this.orderPhone = orderPhone;
		this.orderProvince = orderProvince;
		this.orderCity = orderCity;
		this.orderStreet = orderStreet;
		this.orderDetial = orderDetial;
		this.OInfos = OInfos;
	}

	// Property accessors

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderSerial() {
		return this.orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}

	public Double getOrderMoneyDeliver() {
		return this.orderMoneyDeliver;
	}

	public void setOrderMoneyDeliver(Double orderMoneyDeliver) {
		this.orderMoneyDeliver = orderMoneyDeliver;
	}

	public Double getOrderMoneyTotal() {
		return this.orderMoneyTotal;
	}

	public void setOrderMoneyTotal(Double orderMoneyTotal) {
		this.orderMoneyTotal = orderMoneyTotal;
	}

	public String getOrderRemark() {
		return this.orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderPhone() {
		return this.orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	public String getOrderProvince() {
		return this.orderProvince;
	}

	public void setOrderProvince(String orderProvince) {
		this.orderProvince = orderProvince;
	}

	public String getOrderCity() {
		return this.orderCity;
	}

	public void setOrderCity(String orderCity) {
		this.orderCity = orderCity;
	}

	public String getOrderStreet() {
		return this.orderStreet;
	}

	public void setOrderStreet(String orderStreet) {
		this.orderStreet = orderStreet;
	}

	public String getOrderDetial() {
		return this.orderDetial;
	}

	public void setOrderDetial(String orderDetial) {
		this.orderDetial = orderDetial;
	}

	public Set getOInfos() {
		return this.OInfos;
	}

	public void setOInfos(Set OInfos) {
		this.OInfos = OInfos;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer + ", orderSerial=" + orderSerial + ", orderMoneyDeliver=" + orderMoneyDeliver
				+ ", orderMoneyTotal=" + orderMoneyTotal + ", orderRemark=" + orderRemark + ", orderStatus=" + orderStatus + ", orderDate="
				+ orderDate + ", orderName=" + orderName + ", orderPhone=" + orderPhone + ", orderProvince=" + orderProvince + ", orderCity="
				+ orderCity + ", orderStreet=" + orderStreet + ", orderDetial=" + orderDetial + "]";
	}


}