package repository;
public class Patient extends Person {

<<<<<<< HEAD
    private String idPatient;
    private String symptom;
    private static int idCounter = 1;
    private static final String DELIMITER = ",";

    public Patient(String name, int age, String symptom) {
        super(age, name);
        this.idPatient = "BN" + idCounter;
        this.symptom = symptom;
        idCounter++;
=======
    private final String idPatient;
    private String symptom;

    public Patient(String codePatient, String name, int age, String symptom) {
        super(age, name);
        this.idPatient = codePatient;
        this.symptom = symptom;
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
    }

    // Getter
    public String getIdPatient() {
        return idPatient;
    }

    public String getSymptom() {
        return symptom;
    }

    // Setter
<<<<<<< HEAD
    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

=======
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

<<<<<<< HEAD
    public static void setIdCounter(int maxId) {
        idCounter = maxId + 1;
    }

=======
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
    @Override
    public String showInfo() {
        return "ID: " + idPatient
                + " | Name: " + getName()
                + " | Age: " + getAge()
                + " | Symptom: " + symptom;
    }

    // Chuyển đối tượng thành chuỗi để lưu file
    public String toFileLine() {
<<<<<<< HEAD
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
=======
        return idPatient + "|"
                + getName() + "|"
                + getAge() + "|"
                + symptom;
    }

    // Đọc một dòng trong file và tạo đối tượng Patient
    public static Patient fromFileLine(String line) {

        String[] parts = line.split("\\|");

        if (parts.length != 5) {
            return null;
        }

        return new Patient(
                parts[0],
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
                parts[1],
                Integer.parseInt(parts[2]),
                parts[3]
        );
<<<<<<< HEAD
        patient.setIdPatient(parts[0]);

        return patient;
=======
>>>>>>> 76c6ef64a1e4d49c5262a5e05b498c3637b276fc
    }

    @Override
    public String toString() {
        return showInfo();
    }
}