TRUNCATE table "order";
TRUNCATE table product;
ALTER SEQUENCE order_id_seq RESTART WITH 1;

INSERT INTO product
VALUES ('BUN', 4000, now())
     , ('SAUCE', 6000, now())
     , ('TOMATO', 2000, now())
     , ('SALAD', 1000, now())
     , ('CUCUMBER', 1000, now())
     , ('MEAT', 6000, now())
     , ('BACON', 1000, now())
     , ('CHEESE', 2000, now())
     , ('ONION', 1000, now())
