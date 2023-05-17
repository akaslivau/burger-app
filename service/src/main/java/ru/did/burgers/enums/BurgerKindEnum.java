package ru.did.burgers.enums;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.List.of;
import static ru.did.burgers.enums.ProductNameEnum.*;


@AllArgsConstructor
@Getter
public enum BurgerKindEnum {
    CLASSIC("Классический", of(BUN, MEAT, MEAT, SAUCE, SAUCE, SALAD, CUCUMBER, TOMATO)),
    SIMPLE_HAMBURGER("Простой гамбургер", of(BUN, MEAT, SAUCE)),
    SIMPLE_CHEESBURGER("Простой чизбургер", of(BUN, MEAT, CHEESE, SAUCE)),
    BACONIZER("Беконайзер", of(BUN, MEAT, MEAT, BACON, CHEESE, SAUCE, SAUCE, TOMATO, ONION));

    private final String title;
    private final List<ProductNameEnum> ingredients;

    /**
     * Ищет экземпляр перечисления по его наименованию.
     */
    public static Optional<BurgerKindEnum> find(String o) {
        if (Strings.isNullOrEmpty(o)) {
            return Optional.empty();
        }

        return Stream.of(BurgerKindEnum.values()).filter(k -> k.toString().equals(o)).findFirst();
    }
}
