package androidbeans;

import java.util.HashSet;
import java.util.Set;

import bean.GBatch;

/**
 * OInfo entity. @author MyEclipse Persistence Tools
 */

public class a_OInfo implements java.io.Serializable {
	
	private Integer oinfoId;
	private String oinfoSerial;
	private Integer batchId;
	private Integer goodsId;
	private Integer orderId;
	private String goodsName;
	private Integer goodsNum;
	private Double goodsPrice;
	private Double totalMoney;
	private Double deliverMoney;
	private Integer deliverFree;
	private Integer statusComment;//只能为1或2，1是未评论，2是以评论
	private String pic_item_good;/////////////////新增
	
	public a_OInfo(Integer oinfoId, String oinfoSerial, Integer batchId, Integer goodsId, Integer orderId,
			String goodsName, Integer goodsNum, Double goodsPrice, Double totalMoney, Double deliverMoney,
			Integer deliverFree, Integer statusComment) {
		super();
		this.oinfoId = oinfoId;
		this.oinfoSerial = oinfoSerial;
		this.batchId = batchId;
		this.goodsId = goodsId;
		this.orderId = orderId;
		this.goodsName = goodsName;
		this.goodsNum = goodsNum;
		this.goodsPrice = goodsPrice;
		this.totalMoney = totalMoney;
		this.deliverMoney = deliverMoney;
		this.deliverFree = deliverFree;
		this.statusComment = statusComment;
	}
	
	public a_OInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getOinfoId() {
		return oinfoId;
	}
	
	public void setOinfoId(Integer oinfoId) {
		this.oinfoId = oinfoId;
	}
	
	public String getOinfoSerial() {
		return oinfoSerial;
	}
	
	public void setOinfoSerial(String oinfoSerial) {
		this.oinfoSerial = oinfoSerial;
	}
	
	public Integer getBatchId() {
		return batchId;
	}
	
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	
	public Integer getGoodsId() {
		return goodsId;
	}
	
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public String getGoodsName() {
		return goodsName;
	}
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public Integer getGoodsNum() {
		return goodsNum;
	}
	
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	public Double getTotalMoney() {
		return totalMoney;
	}
	
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	public Double getDeliverMoney() {
		return deliverMoney;
	}
	
	public void setDeliverMoney(Double deliverMoney) {
		this.deliverMoney = deliverMoney;
	}
	
	public Integer getDeliverFree() {
		return deliverFree;
	}
	
	public void setDeliverFree(Integer deliverFree) {
		this.deliverFree = deliverFree;
	}
	
	public Integer getStatusComment() {
		return statusComment;
	}
	
	public void setStatusComment(Integer statusComment) {
		this.statusComment = statusComment;
	}
	
	@Override
	public String toString() {
		return "a_OInfo [oinfoId=" + oinfoId + ", oinfoSerial=" + oinfoSerial + ", batchId=" + batchId + ", goodsId="
				+ goodsId + ", orderId=" + orderId + ", goodsName=" + goodsName + ", goodsNum=" + goodsNum
				+ ", goodsPrice=" + goodsPrice + ", totalMoney=" + totalMoney + ", deliverMoney=" + deliverMoney
				+ ", deliverFree=" + deliverFree + ", statusComment=" + statusComment + "]";
	}
	
	public String getPic_item_good() {
		return pic_item_good;
	}
	
	public void setPic_item_good(String pic_item_good) {
		this.pic_item_good = pic_item_good;
	}
	
}
