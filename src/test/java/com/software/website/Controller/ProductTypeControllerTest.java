package com.software.website.controller;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.software.website.Entity.ProductType;
import com.software.website.service.ProductTypeService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductTypeService productTypeService;

    @BeforeEach
    public void setUp(){

        ProductType productType1 = new ProductType(); 
        productType1.setProductTypeName("Telefon");
        productType1.setProductTypeID(4);

        ProductType productType2 = new ProductType(); 
        productType2.setProductTypeName("TV");
        productType2.setProductTypeID(3);

        ProductType productType3 = new ProductType(); 
        productType3.setProductTypeName("Radio");
        productType3.setProductTypeID(8);

        when(productTypeService.getAllProductTypes()).thenReturn(Arrays.asList(productType1, productType2, productType3)); 
    }


    @Test
    public void testGetAllProductTypes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/allProductTypes")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(3))
        .andExpect(jsonPath("$[0].productTypeName").value("Telefon"))
        .andExpect(jsonPath("$[1].productTypeName").value("TV"))
        .andExpect(jsonPath("$[2].productTypeName").value("Radio")); 
    }

    @Test
    public void testGetAllProductTypeEmptyList() throws Exception {

        when(productTypeService.getAllProductTypes()).thenReturn(Collections.emptyList());


        mockMvc.perform(MockMvcRequestBuilders.get("/allProductTypes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

        verify(productTypeService).getAllProductTypes();
    }
    
}
