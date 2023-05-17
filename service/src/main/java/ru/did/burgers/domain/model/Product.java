package ru.did.burgers.domain.model;

import lombok.*;
import ru.did.burgers.enums.ProductNameEnum;

import javax.persistence.*;

/**
 * @author Didyk Andrey
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "name")
    private String name;

    @MapsId
    @Enumerated(EnumType.STRING)
    private ProductNameEnum productNameEnum;

    @Column(name = "count")
    private Long count;

}
