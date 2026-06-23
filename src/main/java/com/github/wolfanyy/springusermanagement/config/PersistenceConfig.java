package com.github.wolfanyy.springusermanagement.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class PersistenceConfig {

    private final Environment environment;


    public PersistenceConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setPoolName("SpringUserManagementPool");

        dataSource.setDriverClassName(
                environment.getRequiredProperty("db.driver")
        );
        dataSource.setJdbcUrl(
                environment.getRequiredProperty("db.url")
        );
        dataSource.setUsername(
                environment.getRequiredProperty("db.username")
        );
        dataSource.setPassword(
                environment.getRequiredProperty("db.password")
        );

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSource());

        factoryBean.setPackagesToScan(
                "com.github.wolfanyy.springusermanagement.entity"
        );

        factoryBean.setJpaVendorAdapter(
                new HibernateJpaVendorAdapter()
        );

        factoryBean.setPersistenceUnitName("spring-user-management");

        factoryBean.setJpaProperties(hibernateProperties());

        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Properties hibernateProperties() {

        Properties properties = new Properties();

        properties.put("hibernate.dialect",
                environment.getRequiredProperty("hibernate.dialect")
        );

        properties.put("hibernate.show_sql",
                environment.getRequiredProperty("hibernate.show_sql")
        );

        properties.put("hibernate.format_sql",
                environment.getRequiredProperty("hibernate.format_sql")
        );

        properties.put("hibernate.hbm2ddl.auto",
                environment.getRequiredProperty("hibernate.hbm2ddl.auto")
        );

        return properties;
    }
}