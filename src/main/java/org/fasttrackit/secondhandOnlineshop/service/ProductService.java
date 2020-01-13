package org.fasttrackit.secondhandOnlineshop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.secondhandOnlineshop.domain.Product;
import org.fasttrackit.secondhandOnlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.secondhandOnlineshop.persistance.ProductRepository;
import org.fasttrackit.secondhandOnlineshop.transfer.product.GetProductRequest;
import org.fasttrackit.secondhandOnlineshop.transfer.product.ProductResponse;
import org.fasttrackit.secondhandOnlineshop.transfer.product.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    public Product createProduct(SaveProductRequest request) {

        LOGGER.info("Creating product {}", request);
        Product product = objectMapper.convertValue(request, Product.class);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setImageUrl(request.getImageUrl());

        return productRepository.save(product);
    }

    public Product getProduct(long id) {
        LOGGER.info("Retrivering product {}" + id);

        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product " + id + "does not exist."));
    }

    public Product updateProduct(long id, SaveProductRequest request) {
        LOGGER.info("Udating product {}: {}", id, request);
        Product product = getProduct(id);
        BeanUtils.copyProperties(request, product);
        return productRepository.save(product);
    }

    public void deleteProduct(long id) {
        LOGGER.info("Deleting product {}", id);
        productRepository.deleteById(id);
        LOGGER.info("Deleted product {}", id);
    }

    @Transactional
    public Page<ProductResponse> getProducts(GetProductRequest request, Pageable pageable) {
        LOGGER.info("Retrieving products: {}", request);

        Page<Product> products;

        if (request != null && request.getPartialName() != null &&
                request.getMinQuantity() != null) {
            products = productRepository.findByNameContainingAndQuantityGreaterThanEqual(
                    request.getPartialName(), request.getMinQuantity(), pageable);
        } else if (request != null && request.getPartialName() != null) {
            products = productRepository.findByNameContaining(
                    request.getPartialName(), pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products.getContent()) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setDescription(product.getDescription());
            productResponse.setQuantity(product.getQuantity());
            productResponse.setImageUrl(product.getImageUrl());

            productResponses.add(productResponse);
        }
        return new PageImpl<>(productResponses, pageable, products.getTotalElements());
    }


}



