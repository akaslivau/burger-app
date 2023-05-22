package ru.did.burgers.integration;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import ru.did.burgers.TBurgerCreate;
import ru.did.burgers.service.BurgerOrderService;

import static ru.did.burgers.resources.ServiceConstants.GSON;

@Configuration
@KafkaListener(topics = "${integration.order.burger}",
        containerFactory = "messageConcurrentKafkaListenerContainerFactory")
@RequiredArgsConstructor
public class BurgerOrderConsumer {
    private static final Logger log = LogManager.getLogger(BurgerOrderConsumer.class);
    private final BurgerOrderService burgerOrderService;

    @KafkaHandler
    public void listen(String message) {
        try {
            TBurgerCreate payload = GSON.fromJson(message, TBurgerCreate.class);
            burgerOrderService.processOrder(payload);
        } catch (Exception ex) {
            log.error(ex);
        }
    }

}