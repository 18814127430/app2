package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

	// Fields

	private Integer goodsId;
	private Sort sort;
	private String goodsName;
	private String goodsKeyWord;
	private String goodsDescription;
	private String goodsWeight;
	private String goodsRemark;
	private Integer goodsNumTotal;
	private Integer goodsNumStock;
	private Double goodsMoneyEntry;
	private Double goodsMoneyRetail;
	private Double goodsMoneyDeliver;
	private String goodsSize1;
	private String goodsSize2;
	private String goodsUnits;
	private String goodsCheckDate;
	private String goodsCheckDepartment;
	private String goodsCheckSerial;
	private String goodsCheckResult;
	private String img1;
	private String img2;
	private String img3;
	private String img4;
	private String img5;
	private String img6;
	private String img7;
	private String img8;
	private String img9;
	private Set OInfos = new HashSet(0);
	private Set carts = new HashSet(0);
	private Set collects = new HashSet(0);
	private Set GBatchs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** minimal constructor */
	public Goods(Sort sort, String goodsName, String goodsKeyWord, String goodsWeight, String goodsRemark, Integer goodsNumTotal,
			Integer goodsNumStock, Double goodsMoneyEntry, Double goodsMoneyRetail, Double goodsMoneyDeliver, String goodsSize1, String goodsSize2,
			String goodsUnits, String goodsCheckDate, String goodsCheckDepartment, String goodsCheckSerial, String goodsCheckResult, String img1,
			String img2, String img3, String img4, String img5, String img6, String img7, String img8, String img9) {
		this.sort = sort;
		this.goodsName = goodsName;
		this.goodsKeyWord = goodsKeyWord;
		this.goodsWeight = goodsWeight;
		this.goodsRemark = goodsRemark;
		this.goodsNumTotal = goodsNumTotal;
		this.goodsNumStock = goodsNumStock;
		this.goodsMoneyEntry = goodsMoneyEntry;
		this.goodsMoneyRetail = goodsMoneyRetail;
		this.goodsMoneyDeliver = goodsMoneyDeliver;
		this.goodsSize1 = goodsSize1;
		this.goodsSize2 = goodsSize2;
		this.goodsUnits = goodsUnits;
		this.goodsCheckDate = goodsCheckDate;
		this.goodsCheckDepartment = goodsCheckDepartment;
		this.goodsCheckSerial = goodsCheckSerial;
		this.goodsCheckResult = goodsCheckResult;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
	}

	/** full constructor */
	public Goods(Sort sort, String goodsName, String goodsKeyWord, String goodsDescription, String goodsWeight, String goodsRemark,
			Integer goodsNumTotal, Integer goodsNumStock, Double goodsMoneyEntry, Double goodsMoneyRetail, Double goodsMoneyDeliver,
			String goodsSize1, String goodsSize2, String goodsUnits, String goodsCheckDate, String goodsCheckDepartment, String goodsCheckSerial,
			String goodsCheckResult, String img1, String img2, String img3, String img4, String img5, String img6, String img7, String img8,
			String img9, Set OInfos, Set carts, Set collects, Set GBatchs) {
		this.sort = sort;
		this.goodsName = goodsName;
		this.goodsKeyWord = goodsKeyWord;
		this.goodsDescription = goodsDescription;
		this.goodsWeight = goodsWeight;
		this.goodsRemark = goodsRemark;
		this.goodsNumTotal = goodsNumTotal;
		this.goodsNumStock = goodsNumStock;
		this.goodsMoneyEntry = goodsMoneyEntry;
		this.goodsMoneyRetail = goodsMoneyRetail;
		this.goodsMoneyDeliver = goodsMoneyDeliver;
		this.goodsSize1 = goodsSize1;
		this.goodsSize2 = goodsSize2;
		this.goodsUnits = goodsUnits;
		this.goodsCheckDate = goodsCheckDate;
		this.goodsCheckDepartment = goodsCheckDepartment;
		this.goodsCheckSerial = goodsCheckSerial;
		this.goodsCheckResult = goodsCheckResult;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.OInfos = OInfos;
		this.carts = carts;
		this.collects = collects;
		this.GBatchs = GBatchs;
	}

	// Property accessors

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Sort getSort() {
		return this.sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsKeyWord() {
		return this.goodsKeyWord;
	}

	public void setGoodsKeyWord(String goodsKeyWord) {
		this.goodsKeyWord = goodsKeyWord;
	}

	public String getGoodsDescription() {
		return this.goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public String getGoodsWeight() {
		return this.goodsWeight;
	}

	public void setGoodsWeight(String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public String getGoodsRemark() {
		return this.goodsRemark;
	}

	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}

	public Integer getGoodsNumTotal() {
		return this.goodsNumTotal;
	}

	public void setGoodsNumTotal(Integer goodsNumTotal) {
		this.goodsNumTotal = goodsNumTotal;
	}

	public Integer getGoodsNumStock() {
		return this.goodsNumStock;
	}

	public void setGoodsNumStock(Integer goodsNumStock) {
		this.goodsNumStock = goodsNumStock;
	}

	public Double getGoodsMoneyEntry() {
		return this.goodsMoneyEntry;
	}

	public void setGoodsMoneyEntry(Double goodsMoneyEntry) {
		this.goodsMoneyEntry = goodsMoneyEntry;
	}

	public Double getGoodsMoneyRetail() {
		return this.goodsMoneyRetail;
	}

	public void setGoodsMoneyRetail(Double goodsMoneyRetail) {
		this.goodsMoneyRetail = goodsMoneyRetail;
	}

	public Double getGoodsMoneyDeliver() {
		return this.goodsMoneyDeliver;
	}

	public void setGoodsMoneyDeliver(Double goodsMoneyDeliver) {
		this.goodsMoneyDeliver = goodsMoneyDeliver;
	}

	public String getGoodsSize1() {
		return this.goodsSize1;
	}

	public void setGoodsSize1(String goodsSize1) {
		this.goodsSize1 = goodsSize1;
	}

	public String getGoodsSize2() {
		return this.goodsSize2;
	}

	public void setGoodsSize2(String goodsSize2) {
		this.goodsSize2 = goodsSize2;
	}

	public String getGoodsUnits() {
		return this.goodsUnits;
	}

	public void setGoodsUnits(String goodsUnits) {
		this.goodsUnits = goodsUnits;
	}

	public String getGoodsCheckDate() {
		return this.goodsCheckDate;
	}

	public void setGoodsCheckDate(String goodsCheckDate) {
		this.goodsCheckDate = goodsCheckDate;
	}

	public String getGoodsCheckDepartment() {
		return this.goodsCheckDepartment;
	}

	public void setGoodsCheckDepartment(String goodsCheckDepartment) {
		this.goodsCheckDepartment = goodsCheckDepartment;
	}

	public String getGoodsCheckSerial() {
		return this.goodsCheckSerial;
	}

	public void setGoodsCheckSerial(String goodsCheckSerial) {
		this.goodsCheckSerial = goodsCheckSerial;
	}

	public String getGoodsCheckResult() {
		return this.goodsCheckResult;
	}

	public void setGoodsCheckResult(String goodsCheckResult) {
		this.goodsCheckResult = goodsCheckResult;
	}

	public String getImg1() {
		return this.img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return this.img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return this.img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public String getImg4() {
		return this.img4;
	}

	public void setImg4(String img4) {
		this.img4 = img4;
	}

	public String getImg5() {
		return this.img5;
	}

	public void setImg5(String img5) {
		this.img5 = img5;
	}

	public String getImg6() {
		return this.img6;
	}

	public void setImg6(String img6) {
		this.img6 = img6;
	}

	public String getImg7() {
		return this.img7;
	}

	public void setImg7(String img7) {
		this.img7 = img7;
	}

	public String getImg8() {
		return this.img8;
	}

	public void setImg8(String img8) {
		this.img8 = img8;
	}

	public String getImg9() {
		return this.img9;
	}

	public void setImg9(String img9) {
		this.img9 = img9;
	}

	public Set getOInfos() {
		return this.OInfos;
	}

	public void setOInfos(Set OInfos) {
		this.OInfos = OInfos;
	}

	public Set getCarts() {
		return this.carts;
	}

	public void setCarts(Set carts) {
		this.carts = carts;
	}

	public Set getCollects() {
		return this.collects;
	}

	public void setCollects(Set collects) {
		this.collects = collects;
	}

	public Set getGBatchs() {
		return this.GBatchs;
	}

	public void setGBatchs(Set GBatchs) {
		this.GBatchs = GBatchs;
	}

	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", sort=" + sort + ", goodsName=" + goodsName + ", goodsKeyWord=" + goodsKeyWord + ", goodsDescription="
				+ goodsDescription + ", goodsWeight=" + goodsWeight + ", goodsRemark=" + goodsRemark + ", goodsNumTotal=" + goodsNumTotal
				+ ", goodsNumStock=" + goodsNumStock + ", goodsMoneyEntry=" + goodsMoneyEntry + ", goodsMoneyRetail=" + goodsMoneyRetail
				+ ", goodsMoneyDeliver=" + goodsMoneyDeliver + ", goodsSize1=" + goodsSize1 + ", goodsSize2=" + goodsSize2 + ", goodsUnits="
				+ goodsUnits + ", goodsCheckDate=" + goodsCheckDate + ", goodsCheckDepartment=" + goodsCheckDepartment + ", goodsCheckSerial="
				+ goodsCheckSerial + ", goodsCheckResult=" + goodsCheckResult + ", img1=" + img1 + ", img2=" + img2 + ", img3=" + img3 + ", img4="
				+ img4 + ", img5=" + img5 + ", img6=" + img6 + ", img7=" + img7 + ", img8=" + img8 + ", img9=" + img9 + "]";
	}

}