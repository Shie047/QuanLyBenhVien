package control;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import repository.*;

public class PatientManager implements IManager<Patient> {

    private static final String FILE_PATH = "data/patients.txt";
    private static final String SAVE_FAILED = "Lưu thất bại.";

    private final List<Patient> patientList;

    public PatientManager() {
        patientList = new ArrayList<>();
        loadFromFile();
    }

    @Override
    public String add(Patient patient) {

        if (patient == null) {
            return "Bệnh nhân không hợp lệ.";
        }

        if (findById(patient.getIdPatient()) != null) {
            return "Mã bệnh nhân đã tồn tại.";
        }
        patientList.add(patient);
        String saveResult = saveToFile();
        if (saveResult.equals(SAVE_FAILED)) {
            patientList.remove(patient);
            return "Lưu bệnh nhân vào cơ sở dữ liệu thất bại.";
        }

        return "Đã thêm bệnh nhân thành công.";
    }

    @Override
    public String update(String id, Patient newPatient) {

        Patient patient = findById(id);

        if (patient == null) {
            return "Không tìm thấy bệnh nhân.";
        }
        String oldName = patient.getName();
        int oldAge = patient.getAge();
        String oldSymptom = patient.getSymptom();
        patient.setName(newPatient.getName());
        patient.setAge(newPatient.getAge());
        patient.setSymptom(newPatient.getSymptom());

        String saveResult = saveToFile();
        if (saveResult.equals(SAVE_FAILED)) {
            patient.setName(oldName);
            patient.setAge(oldAge);
            patient.setSymptom(oldSymptom);
            return "Cập nhật bệnh nhân trong cơ sở dữ liệu thất bại.";
        }

        return "Đã cập nhật bệnh nhân thành công.";
    }

    @Override
    public String delete(String id) {

        Patient patient = findById(id);

        if (patient == null) {
            return "Không tìm thấy bệnh nhân.";
        }

        patientList.remove(patient);

        String saveResult = saveToFile();
        if (saveResult.equals(SAVE_FAILED)) {
            patientList.add(patient);
            return "Xóa bệnh nhân khỏi cơ sở dữ liệu thất bại.";
        }

        return "Đã xóa bệnh nhân thành công.";
    }

    // Tìm bệnh nhân theo ID
    public Patient findById(String id) {
        for (Patient patient : patientList) {
            if (patient.getIdPatient().equalsIgnoreCase(id)) {
                return patient;
            }
        }
        return null;
    }

    // Lưu file
    private String saveToFile() {
        File folder = new File("data");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Patient patient : patientList) {
                bw.write(patient.toFileLine());
                bw.newLine();
            }
            return "Đã lưu thành công.";

        } catch (IOException e) {
            return SAVE_FAILED;
        }
    }

    // Đọc file
    private String loadFromFile() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return "Không tìm thấy file.";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            int maxId = 0;

            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {

                    Patient patient = Patient.fromFileLine(line);

                    if (patient != null) {
                        patientList.add(patient);
                        try {
                            String numberOnly = patient.getIdPatient().replaceAll("\\D+", "");
                            if (!numberOnly.isEmpty()) {
                                int currentIdNum = Integer.parseInt(numberOnly);
                                if (currentIdNum > maxId) {
                                    maxId = currentIdNum;
                                }
                            }
                        } catch (NumberFormatException ignored) {}
                    }
                }
            }

            Patient.setIdCounter(maxId);

            return "Đã tải thành công.";

        } catch (IOException e) {
            return "Tải thất bại.";
        }
    }
    public List<Patient> getAll() {
        return new ArrayList<>(patientList);
    }
}