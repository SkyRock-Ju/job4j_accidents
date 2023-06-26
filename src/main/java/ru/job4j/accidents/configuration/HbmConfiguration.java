package ru.job4j.accidents.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.job4j.accidents.repository.CrudRepository;

import javax.sql.DataSource;
import java.util.Properties;

//@Configuration
//@PropertySource("classpath:application.yml")
//@EnableTransactionManagement
public class HbmConfiguration {

//    @Bean
    public LocalSessionFactoryBean sessionFactory(@Value("${spring.datasource.hibernate.dialect}") String dialect, DataSource ds) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(ds);
        sessionFactory.setPackagesToScan("ru.job4j.accidents.model");
        Properties cfg = new Properties();
        cfg.setProperty("spring.datasource.hibernate.dialect", dialect);
        sessionFactory.setHibernateProperties(cfg);
        return sessionFactory;
    }

//    @Bean
    public PlatformTransactionManager htx(SessionFactory sf) {
        HibernateTransactionManager tx = new HibernateTransactionManager();
        tx.setSessionFactory(sf);
        return tx;
    }

//    @Bean
    public CrudRepository crudRepository(SessionFactory sessionFactory) {
        return new CrudRepository(sessionFactory);
    }
}
