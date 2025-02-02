package kr.ac.jejunu.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    private static UserDao userDao;
    @BeforeAll
    private static void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "hulk";
        String password = "1234";
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name = "hulk";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }
    @Test
    public void update() throws SQLException {
        String name = "hulk";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        String updatedName = "updateName";
        String updatedPassword = "updatedPassword";
        user.setName(updatedName);
        user.setPassword(updatedPassword);
        userDao.update(user);

        User createdUser = userDao.findById(user.getId());
        assertThat(createdUser.getName(), is(updatedName));
        assertThat(createdUser.getPassword(), is(updatedPassword));
    }

    @Test
    public void delete() throws SQLException {
        String name = "hulk";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        userDao.delete(user.getId());
        User deletedUser = userDao.findById(user.getId());
        assertThat(deletedUser, IsNull.nullValue());
    }

//    @Test
//    public void hallaGet() throws SQLException, ClassNotFoundException {
//        Long id = 1l;
//        String name = "hulk";
//        String password = "1234";
//        UserDao userDao = new HallaUserDao();
//        User user = userDao.findById(id);
//        assertThat(user.getId(), is(id));
//        assertThat(user.getName(), is(name));
//        assertThat(user.getPassword(), is(password));
//    }
//    @Test
//    public void hallaInsert() throws SQLException, ClassNotFoundException {
//        String name = "hulk";
//        String password = "1234";
//        User user = new User();
//        user.setName(name);
//        user.setPassword(password);
//        UserDao userDao = new HallaUserDao();
//        userDao.insert(user);
//        assertThat(user.getId(), greaterThan(1l));
//
//        User insertedUser = userDao.findById(user.getId());
//        assertThat(insertedUser.getName(), is(name));
//        assertThat(insertedUser.getPassword(), is(password));
//    }
}
