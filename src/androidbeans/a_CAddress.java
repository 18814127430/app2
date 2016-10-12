package androidbeans;

import java.sql.Timestamp;

/**
 * CAddress entity. @author MyEclipse Persistence Tools
 */

public class a_CAddress implements java.io.Serializable {

	private Integer customerId;
	private Integer addressId;
	private String addressName;
	private String addressPhone;
	private String addressProvince;
	private String addressCity;
	private String addressStreet;
	private String addressDetial;

	public a_CAddress(Integer customerId, Integer addressId, String addressName, String addressPhone, String addressProvince, String addressCity,
			String addressStreet, String addressDetial) {
		super();
		this.customerId = customerId;
		this.addressId = addressId;
		this.addressName = addressName;
		this.addressPhone = addressPhone;
		this.addressProvince = addressProvince;
		this.addressCity = addressCity;
		this.addressStreet = addressStreet;
		this.addressDetial = addressDetial;
	}

	public a_CAddress() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressPhone() {
		return addressPhone;
	}

	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}

	public String getAddressProvince() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressDetial() {
		return addressDetial;
	}

	public void setAddressDetial(String addressDetial) {
		this.addressDetial = addressDetial;
	}

	@Override
	public String toString() {
		return "a_CAddress [customerId=" + customerId + ", addressId=" + addressId + ", addressName=" + addressName + ", addressPhone="
				+ addressPhone + ", addressProvince=" + addressProvince + ", addressCity=" + addressCity + ", addressStreet=" + addressStreet
				+ ", addressDetial=" + addressDetial + "]";
	}

}
