package com.javaloping.homr.app.init;

import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author victormiranda@gmail.com
 */

@Configuration
@EnableJpaRepositories("com.javaloping.homr.app.repository")
public class DatabaseConfig {

    @Value("${db.url}")
    private String jdbcUrl;

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Value("${db.driver}")
    private String driver;

    @Value("${db.hibernate.dialect}")
    private String dialect;

    @Value("${db.hibernate.packagescan}")
    private String modelPackage;

    @Value("${db.hibernate.hbm2ddl}")
    private String hbm2ddl;

    @Value("${db.liquibase.context}")
    private String liquibaseContext;

    public static final String LIQUIBASE_PATH = "classpath:/database/changelog.xml";

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    @DependsOn({"liquibase"})
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        final Properties hibernateProperties = new Properties();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(modelPackage);

        hibernateProperties.setProperty("hibernate.dialect", dialect);
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);

        entityManagerFactoryBean.setJpaProperties(hibernateProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean(name = "liquibase")
    public SpringLiquibase getLiquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();

        liquibase.setChangeLog(LIQUIBASE_PATH);
        liquibase.setDataSource(dataSource());
        liquibase.setContexts(liquibaseContext);

        return liquibase;
    }
}
