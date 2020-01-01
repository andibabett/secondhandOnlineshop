package org.fasttrackit.magazindehaine.persistance;


import org.fasttrackit.magazindehaine.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContaining(String prtialName, Pageable pageable);

    Page<Product> findByNameContainingAndQuantityGreaterThanEqual(
            String partialName, int minQuantity, Pageable pageable);

    @Query(value = "SELECT * FROM product WHERE `name` LIKE '%?0%'",
            nativeQuery = true)
    Page<Product> findByPartialName(String partialName, Pageable pageable);
}

