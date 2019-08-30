package com.microservice.product.fetchers;


import com.microservice.product.services.IProductService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphQLDataFetchers {
    @Autowired
    private IProductService service;

    public DataFetcher findAll() {
        return dataFetchingEnvironment -> {
            return this.service.findAll();
        };
    }


}
