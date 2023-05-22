package ru.did.burgers.data;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.did.burgers.data.jpa.ProductJpaRepository;
import ru.did.burgers.domain.model.Product;
import ru.did.burgers.domain.repository.ProductRepository;
import ru.did.burgers.enums.ProductNameEnum;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository jpaRepo;

    @Override
    public long updateCount(ProductNameEnum product, Long count) {
        Product p = jpaRepo.findById(product.name())
                .orElseThrow(() -> new IllegalArgumentException("Product not found " + product.name()));
        long newCount = p.getCount() - count;
        if (newCount < 0) {
            return newCount;
        }
        p.setCount(newCount);
        jpaRepo.save(p);
        return newCount;
    }
}
