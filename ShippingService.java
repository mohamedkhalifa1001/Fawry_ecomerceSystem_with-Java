import java.util.*;

public class ShippingService {
    public static void shipItems(List<Shippable> items, Map<Product, Integer> cartItems) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (Shippable item : items) {
            int count = cartItems.get((Product)item);
            double weight = item.getWeight() * count;
            totalWeight += weight;
            System.out.printf("%dx %-12s %.0fg\n", count, item.getName(), weight * 1000);
        }
        System.out.printf("Total package weight %.1fkg\n\n", totalWeight);
    }
}