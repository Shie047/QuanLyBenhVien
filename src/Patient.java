public class Patient extends Person {

    private final String codePatient;
    private String symptom;

    public Patient(String codePatient, String name, int age, String sex, String symptom) {
        super(name, age, sex);
        this.codePatient = codePatient;
        this.symptom = symptom;
    }

    // Getter
    public String getCodePatient() {
        return codePatient;
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
        return "Code: " + codePatient
                + " | Name: " + getName()
                + " | Age: " + getAge()
                + " | Sex: " + getSex()
                + " | Symptom: " + symptom;
    }

    // Chuyển đối tượng thành chuỗi để lưu file
    public String toFileLine() {
        return codePatient + "|"
                + getName() + "|"
                + getAge() + "|"
                + getSex() + "|"
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
                parts[3],
                parts[4]
        );
    }

    @Override
    public String toString() {
        return showInfo();
    }
}