/**  
 * Project Name:ms-db  
 * File Name:Supplier.java  
 * Package Name:com.lemonnt.ms.product.bean  
 * Date:Dec 27, 201610:50:36 AM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: ms-db  
 * File Name: Supplier.java  
 * Package Name: com.lemonnt.ms.product.bean  
 * Date: Dec 27, 201610:50:36 AM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.product.bean;

import java.util.Date;

import com.lemonnt.ms.common.bean.AdvancedDate;
import com.lemonnt.ms.common.bean.Matcher;
import com.lemonnt.ms.common.search.Search;

/**  
 * ClassName: Supplier <br/>
 * Function: TODO supplier object <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 27, 2016 10:50:36 AM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
public class Supplier {
	
	private String id;
	@Search
	@Matcher("^\\S{2,30}$")
	private String name;
	@Search
	@Matcher("^\\S{2,30}$")
	private String brand;
	@Search
	@Matcher("^\\S{4,50}$")
	private String contact;
	@Matcher("^\\S{2,50}$")
	@Search
	private String address;
	@Search
	private String level;
	@Search
	private String description;
	@Search
	private Integer number;
	@Search
	private Date createDate;
	@Search
	private Date procurementDate;
	
	private Date lastModifiedDate;
private String updatePeople;
	
	
	
	
	
	
	/**  
	 * updatePeople.  
	 *  
	 * @return  the updatePeople  
	 * @since   JDK 1.6  
	 */
	public String getUpdatePeople() {
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
	 * number.  
	 *  
	 * @return  the number  
	 * @since   JDK 1.6  
	 */
	public Integer getNumber() {
		return number;
	}
	/**  
	 * number.  
	 *  
	 * @param   number    the number to set  
	 * @since   JDK 1.6  
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**  
	 * id.  
	 *  
	 * @return  the id  
	 * @since   JDK 1.6  
	 */
	public String getId() {
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
	 * brand.  
	 *  
	 * @return  the brand  
	 * @since   JDK 1.6  
	 */
	public String getBrand() {
		return brand;
	}
	/**  
	 * brand.  
	 *  
	 * @param   brand    the brand to set  
	 * @since   JDK 1.6  
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**  
	 * contact.  
	 *  
	 * @return  the contact  
	 * @since   JDK 1.6  
	 */
	public String getContact() {
		return contact;
	}
	/**  
	 * contact.  
	 *  
	 * @param   contact    the contact to set  
	 * @since   JDK 1.6  
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**  
	 * address.  
	 *  
	 * @return  the address  
	 * @since   JDK 1.6  
	 */
	public String getAddress() {
		return address;
	}
	/**  
	 * address.  
	 *  
	 * @param   address    the address to set  
	 * @since   JDK 1.6  
	 */
	public void setAddress(String address) {
		this.address = address;
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
  
