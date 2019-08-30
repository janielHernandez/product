package com.microservice.product.services.impl;

import com.microservice.product.entities.Product;
import com.microservice.product.exception.ProductException;
import com.microservice.product.repositories.ProductRepository;
import com.microservice.product.services.IProductService;
//import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ProductService implements IProductService {

    private ProductRepository productRepository;
//    DozerBeanMapper mapper;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
//        this.mapper = new DozerBeanMapper();
    }

    public List<Product> findAll(){
        return (List<Product>) this.productRepository.findAll();
    }

    public Product findById(Long id) throws ProductException{
        return this.productRepository.findById(id).orElseThrow(
                () -> new ProductException("Id " + id + " not found it")
        );
    }

    public Product save(Product product){
        return this.productRepository.save(product);
    }

    public void deleteById(Long id) throws ProductException{

        if (this.productRepository.existsById(id))
            this.productRepository.deleteById( id );
        else
            throw new ProductException("id " + id + " not found it");
    }

    public  Product update(Long id, Product details) throws ProductException{
        Product product = this.findById(id);
//        mapper.map(details, product);
        product.setId(id);
        return this.save(product);
    }
}
