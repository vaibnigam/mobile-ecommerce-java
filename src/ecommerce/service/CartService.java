package ecommerce.service;

import ecommerce.exception.InsufficientStockException;
import ecommerce.exception.InvalidCartException;
import ecommerce.model.Cart;
import ecommerce.model.CartItem;
import ecommerce.model.Product;

public class CartService {
    private final ProductService productService;

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public void addToCart(Cart cart, long productId, int quantity) {
        validateCart(cart);

        if (quantity <= 0) {
            throw new InvalidCartException("Quantity must be greater than zero.");
        }

        Product product = productService.getProduct(productId);

        if (product.getStock() <= 0) {
            throw new InsufficientStockException(
                    product.getName() + " is out of stock.");
        }

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId() == productId) {
                int newQuantity = item.getQuantity() + quantity;
                validateStock(product, newQuantity);
                item.setQuantity(newQuantity);
                return;
            }
        }

        validateStock(product, quantity);
        cart.addItem(new CartItem(product, quantity));
    }

    public void removeFromCart(Cart cart, long productId) {
        validateCart(cart);

        boolean removed = cart.getItems().removeIf(
                item -> item.getProduct().getId() == productId);

        if (!removed) {
            throw new InvalidCartException("Product not found in cart.");
        }
    }

    public void updateQuantity(Cart cart, long productId, int quantity) {
        validateCart(cart);

        if (quantity <= 0) {
            throw new InvalidCartException("Quantity must be greater than zero.");
        }

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId() == productId) {
                validateStock(item.getProduct(), quantity);
                item.setQuantity(quantity);
                return;
            }
        }

        throw new InvalidCartException("Product not found in cart.");
    }

    public double getSubtotal(Cart cart) {
        validateCart(cart);
        return cart.getSubtotal();
    }

    public int getTotalItems(Cart cart) {
        validateCart(cart);
        return cart.getItems().stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public void clearCart(Cart cart) {
        validateCart(cart);
        cart.clear();
    }

    public boolean isEmpty(Cart cart) {
        return cart == null || cart.isEmpty();
    }

    private void validateCart(Cart cart) {
        if (cart == null) {
            throw new InvalidCartException("Cart cannot be null.");
        }
    }

    private void validateStock(Product product, int quantity) {
        if (quantity > product.getStock()) {
            throw new InsufficientStockException(
                    "Insufficient stock for " + product.getName()
                            + ". Available: " + product.getStock());
        }
    }
}
