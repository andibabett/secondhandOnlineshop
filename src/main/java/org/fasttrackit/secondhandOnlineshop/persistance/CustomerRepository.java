package org.fasttrackit.secondhandOnlineshop.persistance;

import org.fasttrackit.secondhandOnlineshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
