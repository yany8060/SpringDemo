package com.yany.common.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class WebContextUtil {

	private ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

	private Map<String, Object> getLocalMap() {

		if (threadLocal.get() == null) {
			threadLocal.set(new HashMap<String, Object>());
		}

		return threadLocal.get();
	}

	private WebContextUtil() {
	}

	private static class SingletonHolder {
		private static final WebContextUtil instance = new WebContextUtil();
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) SingletonHolder.instance.getLocalMap().get(HttpServletRequest.class.getName());
	}

	public static void setRequest(HttpServletRequest request) {
		SingletonHolder.instance.getLocalMap().put(HttpServletRequest.class.getName(), request);
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) SingletonHolder.instance.getLocalMap().get(HttpServletResponse.class.getName());
	}

	public static void setResponse(HttpServletResponse request) {
		SingletonHolder.instance.getLocalMap().put(HttpServletResponse.class.getName(), request);
	}
}