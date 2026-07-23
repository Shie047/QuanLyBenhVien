package control;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import repository.*;

public class PatientManager implements IManager<Patient> {

    private static final String FILE_PATH = "data/patients.txt";

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
        String saveResult = saveToFile();
        if (saveResult.equals("Save failed.")) {
            patientList.remove(patient);
            return "Failed to save patient to database.";
        }

        return "Patient added successfully.";
    }

    @Override
    public String update(String id, Patient newPatient) {

        Patient patient = findById(id);

        if (patient == null) {
            return "Patient not found.";
        }
        String oldName = patient.getName();
        int oldAge = patient.getAge();
        String oldSymptom = patient.getSymptom();
        patient.setName(newPatient.getName());
        patient.setAge(newPatient.getAge());
        patient.setSymptom(newPatient.getSymptom());

        String saveResult = saveToFile();
        if (saveResult.equals("Save failed.")) {
            patient.setName(oldName);
            patient.setAge(oldAge);
            patient.setSymptom(oldSymptom);
            return "Failed to update patient in database.";
        }

        return "Patient updated successfully.";
    }

    @Override
    public String delete(String id) {

        Patient patient = findById(id);

        if (patient == null) {
            return "Patient not found.";
        }

        patientList.remove(patient);

        String saveResult = saveToFile();
        if (saveResult.equals("Save failed.")) {
            patientList.add(patient);
            return "Failed to delete patient from database.";
        }

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

            return "Loaded successfully.";

        } catch (IOException e) {
            return "Load failed.";
        }
    }
    public List<Patient> getAll() {
        return new ArrayList<>(patientList);
    }
}