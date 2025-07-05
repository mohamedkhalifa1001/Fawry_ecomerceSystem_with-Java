import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Products
        Cheese cheese = new Cheese("Cheese", 100, 5, 0.2, false);
        TV tv = new TV("TV", 300, 3, 7.0);
        MobileScratchCard scratch = new MobileScratchCard("ScratchCard", 50, 10);

        // Customer
        Customer customer = new Customer("mohamed",1000);

        // Cart
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(tv, 1);
        cart.add(scratch, 1);

        // Checkout
        checkout(customer, cart);
    }

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart is empty!");
        }

        double subtotal = 0;
        List<Shippable> shippableItems = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();

            if (p instanceof Expirable && ((Expirable) p).isExpired()) {
                throw new RuntimeException(p.getName() + " is expired.");
            }

            if (qty > p.getQuantity()) {
                throw new RuntimeException(p.getName() + " is out of stock.");
            }

            subtotal += p.getPrice() * qty;
            p.reduceQuantity(qty);

            if (p instanceof Shippable) {
                for (int i = 0; i < qty; i++) {
                    shippableItems.add((Shippable) p);
                }
            }
        }

        double shipping = shippableItems.isEmpty() ? 0 : 30;
        double total = subtotal + shipping;

        if (customer.getBalance() < total) {
            throw new RuntimeException("Insufficient balance!");
        }

        if (!shippableItems.isEmpty()) {
            ShippingService.shipItems(shippableItems, cart.getItems());
        }

        customer.pay(total);

        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            System.out.printf("%dx %-12s %.0f\n", entry.getValue(), entry.getKey().getName(), entry.getKey().getPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f\n", subtotal);
        System.out.printf("Shipping         %.0f\n", shipping);
        System.out.printf("Amount           %.0f\n", total);
        System.out.printf("Balance left     %.0f\n", customer.getBalance());
    }
}