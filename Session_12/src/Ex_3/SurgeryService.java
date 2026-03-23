package Ex_3;

import Data.DBContext;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class SurgeryService {

    public void getSurgeryFee(int id) {
        String sql = "{call GET_SURGERY_FEE(?, ?)}";

        try (Connection conn = DBContext.getConnection()) {
            if (conn == null) return;

            try (CallableStatement cstmt = conn.prepareCall(sql)) {
                // 1. Tham số IN (vị trí số 1)
                cstmt.setInt(1, id);

                // 2. Đăng ký tham số OUT (vị trí số 2) - Bước quan trọng nhất
                cstmt.registerOutParameter(2, Types.DECIMAL);

                // 3. Thực thi
                cstmt.execute();

                // 4. Lấy kết quả từ tham số OUT
                double cost = cstmt.getDouble(2);

                System.out.println("ID phau thuat: " + id);
                System.out.println("Tong chi phi: " + cost + " USD");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SurgeryService().getSurgeryFee(505);
    }
}