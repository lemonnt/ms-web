/**
 * @author : Gavin Li
 * @date   : Nov 1, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.member.service.impl.MemberService
 */
package com.lemonnt.ms.member.service.impl;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lemonnt.ms.common.bean.*;
import com.lemonnt.ms.common.exception.DatabaseException;
import com.lemonnt.ms.common.search.SearchUtil;
import com.lemonnt.ms.common.util.Util;
import com.lemonnt.ms.lsf.bean.PaginationResultInfo;
import com.lemonnt.ms.member.bean.*;
import com.lemonnt.ms.member.dao.IMemberHandler;
import com.lemonnt.ms.member.service.IMemberService;
import com.lemonnt.ms.product.bean.Products;

/**
 * @author : Gavin Li
 * @date   : Nov 1, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.member.service.impl.MemberService
 */
@Component("memberService")
public class MemberService implements IMemberService,MemberConstant{
    
    private static Logger logger = Logger.getLogger(MemberService.class);
    
    @Autowired
    private IMemberHandler memberHandler;

    @Override
    public Integer createMember(MemberBasicInformation memberBasicInformation) throws DatabaseException{
        if(Util.isEmpty(memberBasicInformation)) throw new DatabaseException("The member can not be null");
        if(!new AdvancedMatcher(memberBasicInformation.getCellphoneNumber(),RegExpression.TELEPHONY_NUMBER).match())
            throw new DatabaseException("Invalid cellphone ,please check it !");
        if(memberHandler.checkMemberIsExist(memberBasicInformation) > 0) throw new DatabaseException("用户已经存在 !");
        memberBasicInformation.setCreatedDate(new Date());
        try {
            return memberHandler.insertMember(memberBasicInformation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("服务器繁忙，请稍后再试 !");
        }
        
        
    }

    @Override
    public Integer createMemberProducts(ProductInformation productInformation) throws DatabaseException {       
        if(Util.isEmpty(productInformation)) throw new DatabaseException("The member can not be null");
        if(!new AdvancedMatcher(productInformation.getCellphoneNumber(),RegExpression.TELEPHONY_NUMBER).match())
            throw new DatabaseException("Invalid cellphone ,please check it !");  
        try {
            if(null == productInformation.getPurchaseDate())
               productInformation.setPurchaseDate(new Date());
            return memberHandler.insertMemberProducts(productInformation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("服务器繁忙，请稍后再试 !");
        }
        
    }
    
    public String checkLength(Object value,Integer length) throws DatabaseException{
        if(Util.isEmpty(value)) return null;
        if(Util.byteLength(String.valueOf(value)) > length)
            throw new DatabaseException("The value ["+value+"] is to long");
        return String.valueOf(value);
    }
    
    public String checkNullAndLength(Object value,Integer length) throws DatabaseException{
        if(Util.isEmpty(value)) throw new DatabaseException("The value ["+value+"] can not be null");
        if(Util.byteLength(String.valueOf(value)) > length)
            throw new DatabaseException("The value ["+value+"] is to long");
        return String.valueOf(value);
    }

    @Override
    public Long createMemberID() {
        return memberHandler.getMemberId();
        
    }

    @Override
    public List<Express> obtainExpress() throws DatabaseException {
        try {
            List<Express> expressConfigurations = memberHandler.qryExpress();
            if(Util.isEmpty(expressConfigurations))
                return new ArrayList<Express>();
            return expressConfigurations;
        } catch (Exception e) {
            throw new DatabaseException("加载快递失败");
        }
        
        
    }

    @Override
    public List<Products> obtainProduct(Products product) throws DatabaseException {
        try {
            if(Util.isEmpty(product)) return new ArrayList<Products>();
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("name", product.getName());
            parameters.put("number", product.getNumber());
            List<Products> products = memberHandler.qryProduct(parameters,QRY_PRODUCT);
            if(Util.isEmpty(products))
                return new ArrayList<Products>();
            return products;
        } catch (Exception e) {
            throw new DatabaseException("加载产品名称失败");
        }
        
        
    }

    @Override
    public List<Products> obtainProductName() throws DatabaseException {
        try {
            List<Products> products = memberHandler.qryProduct(null,QRY_PRODUCTNAME);
            if(Util.isEmpty(products))
                return new ArrayList<Products>();
            return products;
        } catch (Exception e) {
            throw new DatabaseException("加载产品名称失败");
        }
        
    }

	@Override
	public PaginationResultInfo<MemberBasicInformation> obtainMemberBasicInformations(MemberQueryParameters memberQueryParameters) throws DatabaseException {
		try {
		    PaginationResultInfo<MemberBasicInformation> result = new PaginationResultInfo<MemberBasicInformation>();
	        if(Util.isEmpty(memberQueryParameters)) return result;
		    int from = 0, to = 0;
	        from = memberQueryParameters.getOffset();
	        to = from + memberQueryParameters.getLimit();
	        memberQueryParameters.setFrom(from);
	        memberQueryParameters.setTo(to);   
	        if(Util.isEmpty(memberQueryParameters.getSort())){
	            memberQueryParameters.setSort("CREATEDDATE DESC"); 
	        }else{
	            memberQueryParameters.setSort(memberQueryParameters.getSort()+" "+memberQueryParameters.getOrder()); 
	        }
	        if(!Util.isEmpty(memberQueryParameters.getSearch())) 
	            memberQueryParameters.setSearch(SearchUtil.search(memberQueryParameters.getSearch(), MemberBasicInformation.class));
	        if(Util.isEmpty(memberQueryParameters.getSearch())) memberQueryParameters.setSearch(null);	        
	        logger.info("The condition to query basic information of member is "+memberQueryParameters.toString());
	        List<MemberBasicInformation> memberBasicInformations = memberHandler.qryMemberBasicInformations(memberQueryParameters);
	        Integer total = memberHandler.total4MemberBasicInformation(memberQueryParameters);
	        result.setTotal(total);
	        result.setRows(memberBasicInformations);
			return result;
		} catch (Exception e) {
		    e.printStackTrace();
			throw new DatabaseException("查询用户失败 ！");
		}
		
	}

    @Override
    public List<ProductInformation> obtainProductInformations(String memberId)
            throws DatabaseException {
       if(Util.isEmpty(memberId)) return new ArrayList<ProductInformation>();
       Map<String, Object> parameters = new HashMap<String, Object>();
       parameters.put("memberid", memberId);
       try {
        return memberHandler.qryProductInformations(parameters);
       } catch (Exception e) {
           throw new DatabaseException("查询用户关联产品失败 ！");
       }
        
    }
    
    @Override
    public Integer removeMember(String memberId) throws DatabaseException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", Util.buildCondition4InStr(memberId, ","));
        logger.info("删除用户的ID为 ["+memberId+"]");
        try {
          //删除会员
            Integer delMembercount = memberHandler.delete(DEL_MEMBER, parameters);
            if(delMembercount > 0){
                Integer delMemberProductCount = memberHandler.delete(DEL_MEMBER_PRODUCT, parameters);
                logger.info("删除关联产品的数量为 : ["+delMemberProductCount+"]");
            }else{
                throw new DatabaseException("删除会员失败 !");
            }
            return delMembercount;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("删除会员失败 !");
        }
        
    }
    
    @Override
    public Integer updateMember(MemberBasicInformation memberBasicInformation) throws DatabaseException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        String memberId = memberBasicInformation.getId();
        parameters.put("id", Util.buildCondition4InStr(memberId, ","));
        logger.info("更新的用户ID为 ["+memberId+"]");
        try {
           //删除会员
            Integer count = 0;
            if(memberHandler.delete(DEL_MEMBER, parameters) > 0){
                count = createMember(memberBasicInformation);
            }else{
                throw new DatabaseException("更新失败 !");
            }
            if(count <= 0) throw new DatabaseException("更新失败 !");
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("更新失败 !");
        }
        
    }

    @Override
    public Integer removeProduct(String productId) throws DatabaseException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", productId);
        logger.info("删除产品的ID为 ["+productId+"]");
        try {
            Integer delMemberProductCount = memberHandler.delete(DEL_PRODUCT, parameters);
            if(delMemberProductCount <= 0)
                throw new DatabaseException("删除产品失败 !");
            return delMemberProductCount;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("删除产品失败 !");
        }
        
    }

    @Override
    public Integer updateProduct(ProductInformation productInformation,String productId) throws DatabaseException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        //实际上为product本身的ID
        parameters.put("id", productId);
        logger.info("删除产品的ID为 ["+productId+"]");
        try {
           //删除会员
            Integer count = 0;
            if(memberHandler.delete(DEL_PRODUCT, parameters) > 0){
                productInformation.setId(productId);
                count = createMemberProducts(productInformation);
            }else{
                throw new DatabaseException("更新失败 !");
            }
            if(count <= 0) throw new DatabaseException("更新失败 !");
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("更新失败 !");
        }
        
    }
    
   

}
