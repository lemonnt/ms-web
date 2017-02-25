/**  
 * Project Name: ms-db  
 * File Name: ProductParameters.java  
 * Package Name: com.lemonnt.ms.product.bean  
 * Date: Jan 24, 201711:21:50 AM  
 * Copyright (c) 2017 All Rights Reserved.  
 *  
 */

package com.lemonnt.ms.product.bean;

/**
 * ClassName: ProductParameters <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Jan 24, 2017 11:21:50 AM <br/>
 * 
 * @author gavli
 * @version 1.0.0
 * @since JDK 1.6
 */
public class ProductParameters {

	private String search;
	private String startDate;
	private String endDate;
	private Integer offset;
	private Integer limit;
	private Integer from;
	private Integer to;
	private String order;
	private String sort;
	private String supplierId;
	
	

	/**  
	 * supplierId.  
	 *  
	 * @return  the supplierId  
	 * @since   JDK 1.6  
	 */
	public String getSupplierId() {
		return supplierId;
	}

	/**  
	 * supplierId.  
	 *  
	 * @param   supplierId    the supplierId to set  
	 * @since   JDK 1.6  
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the from
	 */
	public Integer getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(Integer from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public Integer getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(Integer to) {
		this.to = to;
	}

	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * @param search
	 *            the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the offset
	 */
	public Integer getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String toString() {
		return "startDate : " + startDate + ",endDate : " + endDate + ", search : " + search + ",sort : " + sort;
	}

}
