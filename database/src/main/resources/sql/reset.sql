SET search_path TO burgers;

TRUNCATE table "order";
TRUNCATE table product;
ALTER SEQUENCE order_id_seq RESTART WITH 1;

INSERT INTO product
VALUES ('BUN', 10000, now())
     , ('SAUCE', 10000, now())
     , ('TOMATO', 10000, now())
     , ('SALAD', 10000, now())
     , ('CUCUMBER', 10000, now())
     , ('MEAT', 20000, now())
     , ('BACON', 5000, now())
     , ('CHEESE', 30000, now())
     , ('ONION', 15000, now())

