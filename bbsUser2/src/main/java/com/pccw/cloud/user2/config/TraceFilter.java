package com.pccw.cloud.user2.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class TraceFilter implements Filter {
	private Logger log_ = LoggerFactory.getLogger(TraceFilter.class);
	
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain
            filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        //从Feign拦截器的header中获取traceId
	    String traceId = request.getHeader("traceId");
	    MDC.put("traceId", traceId);
        //打印请求Url
        log_.info("this is TraceFilter,url is {}",request.getRequestURI());
        filterChain.doFilter(srequest, sresponse);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}