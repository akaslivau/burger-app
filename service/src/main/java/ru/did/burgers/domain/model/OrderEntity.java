package ru.did.burgers.domain.model;

import lombok.*;

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
public class OrderEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "info")
    private String info;

    @Column(name = "is_finished")
    private Boolean isFinished;

    public static OrderEntity of(BurgerOrder dto){
        return OrderEntity.builder()
                .info(dto.getInfo())
                .isFinished(false)
                .build();
    }

}
