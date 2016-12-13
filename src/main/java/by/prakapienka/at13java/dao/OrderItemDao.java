package by.prakapienka.at13java.dao;

import by.prakapienka.at13java.model.OrderItem;

import java.util.List;

public interface OrderItemDao {

    OrderItem save(OrderItem item);

    boolean delete(int id);

    OrderItem get(int id);

    List<OrderItem> getAll();

}
