package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persistance.Product;
import ru.geekbrains.persistance.ProductRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired private ProductRepository productRepository;

    @GetMapping
    public String allProducts(Model model) throws SQLException {
        List<Product> products = productRepository.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productRepository.getById(id);
        model.addAttribute("product", product);
        return "product";
    }
    @GetMapping("/{id}/del")
    public String delProduct(@PathVariable Long id) {
        try {
            productRepository.del(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/products";
    }

    @PostMapping("/update")
    public String updateProduct(Product product) throws SQLException {
        if (product.getId() == 0) {
            productRepository.insert(product);
        } else {
            productRepository.update(product);
        }
        return "redirect:/products";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) throws SQLException {
        Product product = new Product(0, "", new BigDecimal(0));
        model.addAttribute("product", product);
        return "product";
    }
}
