/**  
 * Project Name:ms-db  
 * File Name:IProductHandler.java  
 * Package Name:com.lemonnt.ms.product.dao  
 * Date:Dec 7, 20168:56:10 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: ms-db  
 * File Name: IProductHandler.java  
 * Package Name: com.lemonnt.ms.product.dao  
 * Date: Dec 7, 20168:56:10 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.product.dao;

import java.util.List;
import java.util.Map;

import com.lemonnt.ms.product.bean.*;

/**  
 * ClassName: IProductHandler <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 7, 2016 8:56:10 PM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
public interface IProductHandler extends ProductConstant{
	
	/**
	 * 
	 * TODO
	 * @Description: TODO add new product from the supplier
	 * @author gavli  
	 * @param products
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer addProduct(ProcurementProducts products);
	
	
	/**
	 * 
	 * TODO
	 * @Description: TODO add new assiatant product from the supplier
	 * @author gavli  
	 * @param products
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer addAssistantProduct(ProcurementAssistantProducts products);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO add the supplier
	 * @author gavli  
	 * @param supplier
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer addSupplier(Supplier supplier);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO add the product 2 the real platform ,including eltroci 
	 * @author gavli  
	 * @param insaleProducts
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer addInsaleProduct(InsaleProducts insaleProducts);
	
	
	/**
	 * 
	 * TODO
	 * @Description: TODO
	 * @author gavli  
	 * @param supplier
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer checkSupplierIsExist(Supplier supplier);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO 查询supplier
	 * @author gavli  
	 * @param supplier
	 * @return  
	 * @since JDK 1.6
	 */
	public List<Supplier> qrySupplier(ProductParameters productParameters);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO supplier 分页总数
	 * @author gavli  
	 * @param productParameters
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer totalSupplier(ProductParameters productParameters);
	
	/**
	 * 
	 * TODO
	 * @Description: update the produrement's status when it was set into sale
	 * @author gavli  
	 * @param paramenters
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer updateStatusForProcurement(Map<String, String> paramenters);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO query the products from supplier
	 * @author gavli  
	 * @param productParameters
	 * @return  
	 * @since JDK 1.6
	 */
	public List<ProcurementProducts> qrySupplierProduct(ProductParameters productParameters);

	/**
	 * 
	 * TODO
	 * @Description: TODO the total number for products are from the supplier
	 * @author gavli  
	 * @param productParameters
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer totalSupplierProduct(ProductParameters productParameters);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO delete supplier by supplierid
	 * @author gavli  
	 * @param parameters
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer deleteSupplier(Map<String, String> parameters);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO
	 * @author gavli  
	 * @param parameters
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer deleteSupplierProduct(Map<String, String> parameters);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO only to delete product form supplier
	 * @author gavli  
	 * @param parameters
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer deleteOnlySupplierProduct(Map<String, String> parameters);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO query all product in supplier ,not by id
	 * @author gavli  
	 * @param productParameters
	 * @return  
	 * @since JDK 1.6
	 */
	public List<ProcurementProducts> qryAllSupplierProduct(ProductParameters productParameters);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO
	 * @author gavli  
	 * @param productParameters
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer qryAllSupplierProductTotalNumber(ProductParameters productParameters);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO query the number of all products were in sale
	 * @author gavli  
	 * @param productParameters
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer qryAllInsaleProductTotalNumber(ProductParameters productParameters);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO query all products were in sale
	 * @author gavli  
	 * @param productParameters
	 * @return  
	 * @since JDK 1.6
	 */
	public List<InsaleProducts> qryAllInsaleProduct(ProductParameters productParameters);
	
	
	/**
	 * 
	 * TODO
	 * @Description: TODO delete product was in sale
	 * @author gavli  
	 * @param parameters
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer deleteProductInsale(Map<String, String> parameters);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO just update suppliers
	 * @author gavli  
	 * @param supplier
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer updateSupplier(Supplier supplier);
	
	/**
	 * 
	 * TODO udpate product from the supplier
	 * @Description: TODO
	 * @author gavli  
	 * @param procurementProducts
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer updateSupplierProduct(ProcurementProducts procurementProducts);
	
	/**
	 * 
	 * TODO
	 * @Description: TODO update the product have been in sale
	 * @author gavli  
	 * @param insaleProducts
	 * @return  
	 * @since JDK 1.6
	 */
	public Integer updateInsaleProduct(InsaleProducts insaleProducts);
}
  
