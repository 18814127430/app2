package androidbeans;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * GBatch entity. @author MyEclipse Persistence Tools
 */

public class a_GBatch implements java.io.Serializable {

	// Fields
	private Integer batchId;
	private Integer goodsId;
	private Integer companyBySellerId;
	private Integer companyByProducerId;
	private Integer batchNumTotal;
	private Integer batchNumStock;
	private String batchDateKeep1;
	private String batchDateKeep2;
	private String batchDateSend;
	private String batchdateRece;
	private String producerSendContent;

	public a_GBatch(Integer batchId, Integer goodsId, Integer companyBySellerId, Integer companyByProducerId, Integer batchNumTotal,
			Integer batchNumStock, String batchDateKeep1, String batchDateKeep2, String batchDateSend, String batchdateRece,
			String producerSendContent, String sellerReceiveContent) {
		super();
		this.batchId = batchId;
		this.goodsId = goodsId;
		this.companyBySellerId = companyBySellerId;
		this.companyByProducerId = companyByProducerId;
		this.batchNumTotal = batchNumTotal;
		this.batchNumStock = batchNumStock;
		this.batchDateKeep1 = batchDateKeep1;
		this.batchDateKeep2 = batchDateKeep2;
		this.batchDateSend = batchDateSend;
		this.batchdateRece = batchdateRece;
		this.producerSendContent = producerSendContent;
		this.sellerReceiveContent = sellerReceiveContent;
	}

	private String sellerReceiveContent;

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

	public Integer getCompanyBySellerId() {
		return companyBySellerId;
	}

	public void setCompanyBySellerId(Integer companyBySellerId) {
		this.companyBySellerId = companyBySellerId;
	}

	public Integer getCompanyByProducerId() {
		return companyByProducerId;
	}

	public void setCompanyByProducerId(Integer companyByProducerId) {
		this.companyByProducerId = companyByProducerId;
	}

	public Integer getBatchNumTotal() {
		return batchNumTotal;
	}

	public void setBatchNumTotal(Integer batchNumTotal) {
		this.batchNumTotal = batchNumTotal;
	}

	public Integer getBatchNumStock() {
		return batchNumStock;
	}

	public void setBatchNumStock(Integer batchNumStock) {
		this.batchNumStock = batchNumStock;
	}

	public String getBatchDateKeep1() {
		return batchDateKeep1;
	}

	public void setBatchDateKeep1(String batchDateKeep1) {
		this.batchDateKeep1 = batchDateKeep1;
	}

	public String getBatchDateKeep2() {
		return batchDateKeep2;
	}

	public void setBatchDateKeep2(String batchDateKeep2) {
		this.batchDateKeep2 = batchDateKeep2;
	}

	public String getBatchDateSend() {
		return batchDateSend;
	}

	public void setBatchDateSend(String batchDateSend) {
		this.batchDateSend = batchDateSend;
	}

	public String getBatchdateRece() {
		return batchdateRece;
	}

	public void setBatchdateRece(String batchdateRece) {
		this.batchdateRece = batchdateRece;
	}

	public String getProducerSendContent() {
		return producerSendContent;
	}

	public void setProducerSendContent(String producerSendContent) {
		this.producerSendContent = producerSendContent;
	}

	public String getSellerReceiveContent() {
		return sellerReceiveContent;
	}

	public void setSellerReceiveContent(String sellerReceiveContent) {
		this.sellerReceiveContent = sellerReceiveContent;
	}

	@Override
	public String toString() {
		return "a_GBatch [batchId=" + batchId + ", goodsId=" + goodsId + ", companyBySellerId=" + companyBySellerId + ", companyByProducerId="
				+ companyByProducerId + ", batchNumTotal=" + batchNumTotal + ", batchNumStock=" + batchNumStock + ", batchDateKeep1="
				+ batchDateKeep1 + ", batchDateKeep2=" + batchDateKeep2 + ", batchDateSend=" + batchDateSend + ", batchdateRece=" + batchdateRece
				+ ", producerSendContent=" + producerSendContent + ", sellerReceiveContent=" + sellerReceiveContent + "]";
	}

}