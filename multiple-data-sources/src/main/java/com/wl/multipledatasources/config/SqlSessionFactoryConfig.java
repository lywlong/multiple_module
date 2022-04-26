package com.wl.multipledatasources.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/19 16:45
 */
public class SqlSessionFactoryConfig {

    static class MasterSqlSessionFactory{

        @Resource
        private MybatisPlusProperties properties;

        @Autowired(required = false)
        private Interceptor [] interceptors;

        @Autowired
        private ResourceLoader resourceLoader = new DefaultResourceLoader();

        @Autowired(required = false)
        private DatabaseIdProvider databaseIdProvider;

        public SqlSessionFactory sqlSessionFactory(@Qualifier("routingDataSource")DataSource dataSource){
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(dataSource);
            factoryBean.setVfs(SpringBootVFS.class);
            if (StringUtils.hasText(this.properties.getConfigLocation())) {
                factoryBean.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
            }
            factoryBean.setConfiguration(properties.getConfiguration());
            if (!ObjectUtils.isEmpty(this.interceptors)) {
                factoryBean.setPlugins(this.interceptors);
            }
            if (this.databaseIdProvider != null) {
                factoryBean.setDatabaseIdProvider(this.databaseIdProvider);
            }
            if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
                factoryBean.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
            }
            if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())){
                factoryBean.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
            }

            if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
                factoryBean.setMapperLocations(this.properties.resolveMapperLocations());
            }
            try {
                return factoryBean.getObject();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Bean
        public DataSourceTransactionManager masterTransactionManager(@Qualifier("routingDataSource")DataSource dataSource){
            return new DataSourceTransactionManager(dataSource);
        }

    }


}
