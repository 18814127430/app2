package androidbeans;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import bean.Customer;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class a_OOrder implements java.io.Serializable {
	
	private Integer orderId;
	private Integer customerId;
	private String orderSerial;
	private String orderRemark;
	private String orderDate;
	private Double moneyTotal;
	private Double moneyDeliver;
	private String paySerial;
	private String payMethod;//支付方式
	private String payDate;
	private String adrName;
	private String adrPhone;
	private String adrProvince;
	private String adrCity;
	private String adrStreet;
	private String adrDetial;
	private Integer orderType;
	private String deliverTime;//配送时间
	private String billMemo;//发票
	
	public a_OOrder(Integer orderId, Integer customerId, String orderSerial, String orderRemark, String orderDate,
			Double moneyTotal, Double moneyDeliver, String paySerial, String payMethod, String payDate, String adrName,
			String adrPhone, String adrProvince, String adrCity, String adrStreet, String adrDetial, String deliverTime,
			String billMemo) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderSerial = orderSerial;
		this.orderRemark = orderRemark;
		this.orderDate = orderDate;
		this.moneyTotal = moneyTotal;
		this.moneyDeliver = moneyDeliver;
		this.paySerial = paySerial;
		this.payMethod = payMethod;
		this.payDate = payDate;
		this.adrName = adrName;
		this.adrPhone = adrPhone;
		this.adrProvince = adrProvince;
		this.adrCity = adrCity;
		this.adrStreet = adrStreet;
		this.adrDetial = adrDetial;
		this.deliverTime = deliverTime;
		this.billMemo = billMemo;
	}
	
	public a_OOrder() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public String getOrderSerial() {
		return orderSerial;
	}
	
	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}
	
	public String getOrderRemark() {
		return orderRemark;
	}
	
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public Double getMoneyTotal() {
		return moneyTotal;
	}
	
	public void setMoneyTotal(Double moneyTotal) {
		this.moneyTotal = moneyTotal;
	}
	
	public Double getMoneyDeliver() {
		return moneyDeliver;
	}
	
	public void setMoneyDeliver(Double moneyDeliver) {
		this.moneyDeliver = moneyDeliver;
	}
	
	public String getPaySerial() {
		return paySerial;
	}
	
	public void setPaySerial(String paySerial) {
		this.paySerial = paySerial;
	}
	
	public String getPayMethod() {
		return payMethod;
	}
	
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	public String getPayDate() {
		return payDate;
	}
	
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	
	public String getAdrName() {
		return adrName;
	}
	
	public void setAdrName(String adrName) {
		this.adrName = adrName;
	}
	
	public String getAdrPhone() {
		return adrPhone;
	}
	
	public void setAdrPhone(String adrPhone) {
		this.adrPhone = adrPhone;
	}
	
	public String getAdrProvince() {
		return adrProvince;
	}
	
	public void setAdrProvince(String adrProvince) {
		this.adrProvince = adrProvince;
	}
	
	public String getAdrCity() {
		return adrCity;
	}
	
	public void setAdrCity(String adrCity) {
		this.adrCity = adrCity;
	}
	
	public String getAdrStreet() {
		return adrStreet;
	}
	
	public void setAdrStreet(String adrStreet) {
		this.adrStreet = adrStreet;
	}
	
	public String getAdrDetial() {
		return adrDetial;
	}
	
	public void setAdrDetial(String adrDetial) {
		this.adrDetial = adrDetial;
	}
	
	public String getDeliverTime() {
		return deliverTime;
	}
	
	public void setDeliverTime(String deliverTime) {
		this.deliverTime = deliverTime;
	}
	
	public String getBillMemo() {
		return billMemo;
	}
	
	public void setBillMemo(String billMemo) {
		this.billMemo = billMemo;
	}
	
	public Integer getOrderType() {
		return orderType;
	}
	
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	@Override
	public String toString() {
		return "a_OOrder [orderId=" + orderId + ", customerId=" + customerId + ", orderSerial=" + orderSerial
				+ ", orderRemark=" + orderRemark + ", orderDate=" + orderDate + ", moneyTotal=" + moneyTotal
				+ ", moneyDeliver=" + moneyDeliver + ", paySerial=" + paySerial + ", payMethod=" + payMethod + ", payDate="
				+ payDate + ", adrName=" + adrName + ", adrPhone=" + adrPhone + ", adrProvince=" + adrProvince
				+ ", adrCity=" + adrCity + ", adrStreet=" + adrStreet + ", adrDetial=" + adrDetial + ", orderType="
				+ orderType + ", deliverTime=" + deliverTime + ", billMemo=" + billMemo + "]";
	}
	
}
