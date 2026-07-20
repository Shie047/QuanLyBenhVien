package repository;
public class Prescription {

    private final String codePrescription;
    private Patient patient;
    private Doctor doctor;
    private Medicine medicine;
    private String date;

    public Prescription(String codePrescription,
                        Patient patient,
                        Doctor doctor,
                        Medicine medicine,
                        String date) {

        this.codePrescription = codePrescription;
        this.patient = patient;
        this.doctor = doctor;
        this.medicine = medicine;
        this.date = date;
    }

    public static Prescription fromFileLine(String line) {
        return null;
    }

    // Getter

    public String getCodePrescription() {
        return codePrescription;
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

    public String getDate() {
        return date;
    }

    // Setter

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Hiển thị thông tin

    public String showInfo() {

        return "Prescription ID: " + codePrescription
                + "\nPatient: " + patient.getName()
                + "\nDoctor: " + doctor.getName()
                + "\nMedicine: " + medicine.getName()
                + "\nDate: " + date;
    }

    // Lưu file

    public String toFileLine() {

        return codePrescription + "|"
                + patient.getCodePatient() + "|"
                + doctor.getIdDoctor() + "|"
                + medicine.getCodeMedicine() + "|"
                + date;
    }

    @Override
    public String toString() {
        return showInfo();
    }
}