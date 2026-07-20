package repository;
public class Patient extends Person {

    private final String idPatient;
    private String symptom;

    public Patient(String codePatient, String name, int age, String symptom) {
        super(age, name);
        this.idPatient = codePatient;
        this.symptom = symptom;
    }

    // Getter
    public String getIdPatient() {
        return idPatient;
    }

    public String getSymptom() {
        return symptom;
    }

    // Setter
    public void setSymptom(String symptom) {
        this.symptom = symptom;
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
                parts[1],
                Integer.parseInt(parts[2]),
                parts[3]
        );
    }

    @Override
    public String toString() {
        return showInfo();
    }
}