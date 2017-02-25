/**
 * @author : Gavin Li
 * @date   : Nov 1, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.lsf.core.LSFSessionListener
 */
package com.lemonnt.ms.common;

import javax.servlet.http.*;
import org.apache.log4j.Logger;
import com.lemonnt.ms.login.service.impl.AccountService;
import com.lemonnt.ms.lsf.core.LSFBeanStore;

/**
 * @author : Gavin Li
 * @date   : Nov 1, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.lsf.core.LSFSessionListener
 */
public class LSFSessionListener implements HttpSessionListener{
    
    private static Logger logger = Logger.getLogger(LSFSessionListener.class);
    
    private AccountService accountService;
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {       
        logger.info("There is new session was created ["+se.getSession().getId()+"]");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        String sid = session.getId();
        accountService = (AccountService) LSFBeanStore.getBean("accountService");
        System.out.println(sid+accountService);
        
    }

    

}
