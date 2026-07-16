import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatientManager implements IManager<Patient>{
    private static final String FILE_PATH = "data/patient.txt";

    private List<Patient> patientList;

    public PatientManager() {
        patientList = new ArrayList<>();
        loadFromFile();
    }

    @Override
    public void Add(Patient patient) {

        if (findById(patient.getCodePatient()) != null) {
            System.out.println("Ma benh nhan da ton tai!");
            return;
        }

        patientList.add(patient);
        saveToFile();

        System.out.println("Them thanh cong!");
    }

    @Override
    public void Update(String ma, Patient newPatient) {

        Patient patient = findById(ma);

        if (patient == null) {
            System.out.println("Khong tim thay benh nhan!");
            return;
        }

        // Chỉ cập nhật triệu chứng
        patient.setSymptom(newPatient.getSymptom());

        saveToFile();

        System.out.println("Cap nhat thanh cong!");
    }

    @Override
    public void Delete(String ma) {

        Patient patient = findById(ma);

        if (patient == null) {
            System.out.println("Khong tim thay benh nhan!");
            return;
        }

        patientList.remove(patient);

        saveToFile();

        System.out.println("Xoa thanh cong!");
    }

    @Override
    public void ShowAll() {

        if (patientList.isEmpty()) {
            System.out.println("Danh sach benh nhan rong!");
            return;
        }

        for (Patient patient : patientList) {
            System.out.println(patient.showInfo());
        }
    }

    // tìm kiếm

    public Patient findById(String ma) {

        for (Patient patient : patientList) {

            if (patient.getCodePatient().equalsIgnoreCase(ma)) {
                return patient;
            }

        }

        return null;
    }

    // lưu file

    private void saveToFile() {

        File folder = new File("data");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (Patient patient : patientList) {
                bw.write(patient.toFileLine());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Loi luu file!");
        }
    }

    //đọc file

    private void loadFromFile() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (!line.trim().isEmpty()) {
                    Patient patient = Patient.fromFileLine(line);

                    if (patient != null) {
                        patientList.add(patient);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Loi doc file!");
        }
    }

    public List<Patient> getPatientList() {
        return patientList;
    }
}