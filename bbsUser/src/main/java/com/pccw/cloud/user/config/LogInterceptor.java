package com.pccw.cloud.user.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 定义日志拦截器
 * */

public class LogInterceptor extends HandlerInterceptorAdapter {
	
	private final static String TraceId = "traceId";
	
	@Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        MDC.remove(TraceId);
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
    }
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String traceId = null;
		try {
			//获取从ZUUL网关传递的参数
			InputStream in = request.getInputStream();
			String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
			if(StringUtils.isNotBlank(body)){
				JsonObject json = new JsonParser().parse(body).getAsJsonObject();
				traceId = json.get("traceId").getAsString();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
        MDC.put(TraceId, traceId);
        return true;
    }
	
}
