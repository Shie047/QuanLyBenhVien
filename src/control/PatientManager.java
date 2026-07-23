package control;
<<<<<<< HEAD

=======
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import repository.*;
<<<<<<< HEAD

public class PatientManager implements IManager<Patient> {

    private static final String FILE_PATH = "data/patients.txt";
=======
public class PatientManager implements IManager<Patient> {

    private static final String FILE_PATH = "data/patient.txt";
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc

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
<<<<<<< HEAD
        patientList.add(patient);
        String saveResult = saveToFile();
        if (saveResult.equals("Save failed.")) {
            patientList.remove(patient);
            return "Failed to save patient to database.";
        }
=======

        patientList.add(patient);
        saveToFile();
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc

        return "Patient added successfully.";
    }

    @Override
    public String update(String id, Patient newPatient) {

        Patient patient = findById(id);

        if (patient == null) {
            return "Patient not found.";
        }
<<<<<<< HEAD
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
=======

        // Không cho sửa mã bệnh nhân
        patient.setSymptom(newPatient.getSymptom());

        saveToFile();
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc

        return "Patient updated successfully.";
    }

    @Override
    public String delete(String id) {

        Patient patient = findById(id);

        if (patient == null) {
            return "Patient not found.";
        }

        patientList.remove(patient);

<<<<<<< HEAD
        String saveResult = saveToFile();
        if (saveResult.equals("Save failed.")) {
            patientList.add(patient);
            return "Failed to delete patient from database.";
        }
=======
        saveToFile();
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc

        return "Patient deleted successfully.";
    }

<<<<<<< HEAD
    // Tìm bệnh nhân theo ID
    public Patient findById(String id) {
        for (Patient patient : patientList) {
=======


    // Tìm bệnh nhân theo ID
    public Patient findById(String id) {

        for (Patient patient : patientList) {

>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
            if (patient.getIdPatient().equalsIgnoreCase(id)) {
                return patient;
            }
        }
<<<<<<< HEAD
=======

>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
        return null;
    }

    // Lưu file
    private String saveToFile() {
<<<<<<< HEAD
=======

>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
        File folder = new File("data");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
<<<<<<< HEAD
=======

>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
            for (Patient patient : patientList) {
                bw.write(patient.toFileLine());
                bw.newLine();
            }
<<<<<<< HEAD
=======

>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
            return "Saved successfully.";

        } catch (IOException e) {
            return "Save failed.";
        }
    }

    // Đọc file
    private String loadFromFile() {
<<<<<<< HEAD
=======

>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return "File not found.";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
<<<<<<< HEAD
            int maxId = 0;

            while ((line = br.readLine()) != null) {
=======

            while ((line = br.readLine()) != null) {

>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
                if (!line.trim().isEmpty()) {

                    Patient patient = Patient.fromFileLine(line);

                    if (patient != null) {
                        patientList.add(patient);
<<<<<<< HEAD
                        try {
                            String numberOnly = patient.getIdPatient().replaceAll("\\D+", "");
                            if (!numberOnly.isEmpty()) {
                                int currentIdNum = Integer.parseInt(numberOnly);
                                if (currentIdNum > maxId) {
                                    maxId = currentIdNum;
                                }
                            }
                        } catch (NumberFormatException ignored) {}
=======
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
                    }
                }
            }

<<<<<<< HEAD
            Patient.setIdCounter(maxId);

=======
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
            return "Loaded successfully.";

        } catch (IOException e) {
            return "Load failed.";
        }
    }
<<<<<<< HEAD
    public List<Patient> getAll() {
        return new ArrayList<>(patientList);
=======

    public List<Patient> getAll() {
        return patientList;
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
    }
}