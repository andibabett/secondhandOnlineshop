package org.fasttrackit.sacondhandOnlineshop.persistance;


import org.fasttrackit.sacondhandOnlineshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
