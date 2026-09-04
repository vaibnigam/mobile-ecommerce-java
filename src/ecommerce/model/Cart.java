package ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private long userId;
    private List<CartItem> items;

    public Cart(long userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
    }

    public long getUserId() {
        return userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public double getSubtotal() {
        return items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}