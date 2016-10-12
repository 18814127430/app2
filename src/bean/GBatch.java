package bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * GBatch entity. @author MyEclipse Persistence Tools
 */

public class GBatch implements java.io.Serializable {

	// Fields

	private Integer batchId;
	private Goods goods;
	private Company companyBySellerId;
	private Company companyByProducerId;
	private Integer numTotal;
	private Integer numStock;
	private Timestamp dateKeep1;
	private Timestamp dateKeep2;
	private Timestamp dateSend;
	private Timestamp dateRec;
	private String producerSendContent;
	private String sellerReceiveContent;
	private Set OInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public GBatch() {
	}

	/** minimal constructor */
	public GBatch(Goods goods, Integer numTotal, Integer numStock) {
		this.goods = goods;
		this.numTotal = numTotal;
		this.numStock = numStock;
	}

	/** full constructor */
	public GBatch(Goods goods, Company companyBySellerId, Company companyByProducerId, Integer numTotal, Integer numStock, Timestamp dateKeep1,
			Timestamp dateKeep2, Timestamp dateSend, Timestamp dateRec, String producerSendContent, String sellerReceiveContent, Set OInfos) {
		this.goods = goods;
		this.companyBySellerId = companyBySellerId;
		this.companyByProducerId = companyByProducerId;
		this.numTotal = numTotal;
		this.numStock = numStock;
		this.dateKeep1 = dateKeep1;
		this.dateKeep2 = dateKeep2;
		this.dateSend = dateSend;
		this.dateRec = dateRec;
		this.producerSendContent = producerSendContent;
		this.sellerReceiveContent = sellerReceiveContent;
		this.OInfos = OInfos;
	}

	// Property accessors

	public Integer getBatchId() {
		return this.batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Company getCompanyBySellerId() {
		return this.companyBySellerId;
	}

	public void setCompanyBySellerId(Company companyBySellerId) {
		this.companyBySellerId = companyBySellerId;
	}

	public Company getCompanyByProducerId() {
		return this.companyByProducerId;
	}

	public void setCompanyByProducerId(Company companyByProducerId) {
		this.companyByProducerId = companyByProducerId;
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

	public Timestamp getDateKeep1() {
		return this.dateKeep1;
	}

	public void setDateKeep1(Timestamp dateKeep1) {
		this.dateKeep1 = dateKeep1;
	}

	public Timestamp getDateKeep2() {
		return this.dateKeep2;
	}

	public void setDateKeep2(Timestamp dateKeep2) {
		this.dateKeep2 = dateKeep2;
	}

	public Timestamp getDateSend() {
		return this.dateSend;
	}

	public void setDateSend(Timestamp dateSend) {
		this.dateSend = dateSend;
	}

	public Timestamp getDateRec() {
		return this.dateRec;
	}

	public void setDateRec(Timestamp dateRec) {
		this.dateRec = dateRec;
	}

	public String getProducerSendContent() {
		return this.producerSendContent;
	}

	public void setProducerSendContent(String producerSendContent) {
		this.producerSendContent = producerSendContent;
	}

	public String getSellerReceiveContent() {
		return this.sellerReceiveContent;
	}

	public void setSellerReceiveContent(String sellerReceiveContent) {
		this.sellerReceiveContent = sellerReceiveContent;
	}

	public Set getOInfos() {
		return this.OInfos;
	}

	public void setOInfos(Set OInfos) {
		this.OInfos = OInfos;
	}

	@Override
	public String toString() {
		return "GBatch [batchId=" + batchId + ", numTotal=" + numTotal + ", numStock=" + numStock + ", dateKeep1=" + dateKeep1 + ", dateKeep2="
				+ dateKeep2 + ", dateSend=" + dateSend + ", dateRec=" + dateRec + ", producerSendContent=" + producerSendContent
				+ ", sellerReceiveContent=" + sellerReceiveContent + "]";
	}

}