package bean;

/**
 * CWords entity. @author MyEclipse Persistence Tools
 */

public class CWords implements java.io.Serializable {

	// Fields

	private Integer wordsId;
	private Admin admin;
	private Customer customer;
	private String wordsContent;
	private String wordsDate;
	private String wordsStatus;

	// Constructors

	/** default constructor */
	public CWords() {
	}

	/** minimal constructor */
	public CWords(Customer customer, String wordsDate, String wordsStatus) {
		this.customer = customer;
		this.wordsDate = wordsDate;
		this.wordsStatus = wordsStatus;
	}

	/** full constructor */
	public CWords(Admin admin, Customer customer, String wordsContent, String wordsDate, String wordsStatus) {
		this.admin = admin;
		this.customer = customer;
		this.wordsContent = wordsContent;
		this.wordsDate = wordsDate;
		this.wordsStatus = wordsStatus;
	}

	// Property accessors

	public Integer getWordsId() {
		return this.wordsId;
	}

	public void setWordsId(Integer wordsId) {
		this.wordsId = wordsId;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getWordsContent() {
		return this.wordsContent;
	}

	public void setWordsContent(String wordsContent) {
		this.wordsContent = wordsContent;
	}

	public String getWordsDate() {
		return this.wordsDate;
	}

	public void setWordsDate(String wordsDate) {
		this.wordsDate = wordsDate;
	}

	public String getWordsStatus() {
		return this.wordsStatus;
	}

	public void setWordsStatus(String wordsStatus) {
		this.wordsStatus = wordsStatus;
	}

}