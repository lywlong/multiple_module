package com.wl.dpuspringbootstarter.config;

import com.wl.dpuspringbootstarter.properties.DateFormatProperties;
import com.wl.dpuspringbootstarter.service.DateProcessService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangLong
 * @Description:
 * @date 2022/3/21 15:22
 */
@Configuration
@EnableConfigurationProperties(DateFormatProperties.class)
@ConditionalOnProperty(
        prefix = "date.format",
        name = "isFormat",
        havingValue = "true"
)
public class DateProcessConfiguration {

    private final DateFormatProperties dateFormatProperties;

    public DateProcessConfiguration(DateFormatProperties dateFormatProperties) {
        this.dateFormatProperties = dateFormatProperties;
    }

    @Bean(name = "dateProcessServiceC")
    public DateProcessService dateProcessService(){
        return new DateProcessService(dateFormatProperties.isFormat(), dateFormatProperties.getPattern());
    }

}
