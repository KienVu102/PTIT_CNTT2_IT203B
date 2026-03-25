import java.sql.*;

public class TransferMoney {
    public static void main(String[] args){
        String senderId = "ACC01";
        String receiverId = "ACC02";
        double amount = 500.0;
        Connection conn = null;
        
        try {
            conn = DBContext.getConnection();
            if (conn == null) {
                System.out.println("Không thể kết nối đến database.");
                return;
            }
            
            conn.setAutoCommit(false);
            
            String checkSql = "SELECT * FROM Accounts WHERE AccountId = ?";
            PreparedStatement pstmtCheck = conn.prepareStatement(checkSql);
            pstmtCheck.setString(1, senderId);
            ResultSet rsSender = pstmtCheck.executeQuery();
            
            if (!rsSender.next()) {
                System.out.println("Tài khoản người gửi không tồn tại.");
                conn.rollback();
                return;
            }
            
            double balanceSender = rsSender.getDouble("Balance");
            if (balanceSender < amount) {
                System.out.println("Số dư không đủ. Số dư hiện tại: " + balanceSender);
                conn.rollback();
                return;
            }
            rsSender.close();
            pstmtCheck.close();
            
            pstmtCheck = conn.prepareStatement(checkSql);
            pstmtCheck.setString(1, receiverId);
            ResultSet rsReceiver = pstmtCheck.executeQuery();
            
            if (!rsReceiver.next()) {
                System.out.println("Tài khoản người nhận không tồn tại.");
                conn.rollback();
                return;
            }
            rsReceiver.close();
            pstmtCheck.close();

//            CallableStatement cstmt1 = conn.prepareCall("{call sp_UpdateBalance(?, ?)}");
//            cstmt1.setString(1, senderId);
//            cstm


            
            conn.commit();
            System.out.println("Chuyển tiền thành công!");
            
            displayAccountBalances(conn, senderId, receiverId);
            
        } catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Đã rollback transaction.");
                } catch (SQLException rollbackEx) {
                    System.out.println("Lỗi rollback: " + rollbackEx.getMessage());
                }
            }
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Đã rollback transaction.");
                } catch (SQLException rollbackEx) {
                    System.out.println("Lỗi rollback: " + rollbackEx.getMessage());
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("Lỗi đóng kết nối: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    private static void displayAccountBalances(Connection conn, String senderId, String receiverId) throws SQLException {
        System.out.println("\n=== BẢNG KẾT QUẢ ĐỐI SOÁT ===");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-15s %-20s %-15s%n", "Account ID", "Full Name", "Balance");
        System.out.println("--------------------------------------------------");
        
        String sql = "SELECT * FROM Accounts WHERE AccountId IN (?, ?) ORDER BY AccountId";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, senderId);
        pstmt.setString(2, receiverId);
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            String accountId = rs.getString("AccountId");
            String fullName = rs.getString("FullName");
            double balance = rs.getDouble("Balance");
            
            System.out.printf("%-15s %-20s %-15.2f%n", accountId, fullName, balance);
        }
        
        System.out.println("--------------------------------------------------");
        
        rs.close();
        pstmt.close();
    }
}
