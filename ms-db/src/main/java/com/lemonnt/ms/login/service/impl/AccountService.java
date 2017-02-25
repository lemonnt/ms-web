/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.service.impl.AccountService
 */
package com.lemonnt.ms.login.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lemonnt.ms.common.bean.AdvancedMatcher;
import com.lemonnt.ms.common.bean.Encryption;
import com.lemonnt.ms.common.bean.EncryptionType;
import com.lemonnt.ms.common.bean.RegExpression;
import com.lemonnt.ms.common.exception.AuthenticationException;
import com.lemonnt.ms.common.exception.DatabaseException;
import com.lemonnt.ms.common.util.Util;
import com.lemonnt.ms.login.bean.Account;
import com.lemonnt.ms.login.dao.IAccountHandler;
import com.lemonnt.ms.login.service.IAccountService;

/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.service.impl.AccountService
 */
@Component("accountService")
public class AccountService extends Encryption implements IAccountService{
    
    
    private static Logger logger = Logger.getLogger(AccountService.class);
    @Autowired
    private IAccountHandler accountHandler;

    @Override
    public Integer createAccount(Account account) throws AuthenticationException, DatabaseException{
        AdvancedMatcher advancedMatcher = new AdvancedMatcher();
        if(null == account || !advancedMatcher.match(account.getName(), RegExpression.ACCOUNT_NAME) ||
           !advancedMatcher.match(account.getPassword(), RegExpression.PASSWORD) ) 
            throw new AuthenticationException("Ivalid userName or Invalid password,please check it !");
        account.setCreatedate(new Date());
        try {
            account.setPassword(encryptionNormal(account.getPassword(), account.getName()));
            Integer count = accountHandler.insertAccount(account);
            if(count <= 0)
                throw new DatabaseException("Failed to create account,because of the db's problem");
            logger.info("success to insert account : [userName="+account.getName()+"]");
            return count;   
        } catch (Exception e) {
            throw new DatabaseException("Failed to create account,because of the db's problem");
        }    
    }

    @Override
    public Integer isDupAccount(Account account) throws DatabaseException {      
        if(Util.isEmpty(account)) return 0;
        try {
            Account dup = new Account();
            dup.setName(account.getName());
            Integer count = 0;
            Account act = accountHandler.qryAccount(dup);
            if(null != act){
            	count = 1;
            	logger.info("The username has been registered");
            }
                
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("Failed to authenticate account ...");
        }
        
        
        
    }

    @Override
    public Account authenticateAccount(Account account) throws DatabaseException {
        if(Util.isEmpty(account)) return account;
        try {
            account.setPassword(new Encryption(EncryptionType.MD5).encryptionNormal(account.getPassword(), account.getName()));
            return accountHandler.qryAccount(account);
        } catch (Exception e) {
            throw new DatabaseException("Failed to authenticate account ...",e);
        }
        
    }

}
