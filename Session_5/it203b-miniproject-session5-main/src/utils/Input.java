
package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input {
    private static final Scanner sc = new Scanner(System.in);

    public static String inputString(String title) {
        while (true) {
            System.out.print(title);
            String str = sc.nextLine();
            if (str.isBlank()) {
                System.out.println("Không được để trống");
            } else {
                return str;
            }
        }
    }

    public static int inputIntegerPositive(String title) {
        while (true) {
            System.out.print(title);
            try {
                int num = Integer.parseInt(sc.nextLine());
                if (num < 0) {
                    System.out.println("Vui lòng nhập số lớn hơn 0");
                } else {
                    return num;
                }
            } catch (NumberFormatException e) {
                System.out.println("Nhập không đúng định dạng hãy nhập lại");
            }

        }
    }

    public static double inputDoublePositive(String title) {
        while (true) {
            System.out.print(title);
            try {
                double num = Double.parseDouble(sc.nextLine());
                if (num < 0) {
                    System.out.println("Vui lòng nhập số lớn hơn 0");
                } else {
                    return num;
                }
            } catch (NumberFormatException e) {
                System.out.println("Nhập không đúng định dạng hãy nhập lại");
            }
        }
    }

    public static LocalDate inputDate(String title) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.print(title);
            try {
                String input = sc.nextLine();
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Sai định dạng ngày. Ví dụ đúng: 2026-03-11");
            }
        }
    }
}
