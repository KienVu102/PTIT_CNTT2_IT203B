package Ex_6.Interface;

public class SMSNotification implements NotificationService {
    private String phoneNumber;

    public SMSNotification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void sendNotice(String message) {
        System.out.println("Sending SMS notification to: " + phoneNumber);
        System.out.println("Message: " + message);
        System.out.println("SMS sent successfully!");
    }
}
