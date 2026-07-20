package ui;
import java.util.Scanner;

public class Input {

    public static String inputName(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.matches("^[\\p{L}\\s]+$")) {
                return input;
            }
            System.out.println("-> Tên không hợp lệ (chỉ nhập chữ cái). Thử lại!");
        }
    }

    public static int inputAge(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int age = Integer.parseInt(sc.nextLine().trim());
                if (age > 0) return age;
                System.out.println("Tuổi phải lớn hơn 0!");
            } catch (NumberFormatException e) {
                System.out.println("Sai định dạng số, nhập lại đi bạn!");
            }
        }
    }

    public static int inputPositiveInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int num = Integer.parseInt(sc.nextLine().trim());
                if (num > 0) return num;
                System.out.println("-> Giá trị phải > 0!");
            } catch (NumberFormatException e) {
                System.out.println("-> Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }

    public static String inputCode(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (!input.isEmpty() && !input.contains(",")) {
                return input;
            }
            System.out.println("Mã không được để trống hoặc chứa dấu phẩy ','.");
        }
    }

    public static String inputText(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (!input.isEmpty() && !input.contains(",")) {
                return input;
            }
            System.out.println("Nội dung không hợp lệ (trống hoặc có dấu phẩy).");
        }
    }

    public static String inputLine(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    public static double inputPositiveDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value > 0) return value;
                System.out.println("Giá trị phải lớn hơn 0");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi định dạng số thập phân!");
            }
        }
    }

    public static int inputByRange(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int num = Integer.parseInt(sc.nextLine().trim());
                if (num >= min && num <= max) return num;
                System.out.println("Chỉ được chọn từ " + min + " đến " + max + " thôi!");
            } catch (NumberFormatException e) {
                System.out.println("Phải nhập số!");
            }
        }
    }
}