/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.dao.AccountHandler
 */
package com.lemonnt.ms.login.dao;

import com.lemonnt.ms.login.bean.Account;
import com.lemonnt.ms.login.bean.RegisterConstant;

/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.dao.AccountHandler
 */
public interface IAccountHandler extends RegisterConstant{
    
    /**
     * 注册用户
     */
    Integer insertAccount(Account account) ;
    
    /**
     * 查询用户
     */
    Account qryAccount(Account account);

    
    /**
     * 更新用户的登录状态
     */
    Integer updateLoginStatus(Account account);

}
