package com.microservice.product.Services;

import com.microservice.product.entities.Product;
import com.microservice.product.exception.ProductException;
import com.microservice.product.repositories.ProductRepository;
import com.microservice.product.services.impl.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    Product product;

    @Before
    public void init() {
//        product = new Product(10L,"Egg",1.4, new Date());
    }

    @Test
    public void findAllProduct() {
        when(productRepository.findAll() ).thenReturn(Arrays.asList(
                new Product[]{this.product }
                ) );
        assertEquals(1, productService.findAll().size());
    }

    @Test
    public void findProductByIdTest() throws ProductException {
        when(productRepository.findById(10L)).thenReturn(java.util.Optional.of(product));
        Product result = productService.findById(10L);
        this.commonProductTest(result);
    }

    @Test
    public void saveProductTest(){
        when(productRepository.save(product)).thenReturn(product);
        Product result = productService.save(product);
        this.commonProductTest(result);
    }

    private void commonProductTest(Product p){
        assertEquals(10L, p.getId().longValue() );
        assertEquals("Egg", p.getName());
        assertEquals("1.4", p.getPrice().toString());
    }



}
