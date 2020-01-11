package org.fasttrackit.secondhandOnlineshop;

import org.fasttrackit.secondhandOnlineshop.domain.Customer;
import org.fasttrackit.secondhandOnlineshop.domain.Product;
import org.fasttrackit.secondhandOnlineshop.service.CartService;
import org.fasttrackit.secondhandOnlineshop.steps.CustomerSteps;
import org.fasttrackit.secondhandOnlineshop.steps.ProductSteps;
import org.fasttrackit.secondhandOnlineshop.transfer.AddProductToCartRequest;
import org.fasttrackit.secondhandOnlineshop.transfer.CartResponse;
import org.fasttrackit.secondhandOnlineshop.transfer.ProductInCartResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceIntegrationTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerSteps customerSteps;

    @Autowired
    private ProductSteps productSteps;

    @Test
    public void testAddProductToCart_whenNewCartForExistingCustomer_thenCartIsSaved() {
        Customer customer = customerSteps.createCustomer();
        Product product = productSteps.createProduct();

        AddProductToCartRequest request = new AddProductToCartRequest();
        request.setCustomerId(customer.getId());
        request.setProductId(product.getId());

        cartService.addProductToCart(request);

        CartResponse cart = cartService.getCart(customer.getId());

        assertThat(cart.getId(), is(customer.getId()));

        Iterator<ProductInCartResponse> iterator = cart.getProducts().iterator();

        assertThat(iterator.hasNext(), is(true));

        ProductInCartResponse productFromCart = iterator.next();

        assertThat(productFromCart, notNullValue());
        assertThat(productFromCart.getId(), is(product.getId()));
        assertThat(productFromCart.getName(), is(product.getName()));
        assertThat(productFromCart.getPrice(), is(product.getPrice()));
    }
}
