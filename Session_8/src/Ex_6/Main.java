package Ex_6;

import Ex_6.Interface.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Create payment methods
        PaymentMethod creditCard = new CreditCardPayment("1234567890123456", "John Doe");
        PaymentMethod payPal = new PayPalPayment("john.doe@example.com");
        
        // Create discount strategies
        DiscountStrategy noDiscount = new NoDiscount();
        DiscountStrategy tenPercentDiscount = new PercentageDiscount(10);
        DiscountStrategy twentyPercentDiscount = new PercentageDiscount(20);
        
        // Create notification services
        NotificationService emailNotification = new EmailNotification("customer@example.com");
        NotificationService smsNotification = new SMSNotification("+1234567890");
        
        int choice;
        do {
            System.out.println("\n========= SMART HOME PAYMENT SYSTEM ==========");
            System.out.println("1. Test Payment Methods");
            System.out.println("2. Test Discount Strategies");
            System.out.println("3. Test Notification Services");
            System.out.println("4. Complete Transaction (All Features)");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1:
                    testPaymentMethods(sc, creditCard, payPal);
                    break;
                case 2:
                    testDiscountStrategies(sc, noDiscount, tenPercentDiscount, twentyPercentDiscount);
                    break;
                case 3:
                    testNotificationServices(sc, emailNotification, smsNotification);
                    break;
                case 4:
                    completeTransaction(sc, creditCard, payPal, noDiscount, tenPercentDiscount, emailNotification, smsNotification);
                    break;
                case 0:
                    System.out.println("Thoát chương trình");
                    sc.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;
            }
        } while (choice != 0);
    }
    
    private static void testPaymentMethods(Scanner sc, PaymentMethod creditCard, PaymentMethod payPal) {
        System.out.print("Enter payment amount: $");
        double amount = sc.nextDouble();
        sc.nextLine();
        
        System.out.println("\n--- Testing Credit Card Payment ---");
        creditCard.processPayment(amount);
        
        System.out.println("\n--- Testing PayPal Payment ---");
        payPal.processPayment(amount);
    }
    
    private static void testDiscountStrategies(Scanner sc, DiscountStrategy noDiscount, 
                                            DiscountStrategy tenPercent, DiscountStrategy twentyPercent) {
        System.out.print("Enter original amount: $");
        double amount = sc.nextDouble();
        sc.nextLine();
        
        System.out.println("\n--- Testing No Discount ---");
        noDiscount.applyPayment(amount);
        
        System.out.println("\n--- Testing 10% Discount ---");
        tenPercent.applyPayment(amount);
        
        System.out.println("\n--- Testing 20% Discount ---");
        twentyPercent.applyPayment(amount);
    }
    
    private static void testNotificationServices(Scanner sc, NotificationService email, NotificationService sms) {
        System.out.print("Enter notification message: ");
        String message = sc.nextLine();
        
        System.out.println("\n--- Testing Email Notification ---");
        email.sendNotice(message);
        
        System.out.println("\n--- Testing SMS Notification ---");
        sms.sendNotice(message);
    }
    
    private static void completeTransaction(Scanner sc, PaymentMethod creditCard, PaymentMethod payPal,
                                         DiscountStrategy noDiscount, DiscountStrategy tenPercent,
                                         NotificationService email, NotificationService sms) {
        System.out.print("Enter transaction amount: $");
        double amount = sc.nextDouble();
        sc.nextLine();
        
        System.out.println("\nSelect payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.print("Choice: ");
        int paymentChoice = sc.nextInt();
        sc.nextLine();
        
        System.out.println("\nSelect discount:");
        System.out.println("1. No Discount");
        System.out.println("2. 10% Discount");
        System.out.print("Choice: ");
        int discountChoice = sc.nextInt();
        sc.nextLine();
        
        System.out.println("\nSelect notification method:");
        System.out.println("1. Email");
        System.out.println("2. SMS");
        System.out.println("3. Both");
        System.out.print("Choice: ");
        int notificationChoice = sc.nextInt();
        sc.nextLine();
        
        // Apply discount
        DiscountStrategy selectedDiscount = (discountChoice == 2) ? tenPercent : noDiscount;
        double finalAmount = selectedDiscount.applyPayment(amount);
        
        // Process payment
        PaymentMethod selectedPayment = (paymentChoice == 2) ? payPal : creditCard;
        selectedPayment.processPayment(finalAmount);
        
        // Send notification
        String notificationMessage = "Payment of $" + finalAmount + " processed successfully!";
        
        switch (notificationChoice) {
            case 1:
                email.sendNotice(notificationMessage);
                break;
            case 2:
                sms.sendNotice(notificationMessage);
                break;
            case 3:
                email.sendNotice(notificationMessage);
                sms.sendNotice(notificationMessage);
                break;
        }
        
        System.out.println("\nTransaction completed successfully!");
    }
}
