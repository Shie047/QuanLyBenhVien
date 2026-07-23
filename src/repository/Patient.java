package repository;
public class Patient extends Person {

    private String idPatient;
    private String symptom;
    private static int idCounter = 1;
    private static final String DELIMITER = ",";

    public Patient(String name, int age, String symptom) {
        super(age, name);
        this.idPatient = "BN" + idCounter;
        this.symptom = symptom;
        idCounter++;
    }

    // Getter
    public String getIdPatient() {
        return idPatient;
    }

    public String getSymptom() {
        return symptom;
    }

    // Setter
    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public static void setIdCounter(int maxId) {
        idCounter = maxId + 1;
    }

    @Override
    public String showInfo() {
        return "ID: " + idPatient
                + " | Name: " + getName()
                + " | Age: " + getAge()
                + " | Symptom: " + symptom;
    }

    // Chuyển đối tượng thành chuỗi để lưu file
    public String toFileLine() {
        return idPatient + DELIMITER
                + getName() + DELIMITER
                + getAge() + DELIMITER
                + symptom;
    }


    public static Patient fromFileLine(String line) {

        String[] parts = line.split(DELIMITER);

        if (parts.length != 4) {
            return null;
        }

        Patient patient = new Patient(
                parts[1],
                Integer.parseInt(parts[2]),
                parts[3]
        );
        patient.setIdPatient(parts[0]);

        return patient;
    }

    @Override
    public String toString() {
        return showInfo();
    }
}