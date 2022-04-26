package com.wl.multipledatasources.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/19 15:45
 */
public class BaseRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.get();
    }
}
