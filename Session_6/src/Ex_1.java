import java.util.LinkedList;
import java.util.Queue;

class TicketPool {
    private String roomName;
    private Queue<String> tickets = new LinkedList<>();
    private int ticketCounter;

    public TicketPool(String roomName, int initialCount) {
        this.roomName = roomName;
        this.ticketCounter = initialCount;
        for (int i = 1; i <= initialCount; i++) {
            tickets.add(roomName + "-" + String.format("%03d", i));
        }
    }

    public synchronized String getTicket() {
        return tickets.poll();
    }

    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            ticketCounter++;
            tickets.add(roomName + "-" + String.format("%03d", ticketCounter));
        }
    }

    public synchronized int getRemainingCount() {
        return tickets.size();
    }
}

class TicketSupplier implements Runnable {
    private TicketPool roomA, roomB;
    private int supplyCount, interval, rounds;

    public TicketSupplier(TicketPool a, TicketPool b, int count, int interval, int rounds) {
        this.roomA = a;
        this.roomB = b;
        this.supplyCount = count;
        this.interval = interval;
        this.rounds = rounds;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= rounds; i++) {
                Thread.sleep(interval); // Chờ 3 giây

                roomA.addTickets(supplyCount);
                System.out.println("\n>>> [Nhà cung cấp]: Đã thêm " + supplyCount + " vé vào phòng A");

                roomB.addTickets(supplyCount);
                System.out.println(">>> [Nhà cung cấp]: Đã thêm " + supplyCount + " vé vào phòng B\n");
            }
        } catch (InterruptedException e) {
            System.out.println("Nhà cung cấp tạm dừng.");
        }
    }
}

class TicketSeller implements Runnable {
    private String name;
    private TicketPool pool;
    private int soldCount = 0;

    public TicketSeller(String name, TicketPool pool) {
        this.name = name;
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true) {
            String ticket = pool.getTicket();
            if (ticket != null) {
                soldCount++;
                System.out.println(name + " đã bán vé: " + ticket);
                try { Thread.sleep(400); } catch (InterruptedException e) {}
            } else {
                try { Thread.sleep(1000); } catch (InterruptedException e) {}

                if (pool.getRemainingCount() == 0) break;
            }
        }
    }

    public int getSoldCount() { return soldCount; }
}

public class Ex_1 {
    public static void main(String[] args) {
        // Khởi tạo mỗi phòng có 5 vé ban đầu
        TicketPool poolA = new TicketPool("A", 5);
        TicketPool poolB = new TicketPool("B", 5);

        TicketSeller seller1 = new TicketSeller("Quầy 1", poolA);
        TicketSeller seller2 = new TicketSeller("Quầy 2", poolB);

        TicketSupplier supplier = new TicketSupplier(poolA, poolB, 3, 3000, 2);

        Thread t1 = new Thread(seller1);
        Thread t2 = new Thread(seller2);
        Thread tSupp = new Thread(supplier);

        t1.start();
        t2.start();
        tSupp.start();

        try {
            t1.join();
            t2.join();
            tSupp.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------------------");
        System.out.println("KẾT THÚC CHƯƠNG TRÌNH");
        System.out.println("Quầy 1 bán được: " + seller1.getSoldCount() + " vé");
        System.out.println("Quầy 2 bán được: " + seller2.getSoldCount() + " vé");
        System.out.println("Vé còn lại phòng A: " + poolA.getRemainingCount());
        System.out.println("Vé còn lại phòng B: " + poolB.getRemainingCount());
    }
}