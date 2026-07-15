class Doctor extends Person {
    private String maBS;
    private String Department;
    private static int ID = 1;

    public Doctor(int age, String name,String Department) {
        super(age, name);
        this.maBS = "BS" + ID;
        this.Department=Department;
        ID++;
    }

    public String getMaBS() {
        return maBS;
    }
    public void setMaBS(String maBS) {
        this.maBS = maBS;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    @Override
    public void showInfo() {
        System.out.println( maBS + " \nTên: " + name + " \nTuổi: " + age + " \nKhoa: " + Department);
    }

}
