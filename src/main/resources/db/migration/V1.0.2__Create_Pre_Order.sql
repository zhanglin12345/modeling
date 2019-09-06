CREATE TABLE pre_order
(
  id SERIAL NOT NULL ,
  order_id varchar(50) NOT NULL,
  name varchar(50) NOT NULL,
  amount INT NOT NULL,
  price DECIMAL NOT NULL,
  total DECIMAL NOT NULL,
  order_status varchar(50) NOT NULL,
  pre_money  DECIMAL,
  partial_pay_time  BIGINT,
  pay_all_money_delay BIGINT,
  CONSTRAINT pk_pre_order_id PRIMARY KEY (id),
);