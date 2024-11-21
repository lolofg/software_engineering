package com.software.website.controller;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import com.software.website.service.ProductService;
import com.software.website.Entity.Product;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void getAllProducts_ShouldReturnListOfProducts() {
        MockitoAnnotations.openMocks(this);

        Product product1 = new Product(1, "Product1", 100);
        Product product2 = new Product(2, "Product2", 200);
        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productController.getAllProducts();

        assertThat(products).hasSize(2);
        assertThat(products.get(0).getProductName()).isEqualTo("Product1");
        assertThat(products.get(1).getProductName()).isEqualTo("Product2");

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void getOneProduct_ShouldReturnProduct() {
        MockitoAnnotations.openMocks(this);

        Product mockProduct = new Product(1, "Product1", 100);
        when(productService.getOneProductID(1)).thenReturn(mockProduct);

        Product product = productController.getOneProduct(1);

        assertThat(product.getProductID()).isEqualTo(1);
        assertThat(product.getProductName()).isEqualTo("Product1");
        assertThat(product.getProductTypeID()).isEqualTo(100);

        verify(productService, times(1)).getOneProductID(1);
    }
}

