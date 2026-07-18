class Doctor extends Person {
    private String idDoctor;
    private String department;
    private static int ID = 1;

    public Doctor(int age, String name,String department) {
        super(age, name);
        this.idDoctor = "BS" + ID;
        this.department=department;
        ID++;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIDDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public void showInfo() {
        System.out.println( idDoctor + " \nTên: " + name + " \nTuổi: " + age + " \nKhoa: " + department);
    }

}
