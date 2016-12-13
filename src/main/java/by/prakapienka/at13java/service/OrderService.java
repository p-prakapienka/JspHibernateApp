package by.prakapienka.at13java.service;

import by.prakapienka.at13java.model.Order;

import java.util.List;

public interface OrderService {

    Order get(int id, int userId);

    void delete(int id, int userId);

    List<Order> getAll(int userId);

    Order getWithItems(int id, int userId);

    Order insert(Order order, int userId);

    Order update(Order order, int userId);

    Order insertItem(int orderId, int itemId, int userId);

    Order deleteItem(int orderId, int itemId, int userId);
}
