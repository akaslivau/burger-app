package ru.did.burgers.domain.model;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import ru.did.burgers.enums.ProductNameEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "name", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private ProductNameEnum productNameEnum;

    @Column(name = "count")
    private Long count;

    @Version
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
