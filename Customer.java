public class Customer {
    private double balance;

    public Customer(String name, double balance) {
        this.balance = balance;
    }

    public void pay(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("there is no enough Customer balance.");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}