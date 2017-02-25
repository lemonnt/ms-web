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

import com.lemonnt.ms.common.bean.AdvancedDate;
import com.lemonnt.ms.common.bean.Matcher;
import com.lemonnt.ms.common.search.Search;

/**
 * ClassName: InsaleProducts <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 27, 2016 11:03:49 AM <br/>
 * 
 * @author gavli
 * @version 1.0.0
 * @since JDK 1.6
 */
public class InsaleProducts {

	private Integer id;

	@Search
	@Matcher("^\\S{2,30}$")
	private String name;
	@Search
	@Matcher("^\\S{2,40}$")
	private String number;
	@Matcher("^\\S{2,64}$")
	@Search
	private String brand;
	@Matcher("^\\S{2,30}$")
	@Search
	private String color;
	@Matcher("^\\S{2,40}$")
	@Search
	private String style;
	@Matcher("^\\S{2,40}$")
	@Search
	private String kind;
	@Matcher("^\\S{2,20}$")
	@Search
	private String size;
	@Matcher("^\\S{2,50}$")
	@Search
	private String material;
	@Matcher("^\\S{2,40}$")
	@Search
	private String co;
	private String productStandard;
	private String safetyStandard;
	private String level;

	@Matcher("^\\S{2,40}$")
	@Search
	private String purchasingAgent;
	@Search
	private String description;
	// @Matcher("^\\S{30,64}$")
	// private String PRODUCId;
	@Search
	private Date createDate;
	@Matcher("![0,9999]")
	@Search
	private Integer allInventory;
	@Matcher("^\\d{1,5}$")
	private Integer saled;
	@Search
	@Matcher("![0,9999]")
	private Integer inventory;
	@Search
	@Matcher("![1,9999]")
	private Double brandPrice;
	@Matcher("![0,1]")
	@Search
	private Double discountPercent;
	@Matcher("![1,9999]")
	@Search
	private Double salePrice;
	@Search
	private String whereToSale;

	@Matcher("^\\S{30,64}$")
	private String productId;

	@Search
	private Integer status;

	private Date lastModifiedDate;

	private String updatePeople;

	/**
	 * updatePeople.
	 * 
	 * @return the updatePeople
	 * @since JDK 1.6
	 */
	public String getUpdatePeople() {
		if (null == updatePeople)
			updatePeople = getPurchasingAgent();
		return updatePeople;
	}

	/**
	 * updatePeople.
	 * 
	 * @param updatePeople
	 *            the updatePeople to set
	 * @since JDK 1.6
	 */
	public void setUpdatePeople(String updatePeople) {
		this.updatePeople = updatePeople;
	}

	/**
	 * lastModifiedDate.
	 * 
	 * @return the lastModifiedDate
	 * @since JDK 1.6
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * lastModifiedDate.
	 * 
	 * @param lastModifiedDate
	 *            the lastModifiedDate to set
	 * @since JDK 1.6
	 */
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = new AdvancedDate().formatter("yyyy-MM-dd", lastModifiedDate);
	}

	/**
	 * status.
	 * 
	 * @return the status
	 * @since JDK 1.6
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * status.
	 * 
	 * @param status
	 *            the status to set
	 * @since JDK 1.6
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * id.
	 * 
	 * @return the id
	 * @since JDK 1.6
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * id.
	 * 
	 * @param id
	 *            the id to set
	 * @since JDK 1.6
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * productId.
	 * 
	 * @return the productId
	 * @since JDK 1.6
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * productId.
	 * 
	 * @param productId
	 *            the productId to set
	 * @since JDK 1.6
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * whereToSale.
	 * 
	 * @return the whereToSale
	 * @since JDK 1.6
	 */
	public String getWhereToSale() {
		return whereToSale;
	}

	/**
	 * whereToSale.
	 * 
	 * @param whereToSale
	 *            the whereToSale to set
	 * @since JDK 1.6
	 */
	public void setWhereToSale(String whereToSale) {
		this.whereToSale = whereToSale;
	}

	/**
	 * name.
	 * 
	 * @return the name
	 * @since JDK 1.6
	 */
	public String getName() {
		return name;
	}

	/**
	 * name.
	 * 
	 * @param name
	 *            the name to set
	 * @since JDK 1.6
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * number.
	 * 
	 * @return the number
	 * @since JDK 1.6
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * number.
	 * 
	 * @param number
	 *            the number to set
	 * @since JDK 1.6
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * brand.
	 * 
	 * @return the brand
	 * @since JDK 1.6
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * brand.
	 * 
	 * @param brand
	 *            the brand to set
	 * @since JDK 1.6
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * color.
	 * 
	 * @return the color
	 * @since JDK 1.6
	 */
	public String getColor() {
		return color;
	}

	/**
	 * color.
	 * 
	 * @param color
	 *            the color to set
	 * @since JDK 1.6
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * style.
	 * 
	 * @return the style
	 * @since JDK 1.6
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * style.
	 * 
	 * @param style
	 *            the style to set
	 * @since JDK 1.6
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * kind.
	 * 
	 * @return the kind
	 * @since JDK 1.6
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * kind.
	 * 
	 * @param kind
	 *            the kind to set
	 * @since JDK 1.6
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * size.
	 * 
	 * @return the size
	 * @since JDK 1.6
	 */
	public String getSize() {
		return size;
	}

	/**
	 * size.
	 * 
	 * @param size
	 *            the size to set
	 * @since JDK 1.6
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * material.
	 * 
	 * @return the material
	 * @since JDK 1.6
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * material.
	 * 
	 * @param material
	 *            the material to set
	 * @since JDK 1.6
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * co.
	 * 
	 * @return the co
	 * @since JDK 1.6
	 */
	public String getCo() {
		return co;
	}

	/**
	 * co.
	 * 
	 * @param co
	 *            the co to set
	 * @since JDK 1.6
	 */
	public void setCo(String co) {
		this.co = co;
	}

	/**
	 * productStandard.
	 * 
	 * @return the productStandard
	 * @since JDK 1.6
	 */
	public String getProductStandard() {
		return productStandard;
	}

	/**
	 * productStandard.
	 * 
	 * @param productStandard
	 *            the productStandard to set
	 * @since JDK 1.6
	 */
	public void setProductStandard(String productStandard) {
		this.productStandard = productStandard;
	}

	/**
	 * safetyStandard.
	 * 
	 * @return the safetyStandard
	 * @since JDK 1.6
	 */
	public String getSafetyStandard() {
		return safetyStandard;
	}

	/**
	 * safetyStandard.
	 * 
	 * @param safetyStandard
	 *            the safetyStandard to set
	 * @since JDK 1.6
	 */
	public void setSafetyStandard(String safetyStandard) {
		this.safetyStandard = safetyStandard;
	}

	/**
	 * level.
	 * 
	 * @return the level
	 * @since JDK 1.6
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * level.
	 * 
	 * @param level
	 *            the level to set
	 * @since JDK 1.6
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * purchasingAgent.
	 * 
	 * @return the purchasingAgent
	 * @since JDK 1.6
	 */
	public String getPurchasingAgent() {
		return purchasingAgent;
	}

	/**
	 * purchasingAgent.
	 * 
	 * @param purchasingAgent
	 *            the purchasingAgent to set
	 * @since JDK 1.6
	 */
	public void setPurchasingAgent(String purchasingAgent) {
		this.purchasingAgent = purchasingAgent;
	}

	/**
	 * description.
	 * 
	 * @return the description
	 * @since JDK 1.6
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * description.
	 * 
	 * @param description
	 *            the description to set
	 * @since JDK 1.6
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * createDate.
	 * 
	 * @return the createDate
	 * @since JDK 1.6
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * createDate.
	 * 
	 * @param createDate
	 *            the createDate to set
	 * @since JDK 1.6
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * allInventory.
	 * 
	 * @return the allInventory
	 * @since JDK 1.6
	 */
	public Integer getAllInventory() {
		return allInventory;
	}

	/**
	 * allInventory.
	 * 
	 * @param allInventory
	 *            the allInventory to set
	 * @since JDK 1.6
	 */
	public void setAllInventory(Integer allInventory) {
		this.allInventory = allInventory;
	}

	/**
	 * saled.
	 * 
	 * @return the saled
	 * @since JDK 1.6
	 */
	public Integer getSaled() {
		return saled;
	}

	/**
	 * saled.
	 * 
	 * @param saled
	 *            the saled to set
	 * @since JDK 1.6
	 */
	public void setSaled(Integer saled) {
		this.saled = saled;
	}

	/**
	 * inventory.
	 * 
	 * @return the inventory
	 * @since JDK 1.6
	 */
	public Integer getInventory() {
		return inventory;
	}

	/**
	 * inventory.
	 * 
	 * @param inventory
	 *            the inventory to set
	 * @since JDK 1.6
	 */
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	/**
	 * brandPrice.
	 * 
	 * @return the brandPrice
	 * @since JDK 1.6
	 */
	public Double getBrandPrice() {
		return brandPrice;
	}

	/**
	 * brandPrice.
	 * 
	 * @param brandPrice
	 *            the brandPrice to set
	 * @since JDK 1.6
	 */
	public void setBrandPrice(Double brandPrice) {
		this.brandPrice = brandPrice;
	}

	/**
	 * discountPercent.
	 * 
	 * @return the discountPercent
	 * @since JDK 1.6
	 */
	public Double getDiscountPercent() {
		return discountPercent;
	}

	/**
	 * discountPercent.
	 * 
	 * @param discountPercent
	 *            the discountPercent to set
	 * @since JDK 1.6
	 */
	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	/**
	 * salePrice.
	 * 
	 * @return the salePrice
	 * @since JDK 1.6
	 */
	public Double getSalePrice() {
		return salePrice;
	}

	/**
	 * salePrice.
	 * 
	 * @param salePrice
	 *            the salePrice to set
	 * @since JDK 1.6
	 */
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

}
