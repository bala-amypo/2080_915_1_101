package com.example.demo.service;

import java.util.*;
import java.time.LocalDate;
import com.example.demo.model.*;
public interface ProductService {
    Product createProduct(Product p);
    Product getProduct(Long id);
    List<Product> getAllProducts();
}
