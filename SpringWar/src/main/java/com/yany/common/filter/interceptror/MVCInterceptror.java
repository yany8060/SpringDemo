package com.yany.common.filter.interceptror;

import com.alibaba.fastjson.JSON;
import com.yany.common.filter.context.UrlSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yanyong on 2017/2/20.
 */
public class MVCInterceptror implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(MVCInterceptror.class);
    private String redirectUrl;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI().toString();
        String whiteList[] = UrlSet.getInstance().getWhilteUrlSet().toArray(new String[]{});
        logger.info("whiteList:{}", JSON.toJSONString(whiteList));
        logger.info("url:{}", url);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
