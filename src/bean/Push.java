package bean;

import java.sql.Timestamp;

/**
 * Push entity. @author MyEclipse Persistence Tools
 */

public class Push implements java.io.Serializable {

	// Fields

	private Integer pushId;
	private String pushEditor;
	private String pushStatus;
	private String pushTitle;
	private String pushKeyWord;
	private String pushUrl;
	private Timestamp pushDate;
	private String pushContext;

	// Constructors

	/** default constructor */
	public Push() {
	}

	/** minimal constructor */
	public Push(String pushEditor, String pushStatus, String pushTitle, String pushKeyWord, String pushUrl, Timestamp pushDate) {
		this.pushEditor = pushEditor;
		this.pushStatus = pushStatus;
		this.pushTitle = pushTitle;
		this.pushKeyWord = pushKeyWord;
		this.pushUrl = pushUrl;
		this.pushDate = pushDate;
	}

	/** full constructor */
	public Push(String pushEditor, String pushStatus, String pushTitle, String pushKeyWord, String pushUrl, Timestamp pushDate, String pushContext) {
		this.pushEditor = pushEditor;
		this.pushStatus = pushStatus;
		this.pushTitle = pushTitle;
		this.pushKeyWord = pushKeyWord;
		this.pushUrl = pushUrl;
		this.pushDate = pushDate;
		this.pushContext = pushContext;
	}

	// Property accessors

	public Integer getPushId() {
		return this.pushId;
	}

	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}

	public String getPushEditor() {
		return this.pushEditor;
	}

	public void setPushEditor(String pushEditor) {
		this.pushEditor = pushEditor;
	}

	public String getPushStatus() {
		return this.pushStatus;
	}

	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}

	public String getPushTitle() {
		return this.pushTitle;
	}

	public void setPushTitle(String pushTitle) {
		this.pushTitle = pushTitle;
	}

	public String getPushKeyWord() {
		return this.pushKeyWord;
	}

	public void setPushKeyWord(String pushKeyWord) {
		this.pushKeyWord = pushKeyWord;
	}

	public String getPushUrl() {
		return this.pushUrl;
	}

	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
	}

	public Timestamp getPushDate() {
		return this.pushDate;
	}

	public void setPushDate(Timestamp pushDate) {
		this.pushDate = pushDate;
	}

	public String getPushContext() {
		return this.pushContext;
	}

	public void setPushContext(String pushContext) {
		this.pushContext = pushContext;
	}

	@Override
	public String toString() {
		return "Push [pushId=" + pushId + ", pushEditor=" + pushEditor + ", pushStatus=" + pushStatus + ", pushTitle=" + pushTitle + ", pushKeyWord="
				+ pushKeyWord + ", pushUrl=" + pushUrl + ", pushDate=" + pushDate + ", pushContext=" + pushContext + "]";
	}

}