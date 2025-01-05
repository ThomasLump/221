package hiber.dao;
import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserDaoImpTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void testAddUser() {
        User newUser = new User("Ipolit","Matveev", "cooly-lover@spb.ru",new Car("VAZ", 2103));
        userDao.add(newUser);
        List<User> resultList = userDao.listUsers();
        assertEquals(1, resultList.size());
    }

    @Test
    @Transactional
    public void testGetUserByCar() {
        User newUser = new User("Ipolit","Matveev", "cooly-lover@spb.ru",new Car("VAZ", 2103));
        userDao.add(newUser);

        Optional<User> result = userDao.getUserByCar("VAZ",2103);
        assertTrue(result.isPresent());
        assertEquals("Ipolit",result.get().getFirstName());
        assertEquals("Matveev",result.get().getLastName());
        assertEquals(result.get().getId(),result.get().getCar().getUser().getId());
        System.out.println(result.get().toString());
        //наверное есть больше смысла искать по id
    }

    @Test
    @Transactional
    public void testGetUserByCar_UserNotFound() {
        Optional<User> result = userDao.getUserByCar("VAZ",2101);
        assertFalse(result.isPresent());
    }
}
