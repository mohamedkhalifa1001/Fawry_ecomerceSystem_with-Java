public class Cheese extends Product implements Expirable, Shippable {
    private boolean expired;
    private double weight;

    public Cheese(String name, double price, int quantity, double weight, boolean expired) {
        super(name, price, quantity);
        this.weight = weight;
        this.expired = expired;
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}