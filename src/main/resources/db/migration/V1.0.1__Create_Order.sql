CREATE TABLE test_order
(
  id SERIAL NOT NULL ,
  order_id varchar(50) NOT NULL,
  name varchar(50) NOT NULL,
  amount INT NOT NULL,
  price DECIMAL NOT NULL,
  total DECIMAL NOT NULL,
  order_status varchar(50) NOT NULL,
  CONSTRAINT pk_testorder_id PRIMARY KEY (id),
);