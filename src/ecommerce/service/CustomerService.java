package ecommerce.service;

import ecommerce.model.Address;
import ecommerce.model.Customer;
import ecommerce.repository.CustomerRepository;

import java.util.List;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {

        if (customerRepository == null) {
            throw new IllegalArgumentException(
                    "Customer repository cannot be null"
            );
        }

        this.customerRepository = customerRepository;
    }

    public void registerCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer cannot be null"
            );
        }

        if (customerRepository.existsById(customer.getId())) {
            throw new IllegalArgumentException(
                    "Customer already exists with ID: "
                            + customer.getId()
            );
        }

        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new IllegalArgumentException(
                    "Customer already exists with email: "
                            + customer.getEmail()
            );
        }

        customerRepository.save(customer);
    }

    public Customer getCustomerById(long customerId) {

        if (customerId <= 0) {
            throw new IllegalArgumentException(
                    "Customer ID must be greater than zero"
            );
        }

        return customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Customer not found with ID: "
                                        + customerId
                        )
                );
    }

    public Customer getCustomerByEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "Email cannot be null or blank"
            );
        }

        return customerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Customer not found with email: "
                                        + email
                        )
                );
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void addAddress(long customerId, Address address) {

        if (address == null) {
            throw new IllegalArgumentException(
                    "Address cannot be null"
            );
        }

        Customer customer = getCustomerById(customerId);

        customer.addAddress(address);

        customerRepository.save(customer);
    }

    public void removeAddress(long customerId, Address address) {

        if (address == null) {
            throw new IllegalArgumentException(
                    "Address cannot be null"
            );
        }

        Customer customer = getCustomerById(customerId);

        customer.removeAddress(address);

        customerRepository.save(customer);
    }
}