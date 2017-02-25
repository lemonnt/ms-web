package com.lemonnt.ms.shiro.relam;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;

public class DBRealm implements Realm{

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {

		return null;
	}

	@Override
	public String getName() {

		return null;
	}

	@Override
	public boolean supports(AuthenticationToken arg0) {

		return false;
	}

}
