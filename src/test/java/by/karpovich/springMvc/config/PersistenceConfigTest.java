package by.karpovich.springMvc.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class PersistenceConfigTest {

    @InjectMocks
    private PersistenceConfig persistenceConfig;

    @Mock
    private Environment environment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(environment.getRequiredProperty("hibernate.dialect")).thenReturn("org.hibernate.dialect.PostgreSQLDialect");
        when(environment.getRequiredProperty("hibernate.show_sql")).thenReturn("true");
        when(environment.getRequiredProperty("jdbc.driverClassName")).thenReturn("org.postgresql.Driver");
        when(environment.getRequiredProperty("jdbc.url")).thenReturn("jdbc:postgresql://localhost:5432/testdb");
        when(environment.getRequiredProperty("jdbc.username")).thenReturn("testuser");
        when(environment.getRequiredProperty("jdbc.password")).thenReturn("testpass");
    }

    @Test
    public void dataSourceBean() {
        DataSource dataSource = persistenceConfig.dataSource();
        assertNotNull(dataSource);
    }

    @Test
    public void entityManagerFactoryBean() {
        DataSource dataSource = persistenceConfig.dataSource();
        LocalContainerEntityManagerFactoryBean emf = persistenceConfig.entityManagerFactory(dataSource);
        assertNotNull(emf);
    }

    @Test
    public void transactionManagerBean() {
        DataSource dataSource = persistenceConfig.dataSource();
        LocalContainerEntityManagerFactoryBean emf = persistenceConfig.entityManagerFactory(dataSource);
        JpaTransactionManager tm = persistenceConfig.transactionManager(emf.getObject());
        assertNotNull(tm);
    }
}