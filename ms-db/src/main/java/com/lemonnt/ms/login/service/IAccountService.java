/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.service.IAccountService
 */
package com.lemonnt.ms.login.service;

import com.lemonnt.ms.common.exception.AuthenticationException;
import com.lemonnt.ms.common.exception.DatabaseException;
import com.lemonnt.ms.login.bean.Account;

/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.service.IAccountService
 */
public interface IAccountService {
    
    Integer createAccount(Account account) throws AuthenticationException,DatabaseException;
    
    Integer isDupAccount(Account account) throws DatabaseException;
    
    Account authenticateAccount(Account account) throws DatabaseException;

}
