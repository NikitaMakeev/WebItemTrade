package com.web_trade.controllers;

import com.web_trade.entity.Product;
import com.web_trade.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/showProducts")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("product", new Product());
        return "products";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/showProducts";
    }
}

