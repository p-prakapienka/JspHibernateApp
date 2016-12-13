package by.prakapienka.at13java.service;

import by.prakapienka.at13java.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User get(int id);

    User getWithOrders(int id);

    User insert(User user);

    User update(User user);

    void delete(int id);
}
