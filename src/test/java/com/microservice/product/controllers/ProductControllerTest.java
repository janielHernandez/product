package com.microservice.product.controllers;


import com.microservice.product.ProductApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = ProductApplication.class)

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test_application.properties")
@AutoConfigureTestDatabase
public class ProductControllerTest {

    private MockMvc mockMvc;

    private static final Logger logger = LoggerFactory.getLogger(ProductControllerTest.class);

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void verifyAllProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4))).andDo(print());
    }

    @Test
    public void testGetSpain() throws Exception {
        String response = mockMvc.perform(get("/product"+ "/{id}/", 99))
                .andExpect( status().is(HttpStatus.OK.value()) )
                .andExpect(jsonPath("$.name", is("test")))
                .andReturn()
                .getResponse()
                .getContentAsString();

        logger.info("response: " + response);
    }


    @Test
    public void verifyProductById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/99").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.id").value(99))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.price").value(0))
                .andDo(print());
    }

    @Test
    public void verifyInvalidProductId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/0").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andDo(print());
    }


}
