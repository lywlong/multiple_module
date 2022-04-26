package com.wl.throughput.controller;

import com.wl.throughput.service.TestDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/18 11:47
 */
@Slf4j
@RestController
public class DemoController {

    private TestDemoService demoService;

    @Autowired
    public DemoController(TestDemoService demoService) {
        this.demoService = demoService;
    }

    /**
     * 异步调用restful
     * 当controller返回值是Callable的时候，springmvc就会启动一个线程将Callable交给TaskExecutor去处理
     * 然后DispatcherServlet还有所有的spring拦截器都退出主线程，然后把response保持打开的状态
     * 当Callable执行结束之后，springmvc就会重新启动分配一个request请求，然后DispatcherServlet就重新
     * 调用和处理Callable异步执行的返回结果， 然后返回视图
     *
     * @return
     */
    @GetMapping("/call-able")
    public Callable<String> demoController(){
        log.info(Thread.currentThread().getName()+" 进入demoController方法");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info(Thread.currentThread().getName()+"进入call方法");
                String str = demoService.testDemo();;
                log.info(Thread.currentThread().getName()+" testDemoService方法返回");
                return str;
            }
        };
        log.info(Thread.currentThread().getName()+" 从demoController方法返回");
        return callable;
    }

    /**
     * 带超时时间的异步请求 通过WebAsyncTask自定义客户端超时间
     *
     * @return
     */
    @GetMapping("/web-async")
    public WebAsyncTask<String> demo2Controller(){

        log.info(Thread.currentThread().getName()+" 进入demoController方法2");
        //3s钟没返回就认为超时
        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(3000, new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info(Thread.currentThread().getName()+" 进入call方法");
                String testInfo = demoService.testDemo();
                log.info(Thread.currentThread().getName()+" 从testDemoService方法返回");
                return testInfo;
            }
        });
        log.info(Thread.currentThread().getName()+" 从demoController方法返回2");
        webAsyncTask.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.info(Thread.currentThread().getName()+" 执行完毕");
            }
        });
        webAsyncTask.onTimeout(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info(Thread.currentThread().getName()+" 超时。。。");
                // 超时的时候，直接抛异常，让外层统一处理超时异常
                throw new TimeoutException("调用超时");
            }
        });
        return webAsyncTask;
    }

    @GetMapping("/exception")
    public WebAsyncTask<String> exceptionController(){
        log.info(Thread.currentThread().getName()+" 进入demoController访求3");
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                log.info(Thread.currentThread().getName()+" 进入call方法。");
                throw new TimeoutException("调用超时");
            }
        };
        log.info(Thread.currentThread().getName()+" 从demoController方法返回");
        return new WebAsyncTask<>(20000,callable);
    }

}
