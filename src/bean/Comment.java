package bean;

import java.sql.Timestamp;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private OInfo OInfo;
	private Integer commentStar;
	private Integer commentCount;
	private String commentContent;
	private Timestamp commentDate;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(OInfo OInfo) {
		this.OInfo = OInfo;
	}

	/** full constructor */
	public Comment(OInfo OInfo, Integer commentStar, Integer commentCount, String commentContent, Timestamp commentDate) {
		this.OInfo = OInfo;
		this.commentStar = commentStar;
		this.commentCount = commentCount;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
	}

	// Property accessors

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public OInfo getOInfo() {
		return this.OInfo;
	}

	public void setOInfo(OInfo OInfo) {
		this.OInfo = OInfo;
	}

	public Integer getCommentStar() {
		return this.commentStar;
	}

	public void setCommentStar(Integer commentStar) {
		this.commentStar = commentStar;
	}

	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Timestamp getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", OInfo=" + OInfo.getOinfoId() + ", commentStar=" + commentStar + ", commentCount=" + commentCount
				+ ", commentContent=" + commentContent + ", commentDate=" + commentDate + "]";
	}

}