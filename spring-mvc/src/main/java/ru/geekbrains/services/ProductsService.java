package ru.geekbrains.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.exception.ProductNotFoundException;
import ru.geekbrains.persistance.Product;
import ru.geekbrains.persistance.ProductsRepository;

import java.util.List;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> getAll() {
        return productsRepository.findAll();
    }
    public Page<Product> findAll(Specification<Product> spec, Integer page) {
        if(page < 1) {
            page = 1;
        }
        return productsRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }


    public Product saveOrUpdate(Product newProduct) {
        return productsRepository.save(newProduct);
    }

    public Product findById(Long id) {
        return productsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Can't found student with id = " + id));
    }
}
