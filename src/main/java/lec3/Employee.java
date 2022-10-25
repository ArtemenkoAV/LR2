package lec3;

public class Employee extends Worker implements Departable{
    private String department;

    @Override
    public String getDepartment() {
        return department;
    }
}
