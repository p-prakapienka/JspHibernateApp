DELETE FROM orders_products;
DELETE FROM products;
DELETE FROM orders;
DELETE FROM users;

INSERT INTO users (id, name) VALUES (100, 'Test user1');
INSERT INTO users (id, name) VALUES (101, 'Test user2');

INSERT INTO orders (id, name, user_id) VALUES (1000, 'User1 order1', 100);
INSERT INTO orders (id, name, user_id) VALUES (1001, 'User1 order2', 100);
INSERT INTO orders (id, name, user_id) VALUES (1002, 'User2 order3', 101);
INSERT INTO orders (id, name, user_id) VALUES (1003, 'User2 order4', 101);

INSERT INTO products (id, name) VALUES (10000, 'Item1 - Order1 - User1');
INSERT INTO products (id, name) VALUES (10001, 'Item2 - Order1 - User1');
INSERT INTO products (id, name) VALUES (10002, 'Item3 - Order2 - User1');
INSERT INTO products (id, name) VALUES (10003, 'Item4 - Order2 - User1');
INSERT INTO products (id, name) VALUES (10004, 'Item5 - Order3 - User2');
INSERT INTO products (id, name) VALUES (10005, 'Item6 - Order3 - User2');
INSERT INTO products (id, name) VALUES (10006, 'Item7 - Order4 - User2');
INSERT INTO products (id, name) VALUES (10007, 'Item8 - Order4 - User2');

INSERT INTO orders_products (order_id, product_id) VALUES (1000, 10000);
INSERT INTO orders_products (order_id, product_id) VALUES (1000, 10001);
INSERT INTO orders_products (order_id, product_id) VALUES (1001, 10002);
INSERT INTO orders_products (order_id, product_id) VALUES (1001, 10003);
INSERT INTO orders_products (order_id, product_id) VALUES (1002, 10004);
INSERT INTO orders_products (order_id, product_id) VALUES (1002, 10005);
INSERT INTO orders_products (order_id, product_id) VALUES (1003, 10006);
INSERT INTO orders_products (order_id, product_id) VALUES (1003, 10007);