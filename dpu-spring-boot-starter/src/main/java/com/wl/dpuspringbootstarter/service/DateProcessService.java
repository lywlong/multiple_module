package com.wl.dpuspringbootstarter.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author WangLong
 * @Description:
 * @date 2022/3/21 15:22
 */
public class DateProcessService extends AbstractDateProcessService{

    private boolean subFormat;
    private String ofPattern;
    public DateProcessService(boolean format, String pattern) {
        super(format, pattern);
        this.subFormat = format;
    }

    @Override
    String formatDateToyMd(LocalDateTime localDateTime) {

        return "此转换暂未开放！";
    }

    @Override
    String formatDateToyMdHms(LocalDateTime localDateTime) {
        if (this.subFormat){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(this.ofPattern);
            String new_date = dtf.format(localDateTime);
            return new_date;
        }else{
           return localDateTime.toString();
        }
    }
}
