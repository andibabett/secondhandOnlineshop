package org.fasttrackit.secondhandOnlineshop.persistance;


import org.fasttrackit.secondhandOnlineshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
