package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Company entity. @author MyEclipse Persistence Tools
 */

public class Company implements java.io.Serializable {

	// Fields

	private Integer companyId;
	private String companyName;
	private String companyType;
	private String companyPhone;
	private String companyPostCode;
	private String companyFax;
	private String companyUrl;
	private String companyProvince;
	private String companyCity;
	private String companyStress;
	private String companyDetial;
	private String companyIntroduction;
	private String companyStatus;
	private Set GBatchsForProducerId = new HashSet(0);
	private Set GBatchsForSellerId = new HashSet(0);

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(String companyName, String companyType, String companyPhone, String companyPostCode, String companyFax, String companyUrl,
			String companyProvince, String companyCity, String companyStress, String companyDetial, String companyStatus) {
		this.companyName = companyName;
		this.companyType = companyType;
		this.companyPhone = companyPhone;
		this.companyPostCode = companyPostCode;
		this.companyFax = companyFax;
		this.companyUrl = companyUrl;
		this.companyProvince = companyProvince;
		this.companyCity = companyCity;
		this.companyStress = companyStress;
		this.companyDetial = companyDetial;
		this.companyStatus = companyStatus;
	}

	/** full constructor */
	public Company(String companyName, String companyType, String companyPhone, String companyPostCode, String companyFax, String companyUrl,
			String companyProvince, String companyCity, String companyStress, String companyDetial, String companyIntroduction, String companyStatus,
			Set GBatchsForProducerId, Set GBatchsForSellerId) {
		this.companyName = companyName;
		this.companyType = companyType;
		this.companyPhone = companyPhone;
		this.companyPostCode = companyPostCode;
		this.companyFax = companyFax;
		this.companyUrl = companyUrl;
		this.companyProvince = companyProvince;
		this.companyCity = companyCity;
		this.companyStress = companyStress;
		this.companyDetial = companyDetial;
		this.companyIntroduction = companyIntroduction;
		this.companyStatus = companyStatus;
		this.GBatchsForProducerId = GBatchsForProducerId;
		this.GBatchsForSellerId = GBatchsForSellerId;
	}

	// Property accessors

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyType() {
		return this.companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyPhone() {
		return this.companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyPostCode() {
		return this.companyPostCode;
	}

	public void setCompanyPostCode(String companyPostCode) {
		this.companyPostCode = companyPostCode;
	}

	public String getCompanyFax() {
		return this.companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	public String getCompanyUrl() {
		return this.companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public String getCompanyProvince() {
		return this.companyProvince;
	}

	public void setCompanyProvince(String companyProvince) {
		this.companyProvince = companyProvince;
	}

	public String getCompanyCity() {
		return this.companyCity;
	}

	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}

	public String getCompanyStress() {
		return this.companyStress;
	}

	public void setCompanyStress(String companyStress) {
		this.companyStress = companyStress;
	}

	public String getCompanyDetial() {
		return this.companyDetial;
	}

	public void setCompanyDetial(String companyDetial) {
		this.companyDetial = companyDetial;
	}

	public String getCompanyIntroduction() {
		return this.companyIntroduction;
	}

	public void setCompanyIntroduction(String companyIntroduction) {
		this.companyIntroduction = companyIntroduction;
	}

	public String getCompanyStatus() {
		return this.companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	public Set getGBatchsForProducerId() {
		return this.GBatchsForProducerId;
	}

	public void setGBatchsForProducerId(Set GBatchsForProducerId) {
		this.GBatchsForProducerId = GBatchsForProducerId;
	}

	public Set getGBatchsForSellerId() {
		return this.GBatchsForSellerId;
	}

	public void setGBatchsForSellerId(Set GBatchsForSellerId) {
		this.GBatchsForSellerId = GBatchsForSellerId;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", companyType=" + companyType + ", companyPhone=" + companyPhone
				+ ", companyPostCode=" + companyPostCode + ", companyFax=" + companyFax + ", companyUrl=" + companyUrl + ", companyProvince="
				+ companyProvince + ", companyCity=" + companyCity + ", companyStress=" + companyStress + ", companyDetial=" + companyDetial
				+ ", companyIntroduction=" + companyIntroduction + ", companyStatus=" + companyStatus + "]";
	}



}