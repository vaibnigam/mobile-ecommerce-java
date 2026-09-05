package ecommerce.repository;

import ecommerce.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<Long, Customer> customers;

    public InMemoryCustomerRepository() {
        this.customers = new HashMap<>();
    }

    @Override
    public void save(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        customers.put(customer.getId(), customer);
    }

    @Override
    public Optional<Customer> findById(long customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        if (email == null || email.isBlank()) {
            return Optional.empty();
        }

        return customers.values()
                .stream()
                .filter(customer ->
                        customer.getEmail()
                                .equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public void deleteById(long customerId) {
        customers.remove(customerId);
    }

    @Override
    public boolean existsById(long customerId) {
        return customers.containsKey(customerId);
    }
}