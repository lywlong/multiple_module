package com.wl.dpuspringbootstarter.service;

import java.time.LocalDateTime;

/**
 * @author WangLong
 * @Description:
 * @date 2022/3/21 15:29
 */
public abstract class AbstractDateProcessService {

    private boolean format;
    private String pattern;

    public AbstractDateProcessService(boolean format, String pattern) {
        this.format = format;
        this.pattern = pattern;
    }

    //根据yyyy-MM-dd
    abstract String formatDateToyMd(LocalDateTime localDateTime);

    //根据yyyy-MM-dd HH:mm:ss
    //yyyy-MM-dd HH:mm:ss:代表将时间转换为24小时制,例: 2018-06-27 15:24:21
    //yyyy-MM-dd hh:mm:ss:代表将时间转换为12小时制,例: 2018-06-27 03:24:21
    abstract String formatDateToyMdHms(LocalDateTime localDateTime);


}
