/**  
 * Project Name: ms-db  
 * File Name: ProductServiceHanlder.java  
 * Package Name: com.lemonnt.ms.product.service.impl  
 * Date: Dec 13, 201610:03:36 AM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */

package com.lemonnt.ms.product.service.impl;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.lemonnt.ms.common.bean.AdvancedMatcher;
import com.lemonnt.ms.common.exception.MatcherException;
import com.lemonnt.ms.common.search.SearchUtil;
import com.lemonnt.ms.common.util.Util;
import com.lemonnt.ms.exception.ProductException;
import com.lemonnt.ms.lsf.bean.PaginationResultInfo;
import com.lemonnt.ms.product.bean.InsaleProducts;
import com.lemonnt.ms.product.bean.ProcurementAssistantProducts;
import com.lemonnt.ms.product.bean.ProcurementProducts;
import com.lemonnt.ms.product.bean.ProductParameters;
import com.lemonnt.ms.product.bean.Supplier;
import com.lemonnt.ms.product.dao.IProductHandler;
import com.lemonnt.ms.product.service.IProductService;

/**
 * ClassName: ProductServiceHanlder <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 13, 2016 10:03:36 AM <br/>
 * 
 * @author gavli
 * @version 1.0.0
 * @since JDK 1.6
 */
@Component("productService")
public class ProductServiceHanlder implements IProductService {

	private static Logger logger = Logger.getLogger(ProductServiceHanlder.class);

	@Autowired
	private IProductHandler productHandler;

	/**
	 * TODO
	 * 
	 * @throws ProductException
	 * @throws MatcherException
	 * @see com.lemonnt.ms.product.dao.IProductHandler#addProduct(com.lemonnt.ms.product.bean.Products)
	 */
	@Override
	public String addProduct(ProcurementProducts products) throws ProductException, MatcherException {
		if (Util.isEmpty(products))
			throw new ProductException(1000);
		new AdvancedMatcher().match(products);
		products.setCreateDate(new Date());
		try {
			return productHandler.addProduct(products) > 0 ? products.getId() : null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException(1002);
		}

	}

	/**
	 * TODO
	 * 
	 * @see com.lemonnt.ms.product.service.IProductService#addProduct(com.lemonnt.ms.product.bean.Supplier)
	 */
	@Override
	public String addSupplier(Supplier supplier) throws ProductException, MatcherException {
		if (Util.isEmpty(supplier))
			throw new ProductException(1000);
		String id = String.valueOf(UUID.randomUUID()).replaceAll("-", "");
		supplier.setId(id);
		supplier.setCreateDate(new Date());
		new AdvancedMatcher().match(supplier);
		if (checkSupplierIsExist(supplier))
			throw new ProductException(1003);
		try {
			return productHandler.addSupplier(supplier) > 0 ? id : null;
		} catch (Exception e) {
			throw new ProductException(1002);
		}

	}
	
	@Override
	public Integer updateSupplier(Supplier supplier) throws ProductException, MatcherException{
		if (Util.isEmpty(supplier))
			throw new ProductException(1000);
		new AdvancedMatcher().match(supplier);
		try {
			return productHandler.updateSupplier(supplier);
		} catch (Exception e) {
			throw new ProductException(1002);
		}
	}
	

	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.service.IProductService#updateSupplierProduct(com.lemonnt.ms.product.bean.ProcurementProducts)  
	 */
	@Override
	public Integer updateSupplierProduct(ProcurementProducts procurementProducts)
			throws ProductException, MatcherException {
		if (Util.isEmpty(procurementProducts))
			throw new ProductException(1000);
		new AdvancedMatcher().match(procurementProducts);
		try {
			procurementProducts.setStatus(0);
			return productHandler.updateSupplierProduct(procurementProducts);
		} catch (Exception e) {
			throw new ProductException(1002);
		}
	}
	

	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.service.IProductService#updateInsaleProduct(com.lemonnt.ms.product.bean.InsaleProducts)  
	 */
	@Override
	public Integer updateInsaleProduct(InsaleProducts insaleProducts) throws ProductException, MatcherException {
		if (Util.isEmpty(insaleProducts))
			throw new ProductException(1000);
		new AdvancedMatcher().match(insaleProducts);
		try {
			return productHandler.updateInsaleProduct(insaleProducts);
		} catch (Exception e) {
			throw new ProductException(1002);
		}
	}
	

	/**
	 * TODO
	 * 
	 * @see com.lemonnt.ms.product.service.IProductService#addInsaleProduct(com.lemonnt.ms.product.bean.InsaleProducts)
	 */
	@Override
	public Integer addInsaleProduct(InsaleProducts insaleProducts) throws ProductException, MatcherException {
		if (Util.isEmpty(insaleProducts))
			throw new ProductException(1000);
		new AdvancedMatcher().match(insaleProducts);
		insaleProducts.setCreateDate(new Date());
		try {
			//不应该更新数据
			return productHandler.addInsaleProduct(insaleProducts) > 0
					? setStatusForProcurement(insaleProducts.getProductId()) : 0;
			//return productHandler.addInsaleProduct(insaleProducts);
		} catch (Exception e) {
			throw new ProductException(1002);
		}

	}

	/**
	 * TODO
	 * 
	 * @throws ProductException
	 * @see com.lemonnt.ms.product.service.IProductService#checkSupplierIsExist(com.lemonnt.ms.product.bean.Supplier)
	 */
	@Override
	public boolean checkSupplierIsExist(Supplier supplier) throws ProductException {
		try {
			return productHandler.checkSupplierIsExist(supplier) > 0;
		} catch (Exception e) {
			throw new ProductException(1002);
		}

	}

	/**
	 * TODO
	 * 
	 * @see com.lemonnt.ms.product.service.IProductService#addAssistantProduct(com.lemonnt.ms.product.bean.ProcurementAssistantProducts)
	 */
	@Override
	public Integer addAssistantProduct(ProcurementAssistantProducts products)
			throws ProductException, MatcherException {

		if (Util.isEmpty(products))
			throw new ProductException(1000);
		new AdvancedMatcher().match(products);
		products.setCreateDate(new Date());
		try {
			return productHandler.addAssistantProduct(products);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException(1002);
		}

	}

	/**
	 * TODO
	 * 
	 * @see com.lemonnt.ms.product.service.IProductService#showSupplier(com.lemonnt.ms.product.bean.ProductParameters)
	 */
	@Override
	public PaginationResultInfo<Supplier> showSupplier(ProductParameters productParameters)
			throws ProductException, MatcherException {
		if (Util.isEmpty(productParameters))
			throw new ProductException(2001);
		new AdvancedMatcher().match(productParameters);
		PaginationResultInfo<Supplier> result = new PaginationResultInfo<Supplier>();
		try {
			int from = 0, to = 0;
			from = productParameters.getOffset();
			to = from + productParameters.getLimit();
			productParameters.setFrom(from);
			productParameters.setTo(to);
			if (Util.isEmpty(productParameters.getSort())) {
				productParameters.setSort("PROCUREMENTDATE DESC");
			} else {
				productParameters.setSort(productParameters.getSort() + " " + productParameters.getOrder());
			}
			if (!Util.isEmpty(productParameters.getSearch()))
				productParameters.setSearch(SearchUtil.search(productParameters.getSearch(), Supplier.class));
			if (Util.isEmpty(productParameters.getSearch()))
				productParameters.setSearch(null);
			logger.info("The condition to query basic information of product is " + productParameters.toString());
			List<Supplier> suppliers = productHandler.qrySupplier(productParameters);
			Integer total = productHandler.totalSupplier(productParameters);
			result.setTotal(total);
			result.setRows(suppliers);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException(1002);
		}
	}

	/**
	 * TODO
	 * 
	 * @see com.lemonnt.ms.product.service.IProductService#setStatusForProcurement(java.lang.String)
	 */
	@Override
	public Integer setStatusForProcurement(String id) {
		Map<String, String> paramenters = new HashMap<String, String>();
		paramenters.put("id", id);
		return productHandler.updateStatusForProcurement(paramenters);
	}

	/**
	 * TODO
	 * 
	 * @see com.lemonnt.ms.product.service.IProductService#showSupplierProduct(com.lemonnt.ms.product.bean.ProductParameters)
	 */
	@Override
	public PaginationResultInfo<ProcurementProducts> showSupplierProduct(ProductParameters productParameters)
			throws ProductException, MatcherException {
		if (Util.isEmpty(productParameters))
			throw new ProductException(2001);
		new AdvancedMatcher().match(productParameters);
		PaginationResultInfo<ProcurementProducts> result = new PaginationResultInfo<ProcurementProducts>();
		try {
			int from = 0, to = 0;
			from = productParameters.getOffset();
			to = from + productParameters.getLimit();
			productParameters.setFrom(from);
			productParameters.setTo(to);
			if (Util.isEmpty(productParameters.getSort())) {
				productParameters.setSort("PROCUREMENTDATE DESC");
			} else {
				productParameters.setSort(productParameters.getSort() + " " + productParameters.getOrder());
			}
			if (!Util.isEmpty(productParameters.getSearch()))
				productParameters
						.setSearch(SearchUtil.search(productParameters.getSearch(), ProcurementProducts.class));
			if (Util.isEmpty(productParameters.getSearch()))
				productParameters.setSearch(null);
			logger.info("The condition to query basic information of product is " + productParameters.toString());
			List<ProcurementProducts> supplierProducts = productHandler.qrySupplierProduct(productParameters);
			Integer total = productHandler.totalSupplierProduct(productParameters);
			result.setTotal(total);
			result.setRows(supplierProducts);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException(1002);
		}
	}

	/**
	 * TODO
	 * 
	 * @throws ProductException
	 * @see com.lemonnt.ms.product.service.IProductService#invalidSupplier(java.lang.String)
	 */
	@Override
	public Integer invalidSupplier(String id) throws ProductException {
		if (Util.isEmpty(id))
			throw new ProductException(3001);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", Util.buildCondition4InStr(id, ","));
		logger.info("删除的供应商ID为 : " + parameters.toString());
		try {
			Integer delSuppliercount = productHandler.deleteSupplier(parameters);
			if (delSuppliercount > 0) {
				Integer count = productHandler.deleteSupplierProduct(parameters);
				logger.info("删除的产品数量为 : " + count);
			} else {
				throw new ProductException(1002);
			}
			return delSuppliercount;
		} catch (Exception e) {
			throw new ProductException(1002);
		}

	}

	/**
	 * TODO
	 * 
	 * @see com.lemonnt.ms.product.service.IProductService#invalidSupplierProduct(java.lang.String)
	 */
	@Override
	public Integer invalidSupplierProduct(String id) throws ProductException {
		if (Util.isEmpty(id))
			throw new ProductException(3001);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", Util.buildCondition4InStr(id, ","));
		logger.info("删除的ID为 : "+parameters.toString());
		try {
			Integer count = productHandler.deleteOnlySupplierProduct(parameters);
			logger.info("删除的产品数量为 : " + count);
			return count;
		} catch (Exception e) {
			throw new ProductException(1002);
		}

	}

	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.service.IProductService#showAllSupplierProduct(com.lemonnt.ms.product.bean.ProductParameters)  
	 */
	@Override
	public PaginationResultInfo<ProcurementProducts> showAllSupplierProduct(ProductParameters productParameters)
			throws ProductException, MatcherException {
		if (Util.isEmpty(productParameters))
			throw new ProductException(2001);
		new AdvancedMatcher().match(productParameters);
		PaginationResultInfo<ProcurementProducts> result = new PaginationResultInfo<ProcurementProducts>();
		try {
			int from = 0, to = 0;
			from = productParameters.getOffset();
			to = from + productParameters.getLimit();
			productParameters.setFrom(from);
			productParameters.setTo(to);
			if (Util.isEmpty(productParameters.getSort())) {
				productParameters.setSort("PROCUREMENTDATE DESC");
			} else {
				productParameters.setSort(productParameters.getSort() + " " + productParameters.getOrder());
			}
			switchSearch(productParameters);
			if (!Util.isEmpty(productParameters.getSearch()))
				productParameters
						.setSearch(SearchUtil.search(productParameters.getSearch(), ProcurementProducts.class));
			if (Util.isEmpty(productParameters.getSearch()))
				productParameters.setSearch(null);
			logger.info("The condition to query basic information of product is " + productParameters.toString());
			List<ProcurementProducts> supplierProducts = productHandler.qryAllSupplierProduct(productParameters);
			Integer total = productHandler.qryAllSupplierProductTotalNumber(productParameters);
			result.setTotal(total);
			result.setRows(supplierProducts);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException(1002);
		}
	}
	
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.service.IProductService#showAllInsaleProduct(com.lemonnt.ms.product.bean.ProductParameters)  
	 */
	@Override
	public PaginationResultInfo<InsaleProducts> showAllInsaleProduct(ProductParameters productParameters)
			throws ProductException, MatcherException {	  
		if (Util.isEmpty(productParameters))
			throw new ProductException(2001);
		new AdvancedMatcher().match(productParameters);
		PaginationResultInfo<InsaleProducts> result = new PaginationResultInfo<InsaleProducts>();
		try {
			int from = 0, to = 0;
			from = productParameters.getOffset();
			to = from + productParameters.getLimit();
			productParameters.setFrom(from);
			productParameters.setTo(to);
			if (Util.isEmpty(productParameters.getSort())) {
				productParameters.setSort("CREATEDATE DESC");
			} else {
				productParameters.setSort(productParameters.getSort() + " " + productParameters.getOrder());
			}
			if (!Util.isEmpty(productParameters.getSearch()))
				productParameters
						.setSearch(SearchUtil.search(productParameters.getSearch(), InsaleProducts.class));
			if (Util.isEmpty(productParameters.getSearch()))
				productParameters.setSearch(null);
			logger.info("The condition to query basic information of product is " + productParameters.toString());
			List<InsaleProducts> supplierProducts = productHandler.qryAllInsaleProduct(productParameters);
			Integer total = productHandler.qryAllInsaleProductTotalNumber(productParameters);
			result.setTotal(total);
			result.setRows(supplierProducts);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException(1002);
		}
	}
	

	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.service.IProductService#invalidProductInsale(java.lang.String)  
	 */
	@Override
	public Integer invalidProductInsale(String id) throws ProductException, MatcherException {
		if (Util.isEmpty(id))
			throw new ProductException(3001);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", Util.buildCondition4InNumber(id, ","));
		logger.info("删除的ID为 : "+parameters.toString());
		try {
			Integer count = productHandler.deleteProductInsale(parameters);
			logger.info("删除的产品数量为 : " + count);
			return count;
		} catch (Exception e) {
			throw new ProductException(1002);
		}

	}
	
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.service.IProductService#invalidSupplierOnly(java.lang.String)  
	 */
	@Override
	public Integer invalidSupplierOnly(String id) throws ProductException, MatcherException {
		if (Util.isEmpty(id))
			throw new ProductException(3001);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", Util.buildCondition4InStr(id, ","));
		logger.info("删除的供应商ID为 : " + parameters.toString());
		try {
			return productHandler.deleteSupplier(parameters);
		} catch (Exception e) {
			throw new ProductException(1002);
		}
	}




	
	private void switchSearch(ProductParameters productParameters){
		if(Util.isEmpty(productParameters.getSearch())) return ;
		if(productParameters.getSearch().equals("主品")){
			productParameters.setSearch("procurementType:0");
		}else if(productParameters.getSearch().equals("辅材") || productParameters.getSearch().equals("其他")){
			productParameters.setSearch("procurementType:1");
		}else if(productParameters.getSearch().equals("通过")){
			productParameters.setSearch("status:1");
		}else if(productParameters.getSearch().equals("审核中")){
			productParameters.setSearch("status:0");
		}else if(productParameters.getSearch().equals("不通过")){
			productParameters.setSearch("status:2");
		}else if(productParameters.getSearch().equals("上架")){
			productParameters.setSearch("isInsaled:1");
		}else if(productParameters.getSearch().equals("未上架")){
			productParameters.setSearch("isInsaled:0");
		}
	}

	
//	private void switchSearch2(ProductParameters productParameters){
//		if(Util.isEmpty(productParameters.getSearch())) return ;
//		if(productParameters.getSearch().equals("主品")){
//			productParameters.setSearch("procurementType:0");
//		}else if(productParameters.getSearch().equals("辅材") || productParameters.getSearch().equals("其他")){
//			productParameters.setSearch("procurementType:1");
//		}else if(productParameters.getSearch().equals("通过")){
//			productParameters.setSearch("status:1");
//		}else if(productParameters.getSearch().equals("审核中")){
//			productParameters.setSearch("status:0");
//		}else if(productParameters.getSearch().equals("不通过")){
//			productParameters.setSearch("status:2");
//		}else if(productParameters.getSearch().equals("上架")){
//			productParameters.setSearch("isInsaled:1");
//		}else if(productParameters.getSearch().equals("未上架")){
//			productParameters.setSearch("isInsaled:0");
//		}
//	}


	
}
