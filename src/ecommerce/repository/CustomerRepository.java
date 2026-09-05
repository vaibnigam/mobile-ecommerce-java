package ecommerce.repository;

import ecommerce.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    void save(Customer customer);

    Optional<Customer> findById(long customerId);

    List<Customer> findAll();

    Optional<Customer> findByEmail(String email);

    void deleteById(long customerId);

    boolean existsById(long customerId);
}