package com.example.webitemtradeproductadding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import com.example.webitemtradeproductadding.repository.ProductRepository;
import com.example.webitemtradeproductadding.model.Product;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    private ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public void addProduct(Product product, MultipartFile file) throws IOException {
        product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        productRepository.save(product);
    }
    public Optional<Product> getProduct(Long id){
        return productRepository.findById(id);
    }
}
