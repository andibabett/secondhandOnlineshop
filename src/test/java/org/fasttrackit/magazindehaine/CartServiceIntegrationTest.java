package org.fasttrackit.magazindehaine;

import org.fasttrackit.magazindehaine.domain.Cart;
import org.fasttrackit.magazindehaine.domain.Customer;
import org.fasttrackit.magazindehaine.domain.Product;
import org.fasttrackit.magazindehaine.service.CartService;
import org.fasttrackit.magazindehaine.steps.CustomerSteps;
import org.fasttrackit.magazindehaine.steps.ProductSteps;
import org.fasttrackit.magazindehaine.transfer.AddProductToCartRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.common.base.CharMatcher.is;
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

        Cart cart = cartService.getCart(customer.getId());

        assertThat(cart.getId(), is(customer.getId()));

    }
}
