/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.dao.impl.AccountHandler
 */
package com.lemonnt.ms.login.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lemonnt.ms.login.bean.Account;
import com.lemonnt.ms.login.dao.IAccountHandler;

/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.dao.impl.AccountHandler
 */
@Component("accountHandler")
public class AccountHandler implements IAccountHandler{

    @Autowired
    private SqlSession mysqlSources;
    @Override
    public Integer insertAccount(Account account) {
        return mysqlSources.insert(INSERT_ACCOUNT, account);
    }
    @Override
    public Account qryAccount(Account account) {
        return mysqlSources.selectOne(QUERY_ACCOUNT,account);
        
    }
    @Override
    public Integer updateLoginStatus(Account account) {
        return mysqlSources.update(UPDATE_ACCOUNT_STATU, account);
        
    }

}
