package ecommerce.console;

import ecommerce.enums.OrderStatus;
import ecommerce.enums.PaymentMethod;
import ecommerce.enums.PaymentStatus;
import ecommerce.model.Address;
import ecommerce.model.Cart;
import ecommerce.model.Customer;
import ecommerce.model.Order;
import ecommerce.model.Product;
import ecommerce.service.CartService;
import ecommerce.service.CustomerService;
import ecommerce.service.OrderService;
import ecommerce.service.ProductService;
import ecommerce.utility.IdGenerator;

import java.util.List;
import java.util.Scanner;

public class EcommerceConsole {

    private final ProductService productService;
    private final CustomerService customerService;
    private final CartService cartService;
    private final OrderService orderService;
    private final IdGenerator orderIdGenerator;

    private final Scanner scanner;

    private boolean running;
    private long currentCustomerId;

    public EcommerceConsole(
            ProductService productService,
            CustomerService customerService,
            CartService cartService,
            OrderService orderService,
            IdGenerator orderIdGenerator) {

        if (productService == null) {
            throw new IllegalArgumentException(
                    "Product service cannot be null"
            );
        }

        if (customerService == null) {
            throw new IllegalArgumentException(
                    "Customer service cannot be null"
            );
        }

        if (cartService == null) {
            throw new IllegalArgumentException(
                    "Cart service cannot be null"
            );
        }

        if (orderService == null) {
            throw new IllegalArgumentException(
                    "Order service cannot be null"
            );
        }

        if (orderIdGenerator == null) {
            throw new IllegalArgumentException(
                    "Order ID generator cannot be null"
            );
        }

        this.productService = productService;
        this.customerService = customerService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.orderIdGenerator = orderIdGenerator;

        this.scanner = new Scanner(System.in);

        this.running = true;
        this.currentCustomerId = 1L;
    }

    public void start() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("       MOBILE E-COMMERCE SYSTEM");
        System.out.println("========================================");

        System.out.println();
        System.out.println(
                "Logged in as customer ID: "
                        + currentCustomerId
        );

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            System.out.println();

            try {

                switch (choice) {

                    case 1 -> viewAllProducts();

                    case 2 -> searchProducts();

                    case 3 -> viewProductDetails();

                    case 4 -> viewCustomer();

                    case 5 -> addAddress();

                    case 6 -> viewCart();

                    case 7 -> addProductToCart();

                    case 8 -> removeProductFromCart();

                    case 9 -> placeOrder();

                    case 10 -> viewOrders();

                    case 11 -> updateOrderStatus();

                    case 12 -> updatePaymentStatus();

                    case 0 -> exit();

                    default ->
                            System.out.println(
                                    "Invalid choice. Please try again."
                            );
                }

            } catch (RuntimeException exception) {

                System.out.println();
                System.out.println(
                        "Operation failed: "
                                + exception.getMessage()
                );
            }

            System.out.println();
        }

        scanner.close();
    }

    private void displayMenu() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("              MAIN MENU");
        System.out.println("========================================");

        System.out.println("1. View All Products");
        System.out.println("2. Search Products");
        System.out.println("3. View Product Details");

        System.out.println();

        System.out.println("4. View Customer");
        System.out.println("5. Add Address");

        System.out.println();

        System.out.println("6. View Cart");
        System.out.println("7. Add Product To Cart");
        System.out.println("8. Remove Product From Cart");

        System.out.println();

        System.out.println("9. Place Order");
        System.out.println("10. View My Orders");

        System.out.println();

        System.out.println("11. Update Order Status");
        System.out.println("12. Update Payment Status");

        System.out.println();

        System.out.println("0. Exit");

        System.out.println("========================================");
    }

    private void viewAllProducts() {

        List<Product> products =
                productService.getAllProducts();

        System.out.println("Total products: " + products.size());

        System.out.println();

        for (Product product : products) {

            System.out.println(
                    "ID: " + product.getId()
                            + " | "
                            + product.getBrand()
                            + " "
                            + product.getName()
                            + " | ₹"
                            + product.getPrice()
                            + " | Stock: "
                            + product.getStockQuantity()
                            + " | "
                            + product.getCategory()
                            + " | "
                            + (product.isActive()
                            ? "ACTIVE"
                            : "INACTIVE")
            );
        }
    }

    private void searchProducts() {

        System.out.println("1. Search by name");
        System.out.println("2. Search by brand");

        int choice =
                readInt("Enter search type: ");

        String searchTerm =
                readString("Enter search term: ");

        List<Product> products;

        if (choice == 1) {

            products =
                    productService.searchByName(searchTerm);

        } else if (choice == 2) {

            products =
                    productService.searchByBrand(searchTerm);

        } else {

            System.out.println(
                    "Invalid search type."
            );

            return;
        }

        System.out.println();
        System.out.println(
                "Products found: "
                        + products.size()
        );

        for (Product product : products) {

            System.out.println(
                    product.getId()
                            + " - "
                            + product.getBrand()
                            + " "
                            + product.getName()
                            + " - ₹"
                            + product.getPrice()
            );
        }
    }

    private void viewProductDetails() {

        long productId =
                readLong("Enter product ID: ");

        Product product =
                productService.getProductById(productId);

        System.out.println();
        System.out.println("Product Details");
        System.out.println("----------------------------------------");

        System.out.println("ID: " + product.getId());
        System.out.println("Brand: " + product.getBrand());
        System.out.println("Name: " + product.getName());
        System.out.println(
                "Description: "
                        + product.getDescription()
        );
        System.out.println("Category: " + product.getCategory());
        System.out.println("Price: ₹" + product.getPrice());
        System.out.println(
                "Stock: "
                        + product.getStockQuantity()
        );
        System.out.println(
                "Status: "
                        + (product.isActive()
                        ? "ACTIVE"
                        : "INACTIVE")
        );
    }

    private void viewCustomer() {

        Customer customer =
                customerService.getCustomerById(
                        currentCustomerId
                );

        System.out.println(customer);

        System.out.println();
        System.out.println("Addresses:");

        if (customer.getAddresses().isEmpty()) {

            System.out.println("No addresses saved.");

        } else {

            for (int i = 0;
                 i < customer.getAddresses().size();
                 i++) {

                System.out.println(
                        (i + 1)
                                + ". "
                                + customer.getAddresses().get(i)
                );
            }
        }
    }

    private void addAddress() {

        String addressLine =
                readString("Address line: ");

        String city =
                readString("City: ");

        String state =
                readString("State: ");

        String postalCode =
                readString("Postal code: ");

        String country =
                readString("Country: ");

        Address address =
                new Address(
                        addressLine,
                        city,
                        state,
                        postalCode,
                        country
                );

        customerService.addAddress(
                currentCustomerId,
                address
        );

        System.out.println(
                "Address added successfully."
        );
    }

    private void viewCart() {

        Cart cart =
                cartService.getCart(
                        currentCustomerId
                );

        System.out.println("Cart");

        System.out.println("----------------------------------------");

        if (cart.isEmpty()) {

            System.out.println("Cart is empty.");

            return;
        }

        cart.getItems()
                .forEach(item ->
                        System.out.println(
                                item.getProduct().getName()
                                        + " | Quantity: "
                                        + item.getQuantity()
                                        + " | Subtotal: ₹"
                                        + item.getSubtotal()
                        )
                );

        System.out.println("----------------------------------------");

        System.out.println(
                "Total: ₹"
                        + cart.getTotalAmount()
        );
    }

    private void addProductToCart() {

        long productId =
                readLong("Enter product ID: ");

        int quantity =
                readInt("Enter quantity: ");

        cartService.addToCart(
                currentCustomerId,
                productId,
                quantity
        );

        System.out.println(
                "Product added to cart successfully."
        );
    }

    private void removeProductFromCart() {

        long productId =
                readLong("Enter product ID: ");

        cartService.removeFromCart(
                currentCustomerId,
                productId
        );

        System.out.println(
                "Product removed from cart."
        );
    }

    private void placeOrder() {

        Cart cart =
                cartService.getExistingCart(
                        currentCustomerId
                );

        if (cart.isEmpty()) {

            System.out.println(
                    "Cart is empty. Add products before checkout."
            );

            return;
        }

        Customer customer =
                customerService.getCustomerById(
                        currentCustomerId
                );

        if (customer.getAddresses().isEmpty()) {

            System.out.println(
                    "You need a shipping address before placing an order."
            );

            return;
        }

        System.out.println("Select shipping address:");

        List<Address> addresses =
                customer.getAddresses();

        for (int i = 0;
             i < addresses.size();
             i++) {

            System.out.println(
                    (i + 1)
                            + ". "
                            + addresses.get(i)
            );
        }

        int addressChoice =
                readInt("Enter address number: ");

        if (addressChoice < 1
                || addressChoice > addresses.size()) {

            System.out.println(
                    "Invalid address selection."
            );

            return;
        }

        Address shippingAddress =
                addresses.get(addressChoice - 1);

        PaymentMethod paymentMethod =
                selectPaymentMethod();

        if (paymentMethod == null) {
            return;
        }

        long orderId =
                orderIdGenerator.nextId();

        Order order =
                orderService.placeOrder(
                        orderId,
                        currentCustomerId,
                        shippingAddress,
                        paymentMethod
                );

        System.out.println();
        System.out.println(
                "Order placed successfully!"
        );

        System.out.println(
                "Order ID: "
                        + order.getId()
        );

        System.out.println(
                "Total: ₹"
                        + order.getTotalAmount()
        );

        System.out.println(
                "Payment method: "
                        + order.getPaymentMethod()
        );

        System.out.println(
                "Order status: "
                        + order.getStatus()
        );

        System.out.println(
                "Payment status: "
                        + order.getPaymentStatus()
        );
    }

    private PaymentMethod selectPaymentMethod() {

        System.out.println();
        System.out.println("Payment Methods");

        PaymentMethod[] methods =
                PaymentMethod.values();

        for (int i = 0; i < methods.length; i++) {

            System.out.println(
                    (i + 1)
                            + ". "
                            + methods[i]
            );
        }

        int choice =
                readInt("Select payment method: ");

        if (choice < 1
                || choice > methods.length) {

            System.out.println(
                    "Invalid payment method."
            );

            return null;
        }

        return methods[choice - 1];
    }

    private void viewOrders() {

        List<Order> orders =
                orderService.getOrdersByCustomer(
                        currentCustomerId
                );

        if (orders.isEmpty()) {

            System.out.println(
                    "No orders found."
            );

            return;
        }

        System.out.println("My Orders");

        System.out.println("----------------------------------------");

        for (Order order : orders) {

            System.out.println(
                    "Order ID: "
                            + order.getId()
                            + " | Total: ₹"
                            + order.getTotalAmount()
                            + " | Status: "
                            + order.getStatus()
                            + " | Payment: "
                            + order.getPaymentStatus()
            );
        }
    }

    private void updateOrderStatus() {

        long orderId =
                readLong("Enter order ID: ");

        System.out.println("Available statuses:");

        OrderStatus[] statuses =
                OrderStatus.values();

        for (int i = 0; i < statuses.length; i++) {

            System.out.println(
                    (i + 1)
                            + ". "
                            + statuses[i]
            );
        }

        int choice =
                readInt("Select new status: ");

        if (choice < 1
                || choice > statuses.length) {

            System.out.println(
                    "Invalid status."
            );

            return;
        }

        OrderStatus newStatus =
                statuses[choice - 1];

        orderService.updateOrderStatus(
                orderId,
                newStatus
        );

        System.out.println(
                "Order status updated to "
                        + newStatus
        );
    }

    private void updatePaymentStatus() {

        long orderId =
                readLong("Enter order ID: ");

        System.out.println("Available payment statuses:");

        PaymentStatus[] statuses =
                PaymentStatus.values();

        for (int i = 0; i < statuses.length; i++) {

            System.out.println(
                    (i + 1)
                            + ". "
                            + statuses[i]
            );
        }

        int choice =
                readInt("Select payment status: ");

        if (choice < 1
                || choice > statuses.length) {

            System.out.println(
                    "Invalid payment status."
            );

            return;
        }

        PaymentStatus paymentStatus =
                statuses[choice - 1];

        orderService.updatePaymentStatus(
                orderId,
                paymentStatus
        );

        System.out.println(
                "Payment status updated to "
                        + paymentStatus
        );
    }

    private void exit() {

        System.out.println();
        System.out.println(
                "Thank you for using Mobile E-Commerce System."
        );

        running = false;
    }

    private String readString(String message) {

        System.out.print(message);

        return scanner.nextLine().trim();
    }

    private int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException exception) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    private long readLong(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Long.parseLong(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException exception) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }
}