package ru.did.burgers.resources;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;

public final class ServiceConstants {
    public static final ResponseEntity<Void> NO_CONTENT = ResponseEntity.noContent().build();

    public static final Gson GSON = new Gson();


    private ServiceConstants() {
        throw new RuntimeException("forbidden");
    }
}
