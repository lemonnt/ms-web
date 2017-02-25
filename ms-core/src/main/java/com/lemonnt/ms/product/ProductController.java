/**  
 * Project Name:ms-core  
 * File Name:ProductController.java  
 * Package Name:com.lemonnt.ms.product  
 * Date:Dec 27, 20161:45:03 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: ms-core  
 * File Name: ProductController.java  
 * Package Name: com.lemonnt.ms.product  
 * Date: Dec 27, 20161:45:03 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lemonnt.ms.common.exception.MatcherException;
import com.lemonnt.ms.common.exception.UtilException;
import com.lemonnt.ms.common.util.SerializateUtil;
import com.lemonnt.ms.common.util.Util;
import com.lemonnt.ms.exception.ProductException;
import com.lemonnt.ms.lsf.bean.MethodName;
import com.lemonnt.ms.lsf.bean.MonitorTime;
import com.lemonnt.ms.lsf.bean.PaginationResultInfo;
import com.lemonnt.ms.product.bean.InsaleProducts;
import com.lemonnt.ms.product.bean.ProcurementAssistantProducts;
import com.lemonnt.ms.product.bean.ProcurementProducts;
import com.lemonnt.ms.product.bean.ProductParameters;
import com.lemonnt.ms.product.bean.Supplier;
import com.lemonnt.ms.product.service.IProductService;

/**  
 * ClassName: ProductController <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 27, 2016 1:45:03 PM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
@Controller("product")
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	/**
	 * 
	 * TODO
	 * @Description: TODO 创建供应商以及在供应商下面采购的产品
	 * @author gavli  
	 * @param supplier
	 * @param procurementProducts
	 * @return
	 * @throws ProductException
	 * @throws MatcherException  
	 * @since JDK 1.6
	 */
	@MethodName("createSupplierAndProduct")
	public String createSupplierAndProduct(Supplier supplier,ProcurementProducts procurementProducts) throws ProductException, MatcherException{
        String supplierId = productService.addSupplier(supplier);
		if(Util.isEmpty(supplierId)) return null;
		procurementProducts.setSupplierId(supplierId);
		String id = productService.addProduct(procurementProducts);
		return null != id ? supplierId+"#"+id : null;
	}
	
	/**
	 * 
	 * TODO
	 * @Description: TODO 创建供应商以及在供应商下面采购的辅材产品
	 * @author gavli  
	 * @param supplier
	 * @param procurementProducts
	 * @return
	 * @throws ProductException
	 * @throws MatcherException  
	 * @since JDK 1.6
	 */
	@MethodName("createSupplierAndAssistantProduct")
	public String createSupplierAndProduct(Supplier supplier,ProcurementAssistantProducts procurementAssistantProducts) throws ProductException, MatcherException{
        String supplierId = productService.addSupplier(supplier);
		if(Util.isEmpty(supplierId)) return null;
		procurementAssistantProducts.setSupplierId(supplierId);
		return productService.addAssistantProduct(procurementAssistantProducts) > 0 ? supplierId : null;
	}
	
	/**
	 * 
	 * TODO
	 * @Description: TODO  仅仅创建在某供应商下的产品
	 * @author gavli  
	 * @param procurementProducts
	 * @return
	 * @throws ProductException
	 * @throws MatcherException  
	 * @since JDK 1.6
	 */
	@MethodName("createProcurementAssistantProduct")
	public boolean createProcurementProduct(ProcurementAssistantProducts procurementAssistantProducts) throws ProductException, MatcherException{
		return productService.addAssistantProduct(procurementAssistantProducts) > 0;
	}
	
	/**
	 * 
	 * TODO
	 * @Description: TODO  仅仅创建在某供应商下的辅材产品
	 * @author gavli  
	 * @param procurementProducts
	 * @return
	 * @throws ProductException
	 * @throws MatcherException  
	 * @since JDK 1.6
	 */
	@MethodName("createProcurementProduct")
	public String createProcurementProduct(ProcurementProducts procurementProducts) throws ProductException, MatcherException{
		return productService.addProduct(procurementProducts);
	}
	
	/**
	 * 
	 * TODO
	 * @Description: TODO 上架产品
	 * @author gavli  
	 * @param insaleProducts
	 * @return
	 * @throws ProductException
	 * @throws MatcherException  
	 * @since JDK 1.6
	 */
	public boolean createInsaleProduct(InsaleProducts insaleProducts) throws ProductException, MatcherException{
		return productService.addInsaleProduct(insaleProducts) > 0;
	}

    @RequestMapping(value = "supplierL1", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String showSupplierL1(ProductParameters productParameters)throws ProductException, MatcherException{
    	PaginationResultInfo<Supplier> result = null;
    	result =productService.showSupplier(productParameters);
    	try {
			return SerializateUtil.toJson(result);
		} catch (UtilException e) {
			throw new ProductException(2002); 
			
		} 
	}
    
    @RequestMapping(value = "procurementProductL2", produces = {"application/json;charset=UTF-8"})
 	@ResponseBody
 	public String showSupplierProductL2(ProductParameters productParameters)throws ProductException, MatcherException{
     	PaginationResultInfo<ProcurementProducts> result = null;
     	result =productService.showSupplierProduct(productParameters);
     	try {
 			return SerializateUtil.toJson(result);
 		} catch (UtilException e) {
 			throw new ProductException(2002); 
 			
 		} 
 	}
    
    @RequestMapping(value = "showAllSupplierProduct", produces = {"application/json;charset=UTF-8"})
 	@ResponseBody
 	public String showAllSupplierProduct(ProductParameters productParameters)throws ProductException, MatcherException{
     	PaginationResultInfo<ProcurementProducts> result = null;
     	result =productService.showAllSupplierProduct(productParameters);
     	try {
 			return SerializateUtil.toJson(result);
 		} catch (UtilException e) {
 			throw new ProductException(2002); 
 			
 		} 
 	}
    
    @RequestMapping(value = "showAllInsaleProduct", produces = {"application/json;charset=UTF-8"})
  	@ResponseBody
  	public String showAllInsaleProduct(ProductParameters productParameters)throws ProductException, MatcherException{
      	PaginationResultInfo<InsaleProducts> result = null;
      	result =productService.showAllInsaleProduct(productParameters);
      	try {
  			return SerializateUtil.toJson(result);
  		} catch (UtilException e) {
  			throw new ProductException(2002); 
  			
  		} 
  	}
    
    @MonitorTime
    public boolean invalidSupplier(String id) throws ProductException{
    	return productService.invalidSupplier(id) > 0;
    }
    
    @MonitorTime
    public boolean invalidSupplierProduct(String id) throws ProductException{
    	return productService.invalidSupplierProduct(id) > 0;
    }
    
    @MonitorTime
    public boolean updateSupplier(Supplier supplier) throws ProductException, MatcherException{
    	if(Util.isEmpty(supplier)) throw new ProductException(1000);
        return productService.updateSupplier(supplier) > 0;
    }
    
    @MonitorTime
    public boolean updateSupplierProduct(ProcurementProducts procurementProducts) throws ProductException, MatcherException{
    	if(Util.isEmpty(procurementProducts)) throw new ProductException(1000);
        return productService.updateSupplierProduct(procurementProducts) > 0;
    }
    
    @MonitorTime
    public boolean updateInsaleProduct(InsaleProducts insaleProducts) throws ProductException, MatcherException{
        return productService.updateInsaleProduct(insaleProducts) > 0;
    }
    
    
    @MonitorTime
    public boolean invalidInsaledProduct(String id) throws ProductException, MatcherException{
    	return productService.invalidProductInsale(id) > 0;
    }
}
  
