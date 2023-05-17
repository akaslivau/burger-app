package ru.did.burgers.enums;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@AllArgsConstructor
@Getter
public enum ProductNameEnum {
    BUN("Булочка"),
    SAUCE("Соус"),
    SALAD("Салат"),
    CUCUMBER("Хрястящий маринованный огручик"),
    MEAT("Мясная котлета"),
    CHEESE("Сыр"),
    ONION("Лук"),
    TOMATO("Помидорчик"),
    BACON("Бекон");

    private final String title;

    /**
     * Ищет экземпляр перечисления по его наименованию.
     */
    public static Optional<ProductNameEnum> find(String o) {
        if (Strings.isNullOrEmpty(o)) {
            return Optional.empty();
        }

        return Stream.of(ProductNameEnum.values()).filter(k -> k.toString().equals(o)).findFirst();
    }

    /**
     * Проверяет наличие экземпляра в перечислении по его наименованию.
     */
    public static boolean exists(String o) {
        return Stream.of(ProductNameEnum.values()).anyMatch(k -> Objects.equals(k.name(), o));
    }

    /**
     * Возвращает весь список перечисления.
     */
    public static List<ProductNameEnum> findAll() {
        return Stream.of(ProductNameEnum.values()).collect(Collectors.toList());
    }
}
