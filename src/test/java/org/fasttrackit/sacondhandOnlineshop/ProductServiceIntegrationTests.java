package org.fasttrackit.sacondhandOnlineshop;

import org.fasttrackit.sacondhandOnlineshop.domain.Product;
import org.fasttrackit.sacondhandOnlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.sacondhandOnlineshop.service.ProductService;
import org.fasttrackit.sacondhandOnlineshop.steps.ProductSteps;
import org.fasttrackit.sacondhandOnlineshop.transfer.SaveProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSteps productSteps;


    @Test
    public void testCreateProduct_whenValidRequest_thenProductIsSaved() {
        productSteps.createProduct();
    }

    @Test(expected = TransactionSystemException.class)
    public void testCreateProduct_whenInvalidRequest_thenThrowException() {
        SaveProductRequest request = new SaveProductRequest();

        productService.createProduct(request);
    }

    @Test
    public void testGetProduct_whenExistingProduct_thenReturnProduct() {
        Product createProduct = productSteps.createProduct();

        Product product = productService.getProduct(createProduct.getId());

        assertThat(product, notNullValue());
        assertThat(product.getId(), is(createProduct.getId()));
        assertThat(product.getName(), is(createProduct.getName()));
        assertThat(product.getDescription(), is(createProduct.getDescription()));
        assertThat(product.getPrice(), is(createProduct.getPrice()));
        assertThat(product.getQuantity(), is(createProduct.getQuantity()));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetProduct_whenNotExistingProduct_thenThrowResourceNotFoundException() {
        productService.getProduct(9999999999L);
    }

    @Test
    public void testUpdateProduct_whenValidRequest_thenReturnUpdatedProduct() {
        Product createdProduct = productSteps.createProduct();

        SaveProductRequest request = new SaveProductRequest();
        request.setName(createdProduct.getName() + "updated");
        request.setDescription(createdProduct.getDescription() + "updated");
        request.setPrice(createdProduct.getPrice() + 10);
        request.setQuantity(createdProduct.getQuantity() + 10);

        Product updatedProduct = productService.updateProduct(createdProduct.getId(), request);

        assertThat(updatedProduct, notNullValue());
        assertThat(updatedProduct.getId(), is(createdProduct.getId()));
        assertThat(updatedProduct.getName(), is(request.getName()));
        assertThat(updatedProduct.getDescription(), is(request.getDescription()));
        assertThat(updatedProduct.getPrice(), is(request.getPrice()));
        assertThat(updatedProduct.getQuantity(), is(request.getQuantity()));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteProduct_whenExistingProduct_thenProductIsDeleted() {
        Product product = productSteps.createProduct();

        productService.deleteProduct(product.getId());

        productService.getProduct(product.getId());

    }
}
