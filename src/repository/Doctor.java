package repository;
public class Doctor extends Person {
    private String idDoctor;
    private String department;
    private static int idCounter = 1;
    public static final int MIN_AGE = 18;

    public Doctor(int age, String name, String department) {
        super(age, name);
        this.idDoctor = "BS" + idCounter;
        this.department = department;
        idCounter++;
    }
    
    @Override
    public void setAge(int age) {
        if (age < MIN_AGE) {
            throw new IllegalArgumentException("Tuổi bác sĩ phải từ " + MIN_AGE + " trở lên: " + age);
        }
        super.setAge(age);
    }
    public static String getDepartmentName(int choice) {
        switch (choice) {
            case 1: return "Khoa Nội";
            case 2: return "Khoa Ngoại";
            case 3: return "Khoa Nhi";
            case 4: return "Khoa Cấp cứu";
            default: return "Chưa phân bổ";
        }
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
    public String showInfo() {
        return "Mã Bác Sĩ:" + idDoctor + "| Tên: " + getName() + "| Tuổi: " + getAge() + "| Khoa: " + department;
    }
}