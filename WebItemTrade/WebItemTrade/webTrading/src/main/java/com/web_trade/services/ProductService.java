package com.web_trade.services;

import com.web_trade.components.AuthenticationFacade;
import com.web_trade.entity.Product;
import com.web_trade.entity.User;
import com.web_trade.repository.ProductRepository;
import com.web_trade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    public List<Product> getProductsByCurrentUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
            User user = currentUser.getUser();
            return productRepository.findByUser(user);
        }
        return Collections.emptyList();
    }

    public void addProduct(Product product, MultipartFile imageFile) throws IOException {
        String imageName = saveImage(imageFile);
        product.setImageName(imageName);

        Authentication authentication = authenticationFacade.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
            User user = currentUser.getUser();
            product.setUser(user);
            productRepository.save(product);
        }
    }


    private String saveImage(MultipartFile imageFile) throws IOException {
        byte[] bytes = imageFile.getBytes();
        String imageName = imageFile.getOriginalFilename();
        Path path = Paths.get("webTrading/src/main/resources/static/images/" + imageName);
        Files.write(path, bytes);
        return imageName;
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

}