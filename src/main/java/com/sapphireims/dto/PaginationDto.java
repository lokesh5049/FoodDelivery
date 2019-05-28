/** 
 * Java class
 * 
 */
package com.sapphireims.dto;

/**
 * int start, int limit, String sortProperty, String sortDirection
 * @author lokesh.yadav
 *
 * @since Jan 31, 2019
 */
public class PaginationDto {
	
	private int start;	
	private int limit;
	private String sortProperty;
	private  String sortDirection;
	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @return the sortProperty
	 */
	public String getSortProperty() {
		return sortProperty;
	}
	/**
	 * @param sortProperty the sortProperty to set
	 */
	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}
	/**
	 * @return the sortDirection
	 */
	public String getSortDirection() {
		return sortDirection;
	}
	/**
	 * @param sortDirection the sortDirection to set
	 */
	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaginationDto [start=" + start + ", limit=" + limit + ", sortProperty=" + sortProperty
				+ ", sortDirection=" + sortDirection + "]";
	}

}
