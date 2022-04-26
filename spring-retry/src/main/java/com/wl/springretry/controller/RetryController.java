package com.wl.springretry.controller;

import com.wl.springretry.service.RetryRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangLong
 * @Description:
 * @date 2022/3/23 15:41
 */
@RestController
public class RetryController {

    private RetryRequestService retryRequestService;

    @Autowired
    public RetryController(RetryRequestService retryRequestService) {
        this.retryRequestService = retryRequestService;
    }

    public String doRequest(String param){

        String requestResult = retryRequestService.request(param);
        return "Response:"+requestResult;
    }

}
