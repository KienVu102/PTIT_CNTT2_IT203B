class TicketBooth implements Runnable {
    private static int availableTickets = 10;
    private String boothName;

    public TicketBooth(String name) {
        this.boothName = name;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (TicketBooth.class) {
                if (availableTickets > 0) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    availableTickets--;
                    System.out.println(boothName + " đã bán 1 vé. Số vé còn lại: " + availableTickets);
                } else {
                    System.out.println("\n");
                    System.out.println(boothName + " thông báo: Đã hết vé!");
                    break;
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BTTH {
    public static void main(String[] args) {
        System.out.println("--- HỆ THỐNG BÁN VÉ TÀU TẾT BẮT ĐẦU ---");

        Thread quay1 = new Thread(new TicketBooth("Quầy 1"));
        Thread quay2 = new Thread(new TicketBooth("Quầy 2"));
        Thread quay3 = new Thread(new TicketBooth("Quầy 3"));

        quay1.start();
        quay2.start();
        quay3.start();
    }
}