package ui;
import java.util.List;
import java.util.Scanner;
import repository.*;
import control.*;
public class Main {
    public static void main(String[] args) {
        DoctorManager DM = new DoctorManager();
        MedicineManager MM = new MedicineManager();
        Scanner sc = new Scanner(System.in);
        printIfNotNull(DM.loadFile());
        printIfNotNull(MM.loadFile());

        int mainFlag;
        do {
            System.out.println("\nHỆ THỐNG QUẢN LÝ BỆNH VIỆN");
            System.out.println("1. Quản lý Bác sĩ");
            System.out.println("2. Quản lý Bệnh nhân");
            System.out.println("3. Quản lý Thuốc");
            System.out.println("4. Kê Thuốc");
            System.out.println("0. Thoát hệ thống");
            mainFlag = Input.inputByRange(sc, "Chọn chức năng (0-4): ", 0, 4);
            switch (mainFlag) {
                case 1:
                    manageDoctors(sc, DM);
                    break;
                case 2:
                    System.out.println("Trống");
                    break;
                case 3:
                    manageMedicines(sc, MM);
                    break;
                case 4:
                    System.out.println("Trống");
                    break;
                case 0:
                    System.out.println("Đang thoát chương trình");
                    printIfNotNull(DM.saveFile());
                    printIfNotNull(MM.saveFile());
                    break;
            }
        } while (mainFlag != 0);

        sc.close();
    }

    private static void printIfNotNull(String message) {
        if (message != null && !message.isEmpty()) {
            System.out.println(message);
        }
    }

    //quản lý bác sĩ
    private static void manageDoctors(Scanner sc, DoctorManager DM) {
        int flag;
        do {
            System.out.println("\nQUẢN LÝ BÁC SĨ");
            System.out.println("1. Thêm bác sĩ");
            System.out.println("2. Sửa thông tin bác sĩ");
            System.out.println("3. Xóa bác sĩ");
            System.out.println("4. Xem danh sách");
            System.out.println("0. Quay lại menu chính");
            flag = Input.inputByRange(sc, "Chọn chức năng (0-4): ", 0, 4);
            switch (flag) {
                case 1:
                    addDoctor(sc, DM);
                    break;
                case 2:
                    updateDoctor(sc, DM);
                    break;
                case 3:

                    String deleteId = Input.inputLine(sc, "Nhập mã BS cần xóa: ");
                    System.out.println(DM.delete(deleteId));
                    break;
                case 4:
                    printDoctors(DM.getAll());
                    break;
                case 0:
                    System.out.println("Quay lại menu chính...");
                    break;
            }
        } while (flag != 0);
    }
    private static void printDoctors(List<Doctor> doctors) {
        if (doctors.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        System.out.println("DANH SÁCH BÁC SĨ");
        for (Doctor d : doctors) {
            System.out.println(d);
        }
    }

    private static void addDoctor(Scanner sc, DoctorManager DM) {
        System.out.println("THÊM BÁC SĨ MỚI");
        String name = Input.inputName(sc, "Nhập tên: ");
        int age = Input.inputAge(sc, "Nhập tuổi: ");
        while (age < 18) {
            age = Input.inputAge(sc, "Nhập lại tuổi(bác sĩ phải trên 18): ");
        }
        System.out.println("Vui lòng chọn phòng ban:");
        System.out.println("1. Khoa Nội | 2. Khoa Ngoại | 3. Khoa Nhi | 4. Khoa Cấp cứu");
        int choiceDepartment = Input.inputByRange(sc, "Chọn (1-4): ", 1, 4);
        String nameDepartment = nameDepartment(choiceDepartment);
        System.out.println(DM.add(new Doctor(age, name, nameDepartment)));
    }

    private static void updateDoctor(Scanner sc, DoctorManager DM) {
        String FindID = Input.inputLine(sc, "\nNhập mã BS cần sửa: ");
        Doctor OldDoctor = DM.getDoctor(FindID);
        if (OldDoctor == null) {
            System.out.println("Không tìm thấy bác sĩ có mã " + FindID);
            return;
        }
        System.out.println("Bạn muốn sửa thông tin nào của BS " + OldDoctor.getName() + "?");
        System.out.println("1. Tên | 2. Tuổi | 3. Phòng ban");
        int choiceUpdate = Input.inputByRange(sc, "Chọn (1-3): ", 1, 3);

        switch (choiceUpdate) {
            case 1:
                String newName = Input.inputName(sc, "Nhập tên mới: ");
                OldDoctor.setName(newName);
                break;
            case 2:
                int newAge = Input.inputAge(sc, "Nhập tuổi mới: ");
                OldDoctor.setAge(newAge);
                break;
            case 3:
                System.out.println("Vui lòng chọn phòng ban mới:");
                System.out.println("1. Khoa Nội | 2. Khoa Ngoại | 3. Khoa Nhi | 4. Khoa Cấp cứu");
                int newDept = Input.inputByRange(sc, "Chọn (1-4): ", 1, 4);
                OldDoctor.setDepartment(nameDepartment(newDept));
                break;
        }
        System.out.println(DM.update(FindID, OldDoctor));
    }

    private static String nameDepartment(int choice) {
        switch (choice) {
            case 1: return "Khoa Nội";
            case 2: return "Khoa Ngoại";
            case 3: return "Khoa Nhi";
            case 4: return "Khoa Cấp cứu";
            default: return "Chưa phân bổ";
        }
    }

    //quản lý bệnh nhân
    //trống

    //quản lý thuốc
    private static void manageMedicines(Scanner sc, MedicineManager MM) {
        int flag;
        do {
            System.out.println("\nQUẢN LÝ THUỐC");
            System.out.println("1. Thêm thuốc mới");
            System.out.println("2. Sửa thông tin thuốc");
            System.out.println("3. Xóa thuốc");
            System.out.println("4. Xem danh sách thuốc");
            System.out.println("0. Quay lại menu chính");
            flag = Input.inputByRange(sc, "Chọn chức năng (0-4): ", 0, 4);

            switch (flag) {
                case 1:
                    addMedicine(sc, MM);
                    break;
                case 2:
                    updateMedicine(sc, MM);
                    break;
                case 3:
                    String deleteId = Input.inputLine(sc, "Nhập mã Thuốc cần xóa: ");
                    System.out.println(MM.delete(deleteId));
                    break;
                case 4:
                    printMedicines(MM.getAll());
                    break;
                case 0:
                    System.out.println("Quay lại menu chính...");
                    break;
            }
        } while (flag != 0);
    }

    private static void printMedicines(List<Medicine> medicines) {
        if (medicines.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        System.out.println("DANH SÁCH THUỐC");
        for (Medicine m : medicines) {
            System.out.println(m.showInfo());
        }
    }

    private static void addMedicine(Scanner sc, MedicineManager MM) {
        System.out.println("THÊM THUỐC MỚI");
        String code = Input.inputCode(sc, "Nhập mã thuốc: ");
        String name = Input.inputText(sc, "Nhập tên thuốc: ");

        double price = Input.inputPositiveDouble(sc, "Nhập giá thuốc (VND): ");
        int quantity = Input.inputPositiveInt(sc, "Nhập số lượng tồn kho: ");

        System.out.println(MM.add(new Medicine(code, name, price, quantity)));
    }

    private static void updateMedicine(Scanner sc, MedicineManager MM) {
        String FindID = Input.inputLine(sc, "\nNhập mã Thuốc cần sửa: ");
        Medicine oldMed = MM.getMedicine(FindID);

        if (oldMed == null) {
            System.out.println("Không tìm thấy thuốc có mã " + FindID);
            return;
        }

        System.out.println("Bạn muốn sửa thông tin nào của thuốc " + oldMed.getName() + "?");
        System.out.println("1. Tên thuốc | 2. Giá | 3. Số lượng");
        int choiceUpdate = Input.inputByRange(sc, "Chọn (1-3): ", 1, 3);

        switch (choiceUpdate) {
            case 1:
                String newName = Input.inputText(sc, "Nhập tên mới: ");
                oldMed.setName(newName);
                break;
            case 2:
                double newPrice = Input.inputPositiveDouble(sc, "Nhập giá mới (VND): ");
                oldMed.setUnitPrice(newPrice);
                break;
            case 3:
                int newQuantity = Input.inputPositiveInt(sc, "Nhập số lượng mới: ");
                oldMed.setQuantity(newQuantity);
                break;
        }
        System.out.println(MM.update(FindID, oldMed));
    }
}