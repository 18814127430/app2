package androidbeans;

import java.sql.Timestamp;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class a_Comment implements java.io.Serializable {
	
	private Integer commentId;
	private Integer customerId;
	private String customerPhone;
	private Integer orderId;
	private Integer commentStar;
	private Integer commentCount;
	private String commentContent;
	private String orderSerial;
	private Double orderPrice;
	private Timestamp orderDate;
	
	public a_Comment(Integer commentId, Integer customerId, Integer orderId, Integer commentStar, Integer commentCount,
			String commentContent, String orderSerial, Double orderPrice, Timestamp orderDate) {
		super();
		this.commentId = commentId;
		this.customerId = customerId;
		this.orderId = orderId;
		this.commentStar = commentStar;
		this.commentCount = commentCount;
		this.commentContent = commentContent;
		this.orderSerial = orderSerial;
		this.orderPrice = orderPrice;
		this.orderDate = orderDate;
	}
	
	public a_Comment() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getCommentId() {
		return commentId;
	}
	
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getCommentStar() {
		return commentStar;
	}
	
	public void setCommentStar(Integer commentStar) {
		this.commentStar = commentStar;
	}
	
	public Integer getCommentCount() {
		return commentCount;
	}
	
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	
	public String getCommentContent() {
		return commentContent;
	}
	
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	public String getOrderSerial() {
		return orderSerial;
	}
	
	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}
	
	public Double getOrderPrice() {
		return orderPrice;
	}
	
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	public Timestamp getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	
	@Override
	public String toString() {
		return "a_Comment [commentId=" + commentId + ", customerId=" + customerId + ", orderId=" + orderId
				+ ", commentStar=" + commentStar + ", commentCount=" + commentCount + ", commentContent=" + commentContent
				+ ", orderSerial=" + orderSerial + ", orderPrice=" + orderPrice + ", orderDate=" + orderDate + "]";
	}
	
	public String getCustomerPhone() {
		return customerPhone;
	}
	
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
}
