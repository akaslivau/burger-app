package ru.did.burgers.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.did.burgers.domain.model.Customer;

public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

}
