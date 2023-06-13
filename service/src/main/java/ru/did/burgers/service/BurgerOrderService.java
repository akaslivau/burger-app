package ru.did.burgers.service;

import static ru.did.burgers.resources.ServiceConstants.NO_CONTENT;
import static ru.did.burgers.utils.exception.ExceptionUtils.badRequest;
import static ru.did.burgers.utils.exception.ExceptionUtils.iifThrow;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.did.burgers.BurgerApiDelegate;
import ru.did.burgers.TBurgerCreate;
import ru.did.burgers.domain.model.BurgerOrder;
import ru.did.burgers.enums.BurgerKindEnum;

@Service
@RequiredArgsConstructor
@Primary
public class BurgerOrderService implements BurgerApiDelegate {

  private final KitchenService kitchenService;
  private final ReentrantLock lock = new ReentrantLock();

  @Override
  public ResponseEntity<Void> orderBurger(TBurgerCreate dto) {
    processOrder(dto);
    return NO_CONTENT;
  }

  public void processOrder(TBurgerCreate dto) {
    BurgerOrder order = parse(dto);
    try {
      lock.lock();
      kitchenService.acceptOrder(Collections.singletonList(order));
    } finally {
      lock.unlock();
    }
  }

  private BurgerOrder parse(TBurgerCreate dto) {
    BurgerKindEnum burgerName = BurgerKindEnum.find(dto.getName())
        .orElseThrow(() -> badRequest("Такого бургера у нас нет"));

    Integer count = dto.getCount();
    iifThrow(Objects.isNull(count), () -> badRequest("Сколько Вам бургеров надо?"));
    iifThrow(count < 1, () -> badRequest("Ты дурак?"));

    return BurgerOrder.builder()
        .burger(burgerName)
        .count(count)
        .build();
  }


}