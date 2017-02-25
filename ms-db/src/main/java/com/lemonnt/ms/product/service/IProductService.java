/**  
 * Project Name:ms-db  
 * File Name:IProductService.java  
 * Package Name:com.lemonnt.ms.product.service  
 * Date:Dec 7, 20168:56:40 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: ms-db  
 * File Name: IProductService.java  
 * Package Name: com.lemonnt.ms.product.service  
 * Date: Dec 7, 20168:56:40 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.product.service;

import com.lemonnt.ms.common.exception.MatcherException;
import com.lemonnt.ms.exception.ProductException;
import com.lemonnt.ms.lsf.bean.PaginationResultInfo;
import com.lemonnt.ms.product.bean.InsaleProducts;
import com.lemonnt.ms.product.bean.ProcurementAssistantProducts;
import com.lemonnt.ms.product.bean.ProcurementProducts;
import com.lemonnt.ms.product.bean.ProductParameters;
import com.lemonnt.ms.product.bean.Supplier;

/**  
 * ClassName: IProductService <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 7, 2016 8:56:40 PM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
public interface IProductService {
	public String addProduct(ProcurementProducts products) throws ProductException,MatcherException;
	
	public Integer addAssistantProduct(ProcurementAssistantProducts products) throws ProductException,MatcherException;
	
	public String addSupplier(Supplier supplier)  throws ProductException,MatcherException;
	
	public Integer addInsaleProduct(InsaleProducts insaleProducts) throws ProductException,MatcherException;
	
	public boolean checkSupplierIsExist(Supplier supplier)  throws ProductException;
	
	public PaginationResultInfo<Supplier> showSupplier(ProductParameters productParameters)throws ProductException,MatcherException;

	public Integer setStatusForProcurement(String id);
	
	public PaginationResultInfo<ProcurementProducts> showSupplierProduct(ProductParameters productParameters) throws ProductException,MatcherException;
    
	public Integer invalidSupplier(String id) throws ProductException;
	
	public Integer invalidSupplierProduct(String id) throws ProductException;
	
	public PaginationResultInfo<ProcurementProducts> showAllSupplierProduct(ProductParameters productParameters) throws ProductException,MatcherException;
	
	public PaginationResultInfo<InsaleProducts> showAllInsaleProduct(ProductParameters productParameters) throws ProductException,MatcherException;
	
	public Integer invalidProductInsale(String id) throws ProductException,MatcherException;
	
	public Integer invalidSupplierOnly(String id) throws ProductException,MatcherException;
	
	public Integer updateSupplier(Supplier supplier) throws ProductException,MatcherException;
	
	public Integer updateSupplierProduct(ProcurementProducts procurementProducts) throws ProductException,MatcherException;
    
	public Integer updateInsaleProduct(InsaleProducts insaleProducts) throws ProductException,MatcherException;
}
  
