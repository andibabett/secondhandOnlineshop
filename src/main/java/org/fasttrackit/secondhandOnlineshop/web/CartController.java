package org.fasttrackit.secondhandOnlineshop.web;

import org.fasttrackit.secondhandOnlineshop.service.CartService;
import org.fasttrackit.secondhandOnlineshop.transfer.AddProductToCartRequest;
import org.fasttrackit.secondhandOnlineshop.transfer.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping
    public ResponseEntity addProductToCart(
            @RequestBody @Valid AddProductToCartRequest request) {
        cartService.addProductToCart(request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable long id) {
        CartResponse cart = cartService.getCart(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
