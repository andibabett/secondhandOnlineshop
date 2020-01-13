package org.fasttrackit.secondhandOnlineshop.web;

import org.fasttrackit.secondhandOnlineshop.service.CartService;
import org.fasttrackit.secondhandOnlineshop.transfer.cart.AddProductToCartRequest;
import org.fasttrackit.secondhandOnlineshop.transfer.cart.CartResponse;
import org.fasttrackit.secondhandOnlineshop.transfer.cart.UpdateCartRequest;
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

    @PutMapping("/{id}")
    public ResponseEntity updateCart(@PathVariable("id") long id,
                                     @RequestBody @Valid UpdateCartRequest request) {
        cartService.updateCart(id, request);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable long id) {
        CartResponse cart = cartService.getCart(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{itemId}")
    public ResponseEntity removeProductFromCart(@PathVariable("id") long id, @PathVariable("itemId") long itemId) {
        cartService.deleteProductFromCart(id, itemId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCart(@PathVariable("id") long id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
