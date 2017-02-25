/**
 * @author : Gavin Li
 * @date   : Nov 1, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.member.service.IMemberService
 */
package com.lemonnt.ms.member.service;

import java.util.List;

import com.lemonnt.ms.common.exception.DatabaseException;
import com.lemonnt.ms.lsf.bean.PaginationResultInfo;
import com.lemonnt.ms.member.bean.*;
import com.lemonnt.ms.product.bean.Products;

/**
 * @author : Gavin Li
 * @date   : Nov 1, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.member.service.IMemberService
 */
public interface IMemberService {
    Integer createMember(MemberBasicInformation memberBasicInformation) throws DatabaseException;
    Integer createMemberProducts(ProductInformation productInformation)  throws DatabaseException;
    Long createMemberID();
    List<Express> obtainExpress() throws DatabaseException;

    List<Products> obtainProduct(Products products) throws DatabaseException;
    
    List<Products> obtainProductName() throws DatabaseException;
    
    PaginationResultInfo<MemberBasicInformation> obtainMemberBasicInformations(MemberQueryParameters memberQueryParameters) throws DatabaseException;
    
    List<ProductInformation> obtainProductInformations(String memberId) throws DatabaseException;
    
    //删除会员
    Integer removeMember(String memberId) throws DatabaseException;
    
  //删除用户关联的产品
    Integer removeProduct(String productId) throws DatabaseException;
    
    //仅移除用户不移除关联产品
    public Integer updateMember(MemberBasicInformation memberBasicInformation) throws DatabaseException;
    
    //仅移除用户不移除关联产品
    public Integer updateProduct(ProductInformation productInformation,String productId) throws DatabaseException;

}
