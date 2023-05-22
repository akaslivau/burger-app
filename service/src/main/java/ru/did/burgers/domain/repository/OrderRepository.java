package ru.did.burgers.domain.repository;

import ru.did.burgers.domain.model.OrderEntity;

import java.util.Collection;

public interface OrderRepository {

    void saveAll(Collection<OrderEntity> entities);
}
