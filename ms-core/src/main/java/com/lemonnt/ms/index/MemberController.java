/**
 * @author : Gavin Li
 * @date   : Nov 1, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.index.MemberController
 */
package com.lemonnt.ms.index;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.lemonnt.ms.common.exception.*;
import com.lemonnt.ms.common.util.*;
import com.lemonnt.ms.lsf.bean.*;
import com.lemonnt.ms.member.bean.*;
import com.lemonnt.ms.member.service.IMemberService;
import com.lemonnt.ms.product.bean.Products;

/**
 * @author : Gavin Li
 * @date   : Nov 1, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.index.MemberController
 */
@Controller("member")
@RequestMapping("/member")
public class MemberController {
    
    @Autowired
    private IMemberService memberService;
    
    @MonitorTime
    public String buildMember(MemberBasicInformation memberBasicInformation) throws DatabaseException{
        String id = String.valueOf(UUID.randomUUID()).replace("-", "");
        memberBasicInformation.setId(id);
        Integer count = memberService.createMember(memberBasicInformation);
        if(count > 0)  return id;
        return null;
    }

    @MonitorTime
    public Integer buildMemberProducts(ProductInformation productInformation) throws DatabaseException{
        Integer count = memberService.createMemberProducts(productInformation);
        return count;
    }
    
    @MonitorTime
    public List<Products> product(Products products) throws DatabaseException{
        if(Util.isEmpty(products)) return new ArrayList<Products>();
        List<Products> productConfigurations = memberService.obtainProduct(products);
        return productConfigurations;
    }
    
    
    @MonitorTime
    public List<Products> productName() throws DatabaseException{
        List<Products> productConfigurations = memberService.obtainProductName();
        return productConfigurations;
    }
    
    
    @MonitorTime
    public List<Express> express() throws DatabaseException{
        List<Express> expresses = memberService.obtainExpress();
        return expresses;
    }
    
    @RequestMapping(value = "memberL1", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String memberBasicInformations(MemberQueryParameters memberQueryParameters) throws DatabaseException{
    	PaginationResultInfo<MemberBasicInformation> result = null;
        result = memberService.obtainMemberBasicInformations(memberQueryParameters);
    	try {
    	    return SerializateUtil.toJson(result); 
        } catch (Exception e) {
            throw new DatabaseException("获取成员失败 !");
        }
    }
    
    
    @RequestMapping(value = "memberL2", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String memberProductInformations(String memberId) throws DatabaseException{
        ResponseObj<List<ProductInformation>> productInformations = new ResponseObj<List<ProductInformation>>();
        List<ProductInformation> memberd = memberService.obtainProductInformations(memberId);
        productInformations.setData(memberd);
        try {
            String result = SerializateUtil.toJson(productInformations); 
            return result;
        } catch (Exception e) {
            throw new DatabaseException("获取用户关联产品失败 !");
        }
    }
    
    @MonitorTime
    public Integer invalidateMember(String memberId) throws DatabaseException{
        if(Util.isEmpty(memberId)) return 0;
        Integer result = memberService.removeMember(memberId);
        return result;
    }
    
    @MonitorTime
    public Integer removeProduct(String productId) throws DatabaseException{
        if(Util.isEmpty(productId)) return 0;
        Integer result = memberService.removeProduct(productId);
        return result;
    }
    
    @MonitorTime
    public Integer updateMember(MemberBasicInformation memberBasicInformation) throws DatabaseException{
        return memberService.updateMember(memberBasicInformation);
    }
    
    @MonitorTime
    public Integer updateProduct(ProductInformation productInformation,String productId) throws DatabaseException{
        return memberService.updateProduct(productInformation,productId);
    }
    
    
    public static void main(String[] args) throws UtilException {
        ResponseObj<List<MemberBasicInformation>> reb = new ResponseObj<List<MemberBasicInformation>>();
        MemberBasicInformation memberBasicInformation  = new MemberBasicInformation();
        memberBasicInformation.setAddress("中文");
        List<MemberBasicInformation> memberBasicInformations = new ArrayList<MemberBasicInformation>();
        memberBasicInformations.add(memberBasicInformation);
        reb.setData(memberBasicInformations);
        System.out.println(SerializateUtil.toJson(reb));
    }

}
