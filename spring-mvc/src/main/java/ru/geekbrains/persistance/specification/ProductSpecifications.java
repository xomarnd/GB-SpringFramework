package ru.geekbrains.persistance.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persistance.Product;

public class ProductSpecifications {
    public static Specification<Product> priceGEThan(int minPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLEThan(int maxPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}
