package ru.did.burgers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.did.burgers.domain.model.BurgerOrder;
import ru.did.burgers.domain.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class KitchenService {

    private final ProductRepository productRepo;

    @Transactional
    public void acceptOrder(Iterable<BurgerOrder> orders) {

    }
}
