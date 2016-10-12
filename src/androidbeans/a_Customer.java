package androidbeans;

import java.util.HashSet;
import java.util.Set;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */

public class a_Customer implements java.io.Serializable {

	private Integer customerId;
	private String customerToken;

	public a_Customer(Integer customerId, String customerToken) {
		super();
		this.customerId = customerId;
		this.customerToken = customerToken;
	}

	public a_Customer() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerToken() {
		return customerToken;
	}

	public void setCustomerToken(String customerToken) {
		this.customerToken = customerToken;
	}

	@Override
	public String toString() {
		return "a_Customer [customerId=" + customerId + ", customerToken=" + customerToken + "]";
	}

}