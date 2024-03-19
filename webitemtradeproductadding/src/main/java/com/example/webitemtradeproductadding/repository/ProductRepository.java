package com.example.webitemtradeproductadding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.webitemtradeproductadding.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}