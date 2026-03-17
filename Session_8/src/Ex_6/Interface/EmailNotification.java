package Ex_6.Interface;

public class EmailNotification implements NotificationService {
    private String emailAddress;

    public EmailNotification(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void sendNotice(String message) {
        System.out.println("Sending email notification to: " + emailAddress);
        System.out.println("Message: " + message);
        System.out.println("Email sent successfully!");
    }
}
