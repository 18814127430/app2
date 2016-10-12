package bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * OOrder entity. @author MyEclipse Persistence Tools
 */

public class OOrder implements java.io.Serializable {
	
	// Fields
	
	private Integer orderId;
	private Customer customer;
	private String orderSerial;
	private String orderSubject;
	private String orderBody;
	private String orderRemark;
	private Timestamp orderDate;
	private Double moneyTotal;
	private Double moneyDeliver;
	private String paySerial;
	private String payMethod;
	private Timestamp payDate;
	private String adrName;
	private String adrPhone;
	private String adrProvince;
	private String adrCity;
	private String adrStreet;
	private String adrDetial;
	private Integer statusPay;
	private Integer statusMethod;
	private Integer statusOrder;
	private Integer statusSend;
	private Integer statusRefund;
	private Integer numComment;
	private Integer numInfo;
	private String deliverTime;
	private String billMemo;
	
	// Constructors
	
	/** default constructor */
	public OOrder() {
	}
	
	/** minimal constructor */
	public OOrder(Customer customer, Integer statusPay, Integer statusMethod, Integer statusOrder, Integer statusSend,
			Integer statusRefund, Integer numInfo, Integer numComment) {
		this.customer = customer;
		this.statusPay = statusPay;
		this.statusMethod = statusMethod;
		this.statusOrder = statusOrder;
		this.statusSend = statusSend;
		this.statusRefund = statusRefund;
		this.numComment = numComment;
		this.numInfo = numInfo;
	}
	
	/** full constructor */
	public OOrder(Customer customer, String orderSerial, String orderSubject, String orderBody, String orderRemark,
			Timestamp orderDate, Double moneyTotal, Double moneyDeliver, String paySerial, String payMethod,
			Timestamp payDate, String adrName, String adrPhone, String adrProvince, String adrCity, String adrStreet,
			String adrDetial, Integer statusPay, Integer statusMethod, Integer statusOrder, Integer statusSend,
			Integer statusRefund, Integer numInfo, Integer numComment, String deliverTime, String billMemo) {
		this.customer = customer;
		this.orderSubject = orderSubject;
		this.orderBody = orderBody;
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
		this.statusPay = statusPay;
		this.statusMethod = statusMethod;
		this.statusOrder = statusOrder;
		this.statusSend = statusSend;
		this.statusRefund = statusRefund;
		this.numComment = numComment;
		this.numInfo = numInfo;
		this.deliverTime = deliverTime;
		this.billMemo = billMemo;
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
	
	public String getOrderRemark() {
		return this.orderRemark;
	}
	
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	
	public Timestamp getOrderDate() {
		return this.orderDate;
	}
	
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	
	public Double getMoneyTotal() {
		return this.moneyTotal;
	}
	
	public void setMoneyTotal(Double moneyTotal) {
		this.moneyTotal = moneyTotal;
	}
	
	public Double getMoneyDeliver() {
		return this.moneyDeliver;
	}
	
	public void setMoneyDeliver(Double moneyDeliver) {
		this.moneyDeliver = moneyDeliver;
	}
	
	public String getPaySerial() {
		return this.paySerial;
	}
	
	public void setPaySerial(String paySerial) {
		this.paySerial = paySerial;
	}
	
	public String getPayMethod() {
		return this.payMethod;
	}
	
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	public Timestamp getPayDate() {
		return this.payDate;
	}
	
	public void setPayDate(Timestamp payDate) {
		this.payDate = payDate;
	}
	
	public String getAdrName() {
		return this.adrName;
	}
	
	public void setAdrName(String adrName) {
		this.adrName = adrName;
	}
	
	public String getAdrPhone() {
		return this.adrPhone;
	}
	
	public void setAdrPhone(String adrPhone) {
		this.adrPhone = adrPhone;
	}
	
	public String getAdrProvince() {
		return this.adrProvince;
	}
	
	public void setAdrProvince(String adrProvince) {
		this.adrProvince = adrProvince;
	}
	
	public String getAdrCity() {
		return this.adrCity;
	}
	
	public void setAdrCity(String adrCity) {
		this.adrCity = adrCity;
	}
	
	public String getAdrStreet() {
		return this.adrStreet;
	}
	
	public void setAdrStreet(String adrStreet) {
		this.adrStreet = adrStreet;
	}
	
	public String getAdrDetial() {
		return this.adrDetial;
	}
	
	public void setAdrDetial(String adrDetial) {
		this.adrDetial = adrDetial;
	}
	
	public Integer getStatusPay() {
		return this.statusPay;
	}
	
	public void setStatusPay(Integer statusPay) {
		this.statusPay = statusPay;
	}
	
	public Integer getStatusMethod() {
		return this.statusMethod;
	}
	
	public void setStatusMethod(Integer statusMethod) {
		this.statusMethod = statusMethod;
	}
	
	public Integer getStatusOrder() {
		return this.statusOrder;
	}
	
	public void setStatusOrder(Integer statusOrder) {
		this.statusOrder = statusOrder;
	}
	
	public Integer getStatusSend() {
		return this.statusSend;
	}
	
	public void setStatusSend(Integer statusSend) {
		this.statusSend = statusSend;
	}
	
	public Integer getNumComment() {
		return numComment;
	}
	
	public void setNumComment(Integer numComment) {
		this.numComment = numComment;
	}
	
	public Integer getNumInfo() {
		return numInfo;
	}
	
	public void setNumInfo(Integer numInfo) {
		this.numInfo = numInfo;
	}
	
	public String getOrderSubject() {
		return orderSubject;
	}
	
	public void setOrderSubject(String orderSubject) {
		this.orderSubject = orderSubject;
	}
	
	public String getOrderBody() {
		return orderBody;
	}
	
	public void setOrderBody(String orderBody) {
		this.orderBody = orderBody;
	}
	
	public Integer getStatusRefund() {
		return statusRefund;
	}
	
	public void setStatusRefund(Integer statusRefund) {
		this.statusRefund = statusRefund;
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
	
	@Override
	public String toString() {
		return "OOrder [orderId=" + orderId + ", orderSerial=" + orderSerial + ", orderSubject=" + orderSubject
				+ ", orderBody=" + orderBody + ", orderRemark=" + orderRemark + ", orderDate=" + orderDate
				+ ", moneyTotal=" + moneyTotal + ", moneyDeliver=" + moneyDeliver + ", paySerial=" + paySerial
				+ ", payMethod=" + payMethod + ", payDate=" + payDate + ", adrName=" + adrName + ", adrPhone=" + adrPhone
				+ ", adrProvince=" + adrProvince + ", adrCity=" + adrCity + ", adrStreet=" + adrStreet + ", adrDetial="
				+ adrDetial + ", statusPay=" + statusPay + ", statusMethod=" + statusMethod + ", statusOrder="
				+ statusOrder + ", statusSend=" + statusSend + ", statusRefund=" + statusRefund + ", numComment="
				+ numComment + ", numInfo=" + numInfo + ", deliverTime=" + deliverTime + ", billMemo=" + billMemo + "]";
	}
	
}
