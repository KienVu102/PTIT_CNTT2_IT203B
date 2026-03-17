package Ex_6.Interface;

public class PercentageDiscount implements DiscountStrategy {
    private double discountPercentage;

    public PercentageDiscount(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double applyPayment(double amount) {
        double discountAmount = amount * (discountPercentage / 100);
        double finalAmount = amount - discountAmount;
        System.out.println("Applied " + discountPercentage + "% discount: -$" + discountAmount);
        System.out.println("Final amount: $" + finalAmount);
        return finalAmount;
    }
}
