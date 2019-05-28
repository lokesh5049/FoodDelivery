package com.sapphireims.config;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * Hibernate Configuration java file
 * 
 * 
 * @author lokesh.yadav
 * @since   2019-01-10 
 *
 */
@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	
	private static final Logger  LOGGER = Logger.getLogger(HibernateConfig.class);
	
	    @Value("org.hibernate.dialect.MySQLInnoDBDialect")        
	    private String hibernateDialect;
	    @Value("true")     
	    private String hibernateShowSql;
	    @Value("update") 
	    private String hibernateHbm2ddlAuto;
	    @Value("java:jboss/datasources/Customer") 
	    private String datasource;
	
	    
  //Hibernate properties	    
	@Bean
    public Properties getHibernateProperties()
    {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        properties.put("hibernate.connection.datasource",datasource);
        
        return properties;
    }

	@Bean
    public LocalSessionFactoryBean getSessionFactory()
    {
		LOGGER.debug("Session Started!!");
		LocalSessionFactoryBean localSession = new LocalSessionFactoryBean();
		localSession.setHibernateProperties(getHibernateProperties());        
		localSession.setPackagesToScan(new String[]{"com.sapphireims.bo"});
		LOGGER.debug("Session Created!!");
        return localSession;
    }
	
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
    {
        HibernateTransactionManager transaction = new HibernateTransactionManager();
        transaction.setSessionFactory(sessionFactory);
        return transaction;
    }
	
	@Bean
    @Autowired
    public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory)
    {
        return new HibernateTemplate(sessionFactory);
    }
}
