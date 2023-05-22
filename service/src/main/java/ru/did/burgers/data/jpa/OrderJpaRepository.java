package ru.did.burgers.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.did.burgers.domain.model.OrderEntity;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long>, QuerydslPredicateExecutor<OrderEntity> {

}
