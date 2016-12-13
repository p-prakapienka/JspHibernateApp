package by.prakapienka.at13java.dao;

import by.prakapienka.at13java.model.User;

import java.util.List;

public interface UserDao {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    List<User> getAll();

    void deleteAll();
}
