package ecommerce.service;

import ecommerce.model.Cart;
import ecommerce.model.Product;
import ecommerce.repository.CartRepository;
import ecommerce.repository.CustomerRepository;
import ecommerce.repository.ProductRepository;

import java.util.List;

public class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public CartService(
            CartRepository cartRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository) {

        if (cartRepository == null) {
            throw new IllegalArgumentException(
                    "Cart repository cannot be null"
            );
        }

        if (customerRepository == null) {
            throw new IllegalArgumentException(
                    "Customer repository cannot be null"
            );
        }

        if (productRepository == null) {
            throw new IllegalArgumentException(
                    "Product repository cannot be null"
            );
        }

        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public Cart getCart(long customerId) {

        validateCustomer(customerId);

        return cartRepository.findByCustomerId(customerId)
                .orElseGet(() -> {

                    Cart cart = new Cart(customerId);

                    cartRepository.save(cart);

                    return cart;
                });
    }

    public void addToCart(
            long customerId,
            long productId,
            int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero"
            );
        }

        validateCustomer(customerId);

        Product product = getProduct(productId);

        if (!product.isActive()) {
            throw new IllegalStateException(
                    "Product is not active"
            );
        }

        if (product.getStockQuantity() < quantity) {
            throw new IllegalStateException(
                    "Insufficient stock for product: "
                            + product.getName()
            );
        }

        Cart cart = getCart(customerId);

        cart.addItem(product, quantity);

        cartRepository.save(cart);
    }

    public void removeFromCart(
            long customerId,
            long productId) {

        validateCustomer(customerId);

        Cart cart = getExistingCart(customerId);

        cart.removeItem(productId);

        cartRepository.save(cart);
    }

    public Cart getExistingCart(long customerId) {

        validateCustomer(customerId);

        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Cart not found for customer: "
                                        + customerId
                        )
                );
    }

    public void clearCart(long customerId) {

        validateCustomer(customerId);

        Cart cart = getExistingCart(customerId);

        cart.clear();

        cartRepository.save(cart);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    private void validateCustomer(long customerId) {

        if (customerId <= 0) {
            throw new IllegalArgumentException(
                    "Customer ID must be greater than zero"
            );
        }

        if (!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found with ID: "
                            + customerId
            );
        }
    }

    private Product getProduct(long productId) {

        if (productId <= 0) {
            throw new IllegalArgumentException(
                    "Product ID must be greater than zero"
            );
        }

        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product not found with ID: "
                                        + productId
                        )
                );
    }
}