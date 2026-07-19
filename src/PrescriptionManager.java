import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionManager implements IManager<Prescription> {

    private static final String FILE_PATH = "data/prescription.txt";

    private List<Prescription> prescriptionList;

    private PatientManager patientManager;
    private DoctorManager doctorManager;
    private MedicineManager medicineManager;

    public PrescriptionManager(PatientManager patientManager,
                               DoctorManager doctorManager,
                               MedicineManager medicineManager) {

        this.patientManager = patientManager;
        this.doctorManager = doctorManager;
        this.medicineManager = medicineManager;

        prescriptionList = new ArrayList<>();
        loadFromFile();
    }

    @Override
    public void Add(Prescription prescription) {

        if (findById(prescription.getCodePrescription()) != null) {
            System.out.println("Ma don thuoc da ton tai!");
            return;
        }

        prescriptionList.add(prescription);
        saveToFile();

        System.out.println("Them don thuoc thanh cong!");
    }

    @Override
    public void Update(String ma, Prescription newPrescription) {

        Prescription prescription = findById(ma);

        if (prescription == null) {
            System.out.println("Khong tim thay don thuoc!");
            return;
        }

        prescription.setPatient(newPrescription.getPatient());
        prescription.setDoctor(newPrescription.getDoctor());
        prescription.setMedicine(newPrescription.getMedicine());
        prescription.setDate(newPrescription.getDate());

        saveToFile();

        System.out.println("Cap nhat thanh cong!");
    }

    @Override
    public void Add(Patient patient) {

    }

    @Override
    public void Update(String ma, Patient newPatient) {

    }

    @Override
    public void Delete(String ma) {

        Prescription prescription = findById(ma);

        if (prescription == null) {
            System.out.println("Khong tim thay don thuoc!");
            return;
        }

        prescriptionList.remove(prescription);

        saveToFile();

        System.out.println("Xoa thanh cong!");
    }

    @Override
    public void ShowAll() {

        if (prescriptionList.isEmpty()) {
            System.out.println("Danh sach don thuoc rong!");
            return;
        }

        for (Prescription prescription : prescriptionList) {
            System.out.println(prescription.showInfo());
            System.out.println("--------------------------------");
        }
    }

    @Override
    public String add(Prescription Object) {
        return "";
    }

    @Override
    public String update(String MA, Prescription NewObject) {
        return "";
    }

    @Override
    public String delete(String MA) {
        return "";
    }

    @Override
    public void showAll() {

    }

    @Override
    public List<Medicine> getAll() {
        return List.of();
    }

    // ================= TIM KIEM =================

    public Prescription findById(String code) {

        for (Prescription prescription : prescriptionList) {

            if (prescription.getCodePrescription().equalsIgnoreCase(code)) {
                return prescription;
            }

        }

        return null;
    }

    // ================= LUU FILE =================

    private void saveToFile() {

        File folder = new File("data");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (Prescription prescription : prescriptionList) {

                bw.write(prescription.toFileLine());
                bw.newLine();

            }

        } catch (IOException e) {

            System.out.println("Loi luu file!");

        }
    }

    // ================= DOC FILE =================

    private void loadFromFile() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (!line.trim().isEmpty()) {

                    Prescription prescription =
                            Prescription.fromFileLine(
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

        } catch (IOException e) {

            System.out.println("Loi doc file!");

        }

    }

    public List<Prescription> getPrescriptionList() {
        return prescriptionList;
    }

}