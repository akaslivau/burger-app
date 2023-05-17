package ru.did.burgers.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import ru.did.burgers.enums.BurgerKindEnum;

/**
 * @author Didyk Andrey
 */
@Setter
@Builder
@AllArgsConstructor
public class BurgerOrder {

    private final BurgerKindEnum burger;
    private final Integer count;

}
