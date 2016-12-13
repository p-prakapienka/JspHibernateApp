DROP TABLE IF EXISTS orders_products;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id      INT(8) NOT NULL AUTO_INCREMENT,
  name    VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE orders (
  id      INT(8) NOT NULL AUTO_INCREMENT,
  name    VARCHAR(50) NOT NULL,
  user_id INT(8) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE products (
  id       INT(8) NOT NULL AUTO_INCREMENT,
  name     VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE orders_products (
  order_id INT(8),
  product_id INT(8),
  PRIMARY KEY (order_id, product_id),
  CONSTRAINT fk_order_id FOREIGN KEY (order_id)
  REFERENCES orders (id) ON DELETE CASCADE,
  CONSTRAINT fk_product_id FOREIGN KEY (product_id)
  REFERENCES products (id) ON DELETE CASCADE
)

