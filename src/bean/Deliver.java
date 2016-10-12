package bean;

/**
 * Deliver entity. @author MyEclipse Persistence Tools
 */

public class Deliver implements java.io.Serializable {

	// Fields

	private Integer deliverId;
	private String deliverName;
	private String deliverPhone;
	private String deliverStatus;

	// Constructors

	/** default constructor */
	public Deliver() {
	}

	/** full constructor */
	public Deliver(String deliverName, String deliverPhone, String deliverStatus) {
		this.deliverName = deliverName;
		this.deliverPhone = deliverPhone;
		this.deliverStatus = deliverStatus;
	}

	// Property accessors

	public Integer getDeliverId() {
		return this.deliverId;
	}

	public void setDeliverId(Integer deliverId) {
		this.deliverId = deliverId;
	}

	public String getDeliverName() {
		return this.deliverName;
	}

	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}

	public String getDeliverPhone() {
		return this.deliverPhone;
	}

	public void setDeliverPhone(String deliverPhone) {
		this.deliverPhone = deliverPhone;
	}

	public String getDeliverStatus() {
		return this.deliverStatus;
	}

	public void setDeliverStatus(String deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	@Override
	public String toString() {
		return "Deliver [deliverId=" + deliverId + ", deliverName=" + deliverName + ", deliverPhone=" + deliverPhone + ", deliverStatus="
				+ deliverStatus + "]";
	}

}