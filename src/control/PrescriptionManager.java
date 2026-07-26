package control;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import repository.*;

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

        return "Prescription added successfully.";
    }

    @Override
    public String update(String id, Prescription newPrescription) {
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

        return "Prescription updated successfully.";
    }

    @Override
    public String delete(String id) {
        Prescription prescription = findById(id);
        if (prescription == null) {
            return "Prescription not found.";
        }

        int index = prescriptionList.indexOf(prescription);
        prescriptionList.remove(index);

        String saveResult = saveToFile();
        if (saveResult.equals("Save failed.")) {
            prescriptionList.add(index, prescription);
            return "Failed to delete prescription from database.";
        }

        return "Prescription deleted successfully.";
    }


    public int getAvailableQuantity(Prescription old, Medicine medicine) {
        int available = medicine.getQuantity();
        if (medicine.getIdMedicine().equals(old.getMedicine().getIdMedicine())) {
            available += old.getQuantity();
        }
        return available;
    }

    public Prescription addPrescription(Patient patient, Doctor doctor, Medicine medicine, int quantity) {
        if (quantity > medicine.getQuantity()) {
            throw new IllegalArgumentException(
                    "Số lượng vượt quá tồn kho hiện có (" + medicine.getQuantity() + ").");
        }

        Prescription prescription = new Prescription(patient, doctor, medicine, quantity, Prescription.today());
        String result = add(prescription);
        if (!"Prescription added successfully.".equals(result)) {
            throw new IllegalStateException(result);
        }

        Medicine updatedMedicine = new Medicine(medicine.getIdMedicine(), medicine.getName(),
                medicine.getUnitPrice(), medicine.getQuantity() - quantity);
        medicineManager.update(medicine.getIdMedicine(), updatedMedicine);

        return prescription;
    }

    public Prescription editPrescription(String id, Patient patient, Doctor doctor, Medicine medicine, int quantity) {
        Prescription old = findById(id);
        if (old == null) {
            throw new IllegalArgumentException("Không tìm thấy đơn thuốc có mã " + id);
        }

        int available = getAvailableQuantity(old, medicine);
        if (quantity > available) {
            throw new IllegalArgumentException("Số lượng vượt quá tổng khả dụng (" + available + ").");
        }

        Prescription temp = new Prescription(patient, doctor, medicine, quantity, Prescription.today());
        String result = update(id, temp);
        if (!"Prescription updated successfully.".equals(result)) {
            throw new IllegalStateException(result);
        }

        // Hoàn kho thuốc cũ
        Medicine oldMedInDb = medicineManager.getMedicine(old.getMedicine().getIdMedicine());
        if (oldMedInDb != null) {
            Medicine refundedMed = new Medicine(oldMedInDb.getIdMedicine(), oldMedInDb.getName(),
                    oldMedInDb.getUnitPrice(), oldMedInDb.getQuantity() + old.getQuantity());
            medicineManager.update(oldMedInDb.getIdMedicine(), refundedMed);
        }

        // Trừ kho thuốc mới
        Medicine newMedInDb = medicineManager.getMedicine(medicine.getIdMedicine());
        if (newMedInDb != null) {
            Medicine deductedMed = new Medicine(newMedInDb.getIdMedicine(), newMedInDb.getName(),
                    newMedInDb.getUnitPrice(), newMedInDb.getQuantity() - quantity);
            medicineManager.update(newMedInDb.getIdMedicine(), deductedMed);
        }

        return findById(id);
    }


    public Prescription findById(String id) {
        for (Prescription prescription : prescriptionList) {
            if (prescription.getIdPrescription().equalsIgnoreCase(id)) {
                return prescription;
            }
        }
        return null;
    }



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



    private String loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return "File not found.";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Prescription prescription = Prescription.fromFileLine(
                            line,
                            patientManager,
                            doctorManager,
                            medicineManager
                    );

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
        return new ArrayList<>(prescriptionList);
    }

    public void showAll() {
        for (Prescription p : prescriptionList) {
            System.out.println(p.showInfo());
            System.out.println("-------------------------");
        }
    }
}