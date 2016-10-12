package bean;

/**
 * Logs entity. @author MyEclipse Persistence Tools
 */

public class Logs implements java.io.Serializable {

	// Fields

	private Integer logsId;
	private String logsDate;
	private String logsContent;

	// Constructors

	/** default constructor */
	public Logs() {
	}

	/** minimal constructor */
	public Logs(String logsDate) {
		this.logsDate = logsDate;
	}

	/** full constructor */
	public Logs(String logsDate, String logsContent) {
		this.logsDate = logsDate;
		this.logsContent = logsContent;
	}

	// Property accessors

	public Integer getLogsId() {
		return this.logsId;
	}

	public void setLogsId(Integer logsId) {
		this.logsId = logsId;
	}

	public String getLogsDate() {
		return this.logsDate;
	}

	public void setLogsDate(String logsDate) {
		this.logsDate = logsDate;
	}

	public String getLogsContent() {
		return this.logsContent;
	}

	public void setLogsContent(String logsContent) {
		this.logsContent = logsContent;
	}

}