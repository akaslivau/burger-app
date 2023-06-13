package ru.did.burgers.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.did.burgers.domain.model.BurgerOrder;
import ru.did.burgers.domain.model.OrderEntity;
import ru.did.burgers.domain.repository.OrderRepository;
import ru.did.burgers.domain.repository.ProductRepository;
import ru.did.burgers.enums.BurgerKindEnum;
import ru.did.burgers.enums.ProductNameEnum;

@Service
@RequiredArgsConstructor
public class KitchenService {

  private static final Logger log = LogManager.getLogger(KitchenService.class);
  private final ProductRepository productRepo;
  private final OrderRepository orderRepository;

  private final AtomicLong counter = new AtomicLong(0);

  @Transactional
  public void acceptOrder(List<BurgerOrder> orders) {
    log.info(counter.incrementAndGet());

    Map<ProductNameEnum, Long> products = countProducts(orders);
    boolean allProductsPresent = updateProducts(products);
    if (!allProductsPresent) {
      throw new IllegalArgumentException("Не хватает продуктов на заказ, беда!");
    }
    createOrders(orders);
  }

  private Map<ProductNameEnum, Long> countProducts(Iterable<BurgerOrder> orders) {
    Map<ProductNameEnum, Long> map = new HashMap<>();
    for (BurgerOrder order : orders) {
      BurgerKindEnum burger = order.getBurger();
      Integer count = order.getCount();

      List<ProductNameEnum> ingredients = burger.getIngredients();
      for (ProductNameEnum ingredient : ingredients) {
        map.merge(ingredient, (long) count, Long::sum);
      }
    }
    return map;
  }

  private boolean updateProducts(Map<ProductNameEnum, Long> products) {
    for (Map.Entry<ProductNameEnum, Long> entry : products.entrySet()) {
      ProductNameEnum product = entry.getKey();
      Long count = entry.getValue();

      long productCount = productRepo.updateCount(product, count);
      if (productCount < 0) {
        return false;
      }
    }
    return true;
  }

  private void createOrders(Iterable<BurgerOrder> orders) {
    List<OrderEntity> entities = StreamSupport.stream(orders.spliterator(), false)
        .map(OrderEntity::of)
        .collect(Collectors.toList());
    orderRepository.saveAll(entities);
  }

}
