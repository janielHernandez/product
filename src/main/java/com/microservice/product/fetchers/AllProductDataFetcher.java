package com.microservice.product.fetchers;

import com.microservice.product.entities.Product;
import com.microservice.product.services.IProductService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllProductDataFetcher implements DataFetcher<List<Product>> {

    private IProductService productService;

    @Autowired
    public AllProductDataFetcher(IProductService productService) {
        this.productService=productService;
    }

    @Override
    public List<Product> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return this.productService.findAll();
    }
}
