public class Patient extends Person {


    private final String codePatient;
    private String symptom;

    public Patient(String codePatient, String name, int age, String sex, String symptom) {
        super(name, age, sex);
        this.codePatient = codePatient;
        this.symptom = symptom;
    }

    // Chi co getter, KHONG co setter cho maBenhNhan -> dam bao khong the chinh sua
    public String getCodePatient() {
        return codePatient;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    @Override
    public String showInfo() {
        return String.format("[Patient] Code: %-8s | Name: %-20s | Age: %-3d | Sex: %-6s | Symptom: %s", codePatient, getName(), getAge(), getSex(), symptom);
    }


    public String toFileLine() {
        return codePatient + "|" + getName() + "|" + getAge() + "|" + getSex() + "|" + symptom;
    }


    public static Patient fromFileLine(String line) {
        String[] parts = line.split("\\|", -1);
        return new Patient(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4]);
    }
}