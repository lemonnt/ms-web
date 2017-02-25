/**  
 * Project Name:ms-db  
 * File Name:ProcurementProducts.java  
 * Package Name:com.lemonnt.ms.product.bean  
 * Date:Dec 27, 201611:03:49 AM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: ms-db  
 * File Name: ProcurementProducts.java  
 * Package Name: com.lemonnt.ms.product.bean  
 * Date: Dec 27, 201611:03:49 AM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.product.bean;

import java.util.Date;
import java.util.UUID;

import com.lemonnt.ms.common.bean.AdvancedDate;
import com.lemonnt.ms.common.bean.Matcher;

/**  
 * ClassName: ProcurementProducts <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 27, 2016 11:03:49 AM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
public class ProcurementAssistantProducts {
	
	private String id;
	
	@Matcher("^\\S{2,30}$")
	private String name;
	@Matcher("^\\S{2,40}$")
	private String number;
	@Matcher("^\\S{2,30}$")
	private String color;
	
	@Matcher("^\\d{1,5}$")
	private Integer inNumber;
	@Matcher("![1,9999]")
	private Double unitPrice;
	@Matcher("![1,9999999]")
	private Double totalPrice;
	@Matcher("^\\S{2,40}$")
	private String purchasingAgent;
	private String description;
	private String supplierId;
	private Date createDate;
	private Date procurementDate;
	private Integer procurementType;
	
    private String updatePeople;
	
	
	
	
	
	
	/**  
	 * updatePeople.  
	 *  
	 * @return  the updatePeople  
	 * @since   JDK 1.6  
	 */
	public String getUpdatePeople() {
		if(null == updatePeople) updatePeople = getPurchasingAgent();
		return updatePeople;
	}
	/**  
	 * updatePeople.  
	 *  
	 * @param   updatePeople    the updatePeople to set  
	 * @since   JDK 1.6  
	 */
	public void setUpdatePeople(String updatePeople) {
		this.updatePeople = updatePeople;
	}

	/**  
	 * id.  
	 *  
	 * @return  the id  
	 * @since   JDK 1.6  
	 */
	public String getId() {
		if(id == null) id = String.valueOf(UUID.randomUUID()).replaceAll("-", "");
		return id;
	}
	/**  
	 * id.  
	 *  
	 * @param   id    the id to set  
	 * @since   JDK 1.6  
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**  
	 * procurementType.  
	 *  
	 * @return  the procurementType  
	 * @since   JDK 1.6  
	 */
	public Integer getProcurementType() {
		if(null == procurementType) procurementType = 1;
		return procurementType;
	}
	/**  
	 * procurementType.  
	 *  
	 * @param   procurementType    the procurementType to set  
	 * @since   JDK 1.6  
	 */
	public void setProcurementType(Integer procurementType) {
		this.procurementType = procurementType;
	}
	/**  
	 * procurementDate.  
	 *  
	 * @return  the procurementDate  
	 * @since   JDK 1.6  
	 */
	public Date getProcurementDate() {
		return procurementDate;
	}
	/**  
	 * procurementDate.  
	 *  
	 * @param   procurementDate    the procurementDate to set  
	 * @since   JDK 1.6  
	 */
	public void setProcurementDate(String procurementDate) {
		this.procurementDate = new AdvancedDate().formatter("yyyy-MM-dd", procurementDate);
	}
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
	 * name.  
	 *  
	 * @return  the name  
	 * @since   JDK 1.6  
	 */
	public String getName() {
		return name;
	}
	/**  
	 * name.  
	 *  
	 * @param   name    the name to set  
	 * @since   JDK 1.6  
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**  
	 * number.  
	 *  
	 * @return  the number  
	 * @since   JDK 1.6  
	 */
	public String getNumber() {
		return number;
	}
	/**  
	 * number.  
	 *  
	 * @param   number    the number to set  
	 * @since   JDK 1.6  
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**  
	 * color.  
	 *  
	 * @return  the color  
	 * @since   JDK 1.6  
	 */
	public String getColor() {
		return color;
	}
	/**  
	 * color.  
	 *  
	 * @param   color    the color to set  
	 * @since   JDK 1.6  
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**  
	 * inNumber.  
	 *  
	 * @return  the inNumber  
	 * @since   JDK 1.6  
	 */
	public Integer getInNumber() {
		return inNumber;
	}
	/**  
	 * inNumber.  
	 *  
	 * @param   inNumber    the inNumber to set  
	 * @since   JDK 1.6  
	 */
	public void setInNumber(Integer inNumber) {
		this.inNumber = inNumber;
	}
	/**  
	 * unitPrice.  
	 *  
	 * @return  the unitPrice  
	 * @since   JDK 1.6  
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}
	/**  
	 * unitPrice.  
	 *  
	 * @param   unitPrice    the unitPrice to set  
	 * @since   JDK 1.6  
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**  
	 * totalPrice.  
	 *  
	 * @return  the totalPrice  
	 * @since   JDK 1.6  
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}
	/**  
	 * totalPrice.  
	 *  
	 * @param   totalPrice    the totalPrice to set  
	 * @since   JDK 1.6  
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**  
	 * purchasingAgent.  
	 *  
	 * @return  the purchasingAgent  
	 * @since   JDK 1.6  
	 */
	public String getPurchasingAgent() {
		return purchasingAgent;
	}
	/**  
	 * purchasingAgent.  
	 *  
	 * @param   purchasingAgent    the purchasingAgent to set  
	 * @since   JDK 1.6  
	 */
	public void setPurchasingAgent(String purchasingAgent) {
		this.purchasingAgent = purchasingAgent;
	}
	/**  
	 * description.  
	 *  
	 * @return  the description  
	 * @since   JDK 1.6  
	 */
	public String getDescription() {
		return description;
	}
	/**  
	 * description.  
	 *  
	 * @param   description    the description to set  
	 * @since   JDK 1.6  
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**  
	 * createDate.  
	 *  
	 * @return  the createDate  
	 * @since   JDK 1.6  
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**  
	 * createDate.  
	 *  
	 * @param   createDate    the createDate to set  
	 * @since   JDK 1.6  
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
  
