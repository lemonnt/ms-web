package com.lemonnt.ms.lsf.util;

import com.lemonnt.ms.lsf.bean.RequestContext;

/**
 * 
 * @author : Gavin Li
 * @date   : Jun 13, 2016
 * @class  :com.cisco.webex.daily.util.RequestContextUtil
 */
public class RequestContextUtil {

	static final ThreadLocal<RequestContext> context = new ThreadLocal<RequestContext>() {
		@Override
		protected RequestContext initialValue() {
			return new RequestContext();
		}
	};

	public static void setPort(String port) {
		RequestContext requestContext = context.get();
		requestContext.setPort(port);
	}

	public static String getPort() {
		RequestContext requestContext = context.get();
		return requestContext.getPort();
	}

	public static void setHost(String host) {
		RequestContext requestContext = context.get();
		requestContext.setHost(host);
	}

	public static String getHost() {
		RequestContext requestContext = context.get();
		return requestContext.getHost();
	}
}
