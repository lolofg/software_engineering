package com.software.website.service;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.software.website.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import com.software.website.Entity.Product;
import com.software.website.RowMapper.ProductRowMapper;

import java.util.Arrays;
import java.util.List;

class ProductServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts_ShouldReturnListOfProducts() {
        Product product1 = new Product(1, "Product1", 100);
        Product product2 = new Product(2, "Product2", 200);

        when(jdbcTemplate.query(eq("select * from Product"), any(ProductRowMapper.class)))
                .thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertThat(products).hasSize(2);
        assertThat(products.get(0).getProductName()).isEqualTo("Product1");
        assertThat(products.get(1).getProductName()).isEqualTo("Product2");

        verify(jdbcTemplate, times(1)).query(eq("select * from Product"), any(ProductRowMapper.class));
    }

    @Test
    void getOneProductID_ShouldReturnSingleProduct() {
        Product product = new Product(1, "Product1", 100);

        when(jdbcTemplate.queryForObject(eq("select * from Product where ProductID = ?"), 
                                         any(ProductRowMapper.class), eq(1)))
                .thenReturn(product);

        Product result = productService.getOneProductID(1);

        assertThat(result.getProductID()).isEqualTo(1);
        assertThat(result.getProductName()).isEqualTo("Product1");
        assertThat(result.getProductTypeID()).isEqualTo(100);

        verify(jdbcTemplate, times(1)).queryForObject(eq("select * from Product where ProductID = ?"), 
                                                      any(ProductRowMapper.class), eq(1));
    }
}
