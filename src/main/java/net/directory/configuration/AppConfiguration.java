package net.directory.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan({"net.*"})
@PropertySource(value = {"classpath:application.properties"})
//@Import ({SecurityConfig.class})
@EnableTransactionManagement
@ImportResource ("/WEB-INF/security.xml")
public class AppConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver ();
        resolver.setViewClass (JstlView.class);
        resolver.setPrefix ("/WEB-INF/views");
        resolver.setSuffix (".html");
        return resolver;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource ();
        dataSource.setDriverClassName (environment.getRequiredProperty ("jdbc.driverClassName"));
        dataSource.setUrl (environment.getRequiredProperty ("jdbc.url"));
        dataSource.setUsername (environment.getRequiredProperty ("jdbc.username"));
        dataSource.setPassword (environment.getRequiredProperty ("jdbc.password"));
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder (dataSource ());
        builder.scanPackages ("net.directory.models").addProperties (getProperties ());
        return builder.buildSessionFactory ();
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager (sessionFactory ());
    }

    private Properties getProperties() {
        Properties properties = new Properties ();
        properties.put ("current_session_context_class", environment.getRequiredProperty ("current_session_context_class"));
        properties.put ("hibernate.dialect", environment.getRequiredProperty ("hibernate.dialect"));
        properties.put ("hibernate.show_sql", environment.getRequiredProperty ("hibernate.show_sql"));
        properties.put ("hbm2ddl.auto", environment.getRequiredProperty ("hbm2ddl.auto"));
        return properties;
    }
}
