package com.ww.mtt.common;

import com.ww.mtt.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Map;

@Component
public class HttpInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);
    private static final String START_TIME = "requestTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        Map params = request.getParameterMap();
        BufferedReader parameterMap= request.getReader();
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME,start);
        logger.info("request start-->url:{}, params:{}",url, JsonUtil.obj2String(params));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /*String url = request.getRequestURI();
        long start = (long) request.getAttribute(START_TIME);
        long time = System.currentTimeMillis()-start;
        logger.info("request end-->url:{},time:{}",url, time);*/
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI();
        long start = (long) request.getAttribute(START_TIME);
        long time = System.currentTimeMillis()-start;
        logger.info("request completion-->url:{}, runTime:{}ms",url, time);
    }
}
