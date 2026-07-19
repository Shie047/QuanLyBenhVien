public abstract class Person {
    private String name;
    private int age;

    public Person(int age, String name) {
        setAge(age);
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Tuổi không hợp lệ: " + age);
        }
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String showInfo();

    @Override
    public String toString() {
        return showInfo();
    }
}