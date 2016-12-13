package by.prakapienka.at13java.dao;

import by.prakapienka.at13java.model.Order;
import by.prakapienka.at13java.model.OrderItem;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.prakapienka.at13java.TestData.*;

public class OrderDaoTest extends AbstractDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Test
    public void testInsert() {
        Order order = new Order("New order", USER2);
        Order persisted = orderDao.save(order, USER2_ID);
        Assert.assertEquals(order, persisted);
        Assert.assertTrue(orderDao.getAll(USER2_ID).size() == 3);
    }

    @Test
    public void testDelete() {
        orderDao.delete(ORDER3_ID, USER2_ID);
        Assert.assertTrue(orderDao.getAll(USER2_ID).size() == 1);
    }

    @Test
    public void update() {
        Order order = new Order(ORDER3_ID, "New name", USER2);
        Order updated = orderDao.save(order, USER2_ID);
        Assert.assertEquals(updated, order);
    }

    @Test
    public void testGet() {
        Order order = orderDao.get(ORDER3_ID, USER2_ID);
        Assert.assertEquals(order, ORDER3);
    }

    @Test
    public void testGetWithItems() {
        Order order = orderDao.getWithItems(ORDER2_ID, USER1_ID);
        Assert.assertEquals(order, ORDER2);
        Assert.assertTrue(order.getOrderItems().size() == 2);
    }

    @Test
    public void testGetAll() {
        Assert.assertTrue(orderDao.getAll(USER1_ID).size() == 2);
    }

    @Test
    public void testInsertItem() {
        OrderItem item = orderItemDao.save(new OrderItem("New Item"));
        Order order = orderDao.insertItem(ORDER4_ID, item.getId(), USER2_ID);
        Assert.assertTrue(order.getOrderItems().size() == 3);
    }

    @Test
    public void testDeleteItem() {
        Order order = orderDao.deleteItem(ORDER4_ID, ORDER_ITEM_7_ID, USER2_ID);
        Assert.assertTrue(order.getOrderItems().size() == 1);
    }

    @Test
    public void testInsertItemInNewOrder() {
        Order order = new Order("New Order");
        order = orderDao.save(order, USER1_ID);
        order = orderDao.insertItem(order.getId(), ORDER_ITEM_3_ID, USER1_ID);
        Assert.assertNotNull(order);
    }

    @Test
    public void testGetWithItemsEmptySet() {
        Order order = new Order("New Order");
        order = orderDao.save(order, USER1_ID);
        order = orderDao.getWithItems(order.getId(), USER1_ID);
        Assert.assertTrue(order.getOrderItems().isEmpty());
    }
}
