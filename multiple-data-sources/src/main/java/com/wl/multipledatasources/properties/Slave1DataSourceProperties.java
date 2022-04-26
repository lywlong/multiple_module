package com.wl.multipledatasources.properties;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/19 15:32
 */
@Component
@ConfigurationProperties(prefix = "slave1.datasource")
public class Slave1DataSourceProperties extends DataSourceProperties {



}
