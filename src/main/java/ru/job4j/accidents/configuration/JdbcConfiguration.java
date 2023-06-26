package ru.job4j.accidents.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

//@Configuration
//@EnableTransactionManagement
public class JdbcConfiguration {

//    @Bean
    public DataSource ds(@Value("${spring.datasource.jdbc.driver}") String driver,
                         @Value("${spring.datasource.jdbc.url}") String url,
                         @Value("${spring.datasource.jdbc.username}") String username,
                         @Value("${spring.datasource.jdbc.password}") String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

//    @Bean
    public JdbcTemplate jdbc(DataSource ds) {
        return new JdbcTemplate(ds);
    }

}