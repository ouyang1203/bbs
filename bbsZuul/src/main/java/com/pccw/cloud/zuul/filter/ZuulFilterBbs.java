package com.pccw.cloud.zuul.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.google.gson.JsonObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

/**
filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下： 
	pre：路由之前
	routing：路由之时
	post： 路由之后
	error：发送错误调用
	filterOrder：过滤的顺序
	shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
	run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
 * */
@Component
public class ZuulFilterBbs extends ZuulFilter {

	private Logger log_ = LoggerFactory.getLogger(ZuulFilterBbs.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String uri = request.getRequestURL().toString();
		log_.info("current request uri is {},request method is {}",uri,request.getMethod());
		String traceId = UUID.randomUUID().toString().replace("-", "");
		MDC.put("traceId",traceId);
		Object accessToken = request.getParameter("access_token");
		if(null==accessToken) {
			log_.info("token is empty");
			ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){
            	
            }
		}
		try {
			//给后续请求添加参数
			InputStream in = request.getInputStream();
			String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
			if(StringUtils.isBlank(body)){
			    body = "{}";
			}
			JsonObject obj = new JsonObject();
			obj.addProperty("traceId", traceId);
			obj.addProperty("accessToken", accessToken.toString());
			String newBody = obj.toString();
			log_.info("zuul newBody is {}",newBody);
			final byte[] reqBodyBytes = newBody.getBytes();
			ctx.setRequest(new HttpServletRequestWrapper(request){    
			    @Override
			    public ServletInputStream getInputStream() throws IOException {
			      return new ServletInputStreamWrapper(reqBodyBytes);
			    }
			    @Override
			    public int getContentLength() {
			      return reqBodyBytes.length;
			    }
			    @Override
			    public long getContentLengthLong() {
			      return reqBodyBytes.length;
			    }
			  });
		} catch (IOException e) {
			e.printStackTrace();
		}
		log_.info("ok");
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
