import java.util.Scanner;

public class PatientMenu {

    private static Scanner sc = new Scanner(System.in);

    public static void showMenu(PatientManager patientManager) {

        while (true) {

            System.out.println("\n===== PATIENT MENU =====");
            System.out.println("1. Them benh nhan");
            System.out.println("2. Cap nhat benh nhan");
            System.out.println("3. Xoa benh nhan");
            System.out.println("4. Hien thi danh sach");
            System.out.println("0. Quay lai");

            int choice = Input.inputByRange(sc, "Nhap lua chon: ", 0, 4);

            switch (choice) {

                case 1:

                    System.out.print("Ma benh nhan: ");
                    String code = sc.nextLine();

                    String name = Input.inputName(sc, "Ten: ");

                    int age = Input.inputAge(sc, "Tuoi: ");

                    System.out.print("Trieu chung: ");
                    String symptom = sc.nextLine();

                    Patient patient =
                            new Patient(code, name, age, symptom);

                    patientManager.add(patient);

                    break;

                case 2:

                    System.out.print("Nhap ma benh nhan: ");
                    String ma = sc.nextLine();

                    name = Input.inputName(sc, "Ten moi: ");

                    age = Input.inputAge(sc, "Tuoi moi: ");

                    System.out.print("Trieu chung moi: ");
                    symptom = sc.nextLine();

                    Patient newPatient =
                            new Patient(ma, name, age, symptom);

                    patientManager.update(ma, newPatient);

                    break;

                case 3:

                    System.out.print("Nhap ma benh nhan: ");
                    patientManager.delete(sc.nextLine());

                    break;

                case 4:

                    patientManager.showAll();

                    break;

                case 0:

                    return;
            }

        }

    }

}