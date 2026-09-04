package ecommerce.service;

import ecommerce.model.Cart;
import ecommerce.model.CartItem;
import ecommerce.model.Product;

public class CartService {

    private ProductService productService;

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public void addToCart(Cart cart, long productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        Product product = productService.getProduct(productId);

        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Insufficient stock.");
        }

        for (CartItem item : cart.getItems()) {

            if (item.getProduct().getId() == productId) {

                int newQuantity = item.getQuantity() + quantity;

                if (newQuantity > product.getStock()) {
                    throw new IllegalArgumentException("Insufficient stock.");
                }

                item.setQuantity(newQuantity);
                return;
            }
        }

        cart.addItem(new CartItem(product, quantity));
    }

    public void removeFromCart(Cart cart, long productId) {

        cart.getItems().removeIf(
                item -> item.getProduct().getId() == productId
        );
    }

    public void updateQuantity(Cart cart, long productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        for (CartItem item : cart.getItems()) {

            if (item.getProduct().getId() == productId) {

                if (quantity > item.getProduct().getStock()) {
                    throw new IllegalArgumentException("Insufficient stock.");
                }

                item.setQuantity(quantity);
                return;
            }
        }

        throw new IllegalArgumentException("Product not found in cart.");
    }

    public double getSubtotal(Cart cart) {
        return cart.getSubtotal();
    }

    public void clearCart(Cart cart) {
        cart.clear();
    }
}