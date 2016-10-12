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
	private String thirdType1;
	private String thirdAccount1;
	private String thirdType2;
	private String thirdAccount2;
	private Set collects = new HashSet(0);
	private Set orders = new HashSet(0);
	private Set carts = new HashSet(0);
	private Set CAddresses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** minimal constructor */
	public Customer(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	/** full constructor */
	public Customer(String customerPhone, String customerPassword, String customerMail, String customerImg, String customerCode,
			String customerToken, String customerArray, String thirdType1, String thirdAccount1, String thirdType2, String thirdAccount2,
			Set collects, Set orders, Set carts, Set CAddresses) {
		this.customerPhone = customerPhone;
		this.customerPassword = customerPassword;
		this.customerMail = customerMail;
		this.customerImg = customerImg;
		this.customerCode = customerCode;
		this.customerToken = customerToken;
		this.customerArray = customerArray;
		this.thirdType1 = thirdType1;
		this.thirdAccount1 = thirdAccount1;
		this.thirdType2 = thirdType2;
		this.thirdAccount2 = thirdAccount2;
		this.collects = collects;
		this.orders = orders;
		this.carts = carts;
		this.CAddresses = CAddresses;
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

	public String getThirdType1() {
		return this.thirdType1;
	}

	public void setThirdType1(String thirdType1) {
		this.thirdType1 = thirdType1;
	}

	public String getThirdAccount1() {
		return this.thirdAccount1;
	}

	public void setThirdAccount1(String thirdAccount1) {
		this.thirdAccount1 = thirdAccount1;
	}

	public String getThirdType2() {
		return this.thirdType2;
	}

	public void setThirdType2(String thirdType2) {
		this.thirdType2 = thirdType2;
	}

	public String getThirdAccount2() {
		return this.thirdAccount2;
	}

	public void setThirdAccount2(String thirdAccount2) {
		this.thirdAccount2 = thirdAccount2;
	}

	public Set getCollects() {
		return this.collects;
	}

	public void setCollects(Set collects) {
		this.collects = collects;
	}

	public Set getOrders() {
		return this.orders;
	}

	public void setOrders(Set orders) {
		this.orders = orders;
	}

	public Set getCarts() {
		return this.carts;
	}

	public void setCarts(Set carts) {
		this.carts = carts;
	}

	public Set getCAddresses() {
		return this.CAddresses;
	}

	public void setCAddresses(Set CAddresses) {
		this.CAddresses = CAddresses;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerPhone=" + customerPhone + ", customerPassword=" + customerPassword
				+ ", customerMail=" + customerMail + ", customerImg=" + customerImg + ", customerCode=" + customerCode + ", customerToken="
				+ customerToken + ", customerArray=" + customerArray + ", thirdType1=" + thirdType1 + ", thirdAccount1=" + thirdAccount1
				+ ", thirdType2=" + thirdType2 + ", thirdAccount2=" + thirdAccount2 + "]";
	}

}