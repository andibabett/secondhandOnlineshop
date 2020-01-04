package org.fasttrackit.magazindehaine.service;

import org.fasttrackit.magazindehaine.domain.Cart;
import org.fasttrackit.magazindehaine.domain.Customer;
import org.fasttrackit.magazindehaine.domain.Product;
import org.fasttrackit.magazindehaine.exception.ResourceNotFoundException;
import org.fasttrackit.magazindehaine.persistance.CartRepository;
import org.fasttrackit.magazindehaine.transfer.AddProductToCartRequest;
import org.fasttrackit.magazindehaine.transfer.CartResponse;
import org.fasttrackit.magazindehaine.transfer.ProductInCartResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public CartService(CartRepository cartRepository, CustomerService customerService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Transactional
    public void addProductToCart(AddProductToCartRequest request) {
        LOGGER.info("Adding product to cart: {}", request);

        Cart cart = cartRepository.findById(request.getCustomerId())
                .orElse(new Cart());

        if (cart.getCustomer() == null) {
            LOGGER.info("New cart will be created. " +
                            "Retrieving cutomer {} to map the relationship.",
                    request.getCustomerId());

            Customer customer = customerService.getCustomer(request.getCustomerId());

            cart.setId(customer.getId());
            cart.setCustomer(customer);
        }
        Product product = productService.getProduct(request.getProductId());
        cart.addToCart(product);

        cartRepository.save(cart);
    }

    @Transactional
    public CartResponse getCart(long id) {
        LOGGER.info("Retrieving cart {}", id);

        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart " + id + "does not exist."));

        CartResponse response = new CartResponse();
        response.setId(cart.getId());

        Set<ProductInCartResponse> productsInCart = new HashSet<>();

        Iterator<Product> cartIterator = cart.getProducts().iterator();

        while (cartIterator.hasNext()) {
            Product product = cartIterator.next();

            ProductInCartResponse productResponse = new ProductInCartResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());

            productsInCart.add(productResponse);
        }

        response.setProducts(productsInCart);
        return response;
    }
}












