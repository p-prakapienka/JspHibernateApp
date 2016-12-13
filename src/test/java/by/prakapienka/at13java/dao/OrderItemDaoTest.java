package by.prakapienka.at13java.dao;

import by.prakapienka.at13java.model.OrderItem;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.prakapienka.at13java.TestData.*;

public class OrderItemDaoTest extends AbstractDaoTest {

    @Autowired
    private OrderItemDao orderItemDao;

    @Test
    public void testInsert() {
        OrderItem item = new OrderItem("New item");
        OrderItem persisted = orderItemDao.save(item);
        Assert.assertEquals(item, persisted);
        Assert.assertTrue(orderItemDao.getAll().size() == 9);
    }

    @Test
    public void testDelete() {
        orderItemDao.delete(ORDER_ITEM_6_ID);
        Assert.assertTrue(orderItemDao.getAll().size() == 7);
    }

    @Test
    public void testUpdate() {
        OrderItem item = new OrderItem(ORDER_ITEM_7_ID, "New name");
        OrderItem updated = orderItemDao.save(item);
        Assert.assertEquals(updated, item);
    }

    @Test
    public void testGet() {
        OrderItem item = orderItemDao.get(ORDER_ITEM_8_ID);
        Assert.assertEquals(item, ORDER_ITEM_8);
    }

    @Test
    public void testGetAll() {
        orderItemDao.getAll();
        Assert.assertTrue(orderItemDao.getAll().size() == 8);
    }
}
