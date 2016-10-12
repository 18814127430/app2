package androidbeans;

import java.sql.Timestamp;

/**
 * Push entity. @author MyEclipse Persistence Tools
 */

public class a_Push implements java.io.Serializable {

	private Integer pushId;
	private String pushUrl;
	private String pushContext;

	public a_Push(Integer pushId, String pushUrl, String pushContext) {
		super();
		this.pushId = pushId;
		this.pushUrl = pushUrl;
		this.pushContext = pushContext;
	}

	public a_Push() {
		// TODO Auto-generated constructor stub
	}

	public Integer getPushId() {
		return pushId;
	}

	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}

	public String getPushUrl() {
		return pushUrl;
	}

	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
	}

	public String getPushContext() {
		return pushContext;
	}

	public void setPushContext(String pushContext) {
		this.pushContext = pushContext;
	}

	@Override
	public String toString() {
		return "a_Push [pushId=" + pushId + ", pushUrl=" + pushUrl + ", pushContext=" + pushContext + "]";
	}

}