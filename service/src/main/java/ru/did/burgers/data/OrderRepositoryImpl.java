package ru.did.burgers.data;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.did.burgers.data.jpa.OrderJpaRepository;
import ru.did.burgers.domain.model.OrderEntity;
import ru.did.burgers.domain.repository.OrderRepository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository jpaRepo;

    @Override
    public void saveAll(Collection<OrderEntity> entities) {
        jpaRepo.saveAll(entities);
    }
}
