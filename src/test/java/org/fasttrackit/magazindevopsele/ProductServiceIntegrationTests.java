package org.fasttrackit.magazindevopsele;

import org.fasttrackit.magazindevopsele.domain.Product;
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
    }
    @Test(expected = TransactionSystemException.class)
    public void testCreateProduct_whenInvalidRequest_thenThrowException(){
	    SaveProductRequest request = new SaveProductRequest();

	    productService.createProduct(request);
    }



}
