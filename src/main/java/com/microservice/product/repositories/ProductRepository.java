package com.microservice.product.repositories;

import com.microservice.product.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByName(String name);

}
