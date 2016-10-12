package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private Integer adminId;
	private String adminAccount;
	private String adminPassword;
	private String adminName;
	private String adminPhone;
	private String adminMail;
	private String adminRegion;
	private String adminImg;
	private String adminClass;
	private String adminStartDate;
	private Set CWordses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** minimal constructor */
	public Admin(String adminAccount, String adminPassword, String adminName, String adminPhone, String adminMail, String adminRegion,
			String adminImg, String adminClass, String adminStartDate) {
		this.adminAccount = adminAccount;
		this.adminPassword = adminPassword;
		this.adminName = adminName;
		this.adminPhone = adminPhone;
		this.adminMail = adminMail;
		this.adminRegion = adminRegion;
		this.adminImg = adminImg;
		this.adminClass = adminClass;
		this.adminStartDate = adminStartDate;
	}

	/** full constructor */
	public Admin(String adminAccount, String adminPassword, String adminName, String adminPhone, String adminMail, String adminRegion,
			String adminImg, String adminClass, String adminStartDate, Set CWordses) {
		this.adminAccount = adminAccount;
		this.adminPassword = adminPassword;
		this.adminName = adminName;
		this.adminPhone = adminPhone;
		this.adminMail = adminMail;
		this.adminRegion = adminRegion;
		this.adminImg = adminImg;
		this.adminClass = adminClass;
		this.adminStartDate = adminStartDate;
		this.CWordses = CWordses;
	}

	// Property accessors

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminAccount() {
		return this.adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPhone() {
		return this.adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getAdminMail() {
		return this.adminMail;
	}

	public void setAdminMail(String adminMail) {
		this.adminMail = adminMail;
	}

	public String getAdminRegion() {
		return this.adminRegion;
	}

	public void setAdminRegion(String adminRegion) {
		this.adminRegion = adminRegion;
	}

	public String getAdminImg() {
		return this.adminImg;
	}

	public void setAdminImg(String adminImg) {
		this.adminImg = adminImg;
	}

	public String getAdminClass() {
		return this.adminClass;
	}

	public void setAdminClass(String adminClass) {
		this.adminClass = adminClass;
	}

	public String getAdminStartDate() {
		return this.adminStartDate;
	}

	public void setAdminStartDate(String adminStartDate) {
		this.adminStartDate = adminStartDate;
	}

	public Set getCWordses() {
		return this.CWordses;
	}

	public void setCWordses(Set CWordses) {
		this.CWordses = CWordses;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminAccount=" + adminAccount + ", adminPassword=" + adminPassword + ", adminName=" + adminName
				+ ", adminPhone=" + adminPhone + ", adminMail=" + adminMail + ", adminRegion=" + adminRegion + ", adminImg=" + adminImg
				+ ", adminClass=" + adminClass + ", adminStartDate=" + adminStartDate + "]";
	}

}