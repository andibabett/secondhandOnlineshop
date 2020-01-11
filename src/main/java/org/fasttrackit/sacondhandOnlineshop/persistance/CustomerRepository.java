package org.fasttrackit.sacondhandOnlineshop.persistance;

import org.fasttrackit.sacondhandOnlineshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
