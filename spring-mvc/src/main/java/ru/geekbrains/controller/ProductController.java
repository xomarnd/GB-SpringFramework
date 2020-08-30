package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persistance.Product;
import ru.geekbrains.persistance.ProductRepository;


@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String allProducts(Model model) {
        model.addAttribute("products", productRepository.getAllProducts());
        return "products";
    }

    @GetMapping("/form")
    public String formProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/form")
    public String newProduct(Product product) {
        productRepository.insert(product);
        return "redirect:/product";
    }

    @GetMapping("/find_form")
    public String findProduct(String title) {
        productRepository.findUsingIterator(title);
        return "redirect:/product";
    }
}
