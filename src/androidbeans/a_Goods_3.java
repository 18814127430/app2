package androidbeans;

import java.util.HashSet;
import java.util.Set;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class a_Goods_3 implements java.io.Serializable {

	private Integer goodsId;
	private String goodsName;
	private String goodsWeight;
	private String goodsRemark;
	private Integer goodsNumStock;
	private Double goodsMoneyRetail;
	private Double goodsMoneyDeliver;
	private String goodsSize1;
	private String goodsSize2;
	private String goodsUnits;
	private String goodsCheckDate;
	private String goodsCheckDepartment;
	private String goodsCheckSerial;
	private String goodsCheckResult;
	private String goodsAddDate;
	private String goodsHtmlUrl;

	public a_Goods_3(Integer goodsId, String goodsName, String goodsWeight, String goodsRemark, Integer goodsNumStock, Double goodsMoneyRetail,
			Double goodsMoneyDeliver, String goodsSize1, String goodsSize2, String goodsUnits, String goodsCheckDate, String goodsCheckDepartment,
			String goodsCheckSerial, String goodsCheckResult, String goodsAddDate, String goodsHtmlUrl) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsWeight = goodsWeight;
		this.goodsRemark = goodsRemark;
		this.goodsNumStock = goodsNumStock;
		this.goodsMoneyRetail = goodsMoneyRetail;
		this.goodsMoneyDeliver = goodsMoneyDeliver;
		this.goodsSize1 = goodsSize1;
		this.goodsSize2 = goodsSize2;
		this.goodsUnits = goodsUnits;
		this.goodsCheckDate = goodsCheckDate;
		this.goodsCheckDepartment = goodsCheckDepartment;
		this.goodsCheckSerial = goodsCheckSerial;
		this.goodsCheckResult = goodsCheckResult;
		this.goodsAddDate = goodsAddDate;
		this.goodsHtmlUrl = goodsHtmlUrl;
	}

	public a_Goods_3() {
		// TODO Auto-generated constructor stub
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public String getGoodsRemark() {
		return goodsRemark;
	}

	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}

	public Integer getGoodsNumStock() {
		return goodsNumStock;
	}

	public void setGoodsNumStock(Integer goodsNumStock) {
		this.goodsNumStock = goodsNumStock;
	}

	public Double getGoodsMoneyRetail() {
		return goodsMoneyRetail;
	}

	public void setGoodsMoneyRetail(Double goodsMoneyRetail) {
		this.goodsMoneyRetail = goodsMoneyRetail;
	}

	public Double getGoodsMoneyDeliver() {
		return goodsMoneyDeliver;
	}

	public void setGoodsMoneyDeliver(Double goodsMoneyDeliver) {
		this.goodsMoneyDeliver = goodsMoneyDeliver;
	}

	public String getGoodsSize1() {
		return goodsSize1;
	}

	public void setGoodsSize1(String goodsSize1) {
		this.goodsSize1 = goodsSize1;
	}

	public String getGoodsSize2() {
		return goodsSize2;
	}

	public void setGoodsSize2(String goodsSize2) {
		this.goodsSize2 = goodsSize2;
	}

	public String getGoodsUnits() {
		return goodsUnits;
	}

	public void setGoodsUnits(String goodsUnits) {
		this.goodsUnits = goodsUnits;
	}

	public String getGoodsCheckDate() {
		return goodsCheckDate;
	}

	public void setGoodsCheckDate(String goodsCheckDate) {
		this.goodsCheckDate = goodsCheckDate;
	}

	public String getGoodsCheckDepartment() {
		return goodsCheckDepartment;
	}

	public void setGoodsCheckDepartment(String goodsCheckDepartment) {
		this.goodsCheckDepartment = goodsCheckDepartment;
	}

	public String getGoodsCheckSerial() {
		return goodsCheckSerial;
	}

	public void setGoodsCheckSerial(String goodsCheckSerial) {
		this.goodsCheckSerial = goodsCheckSerial;
	}

	public String getGoodsCheckResult() {
		return goodsCheckResult;
	}

	public void setGoodsCheckResult(String goodsCheckResult) {
		this.goodsCheckResult = goodsCheckResult;
	}

	public String getGoodsAddDate() {
		return goodsAddDate;
	}

	public void setGoodsAddDate(String goodsAddDate) {
		this.goodsAddDate = goodsAddDate;
	}

	public String getGoodsHtmlUrl() {
		return goodsHtmlUrl;
	}

	public void setGoodsHtmlUrl(String goodsHtmlUrl) {
		this.goodsHtmlUrl = goodsHtmlUrl;
	}

	@Override
	public String toString() {
		return "a_Goods_3 [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsWeight=" + goodsWeight + ", goodsRemark=" + goodsRemark
				+ ", goodsNumStock=" + goodsNumStock + ", goodsMoneyRetail=" + goodsMoneyRetail + ", goodsMoneyDeliver=" + goodsMoneyDeliver
				+ ", goodsSize1=" + goodsSize1 + ", goodsSize2=" + goodsSize2 + ", goodsUnits=" + goodsUnits + ", goodsCheckDate=" + goodsCheckDate
				+ ", goodsCheckDepartment=" + goodsCheckDepartment + ", goodsCheckSerial=" + goodsCheckSerial + ", goodsCheckResult="
				+ goodsCheckResult + ", goodsAddDate=" + goodsAddDate + ", goodsHtmlUrl=" + goodsHtmlUrl + "]";
	}

}