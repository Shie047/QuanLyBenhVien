package control;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import repository.*;
public class PatientManager implements IManager<Patient> {

    private static final String FILE_PATH = "data/patient.txt";

    private final List<Patient> patientList;

    public PatientManager() {
        patientList = new ArrayList<>();
        loadFromFile();
    }

    @Override
    public String add(Patient patient) {

        if (patient == null) {
            return "Patient is null.";
        }

        if (findById(patient.getIdPatient()) != null) {
            return "Patient ID already exists.";
        }

        patientList.add(patient);
        saveToFile();

        return "Patient added successfully.";
    }

    @Override
    public String update(String id, Patient newPatient) {

        Patient patient = findById(id);

        if (patient == null) {
            return "Patient not found.";
        }

        // Không cho sửa mã bệnh nhân
        patient.setSymptom(newPatient.getSymptom());

        saveToFile();

        return "Patient updated successfully.";
    }

    @Override
    public String delete(String id) {

        Patient patient = findById(id);

        if (patient == null) {
            return "Patient not found.";
        }

        patientList.remove(patient);

        saveToFile();

        return "Patient deleted successfully.";
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

            return "Saved successfully.";

        } catch (IOException e) {
            return "Save failed.";
        }
    }

    // Đọc file
    private String loadFromFile() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return "File not found.";
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

            return "Loaded successfully.";

        } catch (IOException e) {
            return "Load failed.";
        }
    }

    public List<Patient> getAll() {
        return patientList;
    }
}