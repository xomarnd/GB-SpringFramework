package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persistance.Product;
import ru.geekbrains.persistance.specification.ProductSpecifications;
import ru.geekbrains.services.ProductsService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) { this.productsService = productsService; }

    @GetMapping
    public String allProducts(Model model) throws SQLException {
        List<Product> products = productsService.getAll();
        model.addAttribute("products", products);
        return "allproducts";
    }

//    @GetMapping("{id}")
//    public String editProduct(@PathVariable Long id, Model model) {
//        Product product = productRepository.getById(id);
//        model.addAttribute("product", product);
//        return "product";
//    }

//    @GetMapping("/{id}/del")
//    public String delProduct(@PathVariable Long id) {
//        try {
//            productRepository.del(id);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return "redirect:/products";
//    }

//    @PostMapping("/update")
//    public String updateProduct(Product product) throws SQLException {
//        if (product.getId() == 0) {
//            productRepository.insert(product);
//        } else {
//            productRepository.update(product);
//        }
//        return "redirect:/products";
//    }
    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", productsService.findById(id));
        return "product";
    }

    @PostMapping("/update")
    public String modifyStudent(@ModelAttribute Product modifiedProduct) {
        productsService.saveOrUpdate(modifiedProduct);
        return "redirect:/products/";
    }

//    @GetMapping("/new")
//    public String addNewProduct(Model model) throws SQLException {
//        Product product = new Product( "", new BigDecimal(0));
//        model.addAttribute("product", product);
//        return "product";
//    }
    @GetMapping("/new")
    public String showAddForm() {
        return "product";
    }

    @PostMapping("/new")
    public String saveNewStudent(@ModelAttribute Product newProduct) {
        productsService.saveOrUpdate(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/filter")
    public String findProductsByMinPrice(Model model,
                                         @RequestParam(name = "page", defaultValue = "1") Integer pageNumber,
                                         @RequestParam(name = "min_price", required = false) Integer minPrice,
                                         @RequestParam(name = "max_price", required = false) Integer maxPrice) {
        Specification<Product> spec = Specification.where(null);

        if(minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGEThan(minPrice));
        }
        if(maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLEThan(maxPrice));
        }

        List<Product> products = productsService.findAll(spec,pageNumber).getContent();
        model.addAttribute("products", products);
        return "products";
    }
}
