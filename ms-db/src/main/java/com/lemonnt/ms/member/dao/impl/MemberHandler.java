/**
 * @author : Gavin Li
 * @date   : Nov 1, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.member.dao.impl.MemberHandler
 */
package com.lemonnt.ms.member.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lemonnt.ms.common.util.Util;
import com.lemonnt.ms.member.bean.*;
import com.lemonnt.ms.member.dao.IMemberHandler;
import com.lemonnt.ms.product.bean.Products;

/**
 * @author : Gavin Li
 * @date   : Nov 1, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.member.dao.impl.MemberHandler
 */
@Component("memberHandler")
public class MemberHandler implements IMemberHandler{

    @Autowired
    private SqlSession mysqlSources;
    
    @Override
    public Integer insertMember(MemberBasicInformation memberBasicInformation) {
        return mysqlSources.insert(INSERT_MEMBER,memberBasicInformation);
        
    }

    @Override
    public Integer insertMemberProducts(ProductInformation productInformation) {
        return mysqlSources.insert(INSERT_MEMBER_PRODUCT,productInformation);
        
    }

    @Override
    public Long getMemberId() {
        return mysqlSources.selectOne(MEMBER_ID);
        
    }

    @Override
    public Integer checkMemberIsExist(MemberBasicInformation memberBasicInformation) {
        return mysqlSources.selectOne(MEMBER_IS_EXIST,memberBasicInformation);
        
    }

    @Override
    public List<Express> qryExpress() {
        return mysqlSources.selectList(QRY_EXPRESS);
        
    }

    @Override
    public List<Products> qryProduct(Map<String, Object> parameters,String sql) {
        if(parameters == null || parameters.isEmpty())
          return mysqlSources.selectList(sql);
        return mysqlSources.selectList(sql,parameters);
        
    }

	@Override
	public List<MemberBasicInformation> qryMemberBasicInformations(MemberQueryParameters memberQueryParameters) {
		List<MemberBasicInformation> memberBasicInformations = mysqlSources.selectList(QRY_MEMBER, memberQueryParameters);
		if(Util.isEmpty(memberBasicInformations)) return new ArrayList<MemberBasicInformation>();
		return memberBasicInformations;
	}

    @Override
    public List<ProductInformation> qryProductInformations(Map<String, Object> parameters) {
        List<ProductInformation> productInformations = mysqlSources.selectList(QRY_MEMBER_PRODUCT,parameters);
        if(Util.isEmpty(productInformations)) return new ArrayList<ProductInformation>();
        return productInformations;
    }

    @Override
    public Integer total4MemberBasicInformation(MemberQueryParameters memberQueryParameters) {
        return mysqlSources.selectOne(QRY_MEMBER_TOTAL,memberQueryParameters);
        
    }

    @Override
    public Integer delete(String sql,Map<String, Object> parameters) {
        return mysqlSources.delete(sql,parameters);
        
    }


}
