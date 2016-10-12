package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * OInfo entity. @author MyEclipse Persistence Tools
 */

public class OInfo implements java.io.Serializable {

	// Fields

	private Integer ocId;
	private Goods goods;
	private GBatch GBatch;
	private Order order;
	private Integer ocNum;
	private Double ocMoneyDeliver;
	private Double ocMoneyTotal;
	private String ocDate;
	private Set comments = new HashSet(0);

	// Constructors

	/** default constructor */
	public OInfo() {
	}

	/** minimal constructor */
	public OInfo(Goods goods, GBatch GBatch, Order order, Integer ocNum, Double ocMoneyDeliver, Double ocMoneyTotal, String ocDate) {
		this.goods = goods;
		this.GBatch = GBatch;
		this.order = order;
		this.ocNum = ocNum;
		this.ocMoneyDeliver = ocMoneyDeliver;
		this.ocMoneyTotal = ocMoneyTotal;
		this.ocDate = ocDate;
	}

	/** full constructor */
	public OInfo(Goods goods, GBatch GBatch, Order order, Integer ocNum, Double ocMoneyDeliver, Double ocMoneyTotal, String ocDate, Set comments) {
		this.goods = goods;
		this.GBatch = GBatch;
		this.order = order;
		this.ocNum = ocNum;
		this.ocMoneyDeliver = ocMoneyDeliver;
		this.ocMoneyTotal = ocMoneyTotal;
		this.ocDate = ocDate;
		this.comments = comments;
	}

	// Property accessors

	public Integer getOcId() {
		return this.ocId;
	}

	public void setOcId(Integer ocId) {
		this.ocId = ocId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public GBatch getGBatch() {
		return this.GBatch;
	}

	public void setGBatch(GBatch GBatch) {
		this.GBatch = GBatch;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getOcNum() {
		return this.ocNum;
	}

	public void setOcNum(Integer ocNum) {
		this.ocNum = ocNum;
	}

	public Double getOcMoneyDeliver() {
		return this.ocMoneyDeliver;
	}

	public void setOcMoneyDeliver(Double ocMoneyDeliver) {
		this.ocMoneyDeliver = ocMoneyDeliver;
	}

	public Double getOcMoneyTotal() {
		return this.ocMoneyTotal;
	}

	public void setOcMoneyTotal(Double ocMoneyTotal) {
		this.ocMoneyTotal = ocMoneyTotal;
	}

	public String getOcDate() {
		return this.ocDate;
	}

	public void setOcDate(String ocDate) {
		this.ocDate = ocDate;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "OInfo [ocId=" + ocId + ", goods=" + goods + ", GBatch=" + GBatch + ", order=" + order + ", ocNum=" + ocNum + ", ocMoneyDeliver="
				+ ocMoneyDeliver + ", ocMoneyTotal=" + ocMoneyTotal + ", ocDate=" + ocDate + "]";
	}


}