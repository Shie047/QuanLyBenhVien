import java.util.Scanner;

public class Input {
        public static String InputName(Scanner sc, String infomation) {
            String input;
            while (true) {
                System.out.print(infomation);
                input = sc.nextLine();
                if (input.matches("^[\\p{L}\\s]+$")) {
                    return input;
                }
                System.out.println("Tên chỉ được chứa chữ cái. Vui lòng nhập lại!");
            }
        }
        public static int InputAge(Scanner sc, String infomation) {
            int age;
            while (true) {
                System.out.print(infomation);
                try {
                    age = Integer.parseInt(sc.nextLine());
                    if (age > 0) {
                        return age;
                    }
                    System.out.println("Tuổi phải lớn hơn 0. Vui lòng nhập lại!");
                } catch (NumberFormatException e) {
                    System.out.println("Tuổi phải là một số. Vui lòng nhập lại!");
                }
            }
        }
        public static int InputByRange(Scanner sc, String infomation, int min, int max) {
            int num;
            while (true) {
                System.out.print(infomation);
                try {
                    num = Integer.parseInt(sc.nextLine());
                    if (num >= min && num <= max) {
                        return num;
                    }
                    System.out.println("Lựa chọn chỉ được từ " + min + " đến " + max + ". Vui lòng nhập lại!");
                } catch (NumberFormatException e) {
                    System.out.println("Phải nhập số. Vui lòng nhập lại!");
                }
            }
        }
}
