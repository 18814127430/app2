package bean;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private OInfo OInfo;
	private String commentStars;
	private String commentContent;
	private Integer commentNum;
	private String commentDate;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** full constructor */
	public Comment(OInfo OInfo, String commentStars, String commentContent, Integer commentNum, String commentDate) {
		this.OInfo = OInfo;
		this.commentStars = commentStars;
		this.commentContent = commentContent;
		this.commentNum = commentNum;
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

	public String getCommentStars() {
		return this.commentStars;
	}

	public void setCommentStars(String commentStars) {
		this.commentStars = commentStars;
	}

	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getCommentNum() {
		return this.commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public String getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", OInfo=" + OInfo + ", commentStars=" + commentStars + ", commentContent=" + commentContent
				+ ", commentNum=" + commentNum + ", commentDate=" + commentDate + "]";
	}

}