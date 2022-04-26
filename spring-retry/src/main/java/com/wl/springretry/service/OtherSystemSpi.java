package com.wl.springretry.service;

import org.springframework.stereotype.Component;

/**
 * @author WangLong
 * @Description:
 * @date 2022/3/23 15:37
 */
@Component
public class OtherSystemSpi {

    public String request(String otherParam){

        return "other system spi:"+otherParam;

    }

}
