package repository;

import control.DoctorManager;
import control.MedicineManager;
import control.PatientManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prescription {

    private String idPrescription;
    private Patient patient;
    private Doctor doctor;
    private Medicine medicine;
    private int quantity;
    private String date;
    private static int idCounter = 1;
    private static final String DELIMITER = ",";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public static String today() {
        return LocalDate.now().format(DATE_FORMAT);
    }
    public Prescription(Patient patient, Doctor doctor, Medicine medicine, int quantity, String date) {

        this.idPrescription = "DT" + idCounter;
        this.patient = patient;
        this.doctor = doctor;
        this.medicine = medicine;
        this.quantity = quantity;
        this.date = date;
        idCounter++;
    }

    public static Prescription fromFileLine(String line, PatientManager pMgr, DoctorManager dMgr, MedicineManager mMgr) {
        String[] parts = line.split(DELIMITER);
        if (parts.length != 6) return null;
        Patient p = pMgr.findById(parts[1]);
        Doctor d = dMgr.getDoctor(parts[2]);
        Medicine m = mMgr.getMedicine(parts[3]);

        if (p == null || d == null || m == null) return null;

        int qty = Integer.parseInt(parts[4]);
        String date = parts[5];

        Prescription pres = new Prescription(p, d, m, qty, date);
        pres.setIdPrescription(parts[0]);
        return pres;
    }

    // Getter

    public String getIdPrescription() {
        return idPrescription;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    // Setter

    public void setIdPrescription(String idPrescription) {
        this.idPrescription = idPrescription;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static void setIdCounter(int maxId) {
        idCounter = maxId + 1;
    }

    // Hiển thị thông tin

    public String showInfo() {

        return "Mã đơn thuốc: " + idPrescription
                + "\nBệnh nhân: " + patient.getName()
                + "\nBác sĩ: " + doctor.getName()
                + "\nThuốc: " + medicine.getName()
                + "\nSố lượng: " + quantity
                + "\nNgày kê: " + date;
    }

    // Lưu file

    public String toFileLine() {

        return idPrescription + DELIMITER
                + patient.getIdPatient() + DELIMITER
                + doctor.getIdDoctor() + DELIMITER
                + medicine.getIdMedicine() + DELIMITER
                + quantity + DELIMITER
                + date;
    }

    @Override
    public String toString() {
        return showInfo();
    }
}