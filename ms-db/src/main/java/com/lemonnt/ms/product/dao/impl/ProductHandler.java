/**  
 * Project Name:ms-db  
 * File Name:ProductHandler.java  
 * Package Name:com.lemonnt.ms.product.dao.impl  
 * Date:Dec 7, 20168:59:05 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: ms-db  
 * File Name: ProductHandler.java  
 * Package Name: com.lemonnt.ms.product.dao.impl  
 * Date: Dec 7, 20168:59:05 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.product.dao.impl;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.lemonnt.ms.product.bean.InsaleProducts;
import com.lemonnt.ms.product.bean.ProcurementAssistantProducts;
import com.lemonnt.ms.product.bean.ProcurementProducts;
import com.lemonnt.ms.product.bean.ProductParameters;
import com.lemonnt.ms.product.bean.Supplier;
import com.lemonnt.ms.product.dao.IProductHandler;


/**  
 * ClassName: ProductHandler <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 7, 2016 8:59:05 PM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
@Component("productHandler")
public class ProductHandler implements IProductHandler {

	@Autowired
    private SqlSession mysqlSources;
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#addProduct(com.lemonnt.ms.product.bean.Products)  
	 */
	@Override
	public Integer addProduct(ProcurementProducts products) {		  
		return mysqlSources.insert(INSERT_PRODUCT_SUPPLIER, products);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#addProduct(com.lemonnt.ms.product.bean.Supplier)  
	 */
	@Override
	public Integer addSupplier(Supplier supplier) {
		return mysqlSources.insert(INSERT_SUPPLIER, supplier);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#addInsaleProduct(com.lemonnt.ms.product.bean.InsaleProducts)  
	 */
	@Override
	public Integer addInsaleProduct(InsaleProducts insaleProducts) {
		return mysqlSources.insert(INSERT_INSALE_PRODUCT, insaleProducts);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#checkSupplierIsExist(com.lemonnt.ms.product.bean.Supplier)  
	 */
	@Override
	public Integer checkSupplierIsExist(Supplier supplier) {
		return mysqlSources.selectOne(QRY_SUPPLIER_IS_EXIST,supplier);
	}

	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#addAssistantProduct(com.lemonnt.ms.product.bean.ProcurementAssistantProducts)  
	 */
	@Override
	public Integer addAssistantProduct(ProcurementAssistantProducts products) {
		return mysqlSources.insert(INSERT_ASSISTANT_PRODUCT_INFORMATION, products);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#qrySupplier(com.lemonnt.ms.product.bean.Supplier)  
	 */
	@Override
	public List<Supplier> qrySupplier(ProductParameters productParameters) {
		 return mysqlSources.selectList(QRY_SUPPLIER_INFORMATION,productParameters);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#totalSupplier(com.lemonnt.ms.product.bean.ProductParameters)  
	 */
	@Override
	public Integer totalSupplier(ProductParameters productParameters) {
		return mysqlSources.selectOne(SHOW_SUPPLIER_TOTAL,productParameters);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#updateStatusForProcurement(java.util.Map)  
	 */
	@Override
	public Integer updateStatusForProcurement(Map<String, String> paramenters) {
		return mysqlSources.update(SET_STATUS_INSALE_PROCUREMENT,paramenters);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#qrySupplierProduct(com.lemonnt.ms.product.bean.ProductParameters)  
	 */
	@Override
	public List<ProcurementProducts> qrySupplierProduct(ProductParameters productParameters) {
		return mysqlSources.selectList(SHOW_SUPPLIER_PRODUCT,productParameters);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#totalSupplierProduct(com.lemonnt.ms.product.bean.ProductParameters)  
	 */
	@Override
	public Integer totalSupplierProduct(ProductParameters productParameters) {
		return mysqlSources.selectOne(SHOW_SUPPLIER_PRODUCT_TOTAL,productParameters);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#deleteSupplier(java.util.Map)  
	 */
	@Override
	public Integer deleteSupplier(Map<String, String> parameters) {
		return mysqlSources.delete(DELETE_SUPPLIER,parameters);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#deleteSupplierProduct(java.util.Map)  
	 */
	@Override
	public Integer deleteSupplierProduct(Map<String, String> parameters) {
		return mysqlSources.delete(DELTETE_SUPPLIER_PRODUCT,parameters);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#deleteOnlySupplierProduct(java.util.Map)  
	 */
	@Override
	public Integer deleteOnlySupplierProduct(Map<String, String> parameters) {
		 return mysqlSources.delete(DELTETE_ONLY_SUPPLIER_PRODUCT,parameters);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#showAllSupplierProduct(com.lemonnt.ms.product.bean.ProductParameters)  
	 */
	@Override
	public List<ProcurementProducts> qryAllSupplierProduct(ProductParameters productParameters) {
		 return mysqlSources.selectList(SHOW_ALL_SUPPLIER_PRODUCT,productParameters);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#qryAllSupplierProductTotalNumber(com.lemonnt.ms.product.bean.ProductParameters)  
	 */
	@Override
	public Integer qryAllSupplierProductTotalNumber(ProductParameters productParameters) {
		  
		return mysqlSources.selectOne(SHOW_ALL_SUPPLIER_PRODUCT_TOTAL_NUMBER,productParameters);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#qryAllInsaleProductTotalNumber(com.lemonnt.ms.product.bean.ProductParameters)  
	 */
	@Override
	public Integer qryAllInsaleProductTotalNumber(ProductParameters productParameters) {
		 return mysqlSources.selectOne(QRY_ALL_INSALE_PRODUCT_TOTAL_NUMBER,productParameters);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#qryAllInsaleProduct(com.lemonnt.ms.product.bean.ProductParameters)  
	 */
	@Override
	public List<InsaleProducts> qryAllInsaleProduct(ProductParameters productParameters) {
		return mysqlSources.selectList(QRY_ALL_INSALE_PRODUCT,productParameters);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#deleteProductInsale(com.lemonnt.ms.product.bean.ProductParameters)  
	 */
	@Override
	public Integer deleteProductInsale(Map<String, String> parameters) {
		return mysqlSources.delete(DELETE_INSALED_PRODUCT,parameters);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#updateSupplier(com.lemonnt.ms.product.bean.Supplier)  
	 */
	@Override
	public Integer updateSupplier(Supplier supplier) {
		return mysqlSources.update(UPDATE_SUPPLIER,supplier);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#updateSupplierProduct(com.lemonnt.ms.product.bean.ProcurementProducts)  
	 */
	@Override
	public Integer updateSupplierProduct(ProcurementProducts procurementProducts) {
		return mysqlSources.update(UPDATE_SUPPLEIR_PRODUCT,procurementProducts);
	}
	/**  
	 * TODO
	 * @see com.lemonnt.ms.product.dao.IProductHandler#updateInsaleProduct(com.lemonnt.ms.product.bean.InsaleProducts)  
	 */
	@Override
	public Integer updateInsaleProduct(InsaleProducts insaleProducts) {
		 return mysqlSources.update(UPDATE_INSALE_PRODUCT,insaleProducts);
	}


}
  
