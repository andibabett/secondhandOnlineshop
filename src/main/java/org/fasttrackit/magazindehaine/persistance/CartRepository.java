package org.fasttrackit.magazindehaine.persistance;


import org.fasttrackit.magazindehaine.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
