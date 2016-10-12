package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Sort entity. @author MyEclipse Persistence Tools
 */

public class Sort implements java.io.Serializable {

	// Fields

	private Integer sortId;
	private Sort sort;
	private Integer sortClass;
	private String sortName;
	private String sortImgPath;
	private String sortImgExtension;
	private Set sorts = new HashSet(0);
	private Set goodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Sort() {
	}

	/** minimal constructor */
	public Sort(Integer sortClass, String sortName, String sortImgPath, String sortImgExtension) {
		this.sortClass = sortClass;
		this.sortName = sortName;
		this.sortImgPath = sortImgPath;
		this.sortImgExtension = sortImgExtension;
	}

	/** full constructor */
	public Sort(Sort sort, Integer sortClass, String sortName, String sortImgPath, String sortImgExtension, Set sorts, Set goodses) {
		this.sort = sort;
		this.sortClass = sortClass;
		this.sortName = sortName;
		this.sortImgPath = sortImgPath;
		this.sortImgExtension = sortImgExtension;
		this.sorts = sorts;
		this.goodses = goodses;
	}

	// Property accessors

	public Integer getSortId() {
		return this.sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public Sort getSort() {
		return this.sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public Integer getSortClass() {
		return this.sortClass;
	}

	public void setSortClass(Integer sortClass) {
		this.sortClass = sortClass;
	}

	public String getSortName() {
		return this.sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortImgPath() {
		return this.sortImgPath;
	}

	public void setSortImgPath(String sortImgPath) {
		this.sortImgPath = sortImgPath;
	}

	public String getSortImgExtension() {
		return this.sortImgExtension;
	}

	public void setSortImgExtension(String sortImgExtension) {
		this.sortImgExtension = sortImgExtension;
	}

	public Set getSorts() {
		return this.sorts;
	}

	public void setSorts(Set sorts) {
		this.sorts = sorts;
	}

	public Set getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}

	@Override
	public String toString() {
		return "Sort [sortId=" + sortId + ", sortClass=" + sortClass + ", sortName=" + sortName + ", sortImgPath=" + sortImgPath
				+ ", sortImgExtension=" + sortImgExtension + "]";
	}

}