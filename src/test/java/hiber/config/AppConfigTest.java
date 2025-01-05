package hiber.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AppConfigTest {

    @Test
    public void testDataSource() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        DataSource ds = context.getBean(DataSource.class);
        assertNotNull(ds);
        try(Connection connection = ds.getConnection()) {
            assertNotNull(connection);
            assertTrue(connection.isValid(1));
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void testSessionFactory() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        LocalSessionFactoryBean sf = context.getBean(LocalSessionFactoryBean.class);
        assertNotNull(sf);
    }

    @Test
    public void testTransactionManager() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HibernateTransactionManager tm = context.getBean(HibernateTransactionManager.class);
        assertNotNull(tm);
    }
}
