package repository;
<<<<<<< HEAD

import control.DoctorManager;
import control.MedicineManager;
import control.PatientManager;

public class Prescription {

    private String idPrescription;
    private Patient patient;
    private Doctor doctor;
    private Medicine medicine;
    private int quantity;
    private String date;
    private static int idCounter = 1;
    private static final String DELIMITER = ",";
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
=======
public class Prescription {

    private final String idPrescription;
    private Patient patient;
    private Doctor doctor;
    private Medicine medicine;
    private String date;

    public Prescription(String idPrescription,
                        Patient patient,
                        Doctor doctor,
                        Medicine medicine,
                        String date) {

        this.idPrescription = idPrescription;
        this.patient = patient;
        this.doctor = doctor;
        this.medicine = medicine;
        this.date = date;
    }

    public static Prescription fromFileLine(String line) {
        return null;
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
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

<<<<<<< HEAD
    public int getQuantity() {
        return quantity;
    }

=======
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
    public String getDate() {
        return date;
    }

    // Setter

<<<<<<< HEAD
    public void setIdPrescription(String idPrescription) {
        this.idPrescription = idPrescription;
    }

=======
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

<<<<<<< HEAD
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

=======
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
    public void setDate(String date) {
        this.date = date;
    }

<<<<<<< HEAD
    public static void setIdCounter(int maxId) {
        idCounter = maxId + 1;
    }

=======
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
    // Hiển thị thông tin

    public String showInfo() {

        return "Prescription ID: " + idPrescription
                + "\nPatient: " + patient.getName()
                + "\nDoctor: " + doctor.getName()
                + "\nMedicine: " + medicine.getName()
<<<<<<< HEAD
                + "\nQuantity: " + quantity
=======
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
                + "\nDate: " + date;
    }

    // Lưu file

    public String toFileLine() {

<<<<<<< HEAD
        return idPrescription + DELIMITER
                + patient.getIdPatient() + DELIMITER
                + doctor.getIdDoctor() + DELIMITER
                + medicine.getIdMedicine() + DELIMITER
                + quantity + DELIMITER
=======
        return idPrescription + "|"
                + patient.getIdPatient() + "|"
                + doctor.getIdDoctor() + "|"
                + medicine.getIdMedicine() + "|"
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
                + date;
    }

    @Override
    public String toString() {
        return showInfo();
    }
}