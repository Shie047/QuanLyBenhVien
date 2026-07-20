import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionManager implements IManager<Prescription> {

    private static final String FILE_PATH = "data/prescription.txt";

    private final List<Prescription> prescriptionList;

    public PrescriptionManager() {
        prescriptionList = new ArrayList<>();
        loadFromFile();
    }

    @Override
    public String add(Prescription prescription) {

        if (prescription == null) {
            return "Prescription is null.";
        }

        if (findById(prescription.getCodePrescription()) != null) {
            return "Prescription ID already exists.";
        }

        prescriptionList.add(prescription);
        saveToFile();

        return "Prescription added successfully.";
    }

    @Override
    public String update(String id, Prescription newPrescription) {

        Prescription prescription = findById(id);

        if (prescription == null) {
            return "Prescription not found.";
        }

        int index = prescriptionList.indexOf(prescription);

        prescriptionList.set(index, newPrescription);

        saveToFile();

        return "Prescription updated successfully.";
    }

    @Override
    public String delete(String id) {

        Prescription prescription = findById(id);

        if (prescription == null) {
            return "Prescription not found.";
        }

        prescriptionList.remove(prescription);

        saveToFile();

        return "Prescription deleted successfully.";
    }

    @Override
    public void showAll() {

        if (prescriptionList.isEmpty()) {
            return;
        }

        for (Prescription prescription : prescriptionList) {
            System.out.println(prescription.showInfo());
        }
    }

    // ================= FIND =================

    public Prescription findById(String id) {

        for (Prescription prescription : prescriptionList) {

            if (prescription.getCodePrescription().equalsIgnoreCase(id)) {
                return prescription;
            }

        }

        return null;
    }

    // ================= SAVE FILE =================

    private String saveToFile() {

        File folder = new File("data");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (Prescription prescription : prescriptionList) {

                bw.write(prescription.toFileLine());
                bw.newLine();

            }

            return "Saved successfully.";

        } catch (IOException e) {

            return "Save failed.";
        }
    }

    // ================= LOAD FILE =================

    private String loadFromFile() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return "File not found.";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (!line.trim().isEmpty()) {

                    Prescription prescription =
                            Prescription.fromFileLine(line);

                    if (prescription != null) {
                        prescriptionList.add(prescription);
                    }
                }
            }

            return "Loaded successfully.";

        } catch (IOException e) {

            return "Load failed.";
        }
    }

    public List<Prescription> getAll() {
        return prescriptionList;
    }
}