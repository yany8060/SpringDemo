package com.yany.common.filter.webfilter;


import com.yany.common.aop.WebContextUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebContextFilter implements Filter {

  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    try {

      WebContextUtil.setRequest((HttpServletRequest) request);
      WebContextUtil.setResponse((HttpServletResponse) response);

    } finally {
      chain.doFilter(request, response);
    }
  }

  @Override
  public void destroy() {}
}
