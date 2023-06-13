SELECT name
     , count
     , (SELECT COUNT(1) FROM "order") must_be_400
FROM product
WHERE count > 0;

