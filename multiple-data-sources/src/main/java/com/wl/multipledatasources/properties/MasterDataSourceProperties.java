package com.wl.multipledatasources.properties;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/19 14:48
 */
@Component
@ConfigurationProperties(prefix = "master.datasource")
public class MasterDataSourceProperties extends DataSourceProperties {



}
