package ru.did.burgers.domain.repository;

import ru.did.burgers.enums.ProductNameEnum;

public interface ProductRepository {
    long updateCount(ProductNameEnum product, Long count);
}
