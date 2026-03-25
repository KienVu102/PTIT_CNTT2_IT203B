package Ex_3;

import Database.DBContext;

import java.sql.*;

public class HospitalService {

    public void xuatVienVaThanhToan(int maBenhNhan, double tienVienPhi) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            conn.setAutoCommit(false);

            String sqlCheck = "SELECT so_du FROM Benh_Nhan WHERE id = ?";
            PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
            psCheck.setInt(1, maBenhNhan);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                double soDu = rs.getDouble("so_du");
                if (soDu < tienVienPhi) {
                    throw new Exception("Lỗi: Số dư không đủ");
                }
            } else {
                throw new Exception("Lỗi: Không tìm thấy bệnh nhân");
            }

            String sql1 = "UPDATE Benh_Nhan SET so_du = so_du - ? WHERE id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setDouble(1, tienVienPhi);
            ps1.setInt(2, maBenhNhan);
            ps1.executeUpdate();

            String sql2 = "UPDATE Giuong_Benh SET trang_thai = 'Trong' WHERE ma_bn_nam = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, maBenhNhan);
            int rowBed = ps2.executeUpdate();

            if (rowBed == 0) {
                throw new Exception("Lỗi: Không tìm thấy giường bệnh");
            }

            String sql3 = "UPDATE Benh_Nhan SET trang_thai = 'Da xuat vien' WHERE id = ?";
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setInt(1, maBenhNhan);
            int rowPatient = ps3.executeUpdate();

            if (rowPatient == 0) {
                throw new Exception("Lỗi: Cập nhật trạng thái thất bại");
            }

            conn.commit();
            System.out.println("Thành công: Đã hoàn tất thủ tục xuất viện!");

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Giao dịch thất bại: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        HospitalService service = new HospitalService();
        service.xuatVienVaThanhToan(1, 500000.0);
    }
}