package com.microservice.product.services;

import com.microservice.product.entities.Product;
import com.microservice.product.exception.ProductException;

import java.util.List;

public interface IProductService {

    List<Product> findAll();
    Product findById(Long id) throws ProductException;
    Product save(Product product);
    void deleteById(Long id) throws ProductException;
    Product update(Long id, Product details) throws ProductException;
}
