package org.fasttrackit.secondhandOnlineshop;

import org.fasttrackit.secondhandOnlineshop.domain.Customer;
import org.fasttrackit.secondhandOnlineshop.domain.Product;
import org.fasttrackit.secondhandOnlineshop.service.CartService;
import org.fasttrackit.secondhandOnlineshop.steps.CustomerSteps;
import org.fasttrackit.secondhandOnlineshop.steps.ProductSteps;
import org.fasttrackit.secondhandOnlineshop.transfer.cart.AddProductToCartRequest;
import org.fasttrackit.secondhandOnlineshop.transfer.cart.CartResponse;
import org.fasttrackit.secondhandOnlineshop.transfer.product.ProductInCartResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

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

        assertThat(cart, notNullValue());
        assertThat(cart.getId(), is(customer.getId()));
        assertThat(cart.getProducts(),notNullValue());
        assertThat(cart.getProducts(), hasSize(1));

//        Iterator<ProductInCartResponse> iterator = cart.getProducts().iterator();
//        assertThat(iterator.hasNext(), is(true));

        ProductInCartResponse productFromCart = cart.getProducts().iterator().next();

        assertThat(productFromCart, notNullValue());
        assertThat(productFromCart.getId(), is(product.getId()));
        assertThat(productFromCart.getName(), is(product.getName()));
        assertThat(productFromCart.getPrice(), is(product.getPrice()));
    }
}
