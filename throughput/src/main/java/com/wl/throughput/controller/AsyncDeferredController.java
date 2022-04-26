package com.wl.throughput.controller;

import com.wl.throughput.service.LongTimeTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/18 15:08
 */
@Slf4j
@RestController
public class AsyncDeferredController {

    private final LongTimeTask taskService;

    @Autowired
    public AsyncDeferredController(LongTimeTask taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/deferred")
    public DeferredResult<String> executeSlowTask(){
        log.info(Thread.currentThread().getName()+" 进入executeSlowTask方法");
        DeferredResult<String> deferredResult = new DeferredResult<>();
        //deferredResult.
        //调用长时间执行任务
        String info = taskService.longTimeTask(deferredResult);
        // 当长时间任务中使用deferred.setResult("world");这个方法时，
        // 会从长时间任务中返回，继续controller里面的流程
        log.info(Thread.currentThread().getName()+" 从executeSlowTask方法返回");
        //超时的回调方法
        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                log.info(Thread.currentThread().getName()+" onTimeout");
                //返回超时信息
                deferredResult.setErrorResult("time out!");
            }
        });
        // 处理完成的回调方法，无论是超时还是处理成功，都会进入这个回调方法
        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.info(Thread.currentThread().getName()+" onCompletion");
            }
        });
        return deferredResult;
    }
}
