package Ex_6.Interface;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double applyPayment(double amount) {
        System.out.println("No discount applied. Original amount: $" + amount);
        return amount;
    }
}
