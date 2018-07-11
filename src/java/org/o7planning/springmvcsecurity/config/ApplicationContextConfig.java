
package org.o7planning.springmvcsecurity.config;

import java.util.Properties;
import javax.servlet.ServletContext;
 
import javax.sql.DataSource;
 
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.o7planning.springmvcsecurity.dao.ApplicantDAO;
import org.o7planning.springmvcsecurity.dao.ClientDAO;
import org.o7planning.springmvcsecurity.dao.FichierDAO;
import org.o7planning.springmvcsecurity.dao.UserInfoDAO;
import org.o7planning.springmvcsecurity.dao.impl.ApplicantDAOImpl;
import org.o7planning.springmvcsecurity.dao.impl.ClientDAOImpl;
import org.o7planning.springmvcsecurity.dao.impl.FichierDAOImpl;
import org.o7planning.springmvcsecurity.dao.impl.UserInfoDAOImpl;
import org.o7planning.springmvcsecurity.utilitaires.MyUtilsDivers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("org.o7planning.springmvcsecurity.*")
@EnableTransactionManagement
@PropertySource("/WEB-INF/classes/myhibernatus-cfg.properties")
public class ApplicationContextConfig {
 
    @Autowired
    private Environment env;
    
    //@Autowired
    private UserInfoDAO userInfoDAO;
    
    
    
   
    
    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
        rb.setBasenames(new String[]{"/WEB-INF/messages/validator"});
        
        return rb;
    }
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver(){
      InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
      viewResolver.setPrefix("/WEB-INF/jsp/");   
      viewResolver.setSuffix(".jsp");
        return viewResolver;
        
    }
    @Bean(name = "dataSource")
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        // See: datasouce-cfg.properties
        dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
        dataSource.setUrl(env.getProperty("ds.url"));
        dataSource.setUsername(env.getProperty("ds.username"));
        dataSource.setPassword(env.getProperty("ds.password"));
 
        System.out.println("## getDataSource: " + dataSource);
 
        return dataSource;
    }
    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception{
        System.out.println("## getSessionFactory");
        
        try{
            Properties properties = new Properties();
            //Regards sur le fichier ds-hibernatus
            properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
            properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
            properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
            
            
            LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
            factoryBean.setPackagesToScan(new String[] { "org.o7planning.springmvcsecurity.entity" });
            factoryBean.setDataSource(dataSource);
            factoryBean.setHibernateProperties(properties);
            factoryBean.afterPropertiesSet();
            SessionFactory sf = factoryBean.getObject();
            System.out.println("## getSessionFactory "+sf);
            return sf;
            
            
        }catch(Exception exop){
            System.err.println("Error getSessionFactory "+ exop);
            exop.printStackTrace();
            throw exop;
        }
    }
   /* public void setServletContext(ServletContext servletContext){
    
}*/
    
    //Hibernatus Transaction Manager
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }
    
    //Le plus de l'application Spring MVC Form Handling and Hibernate Tutorial https://o7planning.org/en/10601/spring-mvc-form-handling-and-hibernate-tutorial
    
    @Bean(name = "applicantDAO")
    public ApplicantDAO getApplicantDAO(){
        return new ApplicantDAOImpl();
    }
    
    @Bean(name = "userDAO")
    public UserInfoDAOImpl getUserDAO(){
        return new UserInfoDAOImpl();
    }
    
    @Bean(name = "multipartResolver")
    public MultipartResolver getMultipartResolver(){
        CommonsMultipartResolver myResolver = new CommonsMultipartResolver();
        myResolver.setMaxUploadSize(10*1024*1024);
        return myResolver;
    }
    
    @Bean(name = "clientDAO")
    public ClientDAO getClientDAO(){
        return new ClientDAOImpl();
    }
    @Bean(name = "fichierDAO")
    public FichierDAO getFichierDAO(){
        return new FichierDAOImpl();
    }
    
   /* @Bean(name = "monChemin")
    public String getMonChemin(){
        return MyUtilsDivers.getCHEMINUPLOAD();
    }*/
    
}
