package ru.did.burgers.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.did.burgers.domain.model.Product;

public interface ProductJpaRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

}
