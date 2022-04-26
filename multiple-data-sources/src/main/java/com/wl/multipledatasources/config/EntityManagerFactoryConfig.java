package com.wl.multipledatasources.config;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/19 15:58
 */
public class EntityManagerFactoryConfig {

    @Configuration
    @EnableJpaRepositories(basePackages = {"com.wl.multipledatasources.repository"})
    static class MasterEntityManagerFactory{

        @Resource(name = "routingDataSource")
        private DataSource routingDataSource;

        @Bean
        @Primary
        public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder){
            Map<String,Object> properties = new HashMap<>();
            properties.put("hibernate.hbm2ddl.auto","update");
            properties.put("hibernate.id.new_generator_mappings",true);
            properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
            return builder.dataSource(routingDataSource).packages("com.wl.multipledatasources.domain").persistenceUnit("master")
                    .properties(properties).build();
        }

        @Bean
        @Primary
        public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder){
            JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
            return jpaTransactionManager;
        }

    }

}
