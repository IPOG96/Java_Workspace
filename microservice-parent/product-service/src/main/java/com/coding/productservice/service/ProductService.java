package com.coding.productservice.service;


import com.coding.productservice.dto.ProductRequest;
import com.coding.productservice.dto.ProductResponse;
import com.coding.productservice.model.Product;
import com.coding.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {


    @Autowired
    ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest) {

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());


    }


    public List<ProductResponse> getAllProducts() {

        List<Product> products = (List<Product>) productRepository.findAll();
        //  products.stream().map(product -> mapToProductResponse(product));
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }
}
