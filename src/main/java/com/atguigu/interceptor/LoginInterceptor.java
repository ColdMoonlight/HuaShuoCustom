package com.atguigu.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/9/9.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger=LoggerFactory.getLogger(LoginInterceptor.class);
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //
        if ((!request.getRequestURI().contains("index")) && (!request.getRequestURI().contains("toMlbackAdminLogin")) && (!request.getRequestURI().contains("toCheakAdminUser")) && session.getAttribute("adminuser") == null) {
            response.sendRedirect(request.getContextPath()+"/MlbackAdmin/toMlbackAdminLogin");
        }
        logger.error("welcome to shabi room!");
        
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
