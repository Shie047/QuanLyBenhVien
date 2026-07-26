package ui;

import java.util.List;
import java.util.Scanner;
import repository.*;
import control.*;

public class Main {

    public static void main(String[] args) {
        DoctorManager DM = new DoctorManager();
        MedicineManager MM = new MedicineManager();
        PatientManager PM = new PatientManager();
        Scanner sc = new Scanner(System.in);
        printIfNotNull(DM.loadFile());
        printIfNotNull(MM.loadFile());
        PrescriptionManager PRM = new PrescriptionManager(PM, DM, MM);
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
                case 1: manageDoctors(sc, DM); break;
                case 2: managePatients(sc, PM); break;
                case 3: manageMedicines(sc, MM); break;
                case 4: managePrescriptions(sc, PRM, PM, DM, MM); break;
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
    //QUẢN LÝ BÁC SĨ
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
                case 1: addDoctor(sc, DM); break;
                case 2: updateDoctor(sc, DM); break;
                case 3:
                    String deleteId = Input.inputLine(sc, "Nhập mã BS cần xóa: ");
                    System.out.println(DM.delete(deleteId));
                    break;
                case 4: printDoctors(DM.getAll()); break;
                case 0: System.out.println("Quay lại menu chính..."); break;
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
        while (age < Doctor.MIN_AGE) {
            age = Input.inputAge(sc, "Nhập lại tuổi (bác sĩ phải từ " + Doctor.MIN_AGE + " trở lên): ");
        }
        System.out.println("Vui lòng chọn phòng ban:");
        System.out.println("1. Khoa Nội | 2. Khoa Ngoại | 3. Khoa Nhi | 4. Khoa Cấp cứu");
        int choiceDepartment = Input.inputByRange(sc, "Chọn (1-4): ", 1, 4);
        String nameDepartment = Doctor.getDepartmentName(choiceDepartment);

        System.out.println(DM.add(new Doctor(age, name, nameDepartment)));
    }

    private static void updateDoctor(Scanner sc, DoctorManager DM) {
        String FindID = Input.inputLine(sc, "\nNhập mã BS cần sửa: ");
        Doctor oldDoctor = DM.getDoctor(FindID);
        if (oldDoctor == null) {
            System.out.println("Không tìm thấy bác sĩ có mã " + FindID);
            return;
        }
        System.out.println("Bạn muốn sửa thông tin nào của BS " + oldDoctor.getName() + "?");
        System.out.println("1. Tên | 2. Tuổi | 3. Phòng ban");
        int choiceUpdate = Input.inputByRange(sc, "Chọn (1-3): ", 1, 3);
        String newName = oldDoctor.getName();
        int newAge = oldDoctor.getAge();
        String newDept = oldDoctor.getDepartment();

        switch (choiceUpdate) {
            case 1: newName = Input.inputName(sc, "Nhập tên mới: "); break;
            case 2: newAge = Input.inputAge(sc, "Nhập tuổi mới: "); break;
            case 3:
                System.out.println("Vui lòng chọn phòng ban mới:");
                System.out.println("1. Khoa Nội | 2. Khoa Ngoại | 3. Khoa Nhi | 4. Khoa Cấp cứu");
                int deptChoice = Input.inputByRange(sc, "Chọn (1-4): ", 1, 4);
                newDept = Doctor.getDepartmentName(deptChoice);
                break;
        }

        try {
            Doctor tempDoctor = new Doctor(newAge, newName, newDept);
            tempDoctor.setIdDoctor(FindID);
            System.out.println(DM.update(FindID, tempDoctor));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    //QUẢN LÝ BỆNH NHÂN
    private static void managePatients(Scanner sc, PatientManager PM) {
        int flag;
        do {
            System.out.println("\nQUẢN LÝ BỆNH NHÂN");
            System.out.println("1. Thêm bệnh nhân");
            System.out.println("2. Sửa thông tin bệnh nhân");
            System.out.println("3. Xóa bệnh nhân");
            System.out.println("4. Xem danh sách");
            System.out.println("0. Quay lại menu chính");
            flag = Input.inputByRange(sc, "Chọn chức năng (0-4): ", 0, 4);
            switch (flag) {
                case 1: addPatient(sc, PM); break;
                case 2: updatePatient(sc, PM); break;
                case 3:
                    String deleteId = Input.inputCode(sc, "Nhập mã bệnh nhân cần xóa: ");
                    System.out.println(PM.delete(deleteId));
                    break;
                case 4: printPatients(PM.getAll()); break;
                case 0: System.out.println("Quay lại menu chính..."); break;
            }
        } while (flag != 0);
    }

    private static void printPatients(List<Patient> patients) {
        if (patients.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        System.out.println("DANH SÁCH BỆNH NHÂN");
        for (Patient p : patients) {
            System.out.println(p);
        }
    }

    private static void addPatient(Scanner sc, PatientManager PM) {
        System.out.println("THÊM BỆNH NHÂN MỚI");
        String name = Input.inputName(sc, "Nhập tên: ");
        int age = Input.inputAge(sc, "Nhập tuổi: ");
        String symptom = Input.inputSymptom(sc, "Nhập triệu chứng: ");

        Patient patient = new Patient(name, age, symptom);
        System.out.println(PM.add(patient));
        System.out.println("Mã bệnh nhân được cấp: " + patient.getIdPatient());
    }

    private static void updatePatient(Scanner sc, PatientManager PM) {
        String code = Input.inputCode(sc, "Nhập mã bệnh nhân cần sửa: ");
        Patient old = PM.findById(code);
        if (old == null) {
            System.out.println("Không tìm thấy bệnh nhân có mã " + code);
            return;
        }

        System.out.println("Bạn muốn sửa thông tin nào của bệnh nhân " + old.getName() + "?");
        System.out.println("1. Tên | 2. Tuổi | 3. Triệu chứng");
        int choiceUpdate = Input.inputByRange(sc, "Chọn (1-3): ", 1, 3);

        String newName = old.getName();
        int newAge = old.getAge();
        String newSymptom = old.getSymptom();

        switch (choiceUpdate) {
            case 1: newName = Input.inputName(sc, "Nhập tên mới: "); break;
            case 2: newAge = Input.inputAge(sc, "Nhập tuổi mới: "); break;
            case 3: newSymptom = Input.inputSymptom(sc, "Nhập triệu chứng mới: "); break;
        }

        Patient tempPatient = new Patient(newName, newAge, newSymptom);
        System.out.println(PM.update(code, tempPatient));
    }

    // QUẢN LÝ THUỐC
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
                case 1: addMedicine(sc, MM); break;
                case 2: updateMedicine(sc, MM); break;
                case 3:
                    String deleteId = Input.inputLine(sc, "Nhập mã Thuốc cần xóa: ");
                    System.out.println(MM.delete(deleteId));
                    break;
                case 4: printMedicines(MM.getAll()); break;
                case 0: System.out.println("Quay lại menu chính..."); break;
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

        String newName = oldMed.getName();
        double newPrice = oldMed.getUnitPrice();
        int newQuantity = oldMed.getQuantity();

        switch (choiceUpdate) {
            case 1: newName = Input.inputText(sc, "Nhập tên mới: "); break;
            case 2: newPrice = Input.inputPositiveDouble(sc, "Nhập giá mới (VND): "); break;
            case 3: newQuantity = Input.inputPositiveInt(sc, "Nhập số lượng mới: "); break;
        }

        Medicine tempMed = new Medicine(oldMed.getIdMedicine(), newName, newPrice, newQuantity);
        System.out.println(MM.update(FindID, tempMed));
    }
    private static void managePrescriptions(Scanner sc, PrescriptionManager PRM, PatientManager PM, DoctorManager DM, MedicineManager MM) {
        int flag;
        do {
            System.out.println("\nKÊ THUỐC");
            System.out.println("1. Thêm đơn thuốc");
            System.out.println("2. Cập nhật đơn thuốc");
            System.out.println("3. Xóa đơn thuốc");
            System.out.println("4. Hiển thị danh sách");
            System.out.println("0. Quay lại menu chính");
            flag = Input.inputByRange(sc, "Chọn chức năng (0-4): ", 0, 4);
            switch (flag) {
                case 1: addPrescription(sc, PRM, PM, DM, MM); break;
                case 2: updatePrescription(sc, PRM, PM, DM, MM); break;
                case 3:
                    String deleteId = Input.inputCode(sc, "Nhập mã đơn thuốc cần xóa (VD: DT1): ");
                    System.out.println(PRM.delete(deleteId));
                    break;
                case 4: printPrescriptions(PRM.getAll()); break;
                case 0: System.out.println("Quay lại menu chính..."); break;
            }
        } while (flag != 0);
    }

    private static void printPrescriptions(List<Prescription> prescriptions) {
        if (prescriptions.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        System.out.println("DANH SÁCH ĐƠN THUỐC");
        for (Prescription p : prescriptions) {
            System.out.println(p);
            System.out.println("----------------------------");
        }
    }

    private static void addPrescription(Scanner sc, PrescriptionManager PRM, PatientManager PM, DoctorManager DM, MedicineManager MM) {
        System.out.println("THÊM ĐƠN THUỐC MỚI");

        String patientCode = Input.inputCode(sc, "Nhập mã bệnh nhân (VD: BN1): ");
        Patient patient = PM.findById(patientCode);
        if (patient == null) {
            System.out.println("Không tìm thấy bệnh nhân!");
            return;
        }
        System.out.println("\n[THÔNG TIN BỆNH NHÂN]");
        System.out.println("- Họ tên: " + patient.getName());
        System.out.println("- Triệu chứng: " + patient.getSymptom() + "\n");

        String doctorCode = Input.inputCode(sc, "Nhập mã bác sĩ kê đơn (VD: BS1): ");
        Doctor doctor = DM.getDoctor(doctorCode);
        if (doctor == null) {
            System.out.println("Không tìm thấy bác sĩ!");
            return;
        }

        Medicine medicine;
        while (true) {
            String medicineCode = Input.inputCode(sc, "Nhập mã thuốc: ");
            medicine = MM.getMedicine(medicineCode);
            if (medicine != null) {
                break;
            }
            System.out.println("Không tìm thấy thuốc có mã " + medicineCode + ". Vui lòng nhập lại!");
        }

        while (true) {
            int quantity = Input.inputNonNegativeInt(sc,
                    "Nhập số lượng thuốc (tồn kho: " + medicine.getQuantity() + ", nhập 0 để hủy đơn thuốc): ");
            if (quantity == 0) {
                System.out.println("Đã hủy kê đơn thuốc.");
                return;
            }
            try {
                Prescription prescription = PRM.addPrescription(patient, doctor, medicine, quantity);
                System.out.println("Prescription added successfully.");
                System.out.println("Mã đơn thuốc được cấp: " + prescription.getIdPrescription());
                return;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage() + " Vui lòng nhập lại!");
            }
        }
    }

    private static void updatePrescription(Scanner sc, PrescriptionManager PRM, PatientManager PM, DoctorManager DM, MedicineManager MM) {
        String code = Input.inputCode(sc, "Nhập mã đơn thuốc cần sửa (VD: DT1): ");
        Prescription old = PRM.findById(code);
        if (old == null)  {
            System.out.println("Không tìm thấy đơn thuốc có mã " + code);
            return;
        }

        String patientCode = Input.inputCode(sc, "Nhập mã bệnh nhân mới (VD: BN1): ");
        Patient patient = PM.findById(patientCode);
        if (patient == null) {
            System.out.println("Không tìm thấy bệnh nhân!");
            return;
        }

        System.out.println("\n[THÔNG TIN BỆNH NHÂN]");
        System.out.println("- Họ tên: " + patient.getName());
        System.out.println("- Triệu chứng: " + patient.getSymptom() + "\n");

        String doctorCode = Input.inputCode(sc, "Nhập mã bác sĩ mới (VD: BS1): ");
        Doctor doctor = DM.getDoctor(doctorCode);
        if (doctor == null) {
            System.out.println("Không tìm thấy bác sĩ!");
            return;
        }

        Medicine medicine;
        while (true) {
            String medicineCode = Input.inputCode(sc, "Nhập mã thuốc mới: ");
            medicine = MM.getMedicine(medicineCode);
            if (medicine != null) {
                break;
            }
            System.out.println("Không tìm thấy thuốc có mã " + medicineCode + ". Vui lòng nhập lại!");
        }

        while (true) {
            int available = PRM.getAvailableQuantity(old, medicine);
            int quantity = Input.inputNonNegativeInt(sc,
                    "Nhập số lượng thuốc (Tổng khả dụng: " + available + ", nhập 0 để hủy sửa đơn thuốc): ");
            if (quantity == 0) {
                System.out.println("Đã hủy sửa đơn thuốc.");
                return;
            }
            try {
                PRM.editPrescription(code, patient, doctor, medicine, quantity);
                System.out.println("Prescription updated successfully.");
                return;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage() + " Vui lòng nhập lại!");
            }
        }
    }
}