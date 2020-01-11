package org.fasttrackit.secondhandOnlineshop.web;


import org.fasttrackit.secondhandOnlineshop.domain.Product;
import org.fasttrackit.secondhandOnlineshop.service.ProductService;
import org.fasttrackit.secondhandOnlineshop.transfer.GetProductsRequest;
import org.fasttrackit.secondhandOnlineshop.transfer.ProductResponse;
import org.fasttrackit.secondhandOnlineshop.transfer.SaveProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    // todo: fix LazyLoading for all endpoints
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid SaveProductRequest request) {
        Product product = productService.createProduct(request);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getProducts(
            GetProductsRequest request, Pageable pageable) {
        Page<ProductResponse> products = productService.getProducts(request, pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") Long id, @RequestBody @Valid SaveProductRequest request) {
        Product product = productService.updateProduct(id, request);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
