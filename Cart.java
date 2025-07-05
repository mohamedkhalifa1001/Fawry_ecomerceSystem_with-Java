import java.util.*;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();

    public void add(Product p, int quantity) {
        if (quantity > p.getQuantity()) {
            throw new IllegalArgumentException("Quantity exceeds stock.");
        }
        items.put(p, items.getOrDefault(p, 0) + quantity);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}