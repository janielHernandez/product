package com.microservice.product.controllers;


import com.microservice.product.entities.Product;
import com.microservice.product.exception.ProductException;
import com.microservice.product.services.GraphQLProductService;
import com.microservice.product.services.IProductService;
import graphql.ExecutionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    private GraphQLProductService graphQLService;
    private IProductService productService;

    @Autowired
    public ProductController(IProductService productService,
                             GraphQLProductService graphQLService){
        this.productService = productService;
        this.graphQLService = graphQLService;
    }

    @PostMapping("graphql")
    public ResponseEntity<Object> graph(@RequestBody String query){
        logger.info("Looking ");
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) throws ProductException {
        var product = this.productService.findById(id);
        return ResponseEntity.ok( product );
    }

    @GetMapping
    public List<Product> findAll(){
        return this.productService.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) throws ProductException{
        this.productService.deleteById(id);
    }

    @PostMapping
    public Product create(@RequestBody @Valid Product product){
        return this.productService.save(product);
    }

    @PutMapping("{id}")
    public Product update(@PathVariable("id") Long id, @RequestBody @Valid Product details) throws ProductException{
       return this.productService.update(id, details);
    }
}
