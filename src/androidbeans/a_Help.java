package androidbeans;

import java.sql.Timestamp;

/**
 * Push entity. @author MyEclipse Persistence Tools
 */

public class a_Help implements java.io.Serializable {

	private Integer helpId;
	private String helpContext;

	public a_Help(Integer helpId, String helpContext) {
		super();
		this.helpId = helpId;
		this.helpContext = helpContext;
	}

	public a_Help() {
		// TODO Auto-generated constructor stub
	}

	public Integer getHelpId() {
		return helpId;
	}

	public void setHelpId(Integer helpId) {
		this.helpId = helpId;
	}

	public String getHelpContext() {
		return helpContext;
	}

	public void setHelpContext(String helpContext) {
		this.helpContext = helpContext;
	}

	@Override
	public String toString() {
		return "a_Help [helpId=" + helpId + ", helpContext=" + helpContext + "]";
	}

}