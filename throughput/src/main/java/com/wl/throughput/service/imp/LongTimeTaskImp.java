package com.wl.throughput.service.imp;

import com.wl.throughput.service.LongTimeTask;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/18 15:42
 */
@Service
public class LongTimeTaskImp implements LongTimeTask {

    @Override
    public String longTimeTask(DeferredResult deferredResult) {
        return "长时间任务执行方法:"+deferredResult.getResult();
    }
}
