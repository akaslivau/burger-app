package ru.did.burgers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableRetry
@EnableCaching(proxyTargetClass = true)
public class BurgersApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurgersApplication.class, args);
    }

}
