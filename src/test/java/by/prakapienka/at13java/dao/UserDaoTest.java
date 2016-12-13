package by.prakapienka.at13java.dao;

import by.prakapienka.at13java.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.prakapienka.at13java.TestData.USER1;
import static by.prakapienka.at13java.TestData.USER1_ID;

public class UserDaoTest extends AbstractDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsertAndDelete() {
        User user = userDao.save(new User("Test user3"));
        User persisted = userDao.get(user.getId());
        Assert.assertEquals(user, persisted);
        Assert.assertTrue(userDao.getAll().size() == 3);
    }

    @Test
    public void testDelete() {
        userDao.delete(USER1_ID);
        Assert.assertTrue(userDao.getAll().size() == 1);
    }

    @Test
    public void testUpdate() {
        User user = new User(USER1_ID, "New name");
        User updated = userDao.save(user);
        Assert.assertEquals(updated, user);
    }

    @Test
    public void testGetAll() {
        List<User> users = userDao.getAll();
        Assert.assertTrue(users.size() == 2);
    }

    @Test
    public void testGet() {
        User user = userDao.get(100);
        Assert.assertEquals(user, USER1);
    }

}
