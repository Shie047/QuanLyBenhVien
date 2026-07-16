public class Doctor extends Person {

    private final String codeDoctor;
    private String specialty;
    private int yearsOfExperience;
    public Doctor(String codeDoctor, String name, int age, String sex,
                  String specialty, int yearsOfExperience) {

        super(name, age, sex);
        this.codeDoctor = codeDoctor;
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getCodeDoctor() {
        return codeDoctor;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String showInfo() {
        return "Code: " + codeDoctor
                + " | Name: " + getName()
                + " | Age: " + getAge()
                + " | Sex: " + getSex()
                + " | Specialty: " + specialty
                + " | Experience: " + yearsOfExperience + " years";
    }

    // Lưu xuống file
    public String toFileLine() {
        return codeDoctor + "|"
                + getName() + "|"
                + getAge() + "|"
                + getSex() + "|"
                + specialty + "|"
                + yearsOfExperience;
    }

    // Đọc từ file
    public static Doctor fromFileLine(String line) {

        String[] parts = line.split("\\|");

        if (parts.length != 6) {
            return null;
        }

        return new Doctor(
                parts[0],
                parts[1],
                Integer.parseInt(parts[2]),
                parts[3],
                parts[4],
                Integer.parseInt(parts[5])
        );
    }

    @Override
    public String toString() {
        return showInfo();
    }
}