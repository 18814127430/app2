package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * OInfo entity. @author MyEclipse Persistence Tools
 */

public class OInfo implements java.io.Serializable {

	// Fields

	private Integer oinfoId;
	private GBatch GBatch;
	private OOrder OOrder;
	private String oinfoSerial;
	private Integer goodsNum;
	private Double goodsPrice;
	private Integer statusSend;
	private Integer statusRefund;
	private Integer statusComment;

	// Constructors

	/** default constructor */
	public OInfo() {
	}

	/** minimal constructor */
	public OInfo(GBatch GBatch, OOrder OOrder, Integer goodsNum, Double goodsPrice, Integer statusSend, Integer statusRefund, Integer statusComment) {
		this.GBatch = GBatch;
		this.OOrder = OOrder;
		this.goodsNum = goodsNum;
		this.goodsPrice = goodsPrice;
		this.statusSend = statusSend;
		this.statusRefund = statusRefund;
		this.statusComment = statusComment;
	}

	/** full constructor */
	public OInfo(GBatch GBatch, OOrder OOrder, String oinfoSerial, Integer goodsNum, Double goodsPrice, Integer statusSend, Integer statusRefund,
			Integer statusComment) {
		this.GBatch = GBatch;
		this.OOrder = OOrder;
		this.oinfoSerial = oinfoSerial;
		this.goodsNum = goodsNum;
		this.goodsPrice = goodsPrice;
		this.statusSend = statusSend;
		this.statusRefund = statusRefund;
		this.statusComment = statusComment;
	}

	// Property accessors

	public Integer getOinfoId() {
		return this.oinfoId;
	}

	public void setOinfoId(Integer oinfoId) {
		this.oinfoId = oinfoId;
	}

	public GBatch getGBatch() {
		return this.GBatch;
	}

	public void setGBatch(GBatch GBatch) {
		this.GBatch = GBatch;
	}

	public OOrder getOOrder() {
		return this.OOrder;
	}

	public void setOOrder(OOrder OOrder) {
		this.OOrder = OOrder;
	}

	public String getOinfoSerial() {
		return this.oinfoSerial;
	}

	public void setOinfoSerial(String oinfoSerial) {
		this.oinfoSerial = oinfoSerial;
	}

	public Integer getGoodsNum() {
		return this.goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Double getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Integer getStatusSend() {
		return this.statusSend;
	}

	public void setStatusSend(Integer statusSend) {
		this.statusSend = statusSend;
	}

	public Integer getStatusRefund() {
		return this.statusRefund;
	}

	public void setStatusRefund(Integer statusRefund) {
		this.statusRefund = statusRefund;
	}

	public Integer getStatusComment() {
		return this.statusComment;
	}

	public void setStatusComment(Integer statusComment) {
		this.statusComment = statusComment;
	}

	@Override
	public String toString() {
		return "OInfo [oinfoId=" + oinfoId + ", oinfoSerial=" + oinfoSerial + ", goodsNum=" + goodsNum + ", goodsPrice=" + goodsPrice
				+ ", statusSend=" + statusSend + ", statusRefund=" + statusRefund + ", statusComment=" + statusComment + "]";
	}

}