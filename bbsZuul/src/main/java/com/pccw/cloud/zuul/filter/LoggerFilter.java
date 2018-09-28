package com.pccw.cloud.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class LoggerFilter extends ZuulFilter{
	
	private Logger log_ = LoggerFactory.getLogger(LoggerFilter.class);
	
	@Autowired
	Tracer tracer;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String access_token = request.getParameter("access_token");
		tracer.addTag("token",access_token);
		log_.info("access_token is {}",access_token);
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 800;
	}

}
