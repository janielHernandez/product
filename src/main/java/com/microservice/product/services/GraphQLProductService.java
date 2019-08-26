package com.microservice.product.services;

import com.microservice.product.fetchers.AllProductDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLProductService {

    @Value("classpath:/graph/product.graphqls")
    Resource resource;

    private IProductService productService;

    private GraphQL graphQL;

    private  AllProductDataFetcher allProductDataFetcher;

    @Autowired
    public GraphQLProductService(IProductService productService,
                                 AllProductDataFetcher allProductDataFetcher
    ) {
        this.productService = productService;
        this.allProductDataFetcher = allProductDataFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {

        File file = resource.getFile();

        //Parse SchemaF
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
        RuntimeWiring runtimeWiring = this.buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }
    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("findAll", allProductDataFetcher)
                )
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
