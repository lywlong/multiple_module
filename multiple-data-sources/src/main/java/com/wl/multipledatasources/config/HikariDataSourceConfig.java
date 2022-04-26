package com.wl.multipledatasources.config;

import com.wl.multipledatasources.properties.MasterDataSourceProperties;
import com.wl.multipledatasources.properties.Slave1DataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/19 15:33
 */
@Configuration
public class HikariDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "master.datasource.hikari")
    public HikariDataSource masterDataSource(MasterDataSourceProperties properties){
        HikariDataSource dataSource = createDataSource(properties,HikariDataSource.class);
        if (StringUtils.hasText(properties.getName())){
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "slave1.datasource.hikari")
    public HikariDataSource slave1DataSource(Slave1DataSourceProperties properties){
        HikariDataSource dataSource = createDataSource(properties,HikariDataSource.class);
        if (StringUtils.hasText(properties.getName())){
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

    @SuppressWarnings(("unchecked"))
    protected static <T> T createDataSource(DataSourceProperties dataSourceProperties, Class<? extends DataSource> type){
        return (T) dataSourceProperties.initializeDataSourceBuilder().type(type);
    }

    @Bean
    @Primary
    @DependsOn({"masterDataSource","slave1DataSource"})
    public AbstractRoutingDataSource routingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                                       @Qualifier("slave1DataSource") DataSource slave1DataSource){
        BaseRoutingDataSource bds = new BaseRoutingDataSource();
        Map<Object,Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DbEnum.MASTER,masterDataSource);
        targetDataSource.put(DbEnum.SLAVE1,slave1DataSource);
        bds.setDefaultTargetDataSource(masterDataSource);
        bds.setTargetDataSources(targetDataSource);
        return bds;
    }


}
