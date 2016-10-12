package bean;

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
	private Integer batchNumTotal;
	private Integer batchNumStock;
	private String batchDateKeep;
	private String producerSendDate;
	private String producerSendContent;
	private String sellerReceiveDate;
	private String sellerReceiveContent;
	private Set OInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public GBatch() {
	}

	/** minimal constructor */
	public GBatch(Goods goods, Integer batchNumTotal, Integer batchNumStock, String batchDateKeep, String producerSendDate, String sellerReceiveDate) {
		this.goods = goods;
		this.batchNumTotal = batchNumTotal;
		this.batchNumStock = batchNumStock;
		this.batchDateKeep = batchDateKeep;
		this.producerSendDate = producerSendDate;
		this.sellerReceiveDate = sellerReceiveDate;
	}

	/** full constructor */
	public GBatch(Goods goods, Company companyBySellerId, Company companyByProducerId, Integer batchNumTotal, Integer batchNumStock,
			String batchDateKeep, String producerSendDate, String producerSendContent, String sellerReceiveDate, String sellerReceiveContent,
			Set OInfos) {
		this.goods = goods;
		this.companyBySellerId = companyBySellerId;
		this.companyByProducerId = companyByProducerId;
		this.batchNumTotal = batchNumTotal;
		this.batchNumStock = batchNumStock;
		this.batchDateKeep = batchDateKeep;
		this.producerSendDate = producerSendDate;
		this.producerSendContent = producerSendContent;
		this.sellerReceiveDate = sellerReceiveDate;
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

	public Integer getBatchNumTotal() {
		return this.batchNumTotal;
	}

	public void setBatchNumTotal(Integer batchNumTotal) {
		this.batchNumTotal = batchNumTotal;
	}

	public Integer getBatchNumStock() {
		return this.batchNumStock;
	}

	public void setBatchNumStock(Integer batchNumStock) {
		this.batchNumStock = batchNumStock;
	}

	public String getBatchDateKeep() {
		return this.batchDateKeep;
	}

	public void setBatchDateKeep(String batchDateKeep) {
		this.batchDateKeep = batchDateKeep;
	}

	public String getProducerSendDate() {
		return this.producerSendDate;
	}

	public void setProducerSendDate(String producerSendDate) {
		this.producerSendDate = producerSendDate;
	}

	public String getProducerSendContent() {
		return this.producerSendContent;
	}

	public void setProducerSendContent(String producerSendContent) {
		this.producerSendContent = producerSendContent;
	}

	public String getSellerReceiveDate() {
		return this.sellerReceiveDate;
	}

	public void setSellerReceiveDate(String sellerReceiveDate) {
		this.sellerReceiveDate = sellerReceiveDate;
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
		return "GBatch [batchId=" + batchId + ", batchNumTotal=" + batchNumTotal + ", batchNumStock=" + batchNumStock + ", batchDateKeep="
				+ batchDateKeep + ", producerSendDate=" + producerSendDate + ", producerSendContent=" + producerSendContent + ", sellerReceiveDate="
				+ sellerReceiveDate + ", sellerReceiveContent=" + sellerReceiveContent + "]";
	}

}