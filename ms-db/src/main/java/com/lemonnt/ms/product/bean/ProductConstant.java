package com.lemonnt.ms.product.bean;

public interface ProductConstant {
	public static final String PRODUCT_EX = "product_ex";

	//add product
	public static final String  INSERT_PRODUCT_SUPPLIER = PRODUCT_EX+".insertSupplierProduct";
	
	public static final String  INSERT_SUPPLIER = PRODUCT_EX+".insertSupplier";
	
	//insertInsaleProduct
	public static final String  INSERT_INSALE_PRODUCT = PRODUCT_EX+".insertInsaleProduct";
	
	public static final String  QRY_SUPPLIER_IS_EXIST = PRODUCT_EX+".checkSupplier";
	
	public static final String QRY_SUPPLIER_INFORMATION = PRODUCT_EX+".showSupplier";
	
	//insertSupplierAssistantProduct
	public static final String INSERT_ASSISTANT_PRODUCT_INFORMATION = PRODUCT_EX+".insertSupplierAssistantProduct";
	
    //showSupplierTotal
	public static final String SHOW_SUPPLIER_TOTAL = PRODUCT_EX+".showSupplierTotal";
	
	public static final String SET_STATUS_INSALE_PROCUREMENT = PRODUCT_EX+".setStatusInSaleForProcurement";

	public static final String SHOW_SUPPLIER_PRODUCT =  PRODUCT_EX+".showSupplierProduct";
	
	public static final String SHOW_SUPPLIER_PRODUCT_TOTAL = PRODUCT_EX+".showSupplierProductTotal";
	
	public static final String DELETE_SUPPLIER = PRODUCT_EX+".deleteSupplier";
	
	public static final String DELTETE_SUPPLIER_PRODUCT = PRODUCT_EX+".deleteSupplierProduct";
	
	//deleteOnlySupplierProduct
	public static final String DELTETE_ONLY_SUPPLIER_PRODUCT = PRODUCT_EX+".deleteOnlySupplierProduct";
	
	public static final String SHOW_ALL_SUPPLIER_PRODUCT=PRODUCT_EX+".showAllSupplierProduct";
	
	public static final String SHOW_ALL_SUPPLIER_PRODUCT_TOTAL_NUMBER=PRODUCT_EX+".showAllSupplierProductTotal";
	
	public static final String QRY_ALL_INSALE_PRODUCT_TOTAL_NUMBER=PRODUCT_EX+".showInsaleProductTotalNumber";
	
	public static final String QRY_ALL_INSALE_PRODUCT=PRODUCT_EX+".showInsaleProduct";
	
	public static final String DELETE_INSALED_PRODUCT = PRODUCT_EX+".deleteInsaleProduct";
	
	public static final String 	UPDATE_SUPPLIER =  PRODUCT_EX+".updateSupplier";
	
	public static final String UPDATE_SUPPLEIR_PRODUCT = PRODUCT_EX+".updateSupplierProduct";
	
	public static final String UPDATE_INSALE_PRODUCT = PRODUCT_EX+".updateInsaleProduct";
}
