package bean;

import java.sql.Timestamp;
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
	private String goodsTags;
	private String goodsRemark;
	private String goodsDescription;
	private Timestamp goodsAddDate;
	private String goodsHtmlUrl;
	private Integer numTotal;
	private Integer numStock;
	private Double moneyOld;
	private Double moneyNew;
	private Double moneyDeliver;
	private Double moneyLeast;
	private String goodsUnits;
	private String goodsSize1;
	private String goodsSize2;
	private String goodsWeight;
	private String checkDepartment;
	private String checkDate;
	private String checkSerial;
	private String checkResult;
	private String img1;
	private String img2;
	private String img3;
	private String img4;
	private String img5;
	private String img6;
	private String img7;
	private String img8;
	private String img9;
	private String img10;
	private String img11;
	private String img12;
	private String img13;
	private String img14;
	private String img15;
	private String img16;
	private Integer countCollect;
	private Integer countCart;
	private Integer countOrder;
	private Integer commentStar1;
	private Integer commentStar2;
	private Integer commentStar3;
	private Integer commentStar4;
	private Integer commentStar5;
	private Set pages = new HashSet(0);
	private Set carts = new HashSet(0);
	private Set collects = new HashSet(0);
	private Set GBatchs = new HashSet(0);
	
	// Constructors
	
	/** default constructor */
	public Goods() {
	}
	
	/** minimal constructor */
	public Goods(Sort sort, String goodsName, String goodsKeyWord, Integer numTotal, Integer numStock, Double moneyOld,
			Double moneyNew, Double moneyDeliver, Double moneyLeast, String goodsUnits) {
		this.sort = sort;
		this.goodsName = goodsName;
		this.goodsKeyWord = goodsKeyWord;
		this.numTotal = numTotal;
		this.numStock = numStock;
		this.moneyOld = moneyOld;
		this.moneyNew = moneyNew;
		this.moneyDeliver = moneyDeliver;
		this.moneyLeast = moneyLeast;
		this.goodsUnits = goodsUnits;
	}
	
	/** full constructor */
	public Goods(Sort sort, String goodsName, String goodsKeyWord, String goodsTags, String goodsRemark,
			String goodsDescription, Timestamp goodsAddDate, String goodsHtmlUrl, Integer numTotal, Integer numStock,
			Double moneyOld, Double moneyNew, Double moneyDeliver, Double moneyLeast, String goodsUnits, String goodsSize1,
			String goodsSize2, String goodsWeight, String checkDepartment, String checkDate, String checkSerial,
			String checkResult, String img1, String img2, String img3, String img4, String img5, String img6, String img7,
			String img8, String img9, String img10, String img11, String img12, String img13, String img14, String img15,
			String img16, Integer countCollect, Integer countCart, Integer countOrder, Integer commentStar1,
			Integer commentStar2, Integer commentStar3, Integer commentStar4, Integer commentStar5, Set pages, Set carts,
			Set collects, Set GBatchs) {
		this.sort = sort;
		this.goodsName = goodsName;
		this.goodsKeyWord = goodsKeyWord;
		this.goodsTags = goodsTags;
		this.goodsRemark = goodsRemark;
		this.goodsDescription = goodsDescription;
		this.goodsAddDate = goodsAddDate;
		this.goodsHtmlUrl = goodsHtmlUrl;
		this.numTotal = numTotal;
		this.numStock = numStock;
		this.moneyOld = moneyOld;
		this.moneyNew = moneyNew;
		this.moneyDeliver = moneyDeliver;
		this.moneyLeast = moneyLeast;
		this.goodsUnits = goodsUnits;
		this.goodsSize1 = goodsSize1;
		this.goodsSize2 = goodsSize2;
		this.goodsWeight = goodsWeight;
		this.checkDepartment = checkDepartment;
		this.checkDate = checkDate;
		this.checkSerial = checkSerial;
		this.checkResult = checkResult;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.countCollect = countCollect;
		this.countCart = countCart;
		this.countOrder = countOrder;
		this.commentStar1 = commentStar1;
		this.commentStar2 = commentStar2;
		this.commentStar3 = commentStar3;
		this.commentStar4 = commentStar4;
		this.commentStar5 = commentStar5;
		this.pages = pages;
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
	
	public String getGoodsTags() {
		return this.goodsTags;
	}
	
	public void setGoodsTags(String goodsTags) {
		this.goodsTags = goodsTags;
	}
	
	public String getGoodsRemark() {
		return this.goodsRemark;
	}
	
	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}
	
	public String getGoodsDescription() {
		return this.goodsDescription;
	}
	
	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}
	
	public Timestamp getGoodsAddDate() {
		return this.goodsAddDate;
	}
	
	public void setGoodsAddDate(Timestamp goodsAddDate) {
		this.goodsAddDate = goodsAddDate;
	}
	
	public String getGoodsHtmlUrl() {
		return this.goodsHtmlUrl;
	}
	
	public void setGoodsHtmlUrl(String goodsHtmlUrl) {
		this.goodsHtmlUrl = goodsHtmlUrl;
	}
	
	public Integer getNumTotal() {
		return this.numTotal;
	}
	
	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
	}
	
	public Integer getNumStock() {
		return this.numStock;
	}
	
	public void setNumStock(Integer numStock) {
		this.numStock = numStock;
	}
	
	public Double getMoneyOld() {
		return this.moneyOld;
	}
	
	public void setMoneyOld(Double moneyOld) {
		this.moneyOld = moneyOld;
	}
	
	public Double getMoneyNew() {
		return this.moneyNew;
	}
	
	public void setMoneyNew(Double moneyNew) {
		this.moneyNew = moneyNew;
	}
	
	public Double getMoneyDeliver() {
		return this.moneyDeliver;
	}
	
	public void setMoneyDeliver(Double moneyDeliver) {
		this.moneyDeliver = moneyDeliver;
	}
	
	public Double getMoneyLeast() {
		return this.moneyLeast;
	}
	
	public void setMoneyLeast(Double moneyLeast) {
		this.moneyLeast = moneyLeast;
	}
	
	public String getGoodsUnits() {
		return this.goodsUnits;
	}
	
	public void setGoodsUnits(String goodsUnits) {
		this.goodsUnits = goodsUnits;
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
	
	public String getGoodsWeight() {
		return this.goodsWeight;
	}
	
	public void setGoodsWeight(String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}
	
	public String getCheckDepartment() {
		return this.checkDepartment;
	}
	
	public void setCheckDepartment(String checkDepartment) {
		this.checkDepartment = checkDepartment;
	}
	
	public String getCheckDate() {
		return this.checkDate;
	}
	
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	
	public String getCheckSerial() {
		return this.checkSerial;
	}
	
	public void setCheckSerial(String checkSerial) {
		this.checkSerial = checkSerial;
	}
	
	public String getCheckResult() {
		return this.checkResult;
	}
	
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
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
	
	public String getImg10() {
		return this.img10;
	}
	
	public void setImg10(String img10) {
		this.img10 = img10;
	}
	
	public String getImg11() {
		return this.img11;
	}
	
	public void setImg11(String img11) {
		this.img11 = img11;
	}
	
	public String getImg12() {
		return this.img12;
	}
	
	public void setImg12(String img12) {
		this.img12 = img12;
	}
	
	public Integer getCountCollect() {
		return this.countCollect;
	}
	
	public void setCountCollect(Integer countCollect) {
		this.countCollect = countCollect;
	}
	
	public Integer getCountCart() {
		return this.countCart;
	}
	
	public void setCountCart(Integer countCart) {
		this.countCart = countCart;
	}
	
	public Integer getCountOrder() {
		return this.countOrder;
	}
	
	public void setCountOrder(Integer countOrder) {
		this.countOrder = countOrder;
	}
	
	public Integer getCommentStar1() {
		return this.commentStar1;
	}
	
	public void setCommentStar1(Integer commentStar1) {
		this.commentStar1 = commentStar1;
	}
	
	public Integer getCommentStar2() {
		return this.commentStar2;
	}
	
	public void setCommentStar2(Integer commentStar2) {
		this.commentStar2 = commentStar2;
	}
	
	public Integer getCommentStar3() {
		return this.commentStar3;
	}
	
	public void setCommentStar3(Integer commentStar3) {
		this.commentStar3 = commentStar3;
	}
	
	public Integer getCommentStar4() {
		return this.commentStar4;
	}
	
	public void setCommentStar4(Integer commentStar4) {
		this.commentStar4 = commentStar4;
	}
	
	public Integer getCommentStar5() {
		return this.commentStar5;
	}
	
	public void setCommentStar5(Integer commentStar5) {
		this.commentStar5 = commentStar5;
	}
	
	public Set getPages() {
		return this.pages;
	}
	
	public void setPages(Set pages) {
		this.pages = pages;
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
	
	public String getImg13() {
		return img13;
	}
	
	public void setImg13(String img13) {
		this.img13 = img13;
	}
	
	public String getImg14() {
		return img14;
	}
	
	public void setImg14(String img14) {
		this.img14 = img14;
	}
	
	public String getImg15() {
		return img15;
	}
	
	public void setImg15(String img15) {
		this.img15 = img15;
	}
	
	public String getImg16() {
		return img16;
	}
	
	public void setImg16(String img16) {
		this.img16 = img16;
	}
	
	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsKeyWord=" + goodsKeyWord + ", goodsTags="
				+ goodsTags + ", goodsRemark=" + goodsRemark + ", goodsDescription=" + goodsDescription + ", goodsAddDate="
				+ goodsAddDate + ", goodsHtmlUrl=" + goodsHtmlUrl + ", numTotal=" + numTotal + ", numStock=" + numStock
				+ ", moneyOld=" + moneyOld + ", moneyNew=" + moneyNew + ", moneyDeliver=" + moneyDeliver + ", moneyLeast="
				+ moneyLeast + ", goodsUnits=" + goodsUnits + ", goodsSize1=" + goodsSize1 + ", goodsSize2=" + goodsSize2
				+ ", goodsWeight=" + goodsWeight + ", checkDepartment=" + checkDepartment + ", checkDate=" + checkDate
				+ ", checkSerial=" + checkSerial + ", checkResult=" + checkResult + ", img1=" + img1 + ", img2=" + img2
				+ ", img3=" + img3 + ", img4=" + img4 + ", img5=" + img5 + ", img6=" + img6 + ", img7=" + img7 + ", img8="
				+ img8 + ", img9=" + img9 + ", img10=" + img10 + ", img11=" + img11 + ", img12=" + img12 + ", img13="
				+ img13 + ", img14=" + img14 + ", img15=" + img15 + ", img16=" + img16 + ", countCollect=" + countCollect
				+ ", countCart=" + countCart + ", countOrder=" + countOrder + ", commentStar1=" + commentStar1
				+ ", commentStar2=" + commentStar2 + ", commentStar3=" + commentStar3 + ", commentStar4=" + commentStar4
				+ ", commentStar5=" + commentStar5 + "]";
	}
	
}
