import java.io.Serializable;

public class Doctor extends Person implements Serializable {
    private String idDoctor;
    private String department;
    private static int idCounter = 1;

    public Doctor(int age, String name, String department) {
        super(age, name);
        this.idDoctor = "BS" + idCounter;
        this.department = department;
        idCounter++;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public static void setIdCounter(int maxId) {
        idCounter = maxId + 1;
    }

    @Override
    public void showInfo() {
        System.out.println(idDoctor + " \nTên: " + getName() + " \nTuổi: " + getAge() + " \nKhoa: " + department);
    }
}
