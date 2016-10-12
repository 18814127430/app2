package bean;

import java.sql.Timestamp;

/**
 * Logs entity. @author MyEclipse Persistence Tools
 */

public class Logs implements java.io.Serializable {

	// Fields

	private Integer logsId;
	private Timestamp logsDate;
	private String logsContent;

	// Constructors

	/** default constructor */
	public Logs() {
	}

	/** minimal constructor */
	public Logs(Timestamp logsDate) {
		this.logsDate = logsDate;
	}

	/** full constructor */
	public Logs(Timestamp logsDate, String logsContent) {
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

	public Timestamp getLogsDate() {
		return this.logsDate;
	}

	public void setLogsDate(Timestamp logsDate) {
		this.logsDate = logsDate;
	}

	public String getLogsContent() {
		return this.logsContent;
	}

	public void setLogsContent(String logsContent) {
		this.logsContent = logsContent;
	}

	@Override
	public String toString() {
		return "Logs [logsId=" + logsId + ", logsDate=" + logsDate + ", logsContent=" + logsContent + "]";
	}

}