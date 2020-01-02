package org.fasttrackit.magazindehaine.persistance;

import org.fasttrackit.magazindehaine.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
