package com.wl.dpuspringbootstarter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangLong
 * @Description: 属性实体类：配置信息映射
 * @date 2022/3/21 15:19
 */
@Configuration
@ConfigurationProperties(prefix = "date.format")
public class DateFormatProperties {

    private boolean format;
    private String pattern;

    public boolean isFormat() {
        return format;
    }

    public void setFormat(boolean format) {
        this.format = format;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
