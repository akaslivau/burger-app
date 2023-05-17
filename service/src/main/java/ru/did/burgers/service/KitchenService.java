package ru.did.burgers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.did.burgers.domain.model.BurgerOrder;

@Service
@RequiredArgsConstructor
public class KitchenService {

    public void acceptOrder(Iterable<BurgerOrder> orders){

    }
}
