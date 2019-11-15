CREATE TABLE pre_order
(
  id SERIAL PRIMARY KEY,
  order_id varchar(50) NOT NULL,
  name varchar(50) NOT NULL,
  amount integer NOT NULL,
  price decimal NOT NULL,
  total decimal NOT NULL,
  order_status varchar(50) NOT NULL,
  pre_money  decimal,
  partial_pay_time  BIGINT,
  pay_all_money_delay BIGINT
);