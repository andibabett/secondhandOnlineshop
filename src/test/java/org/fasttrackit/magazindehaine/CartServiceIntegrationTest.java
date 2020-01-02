package org.fasttrackit.magazindehaine;

import org.fasttrackit.magazindehaine.service.CartService;
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

    @Test
    public void testAddProductToCart_whenNewCartForExistingCustomer_thenCartIsSaved(){

    }
}
