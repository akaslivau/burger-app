package ru.did.burgers.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.did.burgers.enums.BurgerKindEnum;

/**
 * @author Didyk Andrey
 */
@Getter
@Builder
@AllArgsConstructor
public class BurgerOrder {

    private final BurgerKindEnum burger;
    private final Integer count;

    public String getInfo() {
        return burger + ": " + count;
    }
}
