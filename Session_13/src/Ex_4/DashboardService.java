package Ex_4;

import Database.DBContext;

import java.sql.*;
import java.util.*;

public class DashboardService {

    public List<BenhNhanDTO> getDashboardData() {
        Map<Integer, BenhNhanDTO> patientMap = new LinkedHashMap<>();

        String sql = "SELECT b.id, b.ho_ten, d.id as dv_id, d.ten_dich_vu " +
                "FROM BenhNhan b " +
                "LEFT JOIN DichVuSuDung d ON b.id = d.ma_benh_nhan";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int patientId = rs.getInt("id");

                BenhNhanDTO dto = patientMap.get(patientId);
                if (dto == null) {
                    dto = new BenhNhanDTO();
                    dto.setId(patientId);
                    dto.setHoTen(rs.getString("ho_ten"));
                    dto.setDsDichVu(new ArrayList<>());
                    patientMap.put(patientId, dto);
                }

                int serviceId = rs.getInt("dv_id");
                if (!rs.wasNull()) {
                    DichVuDTO dv = new DichVuDTO();
                    dv.setId(serviceId);
                    dv.setTenDichVu(rs.getString("ten_dich_vu"));
                    dto.getDsDichVu().add(dv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(patientMap.values());
    }

    public static void main(String[] args) {
        DashboardService service = new DashboardService();
        List<BenhNhanDTO> list = service.getDashboardData();
        for (BenhNhanDTO b : list) {
            System.out.println("Bệnh nhân: " + b.getHoTen() + " - Dịch vụ: " + b.getDsDichVu().size());
        }
    }
}