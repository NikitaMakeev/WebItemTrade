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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String addProduct(@ModelAttribute Product product, @RequestParam("image") MultipartFile imageFile) {
        try {
            productService.addProduct(product, imageFile);
            return "redirect:/showProducts";
        } catch (IOException e) {
            // Handle exception
            return "redirect:/error";
        }
    }

    @PostMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/showProducts";
    }



}

