/**
 * @author : Gavin Li
 * @date   : Nov 5, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.member.bean.ProductNumberConfiguration
 */
package com.lemonnt.ms.product.bean;

import java.util.Date;

import com.lemonnt.ms.common.bean.Matcher;
import com.lemonnt.ms.common.bean.RegExpression;

/**
 * @author : Gavin Li
 * @date   : Nov 5, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.member.bean.ProductNumberConfiguration
 */
public class Products {
	@Matcher(expression=RegExpression.NotNull)
    private String name;
    //商品编号
	@Matcher(expression=RegExpression.NotNull)
    private String number;
    //商品库存
	@Matcher("!<=9999")
    private Integer inventory;
	@Matcher("!<=99999")
    private Double inprice;
	@Matcher("!<=99999")
    private Double outprice;
	@Matcher("!<=99999")
    private Double brandPrice;
	@Matcher(expression=RegExpression.NotNull)
    private String brand;
    //男装，女装，男童装，女童装
	@Matcher(expression=RegExpression.NotNull)
    private String kind;
	@Matcher(expression=RegExpression.NotNull)
    private String supplier;
    //供应商产品编号
    private String supplierNumber;
    @Matcher(expression=RegExpression.NotNull)
    private String status;
    private Date start;
    private Date end;
    private Date createDate;
    private String style;
    @Matcher(expression=RegExpression.NotNull)
    private String color;
    @Matcher(expression=RegExpression.NotNull)
    private String size;
    //谁上货的
    private String owner;
    private String barCode;
    //备注
    private String description;
    
    //销量
    private Integer outNumber;
    
    
    
    public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public String getSupplierNumber() {
		return supplierNumber;
	}
	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getOutNumber() {
		return outNumber;
	}
	public void setOutNumber(Integer outNumber) {
		this.outNumber = outNumber;
	}
	public Double getBrandPrice() {
		return brandPrice;
	}
	public void setBrandPrice(Double brandPrice) {
		this.brandPrice = brandPrice;
	}
	/**
     * @return the size
     */
    public String getSize() {
        return size;
    }
    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }
    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }
    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }
    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }
    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }
    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }
    /**
     * @return the inprice
     */
    public Double getInprice() {
        return inprice;
    }
    /**
     * @param inprice the inprice to set
     */
    public void setInprice(Double inprice) {
        this.inprice = inprice;
    }
    /**
     * @return the outprice
     */
    public Double getOutprice() {
        return outprice;
    }
    /**
     * @param outprice the outprice to set
     */
    public void setOutprice(Double outprice) {
        this.outprice = outprice;
    }
    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }
    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
    /**
     * @return the kind
     */
    public String getKind() {
        return kind;
    }
    /**
     * @param kind the kind to set
     */
    public void setKind(String kind) {
        this.kind = kind;
    }
    /**
     * @return the supplier
     */
    public String getSupplier() {
        return supplier;
    }
    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return the start
     */
    public Date getStart() {
        return start;
    }
    /**
     * @param start the start to set
     */
    public void setStart(Date start) {
        this.start = start;
    }
    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }
    /**
     * @param end the end to set
     */
    public void setEnd(Date end) {
        this.end = end;
    }
    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }
    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    
    
   
    
    
    

}
