package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */

public class Customer implements java.io.Serializable {

	// Fields

	private Integer customerId;
	private String customerPhone;
	private String customerPassword;
	private String customerMail;
	private String customerImg;
	private String customerCode;
	private String customerToken;
	private String customerArray;
	private Set collects = new HashSet(0);
	private Set carts = new HashSet(0);
	private Set CWordses = new HashSet(0);
	private Set orders = new HashSet(0);
	private Set CAddresses = new HashSet(0);
	private Set comments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** minimal constructor */
	public Customer(String customerPhone, String customerPassword, String customerMail, String customerImg, String customerCode, String customerToken) {
		this.customerPhone = customerPhone;
		this.customerPassword = customerPassword;
		this.customerMail = customerMail;
		this.customerImg = customerImg;
		this.customerCode = customerCode;
		this.customerToken = customerToken;
	}

	/** full constructor */
	public Customer(String customerPhone, String customerPassword, String customerMail, String customerImg, String customerCode,
			String customerToken, String customerArray, Set collects, Set carts, Set CWordses, Set orders, Set CAddresses, Set comments) {
		this.customerPhone = customerPhone;
		this.customerPassword = customerPassword;
		this.customerMail = customerMail;
		this.customerImg = customerImg;
		this.customerCode = customerCode;
		this.customerToken = customerToken;
		this.customerArray = customerArray;
		this.collects = collects;
		this.carts = carts;
		this.CWordses = CWordses;
		this.orders = orders;
		this.CAddresses = CAddresses;
		this.comments = comments;
	}

	// Property accessors

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPhone() {
		return this.customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerPassword() {
		return this.customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerMail() {
		return this.customerMail;
	}

	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}

	public String getCustomerImg() {
		return this.customerImg;
	}

	public void setCustomerImg(String customerImg) {
		this.customerImg = customerImg;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerToken() {
		return this.customerToken;
	}

	public void setCustomerToken(String customerToken) {
		this.customerToken = customerToken;
	}

	public String getCustomerArray() {
		return this.customerArray;
	}

	public void setCustomerArray(String customerArray) {
		this.customerArray = customerArray;
	}

	public Set getCollects() {
		return this.collects;
	}

	public void setCollects(Set collects) {
		this.collects = collects;
	}

	public Set getCarts() {
		return this.carts;
	}

	public void setCarts(Set carts) {
		this.carts = carts;
	}

	public Set getCWordses() {
		return this.CWordses;
	}

	public void setCWordses(Set CWordses) {
		this.CWordses = CWordses;
	}

	public Set getOrders() {
		return this.orders;
	}

	public void setOrders(Set orders) {
		this.orders = orders;
	}

	public Set getCAddresses() {
		return this.CAddresses;
	}

	public void setCAddresses(Set CAddresses) {
		this.CAddresses = CAddresses;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerPhone=" + customerPhone + ", customerPassword=" + customerPassword
				+ ", customerMail=" + customerMail + ", customerImg=" + customerImg + ", customerCode=" + customerCode + ", customerToken="
				+ customerToken + ", customerArray=" + customerArray + "]";
	}

}