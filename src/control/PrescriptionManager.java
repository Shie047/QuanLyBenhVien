package control;
<<<<<<< HEAD

=======
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import repository.*;
<<<<<<< HEAD

public class PrescriptionManager implements IManager<Prescription> {
    private static final String FILE_PATH = "data/prescriptions.txt";
    private List<Prescription> prescriptionList;
    private PatientManager patientManager;
    private DoctorManager doctorManager;
    private MedicineManager medicineManager;
    public PrescriptionManager(PatientManager patientManager, DoctorManager doctorManager, MedicineManager medicineManager) {
        this.patientManager = patientManager;
        this.doctorManager = doctorManager;
        this.medicineManager = medicineManager;

        this.prescriptionList = new ArrayList<>();
        loadFromFile();
    }
    @Override
    public String add(Prescription prescription) {
        if (prescription == null) {
            return "Prescription is null.";
        }
        if (findById(prescription.getIdPrescription()) != null) {
            return "Prescription ID already exists.";
        }
        prescriptionList.add(prescription);
        String saveResult = saveToFile();
        if (saveResult.equals("Save failed.")) {
            prescriptionList.remove(prescription);
            return "Failed to save prescription to database.";
        }
=======
public class PrescriptionManager implements IManager<Prescription> {

    private static final String FILE_PATH = "data/prescription.txt";

    private static List<Prescription> prescriptionList;

    public PrescriptionManager() {
        prescriptionList = new ArrayList<>();
        loadFromFile();
    }

    @Override
    public String add(Prescription prescription) {

        if (prescription == null) {
            return "Prescription is null.";
        }

        if (findById(prescription.getIdPrescription()) != null) {
            return "Prescription ID already exists.";
        }

        prescriptionList.add(prescription);
        saveToFile();
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc

        return "Prescription added successfully.";
    }

    @Override
    public String update(String id, Prescription newPrescription) {
<<<<<<< HEAD
        Prescription oldPrescription = findById(id);
        if (oldPrescription == null) {
            return "Prescription not found.";
        }

        int index = prescriptionList.indexOf(oldPrescription);
        newPrescription.setIdPrescription(oldPrescription.getIdPrescription());
        prescriptionList.set(index, newPrescription);
        String saveResult = saveToFile();
        if (saveResult.equals("Save failed.")) {
            prescriptionList.set(index, oldPrescription);
            return "Failed to update prescription in database.";
        }
=======

        Prescription prescription = findById(id);

        if (prescription == null) {
            return "Prescription not found.";
        }

        int index = prescriptionList.indexOf(prescription);

        prescriptionList.set(index, newPrescription);

        saveToFile();
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc

        return "Prescription updated successfully.";
    }

    @Override
    public String delete(String id) {
<<<<<<< HEAD
        Prescription prescription = findById(id);
=======

        Prescription prescription = findById(id);

>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
        if (prescription == null) {
            return "Prescription not found.";
        }

<<<<<<< HEAD
        int index = prescriptionList.indexOf(prescription);
        prescriptionList.remove(index);

        String saveResult = saveToFile();
        if (saveResult.equals("Save failed.")) {
            // Rollback: Thêm lại vào đúng vị trí cũ
            prescriptionList.add(index, prescription);
            return "Failed to delete prescription from database.";
        }
=======
        prescriptionList.remove(prescription);

        saveToFile();
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc

        return "Prescription deleted successfully.";
    }

<<<<<<< HEAD
    // ================= FIND =================

    public Prescription findById(String id) {
        for (Prescription prescription : prescriptionList) {
            if (prescription.getIdPrescription().equalsIgnoreCase(id)) {
                return prescription;
            }
        }
=======


    // ================= FIND =================

    public static Prescription findById(String id) {

        for (Prescription prescription : prescriptionList) {

            if (prescription.getIdPrescription().equalsIgnoreCase(id)) {
                return prescription;
            }

        }

>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
        return null;
    }

    // ================= SAVE FILE =================

    private String saveToFile() {
<<<<<<< HEAD
        File folder = new File("data");
=======

        File folder = new File("data");

>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
<<<<<<< HEAD
            for (Prescription prescription : prescriptionList) {
                bw.write(prescription.toFileLine());
                bw.newLine();
            }
            return "Saved successfully.";
        } catch (IOException e) {
=======

            for (Prescription prescription : prescriptionList) {

                bw.write(prescription.toFileLine());
                bw.newLine();

            }

            return "Saved successfully.";

        } catch (IOException e) {

>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
            return "Save failed.";
        }
    }

    // ================= LOAD FILE =================

    private String loadFromFile() {
<<<<<<< HEAD
        File file = new File(FILE_PATH);
=======

        File file = new File(FILE_PATH);

>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
        if (!file.exists()) {
            return "File not found.";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
<<<<<<< HEAD
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Prescription prescription = Prescription.fromFileLine(
                            line,
                            patientManager,
                            doctorManager,
                            medicineManager
                    );
=======

            String line;

            while ((line = br.readLine()) != null) {

                if (!line.trim().isEmpty()) {

                    Prescription prescription =
                            Prescription.fromFileLine(line);
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc

                    if (prescription != null) {
                        prescriptionList.add(prescription);
                    }
                }
            }
<<<<<<< HEAD
            return "Loaded successfully.";
        } catch (IOException e) {
=======

            return "Loaded successfully.";

        } catch (IOException e) {

>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
            return "Load failed.";
        }
    }

    public List<Prescription> getAll() {
<<<<<<< HEAD
        return new ArrayList<>(prescriptionList);
    }

    public void showAll() {
        for (Prescription p : prescriptionList) {
            System.out.println(p.showInfo());
            System.out.println("-------------------------");
        }
=======
        return prescriptionList;
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
    }
}