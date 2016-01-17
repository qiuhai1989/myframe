package com.haiqiu.interceptor;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by T430S on 2015/12/13.
 */
public class MyInterceptor3 extends HandlerInterceptorAdapter {
    private NamedThreadLocal<Long> starTimeThreadLocal = new NamedThreadLocal<>("StopWatch-StartTime");
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(this.getClass().getSimpleName()+"---preHandle()...");
        long  beginTime = System.currentTimeMillis();
        starTimeThreadLocal.set(beginTime);
        return super.preHandle(request, response, handler);
    }

    public MyInterceptor3() {
        super();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(this.getClass().getSimpleName()+"---postHandle()...");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(this.getClass().getSimpleName()+"---afterCompletion()...");
        long endTime = System.currentTimeMillis();
        long beginTime = starTimeThreadLocal.get();
        long consumeTime = endTime-beginTime;
        System.out.println("consumeTime="+consumeTime);
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(this.getClass().getSimpleName()+"---afterConcurrentHandlingStarted()...");
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
