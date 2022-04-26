package com.wl.springretry;

import com.wl.springretry.controller.RetryController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
@Slf4j
public class SpringRetryApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringRetryApplication.class, args);
        String result = applicationContext.getBean(RetryController.class).doRequest("retry test");
        log.info(result);
    }

}
