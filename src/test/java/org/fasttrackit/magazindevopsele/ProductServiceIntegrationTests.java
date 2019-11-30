package org.fasttrackit.magazindevopsele;

import org.fasttrackit.magazindevopsele.domain.Product;
import org.fasttrackit.magazindevopsele.exception.ResourceNotFoundException;
import org.fasttrackit.magazindevopsele.service.ProductService;
import org.fasttrackit.magazindevopsele.transfer.SaveProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;


@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceIntegrationTests {

    @Autowired
    private ProductService productService;


    @Test
    public void testCreateProduct_whenValidRequest_thenProductIsSaved() {
        createProduct();
    }


    @Test(expected = TransactionSystemException.class)
    public void testCreateProduct_whenInvalidRequest_thenThrowException() {
        SaveProductRequest request = new SaveProductRequest();

        productService.createProduct(request);
    }

    @Test
    public void testGetProduct_whenExistingProduct_thenReturnProduct() {
        Product createProduct = createProduct();

        Product product = productService.getProduct(createProduct.getId());

        assertThat(product, notNullValue());
        assertThat(product.getId(), is(createProduct.getId()));
        assertThat(product.getName(), is(createProduct.getName()));
        assertThat(product.getProductCode(), is(createProduct.getProductCode()));
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
        Product createdProduct = createProduct();

        SaveProductRequest request = new SaveProductRequest();
        request.setName(createdProduct.getName()+ "updated");
        request.setDescription(createdProduct.getDescription()+ "updated");
        request.setProductCode(createdProduct.getProductCode()+ "updated");
        request.setPrice(createdProduct.getPrice()+ 10);
        request.setQuantity(createdProduct.getQuantity()+ 10);

        Product updatedProduct = productService.updateProduct(createdProduct.getId(), request);

        assertThat(updatedProduct, notNullValue());
        assertThat(updatedProduct.getId(), is(updatedProduct.getId()));
        assertThat(updatedProduct.getName(), is(updatedProduct.getName()));
        assertThat(updatedProduct.getProductCode(), is(updatedProduct.getProductCode()));
        assertThat(updatedProduct.getDescription(), is(updatedProduct.getDescription()));
        assertThat(updatedProduct.getPrice(), is(updatedProduct.getPrice()));
        assertThat(updatedProduct.getQuantity(), is(updatedProduct.getQuantity()));

    }

    private Product createProduct() {
        SaveProductRequest request = new SaveProductRequest();
        request.setName("Banana " + System.currentTimeMillis());
        request.setPrice(5.0);
        request.setQuantity(100);
        request.setDescription("Healthy food");
        request.setProductCode("1A");

        Product createdProduct = productService.createProduct(request);

        assertThat(createdProduct, notNullValue());
        assertThat(createdProduct.getId(), notNullValue());
        assertThat(createdProduct.getId(), greaterThan(0L));
        assertThat(createdProduct.getName(), is(request.getName()));
        assertThat(createdProduct.getDescription(), is(request.getDescription()));
        assertThat(createdProduct.getPrice(), is(request.getPrice()));
        assertThat(createdProduct.getProductCode(), is(request.getProductCode()));
        assertThat(createdProduct.getQuantity(), is(request.getQuantity()));

        return createdProduct;

    }


}
