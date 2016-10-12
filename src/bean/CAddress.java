package bean;

import java.sql.Timestamp;

/**
 * CAddress entity. @author MyEclipse Persistence Tools
 */

public class CAddress implements java.io.Serializable {

	// Fields

	private Integer addressId;
	private Customer customer;
	private String addressName;
	private String addressPhone;
	private String addressProvince;
	private String addressCity;
	private String addressStreet;
	private String addressDetial;
	private Timestamp addressDate;

	// Constructors

	/** default constructor */
	public CAddress() {
	}

	/** full constructor */
	public CAddress(Customer customer, String addressName, String addressPhone, String addressProvince, String addressCity, String addressStreet,
			String addressDetial, Timestamp addressDate) {
		this.customer = customer;
		this.addressName = addressName;
		this.addressPhone = addressPhone;
		this.addressProvince = addressProvince;
		this.addressCity = addressCity;
		this.addressStreet = addressStreet;
		this.addressDetial = addressDetial;
		this.addressDate = addressDate;
	}

	// Property accessors

	public Integer getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getAddressName() {
		return this.addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressPhone() {
		return this.addressPhone;
	}

	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}

	public String getAddressProvince() {
		return this.addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}

	public String getAddressCity() {
		return this.addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressStreet() {
		return this.addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressDetial() {
		return this.addressDetial;
	}

	public void setAddressDetial(String addressDetial) {
		this.addressDetial = addressDetial;
	}

	public Timestamp getAddressDate() {
		return this.addressDate;
	}

	public void setAddressDate(Timestamp addressDate) {
		this.addressDate = addressDate;
	}

	@Override
	public String toString() {
		return "CAddress [addressId=" + addressId + ", addressName=" + addressName + ", addressPhone=" + addressPhone + ", addressProvince="
				+ addressProvince + ", addressCity=" + addressCity + ", addressStreet=" + addressStreet + ", addressDetial=" + addressDetial
				+ ", addressDate=" + addressDate + "]";
	}

}