package ecommerce;

import ecommerce.console.EcommerceConsole;
import ecommerce.loader.CustomerDataLoader;
import ecommerce.loader.DataLoader;
import ecommerce.loader.ProductDataLoader;
import ecommerce.repository.CartRepository;
import ecommerce.repository.CustomerRepository;
import ecommerce.repository.InMemoryCartRepository;
import ecommerce.repository.InMemoryCustomerRepository;
import ecommerce.repository.InMemoryOrderRepository;
import ecommerce.repository.InMemoryProductRepository;
import ecommerce.repository.OrderRepository;
import ecommerce.repository.ProductRepository;
import ecommerce.service.CartService;
import ecommerce.service.CustomerService;
import ecommerce.service.OrderService;
import ecommerce.service.ProductService;
import ecommerce.utility.IdGenerator;

public class Main {

    public static void main(String[] args) {

        ProductRepository productRepository =
                new InMemoryProductRepository();

        CustomerRepository customerRepository =
                new InMemoryCustomerRepository();

        CartRepository cartRepository =
                new InMemoryCartRepository();

        OrderRepository orderRepository =
                new InMemoryOrderRepository();


        ProductService productService =
                new ProductService(productRepository);

        CustomerService customerService =
                new CustomerService(customerRepository);

        CartService cartService =
                new CartService(
                        cartRepository,
                        customerRepository,
                        productRepository
                );

        OrderService orderService =
                new OrderService(
                        orderRepository,
                        customerRepository,
                        productRepository,
                        cartRepository
                );


        ProductDataLoader productDataLoader =
                new ProductDataLoader(productRepository);

        CustomerDataLoader customerDataLoader =
                new CustomerDataLoader(customerRepository);

        DataLoader dataLoader =
                new DataLoader(
                        productDataLoader,
                        customerDataLoader
                );


        dataLoader.loadAll();


        IdGenerator orderIdGenerator =
                new IdGenerator(1000);


        EcommerceConsole console =
                new EcommerceConsole(
                        productService,
                        customerService,
                        cartService,
                        orderService,
                        orderIdGenerator
                );

        console.start();
    }
}