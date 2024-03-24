package com.web_trade.controllers;

import com.web_trade.entity.Product;
import com.web_trade.entity.User;
import com.web_trade.services.CustomUserDetails;
import com.web_trade.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/showProducts")
    public String showProducts(Model model) {
        List<Product> products = productService.getProductsByCurrentUser();
        model.addAttribute("products", products);
        model.addAttribute("product", new Product());
        return "products";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/showProducts";
    }
}

