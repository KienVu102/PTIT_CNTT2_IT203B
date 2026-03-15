import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 1. Lớp Ticket theo yêu cầu K1
class Ticket {
    String ticketId;
    String roomName;
    boolean isSold;

    public Ticket(String ticketId, String roomName) {
        this.ticketId = ticketId;
        this.roomName = roomName;
        this.isSold = false;
    }
}

// 2. Lớp TicketPool quản lý danh sách Ticket
class TicketPool1 {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();
    private int ticketCounter = 0;

    public TicketPool1(String roomName, int initialCount) {
        this.roomName = roomName;
        addTickets(initialCount);
    }

    // Phương thức thêm vé (Dùng cho cả khởi tạo và Supplier)
    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            ticketCounter++;
            String id = roomName + "-" + String.format("%03d", ticketCounter);
            tickets.add(new Ticket(id, roomName));
        }
    }

    // Phương thức sellTicket theo yêu cầu K1
    public synchronized Ticket sellTicket() {
        for (Ticket t : tickets) {
            if (!t.isSold) {
                t.isSold = true;
                // Xóa khỏi list sau khi bán để dễ quản lý số lượng còn lại
                tickets.remove(t);
                return t;
            }
        }
        return null;
    }

    public synchronized int getRemainingCount() {
        return tickets.size();
    }

    public String getRoomName() { return roomName; }
}

// 3. Lớp TicketSupplier (Mới cho K2)
class TicketSupplier2 implements Runnable {
    private TicketPool1 roomA, roomB;
    private int supplyCount, interval, rounds;

    public TicketSupplier2(TicketPool1 a, TicketPool1 b, int count, int interval, int rounds) {
        this.roomA = a;
        this.roomB = b;
        this.supplyCount = count;
        this.interval = interval;
        this.rounds = rounds;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < rounds; i++) {
                Thread.sleep(interval);
                roomA.addTickets(supplyCount);
                roomB.addTickets(supplyCount);
                System.out.println("\n[Nhà cung cấp]: Đã thêm " + supplyCount + " vé vào mỗi phòng\n");
            }
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}

// 4. Lớp BookingCounter (Kế thừa K1, chạy Runnable)
class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool1 roomA, roomB;
    private int soldCount = 0;
    private Random random = new Random();

    public BookingCounter(String name, TicketPool1 a, TicketPool1 b) {
        this.counterName = name;
        this.roomA = a;
        this.roomB = b;
    }

    @Override
    public void run() {
        while (true) {
            // Chọn ngẫu nhiên phòng A hoặc B để bán
            TicketPool1 targetPool = random.nextBoolean() ? roomA : roomB;
            Ticket ticket = targetPool.sellTicket();

            // Nếu phòng ngẫu nhiên hết vé, thử phòng còn lại
            if (ticket == null) {
                targetPool = (targetPool == roomA) ? roomB : roomA;
                ticket = targetPool.sellTicket();
            }

            if (ticket != null) {
                soldCount++;
                System.out.println(counterName + " đã bán vé " + ticket.ticketId);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            } else {
                // Tạm nghỉ đợi nhà cung cấp, nếu vẫn hết vé thì mới nghỉ hẳn
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
                if (roomA.getRemainingCount() == 0 && roomB.getRemainingCount() == 0) break;
            }
        }
    }

    public int getSoldCount() { return soldCount; }
}

// 5. Hàm Main thực thi
public class Ex_2 {
    public static void main(String[] args) throws InterruptedException {
        TicketPool1 poolA = new TicketPool1("A", 10);
        TicketPool1 poolB = new TicketPool1("B", 10);

        BookingCounter q1 = new BookingCounter("Quầy 1", poolA, poolB);
        BookingCounter q2 = new BookingCounter("Quầy 2", poolA, poolB);
        TicketSupplier2 supplier = new TicketSupplier2(poolA, poolB, 3, 3000, 2);

        Thread t1 = new Thread(q1);
        Thread t2 = new Thread(q2);
        Thread tS = new Thread(supplier);

        t1.start();
        t2.start();
        tS.start();

        t1.join();
        t2.join();
        tS.join();

        System.out.println("---------------------------------");
        System.out.println("Quầy 1 bán được: " + q1.getSoldCount() + " vé");
        System.out.println("Quầy 2 bán được: " + q2.getSoldCount() + " vé");
        System.out.println("Vé còn lại phòng A: " + poolA.getRemainingCount());
        System.out.println("Vé còn lại phòng B: " + poolB.getRemainingCount());
    }
}