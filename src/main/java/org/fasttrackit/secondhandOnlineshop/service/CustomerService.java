package org.fasttrackit.secondhandOnlineshop.service;

import org.fasttrackit.secondhandOnlineshop.domain.Customer;
import org.fasttrackit.secondhandOnlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.secondhandOnlineshop.persistance.CustomerRepository;
import org.fasttrackit.secondhandOnlineshop.transfer.customer.SaveCustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(SaveCustomerRequest request) {
        LOGGER.info("Creating customer {}", request);
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());

        return customerRepository.save(customer);
    }

    public Customer getCustomer(long id) {
        LOGGER.info("Retrivering customer {}" + id);
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer " + id + "does not exist."));
    }

    public Customer updateCustomer(long id, SaveCustomerRequest request) {
        LOGGER.info("Udating customer {}: {}", id, request);

        Customer customer = getCustomer(id);

        BeanUtils.copyProperties(request, customer);

        return customerRepository.save(customer);
    }

    public void deleteCustomer(long id) {
        LOGGER.info("Deleting customer {}", id);
        customerRepository.deleteById(id);
        LOGGER.info("Deleted customer {}", id);
    }
}



