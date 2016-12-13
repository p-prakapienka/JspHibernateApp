package by.prakapienka.at13java.service;

import by.prakapienka.at13java.model.OrderItem;

import java.util.List;

public interface ProductService {

    List<OrderItem> getAll();

    OrderItem get(int id);

    OrderItem insert(OrderItem product);

    OrderItem update(OrderItem product);

    void delete(int id);
}
