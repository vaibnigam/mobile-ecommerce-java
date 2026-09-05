package ecommerce.loader;

import ecommerce.model.Address;
import ecommerce.model.Customer;
import ecommerce.repository.CustomerRepository;

public class CustomerDataLoader {

    private final CustomerRepository customerRepository;

    public CustomerDataLoader(CustomerRepository customerRepository) {

        if (customerRepository == null) {
            throw new IllegalArgumentException(
                    "Customer repository cannot be null"
            );
        }

        this.customerRepository = customerRepository;
    }

    public void load() {

        Customer customer1 = new Customer(
                1L,
                "Rahul Sharma",
                "rahul.sharma@example.com",
                "9876543210"
        );

        customer1.addAddress(
                new Address(
                        "12 MG Road",
                        "Indore",
                        "Madhya Pradesh",
                        "452001",
                        "India"
                )
        );

        customerRepository.save(customer1);

        Customer customer2 = new Customer(
                2L,
                "Priya Verma",
                "priya.verma@example.com",
                "9876543211"
        );

        customer2.addAddress(
                new Address(
                        "45 Park Street",
                        "Kolkata",
                        "West Bengal",
                        "700016",
                        "India"
                )
        );

        customerRepository.save(customer2);

        Customer customer3 = new Customer(
                3L,
                "Amit Singh",
                "amit.singh@example.com",
                "9876543212"
        );

        customer3.addAddress(
                new Address(
                        "78 Civil Lines",
                        "Delhi",
                        "Delhi",
                        "110054",
                        "India"
                )
        );

        customerRepository.save(customer3);
    }
}