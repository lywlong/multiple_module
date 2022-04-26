package com.wl.throughput.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/18 16:01
 */

@Slf4j
public class CustomAsyncHandlerInterceptor implements AsyncHandlerInterceptor {

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截之后，重新写回数据，将原来的内容换成如下字符串
        String resp = "Test info is AsyncHandlerInterceptor";
        response.setContentLength(resp.length());
        response.getOutputStream().write(resp.getBytes());
        log.info(Thread.currentThread().getName()+" 进入afterConcurrentHandlingStarted方法");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info(Thread.currentThread().getName()+" 服务调用完成，返回结果给客户端");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            System.out.println("发生异常："+ex.getMessage());
        }
    }
}
