CREATE TABLE test_order
(
  id SERIAL PRIMARY KEY,
  order_id VARCHAR(50) NOT NULL,
  name VARCHAR(50) NOT NULL,
  amount integer NOT NULL,
  price decimal NOT NULL,
  total decimal NOT NULL,
  order_status VARCHAR(50) NOT NULL
)