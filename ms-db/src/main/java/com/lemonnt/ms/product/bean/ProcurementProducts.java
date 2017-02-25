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
import com.lemonnt.ms.common.search.Search;

/**  
 * ClassName: ProcurementProducts <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 27, 2016 11:03:49 AM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
public class ProcurementProducts {
	
	private String id;
	
	@Search
	@Matcher("^\\S{2,30}$")
	private String name;
	@Search
	@Matcher("^\\S{2,40}$")
	private String number;
	@Search
	@Matcher("^\\S{2,30}$")
	private String color;
	@Search
	@Matcher("^\\S{2,40}$")
	private String style;
	@Search
	@Matcher("^\\S{2,40}$")
	private String kind;
	@Matcher("^\\S{2,20}$")	  
	private String size;
	@Search
	@Matcher("^\\S{2,50}$")
	private String material;
	@Search
	@Matcher("^\\S{2,40}$")
	private String co;
	private String productStandard;
	private String safetyStandard;
	private String level;
	@Search
	@Matcher("![0,9999]")
	private Integer inNumber;
	@Matcher("![1,9999]")
	@Search
	private Double unitPrice;
	@Matcher("![1,9999999]")
	private Double totalPrice;
	@Matcher("^\\S{2,40}$")
	@Search
	private String purchasingAgent;
	private String description;
	private String supplierId;
	private Date createDate;
	@Search
	private Date procurementDate;
	@Search
	private Integer procurementType;
	@Search
	private Integer status;
	@Search
	private Integer isInsaled;
	@Search
	private String productStatus;

	private Date lastModifiedDate;
	
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
	 * lastModifiedDate.  
	 *  
	 * @return  the lastModifiedDate  
	 * @since   JDK 1.6  
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	/**  
	 * lastModifiedDate.  
	 *  
	 * @param   lastModifiedDate    the lastModifiedDate to set  
	 * @since   JDK 1.6  
	 */
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = new AdvancedDate().formatter("yyyy-MM-dd", lastModifiedDate);
	}
	/**  
	 * productStatus.  
	 *  
	 * @return  the productStatus  
	 * @since   JDK 1.6  
	 */
	public String getProductStatus() {
		return productStatus;
	}
	/**  
	 * productStatus.  
	 *  
	 * @param   productStatus    the productStatus to set  
	 * @since   JDK 1.6  
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	/**  
	 * isInsaled.  
	 *  
	 * @return  the isInsaled  
	 * @since   JDK 1.6  
	 */
	public Integer getIsInsaled() {
		return isInsaled;
	}
	/**  
	 * isInsaled.  
	 *  
	 * @param   isInsaled    the isInsaled to set  
	 * @since   JDK 1.6  
	 */
	public void setIsInsaled(Integer isInsaled) {
		this.isInsaled = isInsaled;
	}
	
	/**  
	 * status.  
	 *  
	 * @return  the status  
	 * @since   JDK 1.6  
	 */
	public Integer getStatus() {
		return status;
	}
	/**  
	 * status.  
	 *  
	 * @param   status    the status to set  
	 * @since   JDK 1.6  
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	 * style.  
	 *  
	 * @return  the style  
	 * @since   JDK 1.6  
	 */
	public String getStyle() {
		return style;
	}
	/**  
	 * style.  
	 *  
	 * @param   style    the style to set  
	 * @since   JDK 1.6  
	 */
	public void setStyle(String style) {
		this.style = style;
	}
	/**  
	 * kind.  
	 *  
	 * @return  the kind  
	 * @since   JDK 1.6  
	 */
	public String getKind() {
		return kind;
	}
	/**  
	 * kind.  
	 *  
	 * @param   kind    the kind to set  
	 * @since   JDK 1.6  
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}
	/**  
	 * size.  
	 *  
	 * @return  the size  
	 * @since   JDK 1.6  
	 */
	public String getSize() {
		return size;
	}
	/**  
	 * size.  
	 *  
	 * @param   size    the size to set  
	 * @since   JDK 1.6  
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**  
	 * material.  
	 *  
	 * @return  the material  
	 * @since   JDK 1.6  
	 */
	public String getMaterial() {
		return material;
	}
	/**  
	 * material.  
	 *  
	 * @param   material    the material to set  
	 * @since   JDK 1.6  
	 */
	public void setMaterial(String material) {
		this.material = material;
	}
	/**  
	 * co.  
	 *  
	 * @return  the co  
	 * @since   JDK 1.6  
	 */
	public String getCo() {
		return co;
	}
	/**  
	 * co.  
	 *  
	 * @param   co    the co to set  
	 * @since   JDK 1.6  
	 */
	public void setCo(String co) {
		this.co = co;
	}
	/**  
	 * productStandard.  
	 *  
	 * @return  the productStandard  
	 * @since   JDK 1.6  
	 */
	public String getProductStandard() {
		return productStandard;
	}
	/**  
	 * productStandard.  
	 *  
	 * @param   productStandard    the productStandard to set  
	 * @since   JDK 1.6  
	 */
	public void setProductStandard(String productStandard) {
		this.productStandard = productStandard;
	}
	/**  
	 * safetyStandard.  
	 *  
	 * @return  the safetyStandard  
	 * @since   JDK 1.6  
	 */
	public String getSafetyStandard() {
		return safetyStandard;
	}
	/**  
	 * safetyStandard.  
	 *  
	 * @param   safetyStandard    the safetyStandard to set  
	 * @since   JDK 1.6  
	 */
	public void setSafetyStandard(String safetyStandard) {
		this.safetyStandard = safetyStandard;
	}
	/**  
	 * level.  
	 *  
	 * @return  the level  
	 * @since   JDK 1.6  
	 */
	public String getLevel() {
		return level;
	}
	/**  
	 * level.  
	 *  
	 * @param   level    the level to set  
	 * @since   JDK 1.6  
	 */
	public void setLevel(String level) {
		this.level = level;
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
  
