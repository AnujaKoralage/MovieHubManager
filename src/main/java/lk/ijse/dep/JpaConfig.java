package lk.ijse.dep;

import lk.ijse.dep.dao.ActorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@PropertySource("file:C:\\Users\\User\\Desktop\\FIlmProject\\resources\\application.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = ActorDAO.class)
public class JpaConfig {

    @Autowired
    Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds, JpaVendorAdapter adapter){
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(ds);
        lcemfb.setJpaVendorAdapter(adapter);
        lcemfb.setPackagesToScan("lk.ijse.dep.entities");
        return  lcemfb;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getRequiredProperty("javax.persistence.jdbc.driver"));
        ds.setUrl(env.getRequiredProperty("javax.persistence.jdbc.url"));
        ds.setUsername(env.getRequiredProperty("javax.persistence.jdbc.user"));
        ds.setPassword(env.getRequiredProperty("javax.persistence.jdbc.password"));

        return ds;

    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setDatabasePlatform(env.getRequiredProperty("hibernate.dialect"));
        adapter.setShowSql(Boolean.getBoolean(env.getRequiredProperty("hibernate.show_sql")));
        adapter.setGenerateDdl(env.getRequiredProperty("hibernate.hbm2ddl.auto").equalsIgnoreCase("update")?true:false);
        return adapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManager){
        return new JpaTransactionManager(entityManager);
    }

}
