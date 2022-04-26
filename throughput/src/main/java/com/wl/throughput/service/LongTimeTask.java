package com.wl.throughput.service;

import org.springframework.web.context.request.async.DeferredResult;

public interface LongTimeTask {

    String longTimeTask(DeferredResult deferredResult);

}
