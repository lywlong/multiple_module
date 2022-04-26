package com.wl.springretry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.MediaSize;

/**
 * @author WangLong
 * @Description:
 * @date 2022/3/23 15:36
 */
@Service
@Slf4j
public class RetryRequestService {


    private OtherSystemSpi otherSystemSpi;

    @Autowired
    public RetryRequestService(OtherSystemSpi otherSystemSpi) {
        this.otherSystemSpi = otherSystemSpi;
    }

    @Retryable(value = RuntimeException.class,maxAttempts = 5,backoff = @Backoff(delay = 100))
    public String request(String param){

        double random = Math.random();
        log.info("random value:"+random);
        if (random > 0.1){
            throw new RuntimeException("超时");
        }
        return otherSystemSpi.request(param);
    }

}
