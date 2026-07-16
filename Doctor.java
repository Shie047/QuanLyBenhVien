public class Doctor extends Person {

    private final String codeDoctor;
    private String specialty;

    public Doctor(String codeDoctor, String name, int age, String sex, String specialty) {
        super(name, age, sex);
        this.codeDoctor = codeDoctor;
        this.specialty = specialty;
    }

    public String getCodeDoctor() {
        return codeDoctor;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String showInfo() {
        return "Code: " + codeDoctor
                + " | Name: " + getName()
                + " | Age: " + getAge()
                + " | Sex: " + getSex()
                + " | Specialty: " + specialty;
    }

    // Chuyển đối tượng thành chuỗi để lưu file
    public String toFileLine() {
        return codeDoctor + "|"
                + getName() + "|"
                + getAge() + "|"
                + getSex() + "|"
                + specialty;
    }

    public static Doctor fromFileLine(String line) {

        String[] parts = line.split("\\|");

        if (parts.length != 5) {
            return null;
        }

        return new Doctor(
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