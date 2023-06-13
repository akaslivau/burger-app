package ru.did.burgers.integration;

import static ru.did.burgers.resources.ServiceConstants.GSON;

import com.google.common.base.Strings;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import ru.did.burgers.TBurgerCreate;
import ru.did.burgers.service.BurgerOrderService;

@Configuration
@KafkaListener(topics = "${integration.order.burger}",
    containerFactory = "messageConcurrentKafkaListenerContainerFactory")
@RequiredArgsConstructor
public class BurgerOrderConsumer {

  private static final Logger log = LogManager.getLogger(BurgerOrderConsumer.class);
  private static final int N_THREADS = 10;
  private final BurgerOrderService burgerOrderService;
  private final ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);

  @KafkaHandler
  public void listen(String message, @Header(value = "uuid", required = false) String msgGuid) {
    TBurgerCreate payload = GSON.fromJson(message, TBurgerCreate.class);
    executor.submit(() -> burgerOrderService.processOrder(payload));
  }

  @PreDestroy
  void preDestroy() {
    executor.shutdown();
    try {
      if (!executor.awaitTermination(20, TimeUnit.SECONDS)) {
        executor.shutdownNow();
      }
    } catch (InterruptedException ie) {
      executor.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }
}