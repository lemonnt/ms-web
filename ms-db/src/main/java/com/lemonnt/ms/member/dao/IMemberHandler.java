/**
 * @author : Gavin Li
 * @date   : Nov 1, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.member.dao.IMember
 */
package com.lemonnt.ms.member.dao;

import java.util.List;
import java.util.Map;

import com.lemonnt.ms.member.bean.*;
import com.lemonnt.ms.product.bean.Products;

/**
 * @author : Gavin Li
 * @date   : Nov 1, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.member.dao.IMember
 */
public interface IMemberHandler extends MemberConstant{
    
    Integer insertMember(MemberBasicInformation memberBasicInformation);
    Integer insertMemberProducts(ProductInformation productInformation);
    Long getMemberId();
    Integer checkMemberIsExist(MemberBasicInformation memberBasicInformation);
    List<Express> qryExpress();
    List<Products> qryProduct(Map<String, Object> parameters,String sql);
    List<MemberBasicInformation> qryMemberBasicInformations(MemberQueryParameters memberQueryParameters);
    List<ProductInformation> qryProductInformations(Map<String, Object> parameters);
    Integer total4MemberBasicInformation(MemberQueryParameters memberQueryParameters);
    Integer delete(String sql,Map<String, Object> parameters);
    
}
