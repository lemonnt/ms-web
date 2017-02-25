/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.AccountController
 */
package com.lemonnt.ms.login;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lemonnt.ms.common.exception.AuthenticationException;
import com.lemonnt.ms.common.exception.DatabaseException;
import com.lemonnt.ms.common.util.Util;
import com.lemonnt.ms.login.bean.Account;
import com.lemonnt.ms.login.bean.AccountStatus;
import com.lemonnt.ms.login.service.IAccountService;
import com.lemonnt.ms.lsf.bean.MonitorTime;
import com.lemonnt.ms.lsf.bean.Page;
import com.lemonnt.ms.lsf.core.LSFSession;

/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.AccountController
 */
@Controller("account")
@RequestMapping("/login/")
public class AccountController extends LSFSession{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(AccountController.class);
    
    @Autowired
    private IAccountService accountService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Page("login")
    public void modelView(Model model){
        
    }

    
    @MonitorTime
    public String createUser(Account account) throws DatabaseException{
        try {
            Integer isRegister = accountService.isDupAccount(account);
            if(isRegister > 0)
                return AccountStatus.DUP.getName();
            if(accountService.createAccount(account) > 0)
                return AccountStatus.SUCCESS.getName();
            return AccountStatus.FAIL.getName();
        } catch (AuthenticationException e) {
            return AccountStatus.AUTHEXCEPTION.getName();
        }catch (DatabaseException e) {
            return AccountStatus.OTHER.getName();
        }
        
        
    }
    
    @MonitorTime
    public boolean login(Account account) throws AuthenticationException, DatabaseException{
        if(Util.isEmpty(account)) return false;    
        Account act = accountService.authenticateAccount(account);
        if(null != act){
            setAuthencationSid(account.getName()); 
            setLanguages(act.getLanguage());
            logger.info("UserName : "+account.getName()+" successed to login");
        }      
        return null != act;
    }
    
    @MonitorTime
    public String logout(Account account) throws AuthenticationException, DatabaseException{
        if(Util.isEmpty(account)) return null; 
        try {
            invalidate(account.getName());
            return Util.ipAddress();
        } catch (Exception e) {
            return null;
        }
        
    }

}
