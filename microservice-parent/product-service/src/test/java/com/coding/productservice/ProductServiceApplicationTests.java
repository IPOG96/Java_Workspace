/*
package com.coding.productservice;

import com.coding.productservice.dto.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.function.Supplier;


@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Container
    static OracleContainer oracle = new OracleContainer.("Oracle Database 18c (18.4.0) Express Edition (XE)");


    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", (Supplier<Object>) oracle.getJdbcDriverInstance());
    }


    @Test
    void shouldCreateProduct() throws Exception {

        ProductRequest productRequest = getProductRequest();

        String productRequestString = objectMapper.writeValueAsString(productRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                        .content(String.valueOf(MediaType.APPLICATION_JSON)).content(productRequestString))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    private ProductRequest getProductRequest() {

        return ProductRequest.builder().name("Iphone12").description("Iphone12").price(BigDecimal.valueOf(12000)).build();
    }

}
*/
