package by.prakapienka.at13java;

import by.prakapienka.at13java.model.Order;
import by.prakapienka.at13java.model.OrderItem;
import by.prakapienka.at13java.model.User;

public class TestData {

    public final static Integer USER1_ID = 100;
    public final static Integer USER2_ID = 101;

    public final static User USER1 = new User(USER1_ID, "Test user1");
    public final static User USER2 = new User(USER2_ID, "Test user2");

    public final static Integer ORDER1_ID = 1000;
    public final static Integer ORDER2_ID = 1001;
    public final static Integer ORDER3_ID = 1002;
    public final static Integer ORDER4_ID = 1003;

    public final static Order ORDER1 = new Order(ORDER1_ID, "User1 order1", USER1);
    public final static Order ORDER2 = new Order(ORDER2_ID, "User1 order2", USER1);
    public final static Order ORDER3 = new Order(ORDER3_ID, "User2 order3", USER2);
    public final static Order ORDER4 = new Order(ORDER4_ID, "User2 order4", USER2);

    public final static Integer ORDER_ITEM_1_ID = 10000;
    public final static Integer ORDER_ITEM_2_ID = 10001;
    public final static Integer ORDER_ITEM_3_ID = 10002;
    public final static Integer ORDER_ITEM_4_ID = 10003;
    public final static Integer ORDER_ITEM_5_ID = 10004;
    public final static Integer ORDER_ITEM_6_ID = 10005;
    public final static Integer ORDER_ITEM_7_ID = 10006;
    public final static Integer ORDER_ITEM_8_ID = 10007;

    public final static OrderItem ORDER_ITEM_1 = new OrderItem(ORDER_ITEM_1_ID, "Item1 - Order1 - User1");
    public final static OrderItem ORDER_ITEM_2 = new OrderItem(ORDER_ITEM_2_ID, "Item2 - Order1 - User1");
    public final static OrderItem ORDER_ITEM_3 = new OrderItem(ORDER_ITEM_3_ID, "Item3 - Order2 - User1");
    public final static OrderItem ORDER_ITEM_4 = new OrderItem(ORDER_ITEM_4_ID, "Item4 - Order2 - User1");
    public final static OrderItem ORDER_ITEM_5 = new OrderItem(ORDER_ITEM_5_ID, "Item5 - Order3 - User2");
    public final static OrderItem ORDER_ITEM_6 = new OrderItem(ORDER_ITEM_6_ID, "Item6 - Order3 - User2");
    public final static OrderItem ORDER_ITEM_7 = new OrderItem(ORDER_ITEM_7_ID, "Item7 - Order4 - User2");
    public final static OrderItem ORDER_ITEM_8 = new OrderItem(ORDER_ITEM_8_ID, "Item8 - Order4 - User2");

}
