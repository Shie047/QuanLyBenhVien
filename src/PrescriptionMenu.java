import java.util.Scanner;

public class PrescriptionMenu {

    private static final Scanner sc = new Scanner(System.in);

    public static void showMenu(PrescriptionManager prescriptionManager,
                                PatientManager patientManager,
                                DoctorManager doctorManager) {

        while (true) {

            System.out.println("\n========== PRESCRIPTION MENU ==========");
            System.out.println("1. Them don thuoc");
            System.out.println("2. Cap nhat don thuoc");
            System.out.println("3. Xoa don thuoc");
            System.out.println("4. Hien thi danh sach");
            System.out.println("0. Quay lai");

            int choice = Input.inputByRange(sc, "Nhap lua chon: ", 0, 4);

            switch (choice) {

                case 1:

                    String code = Input.inputCode(sc, "Ma don thuoc: ");

                    String patientCode = Input.inputCode(sc, "Ma benh nhan: ");
                    Patient patient = patientManager.findById(patientCode);

                    if (patient == null) {
                        System.out.println("Khong tim thay benh nhan!");
                        break;
                    }

                    String doctorCode = Input.inputCode(sc, "Ma bac si: ");
                    Doctor doctor = doctorManager.getDoctor(doctorCode);

                    if (doctor == null) {
                        System.out.println("Khong tim thay bac si!");
                        break;
                    }

                    String date = Input.inputLine(sc, "Ngay ke don: ");

                    Prescription prescription =
                            new Prescription(code, patient, doctor, date);

                    prescriptionManager.add(prescription);

                    System.out.println("Them don thuoc thanh cong!");

                    break;

                case 2:

                    code = Input.inputCode(sc, "Nhap ma don thuoc: ");

                    patientCode = Input.inputCode(sc, "Ma benh nhan moi: ");
                    patient = patientManager.findById(patientCode);

                    if (patient == null) {
                        System.out.println("Khong tim thay benh nhan!");
                        break;
                    }

                    doctorCode = Input.inputCode(sc, "Ma bac si moi: ");
                    doctor = doctorManager.getDoctor(doctorCode);

                    if (doctor == null) {
                        System.out.println("Khong tim thay bac si!");
                        break;
                    }

                    date = Input.inputLine(sc, "Ngay ke don moi: ");

                    Prescription newPrescription =
                            new Prescription(code, patient, doctor, date);

                    prescriptionManager.update(code, newPrescription);

                    System.out.println("Cap nhat thanh cong!");

                    break;

                case 3:

                    code = Input.inputCode(sc, "Nhap ma don thuoc can xoa: ");

                    prescriptionManager.delete(code);

                    break;

                case 4:

                    prescriptionManager.showAll();

                    break;

                case 0:

                    return;
            }
        }
    }
}