package by.prakapienka.at13java.service;

import by.prakapienka.at13java.dao.OrderDao;
import by.prakapienka.at13java.dao.UserDao;
import by.prakapienka.at13java.model.Order;
import by.prakapienka.at13java.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User get(int id) {
        return userDao.get(id);
    }

    @Override
    public User getWithOrders(int id) {
        User user = userDao.get(id);
        List<Order> orders = orderDao.getAll(id);
        user.setOrders(orders);
        return user;
    }

    @Override
    public User insert(User user) {
        return userDao.save(user);
    }

    @Override
    public User update(User user) {
        return userDao.save(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
