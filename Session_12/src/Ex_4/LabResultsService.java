package Ex_4;

import Data.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class LabResultsService {

    public void insertBatchResults(List<String> resultList) {
        String sql = "INSERT INTO Results(data) VALUES (?)";

        try (Connection conn = DBContext.getConnection()) {
            if (conn == null) return;

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                for (String data : resultList) {
                    pstmt.setString(1, data);

                    pstmt.executeUpdate();
                }
                System.out.println("Da nap thanh cong " + resultList.size() + " ket qua.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> mockData = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            mockData.add("Ket qua xet nghiem mau so " + i);
        }

        long startTime = System.currentTimeMillis();

        new LabResultsService().insertBatchResults(mockData);

        long endTime = System.currentTimeMillis();
        System.out.println("Thoi gian thuc thi: " + (endTime - startTime) + " ms");
    }
}