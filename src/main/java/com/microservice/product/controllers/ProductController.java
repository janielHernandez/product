package com.microservice.product.controllers;


import com.microservice.product.entities.Product;
import com.microservice.product.exception.ProductException;
import com.microservice.product.services.IProductService;
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

//    private final GraphQLProductService graphQLService;
    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService){
        this.productService = productService;
//        this.graphQLService = graphQLService;
    }

//    @PostMapping("graphql")
//    public ResponseEntity<Object> graph(@RequestBody String query){
//        logger.info("Looking ");
//        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
//        return new ResponseEntity<>(execute, HttpStatus.OK);
//    }

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
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws ProductException{

        try{
            this.productService.deleteById(id);
            return ResponseEntity.ok( Boolean.TRUE );
        } catch (ProductException ex) {
            throw new ProductException("id " + id + " dont exist, could be deleted");
        }

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
