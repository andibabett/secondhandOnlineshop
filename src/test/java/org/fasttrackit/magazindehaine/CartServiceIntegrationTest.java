package org.fasttrackit.magazindehaine;

import org.fasttrackit.magazindehaine.domain.Customer;
import org.fasttrackit.magazindehaine.service.CartService;
import org.fasttrackit.magazindehaine.steps.CustomerSteps;
import org.fasttrackit.magazindehaine.transfer.AddProductToCartRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceIntegrationTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerSteps customerSteps;

    @Test
    public void testAddProductToCart_whenNewCartForExistingCustomer_thenCartIsSaved() {
        Customer customer = customerSteps.createCustomer();

        AddProductToCartRequest request = new AddProductToCartRequest();
        request.setCustomerId(customer.getId());

        cartService.addProductToCart(request);
    }
}
